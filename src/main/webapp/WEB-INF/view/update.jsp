<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Employee"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>演習３</title>
</head>
<body>
	<h2>演習03</h2>
	<h5>MySQLでDBの登録、更新、削除</h5>

	<h3>update List</h3>
		<% Employee emp = (Employee) request.getAttribute("employee"); %>
	<%-- <p>
		<%=emp%><br>
		<%=emp.getPrimaryid()%><br>						
		<%=emp.getLoginid()%><br>
		<%=emp.getName()%><br>
		<%=emp.getAge()%>
	</p> --%>

	<form action="SelectEmployees" method="post">
		<%-- <input type="text" name="id" value="${emp.getId()}" readonly><br> --%>
		<input type="text" name="primaryid" value="<%=emp.getPrimaryid()%>" readonly><br>
		<input type="text" name="loginid" value="<%=emp.getLoginid()%>"><br>
		<input type="text" name="name" value="<%=emp.getName()%>"><br>
		<input type="text" name="age" value="<%=emp.getAge()%>"><br>

		<input type="submit" name="update" value="ユーザーを更新する（Update）">
	</form><br>

	<a href="Index?page=index"">index画面へ</a><br>
	<a href="Crud?page=crud">CRUD入力画面へ</a><br>
	<!-- <a href="Crud?page=result">結果画面へ</a> -->
	<a href="ListAll?page=listAll">ユーザー一覧画面へ</a>

</body>
</html>