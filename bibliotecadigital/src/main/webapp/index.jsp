<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
    <!doctype html>
    <html lang="es">

    <head>
        <jsp:include page="includes/head.jsp" />
    </head>

    <body>
        <jsp:include page="includes/header.jsp" />
        <div class="container-fluid mt-3">
            <div class="row text-center">
                <div class="col-12 mb-2">
                    <img src="/assets/image/logoinksandbooks.png" alt="books" width="100" height="90"
                        class="d-inline-block align-text-top">
                    <h1 class="h-color">Bienvenido a Inks & Books</h1>
                </div>
            </div>
        </div>
        <div class="row justify-content-center text-center">
            <div class="col-md-6 col-lg-5 text-end pe-4">
                <form action="/views/register.jsp" method="get">
                    <button class="btn btn-search-outline btn-search-hover" type="submit">¿No tienes cuenta?
                        ¡Registrate!
                    </button>
                </form>
            </div>
            <div class="col-4 text-start">
                <form action="/views/login.jsp" method="get">
                    <button class="btn btn-search-outline btn-search-hover" type="submit">Iniciar sesión
                    </button>
                </form>
            </div>
        </div>
        <jsp:include page="includes/footer.jsp" />