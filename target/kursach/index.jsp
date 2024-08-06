<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>

    <link rel="stylesheet" type="text/css" href = "Style.css">

    <title>Курсовая работа</title>
</head>
    
<body>

<%HttpSession session1 = request.getSession();%>
<%java.lang.String notification = (java.lang.String) session1.getAttribute("notification");%>

<p>Уведомление: <%=notification%></p>

<form action="display" id="formStyle">

    <style>
        .row {
            display: flex;
        }
    </style>
    
    <div class="row">
    <a href="display?display=addAgreement" class="buttonsStyle">Добавить договор</a><br>
    <a href="display?display=updateAgreement" class="buttonsStyle">Изменить договоры</a><br>
    <a href="display?display=deleteAgreement" class="buttonsStyle">Удалить договоры</a><br>
    <a href="display?display=checkAgreement" class="buttonsStyle">Показать договоры</a><br>
    </div>

    <div class="row">
    <a href="display?display=addContragent" class="buttonsStyle">Добавить контрагента</a><br>
    <a href="display?display=updateContragent" class="buttonsStyle">Изменить контрагентов</a><br>
    <a href="display?display=deleteContragent" class="buttonsStyle">Удалить контрагентов</a><br>
    <a href="display?display=checkContragent" class="buttonsStyle">Показать контрагентов</a><br>
    </div>

    <div class="row">
    <a href="display?display=addProvider" class="buttonsStyle">Добавить поставщика</a><br>
    <a href="display?display=updateProvider" class="buttonsStyle">Изменить поставщиков</a><br>
    <a href="display?display=deleteProvider" class="buttonsStyle">Удалить поставщиков</a><br>
    <a href="display?display=checkProvider" class="buttonsStyle">Показать поставщиков</a><br>
    </div>

</form>
</body>
</html>