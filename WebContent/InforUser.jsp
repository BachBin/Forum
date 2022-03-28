<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>	
	<title>Thông tin tài khoản</title>
	<meta charset="utf-8">
	<meta name="title" content="Ask online Form">	
	<link href="https://fonts.googleapis.com/css?family=Roboto:300,400" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
	<link rel="stylesheet" href="css/loginstyle.css">
	<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css"> </head>
	<script src="js/tata.js"></script>
    <style type="text/css">
    	.avatar {
		  vertical-align: middle;
		  width: 100px;
		  height: 100px;
		  border-radius: 50%;
		  justify-content: center;
		  align-items: center;
		  align-content: center;
		}
		input[type="file"] {
		    display: none;
		}
		.custom-file-upload {
		    border: 1px solid #ccc;
		    display: inline-block;
		    padding: 6px 12px;
		    cursor: pointer;
		}
    </style>
</head>

<body>
<c:if test="${not empty sessionScope.success }">
	<script type="text/javascript">
		var success = "${sessionScope.success}";			
		tata.success('Thành công', success);
	</script>
	<c:remove var="success"/>
</c:if>
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
    	<a class="btn btn-primary btn-primary3838" style="text-decoration: none; margin-bottom: 14px;" href="home">Trở về</a>     
      <div class="title">Thông tin tài khoản</div>     
      <form action="updateuser" method="post" enctype= "multipart/form-data">
      	<div class="text-center">
      		<c:choose>
      			<c:when test="${not empty auth.getImage()}">
      				<img id="avarView" alt="Avartar" src="images/${auth.getImage() }" class="avatar">
      			</c:when>
      			<c:otherwise>
      				<img id="avarView" alt="Avartar" src="images/User-Linear-80px.png" class="avatar">
      			</c:otherwise>
      		</c:choose>
      		
      		<div style="margin-top: 10px; margin-bottom: 10px;">
      			<label class="custom-file-upload button">
				    <input type="file" accept="image/*" name="image" id="file" onchange="loadFile(event)"/>
				    <i class="fa fa-cloud-upload"></i> Sửa
				</label>  
				<a href="delavartar?id=${auth.getId() }" class="custom-file-upload button" style="text-decoration: none;">
				    <i class="fa fa-trash"></i> Xóa
				</a>	   
				
      		</div>
      	</div>
      	<input type="hidden" name="id" value="${auth.getId() }"  />      	
      	<input type="text" name="firstname" placeholder="Họ" value="${auth.getFirstName() }" />
      	<input type="text" name="middlename" placeholder="Tên đệm" value="${auth.getMiddleName() }" />
      	<input type="text" name="lastname" placeholder="Tên" value="${auth.getLastName() }" />
      	<input type="number" name="mobile" placeholder="Số điện thoại" value="${auth.getMobile() }" />
        <input type="email" name="email" placeholder="Email" value="${auth.getEmail() }" />
        <input type="password" required="required" name="password" placeholder="Mật khẩu*"  />
        <input type="text" name="intro" placeholder="Mô tả ngắn" value="${auth.getIntro() }" />
        <textarea style="width: 95%; padding-left: 20px;" rows="5" name="profile" placeholder="Mô tả chi tiết">${auth.getProfile() }</textarea>        
        <div class="text-center">
          	<button type="submit" class="button" style="border: none;">Cập nhật</button>           
        </div>
      </form>
    </div>

 
  </div>
</div>
  <script src='https://code.jquery.com/jquery-2.2.4.min.js'></script>    
	<script>
		var loadFile = function(event) {
			var image = document.getElementById('avarView');
			image.src = URL.createObjectURL(event.target.files[0]);
		};
	</script>
</body>
</html>
