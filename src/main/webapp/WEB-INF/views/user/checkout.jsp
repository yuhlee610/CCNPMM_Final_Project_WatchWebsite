<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<body>
	<!--================Checkout Area =================-->
	<section class="checkout_area section_padding"
		style="padding-top: 100px;">
		<div class="container">
			<div class="billing_details">
				<div class="row">
					<div class="col-lg-4">
						<h3>Billing Details</h3>
						<form class="row contact_form" action="#" method="post"
							novalidate="novalidate">
							<p>Customer name:</p>
							<div class="col-md-12 form-group p_star">
								<input type="text" class="form-control" id="name" name="name"
									value="${user.name }" placeholder="Customer name" required />

							</div>
							<p>Phone number:</p>
							<div class="col-md-12 form-group p_star">
								<input type="text" class="form-control" id="phone" name="phone"
									value="${user.phone }" placeholder="Phone number"
									pattern="^[0-9]+$" required />

							</div>
							<p>Address:</p>
							<div class="col-md-12 form-group p_star">
								<input type="text" class="form-control" id="address"
									name="address" value="${user.address }" placeholder="Address"
									required />

							</div>
						</form>
					</div>
					<div class="col-lg-8">
						<div class="order_box">
							<h2>Your Order</h2>
							<ul class="list">
								<li><a href="#">Product <span>Total</span>
								</a></li>
								<c:forEach var="i" items="${listCart}">
									<li><a href="#"> ${i.name }
										<span class="middle">x${i.count}</span>
										<span class="last">$${i.count * i.price }</span>
									</a></li>
								</c:forEach>
							</ul>
							<ul class="list list_2">
								<li><a href="#">Subtotal <span>$${total}</span>
								</a></li>
								<li><a href="#">Total <span>$${total}</span>
								</a></li>
							</ul>
							<div class="payment_item">
								<div class="radion_btn">
									<input type="radio" id="f-option5" name="selector" /> <label
										for="f-option5">Check payments</label>
									<div class="check"></div>
								</div>
								<p>Please send a check to Store Name, Store Street, Store
									Town, Store State / County, Store Postcode.</p>
							</div>
							<div class="payment_item active">
								<div class="radion_btn">
									<input type="radio" id="f-option6" name="selector" /> <label
										for="f-option6">Paypal </label> <img
										src="img/product/single-product/card.jpg" alt="" />
									<div class="check"></div>
								</div>
								<p>Please send a check to Store Name, Store Street, Store
									Town, Store State / County, Store Postcode.</p>
							</div>
							<div class="creat_account">
								<input type="checkbox" id="f-option4" name="selector" /> <label
									for="f-option4">I’ve read and accept the </label> <a href="#">terms
									& conditions*</a>
							</div>
							<a class="btn_3" href="#">Proceed to Paypal</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--================End Checkout Area =================-->
</body>
</html>