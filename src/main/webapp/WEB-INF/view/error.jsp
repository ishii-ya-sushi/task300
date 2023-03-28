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

	<h3>error List</h3>
	
	error:("primaryIDが存在しません");<br><br>

	<a href="Index?page=index"">index画面へ</a><br>
	<a href="Crud?page=crud">CRUD入力画面へ</a><br>
	<a href="ListAll?page=listAll">ユーザー一覧画面へ</a>

</body>
</html>