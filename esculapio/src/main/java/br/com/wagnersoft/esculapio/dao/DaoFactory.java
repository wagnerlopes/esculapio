package br.com.wagnersoft.esculapio.dao;

/** F�brica abstrata de DAO. (Padr�o GoF Abstract Factory)
 * @author Abreu Lopes
 * @since 1.0
 * @version $Id: DaoFactory.java 2282 2013-07-29 11:34:25Z gardino $
 */
public abstract class DaoFactory {

  /** Constante do nome da implementa��o JPA (EclipseLink). */
  public static final Class<? extends DaoFactory> JPA = DaoFactoryJpaImpl.class;
  
  /** Retorna a f�brica apropriada para a implementa��o solicitada.
   * @param factory Classe de implementa��o da f�brica
   * @return F�brica de DAO apropriada
   */
  public static DaoFactory getInstance(Class<? extends DaoFactory> factory) {
    try {
      return factory.newInstance();
    } catch (Exception erro) {
      throw new RuntimeException("Erro ao criar a DaoFactory: " + factory, erro);
    }
  }

  /** Construtor com acesso restrito, usar o m�todo getInstance() para obter uma f�brica. */
  protected DaoFactory() {
    // Construtor default.
  }
  
  // Adicionar os m�todos para criar os DAO necess�rios na aplica��o, devem ser instanciados na classe AppConfig definida
  // para a aplica��o (classe anotada com @Configuration para inicializa��o pelo Spring). 
  
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
