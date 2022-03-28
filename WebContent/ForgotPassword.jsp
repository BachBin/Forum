<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html >
<head>
	<meta charset="UTF-8">
	<title>Quên mật khẩu Forum</title>
	<meta name="title" content="Ask online Form">
	<meta name="description" content="The Ask is a bootstrap design help desk, support forum website template coded and designed with bootstrap Design, Bootstrap, HTML5 and CSS. Ask ideal for wiki sites, knowledge base sites, support forum sites">
	<meta name="keywords" content="HTML, CSS, JavaScript,Bootstrap,js,Forum,webstagram ,webdesign ,website ,web ,webdesigner ,webdevelopment">
	<meta name="robots" content="index, nofollow">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="language" content="English">
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

	<div class="modal-wrap">

  <div class="modal-bodies">  	
    <div class="modal-body modal-body-step-1 is-showing">	    
      <a class="btn btn-primary btn-primary3838" style="text-decoration: none;" href="logIn.jsp?from=home">Trở về</a>        
      <div class="title">Quên mật khẩu</div>      
      <form action="forgotpassword" method="post">      	
        <input type="email" name="email" placeholder="Email" value=""/>              
       	     	
        <div class="g-recaptcha" data-sitekey="6Lem47MdAAAAAFzttiIebnNIbKCKxJJqQBSiAKbG" style="margin: 0 auto;  width: fit-content;"></div>
      	<br>   
      	<br>  	
        <div class="text-center">
          	<button type="submit" class="button" style="border: none;">Lấy lại mật khẩu</button>            
        </div>
          	
      </form>
    </div>

 
  </div>
</div>
  <script src='https://code.jquery.com/jquery-2.2.4.min.js'></script>    

</body>
</html>
