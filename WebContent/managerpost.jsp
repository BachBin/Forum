<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>Quản lý bài đăng</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="title" content="Ask online Form">
<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="css/style.css" rel="stylesheet" type="text/css">
<link href="css/editor.css" rel="stylesheet" type="text/css">
<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
<script src="js/tata.js"></script>
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
	<jsp:useBean id="report" class="Bo.Reportbo" />	
	<div class="top-bar">
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<div class="navbar-menu-left-side239">
						<ul>
							<li><a
								href="mailto:bachvanmanh06072000@gmail.com?subject=Hỗ trợ Website Forum"
								target="_blank"><i class="fa fa-envelope-o"
									aria-hidden="true"></i>Liên lạc</a></li>
							<li><a href="tel: 0827700854" target="_blank"><i
									class="fa fa-headphones" aria-hidden="true"></i>Hổ trợ</a></li>
						</ul>
					</div>
				</div>
				<div class="col-md-6">
					<div class="navbar-serch-right-side">
						<form class="navbar-form" role="search" action="home" method="get">
							<div class="input-group add-on">
								<input class="form-control form-control222" name="search"
									placeholder="Tìm kiếm" id="srch-term" type="text"
									value="${not empty param.search?param.search:""}">
								<div class="input-group-btn">
									<button class="btn btn-default btn-default2913" type="submit">
										<i class="glyphicon glyphicon-search"></i>
									</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
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
			<h3>Tất cả bài đăng</h3>
			<ol class="breadcrumb breadcrumb839">
				<li><a href="#">Trang chủ</a></li>
				<li class="active">Quản lý bài đăng</li>
			</ol>
		</div>
	</section>
	<section class="main-content920">		
		<div class="container">
			<div class="row">
				<div class="col-md-9" style="margin-top: 20px;">
					<form action="managerpost" method="get">
						<input class="form-control" type="text" name="searchmn" value="${not empty param.searchmn?param.searchmn:''}" placeholder="Tìm kiếm: Nhập từ gì đó trong bài đăng" aria-label="Search">
						<select class="form-control" name="type">
							<option value="-1" ${param.type != null && param.type == -1 ? 'selected' : ""}>Tất cả</option>
							<option value="0" ${param.type != null && param.type == 0 ? 'selected' : ""}>Câu hỏi</option>
							<option value="1" ${param.type != null && param.type == 1 ? 'selected' : ""}>Mẹo và thủ thuật</option>										
						</select>										
						<button type="submit" class="btn btn-primary">Tìm kiếm</button>	
					</form>
				</div>
				<div class="col-md-9 user-profile328903">
					<c:if test="${listPost.size() == 0 }">
						<p class="text-center"> Không có kết quả phù hợp. </p>
					</c:if>
					<c:forEach items="${listPost }" var="q">
						<div class="question-type2033">
							<div class="row">
								<div class="col-md-1">
									<div class="left-user12923 left-user12923-repeat">
										<a href="inbox?user=${q.getUniqueId() }"><img src="images/${q.getImage() }" alt="image">
										</a>
										<c:choose>
											<c:when test="${q.isSolved() == true}">
												<a href="#"><i class="fa fa-check" aria-hidden="true"></i></a>
											</c:when>
											<c:otherwise>
												<a href="#"><i class="fa fa-times" aria-hidden="true"></i></a>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
								<div class="col-md-9">
									<div class="right-description893">
										<div id="que-hedder2983">
											<h3>
												<a href="detail?post=${q.getSlug() }" target="_blank">${q.getTitle() }</a>
											</h3>
										</div>
										<div class="ques-details10018">
											<p>${q.getSummary() }</p>
										</div>
										<hr>
										<div class="ques-icon-info3293">
											<a href="detail?post=${q.getSlug() }"><i
												class="fa fa-clock-o" aria-hidden="true">
													${dateAgo.formatDate(q.getCreatedAt()) }</i></a>
											<c:choose>
												<c:when test="${q.isType() == 'true' }">
													<a href="detail?post=${q.getSlug() }"><i
														class="fa fa-comment" aria-hidden="true"> Tips</i></a>
												</c:when>
												<c:otherwise>
													<a href="detail?post=${q.getSlug() }"><i
														class="fa fa-question-circle-o" aria-hidden="true">
															Question</i></a>
												</c:otherwise>
											</c:choose>
											<div style="float: right;">
												<c:if test="${q.isShow() == false }">
													<a href="admit?post=${q.getSlug() }"> <i
														class="fa fa-unlock" aria-hidden="true"> Duyệt</i>
													</a>
												</c:if>
												<a href="askquestion?post=${q.getSlug() }"> <i
													class="fa fa-pencil-square-o" aria-hidden="true"> Sửa</i>
												</a> <a href="deletepost?post=${q.getSlug() }"
													style="color: red;"
													onclick="return confirm('Bạn có chắc muốn xóa bài đăng?');">
													<i class="fa fa-trash" aria-hidden="true"> Xóa</i>
												</a>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-2">
									<div class="ques-type302">
										<a href="#">
											<button type="button" class="q-type238">
												<i class="fa fa-comment" aria-hidden="true">
													${q.getAnswer() } answer</i>
											</button>
										</a> <a href="#">
											<button type="button" class="q-type23 button-ques2973">
												<i class="fa fa-user-circle-o" aria-hidden="true">
													${q.getView() } view</i>
											</button>
										</a> <a href="#" data-toggle="modal" data-target="#ReportModal" data-id="${q.getSlug() }">
											<button type="button" class="q-type23 button-ques2973">
												<i class="fa fa-user-circle-o" aria-hidden="true">
													${report.getSLReport(q.getId()) } report</i>
											</button>
										</a>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>

					<nav aria-label="Page navigation">
						<ul class="pagination">
							<c:if test="${tag > 1 }">
								<li><a href="managerpost?page=${tag - 1}&searchmn=${not empty param.searchmn?param.searchmn:''}"
									aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
							</c:if>
							<c:forEach begin="1" end="${endP }" var="i">
								<li class="${tag == i?"active":""}"><a
									href="managerpost?page=${i}&searchmn=${not empty param.searchmn?param.searchmn:''}">${i}</a></li>
							</c:forEach>
							<c:if test="${tag < endP }">
								<li><a href="managerpost?page=${tag+1}&searchmn=${not empty param.searchmn?param.searchmn:''}" aria-label="Next"><span
										aria-hidden="true">&raquo;</span></a></li>
							</c:if>
						</ul>
					</nav>
				</div>
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
											<a href="inbox?user=${u.getUniqueId() }"><img src="images/${u.getImage() }"
												alt="Image"></a>
										</c:when>
										<c:otherwise>
											<a href="inbox?user=${u.getUniqueId() }"><img src="images/User-Linear-80px.png"
												alt="Image"></a>
										</c:otherwise>
									</c:choose>
									<div class="imag-overlay39">
										<a href="inbox?user=${u.getUniqueId() }"><i class="fa fa-plus" aria-hidden="true"></i></a>
									</div>
								</div>
								<span class="points-details938"> <c:choose>
										<c:when
											test="${not empty u.getFirstName() or not empty u.getMiddleName() or not empty u.getLastName()}">
											<a href="#"><h5>${u.getFirstName()}
													${u.getMiddleName()} ${u.getLastName()}</h5></a>
											<c:choose>
												<c:when test="${u.getType()==1 }">
													<a href="#" class="designetion439" style="margin: 0px;">Manager</a>
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
	<!-- Modal Seen Report  -->
	<div class="modal fade" id="ReportModal" tabindex="-1"
		role="dialog" aria-labelledby="ReportModal" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form id="reportForm" name="reportForm" action="">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">Nội Dung Tố Cáo</h4>
					</div>
					<div class="modal-body" id="contentRP">
											
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>						
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- End Modal Seen Report  -->
	<!--    footer -->
	<div class="footer-search">
		<div class="container">
			<div class="row">
				<form class="navbar-form" role="search" action="home" method="get">
					<div id="custom-search-input">
						<div class="input-group col-md-12">
							<i class="fa fa-pencil-square-o" aria-hidden="true"></i> <input
								type="text" class="search-query form-control user-control30"
								name="search" value="${not empty param.search?param.search:""}"
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
	
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/editor.js"></script>
	<script type="text/javascript">
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
	
	<script type="text/javascript">
		$('#ReportModal').on('show.bs.modal', function(e) {
			var slug = $(e.relatedTarget).data('id');			
			$.ajax({
				url : "/Forum/getReport",
				type : "post",
				data : {slug:slug},
				success : function(data) 
				{		
					if(data!="") {
						document.getElementById('contentRP').innerHTML = data;
					}
					else {
						document.getElementById('contentRP').innerHTML = "<p>Bài đăng chưa có tố cáo.</p>";
					}
				}				
			})
		});
		
	</script>	
</body>

</html>