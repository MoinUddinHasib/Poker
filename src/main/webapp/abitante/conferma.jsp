<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inserisci Municipio</title>
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/favicon.ico"
	type="image/x-icon" />
</head>
<body>

	<div class="container">

		<%@ include file="../header.jsp"%>

		<div class="page-header">
			<h3>Conferma Eliminazione Abitante</h3>
		</div>

		<a
			href="${pageContext.request.contextPath}/ExecuteCancellaAbitanteServlet?idAbitante=<%=request.getParameter("idAbitante")%>"
			class="btn btn-info">Elimina</a>

	</div>
	<!-- /.container -->



</body>
</html>