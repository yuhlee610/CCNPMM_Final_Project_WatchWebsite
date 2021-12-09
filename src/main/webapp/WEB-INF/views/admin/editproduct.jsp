<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.servletContext.contextPath}/">
<meta charset="UTF-8">
<title>Edit Product</title>
<link rel="stylesheet" href="resources/admin/css/templatemo-style.css">
</head>
<body>
<div class="container tm-mt-big tm-mb-big">
		<div class="row">
			<div class="col-xl-9 col-lg-10 col-md-12 col-sm-12 mx-auto">
				<div class="tm-bg-primary-dark tm-block tm-block-h-auto">
					<div class="row">
						<div class="col-12">
							<h2 class="tm-block-title d-inline-block">Edit Product</h2>
						</div>
					</div>
					<form:form method="post" modelAttribute="editproduct"
						enctype="multipart/form-data" >
						<div class="row tm-edit-product-row">

							<div class="col-xl-6 col-lg-6 col-md-12">

								<div class="form-group mb-3">
									<label for="id">Product Id </label>
									<input name="id" type="text" class="form-control" value="${id}" disabled />
								</div>
								<div class="form-group mb-3">
									<label for="name">Product Name </label>
									<form:input path="name" type="text" class="form-control" value="${name}"/>
								</div>
								<div class="form-group mb-3">
									<label for="description">Description</label>
									<textarea class="form-control" rows="3" name="decription" >${decription}</textarea>
								</div>s

								<div class="form-group mb-3">
									<label for="price">Price</label>
									<form:input path="price" type="number" min="0" class="form-control" value="${price}"/>
								</div>
								<div class="form-group mb-3">
									<label for="amount">Amount</label>
									<form:input path="amount" type="number" min="0" class="form-control" value="${amount}"/>
								</div>
								<div class="row">
									<div class="form-group mb-3 col-xs-12 col-sm-4">
										<label for="brand">Brand </label>
										<form:select path="brandId">

											<form:options items="${brands}" />
										</form:select>
									</div>
									<div class="form-group mb-3 col-xs-12 col-sm-4">
										<label for="energy">Energy</label>
										<form:select path="energyId">

											<form:options value="" items="${energys}" />
										</form:select>
									</div>
									<div class="form-group mb-3 col-xs-12 col-sm-4">
										<label for="material">Material</label>
										<form:select path="materialId">

											<form:options items="${materials}" />
										</form:select>
									</div>
								</div>
							</div>

							<div class="col-xl-6 col-lg-6 col-md-12 mx-auto mb-4">
								<div class="tm-product-img-dummy mx-auto">
									<img id="targetImg" src="resources/assets/img/gallery/${image}" style="height: 240px; max-width: 100%">

								</div>
								<div class="custom-file mt-3 mb-3">
									<input id="file" name="file" type="file" accept="image/*"
										style="display: none;" /> <input type="button"
										class="btn btn-primary btn-block mx-auto"
										value="UPLOAD PRODUCT IMAGE"
										onclick="document.getElementById('file').click();" />
								</div>
							</div>

							<div class="col-12">
								<button type="submit"
									class="btn btn-primary btn-block text-uppercase">Add
									Product Now</button>
							</div>


						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="resources/admin/vendor/jquery/jquery.min.js"></script>
<script
	src="resources/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="resources/admin/vendor/jquery-easing/jquery.easing.min.js"></script>
<script src="resources/admin/js/displayimg.js"></script>
<script src="resources/admin/js/jquery.validate.min.js"></script>
<script src="resources/admin/js/validation.js"></script>
</html>