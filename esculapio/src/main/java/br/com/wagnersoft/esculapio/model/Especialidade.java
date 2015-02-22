package br.com.wagnersoft.esculapio.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the especialidades database table.
 */
@Entity
@Table(name="especialidades")
@NamedQueries(value={
  @NamedQuery(name="Especialidade.findByDesc", query="SELECT e FROM Especialidade e WHERE UPPER(e.descricao) LIKE ?1"),
})
public class Especialidade implements Serializable {

  private static final long serialVersionUID = 1L;

	@Id
	private int codigo;

	private String descricao;

	public Especialidade() {
	}

  @Override
  public String toString() {
    return this.getDescricao() == null ? "ESPECIALIDADE" : this.getDescricao();
  }

	@Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + codigo;
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
    Especialidade other = (Especialidade) obj;
    if (codigo != other.codigo)
      return false;
    return true;
  }


  public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}