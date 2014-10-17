<%@page contentType="text/html; charset=ISO-8859-1"%>

<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<tiles:insertDefinition name="layout">

<tiles:putAttribute name="corpo">

  <!-- Breadcrumb -->
  <ul class="breadcrumb">
    <li id="bc1"><a href='<s:url namespace="/" action="inicio"/>' title="Início"><i class="fa fa-home fa-lg"></i></a></li>
    <li><span id="bc2" class="label label-default">Procedimentos M&eacute;dicos</span></li>
    <li><span id="bc3" class="label label-default">Compara&ccedil;&atilde;o</span></li>
  </ul>

  <t:msg />

  <s:form id="formPM" cssClass="form-inline" theme="simple">
    <div class="form-group">
      <label class="control-label" for="listaPM">Procedimento M&eacute;dico: </label>
      <s:select id="listaPM" cssClass="form-control" emptyOption="false" list="#{'-1':'Pesquise Procedimentos...'}" onchange="listarOcsPM(this.value);"/>
    </div>
  </s:form>

  <p>&nbsp;</p>

  <s:form id="formDados" cssClass="form-horizontal" cssStyle="display:none" namespace="/" action="guia!input" theme="simple">
    <table class="table table-striped">
      <thead id="tabHead"></thead>
      <tbody id="tabBody"></tbody>
    </table>
    <div class="form-group text-center">
      <button type="submit" class="btn btn-success"><i class="fa fa-check fa-lg"></i> Salvar</button>
      <a class="btn btn-danger"  href='<s:url namespace="/" action="inicio"/>'><i class="fa fa-trash-o fa-lg"></i> Cancelar</a>
    </div>
  </s:form>

</tiles:putAttribute>

</tiles:insertDefinition>
