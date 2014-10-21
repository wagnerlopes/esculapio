package br.com.wagnersoft.esculapio.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.TableGenerator;

/**
 * The persistent class for the dth database table.
 */
@Entity
@NamedQueries(value={
  @NamedQuery(name="Dth.findByDesc", query="SELECT o FROM Dth o WHERE UPPER(o.descricao) LIKE UPPER(CONCAT('%',CONCAT(?1,'%')))"),
  @NamedQuery(name="Dth.findByOCS", query="SELECT o FROM Dth o WHERE o.ocs.id = ?1"),
  @NamedQuery(name="Dth.excluirPorOcs", query="DELETE FROM Dth o WHERE o.ocs.id = ?1")
})
public class Dth implements Comparable<Dth>, Serializable {

  private static final long serialVersionUID = 1L;

	@Id
  @GeneratedValue(strategy=GenerationType.TABLE, generator="TG_DTH")
  @TableGenerator(name="TG_DTH", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", pkColumnValue="DTH", allocationSize=1)
	private Integer id;

	private String codigo;

	private String descricao;

	private String unidade;

	private BigDecimal valor;

  @ManyToOne
  @JoinColumn(name="ocs_id", updatable=false, nullable=false)
  private Ocs ocs;

	public Dth() {
	}

  @Override
  public int compareTo(Dth o) {
    return this.getCodigo().compareTo(o.getCodigo());
  }

	@Override
	public String toString() {
	  return this.getCodigo() == null ? "DTH" :new StringBuilder(this.getCodigo()).append(" - ").append(this.getCodigo()).toString();
	}
	
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Dth other = (Dth) obj;
    if (codigo == null) {
      if (other.codigo != null)
        return false;
    } else if (!codigo.equals(other.codigo))
      return false;
    return true;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Ocs getOcs() {
    return this.ocs;
  }

  public void setOcs(Ocs ocs) {
    this.ocs = ocs;
  }

  public String getUnidade() {
		return this.unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

  public String getCodigof() {
    return this.codigo.isEmpty() || this.codigo.length() < 8 ? "CODIGO" : new StringBuilder(codigo.substring(0,2)).append(".")
                                                                          .append(codigo.substring(2,4)).append(".")
                                                                          .append(codigo.substring(4,7)).append("-")
                                                                          .append(codigo.substring(7)).toString();
  }
	
}
