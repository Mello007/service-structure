
var StructureId = "";

// function getRecordsByStructure(id) {
//     var json = $.get("example.php")
//         .success(function() { alert("Успешное выполнение"); })
//         .error(function() { alert("Ошибка выполнения"); })
//         .complete(function() { alert("Завершение выполнения"); })
// }


$(document).ready(function() {
    var id = window.location.href.split('?')[1];
    var studentTable = document.getElementById('all-items-records-desc');
    $.get("/app/structures/" + id, function(data){
        var values = JSON.parse(data.data);
        var id =  document.createElement('th');
        id.setAttribute("id", "recordId");
        id.innerHTML = "id";
        studentTable.appendChild(id);
        var reg = values['properties'];
        for (var property in reg) {
            var idStructure = document.createElement('th');
            idStructure.innerHTML = property;
            idStructure.setAttribute("id", property);
            studentTable.appendChild(idStructure);
        }
        var operations = document.createElement('th');
        operations.innerHTML = "Операции";
        operations.setAttribute("id", "operations");
        studentTable.appendChild(operations);
    });
    fillTable(id);
});


function fillTable(id) {
    $.get("/app/structures/" + id + "/records", function(newdata){
        var studentTable = document.getElementById('all-items-records');
        // start iterate for all objects in json
        for (var i = 0; i < newdata.length; i++){
            var recordFromJson = JSON.parse(newdata[i]['data']);
            console.log(recordFromJson);
            
                var recordId = document.createElement('td');
                recordId.innerHTML = JSON.parse(newdata[i]['id']);
                studentTable.appendChild(recordId);
                for (var key in recordFromJson) {
                        var item = document.createElement('td');
                        item.innerHTML = recordFromJson[key];
                        studentTable.appendChild(item);
                }
            
            
            
            var operations = document.createElement('td');
            operations.innerHTML =
                ' <button class="btn btn-primary btn-xs" onclick="openRecord(\'' + JSON.parse(newdata[i]['id']) + '\')">Просмотреть запись</button> '  +
                '<button class="btn btn-primary btn-xs" onclick="deleteRecord(\'' + JSON.parse(newdata[i]['id']) + '\')">Удалить</button>';
            var elementContainer = document.createElement('tr');
            studentTable.appendChild(operations);
            studentTable.appendChild(elementContainer);
        }
    });

}

function addNewRecord() {
    var id = window.location.href.split('?')[1];
    var data = $('#recordJson').val();
    $.ajax({
        type: "POST",
        url: "/app/structures/"+ id + "/records",
        contentType: "application/json",
        dataType: 'json',
        data: data
    });
    location.reload();
}

function deleteRecord(id) {
    $.ajax({
        type: "DELETE",
        url: "/app/structures/records/" + id,
        contentType: "application/json",
        dataType: 'json',
        data: id
    });
    location.reload();
}


var idRecord = '';
function openRecord(id) {
    idRecord = id;
    $.get("/app/structures/records/" + id, function(data){
        $('#recordEdit').modal();
        var record = document.getElementById('newRecord');
        record.value = JSON.stringify(data.data);
    });
}

function updateRecord() {
    var data = JSON.parse($('#newRecord').val());
    $.ajax({
        type: "PUT",
        url: "/app/structures/records/" + idRecord,
        contentType: "application/json",
        dataType: 'json',
        data: data
    });
    location.reload();
}


