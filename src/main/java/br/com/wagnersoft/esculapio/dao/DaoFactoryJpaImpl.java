package br.com.wagnersoft.esculapio.dao;

import br.com.wagnersoft.esculapio.model.Beneficiario;
import br.com.wagnersoft.esculapio.model.Contrato;
import br.com.wagnersoft.esculapio.model.Dth;
import br.com.wagnersoft.esculapio.model.Especialidade;
import br.com.wagnersoft.esculapio.model.GuiaEncaminhamento;
import br.com.wagnersoft.esculapio.model.Ocs;
import br.com.wagnersoft.esculapio.model.OcsPm;
import br.com.wagnersoft.esculapio.model.ProcedimentoMedico;
import br.com.wagnersoft.esculapio.model.Profissional;
import br.com.wagnersoft.esculapio.model.Protocolo;
import br.com.wagnersoft.esculapio.model.TipoAcomodacao;
import br.com.wagnersoft.esculapio.model.TipoAtendimento;
import br.com.wagnersoft.esculapio.model.TipoConsulta;
import br.com.wagnersoft.esculapio.model.TipoGuiaConsulta;
import br.com.wagnersoft.esculapio.model.TipoTermo;
import br.com.wagnersoft.esculapio.model.TissTabela;

/** Implementação da fábrica abstrata de DAO para uso com o EclipseLink (JPA).
 * @author Abreu Lopes
 * @since 1.0
 * @version $Id$
 */
public class DaoFactoryJpaImpl extends DaoFactory {

  /** Método auxiliar para instanciar os DAO.
   * @param daoClass Classe do DAO a ser instanciado
   * @return DAO que manipula uma entidade do modelo
   */
  private GenericDaoJpaImpl<?, ?> instanciarDao(final Class<?> daoClass) {
    try {
      return (GenericDaoJpaImpl<?, ?>) daoClass.newInstance();
    } catch (Exception erro) {
      throw new RuntimeException("Erro instanciando DAO: " + daoClass, erro);
    }
  }

  // Implementar métodos definidos na fábrica abstrata DaoFactory para instanciar o DAO
  
  @Override
  public BeneficiarioDao getBeneficiarioDao() {
    return (BeneficiarioDao) instanciarDao(BeneficiarioDaoJpa.class);
  }

  @Override
  public ContratoDao getContratoDao() {
    return (ContratoDao) instanciarDao(ContratoDaoJpa.class);
  }

  @Override
  public DthDao getDthDao() {
    return (DthDao) instanciarDao(DthDaoJpa.class);
  }

  @Override
  public EspecialidadeDao getEspecialidadeDao() {
    return (EspecialidadeDao) instanciarDao(EspecialidadeDaoJpa.class);
  }

  @Override
  public GuiaEncaminhamentoDao getGuiaEncaminhamentoDao() {
    return (GuiaEncaminhamentoDao) instanciarDao(GuiaEncaminhamentoDaoJpa.class);
  }

  @Override
  public OcsDao getOcsDao() {
    return (OcsDao) instanciarDao(OcsDaoJpa.class);
  }

  @Override
  public OcsPmDao getOcsPmDao() {
    return (OcsPmDao) instanciarDao(OcsPmDaoJpa.class);
  }

  @Override
  public ProcedimentoMedicoDao getProcedimentoMedicoDao() {
    return (ProcedimentoMedicoDao) instanciarDao(ProcedimentoMedicoDaoJpa.class);
  }
  
  @Override
  public ProfissionalDao getProfissionalDao() {
    return (ProfissionalDao) instanciarDao(ProfissionalDaoJpa.class);
  }

  @Override
  public ProtocoloDao getProtocoloDao() {
    return (ProtocoloDao) instanciarDao(ProtocoloDaoJpa.class);
  }

  @Override
  public TipoAcomodacaoDao getTipoAcomodacaoDao() {
    return (TipoAcomodacaoDao) instanciarDao(TipoAcomodacaoDaoJpa.class);
  }
  
  @Override
  public TipoAtendimentoDao getTipoAtendimentoDao() {
    return (TipoAtendimentoDao) instanciarDao(TipoAtendimentoDaoJpa.class);
  }
  
  @Override
  public TipoConsultaDao getTipoConsultaDao() {
    return (TipoConsultaDao) instanciarDao(TipoConsultaDaoJpa.class);
  }
  
  @Override
  public TipoGuiaConsultaDao getTipoGuiaConsultaDao() {
    return (TipoGuiaConsultaDao) instanciarDao(TipoGuiaConsultaDaoJpa.class);
  }
  
  @Override
  public TipoTermoDao getTipoTermoDao() {
    return (TipoTermoDao) instanciarDao(TipoTermoDaoJpa.class);
  }
  
  @Override
  public TissTabelaDao getTissTabelaDao() {
    return (TissTabelaDao) instanciarDao(TissTabelaDaoJpa.class);
  }

  // Criar implementações concretas dos DAO a serem instanciados (usando classes internas para evitar criar classes explícitas)
  
  public static class BeneficiarioDaoJpa extends GenericDaoJpaImpl<Beneficiario, Integer> implements BeneficiarioDao {}
  public static class ContratoDaoJpa extends GenericDaoJpaImpl<Contrato, Integer> implements ContratoDao {}
  public static class DthDaoJpa extends GenericDaoJpaImpl<Dth, Integer> implements DthDao {}
  public static class EspecialidadeDaoJpa extends GenericDaoJpaImpl<Especialidade, Integer> implements EspecialidadeDao {}
  public static class GuiaEncaminhamentoDaoJpa extends GenericDaoJpaImpl<GuiaEncaminhamento, Integer> implements GuiaEncaminhamentoDao {}
  public static class OcsDaoJpa extends GenericDaoJpaImpl<Ocs, Integer> implements OcsDao {}
  public static class OcsPmDaoJpa extends GenericDaoJpaImpl<OcsPm, Integer> implements OcsPmDao {}
  public static class ProcedimentoMedicoDaoJpa extends GenericDaoJpaImpl<ProcedimentoMedico, Integer> implements ProcedimentoMedicoDao {}
  public static class ProfissionalDaoJpa extends GenericDaoJpaImpl<Profissional, Integer> implements ProfissionalDao {}
  public static class ProtocoloDaoJpa extends GenericDaoJpaImpl<Protocolo, Integer> implements ProtocoloDao {}
  public static class TipoAcomodacaoDaoJpa extends GenericDaoJpaImpl<TipoAcomodacao, String> implements TipoAcomodacaoDao {}
  public static class TipoAtendimentoDaoJpa extends GenericDaoJpaImpl<TipoAtendimento, String> implements TipoAtendimentoDao {}
  public static class TipoConsultaDaoJpa extends GenericDaoJpaImpl<TipoConsulta, String> implements TipoConsultaDao {}
  public static class TipoGuiaConsultaDaoJpa extends GenericDaoJpaImpl<TipoGuiaConsulta, String> implements TipoGuiaConsultaDao {}
  public static class TipoTermoDaoJpa extends GenericDaoJpaImpl<TipoTermo, Integer> implements TipoTermoDao {}
  public static class TissTabelaDaoJpa extends GenericDaoJpaImpl<TissTabela, String> implements TissTabelaDao {}

}
