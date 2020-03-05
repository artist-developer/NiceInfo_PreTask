<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<form class="form-inline" id="form" name="form">
	<div style="align:center;">
		<div class="form-group">
			<label for="id">아이디</label> <input type="text"
				class="form-control" id="id" name="id">
		</div>
		<div class="form-group">
			<label for="password">비밀번호</label> <input type="password"
				class="form-control" id="password" name="password">
		</div>
		<button type="button" id="loginbtn" onclick="ajax()" class="btn btn-default">로그인</button>
	</div>
	</form>

</body>
</html>
