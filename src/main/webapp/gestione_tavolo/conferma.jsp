<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />

<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/favicon.ico"
	type="image/x-icon" />
<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css"
	rel="stylesheet" />

<title>Cancella Tavolo</title>
</head>
<body>



	<jsp:include page="../navbar.jsp" />

	<main role="main" class="container">
		<div class='card'>
			<div class="page-header">
				<h3>Conferma Eliminazione Tavolo</h3>
			</div>
			<div class='card-body'>
				<a href="${pageContext.request.contextPath}/CercaTavoloServlet"
					class='btn btn-outline-secondary' style='width: 80px'> Back </a> <a
					href="${pageContext.request.contextPath}/CancellaTavoloServlet?id=<%=request.getParameter("id")%>"
					class="btn btn-info">Elimina</a>

			</div>
		</div>
	</main>


	<jsp:include page="../footer.jsp" />
</body>
</html>