<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<form action="init" method = "post">
<h1><%= "Hello!" %>
</h1>
<p>Ten cua ban la: </p>
<input type="text" name="name">
<br/>
</form>
<br/>
<a href="store">Xem cửa hàng</a>

</body>
</html>