<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../common/head-meta.jsp"/>

<body>
<header>
<jsp:include page="../common/header_logged-out.jsp"/>
<jsp:include page="../common/header_navigation_bar.jsp"/>

</header>
<%--TODO - maybe add the visual element similar to the one on form pages--%>
<section class="login-page">
    <h2>Zaloguj się</h2>
        <c:if test="${not empty unregisteredEmail}">
            <h3 style="color: red">
                ${unregisteredEmail}
            </h3>
        </c:if>
    <form method="post">
        <div class="form-group">
            <input type="email" name="email" placeholder="Email" />
        </div>
        <div class="form-group">
            <input type="password" name="password" placeholder="Hasło" />
            <a href="#" class="btn btn--small btn--without-border reset-password">Przypomnij hasło</a>
        </div>

        <div class="form-group form-group--buttons">
            <a href="/user/register" class="btn btn--without-border">Załóż konto</a>
            <button class="btn" type="submit">Zaloguj się</button>
        </div>
    </form>
</section>

<jsp:include page="../common/footer.jsp"/>