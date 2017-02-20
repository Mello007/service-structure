/**
 * Created by artem on 19.02.17.
 */
function writeTable(jsonRecords) {
    jsonRecords.forEach(function (item) {
        
    })
}


function getRecordsByStructure(id) {
    var json = $.get("example.php")
        .success(function() { alert("Успешное выполнение"); })
        .error(function() { alert("Ошибка выполнения"); })
        .complete(function() { alert("Завершение выполнения"); })
}