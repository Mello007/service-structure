
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
        var structure = '';
        structure = JSON.stringify([data.type]);
        alert(structure);
        structure.forEach(function (item) {
            var idStructure = document.createElement('th');
            idStructure.innerHTML = item['description'];
            studentTable.appendChild(idStructure);
        })
    });

    
    // $.get("/app/structures/" + id, function(data){
    //     structure.value = JSON.stringify([data.data]);
    // });
    //
    // $.get("/app/structures/" + id + "/records", function(data){
    //     records = JSON.parse(data.data);
    // });


    
});


