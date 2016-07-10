<%-- 
    Document   : login
    Created on : 10/07/2016, 16:17:11
    Author     : kieckegard
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>SisBiblioteca</title>
	<link rel="stylesheet" type="text/css" href="css/estilo.css">
	<link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">
        <!-- BOOTSTRAP-->

	<link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.min.css">
	<script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>

	<!-- END OF BOOTSTRAP -->
</head>
<body class="login-body">
	<div class="cad-container login-container">
		<div class="cad-header">
			<h2>Login</h2>
		</div>
		<div class="cad-content">
			<form method="POST" action="Login" class="login-form">
				<div class="group-input login-inputs fix-width">
					<input type="text" name="matricula" placeholder="Matrícula" required>
					<i class="fa fa-user" aria-hidden="true"></i>
				</div>
				<div class="group-input login-inputs">
					<input type="password" name="senha" placeholder="Senha" required>
					<i class="fa fa-key" aria-hidden="true"></i>
				</div>
				<button class="submit-button">Login</button>
			</form>
                    
                    <c:if test="${success == false}">
                        <div id="errorMsg" class="alert alert-danger" style="margin: 10px 10px 5px 10px;">
                            <a class="close" data-dismiss="alert" aria-label="close">&times;</a>
                            <strong>Error!</strong> ${requestScope.errorMsg}
                        </div>
                    </c:if>
		</div>
		
	</div>
</body>
</html>