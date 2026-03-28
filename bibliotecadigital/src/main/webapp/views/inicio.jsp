<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!doctype html>
        <html lang="es">

        <head>
            <jsp:include page="../includes/head.jsp" />
        </head>

        <body>
            <jsp:include page="../includes/header.jsp" />

            <div class="container-fluid">
                <div class="row text-center">
                    <div class="col-sm">
                        <h1 class="h-color mt-2"><i class="bi bi-book"></i> Listado de libros</h1>
                    </div>
                </div>
                <div class="row ms-3">
                    <div class="col-sm mb-1">
                        <form action="/add-book" method="get">
                            <button class="btn btn-custom-hover text-dark fs-3" type="submit">📘Agregar nuevo
                                libro</button>
                        </form>
                    </div>
                </div>
                <div class="row ms-3 me-3 text-center">
                    <div class="col-12">
                        <table class="table table-light table-bordered table-hover">
                            <thead class="text-center">
                                <tr>
                                    <th>Id</th>
                                    <th>Titulo</th>
                                    <th>Autor</th>
                                    <th>Descripcion</th>
                                    <th>Cantidad</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="book" items="${books}">
                                    <tr>
                                        <td>
                                            <c:out value="${book.bookId}" />
                                        </td>
                                        <td>
                                            <c:out value="${book.title}" />
                                        </td>
                                        <td>
                                            <c:out value="${book.author}" />
                                        </td>
                                        <td>
                                            <c:out value="${book.description}" />
                                        </td>
                                        <td>
                                            <c:out value="${book.quantity}" />
                                        </td>
                                        <td>
                                            <form action="update-book">
                                                <input type="hidden" name="bookId" value="${book.bookId}">
                                                <button type="submit">Editar ✍🏻</button>
                                            </form>
                                            <form action="delete-book" method="post">
                                                <input type="hidden" name="bookId" value="${book.bookId}">
                                                <button type="submit"
                                                    onclick="return confirm('¿Esta seguro de querer eliminar este libro?')">Eliminar 🗑️</button>
                                            </form>
                                            <form action="/request-book" method="post">
                                                <input type="hidden" name="bookId" value="${book.bookId}">
                                                <button type="submit"
                                                    onclick="return confirm('¿Esta seguro de querer solicitar este libro?')">Solicitar
                                                    prestamo 📑</button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <jsp:include page="../includes/footer.jsp" />