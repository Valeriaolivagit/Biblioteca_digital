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
                        <h3 class="text-center mt-1 mx-3 h-color">Formulario de Registro:</h3>
                    </div>
                    <div class="card-body text-center">
                        <form action="/register" method="POST">
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" name="firstName" id="firstName" placeholder=""
                                  value="${firstName}"  required>
                                <label for="firstName"><i class="bi bi-person-fill"></i> Nombre</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" name="lastName" id="lastName" placeholder=""
                                   value="${lastName}" required>
                                <label for="lastName"><i class="bi bi-person"></i> Apellidos</label>
                            </div>
                            <div class="form-floating mb-1">
                                <input type="email" class="form-control" name="email" id="email" placeholder=""
                                   value="${email}" required pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}">
                                <label for="email">🖂 Correo electrónico</label>
                            </div>
                            <div class="row g-2">
                                <div class="col-12">
                                    <div id="emailErrorText" class="invalid-feedback text-center ps-2 
                                        small mb-2 mt-0 p-1 bg-warning-subtle rounded border-bg-warning
                                        ${not empty errorEmail ? 'd-block' : 'd-none'}">
                                        <c:out value="${errorEmail}"/>
                                    </div>
                                </div>
                                <div class="row mt-1">
                                    <div class="col-6">
                                        <div class="form-floating">
                                            <input type="password" class="form-control" name="password" id="password"
                                                data-bs-toggle="tooltipPass" data-bs-placement="left"
                                                data-bs-html="true"
                                                data-bs-title="La contraseña debe tener entre 8 a 20 caracteres."
                                                placeholder="" minlength="8" maxlength="20" required>
                                            <label for="password">🔒 Contraseña</label>
                                        </div>
                                    </div>
                                    <div class="col-6">
                                        <div class="form-floating">
                                            <input type="password"
                                                class="form-control ${not empty errorPassword ? 'is-invalid' : ''}"
                                                name="confirmPass" id="confirmPass" placeholder="" minlength="8"
                                                maxlength="20" required>
                                            <label for="confirmPass">🔐 Confirmar </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-12 pt-1">
                                        <div id="passErrorText" class="invalid-feedback text-center ps-2 
                                        small mt-0 p-0 bg-warning-subtle rounded border-bg-warning
                                        ${not empty errorPassword ? 'd-block' : 'd-none'}">
                                            <c:out value="${errorPassword}" />
                                        </div>
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-primary text-center">Registrarse</button>
                        </form>
                    </div>
                </div>
            </div>

            <script src="/assets/javascript/jquery-3.7.1.min.js"></script>
            <script src="/assets/javascript/register.js"></script>
            <jsp:include page="../includes/footer.jsp" />