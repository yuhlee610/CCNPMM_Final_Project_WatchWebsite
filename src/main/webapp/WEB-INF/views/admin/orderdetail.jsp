<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.servletContext.contextPath}/">
<meta charset="UTF-8">
<title>Order Detail</title>
<link
	href="resources/admin/vendor/datatables/dataTables.bootstrap4.min.css"
	rel="stylesheet">
	<link
	href="resources/admin/css/orderdetail.css"
	rel="stylesheet">
</head>
<body>

    <div class="heading">
        <span>Order Detail</span>
        <span> #${code } - <b>${status }</b></span>
    </div>
    <div class="created-date">
        <span>Ngày đặt hàng <i>${orderdate }</i></span>
    </div>
    <div class="row">
        <div class="col-sm-6">
            <div class="card h-100">
                <div class="card-header">THÔNG TIN NGƯỜI NHẬN</div>
                <div class="card-body">
                    <p class="font-weight-light">Tên người nhận: ${name }</p>
                    <p class="font-weight-light">Địa chỉ: ${address }</p>
                    <p class="font-weight-light">Phone: ${phone }</p>
                </div>
            </div>
        </div>
        
    </div>
    <div class="table-responsive-lg " id="Table-OrderDetail">
        <table class="table" id="Table-Detail" style="width: 100%;height: 100px">
            <thead class="thead-light">
                <tr>
                    <th scope="col">Sản phẩm</th>
                    <th scope="col">Giá</th>
                    <th scope="col">Số lượng</th>
                    <th scope="col">Phí vận chuyển</th>
                    <th scope="col">Tổng tiền</th>
                </tr>
            </thead>
            <tbody>
               <c:forEach var="o" items="${orderdetails }">
                        <tr>
                            <td>
                                <div class="d-flex justify-content-left">
                                    <img src="resources/assets/img/gallery/${o.image}" style="width: 100px">
                                    <div class="d-flex flex-column bd-highlight mb-3">
                                        <div style="margin-top: 20px">
                                            ${o.productName }
                                        </div>
                                        <div>
                                            #${o.productId }
                                        </div>
                                    </div>
                                </div>
                            </td>
                            <td> <fmt:formatNumber value="${o.price}" type="currency" /></td>
                            <td>${o.count }</td>
                           <td></td>
                            <td>${o.total }</td>
                        </tr>
                   </c:forEach>

            </tbody>
            <tfoot class="footer borderless">
                
                <tr>
                    <td colspan="3"></td>
                    <td>Tổng cộng</td>
                    <td style="color: red; font-size: larger;"><fmt:formatNumber value="${totalprice}" type="currency" /></td>
                </tr>
            </tfoot>
        </table>
    </div>
   

</body>
<script src="resources/admin/vendor/jquery/jquery.min.js"></script>
<script
	src="resources/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="resources/admin/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="resources/admin/js/sb-admin-2.min.js"></script>
</html>