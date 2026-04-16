<%-- $Id$ --%>

<%@tag body-content="scriptless" trimDirectiveWhitespaces="true"%>

<%@taglib prefix="s" uri="/struts-tags"%>

<%@attribute name="page" type="java.lang.Long" required="true"%>
<%@attribute name="total" type="java.lang.Long" required="true"%>

<s:if test="%{#attr.maxPage > 1}">
  <ul class="pagination pagination-sm">
    <s:if test="%{#attr.page == 1}">
      <li class="disabled"><span>&laquo;</span></li>
    </s:if>
    <s:else>
      <li><s:a href="?page=%{#attr.page - 1}">&laquo;</s:a></li>
    </s:else>
    <s:iterator begin="1" end="%{#attr.maxPage}" status="i">
      <s:if test="%{#attr.page == #i.count}">
        <li class="active"><s:a href="?page=%{#i.count}"><s:property value="#i.count"/></s:a></li>
      </s:if>
      <s:else>
        <li><s:a href="?page=%{#i.count}"><s:property value="#i.count"/></s:a></li>
      </s:else>
    </s:iterator>
    <s:if test="%{#attr.page == #attr.maxPage}">
      <li class="disabled"><span>&raquo;</span></li>
    </s:if>
    <s:else>
      <li><s:a href="?page=%{#attr.page + 1}">&raquo;</s:a></li>
    </s:else>
    <li><s:a href="?page=" title="Lista completa"><span class="glyphicon glyphicon-flash"></span></s:a></li>
  </ul>
</s:if>
<div>
Encontrado(s) <span class="badge"><s:property value="#attr.total" /></span> registro(s).
</div> 
 