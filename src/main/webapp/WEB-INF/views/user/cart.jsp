<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<c:if test="${not empty message}">
	<script>alert("${message}")</script>
</c:if>
<%
	request.getSession().setAttribute("message", "");
%>

<!-- Hero Area Start-->
<div class="slider-area ">
	<div class="single-slider slider-height2 d-flex align-items-center">
		<div class="container">
			<div class="row">
				<div class="col-xl-12">
					<div class="hero-cap text-center">
						<h2>Cart List</h2>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!--================Cart Area =================-->
<section class="cart_area section_padding">
	<div class="container">
		<div class="cart_inner">
			<div class="table-responsive">
				<table class="table">
					<thead>
						<tr>
							<th scope="col">Product</th>
							<th scope="col">Price</th>
							<th scope="col">Quantity</th>
							<th scope="col">Total</th>
							<th scope="col"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="c" items="${items}">
							<tr>
								<form method="get" action="updateCart">
								<input type="hidden" name="productId" value="${c.getProductId() }"/>
								<td>
									<div class="media">
										<div class="d-flex">
											<img src="${c.getImage()}" alt="" />
										</div>
										<div class="media-body">
											<p>${c.getName() }</p>
										</div>
									</div>
								</td>
								<td>
									<h5>$${c.getPrice() }</h5>
								</td>
								<td>
									<div class="number-input">
										<div
											onclick="this.parentNode.querySelector('input[type=number]').stepDown()"
											class="minus">-</div>
										<input class="quantity" min="0" max="${c.getAmount()}"
											name="quantity" value="${c.getCount()}" type="number">
										<div
											onclick="this.parentNode.querySelector('input[type=number]').stepUp()"
											class="plus">+</div>
									</div>
								</td>
								<td>
									<h5>$${c.getPrice() * c.getCount() }</h5>
								</td>
								<td>
									<button class='btn_3' style='cursor: pointer'>Update</button>
								</td>
								</form>
							</tr>
						</c:forEach>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td>
								<h5>Subtotal</h5>
							</td>
							<td>$${total }</td>
						</tr>
						<tr class="shipping_area">
							<td></td>
							<td></td>
							<td></td>
							<td>
								<h5>Shipping</h5>
							</td>
							<td>$0</td>
						</tr>
					</tbody>
				</table>
				<div class="checkout_btn_inner float-right">
					<a class="btn_1" href="#">Continue Shopping</a> <a
						class="btn_1 checkout_btn_1" href="checkout/">Proceed to checkout</a>
				</div>
			</div>
		</div>
</section>
<!--================End Cart Area =================-->