<%@page trimDirectiveWhitespaces="true"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<tiles:insertDefinition name="layout">

<tiles:putAttribute name="corpo">
  <!-- Breadcrumb -->  <ul class="breadcrumb">    <li><a href='<s:url namespace="/" action="inicio"/>' title="Início"><i class="fa fa-home fa-lg"></i></a></li>    <li><span class="label label-default">Erro</span></li>  </ul>  <s:actionerror cssClass="alert alert-error"/>  <pre>   - Mensagem de erro:<br/>   <s:if test="%{exception.message != null}">     <s:property value="%{exception.message}"/>   </s:if>   <s:else>     <s:property value="%{exceptionStack}"/>   </s:else>  </pre>  
</tiles:putAttribute>

</tiles:insertDefinition>
