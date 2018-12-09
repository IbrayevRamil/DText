<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
      integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>

<html>
<head>
    <title>Authorization</title>
</head>

<body>
<div class="container">
    <div align="center">
        <div class="col-md-4 col-md-offset-4">
            <form class="form-horizontal" action="${pageContext.request.contextPath}/login" method="post">
                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input class="form-control" name="username" placeholder="username"
                               required>
                    </div>
                </div>
                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                        <input class="form-control" type="password" name="password"
                               placeholder="Password"
                               required>
                    </div>
                </div>
                <div class="form-group">
                    <c:if test="${param.error != null}">
                        <p class="alert alert-danger">Invalid username or password.</p>
                    </c:if>
                    <c:if test="${param.logout != null}">
                        <p class="alert alert-info">You have been logged out successfully.</p>
                    </c:if>
                    <c:if test="${param.register != null}">
                        <p class="alert alert-success">You have been registered successfully.</p>
                    </c:if>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="form-group row" align="right">
                    <a href="${pageContext.request.contextPath}/register" class="btn btn-primary">Registration</a>
                    <input class="btn btn-success" type="submit" value="Login">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
