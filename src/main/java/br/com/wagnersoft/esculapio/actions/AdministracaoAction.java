package br.com.wagnersoft.esculapio.actions;

import br.com.wagnersoft.esculapio.core.UploadHelper;
import br.com.wagnersoft.esculapio.services.OcsService;
import br.com.wagnersoft.esculapio.services.ProfissionalService;

import java.io.File;

import javax.inject.Inject;

import com.opensymphony.xwork2.ActionSupport;

/** Parâmetros do Datasource.
 * @author Abreu Lopes
 * @since 1.0
 * @version $Id$
 */
public class AdministracaoAction extends ActionSupport {

  /** serialVersionUID */
  private static final long serialVersionUID = 1L;

  private File arquivo;

  private String arquivoFileName;

  private int tipo;

  @Inject
  private OcsService ocsService;

  @Inject
  private ProfissionalService profService;
  
  public String carregarPlanilha() {
    File arq = null;
    try {
      if (this.getArquivo() != null) {
        arq = new UploadHelper().copyFile(this.getArquivo(), this.getArquivoFileName());
        switch (tipo) {
        case 0:
          this.addActionMessage(this.ocsService.carregarPlanilha(arq));
          break;
        case 1:
          this.addActionMessage(this.profService.carregarPlanilha(arq));
          break;
        default:
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
    return SUCCESS;
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
    return tipo;
  }

  public void setTipo(int tipo) {
    this.tipo = tipo;
  }
  
}
