<%@page contentType="text/html; charset=ISO-8859-1"%>

<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<tiles:insertDefinition name="layout">

<tiles:putAttribute name="corpo">

  <!-- Breadcrumb -->
  <ul class="breadcrumb">
    <li><a href='<s:url namespace="/" action="inicio"/>' title="Início"><i class="fa fa-home fa-lg"></i></a></li>
    <li><span class="label label-default">Administra&ccedil;&atilde;o</span></li>
    <li><span class="label label-default">Datasource</span></li>
  </ul>
   
  <h3>Propriedades do Datasource</h3>

  <table class="table table-striped table-bordered">
    <tr>
      <td><i class="fa fa-laptop fa-lg"></i> Database</td>
      <td><s:property value="%{datasource.getConnection().getMetaData().getDatabaseProductVersion()}"/></td>
    </tr>
    <tr>
      <td><i class="fa fa-spinner fa-spin fa-lg"></i> Driver JDBC</td>
      <td><s:property value='%{datasource.getConnection().getMetaData().getDriverName() + " " + datasource.getConnection().getMetaData().getDriverVersion()}'/></td>
    </tr>
    <tr>
      <td><i class="fa fa-file fa-lg"></i> URL</td>
      <td><s:property value="%{datasource.getConnection().getMetaData().getURL()}"/></td>
    </tr>
    <tr>
      <td><i class="fa fa-user fa-lg"></i> Username</td>
      <td><s:property value="%{datasource.getConnection().getMetaData().getUserName()}"/></td>
    </tr>
    <tr>
      <td><i class="fa fa-book fa-lg"></i> Catalog</td>
      <td><s:property value="%{datasource.getConnection().getCatalog()}"/></td>
    </tr>
    <tr>
      <td><i class="fa fa-users fa-lg"></i> Schema</td>
      <td><s:property value="%{datasource.getConnection().getSchema()}"/></td>
    </tr>
  </table>

</tiles:putAttribute>

</tiles:insertDefinition>
