<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Teamphony</title>

<!-- Bootstrap -->
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Font Awesome cdn-->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">


<!-- Custom Theme Style -->
<link
	href="${pageContext.request.contextPath}/resources/css/teamStyle.min.css"
	rel="stylesheet">

<!-- jQuery -->
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>

<!-- Bootstrap -->
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

<!-- Custom Theme Scripts -->
<script
	src="${pageContext.request.contextPath}/resources/js/teamScript.min.js"></script>



<style type="text/css">
.list-member {
	padding: 14px;
}

#menuTitle {
	margin-bottom: 50px;
}

#withdrawBtn {
	margin-top: 60px;
}

#teamManage {
	padding-top: 7%;
	padding-left: 10%;
	margin-right: 0px;
}

.profile {
	margin-bottom: 80px;
}

.card {
	background: #FFF;
	border: 1px solid #AAA;
	border-bottom: 3px solid #BBB;
	padding: 0px;
	margin: 15px;
	overflow: hidden;
}

.card h1 {
	margin: 0px;
	padding: 10px;
	padding-bottom: 0px;
}

.card p {
	margin: 0px;
	padding: 10px;
}

.card-image h1, .card-image h2, .card-image h3, .card-image h4,
	.card-image h5, .card-image h6 {
	position: absolute;
	bottom: 0px;
	width: 100%;
	color: white;
	background: rgba(0, 0, 0, 0.65);
	margin: 0px;
	padding: 6px;
	border: none;
}

.card-image .banner {
	height: 50px;
	width: 50px;
	top: 0px;
	right: 0px;
	background-position: top right;
	background-repeat: no-repeat;
	background-image: url('../images/new.png');
	position: absolute;
}

.card-image {
	width: 100%;
	height: 200px;
	padding: 0px;
	margin: 0px;
	background-position: center;
	background-repeat: no-repeat;
	position: relative;
	overflow: hidden;
}

.image1 {
	background-image:
		url('http://farm6.staticflickr.com/5323/9902848784_cbd10ba3ca_c.jpg');
}

.float-left .card {
	width: 300px;
	height: 270px;
}

.float-right {
	float: right;
	width: 300px;
	height: 270px;
}
</style>

</head>


<body class="nav-md">
	<div class="container body">
		<div class="main_container">

			<div class="col-md-3 left_col">
				<div class="left_col scroll-view">
					<div class="navbar nav_title" style="border: 0;">
						<a href="index.html" class="site_title"><i class="fa fa-paw"></i>
							<span>Teamphony</span></a>
					</div>

					<div class="clearfix"></div>


					<!-- menu profile quick info -->
					<div class="profile">
						<div class="profile_pic">
							<img
								src="${pageContext.request.contextPath}/resources/images/avatar.png"
								alt="..." class="img-circle profile_img">
						</div>
						<div class="profile_info">
							<span>${member.memberId }</span>
							<h2>${member.alias }</h2>
						</div>
					</div>
					<!-- /menu profile quick info -->

					<br />

					<!-- sidebar menu -->
					<%@ include file="/views/common/sideMenu.jspf"%>
					<!-- /sidebar menu -->


				</div>
			</div>

			<!-- top navigation -->

			<div class="top_nav">
				<div class="nav_menu">
					<nav>
						<div class="nav toggle">
							<a id="menu_toggle"><i class="fa fa-bars"></i></a>
						</div>

						<ul class="nav navbar-nav navbar-right">
							<li class=""><a href="javascript:;"
								class="user-profile dropdown-toggle" data-toggle="dropdown"
								aria-expanded="false"> <img
									src="${pageContext.request.contextPath}/resources/images/avatar.png"
									alt="">tnghsla13 <span class=" fa fa-angle-down"></span>
							</a>
								<ul class="dropdown-menu dropdown-usermenu pull-right">
									<li><a href="javascript:;"> Profile</a></li>
									<li><a href="javascript:;">Help</a></li>
									<li><a href="login.html"><i
											class="fa fa-sign-out pull-right"></i> Log Out</a></li>
								</ul></li>
						</ul>
					</nav>
				</div>
			</div>
			<!-- /top navigation -->

			<!-- page content -->
			<div class="right_col" role="main">

				<div class="container" id="teamManage">
					<div class="content float-right">
						<a class="btn btn-info btn-lg" href="${pageContext.request.contextPath}/views/post/createPost.jsp">등록</a>
					</div>
					<div class="content float-left">
						<c:choose>
							<c:when test="${listPost eq null || empty listPost }">
								<p>게시물이 존재하지 않습니다. 팀원들과 공유하고 싶은 자료를 올려보세요.</p>
							</c:when>
							<c:otherwise>
								<c:forEach items="${listPost}" var="post" varStatus="status">
									<a class="card"
										href="${pageContext.request.contextPath}/post/searchByPostId.do?postId=${post.id}">
										<p>${post.contents }</p>
									</a>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>