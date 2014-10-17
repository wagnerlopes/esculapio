package br.com.wagnersoft.esculapio.services;

import br.com.wagnersoft.esculapio.dao.ContratoDao;
import br.com.wagnersoft.esculapio.dao.OcsDao;
import br.com.wagnersoft.esculapio.model.Contrato;
import br.com.wagnersoft.esculapio.model.Ocs;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

/** Serviços de Contratos. (Tabela Contratos)
 * @author Abreu Lopes
 * @since 1.0
 * @version $Id$
 */
@Named("contratoService")
public class ContratoService {

  @Inject
  private OcsDao ocsDao;

  @Inject
  private ContratoDao contratoDao;
  
  /** Construtor. */
  public ContratoService() {
  }

  /** Recuperar Contrato por Id.
   * @return Contrato
   */
  public Contrato recuperar(final Integer id) {
    return this.contratoDao.findById(id);
  }
  
  /** Lista contratos do OCS.
   * @param id Id do OCS
   * @return Lista de contratos
   * @throws Exception erro no processamento
   */
  public List<Contrato> listarPorOcs(final Integer id) throws Exception {
    final List<Contrato> lista = this.ocsDao.findById(id).getContratos();
    if (lista == null || lista.isEmpty()) {
      throw new Exception("Não há contratos cadastrados para este OCS.");
    }
    return lista;
  }

  /** Salvar Contrato do OCS.
   * @return Contrato
   */
  @Transactional
  public void salvar(final Contrato contrato) throws Exception {
    final Ocs ocs = this.ocsDao.findById(contrato.getOcs().getId());
    if (ocs.getContratos().contains(contrato)){
      final Contrato c = ocs.getContratos().get(ocs.getContratos().indexOf(contrato));
      c.setChValor(contrato.getChValor());
      c.setFinalData(contrato.getFinalData());
      c.setInicioData(contrato.getInicioData());
      c.setTermoTipo(contrato.getTermoTipo());
    } else {
      ocs.addContrato(contrato);
    }
    this.ocsDao.save(ocs);
  }

  @Transactional
  public void excluir(final Contrato contrato) throws Exception {
    final Ocs ocs = this.ocsDao.findById(contrato.getOcs().getId());
    ocs.removeContrato(contrato);
    this.ocsDao.save(ocs);
  }
  
}
