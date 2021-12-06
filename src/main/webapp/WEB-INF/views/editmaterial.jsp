 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="modal-body">

   <form:form method="post" modelAttribute="material" >

        <div class="form-group">
            <label >Material Name:</label>
            <input name="materialName" class="form-control" value="${materialName}"/>
            <span class="text-danger"></span>
        </div>

       <input name="materialId" style="display:none;" value="${materialId}"/>
   

        <button type="submit" class="btn btn-success">Save</button>
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
    </form:form>
</div>


