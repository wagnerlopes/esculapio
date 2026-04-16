package br.com.wagnersoft.esculapio.services;

import br.com.wagnersoft.esculapio.dao.BeneficiarioDao;
import br.com.wagnersoft.esculapio.model.Beneficiario;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/** Serviço para Protocolo. (Tabela BENEFICIARIOS)
 * @author Abreu Lopes
 * @since 1.0
 * @version $Id$
 */
@Named("beneficiarioService")
public class BeneficiarioService {

  @Inject
  private BeneficiarioDao dao;

  /** Construtor. */
  public BeneficiarioService() {
  }
  
  public List<Beneficiario> listar() {
    return this.dao.findAll(null);
  }

  public Beneficiario recuperar(final Integer id) {
    return this.dao.findById(id);
  }
  
}
