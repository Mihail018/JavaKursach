<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="kursach.Contragent" %>

<html>
<head>
    <title>Показать контрагентов</title>

    <link rel="stylesheet" type="text/css" href = "style.css">
</head>
    
<body>

<form align="center" action="display" method="post">
    <% java.util.ArrayList<kursach.Contragent> database = (java.util.ArrayList<kursach.Contragent>) request.getAttribute("database"); %>
    <% for (int i=0; i<database.size(); i++) { %>
    <div style="text-align: center; padding: 10px;">
        <div style="margin-left: 30px;">
    <% Contragent item=database.get(i); %>

    <%-- !!!Изменить вид!!! --%>

    <h1>Контрагент: <%=item.getName()%></h1>
    <p> Дата рождения: <%=item.getBirthday().toString()%>
    <p>Состояние: <%=item.getState()%></p>

    </div>
    </div>
    <% } %>
    
</form>

</body>
</html>