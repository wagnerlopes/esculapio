package br.com.wagnersoft.esculapio.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the protocolo database table.
 */
@Entity
public class Protocolo implements Serializable {

  private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String assunto;

	@Temporal(TemporalType.DATE)
	private Date data;

	private String destino;

	@Temporal(TemporalType.DATE)
	@Column(name="doc_data")
	private Date docData;

	@Column(name="doc_nr")
	private String docNr;

	@Column(name="doc_tipo")
	private String docTipo;

	private String observacao;

  private Integer status;
	
  @ManyToOne
  @JoinColumn(name="ocs_id")
	private Ocs ocs;
  
	private BigDecimal valor;

  @OneToMany(mappedBy="protocolo")
  private List<GuiaEncaminhamento> guias;
	
	public Protocolo() {
	  this.guias = new ArrayList<GuiaEncaminhamento>();
	}

  public Protocolo(final Integer id) {
    this();
    this.id = id;
  }
	
  @Override
  public String toString() {
    return this.getId() == null ? "Protocolo" : new StringBuilder(this.getDocTipo())
                                                                  .append(" n° ")
                                                                  .append(this.getDocNr())
                                                                  .append(" - ")
                                                                  .append(this.getOcs().getDescricao())
                                                                  .append(" - ")
                                                                  .append(DateFormat.getDateInstance().format(this.getDocData()))
                                                                  .toString();
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
    Protocolo other = (Protocolo) obj;
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

	public String getAssunto() {
		return this.assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDestino() {
		return this.destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Date getDocData() {
		return this.docData;
	}

	public void setDocData(Date docData) {
		this.docData = docData;
	}

	public String getDocNr() {
		return this.docNr;
	}

	public void setDocNr(String docNr) {
		this.docNr = docNr;
	}

	public String getDocTipo() {
		return this.docTipo;
	}

	public void setDocTipo(String docTipo) {
		this.docTipo = docTipo;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Ocs getOcs() {
		return this.ocs;
	}

	public void setOcs(Ocs ocs) {
		this.ocs = ocs;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public List<GuiaEncaminhamento> getGuias() {
    return guias;
  }

  public void setGuias(List<GuiaEncaminhamento> guias) {
    this.guias = guias;
  }
  
}