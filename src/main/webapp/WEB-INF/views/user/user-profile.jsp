<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../common/head-meta.jsp"/>

<body>
<header>
    <jsp:include page="../common/header_logged-in.jsp"/>
    <jsp:include page="../common/header_navigation_bar.jsp"/>
</header>

<section>
    <table>
        <form:form action="/user/profile/${user.id}" method="post" modelAttribute="user">
            <tr>
                <td><form:label path="email">User email:</form:label></td>
                <td><form:input path="email"/>
                </td>
                <td><form:errors path="email" /></td>
            </tr>
            <tr>
                <td><form:label path="userType">User type:</form:label></td>
                <td>
                    <form:select path="userType" >
                        <form:options items="${userTypes}" itemValue="label" itemLabel="label"/>
                    </form:select>
                </td>
                <td><form:errors path="userType" /></td>
            </tr>
            <tr>
                <td><input type="submit" value="Save"/></td>
            </tr>
        </form:form>
    </table>
</section>

<jsp:include page="../../views/common/footer.jsp"/>