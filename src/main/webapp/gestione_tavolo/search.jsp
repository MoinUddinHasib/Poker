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

<title>Cerca Tavolo</title>

</head>

<body>

	<jsp:include page="../navbar.jsp" />
	<main role="main" class="container">


		<div class='card'>
			<div class='card-header'>
				<h5>Cerca Tavolo</h5>
			</div>
			<div class='card-body'>
				<%-- alert con lista errori --%>
				<div
					class="alert alert-danger ${not empty tavoloErrors?'':'d-none' }"
					role="alert">
					<c:forEach var="errorItem" items="${tavoloErrors }">
						<ul>
							<li>${errorItem }</li>
						</ul>
					</c:forEach>
				</div>

				<form method="post"
					action="${pageContext.request.contextPath}/CercaTavoloServlet">
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
							<label>Denominazione </label> <input type="text"
								name="denominazione" id="denominazione" class="form-control" value="${tavoloCampi.denominazione }">

						</div>

						<div class="form-group col-md-6">
							<label>Data </label> <input type="date" name="data" id="data"
								class="form-control" value="${tavoloCampi.data }">

						</div>

						<div class="form-group col-md-6">
							<label>Puntata minima </label> <input type="number"
								name="puntata" id="puntata" class="form-control" value="${ tavoloCampi.cifraMin}">

						</div>

					</div>
					<a
						href="${pageContext.request.contextPath}/gestione_tavolo/GestioneTavolo.jsp"
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