<%-- $Id$ --%>

<%@page trimDirectiveWhitespaces="true"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<tiles:insertDefinition name="layout">

<tiles:putAttribute name="corpo">

  <!-- Breadcrumb -->
  <ul class="breadcrumb">
    <li><s:a namespace="/" action="inicio" title="Início"><i class="fa fa-home fa-lg"></i></s:a></li>
    <li><span class="label label-default">OCS</span></li>
    <li><span class="label label-default">Contrato</span></li>
  </ul>

  <t:msg />
  
  <div class="page-header">
    <h3><s:property value="contrato.ocs.descricao"/></h3>
  </div>

  <s:form cssClass="form-horizontal" namespace="/" action="contrato!salvar" theme="simple">
    <div class="form-group">
      <label class="col-lg-2 control-label" for="tipo">Tipo de Contrato</label>
      <div class="col-lg-3">
        <s:hidden key="contrato.id" value="%{contrato.id}"/>
        <s:hidden key="contrato.ocs.id" value="%{contrato.ocs.id}"/>
        <s:select cssClass="form-control" key="contrato.termoTipo.codigo" list="#application.TIPO_TERMO" listKey="codigo" listValue="descricao" />
      </div>
    </div>
    <div class="form-group">
      <label class="col-lg-2 control-label" for="inicio">Vig&ecirc;ncia de</label>
      <t:calendario nome="contrato.inicioData" lancador="inicio"/>
    </div>
    <div class="form-group">
      <label class="col-lg-2 control-label" for="final">Vig&ecirc;ncia &agrave;</label>
      <t:calendario nome="contrato.finalData" lancador="final"/>
    </div>
    <div class="form-group">
      <label class="col-lg-2 control-label" for="valorCH">Valor do CH (R$)</label>
      <div class="col-lg-2">
        <s:textfield id="valorCH" cssClass="form-control" name="contrato.chValor"/>
      </div>
    </div>
    <div align="center" class="form-group">
      <button type="submit" class="btn btn-success"><i class="fa fa-check fa-lg"></i> Salvar</button>
      <a class="btn btn-danger"  href='<s:url namespace="/" action="ocs" method="listar"/>'><i class="fa fa-trash-o fa-lg"></i> Cancelar</a>
    </div>
  </s:form>

</tiles:putAttribute>

</tiles:insertDefinition>
