<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <head>
        <title>User Details</title>
        <link rel="stylesheet" href="/resources/css/user.css">
    </head>
    <body>
        <div id="menu">
            <form method="GET" action="/users/${user.id}/edit">
                <input class="button" type="submit" value="Edit"/>
            </form>
            <form method="GET" action="/users/${user.id}/delete">
                <input class="button" type="submit" value="Delete"/>
            </form>
            <form method="GET" action="/users">
                <input class="button" type="submit" value="Cancel"/>
            </form>
        </div>

        <div id="container">
            <div id="heading-container">
                <h1>User Details</h1>
            </div>

            <div class="row">
                <label class="thin">First Name:</label>
                   <span class="input">${user.firstName}</span>
            </div>
            <div class="row">
                <label class="thin">Last Name:</label>
                <span class="input">${user.lastName}</span>
            </div>
            <div class="row">
                <label class="thin">House Number:</label>
                <span class="input">${user.houseNumber}</span>
            </div>
            <div class="row">
                <label class="thin">Street:</label>
                <span class="input">${user.street}</span>
            </div>
            <div class="row">
                <label class="thin">City:</label>
                <span class="input">${user.city}</span>
            </div>
            <div class="row">
                <label class="thin">Postcode:</label>
                <span class="input">${user.postcode}</span>
            </div>
        </div>
    </body>
</html>