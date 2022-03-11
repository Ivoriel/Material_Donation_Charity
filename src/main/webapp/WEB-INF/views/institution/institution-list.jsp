<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../common/head-meta.jsp"/>

<body>
<header>
    <jsp:include page="../common/header_logged-in.jsp"/>
</header>

<section class="help">
    <h2>Lista fundacji</h2>
    <table>
        <c:forEach var="institution" items="${institutionList}">
            <tr>
                <td class="col">
                    <div class="title">${institution.id}</div>
                </td>
                <td class="col">
                    <div class="title">${institution.name}</div>
                </td>
            </tr>
        </c:forEach>
    </table>

</section>

<jsp:include page="../../views/common/footer.jsp"/>