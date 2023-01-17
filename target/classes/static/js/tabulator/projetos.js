//Usuario: Nome, e-mail,  senha, telefone, endereço, cidade e estado
let tabledata = [
    { id: 1, nome: "Oli Bob", descricao: "descricao aqui", thumbnail:"logopng", image: "logopng", galeria:"1,2,3"},
    { id: 2, nome: "Mary May", descricao: "descricao aqui", thumbnail:"logopng", image: "logopng", galeria:"1,2,3"},
    { id: 3, nome: "Christine Lobowski", descricao: "descricao aqui", thumbnail:"logopng", image: "logopng", galeria:"1,2,3"},
    { id: 4, nome: "Brendon Philips", descricao: "descricao aqui", thumbnail:"logopng", image: "logopng", galeria:"1,2,3"},
    { id: 5, nome: "Margret Marmajuke", descricao: "descricao aqui", thumbnail:"logopng", image: "logopng", galeria:"1,2,3"},
];

let table_database = "projets";
let forAddRow = {name: "", description: "", photo: "", gallery: ""}
let teste = ''

//Build Tabulator
let table = new Tabulator("#table", {
    height: "auto",
    layout: "fitColumns",
    ajaxURL:"/api/projects/findAll",
    ajaxConfig:"POST",
    ajaxContentType:"json",
    selectable:true,
    history:true, //record table history
    reactiveData: true, //turn on data reactivity
    columns: [
        { title: "Id", field: "id", width: "50", sorter: "number"},
        { title: "Nome", field: "name", sorter: "string", editor: "input", cellEdited:function(cell){
            let valor = cell.getValue()
            let coluna = cell.getField()
            let id = cell.getRow()._row.data.id
            update_bd(coluna,table_database,id,valor)
        },},
        { title: "Descrição", field: "description", sorter: "string", editor: "input", cellEdited:function(cell){
            let valor = cell.getValue()
            let coluna = cell.getField()
            let id = cell.getRow()._row.data.id
            update_bd(coluna,table_database,id,valor)
        },},
        { title: "thumbnail", field: "photo", sorter: "string", editor: "input", cellEdited:function(cell){
            let valor = cell.getValue()
            let coluna = cell.getField()
            let id = cell.getRow()._row.data.id
            update_bd(coluna,table_database,id,valor)
        },},
        { title: "Foto", field: "photo", formatter:"image", editor: "image",formatterParams:{ height:"50px", width:"50px", urlPrefix: window.location.origin + "/static/images/cliente/", urlSuffix:".png"}},
        { title: "Galeria", field: "gallery", sorter: "string", editor: "input", cellEdited:function(cell){
            let valor = cell.getValue()
            let coluna = cell.getField()
            let id = cell.getRow()._row.data.id
            update_bd(coluna,table_database,id,valor)
        },},
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


$(document).ready(function () {
    toastr.options = {
        "closeButton": true,
        "debug": false,
        "newestOnTop": false,
        "progressBar": true,
        "positionClass": "toast-bottom-right",
        "preventDuplicates": true,
        "onclick": null,
        "showDuration": "300",
        "hideDuration": "1000",
        "timeOut": "5000",
        "extendedTimeOut": "1000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
        }
})

