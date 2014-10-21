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
    <li><span class="label label-default">OCS</span></li>
    <li><span class="label label-default">Cadastro</span></li>
  </ul>

  <t:msg />

  <s:form cssClass="form-horizontal" namespace="/" action="ocs!salvar" theme="simple">
    <div class="form-group">
      <label class="col-lg-2 control-label" for="cnpj">CNPJ</label>
      <div class="col-lg-2">
        <s:hidden key="ocs.id"/>
        <s:textfield id="cnpj" cssClass="form-control" name="ocs.cnpj" maxlength="20"/>
      </div>
    </div>
    <div class="form-group">
      <label class="col-lg-2 control-label" for="desc">Descri&ccedil;&atilde;o</label>
      <div class="col-lg-10">
        <s:textfield id="desc" cssClass="form-control" name="ocs.descricao" maxlength="70"/>
      </div>
    </div>
    <div class="form-group">
      <label class="col-lg-2 control-label" for="espec">Especialidade</label>
      <div class="col-lg-10">
        <s:textfield id="espec" cssClass="form-control" name="ocs.especialidade" maxlength="70"/>
      </div>
    </div>
    <div class="form-group">
      <label class="col-lg-2 control-label" for="end">Endere&ccedil;o</label>
      <div class="col-lg-8">
        <s:textfield id="end" cssClass="form-control" name="ocs.endereco" maxlength="70"/>
      </div>
      <label class="col-lg-1 control-label" for="nr">Nr</label>
      <div class="col-lg-1">
        <s:textfield id="nr" cssClass="form-control" name="ocs.numero" maxlength="30"/>
      </div>
    </div>
    <div class="form-group">
      <label class="col-lg-2 control-label" for="nr">Complemento</label>
      <div class="col-lg-10">
        <s:textfield id="nr" cssClass="form-control" name="ocs.complemento" maxlength="70"/>
      </div>
    </div>
    <div class="form-group">
      <label class="col-lg-2 control-label" for="nr">Munic&iacute;pio</label>
      <div class="col-lg-8">
        <s:textfield id="nr" cssClass="form-control" name="ocs.municipio" maxlength="70"/>
      </div>
      <div class="col-lg-2">
        <t:uf name="ocs.uf" id="uf" cssClass="form-control"/>
      </div>
    </div>
    <div class="form-group">
      <label class="col-lg-2 control-label" for="tel">Telefone</label>
      <div class="col-lg-10">
        <s:textfield id="tel" cssClass="form-control" name="ocs.telefone" maxlength="70"/>
      </div>
    </div>
    <div class="form-group">
      <label class="col-lg-2 control-label" for="ctt">Contato</label>
      <div class="col-lg-10">
        <s:textfield id="ctt" cssClass="form-control" name="ocs.contato" maxlength="70"/>
      </div>
    </div>
    <div align="center" class="form-group">
      <button type="submit" class="btn btn-success"><i class="fa fa-check fa-lg"></i> Salvar</button>
      <a class="btn btn-danger"  href='<s:url namespace="/" action="ocs" method="listar"/>'><i class="fa fa-trash-o fa-lg"></i> Cancelar</a>
    </div>
  </s:form>

  <script type="text/javascript">
    $("#uf").val('${ocs.uf}');
  </script>

</tiles:putAttribute>

</tiles:insertDefinition>
