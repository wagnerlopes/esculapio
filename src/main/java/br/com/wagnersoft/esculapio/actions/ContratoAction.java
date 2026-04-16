package br.com.wagnersoft.esculapio.actions;

import br.com.wagnersoft.esculapio.model.Contrato;
import br.com.wagnersoft.esculapio.services.ContratoService;

import java.util.List;

import javax.inject.Inject;

import com.opensymphony.xwork2.ActionSupport;

/** Gerenciamento de Contratos de OCS.
 * @author Abreu Lopes
 * @since 1.0
 * @version $Id$
 */
public class ContratoAction extends ActionSupport {

  /** serialVersionUID. */
  private static final long serialVersionUID = 1L;

  private List<?> lista;

  private Contrato contrato;
  
  @Inject
  private ContratoService contratoService;

  @Override
  public String input() throws Exception {
    if (this.getContrato() != null && this.getContrato().getId() != null) {
      this.setContrato(this.contratoService.recuperar(this.getContrato().getId()));
    }
    return super.input();
  }
  
  public String excluir() {
    try {
      this.contratoService.excluir(this.getContrato());
    } catch (Throwable e) {
      this.addActionError(e.getMessage());
    }
    return SUCCESS;
  }

  public String salvar() {
    try {
      this.contratoService.salvar(this.getContrato());
    } catch (Throwable e) {
      this.addActionError(e.getMessage());
    }
    return "contratos";
  }

  public void setLista(List<?> lista) {
    this.lista = lista;
  }

  public List<?> getLista() {
    return this.lista;
  }

  public Contrato getContrato() {
    return contrato;
  }

  public void setContrato(Contrato contrato) {
    this.contrato = contrato;
  }

}
