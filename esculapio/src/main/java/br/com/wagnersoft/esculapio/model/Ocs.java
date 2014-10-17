package br.com.wagnersoft.esculapio.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the ocs database table.
 */
@Entity
@Table(name="ocs")
@NamedQueries(value={
  @NamedQuery(name="Ocs.findByCnpj", query="SELECT o FROM Ocs o WHERE o.cnpj = ?1"),
  @NamedQuery(name="Ocs.findByEspec", query="SELECT o FROM Ocs o WHERE o.especialidade = ?1 ORDER BY o.descricao"),
  @NamedQuery(name="Ocs.excluir", query="DELETE FROM Ocs o")
})
public class Ocs implements Comparable<Ocs>, Serializable {
  
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String cnpj;

	private String descricao;

  private String especialidade;

  private String endereco;

  private String numero;

  private String complemento;
  
  private String municipio;

  private String uf;

  private String telefone;

  private String contato;
  
	@OneToMany(mappedBy="ocs", fetch=FetchType.EAGER, orphanRemoval=true)
	private List<Contrato> contratos;

  @OneToMany(mappedBy="ocs", fetch=FetchType.EAGER, orphanRemoval=true)
  private List<OcsPm> ocsPm;
	
  @OneToMany(mappedBy="ocs", fetch=FetchType.EAGER, orphanRemoval=true)
  private List<Dth> dth;

  @OneToMany(mappedBy="ocs")
  private List<GuiaEncaminhamento> guias;

  public Ocs() {
	}

  public Ocs(final Integer id) {
    this.id = id;
  }
  
  @Override
  public String toString() {
    return this.getId() == null ? "OCS" : new StringBuilder(this.getCnpj()).append(" - ").append(this.getDescricao()).toString();
  }

  @Override
  public int compareTo(Ocs o) {
    return this.getDescricao().compareTo(o.getDescricao());
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
    Ocs other = (Ocs) obj;
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

  public String getCnpjf() {
    //final String CNPJ = "^\\d{3}.?\\d{3}.?\\d{3}/?\\d{3}-?\\d{2}$";
    return this.cnpj.isEmpty() ? "CNPJ" : new StringBuilder(cnpj.substring(0,2)).append(".")
                                                    .append(cnpj.substring(2,5)).append(".")
                                                    .append(cnpj.substring(5,8)).append("/")
                                                    .append(cnpj.substring(8,12)).append("-")
                                                    .append(cnpj.substring(12)).toString();
  }

  public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public List<OcsPm> getOcsPm() {
    return ocsPm;
  }

  public void setOcsPm(List<OcsPm> ocsPm) {
    this.ocsPm = ocsPm;
  }

  public OcsPm addOcsPm(OcsPm o) {
    this.getOcsPm().add(o);
    o.setOcs(this);
    return o;
  }

  public OcsPm removeOcsPm(OcsPm o) {
    this.getOcsPm().remove(o);
    o.setOcs(null);
    return o;
  }
  
  public List<Contrato> getContratos() {
		return this.contratos;
	}

	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}

	public Contrato addContrato(Contrato contrato) {
		getContratos().add(contrato);
		contrato.setOcs(this);
		return contrato;
	}

	public Contrato removeContrato(Contrato contrato) {
		getContratos().remove(contrato);
		contrato.setOcs(null);
		return contrato;
	}

  public String getEspecialidade() {
    return especialidade;
  }

  public void setEspecialidade(String especialidade) {
    this.especialidade = especialidade;
  }

  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public String getComplemento() {
    return complemento;
  }

  public void setComplemento(String complemento) {
    this.complemento = complemento;
  }

  public String getMunicipio() {
    return municipio;
  }

  public void setMunicipio(String municipio) {
    this.municipio = municipio;
  }

  public String getUf() {
    return uf;
  }

  public void setUf(String uf) {
    this.uf = uf;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public String getContato() {
    return contato;
  }

  public void setContato(String contato) {
    this.contato = contato;
  }

  public List<Dth> getDth() {
    return dth;
  }

  public void setDth(List<Dth> dth) {
    this.dth = dth;
  }

  public List<GuiaEncaminhamento> getGuias() {
    return guias;
  }

  public void setGuias(List<GuiaEncaminhamento> guias) {
    this.guias = guias;
  }
  
}
