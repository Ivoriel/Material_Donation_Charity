<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../common/head-meta.jsp"/>

<body>
<header>
<jsp:include page="../common/header_unregistered.jsp"/>
</header>
<%--TODO - maybe add the visual element similar to the one on form pages--%>
<section class="login-page">
    <h2>Załóż konto</h2>
    <c:if test="${not empty duplicateEmail}">
        <h3 style="color: red">
            ${duplicateEmail}
        </h3>
    </c:if>
    <form:form method="post" modelAttribute="user">
        <div class="form-group">
            <table>
                <tr><td><form:input path="email" type="email" placeholder="Email" /></td></tr>
                <tr><td><form:errors path="email" style="color: red" /></td></tr>
            </table>
        </div>
        <div class="form-group">
            <table>
                <tr><td><form:input path="password" type="password" placeholder="Hasło" /></td></tr>
                <tr><td><form:errors path="password" style="color: red" /></td></tr>
            </table>
        </div>
        <div class="form-group">
            <input type="password" name="password2" placeholder="Powtórz hasło" id="passwordRetyped" />
        </div>

        <div class="form-group form-group--buttons">
            <a href="/user/login" class="btn btn--without-border">Zaloguj się</a>
            <button class="btn" type="submit">Załóż konto</button>
        </div>
    </form:form>
</section>



<jsp:include page="../common/footer.jsp"/>