package br.com.wagnersoft.esculapio.services;

import br.com.wagnersoft.esculapio.dao.DthDao;
import br.com.wagnersoft.esculapio.model.Dth;
import br.com.wagnersoft.esculapio.model.Ocs;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.transaction.annotation.Transactional;

/** Serviço para Diarias e Taxas Hospitalares. (Tabela DTH)
 * @author Abreu Lopes
 * @since 1.0
 * @version $Id$
 */
@Named("dthService")
@RemoteProxy(name="dthService")
public class DthService {

  @Inject
  private DthDao dao;
  
  public DthService() {
  }
  
  @Transactional
  public void excluir(final Integer id) throws Exception {
    if (id == null) {
      throw new IllegalArgumentException("Excluir: ID do OCS/PM não foi informado");
    }
    final Dth dth = new Dth();
    dth.setId(id);
    this.dao.delete(dth);
  }

  public List<Dth> listar() throws Exception {
    final List<Dth> lista = this.dao.findAll(null);
    if (lista != null && !lista.isEmpty()) {
      Collections.sort(lista);
    }
    return lista;
  }

  public List<Dth> listar(final Integer ocsId) throws Exception {
    final List<Dth> lista = this.dao.findByNamedQuery("Dth.findByOCS", ocsId);
    if (lista == null || lista.isEmpty()) {
      throw new Exception("Não há procedimentos cadastrados para este OCS.");
    }
    return lista;
  }
  
  @RemoteMethod
  public Dth[] listarPorDesc(final String descricao) throws Exception {
    final List<Dth> lista = this.dao.findByNamedQuery("Dth.findByDesc", descricao);
    if (lista != null && !lista.isEmpty()) {
      Collections.sort(lista);
    }
    return lista.toArray(new Dth[0]);
  }

  @RemoteMethod
  public Dth recuperar(final Integer id) throws Exception {
    if (id == null) {
      throw new IllegalArgumentException("ID do DTH não foi informado");
    }
    return this.dao.findById(id);
  }

  @Transactional
  public void salvar(final Dth entidade) throws Exception {
    if (entidade == null) {
      throw new IllegalArgumentException("DTH não foi informado");
    }
    if (entidade.getOcs() == null) {
      throw new IllegalArgumentException("OCS não foi informado");
    }
    if (entidade.getId() != null) {
      final Dth o = this.dao.findById(entidade.getId());
      o.setCodigo(entidade.getCodigo());
      o.setDescricao(entidade.getDescricao());
      o.setUnidade(entidade.getUnidade());
      o.setValor(entidade.getValor());
      o.setOcs(entidade.getOcs());
      this.dao.save(o);
    } else {
      this.dao.save(entidade);
    }
  }

  @Transactional
  public String carregarPlanilha(final Ocs ocs, final File arquivo) throws Exception {
    if (arquivo == null) {
      throw new IllegalArgumentException("Planilha não foi informada");
    }
    final List<Dth> lista = new ArrayList<>();
    int x = this.dao.execute("Dth.excluirPorOcs", ocs.getId());
    final Iterator<Row> rows = new HSSFWorkbook(new POIFSFileSystem(new FileInputStream(arquivo))).getSheetAt(0).rowIterator();
    int cont = 0;
    inicio:
    while(rows.hasNext()) {
      cont += 1;
      final HSSFRow row = (HSSFRow) rows.next();
      if (cont == 1) {
        continue inicio;
      }
      final Dth dth = new Dth();
      dth.setOcs(ocs);
      final Iterator<Cell> cells = row.cellIterator();
      while(cells.hasNext()) {
        final HSSFCell cell = (HSSFCell) cells.next();
        switch (cell.getColumnIndex()) {
        case 0:
          cell.setCellType(1);
          final String codigo = cell.getStringCellValue();
          if (codigo != null) {
            dth.setCodigo(codigo);
          } else {
            continue inicio;
          }
          break;
        case 1:
          cell.setCellType(1);
          final String descricao = cell.getStringCellValue();
          if (descricao != null) {
            dth.setDescricao(descricao.substring(0, descricao.length() > 255 ? 255 : descricao.length()));
          } else {
            continue inicio;
          }
          break;
        case 2:
          cell.setCellType(1);
          final String unidade = cell.getStringCellValue();
          if (unidade != null) {
            dth.setUnidade(unidade);
          } else {
            continue inicio;
          }
          break;
        case 3:
          if (cell.getCellType() != Cell.CELL_TYPE_NUMERIC) {
            throw new Exception("Coluna 4 (Valor) deve ser do tipo numérica");
          }
          final BigDecimal v = BigDecimal.valueOf(cell.getNumericCellValue());
          if (v != null) {
            dth.setValor(v);
          } else {
            continue inicio;
          }
          break;
        default:
          break;
        }
      }
      lista.add(this.dao.save(dth));
    }
    return "Excluídos = " + x + " - Incluídos = " + lista.size();
  }
  
}
