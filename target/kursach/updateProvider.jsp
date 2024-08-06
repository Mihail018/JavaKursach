<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="kursach.Provider" %>

<html>
<head>
    <title>Изменить поставщика</title>

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

    <p>Введите название поставщика: <input type="text" value="<%=item.getName()%>" name = "getName<%=i%>" placeholder="Название..."></p>
    <p>Введите область деятельности поставщика: <input type="text" value="<%=item.getIndustry()%>" name="getIndustry<%=i%>" placeholder="Деятельность..."></p>
    <p>Введите прибыль поставщика: <input type="text" value="<%=item.getProfitFormatted()%>" name = "getProfit<%=i%>" placeholder="Прибыль..."> </p>

    </div>
    </div>
    <% } %>

    <input type="submit" name = "updateSubmit" value="Подтвердить">
    <input type="reset" value="Сбросить">
</form>

</body>
</html>