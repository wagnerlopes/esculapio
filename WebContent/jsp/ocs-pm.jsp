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
    <li><s:a namespace="/" action="inicio" title="Início"><i class="fa fa-home fa-lg"></i></s:a></li>
    <li><span class="label label-default">OCS</span></li>
    <li><span class="label label-default">Procedimentos M&eacute;dicos</span></li>
  </ul>

  <div class="page-header">
    <h3><s:property value="ocs.descricao"/></h3>  
  </div>

  <t:msg />
   
  <s:if test="lista.size > 0">

  <table id="example" class="table table-striped">
    <thead>
      <tr>
        <th class="col-lg-2">C&oacute;digo</th>
        <th class="col-lg-4">Grupo/Subgrupo</th>
        <th class="col-lg-4">Descri&ccedil;&atilde;o</th>
        <th class="col-lg-2 text-right">Valor (R$)</th>
      </tr>
    </thead>
    <tbody>
      <s:iterator id="c" value="lista">
        <tr>
          <td><s:property value="pm.tussf"/></td>
          <td><s:property value='%{pm.grupo + "/" + pm.subgrupo}'/></td>
          <td><s:property value="pm.descricao"/></td>
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
