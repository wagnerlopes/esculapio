function listarOCS(lista,valor){
  ocsService.listarOcs(
    {callback:function(dados){
      dwr.util.removeAllOptions(lista);
      dwr.util.addOptions(lista, {'-1':'Selecione um OCS'});
      dwr.util.addOptions(lista, dados, 'id', 'descricao');
      if (valor != null){
        dwr.util.setValue(lista,valor);
      }
    },
    errorHandler:function(errorString, exception){
      alert(errorString + ' - ' + exception.javaClassName + ' - ' + exception.message);
    }});
}

function listarProfissional(lista,valor){
  profissionalService.listarProfissional(
    {callback:function(dados){
      dwr.util.removeAllOptions(lista);
      dwr.util.addOptions(lista, {'-1':'Selecione um Profissional'});
      dwr.util.addOptions(lista, dados, 'id', 'nome');
      if (valor != null){
        dwr.util.setValue(lista,valor);
      }
    },
    errorHandler:function(errorString, exception){
      alert(errorString + ' - ' + exception.javaClassName + ' - ' + exception.message);
    }});
}


function navega(){
  if ($(location).attr('pathname') != '/hospital/pm!comparar.action'){
    $(location).attr('href','pm!comparar.action');
  }
}

function listarPM(desc, lista){
  $('#bc2').text('Procedimento Médico');
  $('#formPM').css('display','block');
  dwr.util.removeAllRows('tabHead');
  dwr.util.removeAllRows('tabBody');
  pmService.listarPorDescricao(desc,
    {callback:function(pm){
      dwr.util.removeAllOptions(lista);
      dwr.util.addOptions(lista, {'-1':'Selecione um Procedimento Médico...'});
      dwr.util.addOptions(lista, pm, 'id', function(x){return x.descricao + ' (' + x.tussf + ')';});
    },
    errorHandler:function(errorString, exception){
      alert(errorString + ' - ' + exception.javaClassName + ' - ' + exception.message);
    }});
}

function listarOcsPM(pmId){
  dwr.util.removeAllRows('tabHead');
  dwr.util.removeAllRows('tabBody');
  ocsPmService.listarOcsPM(pmId, null,
  {callback:function(dados){
    var headFuncs = [
      function() { return '<th>C&oacute;digo</th>'; },
      function() { return '<th>Grupo/SubGrupo</th>'; },
      function() { return '<th>OCS</th>'; },
      function() { return '<th>Valor(R$)</th>'; },
      function() { return '<th>ID</th>';}];
    var cellFuncs = [
      function(x) { return x.pm.tussf; },
      function(x) { return x.pm.grupo + "/" + x.pm.subgrupo; },
      function(x) { return x.ocs.descricao; },
      function(x) { return '<span class="valor">' + x.valor.toFixed(2) + '</span>'; },
      function(x) { return "<input type='radio' id='ocsPmId' name='guia.guiaOcsPm[0].ocsPm.id' value=" + x.id + ">";}];
    dwr.util.addRows("tabHead", [null], headFuncs, {
      rowCreator:function(options) {
        var row = document.createElement("tr");
        return row;
      },
      cellCreator:function(options) {
        var cell = document.createElement("th");
        return cell;
      },
      escapeHtml:false
    });
    dwr.util.addRows("tabBody", dados, cellFuncs, {
      rowCreator:function(options) {
        var row = document.createElement("tr");
        return row;
      },
      cellCreator:function(options) {
        var cell = document.createElement("td");
        if (options.cellNum == 3) {
          cell.style.textAlign = 'right';
        }
        return cell;
      },
      escapeHtml:false
    });
    if ($("#tabBody > tr").length > 0){
      document.getElementById('formDados').style.display='block';
      valorMin();
    } else {
      document.getElementById('formDados').style.display='none';
    }
  },
  errorHandler:function(errorString, exception){
    alert(errorString + ' - ' + exception.javaClassName + ' - ' + exception.message);
	}});
}

function listarDth(desc){
  $('#bc2').text('Diárias e Taxas Hospitalares');
  $('#formPM').css('display','none');
  dwr.util.removeAllRows('tabHead');
  dwr.util.removeAllRows('tabBody');
  dthService.listarPorDesc(desc,
  {callback:function(dados){
    var headFuncs = [
      function() { return '<th>C&oacute;digo</th>'; },
      function() { return '<th>Descri&ccedil;&atilde;o</th>'; },
      function() { return '<th>OCS</th>'; },
      function() { return '<th>Unidade</th>'; },
      function() { return '<th>Valor(R$)</th>';},
      function() { return '<th>ID</th>';}];
    var cellFuncs = [
      function(x) { return x.codigo; },
      function(x) { return x.descricao; },
      function(x) { return x.ocs.descricao; },
      function(x) { return x.unidade; },
      function(x) { return '<span class="valor text-right">' + x.valor.toFixed(2) + '</span>'; },
      function(x) { return "<input type='radio' id='dthId' name='dthId' value=" + x.id + ">";}];
    dwr.util.addRows("tabHead", [null], headFuncs, {
      rowCreator:function(options) {
        var row = document.createElement("tr");
        return row;
      },
      cellCreator:function(options) {
        var cell = document.createElement("th");
        return cell;
      },
      escapeHtml:false
    });
    dwr.util.addRows("tabBody", dados, cellFuncs, {
      rowCreator:function(options) {
        var row = document.createElement("tr");
        return row;
      },
      cellCreator:function(options) {
        var cell = document.createElement("td");
        if (options.cellNum == 4) {
          cell.style.textAlign = 'right';
        }
        return cell;
      },
      escapeHtml:false
    });
    if ($("#tabBody > tr").length > 0){
      document.getElementById('formDados').style.display='block';
      valorMin();
    } else {
      document.getElementById('formDados').style.display='none';
    }
  },
  errorHandler:function(errorString, exception){
    alert(errorString + ' - ' + exception.javaClassName + ' - ' + exception.message);
  }}
  );
}

function preencherBeneficiario(precCp){
  guiaService.listarBeneficiario(precCp,
    {callback:function(ben){
      dwr.util.removeAllRows('tabBen');
      var cellFuncs = [function(b) { return "<strong>Nome:</strong> " + b.nome + " <input type='hidden' name='guia.beneficiario.id' value='" + b.id + "'/>";},
                       function(b) { return "<strong>Dt Nasc:</strong> " + formataData(b.nascimentoData); },
                       function(b) { return "<strong>UG Origem:</strong> " + b.codom;}];
      dwr.util.addRows("tabBen", ben, cellFuncs, {
        rowCreator:function(options) {
          var row = document.createElement("tr");
          return row;
        },
        cellCreator:function(options) {
          var cell = document.createElement("td");
          return cell;
        },
        escapeHtml:false
      });      
     },
     errorHandler:function(errorString, exception){
      alert(errorString + ' - ' + exception.javaClassName + ' - ' + exception.message);
     }
    });
}

function preencherOcs(ocsId, lista, pm){
  ocsService.recuperar(ocsId,
    {callback:function(dados){
      dwr.util.removeAllRows('tabOcs');
      var cellFuncs = [function(b) { return "<strong>OCS/PSA:</strong> " + b.descricao + " <input type='hidden' name='guia.ocs.id' value='" + b.id + "'/>"; },
                       function(b) { return "<strong>CNPJ/CPF:</strong> " + b.cnpjf;}];
      dwr.util.addRows("tabOcs", new Array(dados), cellFuncs, {
        rowCreator:function(options) {
          var row = document.createElement("tr");
          return row;
        },
        cellCreator:function(options) {
          var cell = document.createElement("td");
          return cell;
        },
        escapeHtml:false
      });
      dwr.util.removeAllOptions(lista);
      dwr.util.addOptions(lista, {'-1':'Selecione um Procedimento...'});
      dwr.util.addOptions(lista, dados.ocsPm, 'id', function(x){return x.pm.descricao + " (" + x.pm.tuss + ")";});
      dwr.util.setValue(lista, pm);
      $("#obsLabel").removeClass("hidden").addClass("show");
      $("#obs").removeClass("hidden").addClass("show");
     },
     errorHandler:function(errorString, exception){
      alert(errorString + ' - ' + exception.javaClassName + ' - ' + exception.message);
     }
    });
}

function alterarPM(ocsPmId, qtd){
  ocsPmService.recuperar(ocsPmId,
    {callback:function(dados){
      var cellFuncs = [function(x) { return x.pm.tussf;},
                       function(x) { return x.pm.descricao;},
                       function()  { return qtd;},
                       function(x) { return x.valor.toFixed(2);},
                       function(x) { return '<a href="#" title="Excluir" onclick="$(this).parent().parent().remove();"><span class="icon-trash"></span></a>';}];
      dwr.util.addRows("listaPM", new Array(dados), cellFuncs, {
        rowCreator:function(options) {
          var row = document.createElement("tr");
          return row;
        },
        cellCreator:function(options) {
          var rowCount = $('#tabPM >tbody >tr').length;
          var idx = options.rowNum + rowCount;
          var cell = document.createElement("td");
          if (options.cellNum == 0) {
            var html = options.data + " <input type='hidden' name='guia.guiaOcsPm[" + idx + "].ocsPm.id' value='" + options.rowData.id + "'/>";
            cell.innerHtml = html;
            options.data = html;
          }
          if (options.cellNum == 2) {
            var html = options.data + " <input type='hidden' name='guia.guiaOcsPm[" + idx + "].pmQtd' value='" + qtd + "'/>";
            cell.innerHtml = html;
            options.data = html;
          }
          return cell;
        },
        escapeHtml:false
      });
      document.getElementById('tabPM').style.display='block';
      $("#btnGp").removeClass("hidden").addClass("show");
      },
      errorHandler:function(errorString, exception){
        alert(errorString + ' - ' + exception.javaClassName + ' - ' + exception.message);
      }
    });
}

function formataData(d) {
  var mes = d.getMonth() + 1;
  var t = d.getDate() + "/" + mes + "/" + d.getFullYear();
  return t;
}

function valorMin() {
  var $td = $('#tabBody .valor');
  var min = 9999999.99;
  $.each($td , function(){
    if(parseFloat($(this).text()) < min){
      min = parseFloat($(this).text());
      tdMin = $(this).parent();
      input = $("this + input");
    }});
  $(tdMin).css('font-weight', 'bold');
  $(tdMin).next().find("input").attr("checked",true);
}

