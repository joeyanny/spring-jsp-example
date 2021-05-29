<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <head>
        <title><spring:message code="user.list.title"/></title>
        <link rel="stylesheet" href="/resources/css/user.css">
    </head>
    <body>
        <div id="menu">
            <form method="GET" action="/users/new">
                <input class="button" type="submit" id="add" value="<spring:message code='menu.add'/>"</input>
            </form>
        </div>

        <div id="container">
            <div id="heading-container">
                <h1><spring:message code="user.list.heading"/></h1>
            </div>

            <div class="table">
                <table id="users-table">
                    <tr class="heading-row">
                        <th class="header-cell"><spring:message code="heading.firstname"/></th>
                        <th class="header-cell"><spring:message code="heading.lastname"/></th>
                        <th class="header-cell"><spring:message code="heading.housenumber"/></th>
                        <th class="header-cell"><spring:message code="heading.street"/></th>
                        <th class="header-cell"><spring:message code="heading.city"/></th>
                        <th class="header-cell"><spring:message code="heading.postcode"/></th>
                    </tr>
                    <c:forEach var="user" items="${users}">
                        <tr class="data-row">
                            <td class="data-cell" id="first-name"><a href="/users/${user.id}">${user.firstName}</a></td>
                            <td class="data-cell" id="last-name"><a href="/users/${user.id}">${user.lastName}</a></td>
                            <td class="data-cell" id="house-number"><a href="/users/${user.id}">${user.houseNumber}</a></td>
                            <td class="data-cell" id="street"><a href="/users/${user.id}">${user.street}</a></td>
                            <td class="data-cell" id="city"><a href="/users/${user.id}">${user.city}</a></td>
                            <td class="data-cell" id="postcode"><a href="/users/${user.id}">${user.postcode}</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </body>
</html>