<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <head>
        <title><spring:message code="user.details.title"/></title>
        <link rel="stylesheet" href="/resources/css/user.css">
    </head>
    <body>
        <div id="menu">
            <form method="GET" action="/users/${user.id}/edit">
                <input class="button" type="submit" id="edit" value="<spring:message code='menu.edit'/>"/>
            </form>
            <form method="GET" action="/users/${user.id}/delete">
                <input class="button" type="submit" id="delete" value="<spring:message code='menu.delete'/>"/>
            </form>
            <form method="GET" action="/users">
                <input class="button" type="submit" id="cancel" value="<spring:message code='menu.cancel'/>"/>
            </form>
        </div>

        <div id="container">
            <div id="heading-container">
                <h1><spring:message code="user.details.heading"/></h1>
            </div>

            <div class="details">
                <div class="details-row">
                    <label class="details-label"><spring:message code="heading.firstname"/></label>
                    <span class="details-value" id="first-name">${user.firstName}</span>
                </div>
                <div class="details-row">
                    <label class="details-label"><spring:message code="heading.lastname"/></label>
                    <span class="details-value" id="last-name">${user.lastName}</span>
                </div>
                <div class="details-row">
                    <label class="details-label"><spring:message code="heading.housenumber"/></label>
                    <span class="details-value" id="house-number">${user.houseNumber}</span>
                </div>
                <div class="details-row">
                    <label class="details-label"><spring:message code="heading.street"/></label>
                    <span class="details-value" id="street">${user.street}</span>
                </div>
                <div class="details-row">
                    <label class="details-label"><spring:message code="heading.city"/></label>
                    <span class="details-value" id="city">${user.city}</span>
                </div>
                <div class="details-row">
                    <label class="details-label"><spring:message code="heading.postcode"/></label>
                    <span class="details-value" id="postcode">${user.postcode}</span>
                </div>
            </div>
        </div>
    </body>
</html>