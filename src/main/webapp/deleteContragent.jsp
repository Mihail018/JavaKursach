<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="kursach.Contragent" %>

<html>
<head>
    <title>Удалить контрагента</title>

    <link rel="stylesheet" type="text/css" href = "style.css">
</head>
    
<body>

<form align="center" action="display" method="post">
    <% java.util.ArrayList<kursach.Contragent> database = (java.util.ArrayList<kursach.Contragent>) request.getAttribute("database"); %>
    <%java.lang.String maxDate=java.time.LocalDate.now().toString();%>
    <% for (int i=0; i<database.size(); i++) { %>
    <div style="text-align: center; padding: 10px;">
        <div style="margin-left: 30px;">
    <% Contragent item=database.get(i); %>

    <h1>Контрагент: <%=item.getName()%></h1>
    <p>Дата рождения: <%=item.getBirthday().toString()%>
    <p>Состояние: <%=item.getState()%></p>

    <input type = "checkbox" name="deleted<%=i%>" value="delete">
    <input hidden type="text" name="hiddenId<%=i%>" value="<%=item.getId()%>">

    </div>
    </div>
    <% } %>

    <input type="submit" name = "deleteSubmit" value="Подтвердить">
    <input type="reset" value="Сбросить">
</form>

</body>
</html>