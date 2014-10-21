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
    <li><span class="label label-default">Benefici&aacute;rios</span></li>
  </ul>

  <t:msg/>   
  
  <table class="table table-striped">
    <thead>
      <tr>
        <th>PREC/CP</th>
        <th>Nome</th>
        <th>Data Nasc</th>
        <th>CODOM</th>
        <th class="text-center">Guias</th>
      </tr>
    </thead>
    <tbody>
      <s:iterator id="c" value="lista">
        <s:url id="listaGuias" action="beneficiario" method="guias">
          <s:param name="beneficiario.id" value="%{id}"/>
        </s:url>
        <tr>
          <td><s:property value="precCp"/></td>
          <td><s:property value="nome"/></td>
          <td><s:property value="nascimentoData"/></td>
          <td><s:property value="codom"/></td>
          <td class="text-center"><s:a href="%{listaGuias}" title="Guias de Encaminhamento"><i class="fa fa-files-o"></i></s:a></td>
        </tr>
      </s:iterator>
    </tbody>
  </table>
  
</tiles:putAttribute>

</tiles:insertDefinition>
