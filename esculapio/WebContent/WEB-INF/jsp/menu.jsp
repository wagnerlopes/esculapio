<!--  $Id$ -->

<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" trimDirectiveWhitespaces="true" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<div id="menu" class="row">

<nav class="navbar navbar-default" role="navigation">
  <!-- Brand and toggle get grouped for better mobile display -->
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
      <span class="sr-only">Toggle navigation</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="http://www2.dgp.eb.mil.br/encaminhamento/entrada_sire.asp">SIRE</a>
  </div>

  <!-- Collect the nav links, forms, and other content for toggling -->
  <div class="collapse navbar-collapse navbar-ex1-collapse">
    <ul class="nav navbar-nav">
      <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Procedimentos <b class="caret"></b></a>
        <ul class="dropdown-menu">
          <li><s:a namespace="/" action="pm" method="comparar">Procedimentos M&eacute;dicos</s:a></li>
          <li><s:a namespace="/" action="dth" method="comparar">Di&aacute;rias e Taxas Hospitalares</s:a></li>
          <li><a href="#">Medicamentos</a></li>
          <li><a href="#">OPME</a></li>
        </ul>
      <li>  
      <li><s:a namespace="/" action="ocs" method="listar">OCS</s:a></li>
      <li><s:a namespace="/" action="guia" method="input">Guia</s:a></li>
      <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Relat&oacute;rios <b class="caret"></b></a>
        <ul class="dropdown-menu">
          <li><s:a namespace="/" action="beneficiario" method="listar">Benefici&aacute;rios</s:a></li>
          <li><s:a namespace="/" action="profissional" method="listar">Profissionais</s:a></li>
          <li class="divider"></li>
          <li><s:a namespace="/" action="protocolo" method="listar">Protocolo</s:a></li>
          <li class="divider"></li>
          <li><s:a namespace="/" action="dth" method="listar">Di&aacute;rias e Taxas Hospitalares</s:a></li>
          <li><s:a namespace="/" action="pm" method="listar">Procedimentos M&eacute;dicos</s:a></li>
        </ul>
      </li>
    </ul>
    <form class="navbar-form navbar-left" role="search">
      <div class="form-group">
        <input id="criterio" type="text" class="form-control" placeholder="Pesquisar" onfocus="navega();">
      </div>
      <div class="btn-group">
        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"><i class="icon-search"></i> Pesquisar <span class="caret"></span></button>
        <ul class="dropdown-menu" role="menu">
          <li><s:a href="#" onclick="listarPM(document.getElementById('criterio').value,document.getElementById('listaPM'));">Procedimentos M&eacute;dicos</s:a></li>
          <li><s:a href="#" onclick="listarDth(document.getElementById('criterio').value,document.getElementById('listaDth'));">Di&aacute;rias e Taxas Hospitalares</s:a></li>
        </ul>
      </div>
    </form>
    <ul class="nav navbar-nav navbar-right">
      <li><s:a namespace="/" action="contato">Contato</s:a></li>
      <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Administra&ccedil;&atilde;o <b class="caret"></b></a>
        <ul class="dropdown-menu">
          <li><s:a namespace="/" action="configuracao" method="listar">Configura&ccedil;&otilde;es</s:a></li>
          <li class="divider"></li>
          <li><s:a namespace="/admin" action="datasource">Datasource</s:a></li>
          <li><s:a namespace="/admin" action="snoop">Snoop</s:a></li>
          <li class="divider"></li>
          <li><s:a namespace="/admin" action="planilha">Carga de Planilha</s:a></li>
        </ul>
      </li>
    </ul>
  </div>
</nav>


  <script type="text/javascript" src="<c:url value='/etc/jquery.min.js'/>"></script>
  <script type="text/javascript" src="<c:url value='/etc/bootstrap/js/jquery.dataTables.min.js'/>"></script>
  <script type="text/javascript" src="<c:url value='/etc/bootstrap/js/bootstrap.min.js'/>"></script>

  <script type="text/javascript" src="<c:url value='/dwr/util.js'/>"></script>
  <script type="text/javascript" src="<c:url value='/dwr/engine.js'/>"></script>
  <script type="text/javascript" src="<c:url value='/dwr/interface/pmService.js'/>"></script>
  <script type="text/javascript" src="<c:url value='/dwr/interface/ocsPmService.js'/>"></script>
  <script type="text/javascript" src="<c:url value='/dwr/interface/dthService.js'/>"></script>
  <script type="text/javascript" src="<c:url value='/etc/esculapio.js'/>"></script>

<script type="text/javascript">
  $(document).ready(function(){
    $('#example').dataTable({
      "aaSorting": [[1,'asc']],
      "sPaginationType": "full_numbers",
      "oLanguage": {
          "sProcessing":   "Processando...",
          "sLengthMenu":   "Mostrar _MENU_ registros",
          "sZeroRecords":  "Não foram encontrados resultados",
          "sInfo":         "Mostrando de _START_ até _END_ de _TOTAL_ registros",
          "sInfoEmpty":    "Mostrando de 0 até 0 de 0 registros",
          "sInfoFiltered": "(filtrado de _MAX_ registros no total)",
          "sInfoPostFix":  "",
          "sSearch":       "Buscar:",
          "sUrl":          "",
          "oPaginate": {
              "sFirst":    "Primeiro",
              "sPrevious": "Anterior",
              "sNext":     "Seguinte",
              "sLast":     "Último"
          }
      }      
    });
  });
</script>

</div>
