<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Добавить контрагента</title>

    <link rel="stylesheet" type="text/css" href = "style.css">
</head>
    
<body>
<h2 align="center">Контрагент</h2>
<form align="center" action="display" method="post">

    <%java.lang.String maxDate=java.time.LocalDate.now().toString();%>

    <p>Введите имя контрагента: <input type="text" name = "getName" placeholder="Имя..."></p>
    <p>Дата дату рождения контрагента: <input type="date" name="getBirthday" value="2003-03-19" min="1860-01-01" max="<%=maxDate%>"></p>
    <p>Введите финансы контрагента: <input type="text" name = "getState" placeholder="Финансы..."> </p>

    <input type="submit" name = "addSubmit" value="Подтвердить">
    <input type="reset" value="Сбросить">
</form>

</body>
</html>
    
</form>

</body>
</html>