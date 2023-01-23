function carrega_fotos_projetos() {
    $.ajax({
        dataType: 'JSON',
        url: '/api/projects/findAll',
        type: 'POST',
        success: function (result) {
            result.map(function (teste, index){
                let id = 'projeto_' + index
        
                $("#projetos-page").append(`
                <div class="col item" id="${id}">
                    <div class="card text-white mb-3" style="background-color: #233a3794">
                        <img src="static/images/cliente/${teste.photo}.png" class="card-img-top p-2 rounded" alt="..." style="height:20em;">
                            <div class="card-body">
                                <h5 class="card-title">${teste.name}<a class="btn stretched-link""></a></h5>
                                <p class="card-text">${teste.description}.</p>
                            </div>
                    </div>
                </div>
                `)
        
                document.getElementById(id).addEventListener('click', function() {
                    window.location.href = window.location.origin +  "/api/project?" + new URLSearchParams({id:teste.id}).toString();
                })
            })
        },
        error: function (jqXHR, textStatus, errorThrown) {
            popup.error();
            console.log(jqXHR.responseJSON.error)
        }
    })
}

$(function () {
if( window.location.pathname == "/projetos"){
    carrega_fotos_projetos()
}
});