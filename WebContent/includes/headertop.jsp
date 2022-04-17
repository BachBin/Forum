<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
							<li>
								<a href="#">
									<i class="fa fa-clock-o"  aria-hidden="true"></i>
									<span id="clock"></span>
								</a>
							</li>
						</ul>
					</div>
				</div>
				<div class="col-md-6">
					<div class="navbar-serch-right-side">
						<form class="navbar-form" role="search" action="home" method="get">
							<div class="input-group add-on">
								<input class="form-control form-control222" name="search"
									placeholder="Tìm kiếm" id="srch-term" type="text"
									value="${not empty param.search?param.search:''}">
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
	
	
	<script type="text/javascript">
		var myVar=setInterval(function () {myTimer()}, 1000);
		var counter = 0;
		function myTimer() {
		    var date = new Date();
		    document.getElementById("clock").innerHTML = date.toLocaleString();
		}
	</script>
	