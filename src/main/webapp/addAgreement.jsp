<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="kursach.Contragent" %>
<%@ page import="kursach.Provider" %>

<html>
<head>
    <title>Добавить договор</title>

    <link rel="stylesheet" type="text/css" href = "style.css">
</head>
    
<body>
<h2 align="center">Договор</h2>
<form align="center" action="display" method="post">

<% java.util.ArrayList<kursach.Contragent> databaseContragent = (java.util.ArrayList<kursach.Contragent>) request.getAttribute("databaseContragent"); %>
<% java.util.ArrayList<kursach.Provider> databaseProvider = (java.util.ArrayList<kursach.Provider>) request.getAttribute("databaseProvider"); %>

    <p>Введите название договора: <input type="text" name = "getName" placeholder="Договор..."> </p>
    <p> Введите дату начала и дату окончания договора:<br>
    Дата начала: <input type="date" name="dateBegin" value="2023-12-11" min="1995-01-01" max="2077-01-01">
    Дата окончания: <input type="date" name="dateEnd" value="2023-12-11" min="1995-01-01" max="2077-01-01"> </p>
    <p>Введите условия договора: <input type="text" name = "getRequirements" placeholder="Требования..."> </p>
    <%-- <p>Введите имя поставщика: <input type="text" name = "getProvider" placeholder="Поставщик..."> </p>
    <p>Введите имя контрагента: <input type="text" name = "getCounterparty" placeholder="Контрагент..."> </p> --%>

    <p>Имя поставщика:
    <select name = "getProvider">
    <%for (int i=0; i<databaseProvider.size(); i++) {%>
        <%Provider item=databaseProvider.get(i);%>
        <option value="<%=item.getName()%>"><%=item.getName()%></option>
    <%}%>
    </select>
    </p>

    <p>Имя контрагента:
    <select name = "getContragent">
    <%for (int i=0; i<databaseContragent.size(); i++) {%>
        <%Contragent item=databaseContragent.get(i);%>
        <option value="<%=item.getName()%>"><%=item.getName()%></option>
    <%}%>
    </select>
    </p>

    <p>Введите размер оплаты за выполненный заказ: <input type="text" name = "getPrice" placeholder="Размер оплаты..."> </p>

    <p>Выберите тип услуги:
    <select name="getType">
    <option value="Товар">Товар</option>
    <option value="Услуга">Услуга</option>
    </select>
    </p>

    <input type="submit" name = "addSubmit" value="Подтвердить">
    <input type="reset" value="Сбросить">
</form>

</body>
</html>