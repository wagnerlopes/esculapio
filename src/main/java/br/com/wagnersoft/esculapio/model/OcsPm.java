package br.com.wagnersoft.esculapio.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the ocs_pm database table.
 */
@Entity
@Table(name="ocs_pm")
@NamedQueries(value={
  @NamedQuery(name="OcsPm.findByPM", query="SELECT o FROM OcsPm o WHERE o.pm.id = ?1"),
  @NamedQuery(name="OcsPm.findByOCS", query="SELECT o FROM OcsPm o WHERE o.ocs.id = ?1"),
  @NamedQuery(name="OcsPm.excluirPorOcs", query="DELETE FROM OcsPm o WHERE o.ocs.id = ?1")
})
public class OcsPm implements Comparable<OcsPm>, Serializable {

  private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="ch_qtd")
	private Integer chQtd;

  @Column(name="valor")
  private BigDecimal valor;

  @ManyToOne
  @JoinColumn(name="ocs_id")
  private Ocs ocs;
  
  @ManyToOne
  @JoinColumn(name="pm_id")
  private ProcedimentoMedico pm;

	public OcsPm() {
	}

  public OcsPm(Integer id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return this.getId() == null ? "OCS-PM" : new StringBuilder(this.getOcs().getDescricao()).append(" - ").append(this.getPm().getDescricao()).toString();
  }
  
  @Override
  public int compareTo(OcsPm o) {
    return this.getPm().getDescricao().compareTo(o.getPm().getDescricao());
  }


	@Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
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
    OcsPm other = (OcsPm) obj;
    if (id != other.id)
      return false;
    return true;
  }

  public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getChQtd() {
		return this.chQtd;
	}

	public void setChQtd(Integer chQtd) {
		this.chQtd = chQtd;
	}

  public BigDecimal getValor() {
    return valor;
  }

  public void setValor(BigDecimal valor) {
    this.valor = valor;
  }

  public ProcedimentoMedico getPm() {
    return pm;
  }

  public void setPm(ProcedimentoMedico pm) {
    this.pm = pm;
  }

  public Ocs getOcs() {
    return ocs;
  }

  public void setOcs(Ocs ocs) {
    this.ocs = ocs;
  }

}