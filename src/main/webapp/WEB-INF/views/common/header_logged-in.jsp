<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

    <nav class="container container--70">
        <ul class="nav--actions">
            <li class="logged-user">
                Witaj Agata
                <ul class="dropdown">
                    <li><a href="/user/profile/${user.id}">Profil</a></li>
                    <li><a href="#">Moje zbiórki</a></li>
                    <li><a href="/user/list">Lista użytkowników</a></li>
                    <li><a href="/institution/list">Lista fundacji</a></li>
                    <li><a href="/user/logout">Wyloguj</a></li>
                </ul>
            </li>
        </ul>

