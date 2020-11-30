<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />

<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet" />
<title>Lista Tavoli</title>
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
		<div class="alert alert-success alert-dismissible fade show ${successMessage==null?'d-none': ''}" role="alert">
		  ${successMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		<div
			class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}"
			role="alert">
			${errorMessage}
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Lista Tavoli</h5> 
		    </div>

		    <div class='card-body'>
		    <a href="${pageContext.request.contextPath}/CercaTavoloServlet" class='btn btn-outline-secondary' style='width:80px'>
		          Back
		        </a>

		        <div class='table-responsive'>
		            <table class='table table-striped ' >
		                <thead>
		                    <tr>
		                        <th>Id</th>
		                        <th>Cifra Minima</th>
		                        <th>Denominazione</th>
		                        <th>Data Creazione</th>
		                        <th>Azioni</th>
		                    </tr>
		                </thead>
		                <tbody>
		                	
		                	<c:forEach items="${requestScope.listaTavoli}" var= 't'>							
		                    <tr>
		                        <td>${t.getId()}</td>
		                        <td>${t.getCifraMin()}</td>
		                        <td>${t.getDenominazione()}</td>
		                        <td>${t.getDataCreazione()}</td>
		                        
														<td><a
							href="DettaglioTavoloServlet?id=${t.getId() }"
							class="btn btn-info">Dettaglio</a> <a
							href="ModificaTavoloServlet?id=${t.getId() }"
							class="btn btn-info">Modifica</a> <a
							href="${pageContext.request.contextPath}/gestione_tavolo/conferma.jsp?id=${t.getId() }"
							class="btn btn-info">Cancella</a></td>
		                    </tr>
		                    </c:forEach>
		                </tbody>
		            </table>
		        </div>
		   
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	
	
	
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>