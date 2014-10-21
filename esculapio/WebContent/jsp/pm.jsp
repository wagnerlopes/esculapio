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
    <li><span class="label label-default">Procedimentos M&eacute;dicos</span></li>
  </ul>

  <t:msg />
   
  <table id="example" class="table table-striped">
    <thead>
      <tr>
        <th class="col-lg-1">C&oacute;digo</th>
        <th class="col-lg-4">Descri&ccedil;&atilde;o</th>
        <th class="col-lg-2">Grupo/Subgrupo
        <th class="col-lg-1">CH
        <th class="col-lg-1">AMB 90</th>
        <th class="col-lg-1">AMB 92</th>
        <th class="col-lg-1">AMB 96</th>
        <th class="col-lg-1">AMB 99</th>
      </tr>
    </thead>
    <tbody>
      <s:iterator id="c" value="lista">
      <tr>
        <td><s:property value="tussf"/></td>
        <td><s:property value="descricao"/></td>
        <td><s:property value='%{grupo + "/" + subgrupo}'/></td>
        <td><s:property value="chQtd"/></td>
        <!-- 
        <td><s:property value="auxiliaresQtd"/></td>
        <td><s:property value="porteAnestesico"/></td>
        -->
        <td><s:property value="amb90"/></td>
        <td><s:property value="amb92"/></td>
        <td><s:property value="amb96"/></td>
        <td><s:property value="amb99"/></td>
      </tr>
      </s:iterator>
    </tbody>
  </table>
  
</tiles:putAttribute>

</tiles:insertDefinition>
