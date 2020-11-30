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

<title>Inserisci Tavolo</title>

</head>

<body>

	<jsp:include page="../navbar.jsp" />
	<main role="main" class="container">

		<%-- alert con lista errori --%>
		<div class="alert alert-danger ${not empty tavoloErrors?'':'d-none' }" role="alert">
			<c:forEach var = "errorItem" items="${tavoloErrors }">
		       	<ul>
					<li> ${errorItem }</li>	
				</ul>
		     	</c:forEach>
		</div> 

		<div class='card'>
			<div class='card-header'>
				<h5>Inserisci Tavolo</h5>
			</div>
			<div class='card-body'>

				<form method="post"
					action="${pageContext.request.contextPath}/InserisciTavoloServlet">
					<div class="form-row">
					
					<div class="form-group col-md-6">
							<label>Esperienza minima </label> <input type="number"
								name="esperienza" id="esperienza" class="form-control" value="${tavoloCampi.esperienzaMin }">

						</div>

						<div class="form-group col-md-6">
							<label>Denominazione </label> <input type="text"
								name="denominazione" id="denominazione" class="form-control"
								value="${tavoloCampi.denominazione }">

						</div>

						<div class="form-group col-md-6">
							<label>Puntata minima </label> <input type="number"
								name="puntata" id="puntata" class="form-control"
								value="${tavoloCampi.cifraMin }">
						</div>

					</div>
					<a
						href="${pageContext.request.contextPath}/gestione_tavolo/GestioneTavolo.jsp"
						class='btn btn-outline-secondary' style='width: 80px'> Back </a>
					<button type="submit" name="submit" value="submit" id="submit"
						class="btn btn-primary">Inserisci</button>

				</form>

				<!-- end card-body -->
			</div>
		</div>

		<!-- end container -->
	</main>
	<jsp:include page="../footer.jsp" />

</body>
</html>