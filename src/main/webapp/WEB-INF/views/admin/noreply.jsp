<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="col-12">
	<!-- Add-User The Modal -->
	<div class="modal" id="addCM">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Reply</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<form:form action="admin/comment/create" method="post"
						modelAttribute="comment">
						<div class="form-group">
							<label>Content :</label>
							<form:textarea class="form-control" rows="3" path="content"></form:textarea>
<input name=idComment type="text" value='' style="display:none" >
<input name=productId type="text" value='' style="display:none" >
							<span class="text-danger"></span>
						</div>

						<button type="submit" id="createComment" class="btn btn-success">Add</button>
						<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
					</form:form>



				</div>
			</div>
		</div>
	</div>
</div>
<div class="card shadow mb-4">
	<div class="card-body">
		<div class="table-responsive">
			<table class="table table-bordered" id="noreplys" width="100%"
				cellspacing="0">
				<thead>
					<tr>
						<th>Product Id</th>
						<th>Username</th>
						<th>Date</th>
						<th>Content</th>

						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="r" items="${noreplies }">
						<tr>

							<td><a class="btn-show-detail"
								href="admin/product/detail/${r.productId }">${r.productId }</a></td>
							<td>${r.user.username }</td>
							<td>${r.date}</td>

							<td>${r.content}</td>

							<td data-toggle="modal" data-target="#addCM" data-id="${r.id}" data-productid="${r.productId}">
								<!-- Button to Open the Modal - Add User -->
								<button type="button" class="btn btn-success"
							
									>
									Reply
								</button>



							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
	</div>
</div>