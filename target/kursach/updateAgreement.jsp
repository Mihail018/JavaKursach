<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="kursach.Agreement" %>
<%@ page import="kursach.Contragent" %>
<%@ page import="kursach.Provider" %>

<html>
<head>
    <title>Изменить договор(ы)</title>

    <link rel="stylesheet" type="text/css" href = "style.css">
</head>
    
<body>

<form align="center" action="display" method="post">
    <% java.util.ArrayList<kursach.Agreement> database = (java.util.ArrayList<kursach.Agreement>) request.getAttribute("database"); %>
    <% java.util.ArrayList<kursach.Contragent> databaseContragent = (java.util.ArrayList<kursach.Contragent>) request.getAttribute("databaseContragent"); %>
    <% java.util.ArrayList<kursach.Provider> databaseProvider = (java.util.ArrayList<kursach.Provider>) request.getAttribute("databaseProvider"); %>
    <% for (int i=0; i<database.size(); i++) { %>
    <div style="text-align: center; padding: 10px;">
        <div style="margin-left: 30px;">
    <% Agreement item=database.get(i); %>
    <h1>Договор: <%=item.getName()%></h1>
    <p>Введите название договора: <input type="text" value="<%=item.getName()%>" name = "getName<%=i%>" placeholder="Договор..."> </p>
    <p> Введите дату начала и дату окончания договора:<br>
    Дата начала: <input type="date" name="dateBegin<%=i%>" value="<%=item.getDataBegin().toString()%>" min="1995-01-01" max="2077-01-01">
    Дата окончания: <input type="date" name="dateEnd<%=i%>" value="<%=item.getDataEnd().toString()%>" min="1995-01-01" max="2077-01-01"> </p>
    <p>Введите условия договора: <input type="text" value="<%=item.getRequirements()%>" name = "getRequirements<%=i%>" placeholder="Требования..."> </p>

    <p>
    Имя поставщика:
    <%java.lang.String providerName=database.get(i).getProvider();%>
    <select name = "getProvider<%=i%>">
    <%for (int j=0; j<databaseProvider.size(); j++) {%>
        <%Provider item2=databaseProvider.get(j);%>
        <option value="<%=item2.getName()%> " <%=providerName.equals(item2.getName()) ? "selected" : "" %>><%=item2.getName()%></option>
    <%}%>
    </select>
    </p>

    <p>Имя контрагента и размер оплаты:
    <%java.lang.String contragentName=database.get(i).getCounterparty();%>
    <select name = "getContragent<%=i%>">
    <%for (int j=0; j<databaseContragent.size(); j++) {%>
        <%Contragent item2=databaseContragent.get(j);%>
        <option value="<%=item2.getName()%>" <%=contragentName.equals(item2.getName()) ? "selected" : "" %>><%=item2.getName()%></option>
    <%}%>
    </p>

    <p>Введите размер оплаты за выполненный заказ: 
    <input type="text" value="<%=item.getPrice()%>" name = "getPrice<%=i%>" placeholder="Размер оплаты..."> 
    </p>

    Выберите тип услуги:
    <%java.lang.String typeName=database.get(i).getType();%>
    <select name="getType<%=i%>">
    <option value="Товар" <%=typeName.equals("Товар") ? "selected" : "" %>>Товар</option>
    <option value="Услуга" <%=typeName.equals("Услуга") ? "selected" : "" %>>Услуга</option>
    </select>
    

    </div>
    </div>
    <% } %>

    <input type="submit" name = "updateSubmit" value="Подтвердить">
    <input type="reset" value="Сбросить">
</form>

</body>
</html>