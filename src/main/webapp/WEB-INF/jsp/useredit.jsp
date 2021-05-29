<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <head>
        <title><spring:message code="user.edit.title"/></title>
           <link rel="stylesheet" href="/resources/css/user.css">
    </head>
    <body>
        <form:form id="form" method="POST" modelAttribute="user" action="/users/${user.id}">
            <div id="menu">
                <input class="button" type="submit" name="save" id="save" value="<spring:message code='menu.save'/>"/>
                <input class="button" type="submit" name="cancel" id="cancel" value="<spring:message code='menu.cancel'/>"/>
            </div>

            <div id="container">
                <div id="heading-container">
                    <h1><spring:message code="user.edit.heading"/></h1>
                </div>

                <spring:hasBindErrors name="user">
                    <c:forEach var="error" items="${errors.allErrors}">
                        <div class="error-banner">
                           <spring:message code='${error.code}'/>
                        </div>
                    </c:forEach>
                </spring:hasBindErrors>

                <div class="table">
                    <div class="row">
                        <form:label path="firstName" class="thin"><spring:message code="label.firstname"/></form:label>
                        <form:input path="firstName" class="input" id="first-name-input" value="${user.firstName}"/>
                        <div class='error-message'>
                            <form:errors path="firstName"/>
                        </div>
                    </div>

                    <div class="row">
                        <form:label path="lastName" class="thin"><spring:message code="label.lastname"/></form:label>
                        <form:input path="lastName" class="input" id="last-name-input" value="${user.lastName}"/>
                        <div class='error-message'>
                            <form:errors path="lastName"/>
                        </div>
                    </div>

                    <div class="row">
                        <form:label path="houseNumber" class="thin"><spring:message code="label.housenumber"/></form:label>
                        <form:input path="houseNumber" class="input" id="house-number-input" value="${user.houseNumber}"/>
                        <div class='error-message'>
                            <form:errors path="houseNumber"/>
                        </div>
                    </div>

                    <div class="row">
                        <form:label path="street" class="thin"><spring:message code="label.street"/></form:label>
                        <form:input path="street" class="input" id="street-input" value="${user.street}"/>
                        <div class='error-message'>
                            <form:errors path="street"/>
                        </div>
                    </div>

                    <div class="row">
                        <form:label path="city" class="thin"><spring:message code="label.city"/></form:label>
                        <form:input path="city" class="input" id="city-input" value="${user.city}"/>
                        <div class='error-message'>
                            <form:errors path="city"/>
                        </div>
                    </div>

                    <div class="row">
                        <form:label path="postcode" class="thin"><spring:message code="label.postcode"/></form:label>
                        <form:input path="postcode" class="input" id="postcode-input" value="${user.postcode}"/>
                        <div class='error-message'>
                            <form:errors path="postcode"/>
                        </div>
                    </div>
                </div>
            </div>
        </form:form>
    <body>
</html>