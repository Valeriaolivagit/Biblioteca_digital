<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!doctype html>
        <html lang="es">

        <head>
            <jsp:include page="../includes/head.jsp" />
        </head>

        <body>
            <jsp:include page="../includes/header.jsp" />

            <div class="container-fluid d-flex justify-content-center align-item-center">
                <div class="card border-dark mt-3 mx-2 card-bg">
                    <div class="card-header card-bg-head">
                        <h3 class="text-center mt-1 mx-3 h-color">Iniciar sesión</h3>
                    </div>
                    <div class="card-body text-center">
                        <form action="/login" method="POST">
                            <div class="form-floating mb-3">
                                <input type="email" class="form-control ${not empty error ? 'is-invalid' : ''}"
                                    id="email" name="email" placeholder="" value="${email}" oninput="clearError(this)">
                                <label for="email">🧑🏻‍💻 Correo electrónico</label>
                            </div>
                            <div class="form-floating mb-2 position-relative">
                                <input type="password" class="form-control ${not empty error ? 'is-invalid' : ''}"
                                    id="password" name="password" placeholder="" autocomplete="off"
                                    oninput="clearError(this)">
                                <label for="password">🔒 Contraseña</label>
                                <i class="bi bi-eye-fill eyestyle" id="toggleSeePass"></i>
                                <div id="loginErrorText" class="invalid-feedback text-center ps-2 
                                        small mb-0 mt-1 px-1 pb-0 bg-warning-subtle rounded border-bg-warning
                                        ${not empty error ? 'd-block' : 'd-none'}">
                                    <c:out value="${error}" />
                                </div>
                            </div>
                            <button type="submit" class="btn btn-primary text-center mt-0">Ingresar</button>
                        </form>
                    </div>
                </div>
            </div>


            <script src="/assets/javascript/jquery-3.7.1.min.js"></script>
            <script src="/assets/javascript/login.js"></script>
            <jsp:include page="../includes/footer.jsp" />