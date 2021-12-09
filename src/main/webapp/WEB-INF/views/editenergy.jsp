 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="modal-body">

   <form:form method="post" modelAttribute="energy" >

        <div class="form-group">
            <label >Energy Name:</label>
            <input name="energyName" class="form-control" value="${energyName}"/>
            <span class="text-danger"></span>
        </div>

       <input name="energyId" style="display:none;" value="${energyId}"/>
   

        <button type="submit" class="btn btn-success">Save</button>
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
    </form:form>
</div>


