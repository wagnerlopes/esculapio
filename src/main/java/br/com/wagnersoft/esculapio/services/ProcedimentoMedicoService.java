package br.com.wagnersoft.esculapio.services;

import br.com.wagnersoft.esculapio.dao.ProcedimentoMedicoDao;
import br.com.wagnersoft.esculapio.model.ProcedimentoMedico;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;

/** Serviço para Procedimento Médico. (Tabela PROCEDIMENTOS_MEDICOS)
 * @author Abreu Lopes
 * @since 1.0
 * @version $Id$
 */
@Named("procedimentoMedicoService")
@RemoteProxy(name="pmService")
public class ProcedimentoMedicoService {

  @Inject
  private ProcedimentoMedicoDao pmDao;

  /** Construtor. */
  public ProcedimentoMedicoService() {
  }
  
  public List<ProcedimentoMedico> listar() {
    return pmDao.findAll(null);
  }
  
  public List<ProcedimentoMedico> listarPorCodigo(final String criterio) throws Exception {
    return pmDao.findByNamedQuery("ProcedimentoMedico.findByCodigo", criterio);
  }

  @RemoteMethod
  public ProcedimentoMedico[] listarPorDescricao(final String criterio) throws Exception {
    final List<ProcedimentoMedico> lista = pmDao.findByNamedQuery("ProcedimentoMedico.findByDesc", criterio);
    Collections.sort(lista);
    return lista.toArray(new ProcedimentoMedico[0]);
  }

}
