<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="it">
<head>
<jsp:include page="./header.jsp" />

<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
<!-- style per le pagine diverse dalla index -->
<link href="./assets/css/global.css" rel="stylesheet" />
<title>Registrazione</title>
</head>

<body>
	
	<main role="main" class="container">

 		<%-- alert con lista errori --%>
		<div class="alert alert-danger ${not empty userErrors?'':'d-none' }" role="alert">
			<c:forEach var = "errorItem" items="${userErrors }">
		       	<ul>
					<li> ${errorItem }</li>	
				</ul>
		     	</c:forEach>
		</div> 


		<div class='card'>
			<div class='card-header'>
				<h5>Registrazione</h5>
			</div>
			<div class='card-body'>

				<form method="post" action="RegistrazioneServlet">

					<div class="form-row">

						 <div class="form-group col-md-6">
							<label>Nome </label> <input type="text" name="nome"
								id="nome" class="form-control" value="${userCampi.nome }">

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
							<label>Password </label> <input type="text" name="password"
								id="password" class="form-control" value="${userCampi.password }">
						</div>
					</div>
					<a
						href="${pageContext.request.contextPath}/ServletLogin"
						class='btn btn-outline-secondary' style='width: 80px'> Back </a>
				<button type="submit" name="submit" value="submit" id="submit"
						class="btn btn-primary">Registrati</button>
				</form>

				<!-- end card-body -->
			</div>
		</div>

		<!-- end container -->
	</main>
	<jsp:include page="./footer.jsp" />

</body>
</html>