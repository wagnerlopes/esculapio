package br.com.wagnersoft.esculapio.core;

import br.com.wagnersoft.esculapio.dao.EspecialidadeDao;
import br.com.wagnersoft.esculapio.dao.TipoTermoDao;
import br.com.wagnersoft.esculapio.dao.TissTabelaDao;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/** Listener do contexto da aplicação corrente.
 * @author Abreu Lopes
 * @since 1.0
 * @version $Id$
 */
@WebListener
public final class ContextListener implements ServletContextListener {

  protected static final Logger logger = LoggerFactory.getLogger(ContextListener.class);

  private ServletContext ctx = null;

  /** Finalizaçãodo contexto da aplicação.
   * @param evento Evento de finalização
   * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
   */
  @Override
  public void contextDestroyed(ServletContextEvent evento) {
    logger.info("Sistema encerrado!.");
  }

  /** Inicialização do contexto da aplicação.
   * @param evt Evento de inicialização
   * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
   */
  @Override
  public void contextInitialized(ServletContextEvent evt) {
    final long tempo = System.nanoTime();
    logger.info("Iniciando contexto HMASP");
    this.ctx = evt.getServletContext();
    final WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.ctx);
    // Especialidade
    try {
      this.ctx.setAttribute("ESPECIALIDADE", wac.getBean(EspecialidadeDao.class).findAll(null));
      logger.info("Atributo ESPECIALIDADE carregado...");
    } catch (Exception e) {
      logger.error("ESPECIALIDADE", e);
    }
    // Termo
    try {
      this.ctx.setAttribute("TIPO_TERMO", wac.getBean(TipoTermoDao.class).findAll(null));
      logger.info("Atributo TERMO carregado...");
    } catch (Exception e) {
      logger.error("TERMO", e);
    }
    // Termo
    try {
      this.ctx.setAttribute("TISS_TABELA", wac.getBean(TissTabelaDao.class).findAll(null));
      logger.info("Atributo TISS_TABELA carregado...");
    } catch (Exception e) {
      logger.error("TERMO", e);
    }
    // SIM_NAO
    try {
      final Map<String, String> simNao = new HashMap<String, String>(2);
      simNao.put("S", "Sim");
      simNao.put("N", "N&atilde;o");
      this.ctx.setAttribute("SIM_NAO", simNao);
      logger.info("Atributo SIM_NAO carregado...");
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    // Protocolo Status
    try {
      final Map<String, String> STATUS = new HashMap<String, String>(5);
      STATUS.put("1", "Implantado");
      STATUS.put("2", "Auditoria");
      STATUS.put("3", "Glosa");
      STATUS.put("4", "Empenhado");
      STATUS.put("5", "Liquidado");
      this.ctx.setAttribute("STATUS", STATUS);
      logger.info("Atributo STATUS carregado...");
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    logger.info("Contexto HMASP iniciado [" + (System.nanoTime() - tempo) * 1E9 + " s]");
  }

}
