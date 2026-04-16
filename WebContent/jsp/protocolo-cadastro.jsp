<%@page trimDirectiveWhitespaces="true"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<tiles:insertDefinition name="layout">

<tiles:putAttribute name="corpo">

  <!-- Breadcrumb -->
  <ul class="breadcrumb">
    <li><s:a namespace="/" action="inicio" title="Início"><i class="fa fa-home fa-lg"></i></s:a></li>
    <li><span class="label label-default">Protocolo</span></li>
    <li><span class="label label-default">Cadastro</span></li>
  </ul>

  <t:msg />

  <s:form cssClass="form-horizontal" namespace="/" action="protocolo!salvar" theme="simple">
    <div class="form-group">
      <label class="col-lg-1 control-label" for="ocs">OCS</label>
      <div class="col-lg-6">
        <s:select cssClass="form-control" id="listaOcs" name="protocolo.ocs.id" list="#{'-1':'Lista de OCS...'}" listKey="id" listValue="descricao" />
      </div>
      <label class="col-lg-1 control-label" for="valor">Valor R$</label>
      <div class="col-lg-2">
        <s:textfield id="valor" cssClass="form-control" name="protocolo.valor" maxlength="20"/>
      </div>
    </div>
    <div class="form-group">
      <label class="col-lg-1 control-label" for="docNr">Doc Nr</label>
      <div class="col-lg-1">
        <s:hidden key="protocolo.id"/>
        <s:textfield id="docNr" cssClass="form-control" name="protocolo.docNr" maxlength="10"/>
      </div>
      <label class="col-lg-1 control-label" for="docData">Doc Data</label>
      <t:calendario  nome="protocolo.docData" lancador="l1"/>
      <label class="col-lg-1 control-label" for="docTipo">Doc Tipo</label>
      <div class="col-lg-1">
        <s:textfield id="docTipo" cssClass="form-control" name="protocolo.docTipo" maxlength="30"/>
      </div>
      <label class="col-lg-1 control-label" for="status">Status</label>
      <div class="col-lg-2">
        <s:select id="status" cssClass="form-control" name="protocolo.status" list="#application['STATUS']" />
      </div>
    </div>
    <div class="form-group">
      <label class="col-lg-1 control-label" for="assunto">Assunto</label>
      <div class="col-lg-7">
        <s:textfield id="assunto" cssClass="form-control" name="protocolo.assunto" maxlength="70"/>
      </div>
      <label class="col-lg-1 control-label" for="data">Data</label>
      <t:calendario nome="protocolo.data" lancador="l2"/>
    </div>
    <div class="form-group">
      <label class="col-lg-1 control-label" for="obs">Observa&ccedil;&atilde;o</label>
      <div class="col-lg-10">
        <s:textfield id="obs" cssClass="form-control" name="protocolo.observacao" maxlength="70"/>
      </div>
    </div>
    <div class="form-group text-center">
      <button type="submit" class="btn btn-success"><i class="fa fa-check fa-lg"></i> Salvar</button>
      <a class="btn btn-danger"  href='<s:url namespace="/" action="protocolo" method="listar"/>'><i class="fa fa-trash-o fa-lg"></i> Cancelar</a>
    </div>
  </s:form>

  <script type="text/javascript" src="/hospital/dwr/interface/ocsService.js"></script>
  <script type="text/javascript">
    listarOCS(document.getElementById('listaOcs'),'${protocolo.ocs.id}');
  </script>

</tiles:putAttribute>

</tiles:insertDefinition>
