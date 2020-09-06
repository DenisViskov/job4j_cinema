<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style>
        .taken {
            background: #ff0000
        }
    </style>


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
    window.onload = function getData() {
        $.ajax({
            type: 'GET',
            url: '<%=request.getContextPath()%>/index',
            data: {req: 'request from index'},
            dataType: 'json'
        }).done(function (data) {
            getContent(data)
        }).fail(function (error) {
            alert(error)
        })
    }

    function getContent(data) {
        for (row in data) {
            let addRow = document.createElement('th')
            addRow.innerHTML = row
            document.getElementById('row').append(addRow)
            let innerRow = document.createElement('tr')
            document.getElementById('body').append(innerRow)
            innerRow.appendChild(addRow)
            for (seat in data[row]) {
                var placeInHall = data[row]
                if (placeInHall[seat] == true) {
                    let position = document.createElement('td')
                    position.innerHTML =
                        '<div><input type="radio"' + ' name=' + row + ' value='
                        + seat + '>' + 'Ряд' + ' '
                        + row + ',' + 'Место'
                        + ' ' + seat + '</div>'
                    innerRow.appendChild(position)
                } else {
                    let position = document.createElement('td')
                    position.innerHTML =
                        '<div class=' + 'taken' + '>' + '<input type="radio"'
                        + ' name=' + row + ' value='
                        + seat + '>' + 'Ряд' + ' '
                        + row + ',' + 'Место'
                        + ' ' + seat + '</div>'
                    innerRow.appendChild(position)
                }
            }
        }
    }
</script>

<div class="container">
    <div class="row pt-3">
        <h4>
            Бронирование мест на сеанс
        </h4>
        <table class="table table-bordered">
            <thead>
            <tr id="row">
                <th style="width: 120px;">Ряд / Место</th>
            </tr>
            </thead>
            <tbody id="body">
            </tbody>
        </table>
    </div>
    <div class="row float-right">
        <button type="button" class="btn btn-success">Оплатить</button>
    </div>
</div>
</body>
</html>