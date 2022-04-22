<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Chi tiết bài đăng</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="title" content="Forum Website">

<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="css/style.css" rel="stylesheet" type="text/css">
<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="css/responsive.css" rel="stylesheet" type="text/css">
</head>
<script src="js/tata.js"></script>
<style>
.like {
	color: green;
}
.dislike {
	color: red;
}
</style>
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
						<li class="active"><a href="home">Trang chủ</a></li>
						<li><a href="askquestion">Đặt câu hỏi</a></li>
						<c:if test="${sessionScope.auth != null }">
							<li><a href="mypost">Bài đăng của tôi</a></li>
						</c:if>
						<c:if
							test="${sessionScope.auth.getType() != 0 and sessionScope.auth != null}">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">Quản lý <span class="caret"></span></a>
								<ul class="dropdown-menu animated zoomIn">
									<c:if test="${sessionScope.auth != null && sessionScope.auth.getType() == 2}">
										<li><a href="managerforum">Quản lý diễn đàn</a></li>
									</c:if>
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
			<h3>Chi tiết bài đăng</h3>
			<ol class="breadcrumb breadcrumb839">
				<li><a href="#">Trang chủ</a></li>
				<li><a href="#">Chi tiết bài đăng</a></li>
				<li class="active">${post.getTitle() }</li>
			</ol>
		</div>
	</section>	
	<jsp:useBean id="dateAgo" class="Controller.DateAgo" />
	<section class="main-content920">
		<div class="container">
			<div class="row">
				<div class="col-md-9">
					<div class="post-details">
						<div class="details-header923">
							<div class="row">
								<div class="col-md-8">
									<div class="post-title-left129">
										<h3>${post.getTitle() }</h3>
									</div>
								</div>
								<div class="col-md-4">
									<div class="post-que-rep-rihght320">
									<c:choose>
										<c:when test="${post.isType() == false}">
											<a href="#"><i class="fa fa-question-circle"
											aria-hidden="true"></i> Câu hỏi</a>	
										</c:when>	
										<c:otherwise>
											<a href="#"><i class="fa fa-comment"
											aria-hidden="true"></i> Mẹo & Thủ thuật</a>	
										</c:otherwise>
									</c:choose>									 
										<a href="#"
											data-pid-id="${post.getId() }" data-toggle="modal"
											data-target="#reportModal" class="r-clor10">Tố cáo</a>
									</div>
								</div>
							</div>
						</div>
						<div class="post-details-info1982">
							<div style="width: 100%; height: 100%;">
								${post.getContent() }
							</div>							
							<hr>
							<div class="post-footer29032">
								<div class="l-side2023">
									<div id="statusPost">
										<c:choose>
											<c:when test="${post.isSolved() == true }">
												<i class="fa fa-check check2" aria-hidden="true"> solved</i>
											</c:when>
											<c:otherwise>
												<i class="fa fa-times check1" style="color: red;" aria-hidden="true"> solved</i>												
											</c:otherwise>
										</c:choose>
									</div>									
									<i class="fa fa-clock-o clock2" aria-hidden="true"> ${dateAgo.formatDate(post.getCreatedAt()) }</i>
									<i class="fa fa-commenting user2" aria-hidden="true"> ${post.getAnswer() } bình luận</i>
									<i class="fa fa-user user2" aria-hidden="true"> ${post.getView() } lượt xem</i>
									<div class="l-side2023" style="float: right;">
										<button style="border: none; background: none;"
											onclick="likePost(${post.getId()})">
											<i id="postlike${post.getId()}" class="fa-solid fa-thumbs-up ${dalike == true?'like':''}" aria-hidden="true"> ${solike }</i>
										</button>
										<button style="border: none; background: none;"
											onclick="dislikePost(${post.getId()})">
											<i id="postdislike${post.getId()}" class="fa-solid fa-thumbs-down ${dadislike == true?'dislike':''}" aria-hidden="true"> ${sodislike }</i>
										</button>
									</div>
								</div>								
							</div>
						</div>
					</div>
					
					<div class="author-details8392" style="min-height: 140px;">
						<div class="author-img202l">
							<img src="images/${post.getImage() }" alt="image">
							<div class="au-image-overlay text-center">
								<a href="inbox?user=${post.getUniqueId() }"><i class="fa fa-plus-square-o"
									aria-hidden="true"></i></a>
							</div>
						</div>
						<span class="author-deatila04re">
							<h5>${author.getFirstName()}${author.getMiddleName()}
								${author.getLastName()}</h5>
							<p>${not empty author.getIntro()?author.getIntro():'Chưa có thông tin gì thêm.' }</p>
						</span>
					</div>
					<div class="comment-list12993">
						<h3>Bình luận</h3>
						<div class="container">	
							<div class="row">
								<div class="comments-container">
									<c:choose>
										<c:when test="${listComment.size() == 0 }">
                                		Chưa có bình luận, hãy để lại bình luận của bạn.
                                	</c:when>
										<c:otherwise>
											<ul id="comments-list" class="comments-list">
												<c:forEach items="${listComment }" var="cmt">
													<li id="cmt${cmt.getId() }">
														<div class="comment-main-level">
															<!-- Avatar -->
															<div class="comment-avatar">
																<img src="images/${cmt.getImage() }" alt="Ảnh đại diện">
															</div>
															<!-- Contenedor del Comentario -->
															<div class="comment-box">
																<div class="comment-head">
																	<h6 class="comment-name">
																		<a href="inbox?user=${cmt.getUniqueId() }">${cmt.getFirstName()}
																			${cmt.getMiddleName()} ${cmt.getLastName()}</a>
																	</h6>

																</div>
																<div class="comment-content">
																	${cmt.getContent() }

																	<div style="float: right;">
																		<span>
																			<button style="border: none; background: none;" onclick="likecmt(${cmt.getId()},${post.getId()})">
																						<i id="likecmtid${cmt.getId() }"
																							class="fa-solid fa-thumbs-up" style="color: green;"
																							aria-hidden="true"> ${cmt.getLike() }</i>
																			</button>
																			<button style="border: none; background: none;" onclick="dislikecmt(${cmt.getId()}, ${post.getId()})">
																				<i id="dislikecmtid${cmt.getId() }"
																					class="fa-solid fa-thumbs-down" style="color: red;"
																					aria-hidden="true"> ${cmt.getDislike() }</i>
																			</button> 
																			<c:if test="${sessionScope.auth.getId() == cmt.getAuthorId() }">
																				<button style="border: none; background: none;" data-toggle="modal" data-target="#modalEditCmt" data-cmtid-id="${cmt.getId() }" data-cmt-content="${cmt.getContent() }">
																					<i id=""
																						class="fa-solid fa-edit" style="color: #23527C"
																						aria-hidden="true"></i>
																				</button> 
																			</c:if>																	
																			<c:choose>																				
																				<c:when
																					test="${sessionScope.auth.getId() == cmt.getAuthorId() || sessionScope.auth.getType() == 1 || sessionScope.auth.getType() == 2}">
																					<button style="border: none; background: none;" onclick="deletecmt(${cmt.getId()}, ${post.getId()})">
																						<i id="deletecmtid${cmt.getId() }"
																							class="far fa-times"
																							style="color: red; font-size: 20px;"
																							aria-hidden="true"></i>
																					</button>
																				</c:when>
																			</c:choose>
																		</span> <span> <i class="fa fa-clock-o"
																			aria-hidden="true">
																				${dateAgo.formatDate(cmt.getCreatedAt()) }</i>
																		</span>
																	</div>
																</div>
															</div>
														</div>
													</li>
												</c:forEach>
											</ul>
										</c:otherwise>
									</c:choose>
								</div>
							</div>							
						</div>
					</div>
					<div class="comment289-box">
						<h3>Để lại bình luận</h3>
						<hr>
						<div class="row">
							<c:choose>
								<c:when test="${sessionScope.auth != null }">									
										<div class="col-md-12">
											<input type="hidden" name="from" value="${post.getSlug() }">
											<input type="hidden" name="idp" value="${post.getId() }">
											<input type="hidden" name="idu"
												value="${post.getAuthorId() }"> 
											<input type="text" id="txtContent" name="content" class="comment-input219882" placeholder="Nhập bình luận">
											<button onclick="comment(${post.getId()})" type="button" class="pos393-submit">Đăng bình luận</button>
										</div>									
								</c:when>
								<c:otherwise>
									<div class="text-center">
										Vui lòng <span><a
											href="logIn.jsp?from=detail?post=${post.getSlug() }"
											style="text-decoration: none;">đăng nhập</a></span> để bình luận.
									</div>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="comment289-box recent-post3290">
						<h3 style="text-align: center;">Bài đăng có thể bạn quan tâm</h3>		
						<hr>		
						<c:if test="${listInvolve.size() == 0 }">
							<div style="text-align: center;">Hiện chưa có bài đăng liên quan</div>
						</c:if>		
						<c:forEach items="${listInvolve }" var="rvp" varStatus="loop">
							<div class="post-details021" style="text-align: center;">
								<a href="detail?post=${rvp.getSlug() }"><h5>${rvp.getTitle() }
									</h5></a>
								<p>${rvp.getSummary() }</p>
								<small style="color: #848991"><fmt:formatDate
										type="both" dateStyle="short" timeStyle="short"
										value="${rvp.getCreatedAt() }" /></small>
							</div>
							<c:if test="${!loop.last}">
								<hr>
							</c:if>
						</c:forEach>
					</div>
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

					<c:choose>
						<c:when test="${not empty sessionScope.auth }">
							<div class="login-part2389">
								<h4>
									Thông tin tài khoản <a href="InforUser.jsp"><i
										class="fa fa-info-circle" aria-hidden="true"></i></a>
								</h4>

								<div class="input-group300">
									<span><i class="fa fa-user" aria-hidden="true"></i></span> <input
										type="text" class="namein309" placeholder="Họ tên"
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
									<input type="hidden" name="from"
										value="${post.getSlug() }">
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
											<a href="#"><h5>${u.getFirstName() }
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
						</c:forEach>
					</div>
					<!--       end recent post    -->
				</aside>
			</div>
		</div>
	</section>
	
	<!-- Modal Report  -->
	<div class="modal fade" id="reportModal" tabindex="-1" role="dialog"
		aria-labelledby="reportModal" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form id="reportForm" name="reportForm" action="">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">Tố cáo bài đăng</h4>
					</div>
					<div class="modal-body">
						<input type="hidden" name="pid">
						<textarea rows="10" cols="" class="form-control input-lg"
							name="content" placeholder="Nội dung tố cáo"></textarea>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
						<button type="submit" class="btn btn-primary">Tố cáo</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- End Modal Report  -->
	
	<!-- Modal Edit Comment -->
	<div id="modalEditCmt" class="modal fade" role="dialog">
	  <div class="modal-dialog">
	
	    <!-- Modal content-->
	    <form id="editCmtForm" name="editCmtForm" action="">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title">Chỉnh sửa bình luận</h4>
		      </div>
		      <div class="modal-body">
		      	<input type="hidden" name="cmtid">
		      	<textarea rows="10" cols="" class="form-control input-lg"
							name="cmtcontent" placeholder="Nội dung bình luận"></textarea>		       
		      </div>
		      <div class="modal-footer">		     	
		        <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
		        <button type="submit" class="btn btn-primary">Lưu</button>
		      </div>
		    </div>
		</form>
	  </div>
	</div>
	<!-- End Modal Edit Comment -->
	
	<!--    footer -->
	<div class="footer-search">
		<div class="container">
			<div class="row">
				<form class="navbar-form" role="search" action="home" method="get">
					<div id="custom-search-input">
						<div class="input-group col-md-12">
							<i class="fa fa-pencil-square-o" aria-hidden="true"></i> <input
								type="text" class="search-query form-control user-control30"
								name="search" value="${not empty param.search?param.search:''}"
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
		$('#modalEditCmt').on('show.bs.modal', function(e) {	
		    var content = $(e.relatedTarget).data('cmt-content');
		   	var cmtid = $(e.relatedTarget).data('cmtid-id');
		   	
		    $(e.currentTarget).find('textarea[name="cmtcontent"]').val(content);
		    $(e.currentTarget).find('input[name="cmtid"]').val(cmtid);
		    
		});		
		
		$(document).ready(function() {
			$("#editCmtForm").submit(function(event) {
				editComment();
				return false;
			});
		});
		
		
		$('#reportModal').on('show.bs.modal', function(e) {
			var pid = $(e.relatedTarget).data('pid-id');

			$(e.currentTarget).find('input[name="pid"]').val(pid);
		});
		
		$(document).ready(function() {
			$("#reportForm").submit(function(event) {
				reportPost();
				return false;
			});
		});
		
		function editComment() {
			$.ajax({
				url : "/Forum/editcmt",
				type : "post",
				data : $('form#editCmtForm').serialize(),
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
		
		function reportPost() {
			$.ajax({
				url : "/Forum/report",
				type : "post",
				data : $('form#reportForm').serialize(),
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
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript">		
	
		function comment(idp) {			
			var txtContent = document.getElementById("txtContent").value
			if(txtContent ==="" || txtContent === null) {
				tata.error('Thất bại', "Nhập nội dung bình luận");
				return;
			}			
			$.ajax({
				url : "/Forum/comment",
				type : "post",
				data : {
					idp : idp,
					content : txtContent
				},
				success : function(data) {
					if (data[0] == "success") {
						tata.success('Thành công', data[1]);
						setTimeout(function() {
							location.reload();
						}, 1000);
					}
					else {
						tata.error('Thất bại', data[1]);
					}
					
				},
				error : function(xhr) {
				}
			});
		}
		function likePost(idp) {
			var sll = document.getElementById("postlike" + idp);
			var sld = document.getElementById("postdislike" + idp);
			
			$.ajax({
				url : "/Forum/likepost",
				type : "post",
				data : {					
					idp : idp
				},
				success : function(data) {
					const myArr = JSON.parse(data);
					if(myArr[0] == 'success')
					{
						sll.innerHTML = " "+myArr[1];
						sld.innerHTML =  " "+myArr[2];
						
						if(sld.classList.contains('dislike')) {
							sld.classList.remove("dislike");
						}
						if(sll.classList.contains('like')) {
							sll.classList.remove("like");
						}
						else {
							sll.classList.add("like");
						}
					}
					else {
						tata.error('Thất bại', myArr[0]);
					}
				},
				error : function(xhr) {
				}
			});
		}
		function dislikePost(idp) {
			var sll = document.getElementById("postlike" + idp);
			var sld = document.getElementById("postdislike" + idp);
			$.ajax({
				url : "/Forum/dislikepost",
				type : "post",
				data : {					
					idp : idp
				},
				success : function(data) {
					const myArr = JSON.parse(data);
					if(myArr[0] == 'success')
					{		
						sll.innerHTML = " "+myArr[1];
						sld.innerHTML =  " "+myArr[2];
						
						if(sll.classList.contains('like')) {
							sll.classList.remove("like");
						}					
						if(sld.classList.contains('dislike')) {
							sld.classList.remove("dislike");
						}
						else {
							sld.classList.add("dislike");
						}
					}
					else {
						tata.error('Thất bại', myArr[0]);
					}
				},
				error : function(xhr) {
				}
			});
		}
		
		
		function likecmt(idcmt, idpost) {
			var stt = document.getElementById("statusPost");
			var sll = document.getElementById("likecmtid" + idcmt);
			var sld = document.getElementById("dislikecmtid" + idcmt);
			
			console.log(idpost)
			$.ajax({
				url : "/Forum/likecmt",
				type : "get",
				data : {
					idcmt : idcmt,
					idpost : idpost
				},
				success : function(data) {
					const myArr = JSON.parse(data);					
					
					if(myArr[0] == 'success')
					{				
						if(myArr[1] == '0' && myArr[3] == 'ok') {
							stt.innerHTML = `<i class="fa fa-times check1" style="color: red;" aria-hidden="true"> solved</i>`;
						}
						else {
							stt.innerHTML = `<i class="fa fa-check check2" aria-hidden="true"> solved</i>`;
						}
						
						sll.innerHTML = " "+myArr[1];
						sld.innerHTML =  " "+myArr[2];
						
						if(sll.classList.contains('like')) {
							sll.classList.remove("like");
						}					
						if(sld.classList.contains('dislike')) {
							sld.classList.remove("dislike");
						}
						else {
							sld.classList.add("dislike");
						}
					}
					else {
						tata.error('Thất bại', myArr[0]);
					}
				},
				error : function(xhr) {
				}
			});
		}
		function dislikecmt(idcmt, idpost) {
			var stt = document.getElementById("statusPost");
			var sll = document.getElementById("likecmtid" + idcmt);
			var sld = document.getElementById("dislikecmtid" + idcmt);
			$.ajax({
				url : "/Forum/dislikecmt",
				type : "get",
				data : {
					idcmt : idcmt,
					idpost : idpost
				},
				success : function(data) {
					const myArr = JSON.parse(data);
					if(myArr[0] == 'success')
					{		
						if(myArr[1] == '0' && myArr[3] == 'ok') {
							stt.innerHTML = `<i class="fa fa-times check1" style="color: red;" aria-hidden="true"> solved</i>`;
						}
						else {
							stt.innerHTML = `<i class="fa fa-check check2" aria-hidden="true"> solved</i>`;
						}
						sll.innerHTML = " "+myArr[1];
						sld.innerHTML =  " "+myArr[2];
						
						if(sll.classList.contains('like')) {
							sll.classList.remove("like");
						}					
						if(sld.classList.contains('dislike')) {
							sld.classList.remove("dislike");
						}
						else {
							sld.classList.add("dislike");
						}
					}
					else {
						tata.error('Thất bại', myArr[0]);
					}
				},
				error : function(xhr) {
				}
			});
		}
		function deletecmt(idc, idpost) {	
			var check = confirm('Bạn có chắc muốn xóa bình luận?');
			if(check == false) return;
			var eleul = document.getElementById("comments-list")			
			var eleli = document.getElementById("cmt"+idc)			
			eleli.remove()
			$.ajax({
				url : "/Forum/deletecomment",
				type : "get",
				data : {
					idc : idc,		
					idpost : idpost
				},
				success : function(data) {	
					if(data[0] == "success"){
						tata.success('Thành công', data[1]);
					}	
					setTimeout(function() {
						location.reload();
					}, 1000);
				},
				error : function(xhr) {
				}
			});
		}		
	</script>	
	
</body>

</html>