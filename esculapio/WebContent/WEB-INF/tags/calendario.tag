<%-- $Id: calendario.tag 1088 2010-10-29 08:48:07Z gardino $ --%>

<%@tag body-content="scriptless" trimDirectiveWhitespaces="true"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<%@attribute name="nome" type="java.lang.String" required="true"%>
<%@attribute name="lancador" type="java.lang.String" required="false"%>
<%@attribute name="valor" type="java.lang.String" required="false"%>
<%@attribute name="scpdata" type="java.lang.String" required="false"%>

<c:if test="${empty lancador}">
  <c:set var="lancador" value="lancador"/>
</c:if>

<div class="col-lg-2">
  <div class="input-group">
    <s:textfield cssClass="form-control" id="%{#attr.nome}" name="%{#attr.nome}" valor="%{#attr.valor}" maxlength="10" onkeyup="contar(this)"/><span class="input-group-btn"><button class="btn btn-default" type="button" id="${lancador}"><span class="glyphicon glyphicon-calendar"></span></button></span>
  </div>
</div>
      
<script type="text/javascript">
  Calendar.setup({inputField: "${nome}", ifFormat: "%d/%m/%Y", button: "${lancador}"});
  function contar(t){
	  if(t.value.length == 2){
	    t.value += '/';
	  }
	  if(t.value.length == 5){
	    t.value += '/';
	  }
	} 
</script> 
