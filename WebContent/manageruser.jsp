<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>Quản lý tài khoản</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="title" content="Ask online Form">
<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="css/style.css" rel="stylesheet" type="text/css">
<link href="css/editor.css" rel="stylesheet" type="text/css">
<!-- <link href="css/animate.css" rel="stylesheet" type="text/css"> -->
<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
<script src="js/tata.js"></script>
<style type="text/css">
.link_button {
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	border-radius: 4px;
	border: solid 1px #20538D;
	text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.4);
	-webkit-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.4), 0 1px 1px
		rgba(0, 0, 0, 0.2);
	-moz-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.4), 0 1px 1px
		rgba(0, 0, 0, 0.2);
	box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.4), 0 1px 1px
		rgba(0, 0, 0, 0.2);
	background: #4479BA;
	color: #FFF;
	padding: 8px 12px;
	text-decoration: none;
}

.addCategory {
	margin-bottom: 20px;
	padding: 20px;
}
</style>
</head>

<body>
	<c:if test="${not empty sessionScope.success }">
		<script type="text/javascript">
			var success = "${sessionScope.success}";
			tata.success('Thành công', success);
		</script>
		<c:remove var="success" />
	</c:if>
	<c:if test="${not empty sessionScope.alert }">
		<script type="text/javascript">
			var alert = "${sessionScope.alert}";
			tata.error('Thất bại', alert);
		</script>
		<c:remove var="alert" />
	</c:if>
	<jsp:useBean id="dateAgo" class="Controller.DateAgo" />
	<!--======== Navbar =======-->
	<c:import url="includes/headertop.jsp"></c:import>
	
	<div class="top-menu-bottom932">
		<nav class="navbar navbar-default">
			<div class="container">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
						aria-expanded="false">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="home"><img src="image/logo.png"
						alt="Logo"></a>
				</div>
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li><a href="home">Trang chủ</a></li>
						<li><a href="askquestion">Đặt câu hỏi</a></li>
						<c:if test="${sessionScope.auth != null }">
							<li><a href="mypost">Bài đăng của tôi</a></li>
						</c:if>
						<c:if
							test="${sessionScope.auth.getType() != 0 and sessionScope.auth != null}">
							<li class="dropdown active"><a href="#"
								class="dropdown-toggle" data-toggle="dropdown" role="button"
								aria-haspopup="true" aria-expanded="false">Quản lý <span
									class="caret"></span></a>
								<ul class="dropdown-menu animated zoomIn">
									<li><a href="managerpost">Quản lý bài đăng</a></li>
									<li><a href="managercategory">Quản lý chủ đề </a></li>
									<li><a href="manageruser">Quản lý tài khoản</a></li>
								</ul></li>
						</c:if>
						<c:if test="${empty sessionScope.auth }">
							<li><a href="logIn.jsp?from=home">Đăng nhập</a></li>
							<li><a href="signup.jsp">Đăng ký</a></li>
						</c:if>
						<c:if test="${sessionScope.auth != null }">
							<li>
								<a href="messaging">Trò chuyện</a>
							</li>
						</c:if>
					</ul>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>
	</div>

	<section class="header-descriptin329">
		<div class="container">
			<h3>Tất cả tài khoản người dùng</h3>
			<ol class="breadcrumb breadcrumb839">
				<li><a href="#">Trang chủ</a></li>
				<li class="active">Quản lý tài khoản</li>
			</ol>
		</div>
	</section>
	<section class="main-content920">
		<div class="container">
			<div class="row">
				<div class="col-md-9 user-profile328903">
					<div class="addCategory">
						<div class="row">
							<div class="col-md-3">
								<a href="#" data-toggle="modal" data-target="#addUserModal"
									class="link_button" style="margin-bottom: 100px;"><i
									class="fa fa-plus-square	" aria-hidden="true"> Thêm tài khoản</i></a>
							</div>
							<div class="col-md-9">
								<form action="manageruser" method="get" >
									<div class="form-row">
										<input class="form-control" type="text" name="searchus" value="${not empty param.searchus?param.searchus:''}" placeholder="Tìm kiếm: Nhập từ gì đó để tìm tài khoản" aria-label="Search">
										<select class="form-control" name="type">
										  <option value="-1" ${param.type != null && param.type == -1 ? 'selected' : ""}>Tất cả</option>
										  <option value="0" ${param.type != null && param.type == 0 ? 'selected' : ""}>Member</option>
										  <option value="1" ${param.type != null && param.type == 1 ? 'selected' : ""}>Manager</option>
										  <option value="2" ${param.type != null && param.type == 2 ? 'selected' : ""}>Admin</option>
										</select>										
										<button type="submit" class="btn btn-primary">Tìm kiếm</button>																				
									</div>
								</form>
							</div>
						</div>
					</div>							
					
					<c:if test="${listUsers.size() == 0 }">
						<p class="text-center"> Không có kết quả phù hợp. </p>
					</c:if>
					<c:forEach items="${listUsers }" var="q">
						<div class="question-type2033">
							<div class="row">
								<div class="col-md-12">
									<div class="right-description893">
										<div id="que-hedder2983">
											<div class="col-md-1" style="margin-right: 20px;">
												<div class="left-user12923 left-user12923-repeat">
													<c:choose>
														<c:when test="${q.getImage() != null}">
															<a href="inbox?user=${q.getUniqueId() }"> <img src="images/${q.getImage() }"
																alt="Ảnh đại diện">
															</a>
														</c:when>
														<c:otherwise>
															<a href="inbox?user=${q.getUniqueId() }"> <img src="images/User-Linear-80px.png"
																alt="Ảnh đại diện">
															</a>
														</c:otherwise>
													</c:choose>

												</div>
											</div>
											<div>
												<h3 id="userid${q.getId() }">
													<a href="#">${q.getFirstName()} ${q.getMiddleName()}
														${q.getLastName()}</a>
												</h3>
												<div style="float: right; margin-right: 50px;">
													<c:choose>
														<c:when test="${q.getType() == 0}">
															<a href="#" class="button-ques2973"> <i
																class="fa fa-user-circle-o" aria-hidden="true"></i>
																Member
															</a>
														</c:when>
														<c:when test="${q.getType() == 1}">
															<a href="#" class="designetion439" style="color: white; margin: 0px;"><i
																class="fa fa-user-circle-o" aria-hidden="true"></i>
																Manager</a>
														</c:when>
														<c:otherwise>
															<a href="#" class="admin439" style="color: white;"><i
																class="fa fa-user-circle-o" aria-hidden="true"></i>
																Admin</a>
														</c:otherwise>
													</c:choose>
												</div>
											</div>

										</div>
										<div class="ques-details10018">
											<p id="">${q.getEmail() }</p>
											<p id="">${q.getIntro() }</p>
											<p id="">${q.getProfile() }</p>
										</div>
										<hr>
										<div class="ques-icon-info3293">
											<div style="float: right;">
												<c:choose>
													<c:when test="${sessionScope.auth.getType() == 2 }">
														<a id="btnEdit${q.getId() }" href="#" data-toggle="modal"
															data-target="#editUserModal" data-uid-id="${q.getId()}"
															data-firstname-id="${q.getFirstName() }"
															data-middlename-id="${q.getMiddleName() }"
															data-lastname-id="${q.getLastName() }"
															data-mobile-id="${q.getMobile() }"
															data-email-id="${q.getEmail() }"
															data-intro-id="${q.getIntro() }"
															data-profile-id="${q.getProfile() }"
															data-userType-id="${q.getType() }"> <i
															class="fa fa-pencil-square-o" aria-hidden="true"> Sửa</i></a> <a
															href="deleteuser?id=${q.getId() }" style="color: red;"
															onclick="return confirm('Bạn có chắc muốn xóa tài khoản?');"><i
															class="fa fa-trash" aria-hidden="true"> Xóa</i></a>
													</c:when>
													<c:when test="${sessionScope.auth.getType() == 1 && q.getType() != 2}">
														<a id="btnEdit${q.getId() }" href="#" data-toggle="modal"
															data-target="#editUserModal" data-uid-id="${q.getId()}"
															data-firstname-id="${q.getFirstName() }"
															data-middlename-id="${q.getMiddleName() }"
															data-lastname-id="${q.getLastName() }"
															data-mobile-id="${q.getMobile() }"
															data-email-id="${q.getEmail() }"
															data-intro-id="${q.getIntro() }"
															data-profile-id="${q.getProfile() }"
															data-userType-id="${q.getType() }"> <i
															class="fa fa-pencil-square-o" aria-hidden="true"> Sửa</i></a> <a
															href="deleteuser?id=${q.getId() }" style="color: red;"
															onclick="return confirm('Bạn có chắc muốn xóa tài khoản?');"><i
															class="fa fa-trash" aria-hidden="true"> Xóa</i></a>
													</c:when>
												</c:choose>												
											</div>
										</div>
									</div>
								</div>

							</div>
						</div>
					</c:forEach>

					<nav aria-label="Page navigation">
						<ul class="pagination">
							<c:if test="${tag > 1 }">
								<li><a href="manageruser?page=${tag - 1}&searchus=${not empty param.searchus?param.searchus:''}"
									aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
							</c:if>
							<c:forEach begin="1" end="${endP }" var="i">
								<li class="${tag == i?"active":''}"><a
									href="manageruser?page=${i}&searchus=${not empty param.searchus?param.searchus:''}">${i}</a></li>
							</c:forEach>
							<c:if test="${tag < endP }">
								<li><a href="manageruser?page=${tag+1}&searchus=${not empty param.searchus?param.searchus:''}" aria-label="Next"><span
										aria-hidden="true">&raquo;</span></a></li>
							</c:if>
						</ul>
					</nav>

				</div>
				<!-- Modal ADD User  -->
				<div class="modal fade" id="addUserModal" tabindex="-1"
					role="dialog" aria-labelledby="addUserModal" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<form id="addForm" name="addForm" action="">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="myModalLabel">Thêm Tài Khoản</h4>
								</div>
								<div class="modal-body">
									<input type="email" required class="form-control input-lg"
										name="email" value="" placeholder="Email"> <input
										type="password" id="password" required
										class="form-control input-lg" name="password" value=""
										placeholder="Mật khẩu"> <input type="password"
										id="confirm_password" required class="form-control input-lg"
										name="repassword" value="" placeholder="Nhập lại mật khẩu"> 
									<label class="radio-inline"> 
										<input type="radio" value="0" name="userType" checked>Member
									</label> 
									<label class="radio-inline"> 
									<input type="radio" value="1" name="userType">Manager
									</label> 
									<c:choose>
										<c:when test="${sessionScope.auth.getType() == 2}">
											<label class="radio-inline"> <input
												type="radio" value="2" name="userType">Admin
											</label>
										</c:when>
									</c:choose>
																	
									
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Đóng</button>
									<button type="submit" id="submit" class="btn btn-primary">Lưu</button>
								</div>
							</form>
						</div>
					</div>
				</div>
				<!-- End Modal ADD User  -->


				<!-- Modal Edit User  -->
				<div class="modal fade" id="editUserModal" tabindex="-1"
					role="dialog" aria-labelledby="editUserModal" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<form id="editForm" name="editForm" action="">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="myModalLabel">Sửa Tài Khoản</h4>
								</div>
								<div class="modal-body">
									<input type="hidden" name="uid" value=""> <input
										type="text" class="form-control input-lg" name="firstname"
										placeholder="Họ" value="" /> <input type="text"
										class="form-control input-lg" name="middlename"
										placeholder="Tên đệm" value="" /> <input type="text"
										class="form-control input-lg" name="lastname"
										placeholder="Tên" value="" /> <input type="number"
										class="form-control input-lg" name="mobile"
										placeholder="Số điện thoại" value="" /> <input type="email"
										required class="form-control input-lg" name="email" value=""
										placeholder="Email"> <input type="text"
										class="form-control input-lg" name="intro"
										placeholder="Mô tả ngắn" value="" />
									<textarea class="form-control" rows="5" name="profile"
										placeholder="Mô tả chi tiết"></textarea>

									<label class="radio-inline"><input type="radio"
										value="0" name="userType" checked>Member</label> <label
										class="radio-inline"><input type="radio" value="1"
										name="userType">Manager</label>
									<c:if test="${sessionScope.auth.getType()==2}">
										<label class="radio-inline"><input type="radio"
											value="2" name="userType">Admin</label>
									</c:if>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Đóng</button>
									<button type="submit" id="submit" class="btn btn-primary">Lưu</button>
								</div>
							</form>
						</div>
					</div>
				</div>
				<!-- End Modal Edit User  -->
				<!--                end of col-md-9 -->
				<!--           strart col-md-3 (side bar)-->
				<aside class="col-md-3 sidebar97239">
					<div class="status-part3821">
						<h4>Thống kê</h4>
						<i class="fa fa-question-circle" aria-hidden="true"> 
						Câu hỏi: ${countQuestion }</i> 
						<i class="fa fa-comment" aria-hidden="true">
						Chia sẻ: ${countAnswer }</i>
						<i class="fa fa-user" aria-hidden="true">
						Tài khoản: ${countUser }</i>
					</div>
					<div class="categori-part329">
						<h4>Chủ đề</h4>
						<ul>
							<c:forEach items="${listCate }" var="c">
								<c:choose>
									<c:when test="${param.cate eq  c.getSlug()}">
										<li><a class="active" href="home?cate=${c.getSlug() }">${c.getTitle() }</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="home?cate=${c.getSlug() }">${c.getTitle() }</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</ul>
					</div>
					<!-- login part-->
					<c:choose>
						<c:when test="${not empty sessionScope.auth }">
							<div class="login-part2389">
								<h4>
									Thông tin tài khoản <a href="InforUser.jsp"><i
										class="fa fa-info-circle" aria-hidden="true"></i></a>
								</h4>

								<div class="input-group300">
									<span><i class="fa fa-user" aria-hidden="true"></i></span> <input
										type="text" class="namein309" placeholder="Name"
										value="${auth.getFirstName() } ${auth.getMiddleName()} ${auth.getLastName()}">
								</div>
								<div class="input-group300">
									<span><i class="fa fa-envelope" aria-hidden="true"></i></span>
									<input type="email" class="passin309" placeholder="Email"
										value="${auth.getEmail() }">
								</div>
								<div class="input-group300">
									<span><i class="fa fa-lock" aria-hidden="true"></i></span>
									<c:choose>
										<c:when test="${auth.getType() == 0}">
											<input type="text" class="passin309" placeholder="Type"
												value="Member">
										</c:when>
										<c:when test="${auth.getType() == 1}">
											<input type="text" class="passin309" placeholder="Type"
												value="Manager">
										</c:when>
										<c:otherwise>
											<input type="text" class="passin309" placeholder="Type"
												value="Admin">
										</c:otherwise>
									</c:choose>
								</div>
								<a href="logout">
									<button type="button" class="userlogin320">Đăng xuất</button>
								</a>

							</div>
						</c:when>
						<c:otherwise>
							<div class="login-part2389">
								<form action="loginhome" method="post">
									<h4>Đăng nhập</h4>
									<div class="input-group300">
										<span><i class="fa fa-user" aria-hidden="true"></i></span> <input
											type="email" class="namein309" name="email"
											placeholder="Email" />
									</div>
									<div class="input-group300">
										<span><i class="fa fa-lock" aria-hidden="true"></i></span> <input
											type="password" class="passin309" name="password"
											placeholder="Mật khẩu" />
									</div>

									<button type="submit" class="userlogin320">Đăng nhập</button>

									<div class="rememberme">
										<a href="signup.jsp" class="resbutton3892">Đăng ký</a>
									</div>
								</form>
							</div>
						</c:otherwise>
					</c:choose>
					<!-- Administrators members-->
					<div class="highest-part302">
						<h4>Quản trị</h4>
						<c:forEach items="${listAdmin }" var="u" varStatus="loop">
							<div class="pints-wrapper">
								<div class="left-user3898">
									<c:choose>
										<c:when test="${not empty u.getImage() }">
											<a href="#"><img src="images/${u.getImage() }"
												alt="Image"></a>
										</c:when>
										<c:otherwise>
											<a href="#"><img src="images/User-Linear-80px.png"
												alt="Image"></a>
										</c:otherwise>
									</c:choose>
									<div class="imag-overlay39">
										<a href="#"><i class="fa fa-plus" aria-hidden="true"></i></a>
									</div>
								</div>
								<span class="points-details938"> <c:choose>
										<c:when
											test="${not empty u.getFirstName() or not empty u.getMiddleName() or not empty u.getLastName()}">
											<a href="#"><h5>${u.getFirstName()}
													${u.getMiddleName()} ${u.getLastName()}</h5></a>
											<c:choose>
												<c:when test="${u.getType()==1 }">
													<a href="#" class="designetion439" style="margin: 0px; margin: 0px;">Manager</a>
												</c:when>
												<c:otherwise>
													<a href="#" class="admin439">Admin</a>
												</c:otherwise>
											</c:choose>
											</a>
										</c:when>
										<c:otherwise>
											<a href="#"><h5>Unknown</h5></a>
											<c:choose>
												<c:when test="${u.getType()==1 }">
													<a href="#" class="designetion439" style="margin: 0px;">Manager</a>
												</c:when>
												<c:otherwise>
													<a href="#" class="admin439">Admin</a>
												</c:otherwise>
											</c:choose>
											</a>
										</c:otherwise>
									</c:choose>
								</span>
							</div>
							<c:if test="${!loop.last}">
								<hr>
							</c:if>
						</c:forEach>
					</div>
					<!-- end of Administrators members -->
					<!--          start tags part-->
					<%-- <div class="tags-part2398">
						<h4>Tags</h4>
						<ul>
							<c:forEach items="${listTag }" var="t">
								<c:choose>
									<c:when test="${param.tags eq t.getSlug() }">
										<li><a class="active" href="home?tags=${t.getSlug() }">${t.getTitle() }</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="home?tags=${t.getSlug() }">${t.getTitle() }</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</ul>
					</div> --%>
					<!--          End tags part-->
					<!--        start recent post  -->
					<div class="recent-post3290">
						<h4>Bài đăng gần đây</h4>
						<c:if test="${listRecentPost.size()==0 }">
							<div class="post-details021">
								<p>Chưa có bài đăng</p>
							</div>
						</c:if>
						<c:forEach items="${listRecentPost }" var="rcp" varStatus="loop">
							<c:if test="${rcp.isShow() == true }">
								<div class="post-details021">
									<a href="detail?post=${rcp.getSlug() }"><h5>${rcp.getTitle() }
										</h5></a>
									<p>${rcp.getSummary() }</p>
									<small style="color: #848991"><fmt:formatDate
											type="both" dateStyle="short" timeStyle="short"
											value="${rcp.getCreatedAt() }" /></small>
								</div>
								<c:if test="${!loop.last}">
									<hr>
								</c:if>
							</c:if>
						</c:forEach>
					</div>
					<!--       end recent post    -->
				</aside>
			</div>
		</div>
	</section>

	<!--    footer -->
	<div class="footer-search">
		<div class="container">
			<div class="row">
				<form class="navbar-form" role="search" action="home" method="get">
					<div id="custom-search-input">
						<div class="input-group col-md-12">
							<i class="fa fa-pencil-square-o" aria-hidden="true"></i> <input
								type="text" class="search-query form-control user-control30"
								name="search" value="${not empty param.search?param.search:"
								"}"
							placeholder="Nhập thông tin cần tìm kiếm ..." /> <span
								class="input-group-btn">
								<button class="btn btn-danger" type="submit">
									<span class=" glyphicon glyphicon-search"></span>
								</button>
							</span>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- Copyright footer -->
	<c:import url="includes/footer.jsp"></c:import>

	<!-- Backtop -->
	<div id="backtop">
		<i class="fa fa-chevron-up" aria-hidden="true"></i>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/editor.js"></script>

	<script type="text/javascript">
		$('#editUserModal')
				.on(
						'show.bs.modal',
						function(e) {
							var uid = $(e.relatedTarget).data('uid-id');
							$(e.currentTarget).find('input[name="uid"]').val(
									uid);

							var firstname = $(e.relatedTarget).data(
									'firstname-id');
							$(e.currentTarget).find('input[name="firstname"]')
									.val(firstname);

							var middlename = $(e.relatedTarget).data(
									'middlename-id');
							$(e.currentTarget).find('input[name="middlename"]')
									.val(middlename);

							var lastname = $(e.relatedTarget).data(
									'lastname-id');
							$(e.currentTarget).find('input[name="lastname"]')
									.val(lastname);

							var mobile = $(e.relatedTarget).data('mobile-id');
							$(e.currentTarget).find('input[name="mobile"]')
									.val(mobile);

							var email = $(e.relatedTarget).data('email-id');
							$(e.currentTarget).find('input[name="email"]').val(
									email);

							var intro = $(e.relatedTarget).data('intro-id');
							$(e.currentTarget).find('input[name="intro"]').val(
									intro);

							var profile = $(e.relatedTarget).data('profile-id');
							$(e.currentTarget).find('textarea[name="profile"]')
									.val(profile);

							var utype = $(e.relatedTarget).data('usertype-id');
							if (utype == 2)
								$(e.currentTarget).find(
										'input[name="userType"][value="2"]')
										.prop("checked", true);
							else if (utype == 1)
								$(e.currentTarget).find(
										'input[name="userType"][value="1"]')
										.prop("checked", true);
							else
								$(e.currentTarget).find(
										'input[name="userType"][value="0"]')
										.prop("checked", true);

						});
		$(document).ready(function() {
			$(window).scroll(function() {
				if ($(this).scrollTop()) {
					$('#backtop').fadeIn();
				} else {
					$('#backtop').fadeOut();
				}
			});
			$('#backtop').click(function() {
				$('html, body').animate({
					scrollTop : 0
				}, 500);
			});
		});
	</script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#editForm").submit(function(event) {
				editUser();
				return false;
			});
		});
		$(document).ready(function() {
			$("#addForm").submit(function(event) {
				if ($('#password').val() != $('#confirm_password').val()) {
					tata.error('Thất bại', "Kiểm tra lại mật khẩu!!!");
					return false;
				}
				addUser();
				return false;
			});
		});
		function editUser() {
			$.ajax({
				url : "/Forum/edituser",
				type : "post",
				data : $('form#editForm').serialize(),
				success : function(data) {
					if (data[0] == "success") {
						tata.success('Thành công', data[1]);
						setTimeout(function() {
							location.reload();
						}, 1000);
					} else {
						tata.error('Thất bại', data[1]);
					}
				},
				error : function(xhr) {
				}
			});
		}
		function addUser() {
			$.ajax({
				url : "/Forum/adduser",
				type : "post",
				data : $('form#addForm').serialize(),
				success : function(data) {
					if (data[0] == "success") {
						tata.success('Thành công', data[1]);

						setTimeout(function() {
							location.reload();
						}, 1000);
					} else {
						tata.error('Thất bại', data[1]);
					}
				},
				error : function(xhr) {
				}
			});
		}
	</script>
</body>

</html>