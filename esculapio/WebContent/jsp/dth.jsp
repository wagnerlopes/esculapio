<%-- $Id$ --%>

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
    <li><span class="label label-default">Di&aacute;rias e Taxas Hospitalares</span></li>
  </ul>

  <t:msg />

  <s:if test="lista.size > 0">
   
  <table id="example" class="table table-striped">
    <thead>
      <tr>
        <th class="col-lg-4">OCS</th>
        <th class="col-lg-1">C&oacute;digo</th>
        <th class="col-lg-5">Descri&ccedil;&atilde;o</th>
        <th class="col-lg-1">Unidade
        <th class="col-lg-1 text-right">Valor (R$)</th>
      </tr>
    </thead>
    <tbody>
      <s:iterator id="c" value="lista">
      <tr>
        <td><s:property value="ocs.descricao"/></td>
        <td><s:property value="codigof"/></td>
        <td><s:property value="descricao"/></td>
        <td><s:property value="unidade"/></td>
        <td class="text-right">
          <s:text name="format.number"><s:param name="valor" value="valor"/></s:text>
        </td>
      </tr>
      </s:iterator>
    </tbody>
    <tfoot><tr><td>&nbsp;</td></tr></tfoot>
  </table>

  </s:if>
  
</tiles:putAttribute>

</tiles:insertDefinition>
