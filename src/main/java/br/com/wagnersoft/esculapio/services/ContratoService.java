package br.com.wagnersoft.esculapio.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import br.com.wagnersoft.esculapio.dao.ContratoDao;
import br.com.wagnersoft.esculapio.dao.OcsDao;
import br.com.wagnersoft.esculapio.model.Contrato;
import br.com.wagnersoft.esculapio.model.Ocs;

/** Serviços de Contratos. (Tabela Contratos)
 * @author Abreu Lopes
 * @since 1.0
 * @version $Id$
 */
@Named("contratoService")
public class ContratoService {

  protected static final Logger logger = LoggerFactory.getLogger(ContratoService.class);

  @Inject
  private OcsDao ocsDao;

  @Inject
  private ContratoDao contratoDao;
  
  public ContratoService() {
    logger.debug("ContratoService iniciado");
  }

  public Contrato recuperar(final Integer id) {
    return this.contratoDao.findById(id);
  }
  
  public List<Contrato> listarPorOcs(final Integer id) throws Exception {
    final List<Contrato> lista = this.ocsDao.findById(id).getContratos();
    if (lista == null || lista.isEmpty()) {
      throw new Exception("Não há contratos cadastrados para este OCS.");
    }
    return lista;
  }

  @Transactional
  public void salvar(final Contrato contrato) throws Exception {
    final Ocs ocs = this.ocsDao.findById(contrato.getOcs().getId());
    ocs.addContrato(contrato);
    this.ocsDao.save(ocs);
  }

  @Transactional
  public void excluir(final Contrato contrato) throws Exception {
    final Ocs ocs = this.ocsDao.findById(contrato.getOcs().getId());
    ocs.removeContrato(contrato);
    this.ocsDao.save(ocs);
  }
  
}
