<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="kursach.Contragent" %>

<html>
<head>
    <title>Изменить контрагента</title>

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

    <p>Введите имя контрагента: <input type="text" value="<%=item.getName()%>" name = "getName<%=i%>" placeholder="Имя..."></p>
    <p>Дата дату рождения контрагента: <input type="date" name="getBirthday<%=i%>" value="<%=item.getBirthday()%>" min="1860-01-01" max="<%=maxDate%>"></p>
    <p>Введите финансы контрагента: <input type="text" name = "getState<%=i%>" value="<%=item.getState()%>" placeholder="Финансы..."> </p>

    </div>
    </div>
    <% } %>

    <input type="submit" name = "updateSubmit" value="Подтвердить">
    <input type="reset" value="Сбросить">
</form>

</body>
</html>