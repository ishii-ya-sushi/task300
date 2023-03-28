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

	<h3>listall List</h3>

	<p>
		<%=(Employee) request.getAttribute("employee")%><br>
		<%
		Employee emp = (Employee) request.getAttribute("employee");
		%>
		<%=emp.getId()%><br>
		<%=emp.getName()%><br>
		<%=emp.getAge()%>
	</p>


	<table>
		<tr>
			<th>ID</th>
		</tr>
		<%
		/* 		for (Employee emplist : (List<Employee>) request.getAttribute("listAll")) { */
		for (Employee emplist : (Employee) request.getAttribute("listAll")) {
		%>
		<tr>
			<td><%=emplist.getId()%></td>
		</tr>
		<%
		}
		%>
	</table>

	<a href="Index?page=index"">index画面へ</a><br>
	<a href="Crud?page=crud">CRUD入力画面へ</a><br>
	<!-- 	<a href="ListAll">一覧画面へ</a> -->
	<a href="ListAll?page=listAll">一覧画面へ</a>
</body>
</html>