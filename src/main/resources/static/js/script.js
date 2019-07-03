


function associarEtiquetas(){
    var checks = $("#etiqueta-item-listar-table").find("input[type='checkbox']:checked");
    var listIdsEtiquetas = [];
    checks.each(function(index,el){
        listIdsEtiquetas.push(parseInt(el.value));
    });
    
    var idItem = parseInt($('#idItem').val());
    
    var post= {
        idItem: idItem,
        listIdsEtiquetas: listIdsEtiquetas
    } 

    var reqRow=[];
    reqRow.push(1);
    reqRow.push(2);
    reqRow.push(7);


    $.ajax({
        type: "POST",
        url: "/item/associar-etiquetas",
        data: {
            idItem: idItem,
            listIdsEtiquetas: listIdsEtiquetas
        },
        timeout: 600000,
        success: function (data) {

            if (data.responseText=="Success")
                alert("Associação realizada com sucesso")
            else
                alert(data.responseText);
                

        },
        error: function (e) {

            if (e.responseText=="Success")
                alert("Associação realizada com sucesso")
            else
                alert(e.responseText);

        }
    });
    
} 
/*
function associarEtiquetas(){
    param ={
        teste : "hahaha"
    };
    $.ajax({
        type: "GET%",
        contentType: "application/json",
        url: "/item/associar-etiquetas2",
        data: param,
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            console.info(data);

        },
        error: function (e) {

            console.info(e);

        }
    });
}
*/