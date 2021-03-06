<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 06.09.2020
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <title>Hello, world!</title>
</head>
<body>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
    function validate() {
        const name = $('#username').val()
        const phone = $('#phone').val()
        if (name == '' || phone == '') {
            alert("Please fill the form field")
            return false
        }
        return true
    }

    function sendPost() {
        if (validate()) {
            $.ajax({
                type: 'POST',
                url: 'http://localhost:8080/cinema/payment',
                data: {
                    row: <%=request.getParameter("row")%>,
                    seat: <%=request.getParameter("seat")%>,
                    name: $('#username').val(),
                    phone: $('#phone').val()
                },
                dataType: "json"
            }).done(function (data) {
                const div = document.createElement('div')
                div.innerText = 'Ряд: ' + data['row'] + ' Место: ' + data['seat']
                document.getElementById('form').appendChild(div)
            }).fail(function (err) {
                alert(err);
                console.log(err)
            });
        }
    }
</script>
<div class="container">
    <div class="row pt-3">
        <h3>
            Вы выбрали ряд <%=request.getParameter("row")%> место <%=request.getParameter("seat")%>, Сумма : 500 рублей.
        </h3>
    </div>
    <div class="row">
        <form id="form">
            <div class="form-group">
                <label for="username">ФИО</label>
                <input type="text" class="form-control" id="username" placeholder="ФИО">
            </div>
            <div class="form-group">
                <label for="phone">Номер телефона</label>
                <input type="text" class="form-control" id="phone" placeholder="Номер телефона">
            </div>
            <button type="button" class="btn btn-success" onclick="sendPost()">Оплатить</button>
        </form>
    </div>
</div>
</body>
</html>
