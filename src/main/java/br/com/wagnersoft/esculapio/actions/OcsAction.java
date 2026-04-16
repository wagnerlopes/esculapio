package br.com.wagnersoft.esculapio.actions;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import br.com.wagnersoft.esculapio.core.UploadHelper;
import br.com.wagnersoft.esculapio.model.Ocs;
import br.com.wagnersoft.esculapio.services.OcsPmService;
import br.com.wagnersoft.esculapio.services.OcsService;

import com.opensymphony.xwork2.ActionSupport;

/** Gerenciamento de OCS.
 * @author Abreu Lopes
 * @since 1.0
 * @version $Id$
 */
public class OcsAction extends ActionSupport {

  /** serialVersionUID. */
  private static final long serialVersionUID = 1L;

  private File arquivo;

  private String arquivoFileName;

  private List<?> lista;

  private Ocs ocs;

  private int tipo;

  @Inject
  private OcsService ocsService;
  
  @Inject
  private OcsPmService ocsPmService;

  public String carregarPlanilha() {
    File arq = null;
    try {
      if (this.getArquivo() != null) {
        arq = new UploadHelper().copyFile(this.getArquivo(), this.getArquivoFileName());
        switch (tipo) {
        case 1:
          this.addActionMessage(this.ocsService.carregarPlanilhaDth(this.getOcs(), arq));
          break;
        default:
          this.addActionMessage(this.ocsPmService.carregarPlanilha(this.getOcs(), arq));
          break;
        }
      }
    } catch (Throwable e) {
      this.addActionError(e.getMessage());
    } finally {
      if (arq != null) {
        arq.delete();
        arq = null;
        this.getArquivo().delete();
      }
    }
    return "carga";
  }

  @Override
  public String input() throws Exception {
    if (this.getOcs() != null && this.getOcs().getId() != null) {
      this.setOcs(this.ocsService.recuperar(this.getOcs().getId()));
    }
    return super.input();
  }

  public String listar() {
    try {
      if (this.getOcs() == null || this.getOcs().getId() == null) {
        this.setLista(this.ocsService.listar());
        //this.total = this.ocsServico.contar();
        //this.maxPage = (long) Math.ceil((float)this.total / 10);
      } else {
        this.setOcs(this.ocsService.recuperar(this.ocs.getId()));
        return INPUT;
      }
    } catch (Throwable e) {
      this.addActionError(e.getMessage());
    }
    return SUCCESS;
  }

  public String salvar() {
    try {
      this.ocsService.salvar(this.getOcs());
      this.setLista(this.ocsService.listar());
    } catch (Throwable e) {
      this.addActionError(e.getMessage());
    }
    return SUCCESS;
  }

  public String dth() {
    try {
      this.setOcs(this.ocsService.recuperar(this.getOcs().getId()));
      if (this.getOcs().getDth() == null || this.getOcs().getDth().isEmpty()) {
        throw new Exception("Não há Diárias e Taxas Hospitalares cadastrados para este OCS.");
      }
      this.setLista(this.getOcs().getDth());
    } catch (Throwable e) {
      this.addActionError(e.getMessage());
    }
    return "dth";
  }
  
  public String pm() {
    try {
      this.setOcs(this.ocsService.recuperar(this.getOcs().getId()));
      if (this.getOcs().getOcsPm() == null || this.getOcs().getOcsPm().isEmpty()) {
        throw new Exception("Não há Procedimentos Médicos cadastrados para este OCS.");
      }
      this.setLista(this.getOcs().getOcsPm());
    } catch (Throwable e) {
      this.addActionError(e.getMessage());
    }
    return "pm";
  }

  public String contratos() {
    try {
      this.setOcs(this.ocsService.recuperar(this.getOcs().getId()));
      if (this.getOcs().getContratos() == null || this.getOcs().getContratos().isEmpty()) {
        throw new Exception("Não há Termos de Contrato cadastrados para este OCS.");
      }
      this.setLista(this.getOcs().getContratos());
    } catch (Throwable e) {
      this.addActionError(e.getMessage());
    }
    return "contratos";
  }

  public String guias() {
    try {
      this.setOcs(this.ocsService.recuperar(this.getOcs().getId()));
      if (this.getOcs().getGuias() == null || this.getOcs().getGuias().isEmpty()) {
        throw new Exception("Não há Guias de Encaminhamento emitidas para este OCS.");
      }
      this.setLista(this.getOcs().getGuias());
    } catch (Throwable e) {
      this.addActionError(e.getMessage());
    }
    return "guias";
  }
  
  public List<?> getLista() {
    return lista;
  }

  public void setLista(List<?> lista) {
    this.lista = lista;
  }

  public Ocs getOcs() {
    return ocs;
  }

  public void setOcs(Ocs ocs) {
    this.ocs = ocs;
  }

  public File getArquivo() {
    return arquivo;
  }

  public void setArquivo(File arquivo) {
    this.arquivo = arquivo;
  }

  public String getArquivoFileName() {
    return arquivoFileName;
  }

  public void setArquivoFileName(String arquivoFileName) {
    this.arquivoFileName = arquivoFileName;
  }

  public int getTipo() {
    return this.tipo;
  }

  public void setTipo(int tipo) {
    this.tipo = tipo;
  }

}
