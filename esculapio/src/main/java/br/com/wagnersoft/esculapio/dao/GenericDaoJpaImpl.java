package br.com.wagnersoft.esculapio.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;

/** Implementação JPA (EclipseLink) de GenericDao.
 * @author Abreu Lopes
 * @version $Id$
 * @since 1.0
 * @param <T> Tipo da entidade
 * @param <ID> Tipo da chave primária
 */
public class GenericDaoJpaImpl<T, ID extends Serializable> implements GenericDao<T, ID> {

  /** Classe da entidade manipulada pelo DAO. */
  private final Class<T> classe;

  /** Entity Manager injetado por IoC. */
  private EntityManager entityManager;

  /** Define a classe da entidade com base no tipo genérico. */
  @SuppressWarnings("unchecked")
  public GenericDaoJpaImpl() {
    this.classe = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
  }

  /** Define a classe da entidade com base no parâmetro informado.
   * @param classe Classe da entidade
   */
  public GenericDaoJpaImpl(final Class<T> classe) {
    this.classe = classe;
  }

  /** Configura o Entity Manager injetado por IoC.
   * @param entityManager Um Entity Manager apropriado
   */
  @PersistenceContext
  public void setEntityManager(final EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public EntityManager getEntityManager() {
    return this.entityManager;
  }

  @Override
  public Class<T> getEntityClass() {
    return this.classe;
  }

  @Override
  public Connection getConnection() {
    return this.getEntityManager().unwrap(Connection.class);
  }
  
  @Override
  public long countAll() {
    final CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
    final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
    cq.select(cb.count(cq.from(this.classe)));
    return this.getEntityManager().createQuery(cq).getSingleResult();
  }

  @Override
  public long countByExample(final T exemplo) {
    final List<T> lista = this.findByExample(exemplo);
    return lista != null ? lista.size() : 0;
  }

  @Override
  public void delete(T entity) {
    this.getEntityManager().remove(this.getEntityManager().contains(entity) ? entity : this.getEntityManager().merge(entity));
  }

  @Override
  public int execute(final String namedQuery, Object... params) {
    final Query query = this.getEntityManager().createNamedQuery(namedQuery);
    for (int i = 0; i < params.length; i++) {
      query.setParameter(i + 1, params[i]);
    }
    return query.executeUpdate();
  }

  @Override
  public List<T> findAll(final Integer page) {
    return findByCriteria(page);
  }

  @Override
  public List<T> findByExample(final T entity) {
    final CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
    cb.equals(entity);
    final CriteriaQuery<T> query = cb.createQuery(this.classe);
    return this.getEntityManager().createQuery(query).getResultList();
  }

  @Override
  public T findById(final ID id) {
    return this.getEntityManager().find(this.classe, id);
  }

  @Override
  public List<T> findByNamedQuery(final String namedQuery, Object... params) {
    final TypedQuery<T> query = this.getEntityManager().createNamedQuery(namedQuery, this.classe);
    for (int i = 0; i < params.length; i++) {
      query.setParameter(i + 1, params[i]);
    }
    final List<T> resultado = query.getResultList();
    return resultado;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Object[]> findByNamedQueryArray(final String namedQuery, Object... params) {
    final Query query = this.getEntityManager().createNamedQuery(namedQuery);
    for (int i = 0; i < params.length; i++) {
      query.setParameter(i + 1, params[i]);
    }
    final List<Object[]> resultado = query.getResultList();
    return resultado;
  }

  @Override
  public List<T> findByNamedQueryAndNamedParams(final String namedQuery, final Map<String, ? extends Object> params) {
    final TypedQuery<T> query = this.getEntityManager().createNamedQuery(namedQuery, this.classe);
    for (final Map.Entry<String, ? extends Object> param : params.entrySet()) {
      query.setParameter(param.getKey(), param.getValue());
    }
    final List<T> result = query.getResultList();
    return result;
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Object[]> findBySQL(final String sql, Object... params) {
    final Query query = this.getEntityManager().createNativeQuery(sql);
    for (int i = 0; i < params.length; i++) {
      query.setParameter(i + 1, params[i]);
    }
    final List<Object[]> resultado = query.getResultList();
    return resultado;
  }

  @Override
  public T save(T entidade) {
    return this.getEntityManager().merge(entidade);
  }

  public List<T> findByCriteria(final Integer page, final Predicate... criterio) {
    final CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
    final TypedQuery<T> query = this.getEntityManager().createQuery(cb.createQuery(this.classe).where(criterio));
    if (page != null) {
      query.setMaxResults(10);
      query.setFirstResult((page-1)*10);
    }
    return query.getResultList();
  }

}
