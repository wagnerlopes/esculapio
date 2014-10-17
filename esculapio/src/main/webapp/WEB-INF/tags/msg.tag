<%-- $Id: mes.tag 704 2010-05-03 14:14:11Z wlopes $ --%>

<%@tag body-content="scriptless" trimDirectiveWhitespaces="true"%>

<%@taglib prefix="s" uri="/struts-tags"%>

<s:if test="hasActionErrors()">
  <div class="alert alert-danger">
    <s:iterator value="actionErrors">
      <button class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
      <s:property default="Mensagem nula." escapeHtml="false" /><br/>
    </s:iterator>
  </div>
</s:if>

<s:if test="hasActionMessages()">
  <div class="alert alert-info">
    <s:iterator value="actionMessages">
      <button class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
      <s:property default="Mensagem nula." escapeHtml="false" /><br/>
    </s:iterator>
  </div>
</s:if>
