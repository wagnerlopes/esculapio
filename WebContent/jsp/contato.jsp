<%@page trimDirectiveWhitespaces="true"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<tiles:insertDefinition name="layout">

<tiles:putAttribute name="corpo">

  <!-- Breadcrumb -->
  <ul class="breadcrumb">
    <li><a href='<s:url namespace="/" action="inicio"/>' title="Início"><i class="fa fa-home fa-lg"></i></a></li>
    <li><span class="label label-default">Contato</span></li>
  </ul>

  <t:msg />

  <div class="well">   
    <address>
      <strong>WagnerSoft - Desenvolvimento de Sistemas de Informa&ccedil;&atilde;o</strong><br/>
      <br/>
      Rua Borba Gato, 331 - Santo Amaro<br/>
      S&atilde;o Paulo, SP<br/><br/>
      <i class="fa fa-phone"></i>&nbsp;<fmt:message key="app.telefone"/>;<br/>
      <i class="fa fa-envelope"></i>&nbsp;<a href="mailto:<fmt:message key='app.email'/>"><fmt:message key="app.email"/></a>
    </address>
  </div>

</tiles:putAttribute>

</tiles:insertDefinition>
