<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.servletContext.contextPath}/">
<meta charset="UTF-8">
<title>Order</title>
<link
	href="resources/admin/vendor/datatables/dataTables.bootstrap4.min.css"
	rel="stylesheet">
</head>
<body>
	

	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">Orders</h6>
		</div>
	
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-bordered" id="dataTable" width="100%"
					cellspacing="0">
					<thead>
						<tr>
						
							<th>Order Id</th>
							<th>Create Date</th>
							<th>Address</th>
							<th>Name</th>
							<th>Phone</th>
							<th>Total</th>
							<th>Status</th>
							
						</tr>
					</thead>
				
					<tbody>
						<c:forEach var="o" items="${orders }">
							<tr>
								<td>${o.code }</td>
								<td>${o.orderDate }</td>
								<td>${o.address }</td>
							
								
								<td>${o.name}</td>
								<td>${o.phone}</td>
									<td><fmt:formatNumber value="${o.total}" type="currency" /></td>
								<td>${o.deliveryStatus}</td>
							


							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>




 
</html>