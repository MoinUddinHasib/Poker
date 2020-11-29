<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />

<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet" />
<title>Compra Credito</title>
</head>

<body>
	
	<jsp:include page="../navbar.jsp" />
	<main role="main" class="container">

 		<%-- alert con lista errori --%>
		<div class="alert alert-danger ${not empty userErrors?'':'d-none' }" role="alert">
			<c:forEach var = "errorItem" items="${userErrors }">
		       	<ul>
					<li> ${errorItem }</li>	
				</ul>
		     	</c:forEach>
		</div> 
		
					<%-- alert conferma --%>
		<div class="alert alert-success ${messaggioConferma!=null?'':'d-none' }" role="alert">
			${messaggioConferma }
		</div>


		<div class='card'>
			<div class='card-header'>
				<h5>Compra Credito</h5>
			</div>
			<div class='card-body'>

				<form method="post" action="${pageContext.request.contextPath}/CompraCreditoServlet">
					<div class="form-row">

						 <div class="form-group col-md-6">
							<label>Importo </label> <input type="number" name="importo"
								id="importo" class="form-control" >

						</div>

					</div>
					<a href="${pageContext.request.contextPath}/play_management/PlayManagement.jsp" class='btn btn-outline-secondary' style='width:80px'>
		          Back
		        </a>
				<button type="submit" name="submit" value="submit" id="submit"
						class="btn btn-primary">Conferma</button>
				</form>

				<!-- end card-body -->
			</div>
		</div>

		<!-- end container -->
	</main>
	<jsp:include page="../footer.jsp" />

</body>
</html>