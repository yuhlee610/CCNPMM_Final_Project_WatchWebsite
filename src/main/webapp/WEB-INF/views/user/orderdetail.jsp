
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
						<c:forEach var="i" items="${listCart}">
							<tr>
								<td>
									<div class="media">
										<div class="d-flex">
											<img src="${i.image}" alt="" />
										</div>
										<div class="media-body">
											<p>${i.name }</p>
											<p>Brand: ${i.brandName }</p>
										</div>
									</div>
								</td>
								<td>
									<h5 id = "Price_${i.productId}" >$${i.price }</h5>
								</td>
								<td>
									<div class="product_count">
										<div class="number">
											<span class="minus" id="${i.productId}" onClick = "myDecFunction(this.id)">-</span>
											<input id="Quantity_${i.productId}" type="text" value="${i.count}" onkeypress="pressEnter(this.id)"/>
											<span class="plus" id="${i.productId}" onClick="myIncFunction(this.id)">+</span>
										</div>
									</div>
								</td>
								<td>
									<h5 id="totalItem_${i.productId}">$${i.count*i.price }</h5>
								</td>
							</tr>

						</c:forEach>
						<tr class="bottom_button">
							<td><a class="btn_1" href="cart">Update Cart</a></td>
							<td></td>
							<td></td>
							<td>
								<div class="cupon_text float-right">
									<a class="btn_1" href="">Close Coupon</a>
								</div>
							</td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td>
								<h4>Total</h4>
							</td>
							<td>
								<h4 id="total-cart">$${total}</h4>
							</td>
						</tr>
					</tbody>
				</table>
				<div class="checkout_btn_inner float-right">
					<a class="btn_1" href="">Continue Shopping</a> <a
						class="btn_1 checkout_btn_1" href="checkout/">Proceed to checkout</a>
				</div>
			</div>
		</div>
		<script>	
			
			function myDecFunction(id){
				var subTotal = parseFloat((document.getElementById(String("total-cart")).innerHTML).substr(1));
				var quantity1 = parseInt(document.getElementById(String("Quantity_"+id)).value) - 1;
				if(quantity1 == 0){
					if (confirm('Are you sure you want to delete this product out of cart?')) {
						$.ajax({
		                    url: "cart/delete",
		                    type: 'POST',
		                    data: { productId: id },
		                    success: function (result) {
		                        if(result == "true"){
		                        	alert("Product was deleted!");
		                        	
		                        }
		                    }
						});
					}
				};
				var price = document.getElementById(String("Price_"+id)).innerHTML;
				var floatPrice = parseFloat(price.substr(1));
				$.ajax({
                    url: "cart/changeCount",
                    type: 'POST',
                    data: { productId: id, quantity: quantity1 },
                    success: function (result) {
                        if(result == "true"){
                        	document.getElementById(String("Quantity_"+id)).value = quantity1;
                        	document.getElementById(String("totalItem_"+id)).innerHTML = "$"+(quantity1*floatPrice);
                        	document.getElementById("total-cart").innerHTML = "$" + (subTotal-floatPrice);
                        }
                       
                    }
				});
			};
			
			function myIncFunction(id){
				var subTotal = parseFloat((document.getElementById(String("total-cart")).innerHTML).substr(1));
				var quantity1 = parseInt(document.getElementById(String("Quantity_"+id)).value) + 1;
				var price = document.getElementById(String("Price_"+id)).innerHTML;
				var floatPrice = parseFloat(price.substr(1));
				$.ajax({
                    url: "cart/changeCount",
                    type: 'POST',
                    data: { productId: id, quantity: quantity1 },
                    success: function (result) {
                        if(result == "true"){
                        	document.getElementById(String("Quantity_"+id)).value = quantity1;
                        	document.getElementById(String("totalItem_"+id)).innerHTML = "$"+(quantity1*floatPrice);
                        	document.getElementById("total-cart").innerHTML = "$" + (subTotal+floatPrice);
                        }
                       
                    }
				});
			};
		
		</script>
	</div>
	
</section>
<!--================End Cart Area =================-->
