package br.com.wagnersoft.esculapio.services;

import br.com.wagnersoft.esculapio.dao.EspecialidadeDao;
import br.com.wagnersoft.esculapio.dao.ProfissionalDao;
import br.com.wagnersoft.esculapio.model.Especialidade;
import br.com.wagnersoft.esculapio.model.Profissional;

import java.io.File;
import java.io.FileInputStream;
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

/** Serviço para Profissional. (Tabela PROFISSIONAL)
 * @author Abreu Lopes
 * @since 1.0
 * @version $Id$
 */
@Named("profissionalService")
@RemoteProxy(name="profissionalService")
public class ProfissionalService {

  @Inject
  private ProfissionalDao dao;

  @Inject
  private EspecialidadeDao especDao;

  /** Construtor. */
  public ProfissionalService() {
  }
  
  public List<Profissional> listar() {
    final List<Profissional> lista = this.dao.findAll(null);
    Collections.sort(lista);
    return lista;
  }

  @RemoteMethod
  public Profissional[] listarProfissional() throws Exception {
    return this.listar().toArray(new Profissional[0]);
  }
  
  @Transactional
  public String carregarPlanilha(final File arquivo) throws Exception {
    if (arquivo == null) {
      throw new IllegalArgumentException("Planilha não foi informada");
    }
    final List<Profissional> lista = new ArrayList<>();
    int x = this.dao.execute("Profissional.excluir");
    final Iterator<Row> rows = new HSSFWorkbook(new POIFSFileSystem(new FileInputStream(arquivo))).getSheetAt(0).rowIterator();
    int cont = 0;
    inicio:
    while(rows.hasNext()) {
      cont += 1;
      final HSSFRow row = (HSSFRow) rows.next();
      if (cont == 1) {
        continue inicio;
      }
      final Profissional p = new Profissional();
      final Iterator<Cell> cells = row.cellIterator();
      while(cells.hasNext()) {
        final HSSFCell cell = (HSSFCell) cells.next();
        switch (cell.getColumnIndex()) {
        case 0:
          final String nome = cell.getStringCellValue();
          if (nome != null) {
            p.setNome(nome);;
          } else {
            continue inicio;
          }
          break;
        case 1:
          final String crm = cell.getStringCellValue();
          if (crm != null) {
            p.setCrm(crm);
          } else {
            continue inicio;
          }
          break;
        case 2:
          final String uf = cell.getStringCellValue();
          if (uf != null) {
            p.setCrmUf(uf);
          } else {
            continue inicio;
          }
          break;
        case 3:
          final String espec = cell.getStringCellValue();
          if (espec != null) {
            final List<Especialidade> e = especDao.findByNamedQuery("Especialidade.findByDesc", espec);
            if (e != null && !e.isEmpty()) {
              p.setEspecialidade(e.get(0));
            }
          } else {
            continue inicio;
          }
          break;
        case 4:
          final String idt = cell.getStringCellValue();
          if (idt != null) {
            p.setIdt(idt);
          } else {
            continue inicio;
          }
          break;
        default:
          break;
        }
      }
      lista.add(this.dao.save(p));
    }
    return "Excluídos = " + x + " - Incluídos = " + lista.size();
  }
  
}
