package br.com.wagnersoft.esculapio.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tiss_tipo_atendimento")
public class TipoAtendimento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String codigo;

	private String descricao;

	public TipoAtendimento() {
	}

	@Override
    public String toString() {
      return this.getDescricao() == null ? "TIPO" : new StringBuilder(this.getCodigo()).append(" - ").append(this.getDescricao()).toString();
    }
	
	public String getCodigo() {
    return codigo;
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

}