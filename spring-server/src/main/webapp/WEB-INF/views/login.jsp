<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<!-- �������� �ּ�ȭ�� �ֽ� CSS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- �ΰ����� �׸� -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- �������� �ּ�ȭ�� �ֽ� �ڹٽ�ũ��Ʈ -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<script>
    function ajax(){
    	var form=document.form;
        var param = {
        		"id":form.id.value,
        		"password":form.password.value
        }
        console.log(form);
        console.log(param);
        $.ajax({
            type : 'post',
            url : '/login',
            data : JSON.stringify(param),
            dataType : 'json',
            contentType : "application/json",
            error: function(xhr, status, error){
            	alert(xhr);
            	alert(status);
                alert(error);
            },
            success : function(json){
                alert(json)
            },
        });
    }
</script>
<body>
	<form class="form-inline" id="form" name="form">
	<div style="align:center;">
		<div class="form-group">
			<label for="id">���̵�</label> <input type="text"
				class="form-control" id="id" name="id">
		</div>
		<div class="form-group">
			<label for="password">��й�ȣ</label> <input type="password"
				class="form-control" id="password" name="password">
		</div>
		<button type="button" id="loginbtn" onclick="ajax()" class="btn btn-default">�α���</button>
	</div>
	</form>
</body>
</html>