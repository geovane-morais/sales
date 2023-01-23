if (window.location.pathname == "/gerenciamento/produtos" ){

let table_database = "produits";
let forAddRow = {name: "", pricePerMeter: "", pathImage: "", isAvailable: "", description: ""}
const disponivel = {"1":"disponivel","0":"indisponivel"};

//Build Tabulator
let table = new Tabulator("#table", {
    height: "auto",
    layout: "fitColumns",
    ajaxURL:"/api/products/findAll",
    ajaxConfig:"POST",
    ajaxContentType:"json",
    selectable:true,
    history:true,
    reactiveData: true,
    columns: [
        { title: "Id", width: "50", field: "id", sorter: "number" },
        { title: "Nome", field: "name", sorter: "string", editor: "input", cellEdited:function(cell){
            let valor = cell.getValue()
            let coluna = cell.getField()
            let id = cell.getRow()._row.data.id
            update_bd(coluna,table_database,id,valor)
        }, },
        { title: "Descriçao", field: "description", width: "200", sorter: "number", editor: "input", cellEdited:function(cell){
            let valor = cell.getValue()
            let coluna = cell.getField()
            let id = cell.getRow()._row.data.id
            update_bd(coluna,table_database,id,valor)
        }, },
        { title: "Preço x Metro", field: "pricePerMeter", sorter: "number", editor: "input", cellEdited:function(cell){
            let valor = cell.getValue()
            let coluna = cell.getField()
            let id = cell.getRow()._row.data.id
            update_bd(coluna,table_database,id,valor)
        }, },
        { title: "thumbnail", field: "pathImage", sorter: "string", editor: "input", cellEdited:function(cell){
            let valor = cell.getValue()
            let coluna = cell.getField()
            let id = cell.getRow()._row.data.id
            update_bd(coluna,table_database,id,valor)
        }, },
        { title: "Foto", field: "pathImage", formatter:"image", editor: "image",formatterParams:{ height:"50px", width:"50px", urlPrefix: window.location.origin + "/static/images/cliente/", urlSuffix:".png"}},
        { title: "Disponibilidade", field: "isAvailable", sorter: "string", editor: "list", editorParams: { values: disponivel }, cellEdited:function(cell){
            let valor = cell.getValue()
            let coluna = cell.getField()
            let id = cell.getRow()._row.data.id
            update_bd(coluna,table_database,id,valor)
        },  },
    ],
});

function update_bd(coluna,table_database,id,valor) {
    $.ajax({
    data: {'coluna': coluna,
            'table_database': table_database,
            'id': id,
            'valor': valor,
        },
    dataType: 'JSON',
    url: '/api/gerenciamento/update',
    type: 'POST',
    success: function(result){
        toastr["success"](coluna+" alterado!")
    },
    error: function(jqXHR, textStatus, errorThrown){
        popup.error();
        console.log(jqXHR.responseJSON.error)
    }
})
}

$("#btn-delete-table").click(function () {
    let lista_row = []
    let data = {'rows': '',
                'table': table_database}
    for (row of table.getSelectedRows()) {
        data['rows']+= row._row.data.id+","
        lista_row.push(row)
    }
    $.ajax({
        data: data,
        dataType: 'JSON',
        url: '/api/gerenciamento/delete',
        type: 'POST',
        success: function(result){
            toastr["success"]("Registro(s) excluído(s)!")
            table.deleteRow(lista_row)
        },
        error: function(jqXHR, textStatus, errorThrown){
            popup.error();
            console.log(jqXHR.responseJSON.error)
        }
    })
})

$("#btn-add-table").click(function () {
    len_colunas = table.getColumns().length
    $.ajax({
        data: {'table': table_database,
                'colunas': len_colunas},
        dataType: 'JSON',
        url: '/api/gerenciamento/insert',
        type: 'POST',
        success: function(result){
            table.replaceData("/api/projects/findAll")
            toastr["success"]("Linha Adicionada com sucesso!")
        },
        error: function(jqXHR, textStatus, errorThrown){
            popup.error();
            console.log(jqXHR.responseJSON.error)
        }
    })
})

}