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
import javax.persistence.Table;

/**
 * The persistent class for the guia_ocs_pm database table.
 */
@Entity
@Table(name="guia_ocs_pm")
public class GuiaOcsPm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="pm_qtd")
  private Integer pmQtd;

  @Column(name="pos_auditoria")
  private BigDecimal posAuditoria;
	
	@ManyToOne
	@JoinColumn(name="guia_id")
	private GuiaEncaminhamento guiaEncaminhamento;

  @ManyToOne
  @JoinColumn(name="ocs_pm_id")
  private OcsPm ocsPm;
	
	public GuiaOcsPm() {
	}

  public GuiaOcsPm(final GuiaEncaminhamento guia, final OcsPm ocsPm, final Integer pmQtd) {
    this.guiaEncaminhamento = guia;
    this.ocsPm = ocsPm;
    this.pmQtd = pmQtd;
  }

  @Override
  public String toString() {
    return new StringBuilder(this.getOcsPm()==null?"OCS - PM":this.getOcsPm().toString()).append(" - ").append(this.getPmQtd()==null?"QTD":this.getPmQtd()).toString();
  }

  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((ocsPm == null) ? 0 : ocsPm.hashCode());
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
    GuiaOcsPm other = (GuiaOcsPm) obj;
    if (ocsPm == null) {
      if (other.ocsPm != null)
        return false;
    } else if (!ocsPm.equals(other.ocsPm))
      return false;
    return true;
  }

  public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

  public GuiaEncaminhamento getGuiaEncaminhamento() {
    return guiaEncaminhamento;
  }

  public void setGuiaEncaminhamento(GuiaEncaminhamento guiaEncaminhamento) {
    this.guiaEncaminhamento = guiaEncaminhamento;
  }

  public OcsPm getOcsPm() {
    return ocsPm;
  }

  public void setOcsPm(OcsPm ocsPm) {
    this.ocsPm = ocsPm;
  }

  public Integer getPmQtd() {
    return pmQtd;
  }

  public void setPmQtd(Integer pmQtd) {
    this.pmQtd = pmQtd;
  }

  public BigDecimal getPosAuditoria() {
    return posAuditoria;
  }

  public void setPosAuditoria(BigDecimal posAuditoria) {
    this.posAuditoria = posAuditoria;
  }
	
}