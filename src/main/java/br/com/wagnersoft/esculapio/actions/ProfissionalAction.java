package br.com.wagnersoft.esculapio.actions;

import br.com.wagnersoft.esculapio.model.Profissional;
import br.com.wagnersoft.esculapio.services.ProfissionalService;

import java.util.List;

import javax.inject.Inject;

import com.opensymphony.xwork2.ActionSupport;

/** Gerenciamento de Profissional.
 * @author Abreu Lopes
 * @since 1.0
 * @version $Id$
 */
public class ProfissionalAction extends ActionSupport {

  /** serialVersionUID. */
  private static final long serialVersionUID = 1L;

  private List<?> lista;

  private Profissional profissional;

  @Inject
  private ProfissionalService servico;

  public String excluir() {
    this.addActionMessage("Profissional não pode ser excluído.");
    return SUCCESS;
  }

  public String listar() {
    try {
      this.setLista(servico.listar());
      if (this.getLista() == null || this.getLista().isEmpty()) {
        this.addActionMessage("Não foi encontrado nenhuma Profissional correspondente.");
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

  public Profissional getProfissional() {
    return profissional;
  }

  public void setProfissional(Profissional profissional) {
    this.profissional = profissional;
  }
  
}
