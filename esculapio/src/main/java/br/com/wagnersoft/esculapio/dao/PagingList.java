/*******************************************************************************
 * Copyright (c) 2011 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0 
 * which accompanies this distribution. 
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at 
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 * 		dclarke - Bug 370875 - Paging List Example
 ******************************************************************************/
package br.com.wagnersoft.esculapio.dao;

import java.util.AbstractList;
import java.util.List;

import javax.persistence.Parameter;
import javax.persistence.TypedQuery;

import org.eclipse.persistence.jpa.JpaHelper;
import org.eclipse.persistence.jpa.JpaQuery;
import org.eclipse.persistence.queries.ReadAllQuery;
import org.eclipse.persistence.queries.ReportQuery;

/**
 * Example class that wraps the execution of a {@link TypedQuery} calculating
 * the current size and then paging the results using the provided page size.
 * The query should contain an ORDER BY and the usage of this may produce
 * incorrect results if the matching data set changes on the database while the
 * results are being paged.
 * 
 * @author dclarke
 * @since EclipseLink 2.3.1
 */
public class PagingList<X> extends AbstractList<X> {

  /** The size determined by {@link #calculateSize()}. */
  private int size;

  /** Provided page size. */
  private int pageSize;

  /**
   * Original query. This query is closed for size calculation and re-used
   * with different first/max values for each page result.
   */
  private TypedQuery<X> query;

  /**
   * Cached page results. This approach holds all retrieved pages. if the
   * total volume of results is large this caching should be changed to only
   * hold a limited set of pages or rely on garbage collection to clear out
   * unused pages.
   */
  private List<X>[] pages;

  @SuppressWarnings("unchecked")
  public PagingList(final TypedQuery<X> query,final int pageSize) {
    this.query = query;
    this.pageSize = pageSize;
    this.size = calculateSize();
    this.pages = new List[(this.size / this.pageSize) + (this.size % this.pageSize > 0 ? 1 : 0)];
  }

  @Override
  public X get(int index) {
    return getPage(index / this.pageSize).get(index % this.pageSize);
  }

  @Override
  public int size() {
    return this.size;
  }

  protected TypedQuery<X> getQuery() {
    return this.query;
  }

  protected List<X>[] getPages() {
    return this.pages;
  }

  protected List<X> getPage(int pageNum) {
    List<X> page = this.getPages()[pageNum];
    if (page == null) {
      this.getQuery().setFirstResult(this.pageSize * pageNum);
      this.getQuery().setMaxResults((this.pageSize * pageNum) + this.pageSize);
      page = this.getQuery().getResultList();
      this.getPages()[pageNum] = page;
    }
    return page;
  }

  /**
   * Using the provided {@link TypedQuery} to calculate the size. The query is
   * copied to create a new query which just retrieves the count.
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  private int calculateSize() {
    JpaQuery<X> queryImpl = (JpaQuery<X>) getQuery();
    ReadAllQuery raq = JpaHelper.getReadAllQuery(getQuery());
    ReportQuery rq = null;
    if (raq.isReportQuery()) {
      rq = (ReportQuery) raq.clone();
      rq.getItems().clear();
      rq.addCount();
      rq.getGroupByExpressions().clear();
      rq.getOrderByExpressions().clear();
    } else {
      rq = new ReportQuery();
      rq.setReferenceClass(raq.getReferenceClass());
      rq.addCount();
      rq.setShouldReturnSingleValue(true);
      rq.setSelectionCriteria(raq.getSelectionCriteria());
    }
    // Wrap new report query as JPA query for execution with parameters
    TypedQuery<Number> countQuery = (TypedQuery<Number>) JpaHelper.createQuery(rq, queryImpl.getEntityManager());
    // Copy parameters
    for (Parameter p : getQuery().getParameters()) {
      countQuery.setParameter(p, getQuery().getParameterValue(p));
    }
    return countQuery.getSingleResult().intValue();
  }

}
