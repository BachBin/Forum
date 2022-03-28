<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html >
<head>
	<meta charset="UTF-8">
	<title>Đăng nhập Forum</title>
	<meta name="title" content="Ask online Form">
	<meta name="description" content="The Ask is a bootstrap design help desk, support forum website template coded and designed with bootstrap Design, Bootstrap, HTML5 and CSS. Ask ideal for wiki sites, knowledge base sites, support forum sites">
	<meta name="keywords" content="HTML, CSS, JavaScript,Bootstrap,js,Forum,webstagram ,webdesign ,website ,web ,webdesigner ,webdevelopment">
	<meta name="robots" content="index, nofollow">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link href="https://fonts.googleapis.com/css?family=Roboto:300,400" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
	<link rel="stylesheet" href="css/loginstyle.css">
	<script src="js/tata.js"></script>
	<style type="text/css">
		input[type="checkbox"] {
		  -webkit-appearance: checkbox;
		     -moz-appearance: checkbox;
		          appearance: checkbox;
		  display: inline-block;
		  width: auto;
		}
	</style>
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>

<body>
<c:if test="${not empty sessionScope.alert }">
	<script type="text/javascript">
		var alert = "${sessionScope.alert}";
		tata.error('Thất bại', alert);
	</script>
	<c:remove var="alert"/>
</c:if>
<div class="modal-wrap">

  <div class="modal-bodies">  	
    <div class="modal-body modal-body-step-1 is-showing">	    
      <a class="btn btn-primary btn-primary3838" style="text-decoration: none;" href="home">Trở về</a>        
      <div class="title">ĐĂNG NHẬP</div>
      <div class="description">Mừng bạn trở lại !!!</div>
      <form action="login" method="post" name="formLogin" onsubmit="return(validate());">
      	<input type="hidden" name="from" value="${param.from}">
        <input type="email" name="email" placeholder="E-Mail*" value="${not empty sessionScope.username?sessionScope.username: ''}"/>
        <input type="password" name="password" placeholder="Mật khẩu*" value="${not empty sessionScope.password?sessionScope.password:''}"/>       
       	<div style="float: right; margin-bottom: 10px;">
       		<a href="ForgotPassword.jsp" style="text-decoration: none; font-size: 14px;">Quên mật khẩu?</a>
       	</div>       	
        <div class="g-recaptcha" data-sitekey="6Lem47MdAAAAAFzttiIebnNIbKCKxJJqQBSiAKbG" style="margin: 0 auto;  width: fit-content;"></div>
      	<br>   
      	<br>  	
        <div class="text-center">
          	<button type="submit" class="button" style="border: none;">Đăng nhập</button>
            <a href="signup.jsp"><div class="button">Tạo tài khoản</div></a>
        </div>
          	
      </form>
    </div>

 
  </div>
</div>
  	<script src='https://code.jquery.com/jquery-2.2.4.min.js'></script>    
	<script type="text/javascript">
		function validate() {
			if( document.formLogin.email.value == "" ) {
				tata.error('Thất bại', "Email không được để trống.");
	            document.formLogin.email.focus() ;
	            return false;
	         }
			if( document.formLogin.password.value == "" ) {
				tata.error('Thất bại', "Mật khẩu không được để trống.");
	            document.formLogin.password.focus() ;
	            return false;
	         }
		}
	</script>
</body>
</html>
