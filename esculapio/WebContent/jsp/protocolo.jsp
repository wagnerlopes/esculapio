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
    <li><span class="label label-default">Protocolo</span></li>
  </ul>
   
  <t:msg />

  <s:if test="lista.size > 0">

  <table class="table table-striped">
    <thead>
      <tr>
        <th>ID</th>
        <th>Assunto</th>
        <th>Data</th>
        <th>OCS</th>
        <th>Doc Tipo</th>
        <th>Doc Nr</th>
        <th>Doc Data</th>
        <th>Valor</th>
        <th>Status</th>
        <th>Op&ccedil;&otilde;es</th>
      </tr>
    </thead>
    <tbody>
      <s:iterator id="c" value="lista">
        <s:url id="listaGuias" action="protocolo" method="guias">
          <s:param name="protocolo.id" value="%{id}"/>
        </s:url>
        <s:url id="editar" action="protocolo" method="input">
          <s:param name="protocolo.id" value="%{id}"/>
        </s:url>
        <s:url id="excluir" action="protocolo" method="excluir">
          <s:param name="protocolo.id" value="%{id}"/>
        </s:url>
      <tr>
        <td><s:property value="id"/></td>
        <td><s:property value="assunto"/></td>
        <td><s:property value="data"/></td>
        <td><s:property value="ocs.descricao"/></td>
        <td><s:property value="docTipo"/></td>
        <td><s:property value="docNr"/></td>
        <td><s:property value="docData"/></td>
        <td><s:text name="format.money"><s:param name="valor" value="valor"/></s:text></td>
        <td><s:property value="%{#application.STATUS[status]}"/></td>
        <td class="text-center">
          <s:a href="%{listaGuias}" title="Guias de Encaminhamento"><i class="fa fa-files-o"></i></s:a>
          <s:a href="%{editar}" title="Editar"><span class="fa fa-wrench"></span></s:a>
          <s:a href="%{excluir}" title="Excluir" onclick="return confirm('Excluir o documento?')"><span class="fa fa-trash-o"></span></s:a>
        </td>
      </tr>
      </s:iterator>
    </tbody>
  </table>

  </s:if>

  <div class="text-center"><s:a action="protocolo" method="input" title="Inserir Novo Protocolo">Novo Protocolo</s:a></div>
  
</tiles:putAttribute>

</tiles:insertDefinition>
