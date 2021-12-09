 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<div class="card shadow mb-4">
<div class="card-body">
<div class="table-responsive">
    <table class="table table-bordered" id="delivereds" width="100%" cellspacing="0">
        <thead>
            <tr >
                <th>Order Code</th>
                <th>Create Date</th>
                  <th>Total</th>
                <th>Name</th>
                <th>Phone</th>
                <th>Address</th>
              <th>User</th>
               
            </tr>
        </thead>
        <tbody>
            <c:forEach var="o" items="${orderdelivereds }">
                <tr>
                    <td><a class="btn-show-detail" href="admin/order/detail/${o.orderId }">${o.code}</a></td>
                    <td>${o.orderDate }</td>
                    <td><fmt:formatNumber value="${o.total}" type="currency" /></td>
                    <td>${o.name}</td>
                     <td>${o.phone}</td>
                      <td>${o.address}</td>
                   <td>${o.user.username}</td>
                   
                </tr>
          </c:forEach>
        </tbody>
    </table>
</div>
</div>
</div>
