<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>Watch shop | eCommers</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="manifest" href="site.webmanifest">

<base href="${pageContext.request.contextPath}/">
<link rel="shortcut icon" type="image/x-icon"
	href="resources/assets/img/favicon.ico">

<!-- All PLUGINS CSS ============================================= -->
<link rel="stylesheet" href="resources/Users/css/assets.css">

<!-- TYPOGRAPHY ============================================= -->
<link rel="stylesheet" type="text/css"
	href="resources/Users/css/typography.css">

<!-- SHORTCODES ============================================= -->
<link rel="stylesheet" type="text/css"
	href="resources/Users/css/shortcodes/shortcodes.css">

<!-- STYLESHEETS ============================================= -->
<link rel="stylesheet" type="text/css"
	href="resources/Users/css/style.css">
<link class="skin" rel="stylesheet" type="text/css"
	href="resources/Users/css/color/color-1.css">

<!-- REVOLUTION SLIDER CSS ============================================= -->
<link rel="stylesheet" type="text/css"
	href="resources/Users/vendors/revolution/css/layers.css">
<link rel="stylesheet" type="text/css"
	href="resources/Users/vendors/revolution/css/settings.css">
<link rel="stylesheet" type="text/css"
	href="resources/Users/vendors/revolution/css/navigation.css">
<link href="resources/Users/css/custom.css" rel="stylesheet" />
<link href="resources/Users/css/favorite.css" rel="stylesheet" />
<!-- REVOLUTION SLIDER END -->
</head>

<body>
	<main>
		<!-- ================ contact section start ================= -->
		<div class="page-content bg-white">
			<!-- Breadcrumb row -->
			<div class="breadcrumb-row">
				<div class="container">
					<ul class="list-inline">
						<li><a href="${pageContext.request.contextPath}/">Home</a></li>
						<li>Profile</li>
					</ul>
				</div>
			</div>
			<!-- Breadcrumb row END -->
			<!-- inner page banner END -->
			<div class="content-block">
				<!-- About Us -->
				<div class="section-area section-sp1">
					<div class="container">
						<div class="row">
							<div class="col-lg-3 col-md-4 col-sm-12 m-b30">
								<div class="profile-bx text-center">
									<div class="user-profile-thumb">
										<img src="${user.avatar}" alt="" />
									</div>
									<div class="profile-info">
										<h4>${user.username}</h4>
										<span>${user.email}</span>
									</div>
									<div class="profile-social">
										<ul class="list-inline m-a0">
											<li><a href="#"><i class="fa fa-facebook"></i></a></li>
											<li><a href="#"><i class="fa fa-twitter"></i></a></li>
											<li><a href="#"><i class="fa fa-linkedin"></i></a></li>
											<li><a href="#"><i class="fa fa-google-plus"></i></a></li>
										</ul>
									</div>
									<div class="profile-tabnav">
										<ul class="nav nav-tabs">
											<!-- <li class="nav-item"><a class="nav-link "
												data-toggle="tab" href="#courses"><i class="ti-book"></i>Courses</a>
											</li>
											<li class="nav-item"><a class="nav-link"
												data-toggle="tab" href="#quiz-results"><i
													class="ti-bookmark-alt"></i>Other Courses</a></li> -->
											<li class="nav-item"><a class="nav-link active"
												data-toggle="tab" href="#edit-profile"><i
													class="ti-pencil-alt"></i>Edit Profile</a></li>
											<li class="nav-item"><a class="nav-link"
												data-toggle="tab" href="#change-password"><i
													class="ti-lock"></i>Change Password</a></li>
										</ul>
									</div>
								</div>
							</div>
							<div class="col-lg-9 col-md-8 col-sm-12 m-b30">
								<div class="profile-content-bx">
									<div class="tab-content">
										<div class="tab-pane" id="courses">
											<div class="profile-head">
												<h3>My Courses</h3>
												<div class="feature-filters style1 ml-auto">
													<ul class="filters" data-toggle="buttons">
														<li data-filter="" class="btn active"><input
															type="radio"> <a href="#"><span>All</span></a></li>
														<li data-filter="publish" class="btn"><input
															type="radio"> <a href="#"><span>Publish</span></a>
														</li>
														<li data-filter="pending" class="btn"><input
															type="radio"> <a href="#"><span>Pending</span></a>
														</li>
													</ul>
												</div>
											</div>
											<div class="loadMyCourse">
												<!--===========================-->
											</div>

										</div>
										<div class="tab-pane" id="quiz-results">
											<div class="profile-head">
												<h3>Other Courses</h3>
											</div>
											<div class="courses-filter">

												<div class="loadCkechout">
													<!--=================-->

												</div>
											</div>
										</div>

										<div class="tab-pane active" id="edit-profile">
											<div class="profile-head">
												<h3>Edit Profile</h3>
											</div>

											<!-- <form action="user/uploadFile" method="post" enctype="multipart/form-data"> -->
											<div class="user-profile-thumb">
												<!-- <input type="file" id="fileUpload" accept="image/*"
													name="fileUpload" style="display: none" />  -->
												<img id="picUpload" name="picUpload"
													src="${user.avatar}" alt="">
											</div>
											<!-- <input class="btn" type="submit" id="btnUpload"
												value="Choose image" style="margin-left: 60px;" /> -->
											<!-- </form> -->




											<form:form class="edit-profile" action="user/editProfile"
												method="post" enctype="multipart/form-data" modelAttrbute="user" >
												
												<div>Choose avatar:</div> <input name="attachment" type="file">
												
												<div class="">
													<div class="form-group row">
														<div class="col-12 col-sm-9 col-md-9 col-lg-10 ml-auto">
															<h3>1. Personal Details</h3>
														</div>
													</div>
													<div class="form-group row">
														<label
															class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">FullName</label>
														<div class="col-12 col-sm-9 col-md-9 col-lg-7">
															<input class="form-control" type="text" id="name"
																name="name" value="${user.name }" required
																 title="invalidate">
														</div>
													</div>
													<div class="form-group row">
														<label
															class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Birthdate</label>
														<div class="col-12 col-sm-9 col-md-9 col-lg-7">
															<input class="form-control" type="date" id="birthday"
																name="birthday" value="${user.birthday}"
																pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))">
														</div>
													</div>
													<!-- <div class="form-group row">
														<label
															class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Gender</label>
														<div class="col-12 col-sm-9 col-md-9 col-lg-7">
															<select name="Gender" id="Gender">
																<option value="Male" selected style="display: none">Male</option>
																<option id="Male" value="Male">Male</option>
																<option id="Female" value="Female">Female</option>

															</select>
														</div>
													</div> -->
													<div class="form-group row">
														<label
															class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Phone</label>
														<div class="col-12 col-sm-9 col-md-9 col-lg-7">
															<input class="form-control" type="text" id="phone"
																name="phone" value="${user.phone }" required
																pattern="^[0-9]+$" title="invalidate">
														</div>
													</div>

													<div class="form-group row">
														<label
															class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Address</label>
														<div class="col-12 col-sm-9 col-md-9 col-lg-7">
															<input class="form-control" type="text" id="address"
																name="address" value="${user.address }" required>
														</div>
													</div>
												</div>
												<div class="">
													<div class="">
														<div class="row">
															<div class="col-12 col-sm-3 col-md-3 col-lg-2"></div>
															<div class="col-12 col-sm-9 col-md-9 col-lg-7">

																<button type="submit" class="btn" id="savechange">Save
																	changes</button>
																<button type="reset" class="btn-secondry">Cancel</button>
															</div>
														</div>
													</div>
												</div>
											</form:form>
										</div>
										<div class="tab-pane" id="change-password">
											<div class="profile-head">
												<h3>Change Password</h3>
											</div>
											<form class="edit-profile">
												<div class="">
													<div class="form-group row">
														<div class="col-12 col-sm-8 col-md-8 col-lg-9 ml-auto">
															<h3>Password</h3>
														</div>
													</div>
													<div class="form-group row">
														<label
															class="col-12 col-sm-4 col-md-4 col-lg-3 col-form-label">Current
															Password</label>
														<div class="col-12 col-sm-8 col-md-8 col-lg-7">
															<input class="form-control" type="password" value=""
																id="currentPassword" required>
														</div>
													</div>
													<div class="form-group row">
														<label
															class="col-12 col-sm-4 col-md-4 col-lg-3 col-form-label">New
															Password</label>
														<div class="col-12 col-sm-8 col-md-8 col-lg-7">
															<input class="form-control" type="password"
																id="newPassword"
																minlength="6" maxlength="20"
																pattern="(?=.*\d)(?=.*[\W_]).{6,}" 
																title="Minimum of 6 characters. Should have at least one special character and one number."
																required>
														</div>
													</div>
													<div class="form-group row">
														<label
															class="col-12 col-sm-4 col-md-4 col-lg-3 col-form-label">Re
															Type New Password</label>
														<div class="col-12 col-sm-8 col-md-8 col-lg-7">
															<input class="form-control" type="password" value=""
																id="confirmPassword" required>
														</div>
													</div>
													<div class="form-group row">
														<div id="message" style="color: red"></div>
													</div>
												</div>
												<div class="row">
													<div class="col-12 col-sm-4 col-md-4 col-lg-3"></div>
													<div class="col-12 col-sm-8 col-md-8 col-lg-7">
														<button type="button" class="btn" id="btnChangePassword">Save
															changes</button>
														<button type="reset" class="btn-secondry">Cancel</button>
													</div>
												</div>

											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- contact area END -->

		</div>
		<!-- ================ contact section end ================= -->
	</main>

</body>

<!-- External JavaScripts -->
<script type="text/javascript">
	if ("${message}" != "") {
		alert("${message}");
	}
</script>
<script src="resources/Users/js/jquery.min.js"></script>
<script src="resources/Users/vendors/bootstrap/js/popper.min.js"></script>
<script src="resources/Users/vendors/bootstrap/js/bootstrap.min.js"></script>
<script
	src="resources/Users/vendors/bootstrap-select/bootstrap-select.min.js"></script>
<script
	src="resources/Users/vendors/bootstrap-touchspin/jquery.bootstrap-touchspin.js"></script>
<script src="resources/Users/vendors/magnific-popup/magnific-popup.js"></script>
<script src="resources/Users/vendors/counter/waypoints-min.js"></script>
<script src="resources/Users/vendors/counter/counterup.min.js"></script>
<script src="resources/Users/vendors/imagesloaded/imagesloaded.js"></script>
<script src="resources/Users/vendors/masonry/masonry.js"></script>
<script src="resources/Users/vendors/masonry/filter.js"></script>
<script src="resources/Users/vendors/owl-carousel/owl.carousel.js"></script>
<script src="resources/Users/js/functions.js"></script>
<script src="resources/Users/js/contact.js"></script>
<script src="resources/Users/vendors/switcher/switcher.js"></script>

<script>
	$(document).ready(function() {


		$(function() {
			// event click button change
			$('#btnUpload').click(function() {
				$('#fileUpload').trigger('click')
			});
			//change data fileUpload
			$('#fileUpload').change(function() {

				debugger;
				//kiem tra  trinh duyet co ho tro Formdata
				if (window.FormData !== undefined) {
					//Lay du lieu tren fileUpload
					var fileUpload = $('#fileUpload').get(0);
					var files = fileUpload.files;
					//tao doi tuong formdata
					var formData = new FormData();
					//dua du lieu vao form
					formData.append('file', files[0]);
					$.ajax({
						type : 'POST',
						url : 'user/uploadFile',
						contentType : false, //khong co header
						processData : false,// khong xu li du lieu
						data : files,
						success : function(urlImage) {
							$('#picUpload').attr('src', urlImage);

						},
						error : function(err) {
							alert('Error ' + err.statusText);
						}
					});
				}
			});
		});

		$("#currentPassword").click(function() {
			$("#message").text("");
		})
		$("#newPassword").click(function() {
			$("#message").text("");
		})
		$("#confirmPassword").click(function() {
			$("#message").text("");
		})

		$("#btnChangePassword").click(function() {
			debugger;
			var passw=  /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}$/;
			var currentPassword = $("#currentPassword").val();
			var newPassword = $("#newPassword").val();
			var confirmPassword = $("#confirmPassword").val();
			if (newPassword === "" || confirmPassword === "") {
				$("#message").text("Nhập đầy đủ thông tin");
			}
			//else if ("^ (?=.*\d)(?=.* [a - z])(?=.* [A - Z])(?!.*\s).* $".test(newPassword))
			//{
			 //   $("#message").text("Please include at least 1 uppercase character, 1 lowercase character, and 1 number.");
			//}
			else if( !newPassword.match(passw)){
				$("#message").text(" 6 to 20 characters which contain at least one numeric digit, one uppercase and one lowercase letter");
			}
			else if (newPassword !== confirmPassword) {
				$("#message").text("Mật khẩu không trùng khớp");
			} else {
				$.ajax({
					url : "user/changePassword",
					type : "post",
					data : {
						newPassword : newPassword,
						currentPassword : currentPassword
					},
					success : function(result) {
						if (result == "true") {
							alert("Thay đổi mật khẩu thành công");
						} else if (result == "NotValid") {
							alert("Mật khẩu hiện tại không chính xác");
						} else if (result == "login") {
							window.location.replace("login");
						} else {
							alert(result);
						}
					}
				})
			}

		})
	});
</script>



</html>