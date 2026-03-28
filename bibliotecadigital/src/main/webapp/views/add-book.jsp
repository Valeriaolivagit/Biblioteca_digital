<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!doctype html>
        <html lang="es">

        <head>
            <jsp:include page="../includes/head.jsp" />
        </head>

        <body>
            <jsp:include page="../includes/header.jsp" />

            <c:if test="${bookToEdit != null}">
                <div class="container-fluid d-flex justify-content-center align-item-center">
                    <div class="card border-dark mt-3 mx-2 card-bg">
                        <div class="card-header card-bg-head">
                            <h3 class="text-center mt-1 mx-3 h-color">📝 Editar libro</h3>
                        </div>
                        <div class="card-body text-center">
                            <form action="/update-book" method="post">
                                <p>
                                    <input type="hidden" name="bookId" value="${bookToEdit.bookId}">
                                </p>
                                <div class="form-floating mb-3">
                                    <input type="text" class="form-control" id="title" name="title" placeholder=""
                                        value="${bookToEdit.title}" required>
                                    <label for="title">📕Título</label>
                                </div>
                                <div class="form-floating mb-3">
                                    <input type="text" class="form-control" id="author" name="author" placeholder=""
                                        value="${bookToEdit.author}" required>
                                    <label for="author">✒️ Autor</label>
                                </div>
                                <div class="form-floating mb-3">
                                    <input type="text" class="form-control" id="description" name="description"
                                        placeholder="" value="${bookToEdit.description}" required>
                                    <label for="description">📜 Descripción</label>
                                </div>
                                <div class="form-floating mb-3">
                                    <input type="number" class="form-control" id="quantity" name="quantity"
                                        placeholder="" value="${bookToEdit.quantity}" min="1" required>
                                    <label for="quantity">📚 Cantidad</label>
                                </div>
                                <button class="btn btn-custom fs-5 ps-4 pe-4" type="submit">Guardar</button>
                            </form>
                        </div>
                    </div>
                </div>
            </c:if>

            <c:if test="${bookToEdit == null}">
                <div class="container-fluid d-flex justify-content-center align-item-center">
                    <div class="card border-dark mt-3 mx-2 card-bg">
                        <div class="card-header card-bg-head">
                            <h3 class="text-center mt-1 mx-3 h-color">📗 Nuevo libro</h3>
                        </div>
                        <div class="card-body text-center">
                            <form action="/add-book" method="post">
                                <div class="form-floating mb-3">
                                    <input type="text" class="form-control" id="title" name="title" placeholder=""
                                        value="${title}" required>
                                    <label for="title">📕Título</label>
                                </div>
                                <div class="form-floating mb-3">
                                    <input type="text" class="form-control" id="author" name="author" placeholder=""
                                        value="${author}" required>
                                    <label for="author">✒️ Autor</label>
                                </div>
                                <div class="form-floating mb-3">
                                    <input type="text" class="form-control" id="description" name="description"
                                        placeholder="" value="${description}" required>
                                    <label for="description">📜 Descripción</label>
                                </div>
                                <div class="form-floating mb-2">
                                    <input type="number" class="form-control ${not empty error ? 'is-invalid' : ''}"
                                        id="quantity" name="quantity" placeholder="" value="" min="1" required>
                                    <label for="quantity">📚 Cantidad</label>
                                    <div id="quantityErrorText" class="invalid-feedback text-center ps-2 
                                        small mt-2 p-0 bg-warning-subtle rounded border-bg-warning">
                                        <c:out value="${error}" />
                                    </div>
                                </div>
                                <button class="btn btn-custom fs-5 ps-4 pe-4" type="submit">Agregar</button>
                            </form>
                        </div>
                    </div>
                </div>
            </c:if>

            <script src="/assets/javascript/jquery-3.7.1.min.js"></script>
            <script src="/assets/javascript/book.js"></script>
            <jsp:include page="../includes/footer.jsp" />