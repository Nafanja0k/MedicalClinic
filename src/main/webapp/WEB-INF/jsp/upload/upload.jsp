<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 28.11.2015
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload a file please</title>
</head>
<body>
<h1>Please upload a file</h1>
<form method="post" action="/upload" enctype="multipart/form-data">
    <%--<input type="text" name="name"/>--%>
    <input type="file" name="file"/>
    <input type="submit"/>
</form>
</body>
</html>
