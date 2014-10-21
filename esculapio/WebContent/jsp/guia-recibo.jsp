<%@page contentType="text/html; charset=ISO-8859-1"%>

<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>

<html lang="en">
 
<head>
<meta http-equiv="content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Expires" content="Tue, 01 Jan 1980 00:00:00 GMT" />
<meta http-equiv="Cache-control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta name="author" content="Abreu Lopes" />
<meta name="copyright" content="(c) 2013, 2a RM" />
<meta name="robots" content="noindex,nofollow,noarchive" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="<c:url value='/etc/bootstrap/css/bootstrap.min.css'/>"  type="text/css" />
<link rel="stylesheet" href="<c:url value='/etc/bootstrap/css/bootstrap-theme.min.css'/>"  type="text/css" />

<title>FUSEX - Guia de Encaminhamento</title>
</head>

<body style="font-size:small">

  <div class="container"> 

  <h6 class="text-center">
    <strong>
      <img src='<s:url value="/etc/armas.png"/>' height="70" width="64"/><br/>
      MINIST&Eacute;RIO DA DEFESA<br/>
      EX&Eacute;RCITO BRASILEIRO<br/>
      DEPARTAMENTO-GERAL DO PESSOAL
    </strong>
  </h6>

  <h5 class="text-center"><strong>UF: H MIL A SAO PAULO</strong></h5>

  <h5 class="text-center"><strong>GUIA DE ENCAMINHAMENTO N&ordm;: ${empty guia.guiaNr ? "_____________" : guia.guiaNr} / FUSEX</strong></h5>

  <table class="table"><tr>
    <td class="col-lg-4 text-left"><b>DATA: <s:text name="format.date"><s:param name="valor" value="guia.emissaoData"/></s:text></b></td>
    <td class="col-lg-4 text-center"><b>VALIDADE 30 DIAS</b></td>
    <td class="col-lg-4 text-right"><b>Tempo de Atendimento: 1 min</b></td>
  </tr></table> 
 
  <table class="table table-bordered">
    <tr>
      <td><strong>BENEFICI&Aacute;RIO:</strong></td>
      <td colspan="2" class="text-center"><strong>Grupo: Ex&eacute;rcito Brasileiro - FUSEX</strong></td>
      <td class="text-right"><strong>Faixa: ${guia.beneficiario.faixaEtaria}</strong></td>
    </tr>
    <tr>
      <td><b>Titular: (PACIENTE)</b> <br/> ${guia.beneficiario.nome}</td>
      <td><b>UG Origem:</b> <br/> ${guia.beneficiario.codom}</td>
      <td><b>Prec CP:</b> <br/> ${guia.beneficiario.precCp}</td>
      <td><b>Assinatura do Respons&aacute;vel:</b></td>
    </tr>
    <tr>
      <td colspan="4"><strong>PRESTADOR DE SERVI&Ccedil;O:</strong></td>
    </tr>
    <tr>
      <td colspan="3" ><b>OCS/PSA:</b> ${guia.ocs.descricao} </td>
      <td><b>CNPJ/CPF:</b> ${guia.ocs.cnpjf}</td>
    </tr>
    <tr>
      <td colspan="2"><b>Endere&ccedil;o:</b> ${guia.ocs.endereco} </td>
      <td><b>N&uacute;mero:</b> ${guia.ocs.numero}</td>
      <td><b>Complemento:</b> ${guia.ocs.complemento}</td>
    </tr>
    <tr>
      <td><b>Munic&iacute;pio:</b> ${guia.ocs.municipio} </td>
      <td><b>UF:</b> ${guia.ocs.uf}</td>
      <td><b>Contato:</b> ${guia.ocs.contato}</td>
      <td><b>Telefone:</b> ${guia.ocs.telefone}</td>
    </tr>
    <tr>
      <td colspan="4"><strong>PROFISSIONAL SOLICITANTE:</strong></td>
    </tr>
    <tr>
      <td><b>Nome:</b> ${guia.solicitante.nome} </td>
      <td><b>CRM:</b> ${guia.solicitante.crm}</td>
      <td><b>UF:</b> ${guia.solicitante.crmUf}</td>
      <td><b>Idt:</b> ${guia.solicitante.idt}</td>
    </tr>
    <tr>
      <td colspan="4"><strong>PROFISSIONAL RESPONS&Aacute;VEL:</strong></td>
    </tr>
    <tr>
      <td><b>Nome:</b> ${guia.responsavel.nome} </td>
      <td><b>CRM:</b> ${guia.responsavel.crm}</td>
      <td><b>UF:</b> ${guia.responsavel.crmUf}</td>
      <td><b>Idt:</b> ${guia.responsavel.idt}</td>
    </tr>
    <tr>
      <td colspan="4"><strong>PROCEDIMENTOS:</strong></td>
    </tr>
    <tr>
      <td colspan="4">
        <table class="table table-bordered">
          <tr>
            <td><strong>Descri&ccedil;&atilde;o:</strong></td>
            <td><strong>Cod DGP (TUSS):</strong></td>
            <td><strong>Quant:</strong></td>
            <td><strong>V.Unit:</strong></td>
            <td><strong>Pos-Aud:</strong></td>
          </tr>
          <s:iterator value="guia.guiaOcsPm">
          <tr>
            <td><s:property value="ocsPm.pm.descricao"/></td>
            <td><s:property value="ocsPm.pm.tussf"/></td>
            <td class="text-right"><s:property value="pmQtd"/></td>
            <td class="text-right"><s:text name="format.number"><s:param name="ocsPm.valor" value="ocsPm.valor"/></s:text></td>
            <td><s:property value="ocsPm.posAuditoria"/></td>
          </tr>
          </s:iterator>
        </table>
      </td>
    </tr>
    <tr>
      <td><strong>Cotista: DAP</strong></td>
      <td><strong>Cr&eacute;dito: D8SAFUSOCSA-FUSEX OCS/C</strong></td>
      <td><strong>ND: 339039</strong></td>
      <td class="text-right"><strong>Total: <s:text name="format.number"><s:param name="guia.valorTotal" value="guia.valorTotal"/></s:text></strong></td>
    </tr>
    <tr>
      <td colspan="3">Obs: ${guia.observacao}</td>
      <td class="text-right"><strong>Valor Devido: <s:text name="format.number"><s:param name="valor" value="%{guia.valorTotal * 0.2}"/></s:text></strong></td>
    </tr>
  </table>
  <p class="small">
    Valores sujeitos a analise e revisão.<br/>
    Registrar, no verso, data e assinatura, por atendimento, se for o caso.<br/>
    1&ordf; Via - OCS/PSA; 2&ordf; Via - Usuário; e 3&ordf; Via - Seção de Contas Médicas.<br/>
    A fim de agilizar o pagamento, a guia, após apresentada na OCS ou PSA, deverá ser devolvida à unidade emitente em até 60 dias.
  </p>
  
  </div>
</body>

</html>
  