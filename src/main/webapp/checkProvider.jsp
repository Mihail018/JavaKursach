<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="kursach.Provider" %>

<html>
<head>
    <title>Показать поставщиков</title>

    <link rel="stylesheet" type="text/css" href = "style.css">
</head>
    
<body>

<form align="center" action="display" method="post">
    <% java.util.ArrayList<kursach.Provider> database = (java.util.ArrayList<kursach.Provider>) request.getAttribute("database"); %>
    <% for (int i=0; i<database.size(); i++) { %>
    <div style="text-align: center; padding: 10px;">
        <div style="margin-left: 30px;">
    <% Provider item=database.get(i); %>

    <h1>Поставщик: <%=item.getName()%></h1>
    <p>Область деятельности: <%=item.getIndustry()%>
    <p>Прибыль: <%=item.getProfitFormatted()%></p>

    </div>
    </div>
    <% } %>
    
</form>

</body>
</html>