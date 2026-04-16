package br.com.wagnersoft.esculapio.actions;

import javax.inject.Inject;
import javax.sql.DataSource;

import com.opensymphony.xwork2.ActionSupport;

/** Parâmetros do Datasource.
 * @author Abreu Lopes
 * @since 1.0
 * @version $Id$
 */
public class DatasourceAction extends ActionSupport {

  /** serialVersionUID */
  private static final long serialVersionUID = 1L;

  @Inject
  private DataSource datasource;
  
  @Override
  public String execute() throws Exception {
    return SUCCESS;
  }

  public DataSource getDatasource() {
    return datasource;
  }
  public void setDatasource(DataSource datasource) {
    this.datasource = datasource;
  }
  
}
