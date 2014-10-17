package br.com.wagnersoft.esculapio.services;

import br.com.wagnersoft.esculapio.dao.ProtocoloDao;
import br.com.wagnersoft.esculapio.model.Protocolo;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

/** Serviço para Protocolo. (Tabela PROTOCOLO)
 * @author Abreu Lopes
 * @since 1.0
 * @version $Id$
 */
@Named("protocoloService")
public class ProtocoloService {

  @Inject
  private ProtocoloDao dao;

  /** Construtor. */
  public ProtocoloService() {
  }
  
  @Transactional
  public void excluir(final Integer id) throws Exception {
    this.dao.delete(this.recuperar(id));
  }

  public List<Protocolo> listar() {
    return dao.findAll(null);
  }

  public Protocolo recuperar(final Integer id) throws Exception {
    if (id == null) {
      throw new IllegalArgumentException("ID não foi informado");
    }
    return this.dao.findById(id);
  }

  @Transactional
  public void salvar(final Protocolo prot) throws Exception {
    if (prot != null && prot.getId() != null) {
      final Protocolo p = this.dao.findById(prot.getId());
      p.setAssunto(prot.getAssunto());
      p.setData(prot.getData());
      p.setDestino(prot.getDestino());
      p.setDocData(prot.getDocData());
      p.setDocNr(prot.getDocNr());
      p.setDocTipo(prot.getDocTipo());
      p.setObservacao(prot.getObservacao());
      p.setOcs(prot.getOcs());
      p.setValor(prot.getValor());
      p.setStatus(prot.getStatus());
      this.dao.save(p);
    } else {
      this.dao.save(prot);
    }
  }
  
}
