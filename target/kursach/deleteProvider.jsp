<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="kursach.Provider" %>

<html>
<head>
    <title>Удалить поставщика</title>

    <link rel="stylesheet" type="text/css" href = "style.css">
</head>
    
<body>

<form align="center" action="display" method="post">
    <% java.util.ArrayList<kursach.Provider> database = (java.util.ArrayList<kursach.Provider>) request.getAttribute("database"); %>
    <%java.lang.String maxDate=java.time.LocalDate.now().toString();%>
    <% for (int i=0; i<database.size(); i++) { %>
    <div style="text-align: center; padding: 10px;">
        <div style="margin-left: 30px;">
    <% Provider item=database.get(i); %>

    <h1>Поставщик: <%=item.getName()%></h1>
    <p>Область деятельности: <%=item.getIndustry()%>
    <p>Прибыль: <%=item.getProfitFormatted()%></p>

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