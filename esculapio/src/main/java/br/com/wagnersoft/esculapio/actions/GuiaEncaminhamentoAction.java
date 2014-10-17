package br.com.wagnersoft.esculapio.actions;

import br.com.wagnersoft.esculapio.model.GuiaEncaminhamento;
import br.com.wagnersoft.esculapio.model.OcsPm;
import br.com.wagnersoft.esculapio.services.GuiaEncaminhamentoService;
import br.com.wagnersoft.esculapio.services.OcsPmService;

import javax.inject.Inject;

import com.opensymphony.xwork2.ActionSupport;

/** Gerenciamento de OCS.
 * @author Abreu Lopes
 * @since 1.0
 * @version $Id$
 */
public class GuiaEncaminhamentoAction extends ActionSupport {

  /** serialVersionUID. */
  private static final long serialVersionUID = 1L;

  private GuiaEncaminhamento guia;

  private OcsPm ocsPm;
  
  @Inject
  private GuiaEncaminhamentoService servico;

  @Inject
  private OcsPmService ocsPmServico;
  
  public String excluir() {
    try {
      this.servico.excluir(this.getGuia());
      this.addActionMessage("Guia Nr " + this.getGuia().getId() + " excluída");
    } catch (Exception e) {
      this.addActionError(e.getMessage());
    }
    return SUCCESS;
  }

  @Override
  public String input() throws Exception {
    if (this.getGuia() != null && this.getGuia().getGuiaOcsPm() != null && this.getGuia().getGuiaOcsPm().get(0) != null) {
      final OcsPm opm = this.ocsPmServico.recuperar(this.getGuia().getGuiaOcsPm().get(0).getOcsPm().getId());
      this.getGuia().setOcs(opm.getOcs());
      this.setOcsPm(opm);
    }
    return super.input();
  }

  public String salvar() {
    try {
      this.setGuia(this.servico.salvar(this.getGuia()));
      this.addActionMessage("Guia Nr " + this.getGuia().getId() + " (" + this.getGuia().getBeneficiario().getNome() + ") incluída");
    } catch (Throwable e) {
      this.addActionError(e.getMessage());
      return INPUT;
    }
    return SUCCESS;
  }
  
  public String recibo() {
    try {
      this.setGuia(this.servico.recuperar(this.getGuia().getId()));
    } catch (Exception e) {
      this.addActionError(e.getMessage());
      return SUCCESS;
    }
    return "recibo";
  }

  public GuiaEncaminhamento getGuia() {
    return guia;
  }

  public void setGuia(GuiaEncaminhamento guia) {
    this.guia = guia;
  }

  public OcsPm getOcsPm() {
    return ocsPm;
  }

  public void setOcsPm(OcsPm ocsPm) {
    this.ocsPm = ocsPm;
  }
  
}
