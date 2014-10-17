package br.com.wagnersoft.esculapio.actions;

import br.com.wagnersoft.esculapio.model.Beneficiario;
import br.com.wagnersoft.esculapio.services.BeneficiarioService;

import java.util.List;

import javax.inject.Inject;

import com.opensymphony.xwork2.ActionSupport;

/** Gerenciamento de Profissional.
 * @author Abreu Lopes
 * @since 1.0
 * @version $Id$
 */
public class BeneficiarioAction extends ActionSupport {

  /** serialVersionUID. */
  private static final long serialVersionUID = 1L;

  private List<?> lista;

  private Beneficiario beneficiario;

  @Inject
  private BeneficiarioService servico;

  public String excluir() {
    this.addActionMessage("Beneficiario n�o pode ser exclu�do.");
    return SUCCESS;
  }

  public String listar() {
    try {
      this.setLista(this.servico.listar());
      if (this.getLista() == null || this.getLista().isEmpty()) {
        this.addActionMessage("N�o foi encontrado nenhum Benefici�rio correspondente.");
      }
    } catch (Throwable e) {
      this.addActionError(e.getMessage());
    }
    return SUCCESS;
  }

  public String guias() {
    try {
      this.setBeneficiario(this.servico.recuperar(this.getBeneficiario().getId()));
      this.setLista(this.getBeneficiario().getGuias());
    } catch (Throwable e) {
      this.addActionError(e.getMessage());
      return INPUT;
    }
    return "guias";
  }
  
  public void setLista(List<?> lista) {
    this.lista = lista;
  }

  public List<?> getLista() {
    return this.lista;
  }

  public Beneficiario getBeneficiario() {
    return beneficiario;
  }

  public void setBeneficiario(Beneficiario beneficiario) {
    this.beneficiario = beneficiario;
  }

}
