<%@page contentType="text/html; charset=ISO-8859-1"%>

<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<tiles:insertDefinition name="layout">

<tiles:putAttribute name="corpo">

  <!-- Breadcrumb -->
  <ul class="breadcrumb">
    <li><a href='<s:url namespace="/" action="inicio"/>' title="Início"><i class="fa fa-home fa-lg"></i></a></li>
    <li><span class="label label-default">Guia de Encaminhamento</span></li>
  </ul>

  <t:msg />

  <p>&nbsp;</p>  
 
  <s:form action="guia!salvar" theme="simple" role="form">

  <div class="row">
    <div class="col-lg-9"></div>
    <div class="col-lg-3">
      <div class="input-group input-group-sm">
        <input type="text" id="prec" class="form-control" title="Informe o PREC/CP do Beneficiário do FUSEx" placeholder="PREC/CP">
        <span class="input-group-btn">
          <button class="btn btn-default" type="button" onclick="preencherBeneficiario(document.getElementById('prec').value);"><i class="fa fa-user fa-lg"></i></button>
        </span>
      </div>
    </div>
  </div>
  <table class="table table-bordered">
    <thead>
      <tr>
        <td colspan="3">
          <strong>BENEFICI&Aacute;RIO:</strong>
        </td>
      </tr>
    </thead>
    <tbody id="tabBen"></tbody>
  </table>
    
  <div class="row">
    <div class="col-lg-6"></div>
    <div class="col-lg-6">
      <div class="input-group input-group-sm">
        <span class="input-group-addon"><i class="fa fa-h-square fa-lg"></i></span>
        <s:select id="listaOcs" cssClass="form-control" list="#{'-1','Selecione'}" listKey="id" listValue="descricao" onchange="preencherOcs(this.value,document.getElementById('listaOcsPm'));"/>
      </div>
    </div>
  </div>
  <table class="table table-bordered">
    <thead>
      <tr>
        <td colspan="2"><strong>PRESTADOR DE SERVI&Ccedil;O:</strong></td>
      </tr>
    </thead>
    <tbody id="tabOcs"></tbody>
  </table>

  <p>&nbsp;</p>  
  
  <div class="row">
    <div class="col-lg-6">
      <label for="listaPS">SOLICITANTE:</label>
      <div class="input-group">
        <span class="input-group-addon"><i class="fa fa-user-md fa-lg"></i></span>
        <s:select id="listaPS" name="guia.solicitante.id" cssClass="form-control" list="#{'-1','Selecione'}" listKey="id" listValue="nome"/>
      </div>
    </div>
    <div class="col-lg-6">
      <label for="listaPR">RESPONS&Aacute;VEL:</label>
      <div class="input-group">
        <span class="input-group-addon"><i class="fa fa-stethoscope fa-lg"></i></span>
        <s:select id="listaPR" name="guia.responsavel.id" cssClass="form-control" list="#{'-1','Selecione'}" listKey="id" listValue="nome"/>
      </div>
    </div>
  </div>

  <p>&nbsp;</p>  

  <div id="pm" class="row" style="display:none">
    <div class="col-lg-10">
      <label class="control-label" for="listaPM">Procedimentos M&eacute;dicos: </label>
      <div class="input-group">
        <span class="input-group-addon"><i class="fa fa-medkit fa-lg"></i></span>
        <s:select id="listaOcsPm" cssClass="form-control" emptyOption="false" list="#{'-1':'Pesquise Procedimentos...'}" />
      </div>
    </div>
    <div class="col-lg-2">
      <label class="control-label" for="pmQtd">Qtd: </label>
      <div class="input-group">
        <s:textfield id="pmQtd" cssClass="form-control" maxlength="2" value="1"/>
        <span class="input-group-btn">
          <s:a href="#" cssClass="btn btn-success" onclick="alterarPM(document.getElementById('listaOcsPm').value,document.getElementById('pmQtd').value);"><i class="icon-plus"></i> Incluir</s:a>
        </span>
      </div>
    </div>
  </div>

  <p>&nbsp;</p>
  
  <table id="tabPM" class="well table table-striped" style="display:none">
    <thead>
      <tr>
        <th class="text-center">C&oacute;digo</th>
        <th class="text-center">Procedimento</th>
        <th class="text-center">Qtd</th>
        <th class="text-center">Valor(R$)</th>
        <th class="text-center">Excluir</th>
      </tr>
    </thead>
    <tbody id="listaPM"></tbody>
  </table>


  <div class="form-group">
    <label id="obsLabel" class="control-label hidden" for="obs">Observa&ccedil;&atilde;o: </label>
    <s:textarea id="obs" cssClass="form-control hidden" name="guia.observacao" cols="80"></s:textarea>
  </div>
  
  <div id="btnGp" class="form-group text-center hidden">
    <button type="submit" class="btn btn-success"><i class="fa fa-check fa-lg"></i> Salvar</button>
    <a class="btn btn-danger"  href='<s:url namespace="/" action="inicio"/>'><i class="fa fa-trash-o fa-lg"></i> Cancelar</a>
  </div>

  </s:form>
  
  <script type="text/javascript" src="/hospital/dwr/interface/ocsService.js"></script>
  <script type="text/javascript" src="/hospital/dwr/interface/profissionalService.js"></script>
  <script type="text/javascript" src="/hospital/dwr/interface/guiaService.js"></script>
  <script type="text/javascript">
    <c:choose>
      <c:when test="${not empty guia.ocs.id}">
        listarOCS(document.getElementById('listaOcs'),${guia.ocs.id});
        preencherOcs(${guia.ocs.id},document.getElementById('listaOcsPm'),${ocsPm.id});
      </c:when>
      <c:otherwise>
        listarOCS(document.getElementById('listaOcs'),null);
      </c:otherwise>
    </c:choose>
    listarProfissional(document.getElementById('listaPS'),null);
    listarProfissional(document.getElementById('listaPR'),null);
  </script>
    
</tiles:putAttribute>

</tiles:insertDefinition>
