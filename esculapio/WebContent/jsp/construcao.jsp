<%@page trimDirectiveWhitespaces="true"%>

<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<tiles:insertDefinition name="layout">

<tiles:putAttribute name="corpo">

<div class="hero-unit">
  <h1>Desculpe!</h1>
  <p>Este recurso est&aacute; em constru&ccedil;&atilde;o.<a href='<s:url namespace="/" action="inicio"/>' title="Início"><i class="fa fa-home fa-lg"></i></a></p>
</div>
    
</tiles:putAttribute>

</tiles:insertDefinition>
