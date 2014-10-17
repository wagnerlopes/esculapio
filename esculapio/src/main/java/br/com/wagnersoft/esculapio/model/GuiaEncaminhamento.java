package br.com.wagnersoft.esculapio.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the guia_encaminhamento database table.
 */
@Entity
@Table(name="guia_encaminhamento")
public class GuiaEncaminhamento implements Serializable {

  private static final long serialVersionUID = 1L;

	@Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="emissao_data")
	private Date emissaoData;

  @ManyToOne
  @JoinColumn(name="beneficiario_id")
  private Beneficiario beneficiario;

  @ManyToOne
  @JoinColumn(name="ocs_id")
  private Ocs ocs;

  @Column(name="guia_nr")
  private Integer guiaNr;

  @Column(name="operador")
  private String operador;
  
  @Column(name="observacao")
  private String observacao;

  @ManyToOne
  @JoinColumn(name="solicitante_id")
  private Profissional solicitante;

  @ManyToOne
  @JoinColumn(name="responsavel_id")
  private Profissional responsavel;

  @Column(name="valor_total")
  private BigDecimal valorTotal;

  @ManyToOne
  @JoinColumn(name="protocolo_id")
  private Protocolo protocolo;

  @OneToMany(mappedBy="guiaEncaminhamento", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<GuiaOcsPm> guiaOcsPm;

	public GuiaEncaminhamento() {
	  this.setGuiaOcsPm(new ArrayList<GuiaOcsPm>());
	}

	public GuiaEncaminhamento(final Beneficiario beneficiario, final Ocs ocs) {
    this();
    this.beneficiario = beneficiario;
    this.ocs = ocs;
	}

  @Override
  public String toString() {
    return this.getId() == null ? "GUIA" : new StringBuilder(this.getGuiaNr()==null?"NR":this.getGuiaNr().toString()).append(" - ").append(this.getBeneficiario()).toString();
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
    GuiaEncaminhamento other = (GuiaEncaminhamento) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  public Date getEmissaoData() {
		return this.emissaoData;
	}

	public void setEmissaoData(Date emissaoData) {
		this.emissaoData = emissaoData;
	}

	public List<GuiaOcsPm> getGuiaOcsPm() {
		return this.guiaOcsPm;
	}

	public void setGuiaOcsPm(List<GuiaOcsPm> guiaOcsPm) {
		this.guiaOcsPm = guiaOcsPm;
	}

	public GuiaOcsPm addGuiaOcsPm(GuiaOcsPm guiaOcsPm) {
	  if (!this.getGuiaOcsPm().contains(guiaOcsPm)) {
	    this.getGuiaOcsPm().add(guiaOcsPm);
	    guiaOcsPm.setGuiaEncaminhamento(this);
	  }
		return guiaOcsPm;
	}

	public GuiaOcsPm removeGuiaOcsPm(GuiaOcsPm guiaOcsPm) {
		getGuiaOcsPm().remove(guiaOcsPm);
		guiaOcsPm.setGuiaEncaminhamento(null);
		return guiaOcsPm;
	}
	
  public Beneficiario getBeneficiario() {
    return beneficiario;
  }

  public void setBeneficiario(Beneficiario beneficiario) {
    this.beneficiario = beneficiario;
  }

  public Ocs getOcs() {
    return ocs;
  }

  public void setOcs(Ocs ocs) {
    this.ocs = ocs;
  }

  public Integer getGuiaNr() {
    return guiaNr;
  }

  public void setGuiaNr(Integer guiaNr) {
    this.guiaNr = guiaNr;
  }

  public String getOperador() {
    return operador;
  }

  public void setOperador(String operador) {
    this.operador = operador;
  }

  public String getObservacao() {
    return observacao;
  }

  public void setObservacao(String observacao) {
    this.observacao = observacao;
  }

  public BigDecimal getValorTotal() {
    return valorTotal;
  }

  public void setValorTotal(BigDecimal valorTotal) {
    this.valorTotal = valorTotal;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public Profissional getSolicitante() {
    return solicitante;
  }

  public void setSolicitante(Profissional solicitante) {
    this.solicitante = solicitante;
  }

  public Profissional getResponsavel() {
    return responsavel;
  }

  public void setResponsavel(Profissional responsavel) {
    this.responsavel = responsavel;
  }
  
}