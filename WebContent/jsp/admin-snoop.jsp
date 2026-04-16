<%@page trimDirectiveWhitespaces="true"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<tiles:insertDefinition name="layout">

<tiles:putAttribute name="corpo">

  <!-- Breadcrumb -->
  <ul class="breadcrumb">
    <li><a href='<s:url namespace="/" action="inicio"/>' title="Início"><i class="fa fa-home fa-lg"></i></a></li>
    <li>Administra&ccedil;&atilde;o</li>
    <li><span class="label label-default">Snoop</span></li>
  </ul>

  <p>Utilit&aacute;rio para debug de requisi&ccedil;&atilde;o HTTP.
     Fornece informa&ccedil;&otilde;es sobre o container do Servlet (escopo da aplica&ccedil;&atilde;o web),
     par&acirc;metros de inicializa&ccedil;&atilde;o, atributos e par&acirc;metros no contexto da requisi&ccedil;&atilde;o.</p>

      <table class="table table-striped table-bordered" summary="info">
        <tr><th colspan="2">Servlet Container</th></tr>
          <tr>
            <td>Container Web</td>
            <td>${pageContext.servletContext.serverInfo}</td>
          </tr>
          <tr>
            <td>API Servlet</td>
            <td>${pageContext.servletContext.majorVersion}.${pageContext.servletContext.minorVersion}</td>
          </tr>
      </table>

      <table  class="table table-striped table-bordered" summary="info2">
        <tr><th colspan="2">Par&acirc;metros de Inicializa&ccedil;&atilde;o</th></tr>
        <c:forEach var="p" items="${initParam}">
          <tr>
            <td>${p.key}</td>
            <td>${p.value}</td>
          </tr>
        </c:forEach>
      </table>

      <table class="table table-striped table-bordered" summary="info3">
        <tr><th colspan="2">Atributos na Aplica&ccedil;&atilde;o ${pageContext.servletContext.servletContextName}</th></tr>
        <c:forEach var="p" items="${applicationScope}">
          <tr>
            <td>${p.key}</td>
            <td>
              <c:choose>
                <c:when test="${p.key == 'org.apache.catalina.jsp_classpath'}">
                  <c:forTokens var="p" items="${p.value}" delims=";">${p}<br/></c:forTokens>
                </c:when>
                <c:otherwise>
                  <c:forTokens var="p" items="${p.value}" delims=",">${p}<br/></c:forTokens>
                </c:otherwise>
              </c:choose>
            </td>
          </tr>
        </c:forEach>
      </table>

      <table  class="table table-striped table-bordered" summary="info4">
        <tr><th colspan="2">Atributos na Sess&atilde;o ${pageContext.session.id}</th></tr>
        <c:forEach var="p" items="${sessionScope}">
          <tr>
            <td>${p.key}</td>
            <td>${p.value}</td>
          </tr>
        </c:forEach>
      </table>

      <table  class="table table-striped table-bordered" summary="info5">
        <tr><th colspan="2">Atributos na Requisi&ccedil;&atilde;o</th></tr>
        <c:forEach var="p" items="${requestScope}">
          <tr>
            <td>${p.key}</td>
            <td>${p.value}</td>
          </tr>
        </c:forEach>
      </table>

      <table class="table table-striped table-bordered" summary="info6">
        <tr><th colspan="2">Atributos na P&aacute;gina</th></tr>
        <c:forEach var="p" items="${pageScope}">
          <tr>
            <td>${p.key}</td>
            <td>${p.value}</td>
          </tr>
        </c:forEach>
      </table>

      <table class="table table-striped table-bordered" summary="info7">
        <tr><th colspan="2">Requisi&ccedil;&atilde;o HTTP</th></tr>
        <tr><td>method       </td><td>${pageContext.request.method}</td></tr>
        <tr><td>requestURI   </td><td>${pageContext.request.requestURI}</td></tr>
        <tr><td>requestURL   </td><td>${pageContext.request.requestURL}</td></tr>
        <tr><td>protocol     </td><td>${pageContext.request.protocol}</td></tr>
        <tr><td>servletPath  </td><td>${pageContext.request.servletPath}</td></tr>
        <tr><td>pathInfo     </td><td>${pageContext.request.pathInfo}</td></tr>
        <tr><td>pathtrans    </td><td>{pageContext.request.pathtranslated}</td></tr>
        <tr><td>contentLength</td><td>${pageContext.request.contentLength}</td></tr>
        <tr><td>contentType  </td><td>${pageContext.request.contentType}</td></tr>
        <tr><td>scheme       </td><td>${pageContext.request.scheme}</td></tr>
        <tr><td>serverName   </td><td>${pageContext.request.serverName}</td></tr>
        <tr><td>serverPort   </td><td>${pageContext.request.serverPort}</td></tr>
        <tr><td>remoteUser   </td><td>${pageContext.request.remoteUser}</td></tr>
        <tr><td>remoteAddr   </td><td>${pageContext.request.remoteAddr}</td></tr>
        <tr><td>remoteHost   </td><td>${pageContext.request.remoteHost}</td></tr>
        <tr><td>authType     </td><td>${pageContext.request.authType}</td></tr>
        <tr><td>queryString  </td><td>${pageContext.request.queryString}</td></tr>
      </table>

      <table class="table table-striped table-bordered" summary="info8">
        <tr><th colspan="2">Cabe&ccedil;alhos na Requisi&ccedil;&atilde;o</th></tr>
        <c:forEach var="p" items="${header}">
          <tr>
            <td>${p.key}</td>
            <td>${p.value}</td>
          </tr>
        </c:forEach>
      </table>

      <table class="table table-striped table-bordered" summary="info9">
        <tr><th colspan="2">Par&acirc;metros na Requisi&ccedil;&atilde;o</th></tr>
        <c:forEach var="p" items="${param}">
          <tr>
            <td>${p.key}</td>
            <td>${p.value}</td>
          </tr>
        </c:forEach>
      </table>

      <table class="table table-striped table-bordered" summary="info10">
        <tr><th colspan="2">Cookies na Requisi&ccedil;&atilde;o</th></tr>
        <c:forEach var="p" items="${cookie}">
          <tr>
            <td>${p.key}</td>
            <td>${p.value}</td>
          </tr>
        </c:forEach>
      </table>

</tiles:putAttribute>

</tiles:insertDefinition>
