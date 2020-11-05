<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <head>
        <title>User List</title>
        <link rel="stylesheet" href="/resources/css/user.css">
    </head>
    <body>
		<div id="menu">
			<form method="GET" action="/users/new">
	    		<input class="button" type="submit" value="Add User"/>
	    	</form>
    	</div>

    	<div id="container">
			<div id="heading-container">
				<h1>View Users</h1>
			</div>

			<div class="table">
				<table>
			    	<c:forEach var="user" items="${users}">
			    		<tr>
				    		<td class="input"><a href="/users/${user.id}">${user.firstName}</a></td>
				    		<td class="input"><a href="/users/${user.id}">${user.lastName}</a></td>
				    		<td class="input"><a href="/users/${user.id}">${user.houseNumber}</a></td>
				    		<td class="input"><a href="/users/${user.id}">${user.street}</a></td>
				    		<td class="input"><a href="/users/${user.id}">${user.city}</a></td>
				    		<td class="input"><a href="/users/${user.id}">${user.postcode}</a></td>
				    	</tr>
				    </c:forEach>
			    </table>
		    </div>
	    </div>
    </body>
</html>