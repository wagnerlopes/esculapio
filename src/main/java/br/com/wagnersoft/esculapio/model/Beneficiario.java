package br.com.wagnersoft.esculapio.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the beneficiario database table.
 */
@Entity
@NamedQueries(value={
  @NamedQuery(name="Beneficiario.findByPrecCp", query="SELECT b FROM Beneficiario b WHERE b.precCp = ?1"),
  @NamedQuery(name="Beneficiario.excluir", query="DELETE FROM Beneficiario b ")
})
public class Beneficiario implements Serializable {

  private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String codom;

	@Temporal(TemporalType.DATE)
	@Column(name="nascimento_data")
	private Date nascimentoData;

	private String nome;

	@Column(name="prec_cp")
	private String precCp;

  @OneToMany(mappedBy="beneficiario", fetch=FetchType.EAGER, orphanRemoval=true)
  private List<GuiaEncaminhamento> guias;
	
	public Beneficiario() {
	}

	@Override
	public String toString() {
	  return this.getId() == null ? "BENEFICIARIO" : new StringBuilder(this.getPrecCp()).append(" - ").append(this.getNome()).toString();
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
    Beneficiario other = (Beneficiario) obj;
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

	public String getCodom() {
		return this.codom;
	}

	public void setCodom(String codom) {
		this.codom = codom;
	}

	public Date getNascimentoData() {
		return this.nascimentoData;
	}

	public void setNascimentoData(Date nascimentoData) {
		this.nascimentoData = nascimentoData;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPrecCp() {
		return this.precCp;
	}

	public void setPrecCp(String precCp) {
		this.precCp = precCp;
	}
	
  public List<GuiaEncaminhamento> getGuias() {
    return guias;
  }

  public void setGuias(List<GuiaEncaminhamento> guias) {
    this.guias = guias;
  }

  public GuiaEncaminhamento removeGuia(GuiaEncaminhamento guia) {
    this.getGuias().remove(guia);
    guia.setBeneficiario(null);
    return guia;
  }
  
  public Integer getIdade() {
    if (this.getNascimentoData() == null) {
      throw new IllegalArgumentException("Beneficiário não tem data de nascimento cadastrada");
    }
    final Calendar hoje = new GregorianCalendar();
    final Calendar dn = new GregorianCalendar();
    dn.setTime(this.getNascimentoData());
    int idade = hoje.get(Calendar.YEAR) - dn.get(Calendar.YEAR);
    boolean isMesMaior = dn.get(Calendar.MONTH) >= hoje.get(Calendar.MONTH);
    boolean isMesIgualMasDiaMaior = dn.get(Calendar.MONTH) == hoje.get(Calendar.MONTH) && dn.get(Calendar.DAY_OF_MONTH) > hoje.get(Calendar.DAY_OF_MONTH);
    if (isMesMaior || isMesIgualMasDiaMaior) {
      idade -= 1;
    }
    return idade;
  }

  public String getFaixaEtaria() {
    String aux = "101 a 110";
    if (this.getIdade() < 11) {
      aux = "0 a 10";
    } else if (this.getIdade() < 21) {
      aux = "11 a 20";
    } else if (this.getIdade() < 31) {
      aux = "21 a 30";
    } else if (this.getIdade() < 41) {
      aux = "31 a 40";
    } else if (this.getIdade() < 51) {
      aux = "41 a 50";
    } else if (this.getIdade() < 61) {
      aux = "51 a 60";
    } else if (this.getIdade() < 71) {
      aux = "61 a 70";
    } else if (this.getIdade() < 81) {
      aux = "71 a 80";
    } else if (this.getIdade() < 91) {
      aux = "81 a 90";
    } else if (this.getIdade() < 101) {
      aux = "91 a 100";
    }
    return aux;
  }
  
}
