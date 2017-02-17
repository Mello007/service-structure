
var globalvar = '';
$(document).ready(function() {
    $('.dropdown-menu-kind li a').click(function(){
        globalvar = $(this).data('val');
    })
});
function addNewItem() {
    var name = $('#itemName').val();
    var requestJSONparametr = "{\"itemName\": \"" + name + "\", \"itemKind\": \"" + globalvar + "\"}";
    $.ajax({
        type: "POST",
        url: "/item/add",
        contentType: "application/json",
        dataType: 'json',
        data: requestJSONparametr,
        success: function (data) {
            alert("Предмет успешно добавлен!");
        },
        error: function (data) {
            alert("Не удалось добавить предмет! Что-то пошло не так, попробуйте еще раз");
        }
    });
}


var x = new XMLHttpRequest();
x.open("GET", "/app/structures", true);  //Указываем адрес GET-запроса
x.onload = function (){ //Функция которая отправляет запрос на сервер для получения всех студентов
    var parsedItem = JSON.parse(this.responseText); //указываем что
    var studentTable = document.getElementById('all-items'); //получаем данные на странице по Id  - all-student
    parsedItem.forEach(function(item)  { //запускаем цикл
        var idStructure = document.createElement('td'); //создаем элемент td для таблицы
        idStructure.innerHTML =  item['id'] ; //внедряем имя студента из БД
        var structureName = document.createElement('td');
        structureName.innerHTML = item['itemPrice'];//создаем элемент td для таблицы
        var operations = document.createElement('td');
        operations.innerHTML =
            '<button class="btn">Просмотреть структуру</button> </br> ' +
            '<button class="btn">Просмотреть записи</button>' +
            '<button class="btn">Удалить</button>';
        var elementContainer = document.createElement('tr'); //создаем тег
        elementContainer.appendChild(idStructure);
        elementContainer.appendChild(structureName);
        elementContainer.appendChild(operations);
        studentTable.appendChild(elementContainer);
    });
    //подключаем к таблице библиотеку для сортировки
};
x.send(null);

setInterval(x, 50000);


$(document).ready(function() {
    $('.dropdown-menu li a').click(function(){
        var val_cur = $(this).data('val');
        var requestJSONparametr = "{\"itemCurr\": \"" + val_cur + "\"}";
        $.ajax({
            type: "POST",
            url: "/item/curr",
            contentType: "application/json",
            dataType: 'json',
            data: requestJSONparametr,
            success: function (data) {
                alert("Цена установлена");
            },
            error: function (data) {
                alert("Не удалось установить цену!");
            }
        });
    });
});