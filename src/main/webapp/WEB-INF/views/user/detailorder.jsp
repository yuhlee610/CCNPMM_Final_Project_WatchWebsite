
<link rel="stylesheet" href="resources/assets/css/TrackingStyles.css">
<!-- CSS here -->
<link rel="stylesheet" href="resources/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/assets/css/owl.carousel.min.css">
<link rel="stylesheet" href="resources/assets/css/flaticon.css">
<link rel="stylesheet" href="resources/assets/css/slicknav.css">
<link rel="stylesheet" href="resources/assets/css/animate.min.css">
<link rel="stylesheet" href="resources/assets/css/magnific-popup.css">
<link rel="stylesheet"
	href="resources/assets/css/fontawesome-all.min.css">
<link rel="stylesheet" href="resources/assets/css/themify-icons.css">
<link rel="stylesheet" href="resources/assets/css/slick.css">
<link rel="stylesheet" href="resources/assets/css/nice-select.css">
<link rel="stylesheet" href="resources/assets/css/style.css">
<style type="text/css">
.number {
	margin: 100px;
}

.minus, .plus {
	width: 20px;
	height: 20px;
	background: #f2f2f2;
	border-radius: 4px;
	padding: 8px 5px 8px 5px;
	border: 1px solid #ddd;
	/* display: inline-block; */
	vertical-align: middle;
	text-align: center;
	font-size: x-large;
}

input {
	height: min-content;
	width: 100px;
	text-align: center;
	font-size: 26px;
	border: 1px solid #ddd;
	border-radius: 4px;
	display: inline-block;
	vertical-align: middle;
	font-size: larger;
}
</style>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!--================Cart Area =================-->
<section class="cart_area section_padding" style="padding-top: 100px;">
	<div class="container">
		<div>
		<div class="tracking-progress-bar">
				<div class="tracking-progress-bar__item tracking-progress-bar__item--first tracking-progress-bar__item">&nbsp</div>
				<span class="tracking-progress-bar__item__bar tracking-progress-bar__item__bar"></span>
				<div class="tracking-progress-bar__item tracking-progress-bar__item">&nbsp</div>

				<span class="tracking-progress-bar__item__bar tracking-progress-bar__item__bar"> </span>
				<div class="tracking-progress-bar__item tracking-progress-bar__item">&nbsp</div>

				<span class="tracking-progress-bar__item__bar tracking-progress-bar__item__bar"> </span>
				<div class="tracking-progress-bar__item tracking-progress-bar__item">&nbsp</div>

				<span class="tracking-progress-bar__item__bar tracking-progress-bar__item__bar"> </span>
				<div class="tracking-progress-bar__item tracking-progress-bar__item">&nbsp</div>

		</div>
		<br>
		<section class="confirmation_part section_padding"
			style="padding-top: 50px; padding-bottom: 50px;">
			<div class="container">
				<div class="row">
					<div class="col-lg-8 col-lx-4">
						<div class="single_confirmation_details">
							<h4>order info</h4>
							<ul>
								<li>
									<p>order number</p> <span>: ${order.code }</span>
								</li>
								<c:choose>
									<c:when test="${order.deliveryStatus =='1'}">
								        <li>
									<p>order status</p> <span>: Waiting Confirm</span>
								</li>
									</c:when >
									<c:when test="${order.deliveryStatus =='2'}">
								        <li>
									<p>order status</p> <span>: Confirmed</span>
								</li>
									</c:when >
									<c:when test="${order.deliveryStatus =='3'}">
								        <li>
									<p>order status</p> <span>: Delivering</span>
								</li>
									</c:when >
									<c:when test="${order.deliveryStatus =='4'}">
								        <li>
									<p>order status</p> <span>: Success</span>
								</li>
									</c:when >
									<c:when test="${order.deliveryStatus =='5'}">
								        <li>
									<p>order status</p> <span>: Canceled</span>
								</li>
									</c:when >
									<c:otherwise>
								        <li>
									<p>order status</p> <span>: Unknown</span>
								</li>
									</c:otherwise>
								</c:choose>
								<li>
									<p>date</p> <span>: ${order.orderDate }</span>
								</li>
								<li>
									<p>total</p> <span>: USD ${order.total }</span>
								</li>
								<li>
									<!-- <p>Payment method</p>
										<span>: Check payments</span> -->
								</li>
								<li>
									<p>Shipping Address</p> <span>: ${order.address}</span>
								</li>

							</ul>
						</div>
					</div>
				</div>
			</div>
		</section>
		<br>
	</div>
	<div class="cart_inner">
		<div class="table-responsive">
			<table class="table">
				<thead>
					<tr>
						<th scope="col">Product</th>
						<th scope="col">Price</th>
						<th scope="col">Quantity</th>
						<th scope="col">Total</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="i" items="${listOrderItem}">
						<tr>
							<td>
								<div class="media">
									<div class="d-flex">
										<img src="${i.image}" alt="" />
									</div>
									<div class="media-body">
										<p>${i.name }</p>
										<p>Product ID: ${i.productId }</p>
									</div>
								</div>
							</td>
							<td>
								<h5 id="Price_${i.productId}">$${i.price }</h5>
							</td>
							<td>
								<h5>${i.count }</h5>
							</td>
							<td>
								<h5 id="totalItem_${i.productId}">$${i.count*i.price }</h5>
							</td>
						</tr>

					</c:forEach>

					<tr>
						<td></td>
						<td></td>
						<td>
							<h4>Total</h4>
						</td>
						<td>
							<!-- <h4 id="total-cart">$${total}</h4> -->
						</td>
					</tr>
				</tbody>
			</table>

		</div>
	</div>
	<script>
		function myDecFunction(id) {
			var subTotal = parseFloat((document
					.getElementById(String("total-cart")).innerHTML).substr(1));
			var quantity1 = parseInt(document.getElementById(String("Quantity_"
					+ id)).value) - 1;
			if (quantity1 == 0) {
				if (confirm('Are you sure you want to delete this product out of cart?')) {
					$.ajax({
						url : "cart/delete",
						type : 'POST',
						data : {
							productId : id
						},
						success : function(result) {
							if (result == "true") {
								alert("Product was deleted!");

							}
						}
					});
				}
			}
			;
			var price = document.getElementById(String("Price_" + id)).innerHTML;
			var floatPrice = parseFloat(price.substr(1));
			$
					.ajax({
						url : "cart/changeCount",
						type : 'POST',
						data : {
							productId : id,
							quantity : quantity1
						},
						success : function(result) {
							if (result == "true") {
								document
										.getElementById(String("Quantity_" + id)).value = quantity1;
								document.getElementById(String("totalItem_"
										+ id)).innerHTML = "$"
										+ (quantity1 * floatPrice);
								document.getElementById("total-cart").innerHTML = "$"
										+ (subTotal - floatPrice);
							}

						}
					});
		};

		function myIncFunction(id) {
			var subTotal = parseFloat((document
					.getElementById(String("total-cart")).innerHTML).substr(1));
			var quantity1 = parseInt(document.getElementById(String("Quantity_"
					+ id)).value) + 1;
			var price = document.getElementById(String("Price_" + id)).innerHTML;
			var floatPrice = parseFloat(price.substr(1));
			$
					.ajax({
						url : "cart/changeCount",
						type : 'POST',
						data : {
							productId : id,
							quantity : quantity1
						},
						success : function(result) {
							if (result == "true") {
								document
										.getElementById(String("Quantity_" + id)).value = quantity1;
								document.getElementById(String("totalItem_"
										+ id)).innerHTML = "$"
										+ (quantity1 * floatPrice);
								document.getElementById("total-cart").innerHTML = "$"
										+ (subTotal + floatPrice);
							}

						}
					});
		};
	</script>
	</div>

</section>
<!--================End Cart Area =================-->
