<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html >
<head>
	<meta charset="UTF-8">
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
				<a style="text-decoration: none;" class="btn btn-primary btn-primary3838" href="logIn.jsp?from=home">Trở về</a>							
				<div class="title">ĐĂNG KÝ</div>
				<div class="description">Bạn chưa có tài khoản???</div>
				<form action="signup" method="post" name="formRegistry" onsubmit="return(validate());">					 
					<input type="email" id="mailregistry" name="email" placeholder="E-Mail*" value="${not empty sessionScope.username1?sessionScope.username1:''}"/> 
					<div class="text-right">
						<button type="button" onclick="sendMa()" class="button" style="border: none;">Lấy mã</button>															
					</div>
					<input type="text" name="maxacnhan" placeholder="Mã xác nhận*" value=""/> 
					<input type="password" name="password" placeholder="Mật khẩu" value="${not empty sessionScope.password1?sessionScope.password1:''}"/> 
					<input type="password" name="repassword" placeholder="Xác nhận mật khẩu*" value="${not empty sessionScope.repassword1?sessionScope.repassword1:''}" />
					
					<div class="g-recaptcha" data-sitekey="6Lem47MdAAAAAFzttiIebnNIbKCKxJJqQBSiAKbG" style="margin: 0 auto;  width: fit-content;"></div>
      				<br/>	
      				
					<div class="text-center">
						<button type="submit" class="button" style="border: none;">Đăng ký</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<script src='https://code.jquery.com/jquery-2.2.4.min.js'></script>   
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> 
	<script type="text/javascript">
		function validate() {
			if( document.formRegistry.maxacnhan.value == "" ) {
				tata.error('Thất bại', "Mã xác nhận không được để trống.");
	            document.formRegistry.maxacnhan.focus() ;
	            return false;
	         }
			if( document.formRegistry.email.value == "" ) {
				tata.error('Thất bại', "Email không được để trống.");
	            document.formRegistry.email.focus() ;
	            return false;
	         }
			if( document.formRegistry.password.value == "" ) {
				tata.error('Thất bại', "Mật khẩu không được để trống.");
	            document.formRegistry.password.focus() ;
	            return false;
	         }
			if( document.formRegistry.repassword.value == "" ) {
				tata.error('Thất bại', "Vui lòng xác thực mật khẩu.");
	            document.formRegistry.repassword.focus() ;
	            return false;
	         }
		}
		function sendMa() {  
			var email = document.getElementById("mailregistry").value;			
					
	        $.ajax({
	            url: "/Forum/mailregistry",
	            type: "post", 
	            data: {
	            	email : email
	            },	           
	            success: function (data) { 	            	
	           		if(data[0] == "success") {
	           			tata.success('Thành công', data[1]);
	           		}   
	           		else {
	           			tata.error('Thất bại', data[1]);
	           		}
	            },
	            error: function (xhr) {
	            }
	        });
	    }	
	</script>
</body>
</html>
