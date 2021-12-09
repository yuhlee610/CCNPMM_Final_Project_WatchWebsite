<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.servletContext.contextPath}/">
<meta charset="UTF-8">
<title>Order</title>
<link
	href="resources/admin/vendor/datatables/dataTables.bootstrap4.min.css"
	rel="stylesheet">
	<link
	href="resources/admin/css/order.css"
	rel="stylesheet">
</head>
<body>
<div style="min-width: 966px; max-width: 1122px; padding: 0 48px; margin: 42px auto; display: flex;">
    <div class="content-wrapper">

        <div class="tab">
           
            <button class="tablinks" onclick="openTabcontrol(event, 'wconfirm')">Chờ xác nhận</button>
            <button class="tablinks" onclick="openTabcontrol(event, 'confirm')">Đã xác nhận</button>
            <button class="tablinks" onclick="openTabcontrol(event, 'deliver')">Đang giao</button>
            <button class="tablinks" onclick="openTabcontrol(event, 'delivered')">Đã giao</button>
            <button class="tablinks" onclick="openTabcontrol(event, 'cancel')">Đơn hủy</button>
        </div>


        <div id="wconfirm" class="tabcontent">
            <jsp:include page="wconfirm.jsp" />
        </div>

        <div id="confirm" class="tabcontent">
            <jsp:include page="confirm.jsp" />
        </div>

        <div id="deliver" class="tabcontent">
            <jsp:include page="deliver.jsp" />
        </div>

        <div id="delivered" class="tabcontent">
            <jsp:include page="delivered.jsp" />
        </div>

        <div id="cancel" class="tabcontent">
            <jsp:include page="cancel.jsp" />
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
<script src="resources/admin/js/changeStatusOrder.js"></script>
<script>
            function openTabcontrol(evt, tabControl) {
                var i, tabcontent, tablinks;
                tabcontent = document.getElementsByClassName("tabcontent");
                for (i = 0; i < tabcontent.length; i++) {
                    tabcontent[i].style.display = "none";
                }
                tablinks = document.getElementsByClassName("tablinks");
                for (i = 0; i < tablinks.length; i++) {
                    tablinks[i].className = tablinks[i].className.replace(" active", "");
                }
                document.getElementById(tabControl).style.display = "block";
               evt.currentTarget.className += " active";
            }
        </script>
        <script>openTabcontrol(event, 'wconfirm')</script>
        <script>
    $(function () {
           
        $('#wconfirms').DataTable({});
        $('#confirms').DataTable({});
        $('#delivers').DataTable({});
        $('#delivereds').DataTable({});
        $('#cancels').DataTable({});
        });</script>
</html>