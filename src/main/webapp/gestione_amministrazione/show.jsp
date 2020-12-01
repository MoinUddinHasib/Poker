<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />

<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet" />
<title>Dettaglio User</title>
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Dettaglio User</h5> 
		    </div>

		    <div class='card-body'>
		    <a href="${pageContext.request.contextPath}/CercaUserServlet" class='btn btn-outline-secondary' style='width:80px'>
		          Back
		        </a>		    
		
		    <div class='card-body'>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">ID:</dt>
				  <dd class="col-sm-9">${user.id}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Nome:</dt>
				  <dd class="col-sm-9">${user.nome}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Cognome:</dt>
				  <dd class="col-sm-9">${user.cognome}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Username:</dt>
				  <dd class="col-sm-9">${user.username}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Data registrazione:</dt>
				  <dd class="col-sm-9">${user.dataRegistrazione}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Stato:</dt>
				   <dd class="col-sm-9">${user.stato}</dd> 
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Esperienza accumulata:</dt>
				   <dd class="col-sm-9">${user.esperienzaAccumulata}</dd> 
		    	</dl>
		    	
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Credito accumulato:</dt>
				   <dd class="col-sm-9">${user.creditoAccumulato}</dd> 
		    	</dl>

					<dl class="row">
						<dt class="col-sm-3 text-right">Ruoli:</dt>
						<ul>
							<c:forEach items="${user.ruoli}" var="r">
								<li>
									<dd>${r.tipo}</dd>
								</li>
							</c:forEach>
						</ul>
					</dl>

				</div>
		    
		   
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>