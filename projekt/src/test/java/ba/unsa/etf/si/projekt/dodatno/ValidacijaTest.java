package ba.unsa.etf.si.projekt.dodatno;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ValidacijaTest {

	@Test
	public void testValidirajKorisnickoImeVecPostoji() {
		List<String> imena = new ArrayList<String>();
		imena.add("dahmic");
		imena.add("dahmic1");
		assertEquals(false, Validacija.validirajKorisnickoIme(imena, "dahmic1"));
	}
	
	@Test
	public void testValidirajKorisnickoImeNePostoji() {
		List<String> imena = new ArrayList<String>();
		imena.add("user1");
		imena.add("user2");
		assertEquals(true, Validacija.validirajKorisnickoIme(imena, "sbotulja"));
	}

	@Test
	public void testRadnaPozicijaNePostoji() {
		assertEquals(false, Validacija.radnaPozicija("Direktor"));
	}
	
	@Test
	public void testRadnaPozicijaIspravna() {
		assertEquals(true, Validacija.radnaPozicija("SalterskiRadnik"));
	}

	@Test
	public void testValidirajMjestoPostoji() {
		assertEquals(true, Validacija.validirajMjesto("45", "50"));
	}
	
	@Test
	public void testValidirajMjestoNePostoji() {
		assertEquals(false, Validacija.validirajMjesto("56", "54"));
	}

	@Test
	public void testValidirajBrojAutobusaNePostoji() {
		List<String> autobusi = new ArrayList<String>();
		autobusi.add("205");
		autobusi.add("63");
		assertEquals(true, Validacija.validirajBrojAutobusa(autobusi, "17"));
	}
	
	@Test
	public void testValidirajBrojAutobusaVecPostoji() {
		List<String> autobusi = new ArrayList<String>();
		autobusi.add("105");
		autobusi.add("78");
		assertEquals(false, Validacija.validirajBrojAutobusa(autobusi, "78"));
	}

	@Test
	public void testValidirajBrojLinijeNePostoji() {
		List<String> linije = new ArrayList<String>();
		linije.add("90");
		assertEquals(true, Validacija.validirajBrojLinije(linije, "15"));
	}
	
	@Test
	public void testValidirajBrojLinijeVecPostoji() {
		List<String> linije = new ArrayList<String>();
		linije.add("20");
		assertEquals(false, Validacija.validirajBrojLinije(linije, "20"));
	}

	@Test
	public void testValidirajVrijemeIspravnoZapisano() {
		assertEquals(true, Validacija.validirajVrijeme("15:20"));
	}
	
	@Test
	public void testValidirajVrijemePogresnoZapisano() {
		assertEquals(false, Validacija.validirajVrijeme("15-20"));
	}

}
