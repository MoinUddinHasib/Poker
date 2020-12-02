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

<title>Modifica Utente</title>

</head>

<body>

	<jsp:include page="../navbar.jsp" />
	<main role="main" class="container">

		<%-- alert con lista errori --%>
		<div class="alert alert-danger ${not empty userErrors?'':'d-none' }"
			role="alert">
			<c:forEach var="errorItem" items="${userErrors }">
				<ul>
					<li>${errorItem }</li>
				</ul>
			</c:forEach>
		</div>

		<div class='card'>
			<div class='card-header'>
				<h5>Modifica Utente</h5>
			</div>
			<div class='card-body'>

				<form method="post"
					action="${pageContext.request.contextPath}/ModificaUserServlet">
					<div class="form-row">
						<input type="hidden" value="${requestScope.id }" name="id" id="id">
						<div class="form-group col-md-6">
							<label>Nome </label> <input type="text" name="nome" id="nome"
								class="form-control" value="${user.nome }" required>

						</div>

						<div class="form-group col-md-6">
							<label>Cognome </label> <input type="text" name="cognome"
								id="cognome" class="form-control" value="${user.cognome }" required>

						</div>

						<div class="form-group col-md-6">
							<label>Username </label> <input type="text" name="username"
								id="username" class="form-control" value="${user.username }" required>
						</div>
						<c:if test="${requestScope.stato eq 'CREATO' }">

							<div class="form-group col-md-3">

								<fieldset>
									<legend>Ruoli</legend>
									<br> <input type="checkbox" name="admin" id="admin"
										<c:if test = "${requestScope.cond_admin == true}">
         								checked
      								</c:if> />
									ADMIN_ROLE <br /> <input type="checkbox" name="special"
										id="special"
										<c:if test = "${requestScope.cond_special == true}">
         								checked
      								</c:if> />
									SPECIAL_PLAYER_ROLE <br /> <input type="checkbox"
										name="player" id="player"
										<c:if test = "${requestScope.cond_player == true}">
         								checked
      								</c:if> />
									PLAYER_ROLE


								</fieldset>
							</div>

						</c:if>
					</div>
					<a href="${pageContext.request.contextPath}/CercaUserServlet"
						class='btn btn-outline-secondary' style='width: 80px'> Back </a>

					<button type="submit" name="submit" value="submit" id="submit"
						class="btn btn-primary">Modifica</button>

				</form>

				<!-- end card-body -->
			</div>
		</div>

		<!-- end container -->
	</main>
	<jsp:include page="../footer.jsp" />

</body>
</html>