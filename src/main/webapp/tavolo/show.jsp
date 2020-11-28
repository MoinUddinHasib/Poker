<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Risultati Ricerca</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
</head>
<body>

	<div class="container">

		<%@ include file="../header.jsp"%>

		<div class="page-header">
			<h3>Pagina dei Risultati</h3>
		</div>
		
		<%-- alert conferma --%>
		<div class="alert alert-success ${messaggioConferma!=null?'':'d-none' }" role="alert">
			${messaggioConferma }
		</div>

		<div class='card'>
		    <div class='card-header'>
		        Visualizza dettaglio
		    </div>
		    
		
		    <div class='card-body'>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">ID:</dt>
				  <dd class="col-sm-9">${municipio.id}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Descrizione:</dt>
				  <dd class="col-sm-9">${municipio.descrizione}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Codice:</dt>
				  <dd class="col-sm-9">${municipio.codice}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Ubicazione:</dt>
				  <dd class="col-sm-9">${municipio.ubicazione}</dd>
		    	</dl>
		    	
		    </div>
		    
		</div>
		<div class="form-group">        
	      <div class="col-sm-offset-2 col-sm-10">
	        <a href="PrepareInsertMunicipioServlet" class="btn btn-primary btn-md">Inserisci Nuovo Elemento</a>
	      </div>
	    </div>

	</div>
</body>
</html>