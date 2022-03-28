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
    <section class="chat-area">
      <header>        
        <a href="messaging" class="back-icon"><i class="fas fa-arrow-left"></i></a>        
        <img src="images/${ibto.getImage()=='' || ibto.getImage()==null ?'User-Linear-80px.png' : ibto.getImage()}" alt="Ảnh đại diện">
        <div class="details">
          <c:choose>
            	<c:when test="${ibto.getFirstName() != '' && ibto.getFirstName() != null  
            			|| ibto.getMiddleName() != '' && ibto.getMiddleName() != null
            			|| ibto.getLastName() != '' && ibto.getLastName() != null
            			}">
            		<span>${ibto.getFirstName() } ${ibto.getMiddleName()} ${ibto.getLastName()}</span>
            	</c:when>
            	<c:otherwise>
            		<span>${ibto.getEmail() }</span>
            	</c:otherwise>
            </c:choose>
            
            <c:choose>
            	<c:when test="${ibto.getStatus() == 'Online' }">
            		<p><i class="fas fa-circle status-dot-ib"></i> ${ibto.getStatus() }</p>
            	</c:when>
            	<c:otherwise>
            		<p><i class="fas fa-circle status-dot-ib-offline"></i> ${ibto.getStatus() }</p>
            	</c:otherwise>
            </c:choose>	               
          
        </div>
      </header>
      <div class="chat-box">

      </div>
      <form action="#" class="typing-area">      	
        <input type="text" class="incoming_id" name="incoming_id" value="${ibto.getUniqueId() }" hidden>
        <input type="text" class="input-field" name="message" placeholder="Nhập tin nhắn..." autocomplete="off">
        <button><i class="fab fa-telegram-plane"></i></button>
      </form>
    </section>
  </div>

  <script src="js/chat.js"></script>

</body>
</html>