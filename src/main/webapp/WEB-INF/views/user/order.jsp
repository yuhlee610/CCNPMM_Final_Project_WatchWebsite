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
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">Products</h6>
		</div>
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
					<thead>
						<tr>
						<th></th>
							<th>Order Code</th>
							<th>Date time</th>
							<th>Total</th>
							<th>Address</th>
							<th>Name</th>
							<th>Phone</th>
							<th>Delivery status</th>
							<th></th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th></th>
							<th>Order Code</th>
							<th>Date time</th>
							<th>Total</th>
							<th>Address</th>
							<th>Name</th>
							<th>Phone</th>
							<th>Delivery status</th>
							<th></th>
						</tr>
					</tfoot>
					<tbody>
						<c:forEach var="p" items="${orders}">
							<tr>
								<td></td>
								<td>${p.code }</td>
								<td>${p.orderDate }</td>
								<td>${p.total}</td>
								<td>${p.address}</td>
								<td>${p.name}</td>
								<td>${p.phone}</td>
								<td>${p.deliveryStatus}</td>

								<td>
									
									<a href="admin/product/delete/${p.orderId }">Detail</a>
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