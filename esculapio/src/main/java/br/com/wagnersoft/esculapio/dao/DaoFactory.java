package br.com.wagnersoft.esculapio.dao;

/** Fábrica abstrata de DAO. (Padrão GoF Abstract Factory)
 * @author Abreu Lopes
 * @since 1.0
 * @version $Id: DaoFactory.java 2282 2013-07-29 11:34:25Z gardino $
 */
public abstract class DaoFactory {

  /** Constante do nome da implementação JPA (EclipseLink). */
  public static final Class<? extends DaoFactory> JPA = DaoFactoryJpaImpl.class;
  
  /** Retorna a fábrica apropriada para a implementação solicitada.
   * @param factory Classe de implementação da fábrica
   * @return Fábrica de DAO apropriada
   */
  public static DaoFactory getInstance(Class<? extends DaoFactory> factory) {
    try {
      return factory.newInstance();
    } catch (Exception erro) {
      throw new RuntimeException("Erro ao criar a DaoFactory: " + factory, erro);
    }
  }

  /** Construtor com acesso restrito, usar o método getInstance() para obter uma fábrica. */
  protected DaoFactory() {
    // Construtor default.
  }
  
  // Adicionar os métodos para criar os DAO necessários na aplicação, devem ser instanciados na classe AppConfig definida
  // para a aplicação (classe anotada com @Configuration para inicialização pelo Spring). 
  
  public abstract BeneficiarioDao getBeneficiarioDao();
  public abstract ContratoDao getContratoDao();
  public abstract DthDao getDthDao();
  public abstract EspecialidadeDao getEspecialidadeDao();
  public abstract GuiaEncaminhamentoDao getGuiaEncaminhamentoDao();
  public abstract OcsDao getOcsDao();
  public abstract OcsPmDao getOcsPmDao();
  public abstract ProcedimentoMedicoDao getProcedimentoMedicoDao();
  public abstract ProtocoloDao getProtocoloDao();
  public abstract ProfissionalDao getProfissionalDao();
  public abstract TipoAcomodacaoDao getTipoAcomodacaoDao();
  public abstract TipoAtendimentoDao getTipoAtendimentoDao();
  public abstract TipoConsultaDao getTipoConsultaDao();
  public abstract TipoGuiaConsultaDao getTipoGuiaConsultaDao();
  public abstract TipoTermoDao getTipoTermoDao();
  public abstract TissTabelaDao getTissTabelaDao();

}
