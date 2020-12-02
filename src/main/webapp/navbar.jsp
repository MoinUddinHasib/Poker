<!-- navbar -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-primary">

	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarsExampleDefault" aria-controls="navbarCollapse"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarsExampleDefault">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link"
				href="${pageContext.request.contextPath}/ServletLogin">Home <span
					class="sr-only">(current)</span></a></li>
			<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
			<li class="nav-item"><a class="nav-link disabled" href="#"
				tabindex="-1" aria-disabled="true">Disabled</a></li>

			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="dropdown01"
				data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dropdown</a>
				<div class="dropdown-menu" aria-labelledby="dropdown01">
					<a class="dropdown-item" href="#">Cerca</a> <a
						class="dropdown-item" href="#">Risultati</a> <a
						class="dropdown-item" href="#">Inserisci nuovo elemento</a>
				</div></li>
			<li class="nav-item active"></li>
			<li class="nav-item active">
      <c:if test="${ sessionScope.user != null}">
        <a class="nav-link" href="${pageContext.request.contextPath}/ServletLogOut">Log-Out <span class="sr-only">(current)</span></a>
        </c:if>
      </li>
			<li>Benvenuto  ${sessionScope.user.nome } ${sessionScope.user.cognome }; Username: ${sessionScope.user.username }; Credito: ${sessionScope.user.creditoAccumulato };
			Esperienza: ${sessionScope.user.esperienzaAccumulata }; In gioco: ${sessionScope.user.tavolo_gioco != null }</li>
		</ul>
		
		<form class="form-inline my-2 my-lg-0">
			<input class="form-control mr-sm-2" type="text" placeholder="Search"
				aria-label="Search">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
		</form>
	</div>
</nav>