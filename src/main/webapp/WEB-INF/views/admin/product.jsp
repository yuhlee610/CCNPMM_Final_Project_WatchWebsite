<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.servletContext.contextPath}/">
<meta charset="UTF-8">
<title>Product</title>
<link
	href="resources/admin/vendor/datatables/dataTables.bootstrap4.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
		<div class="row mb-2">



			<a href="admin/product/create"><i class="fa fa-plus mr-2"></i>
				Add Product</a>

		</div>
	</div>

	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">Products</h6>
		</div>
		<span class="text-danger">${message}</span>
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-bordered" id="dataTable" width="100%"
					cellspacing="0">
					<thead>
						<tr>
							<th>Id</th>
							<th>Name</th>
							<th>Amount</th>
							<th>Price</th>
							<th>Image</th>
							<th>Description</th>
							<th>Sold</th>
							<th>Brand</th>
							<th>Energy</th>
							<th>Material</th>
							<th></th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th>Id</th>
							<th>Name</th>
							<th>Amount</th>
							<th>Price</th>
							<th>Image</th>
							<th>Description</th>
							<th>Sold</th>
							<th>Brand</th>
							<th>Energy</th>
							<th>Material</th>
							<th></th>
						</tr>
					</tfoot>
					<tbody>
						<c:forEach var="p" items="${products }">
							<tr>
								<td>${p.id }</td>
								<td>${p.name }</td>
								<td>${p.amount }</td>
								<td><fmt:formatNumber value="${p.price}" type="currency" /></td>
								<td><img style="width: 50px; height: 50px"
									src="resources/assets/img/gallery/${p.image}"></td>
								<td>${p.decription}</td>
								<td>${p.sold}</td>
								<td>${p.brand.brandName}</td>
								<td>${p.energy.energyName}</td>
								<td>${p.material.materialName}</td>

								<td>


								
										<a href="admin/product/edit/${p.id }" title="Edit"><i class="fa fa-pen" aria-hidden="true" ></i></a>
									<a href="admin/product/delete/${p.id }" title="Delete" onclick="return confirm('Are you sure you want to delete?')"><i class="fa fa-trash" aria-hidden="true" ></i></a>

									

								</td>


							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
<!-- Bootstrap core JavaScript-->
<script src="resources/admin/vendor/jquery/jquery.min.js"></script>
<script
	src="resources/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="resources/admin/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="resources/admin/js/sb-admin-2.min.js"></script>
<script src="resources/admin/vendor/datatables/jquery.dataTables.min.js"></script>
<script
	src="resources/admin/vendor/datatables/dataTables.bootstrap4.min.js"></script>

<!-- Page level custom scripts -->

<script src="resources/admin/js/demo/datatables-demo.js"></script>

</html>