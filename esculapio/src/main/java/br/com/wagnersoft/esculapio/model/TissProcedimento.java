package br.com.wagnersoft.esculapio.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tiss_procedimentos")
public class TissProcedimento implements Comparable<TissProcedimento>, Serializable {

  private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String codigo;

	private String descricao;

	@ManyToOne
	@JoinColumn(name="tiss_tabela_codigo")
	private TissTabela tissTabela;

	public TissProcedimento() {
	}

  @Override
  public String toString() {
    return this.getDescricao() == null ? "PM" : new StringBuilder(this.getCodigo()).append(" - ").append(this.getDescricao()).toString();
  }
	
	@Override
  public int compareTo(TissProcedimento o) {
    return this.descricao.compareTo(o.getDescricao());
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
    TissProcedimento other = (TissProcedimento) obj;
    if (id != other.id)
      return false;
    return true;
  }

  public int getId() {
		return this.id;
	}

	public void setId(int id) {
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

	public TissTabela getTissTabela() {
		return this.tissTabela;
	}

	public void setTissTabela(TissTabela tissTabela) {
		this.tissTabela = tissTabela;
	}

}