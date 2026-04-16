package br.com.wagnersoft.esculapio.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipo_termo")
public class TipoTermo implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  private int codigo;

  private String descricao;

  public TipoTermo() {
  }

  @Override
  public String toString() {
    return this.getDescricao() == null ? "TIPO" : new StringBuilder(Integer.toString(this.getCodigo())).append(" - ").append(this.getDescricao()).toString();
  }

  public int getCodigo() {
    return codigo;
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
