<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <head>
        <title>Edit User</title>
       	<link rel="stylesheet" href="/resources/css/user.css">
    </head>
    <body>
		<form:form id="form" method="POST" modelAttribute="user" action="/users/${user.id}">
			<div id="menu">
				<input class="button" type="submit" name="save" value="Save"/>
				<input class="button" type="submit" name="cancel" value="Cancel"/>
		    </div>

	    	<div id="container">
				<div id="heading-container">
					<h1>Edit User</h1>
				</div>

				<div class="table">
					<div class="row">
						<form:label path="firstName" class="thin">First Name:</form:label>
			    	    <form:input path="firstName" class="input" value="${user.firstName}"/>
			    	</div>
			    	<div class="row">
			    	    <form:label path="lastName" class="thin">Last Name:</form:label>
			    		<form:input path="lastName" class="input" value="${user.lastName}"/>
			    	</div>
			    	<div class="row">
			    	    <form:label path="houseNumber" class="thin">House Number:</form:label>
			    		<form:input path="houseNumber" class="input" value="${user.houseNumber}"/>
			    	</div>
			    	<div class="row">
			    	    <form:label path="street" class="thin">Street:</form:label>
			    		<form:input path="street" class="input" value="${user.street}"/>
			    	</div>
			    	<div class="row">
			    	    <form:label path="city" class="thin">City:</form:label>
			    		<form:input path="city" class="input" value="${user.city}"/>
			    	</div>
			    	<div class="row">
			    	    <form:label path="postcode" class="thin">Postcode:</form:label>
			    		<form:input path="postcode" class="input" value="${user.postcode}"/>
			    	</div>
		    	</div>
			</div>
		</form:form>
    <body>
</html>