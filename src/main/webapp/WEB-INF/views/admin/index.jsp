<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.servletContext.contextPath}/">
<meta charset="ISO-8859-1">
<title>Dashboard</title>
<link
	href="resources/admin/vendor/datatables/dataTables.bootstrap4.min.css"
	rel="stylesheet">
</head>
<body>
	<!-- Page Heading -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
		
	</div>

	<!-- Content Row -->
	<div class="row">

		<!-- Earnings (Monthly) Card Example -->
		<div class="col-xl-3 col-md-6 mb-4">
			<div class="card border-left-primary shadow h-100 py-2">
				<div class="card-body">
					<div class="row no-gutters align-items-center">
						<div class="col mr-2">
							<div
								class="text-xs font-weight-bold text-primary text-uppercase mb-1">
								Total Products</div>
							<div class="h5 mb-0 font-weight-bold text-gray-800">${products}</div>
						</div>
						<div class="col-auto">
							<i class="fas fa-calendar fa-2x text-gray-300"></i>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- Earnings (Monthly) Card Example -->
		<div class="col-xl-3 col-md-6 mb-4">
			<div class="card border-left-success shadow h-100 py-2">
				<div class="card-body">
					<div class="row no-gutters align-items-center">
						<div class="col mr-2">
							<div
								class="text-xs font-weight-bold text-success text-uppercase mb-1">
								Earnings</div>
							<div class="h5 mb-0 font-weight-bold text-gray-800"><fmt:formatNumber value="${earnings}" type="currency" /></div>
						</div>
						<div class="col-auto">
							<i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- Earnings (Monthly) Card Example -->
		<div class="col-xl-3 col-md-6 mb-4">
			<div class="card border-left-info shadow h-100 py-2">
				<div class="card-body">
					<div class="row no-gutters align-items-center">
						<div class="col mr-2">
							<div
								class="text-xs font-weight-bold text-info text-uppercase mb-1">Total Sold
							</div>
							<div class="row no-gutters align-items-center">
								<div class="col-auto">
									<div class="h5 mb-0 mr-3 font-weight-bold text-gray-800"><fmt:formatNumber value="${sold}" type="currency" /></div>
								</div>
								<div class="col">
									<div class="progress progress-sm mr-2">
										<div class="progress-bar bg-info" role="progressbar"
											style="width: 50%" aria-valuenow="50" aria-valuemin="0"
											aria-valuemax="100"></div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-auto">
							<i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- Pending Requests Card Example -->
		<div class="col-xl-3 col-md-6 mb-4">
			<div class="card border-left-warning shadow h-100 py-2">
				<div class="card-body">
					<div class="row no-gutters align-items-center">
						<div class="col mr-2">
							<div
								class="text-xs font-weight-bold text-warning text-uppercase mb-1">
								Users</div>
							<div class="h5 mb-0 font-weight-bold text-gray-800">${users}</div>
						</div>
						<div class="col-auto">
							<i class="fas fa-comments fa-2x text-gray-300"></i>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">Report Monthly</h6>
			<a href="admin/reportmonth"
			class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
			class="fas fa-download fa-sm text-white-50"></i> Export Excel</a>
		</div>
		
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-bordered"  width="100%"
					cellspacing="0">
					<thead>
						<tr>

							<th>Month</th>

							<th>Total Sold</th>
							<th>Total Earning ($)</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="r" items="${reportmonths }">
							<tr>
								<td>${r.date }</td>
								<td>${r.totalSold }</td>
								<td name="totalmonth">${r.totalMoney }</td>
							</tr>
						</c:forEach>
						<tr>
								<td>Total</td>
								<td>${totalsoldmonth }</td>
								<td>${totalmoneymonth }</td>
							</tr>
					</tbody>

				</table>
			</div>
		</div>
	</div>
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">Report Year</h6>
			<a href="admin/reportyear"
			class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
			class="fas fa-download fa-sm text-white-50"></i> Export Excel</a>
		</div>
		
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-bordered" id="year" width="100%"
					cellspacing="0">
					<thead>
						<tr>

							<th>Year</th>

							<th>Total Sold</th>
							<th>Total Earning ($)</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="r" items="${reportyears }">
							<tr>
								<td>${r.date }</td>
								<td>${r.totalSold }</td>
								<td>${r.totalMoney }</td>
							</tr>
						</c:forEach>
						<tr>
								<td>Total</td>
								<td>${totalsoldyear }</td>
								<td>${totalmoneyyear }</td>
							</tr>
					</tbody>

				</table>
			</div>
		</div>
	</div>
	<!-- Content Row -->

	<div class="row">

		<!-- Area Chart -->
		
			
				<!-- Card Header - Dropdown -->
				<div
					class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
					<h6 class="m-0 font-weight-bold text-primary">Earnings
						Overview</h6>
					
				</div>
				<!-- Card Body -->
				<div class="card-body">
					<div class="chart-area">
						<canvas id="myAreaChart"></canvas>
					</div>
				</div>
		
		
	</div>
	<!-- Bootstrap core JavaScript-->
<script src="resources/admin/vendor/jquery/jquery.min.js"></script>
<script
	src="resources/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="resources/admin/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="resources/admin/js/sb-admin-2.min.js"></script>
    <!-- Page level plugins -->
    <script src="resources/admin/vendor/chart.js/Chart.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="resources/admin/js/demo/chart-area-demo.js"></script>
   <script src="resources/admin/vendor/datatables/jquery.dataTables.min.js"></script>
<script
	src="resources/admin/vendor/datatables/dataTables.bootstrap4.min.js"></script>

<!-- Page level custom scripts -->

<script src="resources/admin/js/demo/datatables-demo.js"></script>
 <script>
    $(function () {
           
        $('#month').DataTable({});
        $('#year').DataTable({});
        
        });</script>
</body>

</html>