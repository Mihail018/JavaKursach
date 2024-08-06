<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Добавить поставщика</title>

    <link rel="stylesheet" type="text/css" href = "style.css">
</head>
    
<body>
<h2 align="center">Поставщик</h2>
<form align="center" action="display" method="post">

    <p>Введите название поставщика: <input type="text" name = "getName" placeholder="Название..."></p>
    <p>Введите область деятельности поставщика: <input type="text" name="getIndustry" placeholder="Деятельность..."></p>
    <p>Введите прибыль поставщика: <input type="text" name = "getProfit" placeholder="Прибыль..."> </p>

    <input type="submit" name = "addSubmit" value="Подтвердить">
    <input type="reset" value="Сбросить">
</form>

</body>
</html>