package br.com.wagnersoft.esculapio.core;

import br.com.wagnersoft.esculapio.dao.BeneficiarioDao;
import br.com.wagnersoft.esculapio.dao.ContratoDao;
import br.com.wagnersoft.esculapio.dao.DaoFactory;
import br.com.wagnersoft.esculapio.dao.DthDao;
import br.com.wagnersoft.esculapio.dao.EspecialidadeDao;
import br.com.wagnersoft.esculapio.dao.GuiaEncaminhamentoDao;
import br.com.wagnersoft.esculapio.dao.OcsDao;
import br.com.wagnersoft.esculapio.dao.OcsPmDao;
import br.com.wagnersoft.esculapio.dao.ProcedimentoMedicoDao;
import br.com.wagnersoft.esculapio.dao.ProfissionalDao;
import br.com.wagnersoft.esculapio.dao.ProtocoloDao;
import br.com.wagnersoft.esculapio.dao.TipoAcomodacaoDao;
import br.com.wagnersoft.esculapio.dao.TipoAtendimentoDao;
import br.com.wagnersoft.esculapio.dao.TipoConsultaDao;
import br.com.wagnersoft.esculapio.dao.TipoGuiaConsultaDao;
import br.com.wagnersoft.esculapio.dao.TipoTermoDao;
import br.com.wagnersoft.esculapio.dao.TissTabelaDao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/** Spring Framework AppConfig.
 * @author Abreu Lopes
 * @since 1.0
 * @version $Id$
 */
@Configuration
@ImportResource("classpath*:applicationContext.xml")
public class AppConfig {

  // Métodos para instanciar os DAO a serem injetados nos serviços

  @Bean
  public BeneficiarioDao beneficiarioDao() {
    return DaoFactory.getInstance(DaoFactory.JPA).getBeneficiarioDao();
  }

  @Bean
  public ContratoDao contratoDao() {
    return DaoFactory.getInstance(DaoFactory.JPA).getContratoDao();
  }

  @Bean
  public DthDao dthDao() {
    return DaoFactory.getInstance(DaoFactory.JPA).getDthDao();
  }

  @Bean
  public EspecialidadeDao especialidadeDao() {
    return DaoFactory.getInstance(DaoFactory.JPA).getEspecialidadeDao();
  }

  @Bean
  public GuiaEncaminhamentoDao guiaEncaminhamentoDao() {
    return DaoFactory.getInstance(DaoFactory.JPA).getGuiaEncaminhamentoDao();
  }

  @Bean
  public OcsDao ocsDao() {
    return DaoFactory.getInstance(DaoFactory.JPA).getOcsDao();
  }
  
  @Bean
  public OcsPmDao ocsPmDao() {
    return DaoFactory.getInstance(DaoFactory.JPA).getOcsPmDao();
  }

  @Bean
  public ProcedimentoMedicoDao procedimentoMedicoDao() {
    return DaoFactory.getInstance(DaoFactory.JPA).getProcedimentoMedicoDao();
  }
  
  @Bean
  public ProfissionalDao profissionalDao() {
    return DaoFactory.getInstance(DaoFactory.JPA).getProfissionalDao();
  }
  
  @Bean
  public ProtocoloDao protocoloDao() {
    return DaoFactory.getInstance(DaoFactory.JPA).getProtocoloDao();
  }
  
  @Bean
  public TipoAcomodacaoDao tipoAcomodacaoDao() {
    return DaoFactory.getInstance(DaoFactory.JPA).getTipoAcomodacaoDao();
  }

  @Bean
  public TipoAtendimentoDao tipoAtendimentoDao() {
    return DaoFactory.getInstance(DaoFactory.JPA).getTipoAtendimentoDao();
  }
  
  @Bean
  public TipoConsultaDao tipoConsultaDao() {
    return DaoFactory.getInstance(DaoFactory.JPA).getTipoConsultaDao();
  }

  @Bean
  public TipoGuiaConsultaDao tipoGuiaConsultaDao() {
    return DaoFactory.getInstance(DaoFactory.JPA).getTipoGuiaConsultaDao();
  }

  @Bean
  public TipoTermoDao tipoTermoDao() {
    return DaoFactory.getInstance(DaoFactory.JPA).getTipoTermoDao();
  }

  @Bean
  public TissTabelaDao tissTabelaDao() {
    return DaoFactory.getInstance(DaoFactory.JPA).getTissTabelaDao();
  }
  
  
}
