package br.com.wagnersoft.esculapio.services;

import br.com.wagnersoft.esculapio.dao.OcsDao;
import br.com.wagnersoft.esculapio.model.Ocs;

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

/** Servico de OCS. (Tabela OCS)
 * @author Abreu Lopes
 * @since 1.0
 * @version $Id$
 */
@Named("ocsService")
@RemoteProxy(name="ocsService")
public class OcsService {

  @Inject
  private OcsDao dao;
  
  public OcsService() {
  }

  public Long contar() {
    return this.dao.countAll();
  }
  
  @Transactional
  public void excluir(final Integer id) throws Exception {
    if (id == null) {
      throw new IllegalArgumentException("ID não foi informado");
    }
    this.dao.delete(new Ocs(id));
  }

  public List<Ocs> listar() throws Exception {
    final List<Ocs> lista = this.dao.findAll(null);
    Collections.sort(lista);
    return lista;
  }

  @RemoteMethod
  public Ocs[] listarOcs() throws Exception {
    final List<Ocs> lista = this.dao.findAll(null);
    Collections.sort(lista);
    return lista.toArray(new Ocs[0]);
  }
  
  @RemoteMethod
  public Ocs recuperar(final Integer id) throws Exception {
    return this.dao.findById(id);
  }

  public List<Ocs> listarCnpj(final String cnpj) throws Exception {
    return this.dao.findByNamedQuery("Ocs.findByCnpj", cnpj);
  }
  
  @Transactional
  public void salvar(final Ocs ocs) throws Exception {
    if (ocs != null && ocs.getId() != null) {
      final Ocs o = this.dao.findById(ocs.getId());
      o.setCnpj(ocs.getCnpj());
      o.setDescricao(ocs.getDescricao());
      o.setEspecialidade(ocs.getEspecialidade());
      o.setEndereco(ocs.getEndereco());
      o.setNumero(ocs.getNumero());
      o.setComplemento(ocs.getComplemento());
      o.setMunicipio(ocs.getMunicipio());
      o.setUf(ocs.getUf());
      o.setTelefone(ocs.getTelefone());
      o.setContato(ocs.getContato());
      this.dao.save(o);
    } else {
      this.dao.save(ocs);
    }
  }

  @Transactional
  public String carregarPlanilha(final File arquivo) throws Exception {
    if (arquivo == null) {
      throw new IllegalArgumentException("Planilha não foi informada");
    }
    final List<Ocs> lista = new ArrayList<>();
    int x = 0; //this.dao.execute("Ocs.excluir");
    Iterator<Row> rows;
    rows = new HSSFWorkbook(new POIFSFileSystem(new FileInputStream(arquivo))).getSheetAt(0).rowIterator();
    int cont = 0;
    inicio:
    while(rows.hasNext()) {
      cont += 1;
      final HSSFRow row = (HSSFRow) rows.next();
      if (cont == 1) {
        continue inicio;
      }
      final Ocs ocs = new Ocs();
      final Iterator<Cell> cells = row.cellIterator();
      while(cells.hasNext()) {
        final HSSFCell cell = (HSSFCell) cells.next();
        switch (cell.getColumnIndex()) {
        case 0:
          ocs.setDescricao(cell.getStringCellValue());
          break;
        case 4:
          String cnpj = cell.getStringCellValue();
          if (cnpj.isEmpty()){
            continue inicio;
          }
          ocs.setCnpj(cnpj.replace(".", "").replace("/", "").replace("-", ""));
          break;
        default:
          break;
        }
      }
      ocs.setMunicipio("São Paulo");
      ocs.setUf("SP");
      if (this.listarCnpj(ocs.getCnpjf()).size() == 0){
        lista.add(this.dao.save(ocs));
      }
    }
    return "Registros: excluídos = " + x + ", incluídos = " + lista.size();
  }
  
}
