package esculapio;

import junit.framework.TestCase;

import org.junit.Test;

import br.com.wagnersoft.esculapio.model.Ocs;

public class OcsTest extends TestCase {

  protected void setUp() throws Exception {
    super.setUp();
  }

  @Test
  public void testOcs() throws Exception {
    Ocs ocs = new Ocs();
    ocs.setCnpj("123456789");
    assertTrue(true);  
  }
  
}
