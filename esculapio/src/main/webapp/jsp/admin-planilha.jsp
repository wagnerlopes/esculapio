<%@page trimDirectiveWhitespaces="true"%>

<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<tiles:insertDefinition name="layout">

<tiles:putAttribute name="corpo">

  <!-- Breadcrumb -->
  <ul class="breadcrumb">
    <li><s:a namespace="/" action="inicio" title="Início"><i class="fa fa-home fa-lg"></i></s:a></li>
    <li><span class="label label-default">Administra&ccedil;&atilde;o</span></li>
    <li><span class="label label-default">Carga de Planilha</span></li>
  </ul>

  <t:msg/>

  <blockquote>
    Carrega planilha (arquivo do tipo XLS) contendo lista de OCS ou lista de Profissionais.
  </blockquote>

  <div class="alert alert-warning">
    <strong>Planilha de OCS:</strong> descrição (col A) e CNPJ (col D).
  </div>

  <div class="alert alert-warning">
    <strong>Planilha de Profissionais:</strong> Nome (col A), CRM (col B), UF (col C), Identidade (col D) e Especialidade (col E).
  </div>
  
  <p>&nbsp;</p>

  <s:form cssClass="form-horizontal" namespace="admin" action="ocs!carregarPlanilha" method="post" enctype="multipart/form-data" theme="simple" role="form">
    <div class="form-group">
      <div class="col-lg-6">
        <label class="control-label" for="planilha">Planilha: </label>
        <s:file id="planilha" cssClass="form-control" name="arquivo" size="70" />
      </div>
    </div>
    <div class="form-group">
      <div class="col-lg-6">
        <label class="control-label" for="tipo">Conte&uacute;do: </label>
        <s:radio label="Tipo" name="tipo" list="#{'0':'OCS','1':'Profissionais'}"/>
      </div>
    </div>
    <div class="form-group text-center">
      <button type="submit" class="btn btn-success"><i class="fa fa-upload fa-lg"></i> Enviar</button>
      <button type="reset" class="btn btn-danger"><i class="fa fa-trash-o fa-lg"></i> Limpar</button>
    </div>
  </s:form>

  <p>&nbsp;</p>
    
</tiles:putAttribute>

</tiles:insertDefinition>
