package com.ccnpmm.controller;

import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ccnpmm.dao.CartDAO;
import com.ccnpmm.dao.DetailCartDAO;
import com.ccnpmm.dao.OrderDAO;
import com.ccnpmm.dao.OrderDetailDAO;
import com.ccnpmm.dao.ProductDAO;
import com.ccnpmm.entity.DetailCart;
import com.ccnpmm.entity.Order;
import com.ccnpmm.entity.Order1;
import com.ccnpmm.entity.OrderDetail;
import com.ccnpmm.entity.Product;
import com.ccnpmm.service.PaypalPaymentIntent;
import com.ccnpmm.service.PaypalPaymentMethod;
import com.ccnpmm.utils.Utils;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

@Controller
@RequestMapping("/paypal/")
public class PaypalController {
	public static final String URL_PAYPAL_SUCCESS = "success";
	public static final String URL_PAYPAL_CANCEL = "cancel";
	private static final String CLIENT_ID = "AWGrUtMXpzPBPa2PZOnmE4obgbnJHOPArQYzchPDtOr5zWOHQDRr-YM4qB73BHawXhb23f0To5oGLDRx";
	private static final String CLIENT_SECRET = "EPkXluGafYqJh6YEIiALKKgnCAsicy46PAjFQi3VTp4c4QysCSBykRfaLxy5S3XwQza57SXlqtFn7P5c";
	private static final String MODE = "sandbox";

	private Logger log = LoggerFactory.getLogger(getClass());
	private APIContext apiContext = null;

	@Autowired
	private Common common;
	@Autowired
	private DetailCartDAO detailCartDao;
	@Autowired
	CartDAO cartDao;
	@Autowired
	OrderDAO orderDao;
	@Autowired
	ProductDAO productDao;
	@Autowired
	OrderDetailDAO orderDetailDao;
	private Order1 orderInfo = new Order1();
	private Integer userId = 0;

	@RequestMapping("/authorize_payment")
	public String authorize_paypal(ModelMap model, HttpServletRequest request, @RequestParam("radioName") String check,
			@RequestParam(value = "name", required = true) String customerName,
			@RequestParam(value = "phone", required = true) String phone,
			@RequestParam(value = "address", required = true) String address) {
		// Set orderInfo

		String username = customerName;
		String phoneNumber = phone;
		String address1 = address;
		orderInfo.setName(username);
		orderInfo.setPhone(phoneNumber);
		orderInfo.setAddress(address1);

		while (true) {
			String code = common.setCodeOrder();
			Order order = new Order();
			try {
				order = orderDao.getByCode(code);
			} catch (Exception e) {
				order = null;
			}
			if(order == null) {
				orderInfo.setCode(code);
				break;
			}
		}

		// Kiem tra dang nhap
		try {
			userId = common.Login(request);
		} catch (Exception e) {

		}
		if (userId == 0)
			return "redirect:/";

		// Lay danh sach trong gio hang
		List<DetailCart> cartList = null;
		Float sum = (float) 0;
		cartList = detailCartDao.getByUserId(userId);

		
		//Giỏ hàng trống
		if(cartList.isEmpty()) {
			model.addAttribute("message", "Không có sản phẩm trong giỏ hàng");
			return "redirect:/checkout/";
		}
		for (final DetailCart dc : cartList) {
			sum = sum + dc.getPrice() * dc.getCount();
		}

		orderInfo.setUserId(userId);
		orderInfo.setTotal(Double.valueOf(sum));
		orderInfo.setDeliveryStatus("1");

		if (check.equals("paypal")) {
			String cancelUrl = Utils.getBaseURL(request) + "/paypal/" + URL_PAYPAL_CANCEL;
			String successUrl = Utils.getBaseURL(request) + "/paypal/" + URL_PAYPAL_SUCCESS;

			Double total = Double.valueOf(sum);
			Double tax = Double.valueOf(0);
			Double shipping = Double.valueOf(0);
			total = total + shipping;

			String currency = "USD";
			PaypalPaymentMethod method = PaypalPaymentMethod.paypal;
			PaypalPaymentIntent intent = PaypalPaymentIntent.sale;
			String description = "Thanh toan Paypal";

			try {

				Details details = new Details();
				details.setShipping(String.format("%.2f", shipping));
				details.setSubtotal(String.format("%.2f", total));
				details.setTax(String.format("%.2f", tax));

				Amount amount = new Amount();
				amount.setCurrency(currency);
				amount.setTotal(String.format("%.2f", total));
				amount.setDetails(details);

				Transaction transaction = new Transaction();

				ItemList itemList = new ItemList();
				List<Item> items = new ArrayList<Item>();

				if (cartList != null) {
					for (DetailCart itemCart : cartList) {
						Item item = new Item();
						item.setCurrency(currency).setName(itemCart.getName())
								.setPrice(String.format("%.2f", Double.valueOf(itemCart.getPrice())))
								.setTax(String.format("%.2f", tax)).setQuantity(itemCart.getCount().toString())
								.setSku("Sku");

						items.add(item);
					}
				}

				itemList.setItems(items);
				transaction.setAmount(amount);
				transaction.setDescription(description);
				transaction.setItemList(itemList);
				transaction.setInvoiceNumber("#a5D30xCdv6");

				// Add listTransaction
				List<Transaction> listTransaction = new ArrayList<Transaction>();
				listTransaction.add(transaction);

				RedirectUrls redirectUrls = new RedirectUrls();
				redirectUrls.setCancelUrl(cancelUrl);
				redirectUrls.setReturnUrl(successUrl);

				// Set Payer
				Payer payer = new Payer();
				payer.setPaymentMethod(method.toString());

				Payment payment = new Payment();
				payment.setTransactions(listTransaction);
				payment.setPayer(payer);
				payment.setIntent(intent.toString());
				payment.setRedirectUrls(redirectUrls);

				apiContext = getapiContext();
				apiContext.setMaskRequestId(true);

				payment = payment.create(apiContext);

				for (Links link : payment.getLinks()) {
					if (link.getRel().equals("approval_url")) {
						return "redirect:" + link.getHref();
					}
				}
			} catch (PayPalRESTException e) {
				log.error(e.getMessage());
			}
		}

		else if (check.equals("cod")) {
			if (addOrder()) {
				if (addOrderdeleteCart(userId, orderInfo.getCode())) {
					return "redirect:/order";
				}
			}
		}
		return "redirect:/";
	}

	@RequestMapping(URL_PAYPAL_SUCCESS)
	public String sucessPay(@RequestParam("paymentId") String paymentId,@RequestParam("token") String token, @RequestParam("PayerID") String payerId) {
		try {
			Payment payment = executePayment(paymentId, payerId);
			if (payment.getState().equals("approved")) {

				if (addOrder()) {
					if (addOrderdeleteCart(userId, orderInfo.getCode())) {
						return "user/success";
					}
				}
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		if (addOrder()) {
			if (addOrderdeleteCart(userId, orderInfo.getCode())) {
				return "redirect:/order";
			}
		}
		return "redirect:/";
	}

	@RequestMapping(URL_PAYPAL_CANCEL)
	public String cancelPay() {
		return "cancel";
	}

	// thực hiện giao dich
	public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
		Payment payment = new Payment();
		payment.setId(paymentId);
		PaymentExecution paymentExecution = new PaymentExecution();
		paymentExecution.setPayerId(payerId);
		return payment.execute(apiContext, paymentExecution);
	}

	// Congig Paypal
	public Map<String, String> paypalSdkConfig() {
		Map<String, String> sdkConfig = new HashMap();
		sdkConfig.put("mode", MODE);
		return sdkConfig;
	}

	public OAuthTokenCredential authTokenCredential() {
		return new OAuthTokenCredential(CLIENT_ID, CLIENT_SECRET, paypalSdkConfig());
	}

	public APIContext getapiContext() throws PayPalRESTException {
		APIContext apiContext = new APIContext(authTokenCredential().getAccessToken());
		apiContext.setConfigurationMap(paypalSdkConfig());
		return apiContext;
	}

	public boolean addOrderdeleteCart(Integer userId, String code) {
		try {
			int bool = 1;
			// Lay danh sach trong gio hang
			List<DetailCart> cartList = null;
			Order order = orderDao.getByCode(code);
			cartList = detailCartDao.getByUserId(userId);

			for (final DetailCart dc : cartList) {
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setOrderId(order.getOrderId());
				orderDetail.setProductName(dc.getName());
				orderDetail.setCount(dc.getCount());
				orderDetail.setPrice(Double.valueOf(dc.getPrice()));
				orderDetail.setProductId(dc.getProductId());
				
				Product product = productDao.getById(dc.getProductId());
				product.setAmount(product.getAmount() - dc.getCount());
				product.setSold(product.getSold() + dc.getCount());
				
				try {
					orderDetailDao.insert(orderDetail);
					productDao.update(product);
				} catch (Exception e) {
					bool = 0;
				}

			}

			if (bool == 1) {
				try {
					cartDao.deleteCart(userId);
					return true;
				} catch (Exception e) {
				}
			}

		} catch (Exception e) {

		}
		return false;
	}

	public boolean addOrder() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		
		String orderDate = dtf.format(now).toString();
		orderInfo.setOrderDate(orderDate);

		try {
			orderDao.insert1(orderInfo);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return false;
	}

}
