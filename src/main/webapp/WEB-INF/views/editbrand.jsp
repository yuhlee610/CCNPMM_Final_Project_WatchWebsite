 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="modal-body">

   <form:form method="post" modelAttribute="brand" >

        <div class="form-group">
            <label >Brand Name:</label>
            <input name="brandName" class="form-control" value="${brandName}"/>
            <span class="text-danger"></span>
        </div>

       <input name="brandId" style="display:none;" value="${brandId}"/>
   

        <button type="submit" class="btn btn-success">Save</button>
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
    </form:form>
</div>


