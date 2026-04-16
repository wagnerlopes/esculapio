<%@page trimDirectiveWhitespaces="true"%>

<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<tiles:insertDefinition name="layout">

<tiles:putAttribute name="corpo">

  <!-- Breadcrumb -->
  <ul class="breadcrumb">
    <li><s:a namespace="/" action="inicio" title="Início"><i class="fa fa-home fa-lg"></i></s:a></li>
    <li><span class="label label-default">OCS</span></li>
    <li><span class="label label-default">Carga de Procedimentos M&eacute;dicos</span></li>
  </ul>

  <t:msg/>

  <div class="page-header">
    <h3><s:property value="ocs.descricao"/></h3>  
  </div>
  
  <blockquote>
    Carga de planilha (arquivo XLS) contendo Procedimentos Médicos ou Taxas e Diárias Hospitalares.
  </blockquote>

  <div class="alert alert-warning">
    <strong>Procedimentos M&eacute;dicos:</strong> c&oacute;digo, descri&ccedil;&atilde;o e valor.
  </div>

  <div class="alert alert-warning">
    <strong>Di&aacute;ria e Taxas Hospitalares:</strong> c&oacute;digo, descri&ccedil;&atilde;o, unidade e valor.
  </div>
  
  <s:form cssClass="form-horizontal" action="ocs!carregarPlanilha" method="post" enctype="multipart/form-data" theme="simple" role="form">
    <div class="form-group">
      <div class="col-lg-6">
        <label class="control-label" for="planilha">Planilha: </label>
        <s:file id="planilha" cssClass="form-control" name="arquivo" size="70" />
        <s:hidden name="ocs.id"/>
      </div>
    </div>
    <div class="form-group">
      <div class="col-lg-6">
        <label class="control-label" for="tipo">Conte&uacute;do: </label>
        <s:radio label="Tipo" name="tipo" list="#{'0':'Procedimentos Médicos','1':'Diárias e Taxas Hospitalares'}"/>
      </div>
    </div>
    <div class="form-group text-center">
      <s:submit cssClass="btn btn-success" value="Enviar"/>
      <s:reset  cssClass="btn btn-inverse" value="Limpar"/>
    </div>
  </s:form>

  <p>&nbsp;</p>
    
</tiles:putAttribute>

</tiles:insertDefinition>
