<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<c:if test="${not empty message}">
	<script>
		alert("${message}")
	</script>
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
<!--================Single Product Area =================-->
<div class="product_image_area">
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-lg-12">
				<div class="single_product_img d-flex justify-content-center">
					<img src="${product.getImage() }" alt="#" class="img-fluid">
				</div>
			</div>
			<div class="col-lg-8">
				<div class="single_product_text text-center">
					<h3>${product.getName() }</h3>
					<p>Brand: ${brand}</p>
					<p>Material: ${material}</p>
					<p>Energy: ${energy}</p>
					<p>${product.getDecription() }</p>
					<form class="card_area" method="get" action="addItemToCart">
						<input type="hidden" value="${product.getId()}" name="productId" />
						<div class="product_count_area">
							<p>Quantity</p>
							<div class="product_count d-inline-block">
								<span class="product_count_item inumber-decrement"> <i
									class="ti-minus"></i></span> <input name="quantity"
									class="product_count_item input-number" type="text" value="1"
									min="1" max="${product.getAmount() }"> <span
									class="product_count_item number-increment"> <i
									class="ti-plus"></i></span>
							</div>
							<p>$${product.getPrice() }</p>
						</div>
						<div class="add_to_cart">
							<button class="btn_3">Add to cart</button>
						</div>
					</form>
				</div>

				<c:forEach var="comment" items="${comments}">
					<div class="comment row mt-4 mb-4">
						<div class="avatar col-2 text-center">
							<c:if test="${not empty reply.getAvatar() }">
								<img class="rounded-circle" width="55%"
									src="${reply.getAvatar() }" alt="" />
							</c:if>
							<c:if test="${empty reply.getAvatar() }">
								<img class="rounded-circle" width="55%"
									src="https://joeschmoe.io/api/v1/random" alt="" />
							</c:if>
						</div>
						<div class="col-10">
							<input type="hidden" id="reply${comment.getId()}"
								value="${comment.getReplyFrom()}" />
							<div style="color: #777" id="name_${comment.getId()}">${comment.getUsername()}</div>
							<div style="color: #777; font-style: italic;">${comment.getContent() }</div>
							<p style="font-style: italic;">${comment.getDate()}</p>
							<div style="color: #0d6efd; cursor: pointer"
								onclick="clickReply(${comment.getId()})">Reply</div>
							<c:forEach var="reply" items="${comment.getReplyList() }">
								<div class="comment row mt-4 mb-4">
									<div class="avatar col-2 text-center">
										<c:if test="${not empty reply.getAvatar() }">
											<img class="rounded-circle" width="55%"
												src="${reply.getAvatar() }" alt="" />
										</c:if>
										<c:if test="${empty reply.getAvatar() }">
											<img class="rounded-circle" width="55%"
												src="https://joeschmoe.io/api/v1/random" alt="" />
										</c:if>
									</div>
									<div class="col-10">
										<input type="hidden" id="reply${reply.getId()}"
											value="${reply.getReplyFrom()}" />
										<div style="color: #777; font-style: italic;" id="name_${reply.getId()}">${reply.getUsername()}</div>
										<div style="color: #777">${reply.getContent() }</div>
										<p>${reply.getDate()}</p>
										<div style="color: #0d6efd; cursor: pointer"
											onclick="clickReply(${reply.getId()})">Reply</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</c:forEach>

				<div class="add_comment">
					<form action="addComment" method="post">
						<div class="row mb-2">
							<div class="avatar col-2 text-center">
								<img class="rounded-circle" width="55%"
									src="https://toigingiuvedep.vn/wp-content/uploads/2021/01/anh-avatar-cho-con-gai-cuc-dep.jpg"
									alt="" />
							</div>
							<div class="col-10" id="input-comment">
								<textarea name="content" style="width: 100%;" rows="4"></textarea>
							</div>
						</div>
						<input type="hidden" value="0" name="replyFrom" id="replyFrom" />
						<input type="hidden" value="${product.getId()}" name="productId" />
						<button style="padding: 12px 16px; background-color: #007bff"
							class="button float-right mb-4">Submit</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<!--================End Single Product Area =================-->
<!-- subscribe part here -->
<section class="subscribe_part section_padding">
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-lg-8">
				<div class="subscribe_part_content">
					<h2>Get promotions & updates!</h2>
					<p>Seamlessly empower fully researched growth strategies and
						interoperable internal or “organic” sources credibly innovate
						granular internal .</p>
					<div class="subscribe_form">
						<input type="email" placeholder="Enter your mail"> <a
							href="#" class="btn_1">Subscribe</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- subscribe part end -->