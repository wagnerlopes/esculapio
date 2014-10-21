<%-- $Id --%>
<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" trimDirectiveWhitespaces="true"%>

<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%><!DOCTYPE html><html lang="en"> 
<head>  <tiles:insertAttribute name="meta"/>
  <t:calendarioHeader /></head>
<body>  <div class="container">     <tiles:insertAttribute name="cabecalho"/>    <tiles:insertAttribute name="menu"/>    <tiles:insertAttribute name="corpo"/>    <tiles:insertAttribute name="rodape"/>  </div></body>
</html>
