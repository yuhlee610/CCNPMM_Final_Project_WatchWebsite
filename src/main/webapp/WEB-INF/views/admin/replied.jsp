<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="card shadow mb-4">
<div class="card-body">
<div class="table-responsive">
    <table class="table table-bordered" id="replieds" width="100%" cellspacing="0">
        <thead>
            <tr >
                <th>Product Id</th>
                <th>Username</th>
                  <th>Date</th>
                <th>Content</th>
               <th>Content Reply</th>
               <th>Reply By</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="r" items="${replieds }">
                <tr>
                
                    <td><a class="btn-show-detail" href="admin/product/detail/${r.productId }">${r.productId }</a></td>
                    <td>${r.user.username }</td>
                    <td>${r.date}</td>
                    
                     <td>${r.content}</td>
                     <td>${r.comment.content }</td>
                     <td>${r.user1.username }</td>
                </tr>
          </c:forEach>
        </tbody>
    </table>
</div>
</div>
</div>