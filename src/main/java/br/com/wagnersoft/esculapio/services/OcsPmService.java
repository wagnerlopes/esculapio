package br.com.wagnersoft.esculapio.services;

import br.com.wagnersoft.esculapio.dao.OcsPmDao;
import br.com.wagnersoft.esculapio.model.Ocs;
import br.com.wagnersoft.esculapio.model.OcsPm;

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

/** Serviço para OCS-PM. (Tabela OCS_PM)
 * @author Abreu Lopes
 * @since 1.0
 * @version $Id$
 */
@Named("ocsPmService")
@RemoteProxy(name="ocsPmService")
public class OcsPmService {

  @Inject
  private OcsPmDao dao;
  
  @Inject
  private ProcedimentoMedicoService pmService;

  public OcsPmService() {
  }
  
  @Transactional
  public void excluir(final Integer id) throws Exception {
    if (id == null) {
      throw new IllegalArgumentException("Excluir: ID do OCS/PM não foi informado");
    }
    this.dao.delete(new OcsPm(id));
  }

  public List<OcsPm> listar(final Integer ocsId) throws Exception {
    final List<OcsPm> lista = this.dao.findByNamedQuery("OcsPm.findByOCS", ocsId);
    if (lista == null || lista.isEmpty()) {
      throw new Exception("Não há procedimentos cadastrados para este OCS.");
    }
    return lista;
  }
  
  @RemoteMethod
  public OcsPm[] listarOcsPM(final Integer id, final String tipo) throws Exception {
    List<OcsPm> lista = null;
    if ("ocs".equals(tipo)){
      lista = this.dao.findByNamedQuery("OcsPm.findByOCS", id);
    }
    else {
      lista = this.dao.findByNamedQuery("OcsPm.findByPM", id);
    }
    Collections.sort(lista);
    return lista.toArray(new OcsPm[0]);
  }

  @RemoteMethod
  public OcsPm recuperar(final Integer id) throws Exception {
    if (id == null) {
      throw new IllegalArgumentException("ID do OCS/PM não foi informado");
    }
    return this.dao.findById(id);
  }

  @Transactional
  public void salvar(final OcsPm entidade) throws Exception {
    if (entidade == null) {
      throw new IllegalArgumentException("OCS/PM não foi informado");
    }
    if (entidade.getOcs() == null) {
      throw new IllegalArgumentException("OCS não foi informado");
    }
    if (entidade.getPm() == null) {
      throw new IllegalArgumentException("PM não foi informado");
    }
    if (entidade.getId() != null) {
      final OcsPm o = this.dao.findById(entidade.getId());
      o.setOcs(entidade.getOcs());
      o.setPm(entidade.getPm());
      o.setChQtd(entidade.getChQtd());
      o.setValor(entidade.getValor());
      this.dao.save(o);
    } else {
      this.dao.save(entidade);
    }
  }

  @SuppressWarnings("resource")
@Transactional
  public String carregarPlanilha(final Ocs ocs, final File arquivo) throws Exception {
    if (arquivo == null) {
      throw new IllegalArgumentException("Planilha não foi informada");
    }
    final List<OcsPm> lista = new ArrayList<>();
    int x = this.dao.execute("OcsPm.excluirPorOcs", ocs.getId());
    final Iterator<Row> rows = new HSSFWorkbook(new POIFSFileSystem(new FileInputStream(arquivo))).getSheetAt(0).rowIterator();
    int cont = 0;
    inicio:
    while(rows.hasNext()) {
      cont += 1;
      final HSSFRow row = (HSSFRow) rows.next();
      if (cont == 1) {
        continue inicio;
      }
      final OcsPm opm = new OcsPm();
      opm.setOcs(ocs);
      final Iterator<Cell> cells = row.cellIterator();
      while(cells.hasNext()) {
        final HSSFCell cell = (HSSFCell) cells.next();
        //cell.setCellType(0);
        switch (cell.getColumnIndex()) {
        case 0:
          //final String cod = cell.getStringCellValue();
          final String cod = Long.toString((long) cell.getNumericCellValue());
          if (cod != null) {
            try {
              opm.setPm(this.pmService.listarPorCodigo(cod.trim()).get(0));
            } catch(Exception e){
              continue inicio;
            }
          }
          break;
        case 2:
          double valor = 0;
          try {
            valor = cell.getNumericCellValue();  
          } catch (Exception e) {
          }
          opm.setValor(BigDecimal.valueOf(valor));
          break;
        default:
          break;
        }
      }
      opm.setChQtd(0);
      lista.add(this.dao.save(opm));
    }
    return "Excluídos = " + x + " - Incluídos = " + lista.size();
  }
  
}
