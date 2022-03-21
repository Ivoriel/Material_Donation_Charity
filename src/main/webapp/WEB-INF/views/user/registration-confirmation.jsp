<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../common/head-meta.jsp"/>

<body>
<header>
<jsp:include page="../../views/common/header_logged-out.jsp"/>

</header>
<%--TODO - maybe add the visual element similar to the one on form pages--%>
<section class="login-page">
    <h2>Użytkownik został zarejestrowany.</h2>

        <div class="form-group form-group--buttons">
            <a href="/user/login" class="btn btn--without-border">Zaloguj się</a>
        </div>
</section>



<jsp:include page="../common/footer.jsp"/>