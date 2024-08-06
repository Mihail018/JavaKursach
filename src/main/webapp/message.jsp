<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Сообщение</title>
</head>
    
<body>
<% java.lang.String message = (java.lang.String) request.getAttribute("message").toString(); %>
<p align="center"> <%=message %> </p>

</body>
</html>