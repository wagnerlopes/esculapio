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
    <li><span class="label label-default">Profissionais</span></li>
  </ul>

  <t:msg />
   
  <table id="example" class="table table-striped">
    <thead>
      <tr>
        <th>Nome</th>
        <th>Especialidade</th>
        <th>CRM</th>
        <th>Idt</th>
      </tr>
    </thead>
    <tbody>
      <s:iterator id="c" value="lista">
      <tr>
        <td><s:property value="nome"/></td>
        <td><s:property value="especialidade"/></td>
        <td><s:property value="%{crmf}"/></td>
        <td><s:property value="idt"/></td>
      </tr>
      </s:iterator>
    </tbody>
  </table>
  
</tiles:putAttribute>

</tiles:insertDefinition>
