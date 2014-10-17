package br.com.wagnersoft.esculapio.model;

import java.io.Serializable;
import java.text.DecimalFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the profissionais database table.
 */
@Entity
@Table(name="profissionais")
@NamedQueries(value={
  @NamedQuery(name="Profissional.findByCrm", query="SELECT p FROM Profissional p WHERE p.crm = ?1"),
  @NamedQuery(name="Profissional.excluir", query="DELETE FROM Profissional p")
})
public class Profissional implements Comparable<Profissional>, Serializable {
  
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String crm;

	@Column(name="crm_uf")
	private String crmUf;

  @ManyToOne
  @JoinColumn(name="especialidade")
	private Especialidade especialidade;

	private String idt;

	private String nome;

	public Profissional() {
	}

  @Override
  public String toString() {
    return this.getId() == null ? "PROFISSIONAL" : new StringBuilder(this.getNome())
                                                                     .append(" - CRM ")
                                                                     .append(this.getCrm())
                                                                     .append(" ")
                                                                     .append(this.getCrmUf())
                                                                     .toString();
  }
	
  @Override
  public int compareTo(Profissional o) {
    return this.getNome().compareTo(o.getNome());
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
    Profissional other = (Profissional) obj;
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

	public String getCrm() {
		return this.crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getCrmUf() {
		return this.crmUf;
	}

	public void setCrmUf(String crmUf) {
		this.crmUf = crmUf;
	}
	
	public Especialidade getEspecialidade() {
    return especialidade;
  }

  public void setEspecialidade(Especialidade especialidade) {
    this.especialidade = especialidade;
  }

  public String getIdt() {
		return this.idt;
	}

	public void setIdt(String idt) {
		this.idt = idt;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

  public String getCrmf() {
    final DecimalFormat df = new DecimalFormat();
    df.setGroupingUsed(true);
    return new StringBuilder("CRM").append("-").append(this.getCrmUf()).append(" ").append(df.format(Long.valueOf(this.getCrm()))).toString() ;
  }

	
}