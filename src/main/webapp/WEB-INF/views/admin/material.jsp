<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.servletContext.contextPath}/">
<meta charset="UTF-8">
<title>Material</title>
<link
	href="resources/admin/vendor/datatables/dataTables.bootstrap4.min.css"
	rel="stylesheet">
</head>
<body>
<body>

	<section class="content-header">
		<div class="container-fluid">
			<div class="row mb-2">

				<!-- Button to Open the Modal - Add User -->
				<button type="button" class="btn btn-success"
					style="width: 15%; display: inline; float: right;"
					data-toggle="modal" data-target="#addMT">
					<i class="fa fa-plus mr-2"></i> Add Material
				</button>
			</div>
		</div>
		<!-- /.container-fluid -->
	</section>
	<div class="col-12">
		
		<div class="modal" id="addMT">
			<div class="modal-dialog">
				<div class="modal-content">
					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title">Add Material</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>

					<!-- Modal body -->
					<div class="modal-body">
						<form:form action="admin/material/create" method="post"
							 modelAttribute="material">
							<div class="form-group">
								<label>Material Name :</label>
								<form:input path="materialName" class="form-control"  />

								<span class="text-danger"></span>
							</div>

							<button type="submit" id="createMaterial" class="btn btn-success">Add</button>
							<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
						</form:form>



					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">Material</h6>
		</div>
		<span class="text-danger">${message}</span>
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-bordered" id="dataTable" width="100%"
					cellspacing="0">
					<thead>
						<tr>

							<th>Material Name</th>

							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="m" items="${materials }">
							<tr>
								<td>${m.materialName }</td>


								<td>

									<button type="button" class="btn btn-info btn-xs" title="Edit"
										onclick="EditMT(${m.materialId})">
										<i class="fa fa-pen" aria-hidden="true"></i>
									</button>
									<button type="button" class="btn btn-danger btn-xs"
										title="Delete" onclick="DeleteFunction(${m.materialId})">
										<i class="fa fa-trash" aria-hidden="true"></i>
									</button>

								</td>
							</tr>
						</c:forEach>
					</tbody>

				</table>
			</div>
		</div>
	</div>

	<div class="modal hide fade" id="edit-person">
		<div class="modal-dialog">
			<div class="modal-content">
				<div id="edit-person-container"></div>
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
<script src="resources/admin/js/jquery.validate.min.js"></script>
<script src="resources/admin/js/validation.js"></script>
<script>
   
    function DeleteFunction(e) {
        
        e.preventDefault
        var r = confirm("Do you want to delete?");
       // var addid = $(this).data('id');
        if (r == true) {
            $.ajax({
                type: "POST",
                url: "admin/material/delete",
                data: { id: e },
                success: function (response) {
                    alert("Deleted");
                    window.location.reload();

                },
                error: function () {
                    alert("Error");
                }
            });
        }
    };
    function EditMT(e) {
        var url = "admin/material/edit"; // the url to the controller
        var id = e; // the id that's given to each button in the list
        $.get(url + '/' + id, function (data) {
            $('#edit-person-container').html(data);
            $('#edit-person').modal('show');
          
        });
    };
   </script>
</body>
</html>