<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>演習３</title>
</head>
<body>
	<h2>演習03</h2>
	<h5>MySQLでDBの登録、更新、削除</h5>
	
	<h3>CRUD List</h3>

	ユーザーの作成（Create）
	<form action="SelectEmployees" method="post">
		<input type="text" name="loginid" placeholder="ログインID"><br>
		<input type="text" name="name" placeholder="名前"><br> <input
			type="text" name="age" placeholder="年齢"><br> <input
			type="submit" name="create" value="ユーザーの作成（Create）">
	</form>
	<br> ユーザーの読み出し（Read）
	<form action="SelectEmployees" method="post">
		<input type="text" name="primaryid" placeholder="ﾌﾟﾗｲﾏﾘID"><br>
		<input type="submit" name="read" value="ユーザーの読み出し（Read）">
	</form>
	<br> ユーザーの更新（Update）
	<form action="SelectEmployees" method="post">
		<input type="text" name="primaryid" placeholder="ﾌﾟﾗｲﾏﾘID"><br>
		<!-- <input type="submit" name="update" value="ユーザーの更新（Update）"> -->
		<input type="submit" name="updatePage" value="ユーザーの更新（Update）">
	</form>
	<br> ユーザーの削除（Delete）
	<form action="SelectEmployees" method="post">
		<input type="text" name="primaryid" placeholder="ﾌﾟﾗｲﾏﾘID"><br>
		<!-- <input type="submit" name="delete" value="ユーザーの削除（Delete）"> -->
		<input type="submit" name="deletePage" value="ユーザーの削除（Delete）">
	</form>
	<br>
	<a href="Index?page=index"">index画面へ</a>
	<br>
	<a href="Crud?page=crud">CRUD入力画面へ</a>
	<br>
	<!-- <a href="ListAll">一覧画面へ</a> -->
	<a href="ListAll?page=listAll">ユーザー一覧画面へ</a>

</body>

</html>