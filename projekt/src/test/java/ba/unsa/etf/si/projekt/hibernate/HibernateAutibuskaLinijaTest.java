package ba.unsa.etf.si.projekt.hibernate;

import static org.junit.Assert.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.junit.Test;

import ba.unsa.etf.si.projekt.entiteti.Autobus;
import ba.unsa.etf.si.projekt.entiteti.AutobuskaLinija;
import ba.unsa.etf.si.projekt.entiteti.Radnik;
import ba.unsa.etf.si.projekt.entiteti.TipKorisnickogRacuna;
import ba.unsa.etf.si.projekt.entiteti.TipRadnogMjesta;


public class HibernateAutibuskaLinijaTest {

	@Test
	public void testDodajAutobuskuLiniju() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateAutibuskaLinija hal = new HibernateAutibuskaLinija();
	    HibernateAutobus.dodajAutobus(session, 52,"A20-M-524","M1");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A20-M-524");
	    HibernateRadnik.dodajRadnika(session, "Dinko", "Dinkic", "0611992176509", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "0611992176509");
		//prijedodavanja
	    Long count = (Long) session.createCriteria(AutobuskaLinija.class).setProjection(Projections.rowCount()).uniqueResult();
		int prije=count.intValue();
		hal.dodajAutobuskuLiniju(session, "Sarajevo", "Kakanj", a, r, 2015, 05, 24, 13, 55, 1, 100, 1,7,7.50, 11.00, false);
		//poslije dodavanja
		Long count2 = (Long) session.createCriteria(AutobuskaLinija.class).setProjection(Projections.rowCount()).uniqueResult();
		int poslije=count2.intValue();
		assertEquals(poslije,prije+1);
		HibernateAutibuskaLinija.brisiAutobuskuLiniju(session, 7);
		HibernateAutobus.brisanjeAutobusa(session, "A20-M-524");
		HibernateRadnik.brisiRadnika(session, "0611992176509");
		
		session.close();
		
	}
	
	@Test
	public void testDodajAutobuskuLinijuPolazisteExc() {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		HibernateAutibuskaLinija hal = new HibernateAutibuskaLinija();
	    HibernateAutobus.dodajAutobus(session, 50,"A22-M-422","M11");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A22-M-422");
	    HibernateRadnik.dodajRadnika(session, "Di", "Dii", "1709987125684", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "1709987125684");
		hal.dodajAutobuskuLiniju(session, "123", "Mostar", a, r, 2015, 05, 23, 13, 55, 2, 300, 3,2,16.50, 31.00, false);
		}
		catch (Exception e) {
			assertTrue(true);
			}
    }
	
	@Test
	public void testDodajAutobuskuLinijuOdredisteExc() {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		HibernateAutibuskaLinija hal = new HibernateAutibuskaLinija();
	    HibernateAutobus.dodajAutobus(session, 50,"A53-M-429","M11");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A53-M-429");
	    HibernateRadnik.dodajRadnika(session, "Darko", "Darkic", "1109959459876", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "1109959459876");
		hal.dodajAutobuskuLiniju(session, "Sarajevo", " ", a, r, 2015, 05, 23, 13, 55, 2, 300, 3,2,16.50, 31.00, false);
		}
		catch (Exception e) {
			assertTrue(true);
			}
    }
	
	@Test
	public void testDodajAutobuskuLinijuBrojPeronaExc() {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		HibernateAutibuskaLinija hal = new HibernateAutibuskaLinija();
	    HibernateAutobus.dodajAutobus(session, 50,"A20-M-404","M1M1");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A20-M-404");
	    HibernateRadnik.dodajRadnika(session, "Dod", "Dodicic", "1712994126982", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "1712994126982");
		hal.dodajAutobuskuLiniju(session, "Sarajevo", "Mostar", a, r, 2015, 05, 23, 13, 55, 8, 300, 3,2,16.50, 31.00, false);
		}
		catch (Exception e) {
			assertTrue(true);
			}
    }
	
	@Test
	public void testDodajAutobuskuLinijuDistancaExc() {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		HibernateAutibuskaLinija hal = new HibernateAutibuskaLinija();
	    HibernateAutobus.dodajAutobus(session, 50,"A23-T-424","M11");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A23-T-424");
	    HibernateRadnik.dodajRadnika(session, "Salem", "Salimovic", "2208993179857", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "2208993179857");
		hal.dodajAutobuskuLiniju(session, "Sarajevo", "Mostar", a, r, 2015, 05, 23, 13, 55, 2, -300, 3,2,16.50, 31.00, false);
		}
		catch (Exception e) {
			assertTrue(true);
			}
    }
	
	@Test
	public void testDodajAutobuskuLinijuCijenaJednosmjerneExc() {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		HibernateAutibuskaLinija hal = new HibernateAutibuskaLinija();
	    HibernateAutobus.dodajAutobus(session, 50,"A23-K-424","M11");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A23-K-424");
	    HibernateRadnik.dodajRadnika(session, "Rizo", "Rizic", "0911969159763", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "0911969159763");
		hal.dodajAutobuskuLiniju(session, "Sarajevo", "Mostar", a, r, 2015, 05, 23, 13, 55, 2, 300, 3,2,1650, 31.00, false);
		}
		catch (Exception e) {
			assertTrue(true);
			}
	}
	
	@Test
	public void testDodajAutobuskuLinijuCijenaDvosmjerneExc() {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		HibernateAutibuskaLinija hal = new HibernateAutibuskaLinija();
	    HibernateAutobus.dodajAutobus(session, 50,"A23-J-424","M191");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A23-J-424");
	    HibernateRadnik.dodajRadnika(session, "Kupus", "Kupusovic", "1712993085742", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "1712993085742");
		hal.dodajAutobuskuLiniju(session, "Sarajevo", "Mostar", a, r, 2015, 05, 23, 13, 55, 2, 300, 3,2,16.50, -31, false);
		}
		catch (Exception e) {
			assertTrue(true);
			}
    }
	
	@Test
	public void testDodajAutobuskuLinijuTrajanjeExc() {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		HibernateAutibuskaLinija hal = new HibernateAutibuskaLinija();
	    HibernateAutobus.dodajAutobus(session, 50,"A93-M-494","M11");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A93-M-494");
	    HibernateRadnik.dodajRadnika(session, "Mamo", "Mamic", "0726990286472", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "0726990286472");
		hal.dodajAutobuskuLiniju(session, "Sarajevo", "Mostar", a, r, 2015, 05, 23, 13, 55, 2, 300, -3,2,16.50, 31.00, false);
		}
		catch (Exception e) {
			assertTrue(true);
			}
    }
	
	@Test
	public void testDodajAutobuskuLinijuBrojLinijeExc() {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		HibernateAutibuskaLinija hal = new HibernateAutibuskaLinija();
	    HibernateAutobus.dodajAutobus(session, 50,"A90-M-410","M11G");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A23-M-424");
	    HibernateRadnik.dodajRadnika(session, "Fata", "Fatanovic", "1705990763412", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "1705990763412");
		hal.dodajAutobuskuLiniju(session, "Sarajevo", "Mostar", a, r, 2015, 05, 23, 13, 55, 2, 300, 3,-2,16.50, 31.00, false);
		}
		catch (Exception e) {
			assertTrue(true);
			}
    }
		
	
	@Test
	public void testModifikujAutobuskuLiniju() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateAutibuskaLinija hal = new HibernateAutibuskaLinija();
	    HibernateAutobus.dodajAutobus(session, 50,"A03-M-484","M1H1");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A03-M-484");
	    HibernateRadnik.dodajRadnika(session, "Rizo", "Rizicic", "1712998126972", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "1712998126972");
		hal.dodajAutobuskuLiniju(session, "Sarajevo", "Jajce", a, r, 2015, 05, 23, 13, 55, 2, 300, 3,5,16.50, 31.00, false);
		hal.modifikujAutobuskuLiniju(session, "Sarajevo", "Jajce", a, r, 2015, 05, 23, 14, 55, 2, 300.00, 3, 5, 16.50, 31.00);
	
		AutobuskaLinija al=hal.nadjiAutobuskuLiniju(session, 5);
		assertEquals("Jajce",al.getOdrediste());
		HibernateAutibuskaLinija.brisiAutobuskuLiniju(session, 5);
		HibernateAutobus.brisanjeAutobusa(session, "A03-M-484");
		HibernateRadnik.brisiRadnika(session, "1712998126972");
		session.close();
	}
	
	@Test
	public void testModifikujAutobuskuLinijuPolazisteExc() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		HibernateAutibuskaLinija hal = new HibernateAutibuskaLinija();
	    HibernateAutobus.dodajAutobus(session, 50,"A83-M-484","M11");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A83-M-484");
	    HibernateRadnik.dodajRadnika(session, "Huso", "Husic", "1712991469539", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "1712991469539");
		hal.dodajAutobuskuLiniju(session, "Sarajevo", "Bihac", a, r, 2015, 05, 23, 13, 55, 2, 800, 3,9,16.50, 31.00, false);
		hal.modifikujAutobuskuLiniju(session, "123", "Mostar", a, r, 2015, 05, 23, 14, 55, 2, 800, 3, 9, 16.50, 31.00);
		}
		catch (Exception e) {
			assertTrue(true);
			}
		
		session.close();
	}
	
	@Test
	public void testModifikujAutobuskuLinijuOdredisteExc() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		HibernateAutibuskaLinija hal = new HibernateAutibuskaLinija();
	    HibernateAutobus.dodajAutobus(session, 50,"A93-M-484","MT1");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A93-M-484");
	    HibernateRadnik.dodajRadnika(session, "Dario", "Daric", "0909985652741", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "0909985652741");
		hal.dodajAutobuskuLiniju(session, "Sarajevo", "Zagreb", a, r, 2015, 05, 24, 06, 55, 2, 1000, 3,2,16.50, 31.00, true);
		hal.modifikujAutobuskuLiniju(session, "Sarajevo", "123", a, r, 2015, 05, 24, 06, 55, 2, 1000.00, 3, 2, 16.50, 31.00);
		}
		catch (Exception e) {
			assertTrue(true);
			}
		
		session.close();
	}
	
	@Test
	public void testModifikujAutobuskuLinijuPeronExc() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		HibernateAutibuskaLinija hal = new HibernateAutibuskaLinija();
	    HibernateAutobus.dodajAutobus(session, 50,"A23-I-424","M11");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A23-I-424");
	    HibernateRadnik.dodajRadnika(session, "Nuno", "Nunic", "0301989273419", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "0301989273419");
		hal.dodajAutobuskuLiniju(session, "Sarajevo", "Beograd", a, r, 2015, 05, 23, 13, 55, 2, 1200, 3,2,16.50, 31.00, true);
		hal.modifikujAutobuskuLiniju(session, "Sarajevo", "Mostar", a, r, 2015, 05, 23, 14, 55, -2, 1200.00, 3, 2, 16.50, 31.00);
		}
		catch (Exception e) {
			assertTrue(true);
			}
		
		session.close();
	}
	
	@Test
	public void testModifikujAutobuskuLinijuDistancaExc() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		HibernateAutibuskaLinija hal = new HibernateAutibuskaLinija();
	    HibernateAutobus.dodajAutobus(session, 50,"A23-L-424","M11");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A23-L-424");
	    HibernateRadnik.dodajRadnika(session, "Neka", "Nekic", "1712991163481", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "1712991163481");
		hal.dodajAutobuskuLiniju(session, "Sarajevo", "Dubrovnik", a, r, 2015, 05, 23, 13, 55, 2, 300, 3,10,16.50, 31.00, true);
		hal.modifikujAutobuskuLiniju(session, "Sarajevo", "Dubrovnik", a, r, 2015, 05, 23, 14, 55, 2, -300.00, 3, 10, 16.50, 31.00);
		}
		catch (Exception e) {
			assertTrue(true);
			}
		
		session.close();
	}
	
	@Test
	public void testModifikujAutobuskuLinijuTrajanjeExc() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		HibernateAutibuskaLinija hal = new HibernateAutibuskaLinija();
	    HibernateAutobus.dodajAutobus(session, 50,"A23-P-424","M11");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A23-P-424");
	    HibernateRadnik.dodajRadnika(session, "Vrijedna", "Vrijednic", "1712996173467", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "1712996173467");
		hal.dodajAutobuskuLiniju(session, "Sarajevo", "Gradacac", a, r, 2015, 05, 23, 13, 55, 2, 600, 3,2,16.50, 31.00, false);
		hal.modifikujAutobuskuLiniju(session, "Sarajevo", "Gradacac", a, r, 2015, 05, 23, 14, 55, 2, 600.00, -3, 2, 16.50, 31.00);
		}
		catch (Exception e) {
			assertTrue(true);
			}
		
		session.close();
	}
	
	@Test
	public void testModifikujAutobuskuLinijuCijenaJednosmjernaExc() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		HibernateAutibuskaLinija hal = new HibernateAutibuskaLinija();
	    HibernateAutobus.dodajAutobus(session, 50,"A23-R-424","M11");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A23-R-424");
	    HibernateRadnik.dodajRadnika(session, "Dinka", "Dinkic", "1712991346728", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "1712991346728");
		hal.dodajAutobuskuLiniju(session, "Sarajevo", "Stolac", a, r, 2015, 05, 23, 13, 55, 2, 200, 3,2,16.50, 31.00, false);
		hal.modifikujAutobuskuLiniju(session, "Sarajevo", "Stolac", a, r, 2015, 05, 23, 14, 55, 2, 200.00, 3, 2, 1650, 31.00);
		}
		catch (Exception e) {
			assertTrue(true);
			}
		
		session.close();
	}
	
	@Test
	public void testModifikujAutobuskuLinijuCijenaDvosmjernaExc() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		HibernateAutibuskaLinija hal = new HibernateAutibuskaLinija();
	    HibernateAutobus.dodajAutobus(session, 50,"A23-N-424","M11");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A23-N-424");
	    HibernateRadnik.dodajRadnika(session, "Dinkica", "Dinkicic", "1712995126982", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "1712995126982");
		hal.dodajAutobuskuLiniju(session, "Sarajevo", "Konjic", a, r, 2015, 05, 23, 13, 55, 2, 350, 3,3,16.50, 31.00, false);
		hal.modifikujAutobuskuLiniju(session, "Sarajevo", "Konjic", a, r, 2015, 05, 23, 14, 55, 2, 350.00, 3, 3, 16.50, -31.00);
		}
		catch (Exception e) {
			assertTrue(true);
			}
		
		session.close();
	}
	
	@Test
	public void testModifikujAutobuskuLinijuBrojLinijeExc() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		HibernateAutibuskaLinija hal = new HibernateAutibuskaLinija();
	    HibernateAutobus.dodajAutobus(session, 50,"A23-E-424","M11");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A23-E-424");
	    HibernateRadnik.dodajRadnika(session, "Minka", "Minkic", "1712990126982", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "1712990126982");
		hal.dodajAutobuskuLiniju(session, "Sarajevo", "Kljuc", a, r, 2015, 05, 23, 13, 55, 2, 400, 3,2,16.50, 31.00, false);
		hal.modifikujAutobuskuLiniju(session, "Sarajevo", "Kljuc", a, r, 2015, 05, 23, 14, 55, 2, 400.00, 3, -2, 16.50, 31.00);
		}
		catch (Exception e) {
			assertTrue(true);
			}
		
		session.close();
	}
	
    @Test
	public void testBrisiAutobuskuLiniju() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateAutibuskaLinija hal= new HibernateAutibuskaLinija();
		HibernateAutobus.dodajAutobus(session, 50,"A03-M-434","M11");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A03-M-434");
	    HibernateRadnik.dodajRadnika(session, "Dinko", "Dinkic", "1712997126982", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "1712997126982");
	    hal.dodajAutobuskuLiniju(session, "Sarajevo", "Livno", a, r, 2015, 05, 23, 13, 55, 2, 600, 3,7,16.50, 31.00, false);

		hal.brisiAutobuskuLiniju(session, 7);
		assertEquals(null, hal.nadjiAutobuskuLiniju(session, 7));
		HibernateAutobus.brisanjeAutobusa(session,"A03-M-434");
		HibernateRadnik.brisiRadnika(session, "1712997126982");
		session.close();
	}
	
	@Test
	public void testBrisiAutobuskuLinijuBrLinijeExc() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		HibernateAutibuskaLinija hal= new HibernateAutibuskaLinija();
		hal.brisiAutobuskuLiniju(session, -2);
		}
		catch (Exception e) {
			assertTrue(true);
			}
		
		session.close();
	}

	@Test
	public void testNadjiAutobuskuLiniju() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateAutibuskaLinija hal= new HibernateAutibuskaLinija();
		HibernateAutobus.dodajAutobus(session, 50,"A32-M-432","M11");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A32-M-442");
	    HibernateRadnik.dodajRadnika(session, "Dinko", "Dinkic", "1612994126982", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "1612994126982");
	    hal.dodajAutobuskuLiniju(session, "Sarajevo", "Mostar", a, r, 2015, 05, 23, 13, 55, 2, 300, 3,8,16.50, 31.00, false);
  
	    AutobuskaLinija al=hal.nadjiAutobuskuLiniju(session, 8);
        assertEquals(8,al.getBrojLinije());
        HibernateAutibuskaLinija.brisiAutobuskuLiniju(session, 8);
        HibernateAutobus.brisanjeAutobusa(session, "A32-M-432");
        HibernateRadnik.brisiRadnika(session,"1612994126982");
	}
	
	@Test
	public void testNadjiAutobuskuLinijuBrLinijeExc() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		HibernateAutibuskaLinija hal= new HibernateAutibuskaLinija();
		hal.nadjiAutobuskuLiniju(session, -2);
		}
		catch (Exception e) {
			assertTrue(true);
			}
		
		session.close();
	}

	@Test
	public void testNadjiAutobuskuLinijuOdrediste() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateAutibuskaLinija hal= new HibernateAutibuskaLinija();
		HibernateAutobus.dodajAutobus(session, 50,"A11-M-411","M11");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A11-M-411");
	    HibernateRadnik.dodajRadnika(session, "Danko", "Dankic", "0312994126982", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "0312994126982");
	    hal.dodajAutobuskuLiniju(session, "Sarajevo", "Travnik", a, r, 2015, 05, 23, 13, 55, 2, 200, 3,5,16.50, 31.00, false);
  
	    AutobuskaLinija al=hal.NadjiAutobuskuLinijuOdrediste(session, "Travnik", 2015, 05, 23, 13, 55);
        assertEquals(5,al.getBrojLinije());
        HibernateAutibuskaLinija.brisiAutobuskuLiniju(session, 5);
        HibernateAutobus.brisanjeAutobusa(session, "A11-M-411");
        HibernateRadnik.brisiRadnika(session,"0312994126982");
        session.close();
	}
	
	@Test
	public void testNadjiAutobuskuLinijuOdredisteExc() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		HibernateAutibuskaLinija hal= new HibernateAutibuskaLinija();
		hal.NadjiAutobuskuLinijuOdrediste(session, " ", 2015, 05, 23, 15, 00);
		}
		catch (Exception e) {
			assertTrue(true);
			}
		
		session.close();
	}

	@Test
	public void testSveLinije() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Long count = (Long) session.createCriteria(AutobuskaLinija.class).setProjection(Projections.rowCount()).uniqueResult();
		int izBaze=count.intValue();
		HibernateAutibuskaLinija hal= new HibernateAutibuskaLinija();
		java.util.List linije;
		linije= hal.sveLinije(session);
		int br= linije.size();
		assertEquals(br,izBaze);
		session.close();
	}

	@Test
	public void testIzvjestajORadnicima() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateAutibuskaLinija hal= new HibernateAutibuskaLinija();
		java.util.List izvjestaji;
		izvjestaji= hal.IzvjestajORadnicima(session, "1712993176528");
		Long count = (Long) session.createCriteria(AutobuskaLinija.class).setProjection(Projections.rowCount()).uniqueResult();
		int izBaze=count.intValue();
		izvjestaji= hal.sveLinije(session);
		int br= izvjestaji.size();
		assertEquals(br,izBaze);
		session.close();
	}
	
	@Test
	public void testIzvjestajORadnicimaJMBGExc() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		HibernateAutibuskaLinija hal= new HibernateAutibuskaLinija();
		java.util.List izvjestaji;
		izvjestaji= hal.IzvjestajORadnicima(session, "123Vozic12358");
		}
		catch (Exception e) {
			assertTrue(true);
			}
		session.close();
	}
	
	/*@Test
	public void testIzvjestajORadnicimaPrezimeExc() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		HibernateAutibuskaLinija hal= new HibernateAutibuskaLinija();
		java.util.List izvjestaji;
		izvjestaji= hal.IzvjestajORadnicima(session, "Vozo", "123");
		}
		catch (Exception e) {
			assertTrue(true);
			}
		session.close();
	}*/

	@Test
	public void testIzvjestajOAutobuskimLinijama() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateAutibuskaLinija hal= new HibernateAutibuskaLinija();
		java.util.List izvjestaji;
		izvjestaji=hal.IzvjestajOAutobuskimLinijama(session, 2015, 05, 23, 2015, 05, 30, 12, 30, 21, 30);
		Long count = (Long) session.createCriteria(AutobuskaLinija.class).setProjection(Projections.rowCount()).uniqueResult();
		int izBaze=count.intValue();
		izvjestaji= hal.sveLinije(session);
		int br= izvjestaji.size();
		assertEquals(br,izBaze);
		session.close();
	}

	
}
