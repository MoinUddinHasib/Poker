<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />

<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet" />
<title>Dettaglio Tavolo</title>
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Dettaglio Tavolo</h5> 
		    </div>

		    <div class='card-body'>
		    <a href="${pageContext.request.contextPath}/CercaTavoloServlet" class='btn btn-outline-secondary' style='width:80px'>
		          Back
		        </a>		    
		
		    <div class='card-body'>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">ID:</dt>
				  <dd class="col-sm-9">${tavolo.id}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Esperienza minima:</dt>
				  <dd class="col-sm-9">${tavolo.esperienzaMin}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Puntata minima:</dt>
				  <dd class="col-sm-9">${tavolo.cifraMin}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Denominazione:</dt>
				  <dd class="col-sm-9">${tavolo.denominazione}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Data creazione:</dt>
				  <dd class="col-sm-9">${tavolo.dataCreazione}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Creatore:</dt>
				   <dd class="col-sm-9">${tavolo.user_creatore.username}</dd> 
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