package br.com.wagnersoft.esculapio.services;

import br.com.wagnersoft.esculapio.dao.BeneficiarioDao;
import br.com.wagnersoft.esculapio.dao.GuiaEncaminhamentoDao;
import br.com.wagnersoft.esculapio.dao.OcsPmDao;
import br.com.wagnersoft.esculapio.model.Beneficiario;
import br.com.wagnersoft.esculapio.model.GuiaEncaminhamento;
import br.com.wagnersoft.esculapio.model.GuiaOcsPm;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.transaction.annotation.Transactional;

/** Serviço para Guia de Encaminhamento. (Tabela GUIA_ENCAMINHAMENTO)
 * @author Abreu Lopes
 * @since 1.0
 * @version $Id$
 */
@Named("guiaEncaminhamentoService")
@RemoteProxy(name="guiaService")
public class GuiaEncaminhamentoService {

  @Inject
  private BeneficiarioDao benDao;

  @Inject
  private GuiaEncaminhamentoDao guiaDao;

  @Inject
  private OcsPmDao ocsPmDao;
  
  /** Construtor. */
  public GuiaEncaminhamentoService() {
  }

  @Transactional
  public void excluir(final GuiaEncaminhamento guia) throws Exception {
    final Beneficiario b = this.benDao.findById(guia.getBeneficiario().getId());
    b.removeGuia(guia);
    this.benDao.save(b);
  }

  public GuiaEncaminhamento recuperar(final Integer id) throws Exception {
    return this.guiaDao.findById(id);
  }
  
  @Transactional
  public GuiaEncaminhamento salvar(final GuiaEncaminhamento guia) throws Exception {
    final List<GuiaOcsPm> listaPM = guia.getGuiaOcsPm();
    if (guia.getEmissaoData() == null) {
      guia.setEmissaoData(new Date());
    }
    guia.setGuiaOcsPm(null);
    final GuiaEncaminhamento guiaNew = this.guiaDao.save(guia);
    this.guiaDao.getEntityManager().flush();
    if (listaPM != null && !listaPM.isEmpty()) {
      BigDecimal total = new BigDecimal("0");
      for (final GuiaOcsPm gop: listaPM) {
        gop.setOcsPm(this.ocsPmDao.findById(gop.getOcsPm().getId()));
        total = (gop.getOcsPm().getValor().multiply(BigDecimal.valueOf(gop.getPmQtd()))).add(total);
        guiaNew.getGuiaOcsPm().add(gop);
        gop.setGuiaEncaminhamento(guiaNew);
      }
      guiaNew.setValorTotal(total);
    }
    this.guiaDao.getEntityManager().flush();
    return this.guiaDao.save(guiaNew);
  }

  @RemoteMethod
  public Beneficiario[] listarBeneficiario(final String precCp) throws Exception {
    final List<Beneficiario> lista = this.benDao.findByNamedQuery("Beneficiario.findByPrecCp", precCp);
    return lista.toArray(new Beneficiario[0]);
  }

  public Integer calcularIdade(final Date dataNasc) {
    final Calendar hoje = new GregorianCalendar();
    final Calendar dn = new GregorianCalendar();
    dn.setTime(dataNasc);
    int idade = hoje.get(Calendar.YEAR) - dn.get(Calendar.YEAR);
    boolean isMesMaior = dn.get(Calendar.MONTH) >= hoje.get(Calendar.MONTH);
    boolean isMesIgualMasDiaMaior = dn.get(Calendar.MONTH) == hoje.get(Calendar.MONTH) && dn.get(Calendar.DAY_OF_MONTH) > hoje.get(Calendar.DAY_OF_MONTH);
    if (isMesMaior || isMesIgualMasDiaMaior) {
      idade -= 1;
    }
    return idade;
  }
  
}
