<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Trò chuyện</title>
  <link rel="stylesheet" href="css/styleib.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"/>
</head>

<body>
  <div class="wrapper">
    <section class="users">
      <header>
      	<a href="home" class="back-icon"><i class="fas fa-arrow-left"></i></a>
        <div class="content">
         
          <img src="images/${auth.getImage()=='' || auth.getImage()==null ?'User-Linear-80px.png' : auth.getImage()}" alt="Ảnh đại diện">
          <div class="details">
            <c:choose>
            	<c:when test="${auth.getFirstName() != '' && auth.getFirstName() != null  
            			|| auth.getMiddleName() != '' && auth.getMiddleName() != null
            			|| auth.getLastName() != '' && auth.getLastName() != null
            			}">
            		<span>${auth.getFirstName() } ${auth.getMiddleName()} ${auth.getLastName()}</span>
            	</c:when>
            	<c:otherwise>
            		<span>${auth.getEmail() }</span>
            	</c:otherwise>
            </c:choose>            		
            	
            <c:choose>
            	<c:when test="${auth.getStatus() == 'Online' }">
            		<p><i class="fas fa-circle status-dot-me"></i> ${auth.getStatus() }</p>
            	</c:when>
            	<c:otherwise>
            		<p><i class="fas fa-circle status-dot-me-offline"></i> ${auth.getStatus() }</p>
            	</c:otherwise>
            </c:choose>	    
			
			
          </div>
        </div>
        <a href="logout">
        	<button type="button" class="logout">Đăng xuất</button>
		</a>        
      </header>
      <div class="search">
        <span class="text">Chọn người mà bạn muốn trò chuyện</span>
        <input type="text" placeholder="Nhập tên để tìm kiếm...">
        <button><i class="fas fa-search"></i></button>
      </div>
      <div class="users-list">
  
      </div>
    </section>
  </div>

  <script src="js/users.js"></script>

</body>
</html>