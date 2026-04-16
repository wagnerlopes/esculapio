package br.com.wagnersoft.esculapio.actions;

import br.com.wagnersoft.esculapio.model.Dth;
import br.com.wagnersoft.esculapio.services.DthService;

import java.util.List;

import javax.inject.Inject;

import com.opensymphony.xwork2.ActionSupport;

/** Gerenciamento de Procedimento Médico.
 * @author Abreu Lopes
 * @since 1.0
 * @version $Id$
 */
public class DthAction extends ActionSupport {

  /** serialVersionUID. */
  private static final long serialVersionUID = 1L;

  private List<?> lista;

  private Dth dth;

  @Inject
  private DthService servico;

  public String comparar() {
    return "compara";
  }

  public String listar() {
    try {
      this.setLista(servico.listar());
      if (this.getLista() == null || this.getLista().isEmpty()) {
        this.addActionMessage("Não foi encontrado nenhuma DTH correspondente.");
      }
    } catch (Throwable e) {
      this.addActionError(e.getMessage());
    }
    return SUCCESS;
  }

  public void setLista(List<?> lista) {
    this.lista = lista;
  }

  public List<?> getLista() {
    return this.lista;
  }

  public Dth getDth() {
    return dth;
  }

  public void setDth(Dth dth) {
    this.dth = dth;
  }
  
}
