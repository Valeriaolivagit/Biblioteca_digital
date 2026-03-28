<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!doctype html>
        <html lang="es">

        <head>
            <jsp:include page="../includes/head.jsp" />
        </head>

        <body>
            <jsp:include page="../includes/header.jsp" />

            <div class="container-fluid justify-content-center align-item-center">
                <div class="row text-center">
                    <div class="col-12">
                        <h1 class="h-color mt-3">🎓 Mi perfil</h1>
                    </div>
                </div>
                <div class="row justify-content-center text-center mt-2 ms-4 me-4">
                    <div class="col-sm-6 col-lg-4">
                        <div class="card border-success mb-3">
                            <div class="card-header card-bg-head">Mis datos personales</div>
                            <div class="card-body">
                                <h5 class="card-title">¡Bienvenido/a
                                    <c:out value="${user.firstName}" />!
                                </h5>
                                <form action="/profile" method="POST">
                                    <div class="row g-3 align-items-center">
                                         <div class="col-12">
                                            <input type="hidden" class="form-control" name="id" id="id"
                                                value="${user.id}">
                                        </div>
                                        <div class="col-4">
                                            <label for="firstName"><i class="bi bi-person-fill"></i> Nombre</label>
                                        </div>
                                        <div class="col-8">
                                            <input type="text" class="form-control" name="firstName" id="firstName"
                                                value="${user.firstName}" required>
                                        </div>
                                    </div>
                                    <div class="row g-3 mt-1 align-items-center">
                                        <div class="col-4">
                                            <label for="lastName"><i class="bi bi-person"></i> Apellido/s</label>
                                        </div>
                                        <div class="col-8">
                                            <input type="text" class="form-control" name="lastName" id="lastName"
                                                placeholder="" value="${user.lastName}" required>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="col-12">
                                            <button class="btn btn-custom mt-2 px-4" type="submit">Actualizar</button>
                                        </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </div>

            <jsp:include page="../includes/footer.jsp" />