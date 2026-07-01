<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">



<nav class="navbar shadow-sm py-3 mb-3"
     style="background-color: #F5F0E6;">
    <div class="container">

        <span class="navbar-brand fw-bold fs-3"
              style="color: #4F5D2F;">
             Fórum
        </span>

        <div class="d-flex flex-wrap gap-2 justify-content-center">
            <a href="<c:url value='/topicos/todos' />"
               class="btn btn-sm btn-light border shadow-sm ${selecionado == 'todos'? 'active btn-secondary' : 'btn-light'}">
                📚 Todos
            </a>

            <a href="<c:url value='/topicos/em-aberto' />"
               class="btn btn-sm btn-light border shadow-sm ${selecionado == 'em-aberto' ? 'active btn-secondary' : 'btn-light'}">
                🟡 Em aberto
            </a>

            <a href="<c:url value='/topicos/resolvidos' />"
               class="btn btn-sm btn-light border shadow-sm ${selecionado == 'resolvidos' ? 'active btn-secondary' : 'btn-light'}">
                ✅ Resolvidos
            </a>

            <a href="<c:url value='/topicos/by-usuario-logado' />"
               class="btn btn-sm btn-light border shadow-sm ${selecionado == 'topicosByUsuarioLogado' ? 'active btn-secondary' : 'btn-light'}">
                👤 Meus tópicos
            </a>

            <a href="<c:url value='/topicos/recentes' />"
               class="btn btn-sm btn-light border shadow-sm ${selecionado == 'recentes' ? 'active btn-secondary' : 'btn-light'}">
                🕒 Mais recentes
            </a>

        </div>

        <div class="d-flex align-items-center">

            <span class="me-3 fw-semibold"
                  style="color: #4F5D2F;">
                Olá, ${sessionScope.usuario.nome}
            </span>

            <a href="<c:url value='/cadastro-topico' />"
               class="btn me-2"
               style="background-color: #7D8C3B; color: white;">
                + Novo Tópico
            </a>

            <a href="<c:url value='/logout' />"
               class="btn btn-outline-secondary">
                Sair
            </a>

        </div>

    </div>
</nav>
