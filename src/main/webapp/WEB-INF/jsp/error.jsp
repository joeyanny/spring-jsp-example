<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <head>
        <title><spring:message code="error.title"/></title>
        <link rel="stylesheet" href="/resources/css/user.css">
    </head>
    <body>
        <div id="menu"></div>
        <div id="container">
            <div id="heading-container">
                <h1><spring:message code="error.heading"/></h1>
            </div>

            <div class="error-block">
                <div class="error-image"></div>
                <span class="error-message-large"><spring:message code="error.message"/></span>
            </div>
        </div>
    </body>
</html>