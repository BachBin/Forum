 <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="title" content="Ask online Form">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Ask Me</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="css/style.css" rel="stylesheet" type="text/css">
<!-- <link href="css/animate.css" rel="stylesheet" type="text/css"> -->
<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
</head>
<script src="js/tata.js"></script>

<body>
	
	<!-- Facebook Comment -->
	<div id="fb-root"></div>
	<script async defer crossorigin="anonymous" src="https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v13.0&appId=972558093623884&autoLogAppEvents=1" nonce="2ICrdvpW"></script>
	
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
	<!-- ==========header mega navbar=======-->
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


	<!--======= welcome section on top background=====-->
	<section class="welcome-part-one">
		<div class="container">
			<div class="welcome-demop102 text-center">
				<h2 style="text-transform: uppercase;">Chào bạn đã đến với diễn đàn <br> Chúng tôi sẽ giải đáp thắc mắc của bạn nhanh chóng.</h2>
				<p>Nếu bạn có câu hỏi hay có gì hay muốn chia sẻ. Hãy đăng bài ngay.</p>
			</div>
		</div>
	</section>


	<!-- ======content section/body=====-->
	<section class="main-content920">
		<div class="container">
			<div class="row">
				<div class="col-md-9">
					<div id="main">
						<input 
							id="tab1" 
							type="radio" 
							name="tabs" checked> 
							<label for="tab1">Câu hỏi gần đây</label> 
						<input 
							id="tab2" 
							type="radio"
							name="tabs" ${param.page2 != null?"checked":""  }> 
							<label for="tab2">Chia sẻ mẹo và thủ thuật</label> 
						<input 
							id="tab3"
							type="radio" 
							name="tabs" ${param.page3 != null?"checked":""  }>
							<label for="tab3">Chưa có câu trả lời</label> 
						<input 
							id="tab4"
							type="radio" 
							name="tabs" ${param.page4 != null?"checked":""  }>
						<label for="tab4">Bài đăng gần đây</label>

						<jsp:useBean id="dateAgo" class="Controller.DateAgo" />

						<section id="content1">
							<!--Recent Question Content Section -->
							<c:if test="${listPostQuestion.size()==0 }">
								<div align="center" style="margin: 50px;">
									<h4>Chưa có bài đăng</h4>
								</div>
							</c:if>
							<c:forEach items="${listPostQuestion }" var="p">
								<c:if test="${p.isShow() == true }">
									<div class="question-type2033">
										<div class="row">
											<div class="col-md-1">
												<div class="left-user12923 left-user12923-repeat">
													<a href="inbox?user=${p.getUniqueId() }"><img src="images/${p.getImage() }"
														alt="image"> </a>
													<c:choose>
														<c:when test="${p.getTypeUser()== 1}">
															<a href="#" class="designetion439" style="text-decoration: none;">Manager</a>
														</c:when>
														<c:when test="${p.getTypeUser()== 0}">
															<a href="#" class="designetion439" style="text-decoration: none;">Member</a>
														</c:when>
														<c:otherwise>
															<a href="#" class="admin439" style="text-decoration: none;">Admin</a>
														</c:otherwise>
													</c:choose>
													<c:choose>
														<c:when test="${p.isSolved() == true }">
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
															<a href="detail?post=${p.getSlug() }">${p.getTitle() }</a>
														</h3>
													</div>
													<div class="ques-details10018">
														<p>${p.getSummary() }</p>
													</div>
													<hr>
													<div class="ques-icon-info3293">
														<a href="detail?post=${p.getSlug() }"><i
															class="fa fa-clock-o" aria-hidden="true">
																${dateAgo.formatDate(p.getCreatedAt()) }</i></a> <a
															href="detail?post=${p.getSlug() }"><i
															class="fa fa-question-circle-o" aria-hidden="true">
																Question</i></a> <a href="#" data-pid-id="${p.getId() }"
															data-toggle="modal" data-target="#reportModal"><i
															class="fa fa-bug" aria-hidden="true"> Report</i></a>
													</div>
												</div>
											</div>
											<div class="col-md-2">
												<div class="ques-type302">
													<a href="#">
														<button type="button" class="q-type238">
															<i class="fa fa-comment" aria-hidden="true">
																${p.getAnswer() } answer</i>
														</button>
													</a> <a href="#">
														<button type="button" class="q-type23 button-ques2973">
															<i class="fa fa-user-circle-o" aria-hidden="true">
																${p.getView() } view</i>
														</button>
													</a>
												</div>
											</div>
										</div>
									</div>
								</c:if>
							</c:forEach>
							<c:if test="${param.cate!=null }">
								<nav aria-label="Page navigation">
									<ul class="pagination">
										<c:if test="${tag1 > 1 }">
											<li><a href="home?cate=${param.cate }&page1=${tag1-1}"
												aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
										</c:if>
										<c:forEach begin="1" end="${endP1 }" var="i">
											<li class="${tag1 == i?"active":""}"><a
												href="home?cate=${param.cate }&page1=${i}">${i}</a></li>
										</c:forEach>
										<c:if test="${tag1 < endP1 }">
											<li><a href="home?cate=${param.cate }&page1=${tag1+1}"
												aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
										</c:if>
									</ul>
								</nav>
							</c:if>
							<c:if test="${param.tags!=null }">
								<nav aria-label="Page navigation">
									<ul class="pagination">
										<c:if test="${tag1 > 1 }">
											<li><a href="home?tags=${param.tags }&page1=${tag1-1}"
												aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
										</c:if>
										<c:forEach begin="1" end="${endP1 }" var="i">
											<li class="${tag1 == i?"active":""}"><a
												href="home?tags=${param.tags }&page1=${i}">${i}</a></li>
										</c:forEach>
										<c:if test="${tag1 < endP1 }">
											<li><a href="home?tags=${param.tags }&page1=${tag1+1}"
												aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
										</c:if>
									</ul>
								</nav>
							</c:if>
							<c:if test="${param.cate==null and param.tags==null}">
								<nav aria-label="Page navigation">
									<ul class="pagination">
										<c:if test="${tag1 > 1 }">
											<li><a href="home?page1=${tag1-1}" aria-label="Previous"><span
													aria-hidden="true">&laquo;</span></a></li>
										</c:if>
										<c:forEach begin="1" end="${endP1 }" var="i">
											<li class="${tag1 == i?"active":""}"><a
												href="home?page1=${i}">${i}</a></li>
										</c:forEach>
										<c:if test="${tag1 < endP1 }">
											<li><a href="home?page1=${tag1+1}" aria-label="Next"><span
													aria-hidden="true">&raquo;</span></a></li>
										</c:if>
									</ul>
								</nav>
							</c:if>
						</section>
						<!--  End of content-1------>

						<section id="content2">
							<!--Recently answered Content Section -->
							<c:if test="${listPostTip.size()==0 }">
								<div align="center" style="margin: 50px;">
									<h4>Chưa có bài đăng</h4>
								</div>
							</c:if>
							<c:forEach items="${listPostTip }" var="q">
								<c:if test="${q.isShow() == true }">
									<div class="question-type2033">
										<div class="row">
											<div class="col-md-1">
												<div class="left-user12923 left-user12923-repeat">
													<a href="inbox?user=${q.getUniqueId() }"><img src="images/${q.getImage() }"
														alt="image"> </a> 
													<c:choose>
														<c:when test="${q.getTypeUser()== 1}">
															<a href="#" class="designetion439 col" style="text-decoration: none;">Manager</a>
														</c:when>
														<c:when test="${q.getTypeUser()== 0}">
															<a href="#" class="designetion439 col" style="text-decoration: none;">Member</a>
														</c:when>
														<c:otherwise>
															<a href="#" class="admin439 col" style="text-decoration: none;">Admin</a>
														</c:otherwise>
													</c:choose>	
													
													<c:choose>
														<c:when test="${q.isSolved() == true }">
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
															<a href="detail?post=${q.getSlug() }">${q.getTitle() }</a>
														</h3>
													</div>
													<div class="ques-details10018">
														<p>${q.getSummary() }</p>
													</div>
													<hr>
													<div class="ques-icon-info3293">
														<a href="detail?post=${q.getSlug() }"><i
															class="fa fa-clock-o" aria-hidden="true">
																${dateAgo.formatDate(q.getCreatedAt()) }</i></a> <a
															href="detail?post=${q.getSlug() }"><i
															class="fa fa-comment" aria-hidden="true"> Tips</i></a> <a
															href="#" data-pid-id="${q.getId() }"
															data-toggle="modal" data-target="#reportModal"><i
															class="fa fa-bug" aria-hidden="true"> Report</i></a>
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
													</a>
												</div>
											</div>
										</div>
									</div>
								</c:if>
							</c:forEach>
							<c:if test="${param.cate!=null }">
								<nav aria-label="Page navigation">
									<ul class="pagination">
										<c:if test="${tag2 > 1 }">
											<li><a href="home?cate=${param.cate }&page2=${tag2-1}"
												aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
										</c:if>
										<c:forEach begin="1" end="${endP2 }" var="i">
											<li class="${tag2 == i?"active":""}"><a
												href="home?cate=${param.cate }&page2=${i}">${i}</a></li>
										</c:forEach>
										<c:if test="${tag2 < endP2 }">
											<li><a href="home?cate=${param.cate }&page2=${tag2+1}"
												aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
										</c:if>
									</ul>
								</nav>
							</c:if>
							<c:if test="${param.tags!=null }">
								<nav aria-label="Page navigation">
									<ul class="pagination">
										<c:if test="${tag2 > 1 }">
											<li><a href="home?tags=${param.tags }&page2=${tag2-1}"
												aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
										</c:if>
										<c:forEach begin="1" end="${endP2 }" var="i">
											<li class="${tag2 == i?"active":""}"><a
												href="home?tags=${param.tags }&page2=${i}">${i}</a></li>
										</c:forEach>
										<c:if test="${tag2 < endP2 }">
											<li><a href="home?tags=${param.tags }&page2=${tag2+1}"
												aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
										</c:if>
									</ul>
								</nav>
							</c:if>
							<c:if test="${param.cate==null and param.tags==null}">
								<nav aria-label="Page navigation">
									<ul class="pagination">
										<c:if test="${tag2 > 1 }">
											<li><a href="home?page2=${tag2-1}" aria-label="Previous"><span
													aria-hidden="true">&laquo;</span></a></li>
										</c:if>
										<c:forEach begin="1" end="${endP2 }" var="i">
											<li class="${tag2 == i?"active":""}"><a
												href="home?page2=${i}">${i}</a></li>
										</c:forEach>
										<c:if test="${tag2 < endP2 }">
											<li><a href="home?page2=${tag2+1}" aria-label="Next"><span
													aria-hidden="true">&raquo;</span></a></li>
										</c:if>
									</ul>
								</nav>
							</c:if>
						</section>
						<!--End content-2 -->

						<section id="content3">
							<!--No answered Content Section -->
							<c:if test="${listNoAnswer.size()==0 }">
								<div align="center" style="margin: 50px;">
									<h4>Chưa có bài đăng</h4>
								</div>
							</c:if>
							<c:forEach items="${listNoAnswer }" var="d">
								<c:if test="${d.isShow() == true }">
									<div class="question-type2033">
										<div class="row">
											<div class="col-md-1">
												<div class="left-user12923 left-user12923-repeat">
													<a href="inbox?user=${d.getUniqueId() }"><img src="images/${d.getImage() }"
														alt="image"> </a>
													<c:choose>
														<c:when test="${d.getTypeUser()== 1}">
															<a href="#" class="designetion439" style="text-decoration: none;">Manager</a>
														</c:when>
														<c:when test="${d.getTypeUser()== 0}">
															<a href="#" class="designetion439" style="text-decoration: none;">Member</a>
														</c:when>
														<c:otherwise>
															<a href="#" class="admin439" style="text-decoration: none;">Admin</a>
														</c:otherwise>
													</c:choose>
													<c:choose>														
														<c:when test="${d.isSolved() == true}">
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
															<a href="detail?post=${d.getSlug() }">${d.getTitle() }</a>
														</h3>
													</div>
													<div class="ques-details10018">
														<p>${d.getSummary() }</p>
													</div>
													<hr>
													<div class="ques-icon-info3293">
														<a href="detail?post=${d.getSlug() }"><i
															class="fa fa-clock-o" aria-hidden="true">
																${dateAgo.formatDate(d.getCreatedAt()) }</i></a>
														<c:choose>
															<c:when test="${d.isType() == 'true' }">
																<a href="detail?post=${d.getSlug() }"><i
																	class="fa fa-comment" aria-hidden="true"> Tips</i></a>
															</c:when>
															<c:otherwise>
																<a href="detail?post=${d.getSlug() }"><i
																	class="fa fa-question-circle-o" aria-hidden="true">
																		Question</i></a>
															</c:otherwise>
														</c:choose>
														<a href="#" data-pid-id="${d.getId() }"
															data-toggle="modal" data-target="#reportModal"><i
															class="fa fa-bug" aria-hidden="true"> Report</i></a>
													</div>
												</div>
											</div>
											<div class="col-md-2">
												<div class="ques-type302">
													<a href="#">
														<button type="button" class="q-type238">
															<i class="fa fa-comment" aria-hidden="true">
																${d.getAnswer() } answer</i>
														</button>
													</a> <a href="#">
														<button type="button" class="q-type23 button-ques2973">
															<i class="fa fa-user-circle-o" aria-hidden="true">
																${d.getView() } view</i>
														</button>
													</a>
												</div>
											</div>
										</div>
									</div>
								</c:if>
							</c:forEach>
							<!--End of content-3-->
							<c:if test="${param.cate!=null }">
								<nav aria-label="Page navigation">
									<ul class="pagination">
										<c:if test="${tag3 > 1 }">
											<li><a href="home?cate=${param.cate }&page3=${tag3-1}"
												aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
										</c:if>
										<c:forEach begin="1" end="${endP3 }" var="i">
											<li class="${tag3 == i?"active":""}"><a
												href="home?cate=${param.cate }&page3=${i}">${i}</a></li>
										</c:forEach>
										<c:if test="${tag3 < endP3 }">
											<li><a href="home?cate=${param.cate }&page3=${tag3+1}"
												aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
										</c:if>
									</ul>
								</nav>
							</c:if>
							<c:if test="${param.tags!=null }">
								<nav aria-label="Page navigation">
									<ul class="pagination">
										<c:if test="${tag3 > 1 }">
											<li><a href="home?tags=${param.tags }&page3=${tag3-1}"
												aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
										</c:if>
										<c:forEach begin="1" end="${endP3 }" var="i">
											<li class="${tag3 == i?"active":""}"><a
												href="home?tags=${param.tags }&page3=${i}">${i}</a></li>
										</c:forEach>
										<c:if test="${tag3 < endP3 }">
											<li><a href="home?tags=${param.tags }&page3=${tag3+1}"
												aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
										</c:if>
									</ul>
								</nav>
							</c:if>
							<c:if test="${param.cate==null and param.tags==null}">
								<nav aria-label="Page navigation">
									<ul class="pagination">
										<c:if test="${tag3 > 1 }">
											<li><a href="home?page3=${tag3-1}" aria-label="Previous"><span
													aria-hidden="true">&laquo;</span></a></li>
										</c:if>
										<c:forEach begin="1" end="${endP3 }" var="i">
											<li class="${tag3 == i?"active":""}"><a
												href="home?page3=${i}">${i}</a></li>
										</c:forEach>
										<c:if test="${tag3 < endP3 }">
											<li><a href="home?page3=${tag3+1}" aria-label="Next"><span
													aria-hidden="true">&raquo;</span></a></li>
										</c:if>
									</ul>
								</nav>
							</c:if>
						</section>


						<section id="content4">
							<!--Recent Question Content Section -->
							<c:if test="${listRecentPostAll.size()==0 }">
								<div align="center" style="margin: 50px;">
									<h4>Chưa có bài đăng</h4>
								</div>
							</c:if>
							<c:forEach items="${listRecentPostAll }" var="al">
								<c:if test="${al.isShow() == true }">
									<div class="question-type2033">
										<div class="row">
											<div class="col-md-1">
												<div class="left-user12923 left-user12923-repeat">
													<a href="inbox?user=${al.getUniqueId() }"><img src="images/${al.getImage() }"
														alt="image"> </a>
													<c:choose>
														<c:when test="${al.getTypeUser()== 1}">
															<a href="#" class="designetion439" style="text-decoration: none;">Manager</a>
														</c:when>
														<c:when test="${al.getTypeUser()== 0}">
															<a href="#" class="designetion439" style="text-decoration: none;">Member</a>
														</c:when>
														<c:otherwise>
															<a href="#" class="admin439" style="text-decoration: none;">Admin</a>
														</c:otherwise>
													</c:choose>
													<c:choose>
														<c:when test="${al.isSolved() == true}">
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
															<a href="detail?post=${al.getSlug() }">${al.getTitle() }</a>
														</h3>
													</div>
													<div class="ques-details10018">
														<p>${al.getSummary() }</p>
													</div>
													<hr>
													<div class="ques-icon-info3293">
														<a href="detail?post=${al.getSlug() }"><i
															class="fa fa-clock-o" aria-hidden="true">
																${dateAgo.formatDate(al.getCreatedAt()) }</i></a>
														<c:choose>
															<c:when test="${al.isType() == 'true' }">
																<a href="detail?post=${q.getSlug() }"><i
																	class="fa fa-comment" aria-hidden="true"> Tips</i></a>
															</c:when>
															<c:otherwise>
																<a href="detail?post=${al.getSlug() }"><i
																	class="fa fa-question-circle-o" aria-hidden="true">
																		Question</i></a>
															</c:otherwise>
														</c:choose>
														<a href="#" data-pid-id="${al.getId() }"
															data-toggle="modal" data-target="#reportModal"s><i
															class="fa fa-bug" aria-hidden="true"> Report</i></a>
													</div>
												</div>
											</div>
											<div class="col-md-2">
												<div class="ques-type302">
													<a href="#">
														<button type="button" class="q-type238">
															<i class="fa fa-comment" aria-hidden="true">
																${al.getAnswer() } answer</i>
														</button>
													</a> <a href="#">
														<button type="button" class="q-type23 button-ques2973">
															<i class="fa fa-user-circle-o" aria-hidden="true">
																${al.getView() } view</i>
														</button>
													</a>
												</div>
											</div>
										</div>
									</div>
								</c:if>
							</c:forEach>

							<!--End of content-4-->
							<c:if test="${param.cate!=null }">
								<nav aria-label="Page navigation">
									<ul class="pagination">
										<c:if test="${tag4 > 1 }">
											<li><a href="home?cate=${param.cate }&page4=${tag4-1}"
												aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
										</c:if>
										<c:forEach begin="1" end="${endP4 }" var="i">
											<li class="${tag4 == i?"active":""}"><a
												href="home?cate=${param.cate }&page4=${i}">${i}</a></li>
										</c:forEach>
										<c:if test="${tag4 < endP4 }">
											<li><a href="home?cate=${param.cate }&page4=${tag4+1}"
												aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
										</c:if>
									</ul>
								</nav>
							</c:if>
							<c:if test="${param.tags!=null }">
								<nav aria-label="Page navigation">
									<ul class="pagination">
										<c:if test="${tag4 > 1 }">
											<li><a href="home?tags=${param.tags }&page4=${tag4-1}"
												aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
										</c:if>
										<c:forEach begin="1" end="${endP4 }" var="i">
											<li class="${tag4 == i?"active":""}"><a
												href="home?tags=${param.tags }&page4=${i}">${i}</a></li>
										</c:forEach>
										<c:if test="${tag4 < endP4 }">
											<li><a href="home?tags=${param.tags }&page4=${tag4+1}"
												aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
										</c:if>
									</ul>
								</nav>
							</c:if>
							<c:if test="${param.cate==null and param.tags==null}">
								<nav aria-label="Page navigation">
									<ul class="pagination">
										<c:if test="${tag4 > 1 }">
											<li><a href="home?page4=${tag4-1}" aria-label="Previous"><span
													aria-hidden="true">&laquo;</span></a></li>
										</c:if>
										<c:forEach begin="1" end="${endP4 }" var="i">
											<li class="${tag4 == i?"active":""}"><a
												href="home?page4=${i}">${i}</a></li>
										</c:forEach>
										<c:if test="${tag4 < endP4 }">
											<li><a href="home?page4=${tag4+1}" aria-label="Next"><span
													aria-hidden="true">&raquo;</span></a></li>
										</c:if>
									</ul>
								</nav>
							</c:if>
						</section>

					</div>
					<!-- Comment Facebook -->
					<div class="fb-comments" data-href="http://localhost:8080/Forum/" data-width="100%" data-numposts="10" data-colorscheme="light"></div>
					<!-- End Comment Facebook -->
				</div>
				<!--end of col-md-9 -->
				<!--strart col-md-3 (side bar)-->
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
						<button type="submit" id="submit" class="btn btn-primary">Tố
							cáo</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- End Modal Report  -->	
	
	
	<!-- Copyright footer -->
	<c:import url="includes/footer.jsp"></c:import>

	<!-- Backtop -->
	<div id="backtop">
		<i class="fa fa-chevron-up" aria-hidden="true"></i>
	</div>
	
	<script src="js/tata.js"></script>	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
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
	</script>
	
	<script type="text/javascript">
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
</body>

</html>
