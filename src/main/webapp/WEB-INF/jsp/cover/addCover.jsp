<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cover for ${book.title}</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script> 
<style>
body {font-family: Arial, Helvetica, sans-serif;}
form {border: 3px solid #f1f1f1;}

input[type=text], input[type=password] {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
}

.registerbtn {
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 100%;
}

button:hover {
    opacity: 0.8;
}

.cancelbtn {
    padding: 10px 18px;
    background-color: #f44336;
}

.imgcontainer {
    text-align: center;
    margin: 24px 0 12px 0;
}

img.avatar {
    width: 40%;
    border-radius: 50%;
}

.container {
    padding: 16px;
}

span.psw {
    float: right;
    padding-top: 16px;
}

@media screen and (max-width: 300px) {
    span.psw {
       display: block;
       float: none;
    }
    .cancelbtn {
       width: 100%;
    }
}
</style>

</head>
<body>
	<div class="container-fluid">
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
		<div>
			<%@ include file="../header.jsp"%>
		</div>
		<c:if test="${message !=  null}">
			<div>	
				${message}
			</div>
		</c:if>
		<div class="container">
			<div class="row">
			    <div class="col">
			    </div>
			    <div class="col-6">
			      	<h3 class="text-muted">Upload cover for book ${book.title}</h3	>
			    </div>
			    <div class="col">
			    </div>
			</div>
		</div>
		<div class="container">
			 <div class="row">
			 <div class="col-lg">
			 	</div>
			 	<div class="col-lg">
					<form method="post" action="http://localhost:8090/cover/add/${book.id}" enctype="multipart/form-data">
						<div class="custom-file">
							<input type="file" class="custom-file-input" name="fileUpload" size="50">
							<label class="custom-file-label" for="customFile">Choose file</label>
						</div>
						<input type="submit" value="Upload">
					</form>
				</div>
				<div class="col-lg">
				</div>
			</div>
		</div>
	</div>
</body>
</html>