<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="kursach.Agreement" %>

<html>
<head>
    <title>Показать договор</title>

    <link rel="stylesheet" type="text/css" href = "style.css">
</head>
    
<body>

<form align="center" action="display" method="post">
    <% java.util.ArrayList<kursach.Agreement> database = (java.util.ArrayList<kursach.Agreement>) request.getAttribute("database"); %>
    <% java.util.ArrayList<java.lang.String> status = (java.util.ArrayList<java.lang.String>) request.getAttribute("status"); %>
    <% for (int i=0; i<database.size(); i++) { %>
    <div style="text-align: center; padding: 10px;">
        <div style="margin-left: 30px;">
    <% Agreement item=database.get(i); %>

    <h1>Договор: <%=item.getName()%></h1>
    <p> Дата начала: <%=item.getDataBegin().toString()%>
    Дата окончания: <%=item.getDataEnd().toString()%> </p>
    <p> Условия договора: <%=item.getRequirements()%> </p>
    <p>Имя поставщика: <%=item.getProvider()%></p>
    <p>Имя контрагента: <%=item.getCounterparty()%></p>
    <p>Размер оплаты за выполненный заказ: <%=item.getPrice()%></p>
    <p>Тип услуги: <%=item.getType()%></p>

    <p>Статус выполнения: <%=status.get(i)%></p>

    </div>
    </div>
    <% } %>
    
</form>

</body>
</html>