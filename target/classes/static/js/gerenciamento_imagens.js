if (window.location.pathname == "/gerenciamento/imagens" ){

document.getElementById("content-for-gen").innerHTML = null;
$("#manager_imagens").append(`
    <div class="container">
        <div class="row">
            <div id="drop_zone" class="dropzone col-9">
                <div class="dz-default dz-message">Arraste aqui as imagens ou clique para escolher!</div>
            </div>
            <div class="col-3">
                <button class="btn btn-success" type="button" id="btn_upload"><i data-feather="upload"></i>Enviar</button>
                <button class="btn btn-danger" type="button" id="btn_cancel"><i data-feather="trash"></i>Remover</button>
            </div>
        </div>
        <br><br>
        <div class="col">
            <div class="row" id="imagesServer"></div>
        </div>
        <br>
    </div>
`);

/*$("#manager_imagens").append(`
<iframe name="back_gen_imagens" style="display:none;"></iframe>
<div class="container">
    <div class="col">
      <div class="row" id="imagesServer"></div>
    </div>
    <br>
    <div class="w-100 input-group">
      <form class="d-flex w-100" target="back_gen_imagens" action="/api/gerenciamento/imagens/upload" method="post" enctype="multipart/form-data">
        <input type="file" class="w-75 form-control form-control-lg" id="photo" name="file" aria-describedby="inputGroupFileAddon04" aria-label="Upload">
        <button class="w-25 btn btn-success" type="submit" value="Upload" style="margin-left:0.9em;">Enviar</button>
      </form>
    </div>
    <br>
</div>
`);*/


function carrega_gen_imagens() {
  $("#imagesServer").empty()
    $.ajax({
      dataType: 'JSON',
      url: '/api/gerenciamento/imagens/load_data',
      type: 'GET',
      success: function(result){
        for (teste of result){
          $("#imagesServer").append(`
            <div class="col-lg-3 col-md-4 col-xs-6 thumb">
                <a href="/static/images/cliente/${teste}" class="fancybox" rel="ligthbox">
                    <img  src="/static/images/cliente/${teste}" class="zoom img-fluid "  alt="">
                </a>
            </div>
          `)
        }
        $(".fancybox").fancybox({
          openEffect: "none",
          closeEffect: "none"
      });
  
      $(".zoom").hover(function(){   
          $(this).addClass('transition');
      }, function(){      
          $(this).removeClass('transition');
      });
      
      },
      error: function(jqXHR, textStatus, errorThrown){
          popup.error();
          console.log(jqXHR.responseJSON.error)
      }
  })
}

  let myDropzone = new Dropzone("div#drop_zone", {
    url: "/api/gerenciamento/imagens/upload",
    autoProcessQueue: false,
    uploadMultiple: false,
    maxFiles: 10,
    paramName: "file",
    method: "post",
    clickable: true,
    complete: function (file) {}
  });
  
$("#btn_upload").click(function () {
  myDropzone.uploadFiles(myDropzone.files)
  carrega_gen_imagens();
  Sweetalert2.fire({
    text:'Upload concluído!',
    icon:'success'
  });
  myDropzone.removeAllFiles();
})

$("#btn_cancel").click(function () {
  myDropzone.removeAllFiles()
})

$(function() {
  carrega_gen_imagens();
});

}