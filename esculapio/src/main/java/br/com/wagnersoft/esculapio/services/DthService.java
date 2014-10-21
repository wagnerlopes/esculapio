package br.com.wagnersoft.esculapio.services;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.transaction.annotation.Transactional;

import br.com.wagnersoft.esculapio.dao.DthDao;
import br.com.wagnersoft.esculapio.model.Dth;

/** Serviço para Diarias e Taxas Hospitalares. (Tabela DTH)
 * @author Abreu Lopes
 * @since 1.0
 * @version $Id$
 */
@Named("dthService")
@RemoteProxy(name="dthService")
public class DthService {

  @Inject
  private DthDao dao;

  public DthService() {
  }

  @Transactional
  public void excluir(final Integer id) throws Exception {
    if (id == null) {
      throw new IllegalArgumentException("Excluir: ID do OCS/PM não foi informado");
    }
    final Dth dth = new Dth();
    dth.setId(id);
    this.dao.delete(dth);
  }

  public List<Dth> listar() throws Exception {
    final List<Dth> lista = this.dao.findAll(null);
    if (lista != null && !lista.isEmpty()) {
      Collections.sort(lista);
    }
    return lista;
  }

  public List<Dth> listar(final Integer ocsId) throws Exception {
    final List<Dth> lista = this.dao.findByNamedQuery("Dth.findByOCS", ocsId);
    if (lista == null || lista.isEmpty()) {
      throw new Exception("Não há procedimentos cadastrados para este OCS.");
    }
    return lista;
  }

  @RemoteMethod
  public Dth[] listarPorDesc(final String descricao) throws Exception {
    final List<Dth> lista = this.dao.findByNamedQuery("Dth.findByDesc", descricao);
    if (lista != null && !lista.isEmpty()) {
      Collections.sort(lista);
    }
    return lista.toArray(new Dth[0]);
  }

  @RemoteMethod
  public Dth recuperar(final Integer id) throws Exception {
    if (id == null) {
      throw new IllegalArgumentException("ID do DTH não foi informado");
    }
    return this.dao.findById(id);
  }

  @Transactional
  public void salvar(final Dth entidade) throws Exception {
    if (entidade == null) {
      throw new IllegalArgumentException("DTH não foi informado");
    }
    if (entidade.getOcs() == null) {
      throw new IllegalArgumentException("OCS não foi informado");
    }
    if (entidade.getId() != null) {
      final Dth o = this.dao.findById(entidade.getId());
      o.setCodigo(entidade.getCodigo());
      o.setDescricao(entidade.getDescricao());
      o.setUnidade(entidade.getUnidade());
      o.setValor(entidade.getValor());
      o.setOcs(entidade.getOcs());
      this.dao.save(o);
    } else {
      this.dao.save(entidade);
    }
  }

}
