package br.com.wagnersoft.esculapio.core;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Listener da sessão HTTP de usuário conectado.
 * @author Abreu Lopes
 * @since 1.0
 * @version $Id$
 */
@WebListener
public class HttpListener implements HttpSessionListener {

  protected static final Logger logger = LoggerFactory.getLogger(ContextListener.class);

  /** Monitora a criação de sessão do usuário.
   * @param e Evento "Sessão de Usuário"
   */
  public void sessionCreated(HttpSessionEvent e) {
    logger.debug("{} sessão iniciada", e.getSession().getId());
  }

  /** Encerramento de sessão.
   * Limpa os arquivos gerados na pasta de relatorios.
   * @param e Evento "Sessão de Usuário"
   */
  public void sessionDestroyed(HttpSessionEvent e) {
    logger.debug("{} sessão encerrada", e.getSession().getId());
  }

}
