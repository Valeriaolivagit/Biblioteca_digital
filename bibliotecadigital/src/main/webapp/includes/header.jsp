<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <header>
      <nav class="navbar navbar-expand-lg nav-bar-bg">
        <div class="container-fluid">
          <!--Logo-->
          <a class="navbar-brand d-flex align-items-center" href="/home">
            <img src="/assets/image/logoinksandbooks.png" alt="LogoInks&Books" width="38" height="36"
              class="d-inline-block align-text-top">
            Inks & Books
          </a>
          <!--Boton responsive-->
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent">
            <span class="navbar-toggler-icon"></span>
          </button>


          <div class="collapse navbar-collapse" id="navbarContent">
            <div class="d-flex align-items-center mx-auto gap-3">
              <!--Buscador-->
              <form class="d-flex mx-auto" role="search" action="/home">
                <input name="search" value="${param.search}" class="form-control me-2" type="text"
                  placeholder="Buscar libro..." aria-label="Search" />
                <button class="btn btn-search-outline btn-search-hover" type="submit"><i class="bi bi-search"></i>
                </button>
              </form>
              <!--Menú -->
              <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                  <a class="nav-link" href="/home">Inicio</a>
                </li>
                <c:choose>
                  <c:when test="${not empty sessionScope.user}">
                    <li class="nav-item">
                      <a class="nav-link" href="/return-book">Mis libros</a>
                    </li>
                  </c:when>
                  <c:otherwise>
                    <li class="nav-item">
                      <a class="nav-link" href="../views/register.jsp">Registro</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" href="../views/login.jsp">Login</a>
                    </li>
                  </c:otherwise>
                </c:choose>
              </ul>
            </div>

            <div class="d-flex align-items-center ms-auto gap-3">
              <c:if test="${not empty sessionScope.user}">
                <a class="nav-link" href="/profile">
                  <i class="bi bi-person-square nav-fs"></i> Mi Perfil
                </a>
                <form action="/login" method="get">
                  <button class="btn nav-link-btn btn-custom-outline btn-custom-hover">
                    <i class="bi bi-door-open fs-6" id="logoutIcon"></i>
                    Cerrar sesión</button>
                </form>
              </c:if>
            </div>
          </div>
        </div>
      </nav>
    </header>