<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <head>
        <title>Add User</title>
       	<link rel="stylesheet" href="/resources/css/user.css">
    </head>
    <body>
		<form:form id="form" method="POST" modelAttribute="user" action="/users">
			<div id="menu">
				<input class="button" type="submit" name="save" value="Save"/>
				<input class="button" type="submit" name="cancel" value="Cancel"/>
		    </div>
	    	<div id="container">
				<div id="heading-container">
					<h1>Add User</h1>
				</div>
				<div class="table">
					<div class="row">
						<form:label path="firstName" class="thin">First Name:</form:label>
			    	    <form:input path="firstName" class="input"/>
			    	</div>
		    	    <div class="row">
			    	    <form:label path="lastName" class="thin">Last Name:</form:label>
			    		<form:input path="lastName" class="input"/>
			    	</div>
		    		<div class="row">
			    	    <form:label path="houseNumber" class="thin">House Number:</form:label>
			    		<form:input path="houseNumber" class="input"/>
			    	</div>
		    		<div class="row">
			    	    <form:label path="street" class="thin">Street:</form:label>
			    		<form:input path="street" class="input"/>
			    	</div>
		    		<div class="row">
			    	    <form:label path="city" class="thin">City:</form:label>
			    		<form:input path="city" class="input"/>
			    	</div>
		    		<div class="row">
			    	    <form:label path="postcode" class="thin">Postcode:</form:label>
			    		<form:input path="postcode" class="input"/>
			    	</div>
		    	</div>
			</div>
		</form:form>
    <body>
</html>