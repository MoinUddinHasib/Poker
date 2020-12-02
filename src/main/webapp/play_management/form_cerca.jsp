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
<style>
.ui-autocomplete-loading {
	background: white url("assets/img/anim_16x16.gif") right center
		no-repeat;
}
</style>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/jqueryUI/jquery-ui.min.css" />
<title>Cerca Partita</title>

</head>

<body>

	<jsp:include page="../navbar.jsp" />
	<main role="main" class="container">


		<div class='card'>
			<div class='card-header'>
				<h5>Cerca Partita</h5>
			</div>
			<div class='card-body'>

				<form method="post"
					action="${pageContext.request.contextPath}/CercaPartitaServlet">
					<div
						class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}"
						role="alert">
						${errorMessage}
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="form-row">

						<div class="form-group col-md-6">
							<label>Denominazione </label> <input type="text"
								name="denominazione" id="denominazione" class="form-control">

						</div>

						<div class="form-group col-md-6">
							<label>Data </label> <input type="date" name="data" id="data"
								class="form-control">

						</div>

						<div class="form-group col-md-6">
							<label>Puntata minima </label> <input type="number"
								name="puntata" id="puntata" class="form-control">

						</div>

						<div class="form-group col-md-6">
							<label>Creatore </label>
							<div class="col-sm-4">
								<input class="form-control" type="text" id="creatoreDesc"
									name="creatoreDesc"> <input type="hidden"
									name="creatoreId" id="creatoreId">
							</div>
						</div>

						<div class="form-group col-md-6">
							<label>Partecipante </label>
							<div class="col-sm-4">
								<input class="form-control" type="text" id="partecipanteDesc"
									name="partecipanteDesc"> <input type="hidden"
									name="partecipanteId" id="partecipanteId">
							</div>
						</div>

					</div>
					<a
						href="${pageContext.request.contextPath}/play_management/PlayManagement.jsp"
						class='btn btn-outline-secondary' style='width: 80px'> Back </a>
					<button type="submit" name="submit" value="submit" id="submit"
						class="btn btn-primary">Cerca</button>

					<%-- FUNZIONE JQUERY UI CON AJAX PER AUTOCOMPLETE --%>
					<script>
						$("#creatoreDesc").autocomplete({
							source : function(request, response) {
								$.ajax({
									url : "SearchUserAjaxServlet",
									datatype : "json",
									data : {
										term : request.term,
									},
									success : function(data) {
										response($.map(data, function(item) {
											return {
												label : item.label,
												value : item.value
											}
										}))
									}
								})
							},
							//quando seleziono la voce nel campo deve valorizzarsi la descrizione
							focus : function(event, ui) {
								$("#creatoreDesc").val(ui.item.label)
								return false
							},
							minLength : 1,
							//quando seleziono la voce nel campo hidden deve valorizzarsi l'id
							select : function(event, ui) {
								$('#creatoreId').val(ui.item.value);
								console.log($('#creatoreId').val())
								return false;
							},
						});
					<%-- FUNZIONE JQUERY UI CON AJAX PER AUTOCOMPLETE --%>
						$("#partecipanteDesc").autocomplete({
							source : function(request, response) {
								$.ajax({
									url : "SearchUserAjaxServlet",
									datatype : "json",
									data : {
										term : request.term,
									},
									success : function(data) {
										response($.map(data, function(item) {
											return {
												label : item.label,
												value : item.value
											}
										}))
									}
								})
							},
							//quando seleziono la voce nel campo deve valorizzarsi la descrizione
							focus : function(event, ui) {
								$("#partecipanteDesc").val(ui.item.label)
								return false
							},
							minLength : 1,
							//quando seleziono la voce nel campo hidden deve valorizzarsi l'id
							select : function(event, ui) {
								$('#partecipanteId').val(ui.item.value);
								console.log($('#partecipanteId').val())
								return false;
							},
						});
					</script>
				</form>

				<!-- end card-body -->
			</div>
		</div>

		<!-- end container -->
	</main>
	<jsp:include page="../footer.jsp" />

</body>
</html>