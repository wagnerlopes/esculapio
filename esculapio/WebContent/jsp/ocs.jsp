<%@page contentType="text/html; charset=ISO-8859-1"%>

<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<tiles:insertDefinition name="layout">

<tiles:putAttribute name="corpo">

  <!-- Breadcrumb -->
  <ul class="breadcrumb">
    <li><s:a namespace="/" action="inicio" title="Início"><i class="fa fa-home fa-lg"></i></s:a></li>
    <li><span class="label label-default">OCS</span></li>
  </ul>

  <div class="page-header">
    <h3>Organiza&ccedil;&otilde;es Conveniadas de Sa&uacute;de (OCS)</h3>
  </div>

  <t:msg />

  <s:if test="!hasActionErrors()">
  
  <table id="example" class="table table-striped">
  <thead>
    <tr>
      <th>CNPJ</th>
      <th>Descri&ccedil;&atilde;o</th>
      <th>Especialidade</th>
      <th>Op&ccedil;&otilde;es</th>
      <th><s:a action="ocs" method="input" title="Inserir OCS">Acrescentar</s:a></th>
    </tr>
    </thead>
    <tbody>
    <s:iterator id="c" value="lista">
      <s:url id="listaGuias" action="ocs" method="guias">
        <s:param name="ocs.id" value="%{id}"/>
      </s:url>
      <s:url id="editar" action="ocs" method="input">
        <s:param name="ocs.id" value="%{id}"/>
      </s:url>
      <s:url id="contratosOCS" action="ocs" method="contratos">
        <s:param name="ocs.id" value="%{id}"/>
      </s:url>
      <s:url id="pmOCS" action="ocs" method="pm">
        <s:param name="ocs.id" value="%{id}"/>
      </s:url>

      <s:url id="dthOcs" action="ocs" method="dth">
        <s:param name="ocs.id" value="%{id}"/>
      </s:url>

      <s:url id="carga" action="ocs" method="carregarPlanilha">
        <s:param name="ocs.id" value="%{id}"/>
        <s:param name="ocs.descricao" value="%{descricao}"/>
      </s:url>
      <tr>
        <td><s:property value="cnpjf"/></td>
        <td><div data-title="${descricao}" data-content="${endereco}, ${numero} - ${complemento} - ${municipio}, ${uf} - Tel: ${telefone}" onmouseover="$(this).popover('show')" onmouseout="$(this).popover('hide')"><s:property value="descricao"/></div></td>
        <td><s:property value="especialidade"/></td>
        <td class="text-center">
          <s:a href="%{listaGuias}" title="Guias de Encaminhamento"><i class="fa fa-files-o"></i></s:a>
          <s:a href="%{contratosOCS}" title="Contratos"><i class="fa fa-folder-open"></i></s:a>
          <s:a href="%{pmOCS}" title="Procedimentos"><i class="fa fa-medkit"></i></s:a>
          <s:a href="%{dthOcs}" title="Diárias e Taxas Hospitalares"><i class="fa fa-hospital-o"></i></s:a>
        </td>
        <td class="text-center">
          <s:a href="%{editar}" title="Editar"><span class="fa fa-wrench"></span></s:a>
          <s:a href="%{carga}" title="Carregar Planilha"><span class="fa fa-upload"></span></s:a>
        </td>
      </tr>
    </s:iterator>
    </tbody>
    <tfoot><tr><td>&nbsp;</td></tr></tfoot>
  </table>

  </s:if>

</tiles:putAttribute>

</tiles:insertDefinition>
