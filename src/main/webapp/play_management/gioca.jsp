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
<title>Gioca Partita</title>
</head>

<body>

	<jsp:include page="../navbar.jsp" />
	<main role="main" class="container">


		<div class='card'>
			<div class='card-header'>
				<h5>You're playing now</h5>
			</div>
			<div class='card-body'>

				<div
					class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}"
					role="alert">${errorMessage}</div>

				<%-- alert conferma --%>
				<div
					class="alert alert-success ${messaggioConferma!=null?'':'d-none' }"
					role="alert">${messaggioConferma }</div>

				<form method="post"
					action="${pageContext.request.contextPath}/EsecuzionePartitaServlet">

					<div class="form-group">
						<label class="control-label col-sm-2">Credito Attuale:</label>
						<div class="col-sm-4">${sessionScope.user.creditoAccumulato }
						</div>
					</div>
					<a
						href="${pageContext.request.contextPath}/EsecuzionePartitaServlet"
						class='btn btn-outline-secondary' style='width: 80px'> Lascia
					</a>
					<button type="submit" name="submit" value="submit" id="submit"
						class="btn btn-primary">Gioca</button>



				</form>

				<!-- end card-body -->
			</div>
		</div>

		<!-- end container -->
	</main>
	<jsp:include page="../footer.jsp" />

</body>
</html>