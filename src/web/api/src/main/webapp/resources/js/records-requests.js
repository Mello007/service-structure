$(document).ready(function() {
    var id = window.location.href.split('?')[1];
    var studentTable = document.getElementById('all-items-records-desc');
   
    $.get("/app/structures/" + id, function(data){
        var values = JSON.parse(data.data);
        var idStructure =  document.createElement('th');
        idStructure.setAttribute("id", "recordId");
        idStructure.innerHTML = "id";
        studentTable.appendChild(idStructure);
        var reg = values['properties'];
        for (var property in reg) {
            var idStr = document.createElement('th');
            idStr.innerHTML = property;
            idStr.setAttribute("id", property);
            studentTable.appendChild(idStr);
        }
        var operations = document.createElement('th');
        operations.innerHTML = "Операции";
        operations.setAttribute("id", "operations");
        studentTable.appendChild(operations);
        fillTable(id);
    });
});


function fillTable(id) {
    $.get("/app/structures/" + id + "/records", function(newdata){
        var studentTable = document.getElementById('all-items-records');
        // start iterate for all objects in json
        for (var i = 0; i < newdata.length; i++){
            var recordFromJson = JSON.parse(newdata[i]['data']);
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

function fillRecordsField(){
    var idStructure = window.location.href.split('?')[1];
    var modal = document.getElementById('formAdding');
    $.get("/app/structures/" + idStructure, function(data){
        var values = JSON.parse(data.data);
        var structureColumns = values['properties'];
        for (var column in structureColumns) {
            var name = document.createElement("Label");
            name.setAttribute("for", column);
            name.innerHTML = column +  " :";
            var value = document.createElement("input");
            value.setAttribute("type", "text");
            value.setAttribute("class", "form-control");
            value.setAttribute("id", column);
            modal.appendChild(name);
            modal.appendChild(value);
        }
    });
}


function addNewRecord() {
    var idStructure = window.location.href.split('?')[1];
    var json = {};
    $.get("/app/structures/" + idStructure, function(data){
        var values = JSON.parse(data.data);
        var structureColumns = values['properties'];
        for (var column in structureColumns) {
            json[column] = document.getElementById(column).value;
        }
        $.ajax({
            type: "POST",
            url: "/app/structures/"+ idStructure + "/records",
            contentType: "application/json",
            dataType: 'json',
            data: JSON.stringify(json)
        });
        location.reload();
    });
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


