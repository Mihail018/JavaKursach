<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="kursach.Agreement" %>

<html>
<head>
    <title>Удалить договор</title>

    <link rel="stylesheet" type="text/css" href = "style.css">
</head>
    
<body>

<form align="center" action="display" method="post">
    <% java.util.ArrayList<kursach.Agreement> database = (java.util.ArrayList<kursach.Agreement>) request.getAttribute("database"); %>
    <% for (int i=0; i<database.size(); i++) { %>
    <div style="text-align: center; padding: 10px;">
        <div style="margin-left: 30px;">
    <% Agreement item=database.get(i); %>

    <%-- !!!Изменить вид!!! --%>

    <h1>Договор: <%=item.getName()%></h1>
    <p> Дата начала: <%=item.getDataBegin().toString()%>
    Дата окончания: <%=item.getDataEnd().toString()%> </p>
    <p> Условия договора: <%=item.getRequirements()%> </p>
    <p>Имя поставщика: <%=item.getProvider()%></p>
    <p>Имя контрагента: <%=item.getCounterparty()%></p>
    <p>Размер оплаты за выполненный заказ: <%=item.getPrice()%></p>
    <p>Тип услуги: <%=item.getType()%></p>

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