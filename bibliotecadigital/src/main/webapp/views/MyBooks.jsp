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
                        <h1 class="h-color mt-3">📚 Mis libros</h1>
                    </div>
                </div>
                <div class="row text-center mt-2 ms-4 me-4">
                    <div class="col-12">
                        <table class="table table-hover table-bordered ">
                            <thead class="table-subtle-dark text-center">
                                <tr>
                                    <th>Fecha de solicitud</th>
                                    <th>Titulo</th>
                                    <th>Autor</th>
                                    <th>Descripcion</th>
                                    <th>Status</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="loan" items="${loans}">
                                    <tr>
                                        <td>
                                            <c:out value="${loan.loanDate}" />
                                        </td>
                                        <td>
                                            <c:out value="${loan.bookTitle}" />
                                        </td>
                                        <td>
                                            <c:out value="${loan.bookAuthor}" />
                                        </td>
                                        <td>
                                            <c:out value="${loan.bookDescription}" />
                                        </td>
                                        <td>
                                            <c:out value="${loan.status}" />
                                        </td>
                                        <td>
                                            <form action="return-book" method="post">
                                                <input type="hidden" name="loanId" value="${loan.loanId}">
                                                <input type="hidden" name="bookId" value="${loan.bookId}">
                                                <button type="submit"
                                                    onclick="return confirm('¿Esta seguro de querer devolver este libro?')">Devolver
                                                    libro</button>
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