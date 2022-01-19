<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../common/head-meta.jsp"/>

<body>
<header>

<jsp:include page="../common/header_logged-in.jsp"/>
    <%--TODO - maybe add the visual element similar to the one on form pages--%>

</header>

<section class="login-page">
    <h2>Dodaj nową instytucję charytatywną</h2>
    <c:if test="${not empty duplicateInstitution}">
        <h2 style="color: red">
                ${duplicateInstitution}
        </h2>
    </c:if>
    <form:form method="post" modelAttribute="institution">
        <div class="form-group">
            <table>
                <tr><td><form:input path="name" type="name" placeholder="Nazwa" /></td></tr>
                <tr><td><form:errors path="name" style="color: red" /></td></tr>
            </table>
        </div>
        <div class="form-group">
            <table>
                <tr><td><form:textarea path="description" type="description" placeholder="Opis" /></td></tr>
                <tr><td><form:errors path="description" style="color: red" /></td></tr>
            </table>
        </div>

        <div class="form-group form-group--buttons">
            <button class="btn" type="submit">Dodaj fundację</button>
        </div>
    </form:form>
</section>



<jsp:include page="../common/footer.jsp"/>