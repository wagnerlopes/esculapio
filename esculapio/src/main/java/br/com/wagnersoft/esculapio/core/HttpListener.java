package br.com.wagnersoft.esculapio.core;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Listener da sess�o HTTP de usu�rio conectado.
 * @author Abreu Lopes
 * @since 1.0
 * @version $Id$
 */
@WebListener
public class HttpListener implements HttpSessionListener {

  protected static final Logger logger = LoggerFactory.getLogger(ContextListener.class);

  /** Monitora a cria��o de sess�o do usu�rio.
   * @param e Evento "Sess�o de Usu�rio"
   */
  public void sessionCreated(HttpSessionEvent e) {
    logger.debug("{} sess�o iniciada", e.getSession().getId());
  }

  /** Encerramento de sess�o.
   * Limpa os arquivos gerados na pasta de relatorios.
   * @param e Evento "Sess�o de Usu�rio"
   */
  public void sessionDestroyed(HttpSessionEvent e) {
    logger.debug("{} sess�o encerrada", e.getSession().getId());
  }

}
