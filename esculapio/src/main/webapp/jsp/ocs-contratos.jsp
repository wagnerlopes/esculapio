<%@page contentType="text/html; charset=ISO-8859-1"%>

<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<s:url id="inserir" action="contrato" method="input">
  <s:param name="contrato.ocs.id" value="%{ocs.id}" />
  <s:param name="contrato.ocs.descricao" value="%{ocs.descricao}" />
</s:url>

<tiles:insertDefinition name="layout">

<tiles:putAttribute name="corpo">

  <!-- Breadcrumb -->
  <ul class="breadcrumb">
    <li><s:a namespace="/" action="inicio" title="Início"><i class="fa fa-home fa-lg"></i></s:a></li>
    <li><span class="label label-default">OCS</span></li>
    <li><span class="label label-default">Contratos</span></li>
  </ul>

  <div class="page-header">
    <h3><s:property value="ocs.descricao"/></h3>  
  </div>
   
  <t:msg/>

  <s:if test="lista.size > 0">
  
  <table class="table table-striped">
    <thead>
    <tr>
      <th class="col-lg-2">Tipo</th>
      <th class="col-lg-2">Vig&ecirc;ncia de</th>
      <th class="col-lg-2">Vig&ecirc;ncia a</th>
      <th class="col-lg-1 text-right">Valor do CH</th>
      <th class="col-lg-1 text-center">Op&ccedil;&otilde;es</th>
    </tr>
    </thead>
    <tbody>
    <s:iterator id="c" value="lista">
      <s:url id="editar" action="contrato" method="input">
        <s:param name="contrato.id" value="%{id}"/>
      </s:url>
      <s:url id="excluir" action="contrato" method="excluir">
        <s:param name="contrato.id" value="%{id}"/>
        <s:param name="contrato.ocs.id" value="%{ocs.id}"/>
      </s:url>
      <tr>
        <td><s:property value="termoTipo.descricao"/></td>
        <td><s:date name="inicioData" format="dd/MM/yyyy" /></td>
        <td><s:date name="finalData" format="dd/MM/yyyy" /></td>
        <td class="text-right">
          <s:property value="%{getText('format.money',{chValor})}"/>
        </td>
        <td class="text-center">
          <s:a href="%{editar}" title="Editar"><span class="fa fa-wrench"></span></s:a>
          <s:a href="%{excluir}" title="Excluir"><span class="fa fa-trash-o"></span></s:a>
        </td>
      </tr>
    </s:iterator>
    </tbody>
    <tfoot><tr><td>&nbsp;</td></tr></tfoot>
  </table>
  
  </s:if>

  <div class="text-center"><s:a href="%{inserir}" title="Inserir Contrato">Novo Termo de Contrato</s:a></div>
  
</tiles:putAttribute>

</tiles:insertDefinition>
