<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../common/head-meta.jsp"/>

<body>
<header>
    <jsp:include page="../common/header_logged-in.jsp"/>
    <jsp:include page="../common/header_navigation_bar.jsp"/>
</header>

<section class="help">
    <h2>Lista użytkowników</h2>
    <table>
        <c:forEach var="user" items="${userList}">
            <tr>
                <td class="col">
                    <div class="title">${user.id}</div>
                </td>
                <td class="col">
                    <div class="title">${user.email}</div>
                </td>
                <td class="col">
                    <div class="title">${user.userType}</div>
                </td>
                <form action="/user/profile/${user.id}" method="get">
                    <td>
                        <input type="submit" value="Profil">
                    </td>
                </form>
            </tr>
        </c:forEach>
    </table>

</section>

<jsp:include page="../../views/common/footer.jsp"/>