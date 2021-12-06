<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.servletContext.contextPath}/">
<meta charset="UTF-8">
<title>Users</title>
<link href="resources/admin/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

</head>
<body>
	<h1 class="h3 mb-2 text-gray-800">Users</h1>
	
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">Users</h6>
		</div>
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-bordered" id="dataTable" width="100%"
					cellspacing="0">
					<thead>
						<tr>
							<th>Username</th>
							<th>Name</th>
							<th>Email</th>
							<th>Address</th>
							<th>Phone</th>
							<th>Birthday</th>
							<th>Avatar</th>
							<th>Role</th>
							<th>State</th>
							<th>Action</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th>Username</th>
							<th>Name</th>
							<th>Email</th>
							<th>Address</th>
							<th>Phone</th>
							<th>Birthday</th>
							<th>Avatar</th>
							<th>Role</th>
							<th>State</th>
							<th>Action</th>
						</tr>
					</tfoot>
					<tbody>
						<c:forEach var="u" items="${users }">
							<tr>
								<td>${u.username }</td>
								<td>${u.name }</td>
								<td>${u.email }</td>
								<td>${u.address }</td>
								<td>${u.phone }</td>
								<td><fmt:formatDate type = "date" value = "${u.birthday}" /></td>
								<td><img style="width: 50px;height: 50px" src="resources/assets/img/avatar/${u.avatar}"></td>


								<td><c:choose>
								<c:when test="${u.roleId==1 }">User</c:when>
								<c:otherwise>Admin</c:otherwise>
								</c:choose>
								</td>
									<c:choose>
										<c:when test="${u.state==true }">
										<td style="color:green" id="status${u.id }" >Active</td>
										 <td><a href="javascript:void(0)" class="state_block" id="state_block${u.id }" data-id="${u.id }"  title="Block"><i id="${u.id }" class="fa fa-ban" aria-hidden="true" style="color:red"></i></a></td> 
										</c:when>
										<c:otherwise>
										<td style="color:red" id="status${u.id }">Block</td>
										<td><a href="javascript:void(0)" class="state_active" id="state_active${u.id }" data-id="${u.id }" title="Open"><i id="${u.id }" class="fa fa-check" style="color:green" aria-hidden="true" ></i></a></td>
										</c:otherwise>
									</c:choose>
								
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
<script src="resources/admin/js/changeStatus.js"></script>
</html>