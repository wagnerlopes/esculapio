package br.com.wagnersoft.esculapio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the procedimentos_medicos database table.
 */
@Entity
@Table(name="procedimentos_medicos")
@NamedQueries(value={
  @NamedQuery(name="ProcedimentoMedico.findByDesc", query="SELECT p FROM ProcedimentoMedico p WHERE UPPER(p.descricao) LIKE ?1"),
  @NamedQuery(name="ProcedimentoMedico.findByCodigo", query="SELECT p FROM ProcedimentoMedico p WHERE p.tuss = ?1")
})
public class ProcedimentoMedico implements Comparable<ProcedimentoMedico>, Serializable {

  private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String amb90;

	private String amb92;

	private String amb96;

	private String amb99;

	@Column(name="auxiliares_qtd")
	private int auxiliaresQtd;

	@Column(name="ch_qtd")
	private int chQtd;

	private String descricao;

	private String grupo;

	@Column(name="porte_anestesico")
	private int porteAnestesico;

	private String subgrupo;

	private String tuss;

	public ProcedimentoMedico() {
	}

	@Override
	public int compareTo(ProcedimentoMedico o) {
	  return this.getDescricao().compareTo(o.getDescricao());
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
		ProcedimentoMedico other = (ProcedimentoMedico) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
  public String toString() {
    return this.getDescricao() == null ? "PM" : new StringBuilder(this.getDescricao()).append(" - ").append(this.getTuss()).toString();
  }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAmb90() {
		return this.amb90;
	}

	public void setAmb90(String amb90) {
		this.amb90 = amb90;
	}

	public String getAmb92() {
		return this.amb92;
	}

	public void setAmb92(String amb92) {
		this.amb92 = amb92;
	}

	public String getAmb96() {
		return this.amb96;
	}

	public void setAmb96(String amb96) {
		this.amb96 = amb96;
	}

	public String getAmb99() {
		return this.amb99;
	}

	public void setAmb99(String amb99) {
		this.amb99 = amb99;
	}

	public int getAuxiliaresQtd() {
		return this.auxiliaresQtd;
	}

	public void setAuxiliaresQtd(int auxiliaresQtd) {
		this.auxiliaresQtd = auxiliaresQtd;
	}

	public int getChQtd() {
		return this.chQtd;
	}

	public void setChQtd(int chQtd) {
		this.chQtd = chQtd;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getGrupo() {
		return this.grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public int getPorteAnestesico() {
		return this.porteAnestesico;
	}

	public void setPorteAnestesico(int porteAnestesico) {
		this.porteAnestesico = porteAnestesico;
	}

	public String getSubgrupo() {
		return this.subgrupo;
	}

	public void setSubgrupo(String subgrupo) {
		this.subgrupo = subgrupo;
	}

	public String getTuss() {
		return this.tuss;
	}

  public String getTussf() {
    return this.tuss.isEmpty() ? "CNPJ" : new StringBuilder(tuss.substring(0,2)).append(".")
                                                    .append(tuss.substring(2,4)).append(".")
                                                    .append(tuss.substring(4,7)).append("-")
                                                    .append(tuss.substring(7)).toString();
  }
	
	public void setTuss(String tuss) {
		this.tuss = tuss;
	}
	
}