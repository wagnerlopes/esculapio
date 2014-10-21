<%-- $Id: mes.tag 704 2010-05-03 14:14:11Z wlopes $ --%>

<%@tag body-content="scriptless" trimDirectiveWhitespaces="true"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<%@attribute name="nome" type="java.lang.String" required="false"%>
<%@attribute name="valor" type="java.lang.String" required="false"%>

<c:if test="${empty nome}">
  <c:set var="nome" value="mes"/>
</c:if>

<s:select id="%{#attr.nome}"
          name="%{#attr.nome}"
          value="%{#attr.valor}"
          emptyOption="true"
          required="false"
          list="#{'1':'Janeiro','2':'Fevereiro','3':'Março','4':'Abril','5':'Maio','6':'Junho','7':'Julho','8':'Agosto','9':'Setembro','10':'Outubro','11':'Novembro','12':'Dezembro'}"/>
