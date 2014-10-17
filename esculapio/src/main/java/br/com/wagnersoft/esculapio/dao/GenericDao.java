package br.com.wagnersoft.esculapio.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;

/** DAO genérico que define as operações CRUD básicas de uma entidade do modelo.
 * @author Abreu Lopes
 * @version $Id$
 * @since 1.0
 * @param <T> Tipo da entidade
 * @param <ID> Tipo da chave primária
 */
public interface GenericDao<T, ID extends Serializable> {

  /** Retorna a conexão JDBC em uso.
   * @return java.sql.Connection
   */
  Connection getConnection();
  
  /** EntityManager em uso.
   * @return javax.persistence.EntityManager
   */
  EntityManager getEntityManager();
  
  /** Retorna a classe da entidade.
   * @return java.lang.class
   */
  Class<T> getEntityClass();

  /** Conta todas as entidades.
   * @return total de entidades
   */
  long countAll();

  /** Conta entidades baseado em um exemplo.
   * @param exemplo critério de pesquisa
   * @return número de entidades
   */
  long countByExample(final T exemplo);

  /** Remove a entidade.
   * @param entidade objeto a ser removido
   */
  void delete(final T entidade);

  /** Executa um comando SQL (delete ou stored procedure).
   * @param namedQuery Comando SQL 
   * @param params parâmetros
   * @return Total de registros afetados pelo comando
   */
  int execute(final String namedQuery, Object... params);

  /** Busca todas as entidades.
   * @param page página a ser retornada
   * @return lista de entidades
   */
  List<T> findAll(final Integer page);

  /** Encontra uma entidade pela sua chave primária.
   * @param id chave primária
   * @return entidade
   */
  T findById(final ID id);

  /** Busca entidades através de um exemplo.
   * @param exemplo exemplo de instância
   * @return lista de entidades
   */
  List<T> findByExample(final T exemplo);

  /** Busca usando uma Named Query com parâmetros passados por posição (1, 2, 3, ...)
   *  no Varargs (Object...) da chamada do método.
   *  Use este método somente quando a Query JPQL passar os parâmetros por posição (?1).
   * @param namedQuery consulta (JPQL)
   * @param params parâmetros
   * @return lista de entidades
   */
  List<T> findByNamedQuery(final String namedQuery, Object... params);

  /** Busca usando uma Named Query com parâmetros passados por posição (1, 2, 3, ...)
   *  no Varargs (Object...) da chamada do método.
   *  Use este método somente quando a Query JPQL passar os parâmetros por posição (?1).
   *  O retorno do método é uma lista de Object[] devido ao fato da Named Query ser uma
   *  lista de atributos dos objetos que estão sendo consultados. 
   * @param namedQuery consulta (JPQL)
   * @param params parâmetros
   * @return lista de Object[]
   */
  List<Object[]> findByNamedQueryArray(final String namedQuery, Object... params);

  /** Busca usando uma Named Query e os parâmetros passado por nome no Map da chamada do método.
   *  Use este método somente quando a Query JPQL passar os parâmetros por nome (:nome).
   * @param queryName consulta (JPQL)
   * @param params parâmetros
   * @return the lista de entidades
   */
  List<T> findByNamedQueryAndNamedParams(final String queryName, final Map<String, ? extends Object> params);

  /** Executa uma consulta nativa SQL.
   * @param sql Consulta SQL
   * @param params parâmetros
   * @return lista de array de objetos
   */
  List<Object[]> findBySQL(final String sql, Object... params);

  /** Salva a entidade (update ou insert).
   * @param entidade objeto a ser salvo
   * @return entidade salva
   */
  T save(final T entidade);

  /** Executa uma pesquisa por critério.
   * @param page página a ser retornada
   * @param criterio critérios de pesquisa
   * @return Lista do tipo T
   */
  List<T> findByCriteria(final Integer page, final Predicate... criterio);
  
}
