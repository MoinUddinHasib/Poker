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

<title>Cerca Utente</title>

</head>

<body>

	<jsp:include page="../navbar.jsp" />
	<main role="main" class="container">


		<div class='card'>
			<div class='card-header'>
				<h5>Cerca Utente</h5>
			</div>
			<div class='card-body'>

				<div class="alert alert-danger ${not empty userErrors?'':'d-none' }"
					role="alert">
					<c:forEach var="errorItem" items="${userErrors }">
						<ul>
							<li>${errorItem }</li>
						</ul>
					</c:forEach>
				</div>

				<form method="post"
					action="${pageContext.request.contextPath}/CercaUserServlet">
					<%-- alert conferma --%>
					<div
						class="alert alert-success ${messaggioConferma!=null?'':'d-none' }"
						role="alert">${messaggioConferma }</div>

					<div
						class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}"
						role="alert">
						${errorMessage}
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="form-row">

						<div class="form-group col-md-6">
							<label>Nome </label> <input type="text" name="nome" id="nome"
								class="form-control" value="${userCampi.nome }">

						</div>

						<div class="form-group col-md-6">
							<label>Cognome </label> <input type="text" name="cognome"
								id="cognome" class="form-control" value="${userCampi.cognome }">

						</div>

						<div class="form-group col-md-6">
							<label>Username </label> <input type="text" name="username"
								id="username" class="form-control" value="${userCampi.username }">

						</div>

						<div class="form-group col-md-6">
							<label>Data Registrazione</label> <input type="date" name="data"
								id="data" class="form-control" value="${userCampi.data }">

						</div>

					</div>
					<div class="form-row">
						<div class="form-group col-md-3">
							<label>Stato </label> <select name="stato" class="form-control">
								<c:forEach items="${requestScope.stati}" var="stato">
									<option value="${stato}">${stato}</option>
								</c:forEach>
								<option value="" selected>Qualsiasi Stato</option>
							</select>

						</div>

					</div>
					<div class="form-row">
						<div class="form-group col-md-3">
							<label>Ruolo </label> <select name="ruolo" class="form-control" >
								<c:forEach items="${requestScope.ruoli}" var="ruolo">
									<option value="${ruolo.id }">${ruolo.tipo }</option>
								</c:forEach>
								<option value="" selected>Qualsiasi Ruolo</option>
							</select>

						</div>

					</div>
					<a href="${pageContext.request.contextPath}/home.jsp"
						class='btn btn-outline-secondary' style='width: 80px'> Back </a>
					<button type="submit" name="submit" value="submit" id="submit"
						class="btn btn-primary">Cerca</button>

				</form>

				<!-- end card-body -->
			</div>
		</div>

		<!-- end container -->
	</main>
	<jsp:include page="../footer.jsp" />

</body>
</html>