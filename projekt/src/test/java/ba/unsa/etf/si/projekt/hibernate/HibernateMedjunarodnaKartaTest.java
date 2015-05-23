package ba.unsa.etf.si.projekt.hibernate;

import static org.junit.Assert.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import ba.unsa.etf.si.projekt.entiteti.AutobuskaLinija;
import ba.unsa.etf.si.projekt.entiteti.Radnik;
import ba.unsa.etf.si.projekt.entiteti.TipKarte;
import ba.unsa.etf.si.projekt.entiteti.TipRadnogMjesta;

public class HibernateMedjunarodnaKartaTest {
	
	@Test
	public void testDodajKartu() {
		/*Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateMedjunarodnaKarta hmk = new HibernateMedjunarodnaKarta();
		AutobuskaLinija linija = new AutobuskaLinija();
		TipKarte tip = TipKarte.jednosmjerna;
		//prije
		java.util.List karte;
		karte = hmk.sveMedjunarodneKarte(session);
		int prije = karte.size();
		hmk.dodajKartu(session, linija, 2015, 05, 20, 14, 30, tip, 15.00, "Dina", "A");
		//poslije
		java.util.List karte2;
		karte2 = hmk.sveMedjunarodneKarte(session);
		int poslije = karte2.size();
		assertEquals(poslije, prije+1);
		session.close();*/
	}

	@Test
	public void testSveMedjunarodneKarte() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query q = session.createQuery("SELECT COUNT(*) FROM MedjunarodnaKarta");
		Long count = (Long)q.uniqueResult();
		int izBaze = count.intValue();
		HibernateMedjunarodnaKarta hmk = new HibernateMedjunarodnaKarta();
		java.util.List karte;
		karte = hmk.sveMedjunarodneKarte(session);
		int broj = karte.size();
		assertEquals(broj, izBaze); 
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDodajKartuPrevelikaCijena() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateMedjunarodnaKarta hmk = new HibernateMedjunarodnaKarta();
		AutobuskaLinija linija = new AutobuskaLinija();
		TipKarte tip = TipKarte.jednosmjerna;
		hmk.dodajKartu(session, linija, 2015, 05, 20, 14, 30, tip, 301.00, "Mirhat", "B");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDodajKartuNegativnaCijena() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateMedjunarodnaKarta hmk = new HibernateMedjunarodnaKarta();
		AutobuskaLinija linija = new AutobuskaLinija();
		TipKarte tip = TipKarte.jednosmjerna;
		hmk.dodajKartu(session, linija, 2015, 05, 20, 14, 30, tip, -10.00, "Mirhat", "B");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDodajKartuPogresnoIme() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateMedjunarodnaKarta hmk = new HibernateMedjunarodnaKarta();
		AutobuskaLinija linija = new AutobuskaLinija();
		TipKarte tip = TipKarte.jednosmjerna;
		hmk.dodajKartu(session, linija, 2015, 05, 20, 14, 30, tip, 15.00, "123", "A");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDodajKartuPogresnoPrezime() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateMedjunarodnaKarta hmk = new HibernateMedjunarodnaKarta();
		AutobuskaLinija linija = new AutobuskaLinija();
		TipKarte tip = TipKarte.jednosmjerna;
		hmk.dodajKartu(session, linija, 2015, 05, 20, 14, 30, tip, 15.00, "Dina", "123");
	}

}
