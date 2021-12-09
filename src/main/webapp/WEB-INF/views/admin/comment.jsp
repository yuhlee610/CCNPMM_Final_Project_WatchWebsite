<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.servletContext.contextPath}/">
<meta charset="UTF-8">
<title>Comment</title>
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
           
            <button class="tablinks" onclick="openTabcontrol(event, 'noreply')">No Reply</button>
            <button class="tablinks" onclick="openTabcontrol(event, 'replied')">Replied</button>
           
        </div>


        <div id="noreply" class="tabcontent">
            <jsp:include page="noreply.jsp" />
        </div>

        <div id="replied" class="tabcontent">
            <jsp:include page="replied.jsp" />
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
<script src="resources/admin/js/reply.js"></script>

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
        <script>openTabcontrol(event, 'noreply')</script>
        <script>
    $(function () {
           
        $('#noreplys').DataTable({});
        $('#replieds').DataTable({});
       
        });</script>
</html>