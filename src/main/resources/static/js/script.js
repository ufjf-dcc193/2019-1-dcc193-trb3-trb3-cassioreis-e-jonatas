


function associarEtiquetasItem(){
    var checks = $("#etiqueta-item-listar-table").find("input[type='checkbox']:checked");
    if (checks.length==0){
        alert("Selecione ao menos um item");
        return;
    }

    var listIdsEtiquetas = [];
    checks.each(function(index,el){
        listIdsEtiquetas.push(parseInt(el.value));
    });
    
    var idItem = parseInt($('#idItem').val());
    
    var param = {
        idItem: idItem,
        listIdsEtiquetas: listIdsEtiquetas
    }
    var url = "/item/associar-etiquetas";
    associarEtiquetas(param,url);
}


function associarEtiquetasVinculo(){
    var checks = $("#etiqueta-vinculo-listar-table").find("input[type='checkbox']:checked");
    var listIdsEtiquetas = [];
    checks.each(function(index,el){
        listIdsEtiquetas.push(parseInt(el.value));
    });
    
    var idVinculo = parseInt($('#idVinculo').val());
    
    var param = {
        idVinculo: idVinculo,
        listIdsEtiquetas: listIdsEtiquetas
    }
    var url = "/vinculo/associar-etiquetas";
    associarEtiquetas(param,url);
}

function associarEtiquetas(param, url){
    
    $.ajax({
        type: "POST",
        url: url,
        data: param,
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
