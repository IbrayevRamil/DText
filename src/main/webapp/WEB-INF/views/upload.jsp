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
    <title>EasyDiff</title>
</head>
<body>
<div class="container">
    <form method="post" action="<c:url value="/compare"/>" id="compareForm" enctype="multipart/form-data">
        <h1>
            Upload files:
            <br>
        </h1>
        <div class="form-group">
            <label class="control-label">Select source file: </label>
                <input id="input-source-file" name="source" type="file">
        </div>
        <div class="form-group">
            <label class="control-label">Select target file: </label>
            <input id="input-target-file" name="target" type="file">
        </div>
        <br>
        <h1>
            Additional settings:
            <br>
        </h1>
        <div class="form-group">
            <label class="control-label">Strict mode: </label>
            <input id="input-mode-strict" name="mode" type="checkbox" value="strict">
        </div>
        <div class="form-group">
            <label class="control-label">Side by side formatting type (Inline by default): </label>
            <input id="input-formatting-type" name="format" type="checkbox" value="side_by_side">
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger">Logout</a>
        <button class="btn btn-primary">Submit</button>
    </form>
</div>
</body>
</html>
