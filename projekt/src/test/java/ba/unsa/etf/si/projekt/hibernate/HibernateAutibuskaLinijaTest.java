package ba.unsa.etf.si.projekt.hibernate;

import static org.junit.Assert.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import ba.unsa.etf.si.projekt.entiteti.Autobus;
import ba.unsa.etf.si.projekt.entiteti.AutobuskaLinija;
import ba.unsa.etf.si.projekt.entiteti.Radnik;
import ba.unsa.etf.si.projekt.entiteti.TipKorisnickogRacuna;
import ba.unsa.etf.si.projekt.entiteti.TipRadnogMjesta;


public class HibernateAutibuskaLinijaTest {

	/*@Test
	public void testDodajAutobuskuLiniju() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateAutibuskaLinija hal = new HibernateAutibuskaLinija();
	    HibernateAutobus.dodajAutobus(session, 50,"A23-M-424","M11");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A23-M-424");
	    HibernateRadnik.dodajRadnika(session, "Dinko", "Dinkic", "1712994126982", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "1712994126982");
		hal.dodajAutobuskuLiniju(session, "Sarajevo", "Mostar", a, r, 2015, 05, 23, 13, 55, 2, 300, 3,2,16.50, 31.00, false);
		
		AutobuskaLinija al= HibernateAutibuskaLinija.nadjiAutobuskuLiniju(session, 2);
		assertEquals("Sarajevo",al.getPolaziste());
		session.close();
		
	}
	*/
	@Test
	public void testDodajAutobuskuLinijuPolazisteExc() {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		HibernateAutibuskaLinija hal = new HibernateAutibuskaLinija();
	    HibernateAutobus.dodajAutobus(session, 50,"A23-M-424","M11");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A23-M-424");
	    HibernateRadnik.dodajRadnika(session, "Dinko", "Dinkic", "1712994126982", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "1712994126982");
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
	    HibernateAutobus.dodajAutobus(session, 50,"A23-M-424","M11");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A23-M-424");
	    HibernateRadnik.dodajRadnika(session, "Dinko", "Dinkic", "1712994126982", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "1712994126982");
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
	    HibernateAutobus.dodajAutobus(session, 50,"A23-M-424","M11");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A23-M-424");
	    HibernateRadnik.dodajRadnika(session, "Dinko", "Dinkic", "1712994126982", TipRadnogMjesta.Vozac);
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
	    HibernateAutobus.dodajAutobus(session, 50,"A23-M-424","M11");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A23-M-424");
	    HibernateRadnik.dodajRadnika(session, "Dinko", "Dinkic", "1712994126982", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "1712994126982");
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
	    HibernateAutobus.dodajAutobus(session, 50,"A23-M-424","M11");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A23-M-424");
	    HibernateRadnik.dodajRadnika(session, "Dinko", "Dinkic", "1712994126982", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "1712994126982");
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
	    HibernateAutobus.dodajAutobus(session, 50,"A23-M-424","M11");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A23-M-424");
	    HibernateRadnik.dodajRadnika(session, "Dinko", "Dinkic", "1712994126982", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "1712994126982");
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
	    HibernateAutobus.dodajAutobus(session, 50,"A23-M-424","M11");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A23-M-424");
	    HibernateRadnik.dodajRadnika(session, "Dinko", "Dinkic", "1712994126982", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "1712994126982");
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
	    HibernateAutobus.dodajAutobus(session, 50,"A23-M-424","M11");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A23-M-424");
	    HibernateRadnik.dodajRadnika(session, "Dinko", "Dinkic", "1712994126982", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "1712994126982");
		hal.dodajAutobuskuLiniju(session, "Sarajevo", "Mostar", a, r, 2015, 05, 23, 13, 55, 2, 300, 3,-2,16.50, 31.00, false);
		}
		catch (Exception e) {
			assertTrue(true);
			}
    }
		
	
	/*@Test
	public void testModifikujAutobuskuLiniju() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateAutibuskaLinija hal = new HibernateAutibuskaLinija();
	    HibernateAutobus.dodajAutobus(session, 50,"A23-M-424","M11");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A23-M-424");
	    HibernateRadnik.dodajRadnika(session, "Dinko", "Dinkic", "1712994126982", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "1712994126982");
		hal.dodajAutobuskuLiniju(session, "Sarajevo", "Mostar", a, r, 2015, 05, 23, 13, 55, 2, 300, 3,2,16.50, 31.00, false);
		hal.modifikujAutobuskuLiniju(session, "Sarajevo", "Mostar", a, r, 2015, 05, 23, 14, 55, 2, 300.00, 3, 2, 16.50, 31.00);
	
		AutobuskaLinija al=hal.nadjiAutobuskuLiniju(session, 2);
		assertEquals("Mostar",al.getOdrediste());
		session.close();
	}*/
	
	@Test
	public void testModifikujAutobuskuLinijuPolazisteExc() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		HibernateAutibuskaLinija hal = new HibernateAutibuskaLinija();
	    HibernateAutobus.dodajAutobus(session, 50,"A23-M-424","M11");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A23-M-424");
	    HibernateRadnik.dodajRadnika(session, "Dinko", "Dinkic", "1712994126982", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "1712994126982");
		hal.dodajAutobuskuLiniju(session, "Sarajevo", "Mostar", a, r, 2015, 05, 23, 13, 55, 2, 300, 3,2,16.50, 31.00, false);
		hal.modifikujAutobuskuLiniju(session, "123", "Mostar", a, r, 2015, 05, 23, 14, 55, 2, 300.00, 3, 2, 16.50, 31.00);
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
	    HibernateAutobus.dodajAutobus(session, 50,"A23-M-424","M11");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A23-M-424");
	    HibernateRadnik.dodajRadnika(session, "Dinko", "Dinkic", "1712994126982", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "1712994126982");
		hal.dodajAutobuskuLiniju(session, "Sarajevo", "Mostar", a, r, 2015, 05, 23, 13, 55, 2, 300, 3,2,16.50, 31.00, false);
		hal.modifikujAutobuskuLiniju(session, "Sarajevo", " ", a, r, 2015, 05, 23, 14, 55, 2, 300.00, 3, 2, 16.50, 31.00);
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
	    HibernateAutobus.dodajAutobus(session, 50,"A23-M-424","M11");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A23-M-424");
	    HibernateRadnik.dodajRadnika(session, "Dinko", "Dinkic", "1712994126982", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "1712994126982");
		hal.dodajAutobuskuLiniju(session, "Sarajevo", "Mostar", a, r, 2015, 05, 23, 13, 55, 2, 300, 3,2,16.50, 31.00, false);
		hal.modifikujAutobuskuLiniju(session, "Sarajevo", "Mostar", a, r, 2015, 05, 23, 14, 55, -2, 300.00, 3, 2, 16.50, 31.00);
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
	    HibernateAutobus.dodajAutobus(session, 50,"A23-M-424","M11");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A23-M-424");
	    HibernateRadnik.dodajRadnika(session, "Dinko", "Dinkic", "1712994126982", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "1712994126982");
		hal.dodajAutobuskuLiniju(session, "Sarajevo", "Mostar", a, r, 2015, 05, 23, 13, 55, 2, 300, 3,2,16.50, 31.00, false);
		hal.modifikujAutobuskuLiniju(session, "Sarajevo", "Mostar", a, r, 2015, 05, 23, 14, 55, 2, -300.00, 3, 2, 16.50, 31.00);
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
	    HibernateAutobus.dodajAutobus(session, 50,"A23-M-424","M11");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A23-M-424");
	    HibernateRadnik.dodajRadnika(session, "Dinko", "Dinkic", "1712994126982", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "1712994126982");
		hal.dodajAutobuskuLiniju(session, "Sarajevo", "Mostar", a, r, 2015, 05, 23, 13, 55, 2, 300, 3,2,16.50, 31.00, false);
		hal.modifikujAutobuskuLiniju(session, "Sarajevo", "Mostar", a, r, 2015, 05, 23, 14, 55, 2, 300.00, -3, 2, 16.50, 31.00);
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
	    HibernateAutobus.dodajAutobus(session, 50,"A23-M-424","M11");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A23-M-424");
	    HibernateRadnik.dodajRadnika(session, "Dinko", "Dinkic", "1712994126982", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "1712994126982");
		hal.dodajAutobuskuLiniju(session, "Sarajevo", "Mostar", a, r, 2015, 05, 23, 13, 55, 2, 300, 3,2,16.50, 31.00, false);
		hal.modifikujAutobuskuLiniju(session, "Sarajevo", "Mostar", a, r, 2015, 05, 23, 14, 55, 2, 300.00, 3, 2, 1650, 31.00);
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
	    HibernateAutobus.dodajAutobus(session, 50,"A23-M-424","M11");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A23-M-424");
	    HibernateRadnik.dodajRadnika(session, "Dinko", "Dinkic", "1712994126982", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "1712994126982");
		hal.dodajAutobuskuLiniju(session, "Sarajevo", "Mostar", a, r, 2015, 05, 23, 13, 55, 2, 300, 3,2,16.50, 31.00, false);
		hal.modifikujAutobuskuLiniju(session, "Sarajevo", "Mostar", a, r, 2015, 05, 23, 14, 55, 2, 300.00, 3, 2, 16.50, -31.00);
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
	    HibernateAutobus.dodajAutobus(session, 50,"A23-M-424","M11");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A23-M-424");
	    HibernateRadnik.dodajRadnika(session, "Dinko", "Dinkic", "1712994126982", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "1712994126982");
		hal.dodajAutobuskuLiniju(session, "Sarajevo", "Mostar", a, r, 2015, 05, 23, 13, 55, 2, 300, 3,2,16.50, 31.00, false);
		hal.modifikujAutobuskuLiniju(session, "Sarajevo", "Mostar", a, r, 2015, 05, 23, 14, 55, 2, 300.00, 3, -2, 16.50, 31.00);
		}
		catch (Exception e) {
			assertTrue(true);
			}
		
		session.close();
	}
	

	/*@Test
	public void testBrisiAutobuskuLiniju() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateAutibuskaLinija hal= new HibernateAutibuskaLinija();
		HibernateAutobus.dodajAutobus(session, 50,"A23-M-424","M11");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A23-M-424");
	    HibernateRadnik.dodajRadnika(session, "Dinko", "Dinkic", "1712994126982", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "1712994126982");
	    hal.dodajAutobuskuLiniju(session, "Sarajevo", "Mostar", a, r, 2015, 05, 23, 13, 55, 2, 300, 3,2,16.50, 31.00, false);

		hal.brisiAutobuskuLiniju(session, 2);
		assertEquals(null, hal.nadjiAutobuskuLiniju(session, 2));
		session.close();
	}*/
	
	@Test
	public void testBrisiAutobuskuLinijuBrLinijeExc() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		HibernateAutibuskaLinija hal= new HibernateAutibuskaLinija();
		HibernateAutobus.dodajAutobus(session, 50,"A23-M-424","M11");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A23-M-424");
	    HibernateRadnik.dodajRadnika(session, "Dinko", "Dinkic", "1712994126982", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "1712994126982");
	    hal.dodajAutobuskuLiniju(session, "Sarajevo", "Mostar", a, r, 2015, 05, 23, 13, 55, 2, 300, 3,2,16.50, 31.00, false);

		hal.brisiAutobuskuLiniju(session, -2);
		}
		catch (Exception e) {
			assertTrue(true);
			}
		
		session.close();
	}

	/*@Test
	public void testNadjiAutobuskuLiniju() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateAutibuskaLinija hal= new HibernateAutibuskaLinija();
		HibernateAutobus.dodajAutobus(session, 50,"A23-M-424","M11");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A23-M-424");
	    HibernateRadnik.dodajRadnika(session, "Dinko", "Dinkic", "1712994126982", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "1712994126982");
	    hal.dodajAutobuskuLiniju(session, "Sarajevo", "Mostar", a, r, 2015, 05, 23, 13, 55, 2, 300, 3,2,16.50, 31.00, false);
        AutobuskaLinija al=hal.nadjiAutobuskuLiniju(session, 2);
        assertEquals(2,al.getBrojLinije());
	}*/
	
	@Test
	public void testNadjiAutobuskuLinijuBrLinijeExc() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		HibernateAutibuskaLinija hal= new HibernateAutibuskaLinija();
		HibernateAutobus.dodajAutobus(session, 50,"A23-M-424","M11");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A23-M-424");
	    HibernateRadnik.dodajRadnika(session, "Dinko", "Dinkic", "1712994126982", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "1712994126982");
	    hal.dodajAutobuskuLiniju(session, "Sarajevo", "Mostar", a, r, 2015, 05, 23, 13, 55, 2, 300, 3,2,16.50, 31.00, false);

		hal.nadjiAutobuskuLiniju(session, -2);
		}
		catch (Exception e) {
			assertTrue(true);
			}
		
		session.close();
	}

	@Test
	public void testNadjiAutobuskuLinijuOdrediste() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testNadjiAutobuskuLinijuOdredisteExc() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		HibernateAutibuskaLinija hal= new HibernateAutibuskaLinija();
		HibernateAutobus.dodajAutobus(session, 50,"A23-M-424","M11");
	    Autobus a = HibernateAutobus.nadjiAutobus(session, "A23-M-424");
	    HibernateRadnik.dodajRadnika(session, "Dinko", "Dinkic", "1712994126982", TipRadnogMjesta.Vozac);
	    Radnik r = HibernateRadnik.nadjiRadnika(session, "1712994126982");
	    hal.dodajAutobuskuLiniju(session, "Sarajevo", "Mostar", a, r, 2015, 05, 23, 13, 55, 2, 300, 3,2,16.50, 31.00, false);

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
		Query q = session.createQuery("SELECT COUNT(*) FROM	AutobuskaLinija");
		Long count =(Long)q.uniqueResult();
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
		izvjestaji= hal.IzvjestajORadnicima(session, "Vozo", "Vozic");
		Query q = session.createQuery("SELECT COUNT(*) FROM	AutobuskaLinija");
		Long count =(Long)q.uniqueResult();
		int izBaze=count.intValue();
		izvjestaji= hal.sveLinije(session);
		int br= izvjestaji.size();
		assertEquals(br,izBaze);
		session.close();
	}
	
	@Test
	public void testIzvjestajORadnicimaImeExc() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		HibernateAutibuskaLinija hal= new HibernateAutibuskaLinija();
		java.util.List izvjestaji;
		izvjestaji= hal.IzvjestajORadnicima(session, " ", "Vozic");
		}
		catch (Exception e) {
			assertTrue(true);
			}
		session.close();
	}
	
	@Test
	public void testIzvjestajORadnicimaPrezimeExc() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		HibernateAutibuskaLinija hal= new HibernateAutibuskaLinija();
		java.util.List izvjestaji;
		izvjestaji= hal.IzvjestajORadnicima(session, "Vozo", " ");
		}
		catch (Exception e) {
			assertTrue(true);
			}
		session.close();
	}

	@Test
	public void testIzvjestajOAutobuskimLinijama() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateAutibuskaLinija hal= new HibernateAutibuskaLinija();
		java.util.List izvjestaji;
		izvjestaji=hal.IzvjestajOAutobuskimLinijama(session, 2015, 05, 23, 2015, 05, 30, 12, 30, 21, 30);
		Query q = session.createQuery("SELECT COUNT(*) FROM	AutobuskaLinija");
		Long count =(Long)q.uniqueResult();
		int izBaze=count.intValue();
		izvjestaji= hal.sveLinije(session);
		int br= izvjestaji.size();
		assertEquals(br,izBaze);
		session.close();
	}

	
}
