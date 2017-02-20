function addNewStructure() {
    var data = $('#json').val();
    $.ajax({
        type: "POST",
        url: "/app/structures",
        contentType: "application/json",
        dataType: 'json',
        data: data
    });
    location.reload();
}

function deleteStructure(id) {
    $.ajax({
        type: "DELETE",
        url: "/app/structures/" + id,
        contentType: "application/json",
        dataType: 'json',
        data: id
    });
    location.reload();
}

function showRecords(id) {
    window.open("resources/records.html?" + id);

}

var idStructure = '';
function openStructure(id) {
    idStructure = id;
    $.get("/app/structures/" + id, function(data){
        $('#structureEdit').modal();
        var structure = document.getElementById('jsonEdit');
        structure.value = JSON.stringify([data.data]);
    });
}

function updateStructure() {
    var data = $('#jsonEdit').val();
    $.ajax({
        type: "PUT",
        url: "/app/structures/" + idStructure,
        contentType: "application/json",
        dataType: 'json',
        data: data
    });
    location.reload();
}

$(document).ready(function() {
    var x = new XMLHttpRequest();
    x.open("GET", "/app/structures", true);  //Указываем адрес GET-запроса
    x.onload = function () { //Функция которая отправляет запрос на сервер для получения всех студентов
        var parsedItem = JSON.parse(this.responseText); //указываем что
        var studentTable = document.getElementById('all-items'); //получаем данные на странице по Id  - all-student
        parsedItem.forEach(function (item) { //запускаем цикл
            var json = JSON.parse(item['data']);
            var idStructure = document.createElement('td'); //создаем элемент td для таблицы
            idStructure.innerHTML = item['id']; //внедряем имя студента из БД
            var structureName = document.createElement('td');
            structureName.innerHTML = json.title;//создаем элемент td для таблицы
            var operations = document.createElement('td');
            operations.innerHTML =
                ' <button class="btn btn-primary btn-xs" onclick="openStructure(\'' + item['id'] + '\')">Просмотреть структуру</button> ' +
                '<button class="btn btn-primary btn-xs" onclick="showRecords(\'' + item['id'] + '\')">Просмотреть записи</button> ' +
                '<button class="btn btn-primary btn-xs" onclick="deleteStructure(\'' + item['id'] + '\')">Удалить</button>';
            var elementContainer = document.createElement('tr'); //создаем тег
            elementContainer.appendChild(idStructure);
            elementContainer.appendChild(structureName);
            elementContainer.appendChild(operations);
            studentTable.appendChild(elementContainer);
        });
    };
    x.send(null);
});

