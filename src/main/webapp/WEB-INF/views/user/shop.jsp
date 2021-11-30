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
						<h2>Watch Shop</h2>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Hero Area End-->
<!-- Latest Products Start -->
<section class="popular-items latest-padding">
	<div class="container">
		<div class="row product-btn justify-content-between mb-40">
			<form method="get" action="shop" modelAttribute="filterModel" >
				<div class="btn-group btn-group-toggle form-group btn-group-sm" data-toggle="buttons">
					<label class="btn btn-secondary active btn-sm mr-2"> 
					<input
						type="radio" name="sortBy" id="option1" autocomplete="off" value="Price high to low"
						checked> Price high to low
					</label> 
					<label class="btn btn-secondary btn-sm mr-2"> 
					<input type="radio" value="Price low to high"
						name="sortBy" id="option2" autocomplete="off"> Price low to high
					</label> 
					<label class="btn btn-secondary btn-sm mr-2"> 
					<input type="radio" value="Popular"
						name="sortBy" id="option3" autocomplete="off"> Popular
					</label>
				</div>
				
				<h3>Brands: </h3>
				<div class="btn-group-toggle btn-group-sm form-group" data-toggle="buttons">
					<c:forEach var="b" items="${brandList }">
						  <label class="btn btn-secondary btn-sm">
						    <input type="checkbox" autocomplete="off" name="brands" value="${b.getBrandId() }"> ${b.getBrandName() }
						  </label>
					</c:forEach>
				</div>
				
				<h3>Price: </h3>
				<div class="btn-group btn-group-toggle form-group btn-group-sm" data-toggle="buttons">
					<label class="btn btn-secondary btn-sm active mr-2"> 
					<input
						type="radio" name="price" id="option4" autocomplete="off" value="<500"
						checked> <500
					</label> 
					<label class="btn btn-secondary btn-sm mr-2"> 
					<input type="radio" value="500-1000"
						name="price" id="option5" autocomplete="off"> 500-1000
					</label> 
					<label class="btn btn-secondary btn-sm mr-2"> 
					<input type="radio" value=">1000"
						name="price" id="option6" autocomplete="off"> >1000
					</label> 
				</div>
				
				<div class="form-group btn-group-sm">
					<button class=" btn view-btn1">Submit</button>
				</div>
			</form>
			<!-- Grid and List view -->
			<div class="grid-list-view"></div>
		</div>
		<!-- Nav Card -->
		<div class="tab-content" id="nav-tabContent">
			<!-- card one -->
			<div class="tab-pane fade show active" id="nav-home" role="tabpanel"
				aria-labelledby="nav-home-tab">
				<div class="row">
					<c:forEach var="p" items="${products }">
						<div class="col-xl-4 col-lg-4 col-md-6 col-sm-6">
							<div class="single-popular-items mb-50 text-center">
								<div class="popular-img">
									<img src="${p.getImage() }" alt="">
									<div class="img-cap">
										<span><a href="<c:url value="/addToCart?productId=${p.getId()}&quantity=1" />" >Add to cart</a></span>
										
									</div>
									<div class="favorit-items">
										<span class="flaticon-heart"></span>
									</div>
								</div>
								<div class="popular-caption">
									<h3>
										<a href="product_details.html">${p.getName() }</a>
									</h3>
									<span>$ ${p.getPrice()}</span>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="row justify-content-center">
						<div class="room-btn pt-70">
							<a href="" class="btn view-btn1" id="view_more">View More Products</a>
						</div>
					</div>
			</div>
		</div>
		<!-- End Nav Card -->
	</div>
</section>
<!-- Latest Products End -->
<!--? Shop Method Start-->
<div class="shop-method-area">
	<div class="container">
		<div class="method-wrapper">
			<div class="row d-flex justify-content-between">
				<div class="col-xl-4 col-lg-4 col-md-6">
					<div class="single-method mb-40">
						<i class="ti-package"></i>
						<h6>Free Shipping Method</h6>
						<p>aorem ixpsacdolor sit ameasecur adipisicing elitsf edasd.</p>
					</div>
				</div>
				<div class="col-xl-4 col-lg-4 col-md-6">
					<div class="single-method mb-40">
						<i class="ti-unlock"></i>
						<h6>Secure Payment System</h6>
						<p>aorem ixpsacdolor sit ameasecur adipisicing elitsf edasd.</p>
					</div>
				</div>
				<div class="col-xl-4 col-lg-4 col-md-6">
					<div class="single-method mb-40">
						<i class="ti-reload"></i>
						<h6>Secure Payment System</h6>
						<p>aorem ixpsacdolor sit ameasecur adipisicing elitsf edasd.</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Shop Method End-->
	
