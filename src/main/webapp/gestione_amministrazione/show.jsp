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

		<div class='card'>
		    <div class='card-header'>
		        Visualizza dettaglio
		    </div>
		    
		
		    <div class='card-body'>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">ID:</dt>
				  <dd class="col-sm-9">${abitante.id}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Nome:</dt>
				  <dd class="col-sm-9">${abitante.nome}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Cognome:</dt>
				  <dd class="col-sm-9">${abitante.cognome}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Età:</dt>
				  <dd class="col-sm-9">${abitante.eta}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Residenza:</dt>
				  <dd class="col-sm-9">${abitante.residenza}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Descrizione municipio:</dt>
				   <dd class="col-sm-9">${abitante.municipio}</dd> 
		    	</dl>
		    	
		    </div>
		    
		</div>
		<div class="form-group">        
	      <div class="col-sm-offset-2 col-sm-10">
	        <a href="PrepareInsertAbitanteServlet" class="btn btn-primary btn-md">Inserisci Nuovo Elemento</a>
	      </div>
	    </div>

	</div>
</body>
</html>