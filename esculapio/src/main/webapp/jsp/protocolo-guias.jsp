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
    <li><span class="label label-default">Protocolo</span></li>
    <li><span class="label label-default">Guias de Encaminhamento</span></li>
  </ul>

  <div class="page-header">
    <h3><s:property value="protocolo"/></h3>  
  </div>
   
  <t:msg />

  <s:if test="lista.size > 0">
  
  <table id="example" class="table table-striped">
    <thead>
    <tr>
      <th class="col-lg-1 text-center">Nr</th>
      <th class="col-lg-5 text-left">Benefici&aacute;rio</th>
      <th class="col-lg-2 text-center">Data de Emiss&atilde;o</th>
      <th class="col-lg-2 text-right">Valor Total</th>
      <th class="col-lg-2 text-center">Editar</th>
    </tr>
    </thead>
    <tbody>
    <s:iterator id="c" value="lista">
      <s:url id="excluir" action="guia" method="excluir">
        <s:param name="guia.id" value="%{id}"/>
        <s:param name="guia.beneficiario.id" value="%{beneficiario.id}"/>
      </s:url>
      <tr>
        <td class="text-center"><s:property value="guiaNr"/></td>
        <td class="text-left"><s:property value="beneficiario"/></td>
        <td class="text-center"><s:date name="emissaoData" format="dd/MM/yyyy" /></td>
        <td class="text-right"><s:text name="format.money"><s:param name="valorTotal" value="valorTotal"/></s:text></td>
        <td class="text-center">
          <s:a href="%{excluir}" title="Excluir"><span class="fa fa-trash-o"></span></s:a>
        </td>
      </tr>
    </s:iterator>
    </tbody>
    <tfoot><tr><td>&nbsp;</td></tr></tfoot>
  </table>
  
  </s:if>
  
</tiles:putAttribute>

</tiles:insertDefinition>
