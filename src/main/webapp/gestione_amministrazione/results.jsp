<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />

<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet" />
<title>Lista Utenti</title>
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
		        <h5>Lista Utenti</h5> 
		    </div>

		    <div class='card-body'>
		    <a href="${pageContext.request.contextPath}/CercaUserServlet" class='btn btn-outline-secondary' style='width:80px'>
		          Back
		        </a>

		        <div class='table-responsive'>
		            <table class='table table-striped ' >
		                <thead>
		                    <tr>
		                        <th>Id</th>
		                        <th>Nome</th>
		                        <th>Cognome</th>
		                        <th>Username</th>
		                        <th>Data Registrazione</th>
		                        <th>Stato</th>
		                        <th>Azione</th>
		                    </tr>
		                </thead>
		                <tbody>
		                	
		                	<c:forEach items="${requestScope.listaUsers}" var= 'u'>							
		                    <tr>
		                        <td>${u.id}</td>
		                        <td>${u.nome}</td>
		                        <td>${u.cognome}</td>
		                        <td>${u.username}</td>
		                        <td>${u.dataRegistrazione}</td>
		                        <td>${u.stato}</td>

		                        <td><a
							href="DettaglioUserServlet?id=${u.id }"
							class="btn btn-info">Dettaglio</a> <a
							href="ModificaUserServlet?id=${u.id }"
							class="btn btn-info">Modifica</a> 
							
							<c:if test = "${(u.stato == 'INATTIVO') || (u.stato == 'CREATO')}">
							<a	href="AttivaUserServlet?id=${u.id }"
							class="btn btn-info">Attiva</a>
							</c:if><c:if test = "${u.stato == 'ATTIVO'}">
							<a	href="DisattivaUserServlet?id=${u.id }"
							class="btn btn-info">Disattiva</a>
							</c:if>
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