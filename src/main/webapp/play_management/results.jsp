<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />

<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet" />
<title>Cerca Partita</title>
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
		        <h5>Lista Partite</h5> 
		    </div>

		    <div class='card-body'>
		    <a href="${pageContext.request.contextPath}/CercaPartitaServlet" class='btn btn-outline-secondary' style='width:80px'>
		          Back
		        </a>

		        <div class='table-responsive'>
		            <table class='table table-striped ' >
		                <thead>
		                    <tr>
		                        <th>Id</th>
		                        <th>Esperienza Minima</th>
		                        <th>Cifra Minima</th>
		                        <th>Denominazione</th>
		                        <th>Data Creazione</th>
		                        <th>Creatore</th>
		                        <th>Azione</th>
		                    </tr>
		                </thead>
		                <tbody>
		                	
		                	<c:forEach items="${requestScope.listaTavoli}" var= 't'>							
		                    <tr>
		                        <td>${t.getId()}</td>
		                        <td>${t.getEsperienzaMin()}</td>
		                        <td>${t.getCifraMin()}</td>
		                        <td>${t.getDenominazione()}</td>
		                        <td>${t.getDataCreazione()}</td>
		                        <td>${t.getUser_creatore().getUsername()}</td>

		                        <td>
									<a class="btn btn-primary" href="GiocaPartitaServlet?id=${t.getId()}">Gioca</a>
								</td>
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