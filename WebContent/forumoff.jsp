 <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Dừng hoạt động</title>
</head>
<body>
	<c:if test="${forumOff == 0 }">
		<h1 align="center"> Diễn đàn đang dừng hoạt động</h1>
	</c:if>
	<c:if test="${postOff == 0 }">
		<h1 align="center"> Chức năng đăng bài dừng hoạt động</h1>
	</c:if>
	<c:if test="${loginOff == 0 }">
		<h1 align="center"> Chức năng đăng nhập dừng hoạt động</h1>
	</c:if>
	<c:if test="${registryOff == 0 }">
		<h1 align="center"> Chức năng đăng ký dừng hoạt động</h1>
	</c:if>
	<c:if test="${chatOff == 0 }">
		<h1 align="center"> Chức năng trò chuyện dừng hoạt động</h1>
	</c:if>	
</body>
</html>