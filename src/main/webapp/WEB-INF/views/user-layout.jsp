<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!doctype html>
<html class="no-js" lang="zxx">
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

<!-- CSS here -->
<link rel="stylesheet" href="resources/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/assets/css/owl.carousel.min.css">
<link rel="stylesheet" href="resources/assets/css/flaticon.css">
<link rel="stylesheet" href="resources/assets/css/slicknav.css">
<link rel="stylesheet" href="resources/assets/css/animate.min.css">
<link rel="stylesheet" href="resources/assets/css/magnific-popup.css">
<link rel="stylesheet"
	href="./resources/assets/css/owl.carousel.min.css">
<link rel="stylesheet" href="resources/assets/css/flaticon.css">
<link rel="stylesheet" href="resources/assets/css/slicknav.css">
<link rel="stylesheet" href="resources/assets/css/animate.min.css">
<link rel="stylesheet" href="resources/assets/css/magnific-popup.css">
<link rel="stylesheet"
	href="resources/assets/css/fontawesome-all.min.css">
<link rel="stylesheet" href="resources/assets/css/themify-icons.css">
<link rel="stylesheet" href="resources/assets/css/slick.css">
<link rel="stylesheet" href="resources/assets/css/nice-select.css">
<link rel="stylesheet" href="resources/assets/css/style.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"
	integrity="sha512-HK5fgLBL+xu6dm/Ii3z4xhlSUyZgTT9tuc/hSrtw6uzJOvgRr2a9jyxxT1ely+B+xFAmJKVSTbpM/CuL7qxO8w=="
	crossorigin="anonymous" />

<style>
input[type="number"] {
	-webkit-appearance: textfield;
	-moz-appearance: textfield;
	appearance: textfield;
}

input[type=number]::-webkit-inner-spin-button, input[type=number]::-webkit-outer-spin-button
	{
	-webkit-appearance: none;
}

.number-input {
	border: 2px solid #ddd;
	display: inline-flex;
}

.number-input, .number-input * {
	box-sizing: border-box;
}

.number-input div {
	outline: none;
	-webkit-appearance: none;
	background-color: transparent;
	border: none;
	align-items: center;
	justify-content: center;
	width: 2rem;
	height: 2rem;
	cursor: pointer;
	margin: 0;
	position: relative;
	color: #415094;
	font-size: 20px;
	text-align: center;
}
</style>
</head>

<script>
function onClickReply(id) {
	let replyId = $('#reply' + id).attr('value')
	let container = document.getElementById('input-comment')
	console.log('name_' + id)
	let username = document.getElementById('name_' + id).innerText
	console.log('username: ' + username)
	let showReply = '<div id="tag">Reply ' + username + '<i class="fas fa-times ml-2" onclick="removeReply()" style="cursor: pointer;"></i></div>'
	if(replyId) {
		$('#replyFrom').attr('value', replyId)
		if(document.getElementById('tag')) {
			document.getElementById('tag').remove()
		}
		container.insertAdjacentHTML('afterbegin', showReply)
		console.log(replyId)
	}
	else {
		$('#replyFrom').attr('value', id)
		if(document.getElementById('tag')) {
			document.getElementById('tag').remove()
		}
		container.insertAdjacentHTML('afterbegin', showReply)
		console.log(id)
	}
}

const removeReply = () => {
	document.getElementById('tag').remove()
	$('#replyFrom').attr('value', 0)
}
</script>

<body>
	<!--? Preloader Start -->
	<div id="preloader-active">
		<div
			class="preloader d-flex align-items-center justify-content-center">
			<div class="preloader-inner position-relative">
				<div class="preloader-circle"></div>
				<div class="preloader-img pere-text">
					<img src="resources/assets/img/logo/logo.png" alt="">
				</div>
			</div>
		</div>
	</div>
	<!-- Preloader Start -->
	<header>
		<!-- Header Start -->
		<div class="header-area">
			<div class="main-header header-sticky">
				<div class="container-fluid">
					<div class="menu-wrapper">
						<!-- Logo -->
						<div class="logo">
							<a href="index.html"><img
								src="resources/assets/img/logo/logo.png" alt=""></a>
						</div>
						<!-- Main-menu -->
						<div class="main-menu d-none d-lg-block">
							<nav>
								<ul id="navigation">
									<li><a href="<c:url value="/" />">Home</a></li>
									<li><a href="<c:url value="/shop" />">shop</a></li>
									<li><a href="about.html">about</a></li>
									<li class="hot"><a href="#">Latest</a>
										<ul class="submenu">
											<li><a href="shop.html"> Product list</a></li>
											<li><a href="product_details.html"> Product Details</a></li>
										</ul></li>
									<li><a href="order">Your order</a>
										<ul class="submenu">
											<li><a href="order?type=All">All Orders</a></li>
											<li><a href="order?type=Waitting">Waitting Orders</a></li>
											<li><a href="order?type=Confirmed">Confirmed Orders</a></li>
											<li><a href="order?type=Delivering">Delivering Orders</a></li>
											<li><a href="order?type=Received">Received Orders</a></li>
											<li><a href="order?type=Canceled">Canceled Orders</a></li>
										</ul></li>
									<li><a href="cartList">Your cart</a>
										<ul class="submenu">
											<li><a href="<c:url value="/cartList" />">Cart</a></li>
											<li><a href="">Element</a></li>
											<li><a href="">Confirmation</a></li>
											<li><a href="checkout/">Product Checkout</a></li>
										</ul></li>
									<li><a href="user/profile">Profile</a>
										<ul class="submenu">
											<li><a href="user/profile">Profile</a></li>
											<li><a href="order?type=All">Purchase</a></li>
											<li><a href="logout">Logout</a></li>
											<li><a href="login">Login</a></li>
										</ul></li>
									</li>
								</ul>
							</nav>
						</div>
						<!-- Header Right -->
						<div class="header-right">
							<ul>
								<li>
									<div class="nav-search search-switch">
										<span class="flaticon-search"></span>
									</div>
								</li>
								<!-- <li><a href="login.html"><span class="flaticon-user"></span></a></li> -->
								<li><a href="<c:url value="/cartList" />"><span
										class="flaticon-shopping-cart"></span></a></li>
							</ul>
						</div>
					</div>
					<!-- Mobile Menu -->
					<div class="col-12">
						<div class="mobile_menu d-block d-lg-none"></div>
					</div>
				</div>
			</div>
		</div>
		<!-- Header End -->
	</header>

	<main>
		<jsp:include page="${param.view}" />
	</main>

	<footer>
		<!-- Footer Start-->
		<div class="footer-area footer-padding">
			<div class="container">
				<div class="row d-flex justify-content-between">
					<div class="col-xl-3 col-lg-3 col-md-5 col-sm-6">
						<div class="single-footer-caption mb-50">
							<div class="single-footer-caption mb-30">
								<!-- logo -->
								<div class="footer-logo">
									<a href="index.html"><img
										src="resources/assets/img/logo/logo2_footer.png" alt=""></a>
								</div>
								<div class="footer-tittle">
									<div class="footer-pera">
										<p>Asorem ipsum adipolor sdit amet, consectetur
											adipisicing elitcf sed do eiusmod tem.</p>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xl-2 col-lg-3 col-md-3 col-sm-5">
						<div class="single-footer-caption mb-50">
							<div class="footer-tittle">
								<h4>Quick Links</h4>
								<ul>
									<li><a href="#">About</a></li>
									<li><a href="#"> Offers & Discounts</a></li>
									<li><a href="#"> Get Coupon</a></li>
									<li><a href="#"> Contact Us</a></li>
								</ul>
							</div>
						</div>
					</div>
					<div class="col-xl-3 col-lg-3 col-md-4 col-sm-7">
						<div class="single-footer-caption mb-50">
							<div class="footer-tittle">
								<h4>New Products</h4>
								<ul>
									<li><a href="#">Woman Cloth</a></li>
									<li><a href="#">Fashion Accessories</a></li>
									<li><a href="#"> Man Accessories</a></li>
									<li><a href="#"> Rubber made Toys</a></li>
								</ul>
							</div>
						</div>
					</div>
					<div class="col-xl-3 col-lg-3 col-md-5 col-sm-7">
						<div class="single-footer-caption mb-50">
							<div class="footer-tittle">
								<h4>Support</h4>
								<ul>
									<li><a href="#">Frequently Asked Questions</a></li>
									<li><a href="#">Terms & Conditions</a></li>
									<li><a href="#">Privacy Policy</a></li>
									<li><a href="#">Report a Payment Issue</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<!-- Footer bottom -->
				<div class="row align-items-center">
					<div class="col-xl-7 col-lg-8 col-md-7">
						<div class="footer-copy-right">
							<p>
								<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
								Copyright &copy;
								<script>
									document.write(new Date().getFullYear());
								</script>
								All rights reserved | This template is made with <i
									class="fa fa-heart" aria-hidden="true"></i> by <a
									href="https://colorlib.com" target="_blank">Colorlib</a>
								<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
							</p>
						</div>
					</div>
					<div class="col-xl-5 col-lg-4 col-md-5">
						<div class="footer-copy-right f-right">
							<!-- social -->
							<div class="footer-social">
								<a href="#"><i class="fab fa-twitter"></i></a> <a
									href="https://www.facebook.com/sai4ull"><i
									class="fab fa-facebook-f"></i></a> <a href="#"><i
									class="fab fa-behance"></i></a> <a href="#"><i
									class="fas fa-globe"></i></a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Footer End-->
	</footer>
	<!--? Search model Begin -->
	<div class="search-model-box">
		<div class="h-100 d-flex align-items-center justify-content-center">
			<div class="search-close-btn">+</div>
			<form method="get" action="search" class="search-model-form">
				<input type="text" id="search-input" name="search"
					placeholder="Searching key.....">
			</form>
		</div>
	</div>
	<!-- Search model end -->

	<!-- JS here -->

	<script src="resources/assets/js/vendor/modernizr-3.5.0.min.js"></script>
	<!-- Jquery, Popper, Bootstrap -->
	<script src="resources/assets/js/vendor/jquery-1.12.4.min.js"></script>
	<script src="resources/assets/js/popper.min.js"></script>
	<script src="resources/assets/js/bootstrap.min.js"></script>
	<!-- Jquery Mobile Menu -->
	<script src="resources/assets/js/jquery.slicknav.min.js"></script>

	<!-- Jquery Slick , Owl-Carousel Plugins -->
	<script src="resources/assets/js/owl.carousel.min.js"></script>
	<script src="resources/assets/js/slick.min.js"></script>

	<!-- One Page, Animated-HeadLin -->
	<script src="resources/assets/js/wow.min.js"></script>
	<script src="resources/assets/js/animated.headline.js"></script>
	<script src="resources/assets/js/jquery.magnific-popup.js"></script>

	<!-- Scrollup, nice-select, sticky -->
	<script src="resources/assets/js/jquery.scrollUp.min.js"></script>
	<script src="resources/assets/js/jquery.nice-select.min.js"></script>
	<script src="resources/assets/js/jquery.sticky.js"></script>

	<!-- contact js -->
	<script src="resources/assets/js/contact.js"></script>
	<script src="resources/assets/js/jquery.form.js"></script>
	<script src="resources/assets/js/jquery.validate.min.js"></script>
	<script src="resources/assets/js/mail-script.js"></script>
	<script src="resources/assets/js/jquery.ajaxchimp.min.js"></script>

	<!-- Jquery Plugins, main Jquery -->
	<script src="resources/assets/js/plugins.js"></script>
	<script src="resources/assets/js/main.js"></script>
<script src="resources/admin/vendor/datatables/jquery.dataTables.min.js"></script>
<script
	src="resources/admin/vendor/datatables/dataTables.bootstrap4.min.js"></script>

<!-- Page level custom scripts -->

<script src="resources/admin/js/demo/datatables-demo.js"></script>
	<script>
		$(document).ready(function() {
			jQuery.validator.addMethod("phonenu", function(value, element) {
				if (/^\d{3}-?\d{3}-?\d{4}$/g.test(value)) {
					return true;
				} else {
					return false;
				}
				;
			}, "Invalid phone number");
			
			jQuery.validator.addMethod("checkInteger", function(value) {
				if(Number.isInteger(+value) && value > 0) {
					return true;
				}
				return false;
			}, "Invalid quantity")

			$("#register-form").validate({
				rules : {
					username : "required",
					password : "required",
					name : "required",
					address : "required",
					email : "required",
					confirmPassword : {
						equalTo : "#password",
						required : true
					},
					phone : {
						phonenu : true,
						required : true
					}
				}
			});
			
			$("#cart_form").validate({
				rules: {
					quantity: {
						required: true,
						checkInteger: true
					}
				}
			})
			
			let url = window.location.href
			let query = ''
			let itemsPerPage = 12
			if (url.slice(-4) === 'shop') {
				query = '?viewMore='
			} else {
				query = '&viewMore='
				if (url.includes('?viewMore')) {
					query = '?viewMore='
				}
				/* if (url.includes('&viewMore')) {
					query = '?viewMore='
				} */
			}
			if (url.includes('viewMore')) {
				itemsPerPage = parseInt(url.substr(url.indexOf('viewMore') + 9,
						url.length - 1))
				console.log(itemsPerPage)
				itemsPerPage = itemsPerPage + 10
				url = url.slice(0, url.indexOf('viewMore') - 1)
			}
			$("#view_more").attr("href", url + query + itemsPerPage)
			
			
		})

	</script>
</body>
</html>