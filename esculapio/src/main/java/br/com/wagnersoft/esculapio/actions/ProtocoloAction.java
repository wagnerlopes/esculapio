package br.com.wagnersoft.esculapio.actions;

import br.com.wagnersoft.esculapio.model.Protocolo;
import br.com.wagnersoft.esculapio.services.ProtocoloService;

import java.util.List;

import javax.inject.Inject;

import com.opensymphony.xwork2.ActionSupport;

/** Gerenciamento de Procedimento Médico.
 * @author Abreu Lopes
 * @since 1.0
 * @version $Id$
 */
public class ProtocoloAction extends ActionSupport {

  /** serialVersionUID. */
  private static final long serialVersionUID = 1L;

  private List<?> lista;

  private Protocolo protocolo;

  @Inject
  private ProtocoloService servico;

  public String excluir() {
    try {
      this.servico.excluir(this.getProtocolo().getId());
    } catch (Exception e) {
      this.addActionError(e.getMessage());
    }
    return SUCCESS;
  }

  @Override
  public String input() throws Exception {
    if (this.getProtocolo() != null && this.getProtocolo().getId() != null) {
      this.setProtocolo(this.servico.recuperar(this.getProtocolo().getId()));
    }
    return super.input();
  }
  
  public String listar() {
    try {
      this.setLista(servico.listar());
      if (this.getLista() == null || this.getLista().isEmpty()) {
        this.addActionMessage("Não foi encontrado nenhuma Protocolo correspondente.");
      }
    } catch (Throwable e) {
      this.addActionError(e.getMessage());
    }
    return SUCCESS;
  }

  public String salvar() {
    try {
      this.servico.salvar(this.getProtocolo());
      this.setLista(this.servico.listar());
    } catch (Throwable e) {
      this.addActionError(e.getMessage());
    }
    return SUCCESS;
  }

  public String guias() {
    try {
      this.setProtocolo(this.servico.recuperar(this.getProtocolo().getId()));
      this.setLista(this.getProtocolo().getGuias());
    } catch (Throwable e) {
      this.addActionError(e.getMessage());
      return SUCCESS;
    }
    return "guias";
  }
  
  public void setLista(List<?> lista) {
    this.lista = lista;
  }

  public List<?> getLista() {
    return this.lista;
  }

  public Protocolo getProtocolo() {
    return protocolo;
  }

  public void setProtocolo(Protocolo protocolo) {
    this.protocolo = protocolo;
  }
  
}
