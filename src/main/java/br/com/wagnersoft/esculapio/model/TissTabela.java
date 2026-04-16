package br.com.wagnersoft.esculapio.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tiss_tabela")
public class TissTabela implements Comparable<TissTabela>, Serializable {

  private static final long serialVersionUID = 1L;

	@Id
	private String codigo;

	private String descricao;

	@OneToMany(mappedBy="tissTabela")
	private List<TissProcedimento> tissProcedimentos;

	public TissTabela() {
	}

  @Override
  public String toString() {
    return this.getDescricao() == null ? "TABELA" : new StringBuilder(this.getCodigo()).append(" - ").append(this.getDescricao()).toString();
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
    TissTabela other = (TissTabela) obj;
    if (codigo == null) {
      if (other.codigo != null)
        return false;
    } else if (!codigo.equals(other.codigo))
      return false;
    return true;
  }

  @Override
  public int compareTo(TissTabela o) {
    return this.getDescricao().compareTo(o.getDescricao());
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

	public List<TissProcedimento> getTissProcedimentos() {
		return this.tissProcedimentos;
	}

	public void setTissProcedimentos(List<TissProcedimento> tissProcedimentos) {
		this.tissProcedimentos = tissProcedimentos;
	}

	public TissProcedimento addTissProcedimento(TissProcedimento tissProcedimento) {
		getTissProcedimentos().add(tissProcedimento);
		tissProcedimento.setTissTabela(this);

		return tissProcedimento;
	}

	public TissProcedimento removeTissProcedimento(TissProcedimento tissProcedimento) {
		getTissProcedimentos().remove(tissProcedimento);
		tissProcedimento.setTissTabela(null);

		return tissProcedimento;
	}

}