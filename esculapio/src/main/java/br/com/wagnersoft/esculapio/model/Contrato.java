package br.com.wagnersoft.esculapio.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.persistence.annotations.IdValidation;
import org.eclipse.persistence.annotations.PrimaryKey;

/**
 * The persistent class for the contratos database table.
 */
@Entity
@Table(name="contratos")
@NamedQuery(name="Contrato.findByOcs", query="SELECT c FROM Contrato c WHERE c.ocs.id = ?1")
@PrimaryKey(validation=IdValidation.NONE)
public class Contrato implements Serializable {

  private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name="final_data")
	private Date finalData;

	@Temporal(TemporalType.DATE)
	@Column(name="inicio_data")
	private Date inicioData;

  @Column(name="ch_valor")
  private BigDecimal chValor;

  @ManyToOne
  @JoinColumn(name="termo_tipo")
	private TipoTermo termoTipo;

	@ManyToOne
	@JoinColumn(name="ocs_id")
	private Ocs ocs;

	public Contrato() {
	}

  @Override
  public String toString() {
    return this.getId() == null ? "CONTRATO" : Integer.toString(this.getId());
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
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
    Contrato other = (Contrato) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFinalData() {
		return this.finalData;
	}

	public void setFinalData(Date finalData) {
		this.finalData = finalData;
	}

	public Date getInicioData() {
		return this.inicioData;
	}

	public void setInicioData(Date inicioData) {
		this.inicioData = inicioData;
	}

	public Ocs getOcs() {
		return this.ocs;
	}

	public void setOcs(Ocs ocs) {
		this.ocs = ocs;
	}

  public BigDecimal getChValor() {
    return chValor;
  }

  public void setChValor(BigDecimal chValor) {
    this.chValor = chValor;
  }

  public TipoTermo getTermoTipo() {
    return termoTipo;
  }

  public void setTermoTipo(TipoTermo termoTipo) {
    this.termoTipo = termoTipo;
  }
  
}