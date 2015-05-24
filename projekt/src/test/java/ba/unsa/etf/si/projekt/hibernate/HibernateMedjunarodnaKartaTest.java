package ba.unsa.etf.si.projekt.hibernate;

import static org.junit.Assert.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import ba.unsa.etf.si.projekt.entiteti.Autobus;
import ba.unsa.etf.si.projekt.entiteti.AutobuskaLinija;
import ba.unsa.etf.si.projekt.entiteti.Radnik;
import ba.unsa.etf.si.projekt.entiteti.TipKarte;
import ba.unsa.etf.si.projekt.entiteti.TipRadnogMjesta;

public class HibernateMedjunarodnaKartaTest {
	
	HibernateMedjunarodnaKarta hmk;
	AutobuskaLinija linija;
	TipKarte tip;
	
	@Before
	public void priprema() {
		hmk = new HibernateMedjunarodnaKarta();
		linija = new AutobuskaLinija();
		tip = TipKarte.jednosmjerna;
	}
	
	@Test
	public void testDodajMKartu() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateAutobus ha = new HibernateAutobus();
		ha.dodajAutobus(session, 56, "K77-O-357", "Testmodel300");
		HibernateRadnik hr = new HibernateRadnik();
		TipRadnogMjesta vozac = TipRadnogMjesta.Vozac;
		hr.dodajRadnika(session, "RadnikDM", "Dmk", "2040507080904", vozac);
		Autobus a = ha.nadjiAutobus(session, "K77-O-357");  
		Radnik r = hr.nadjiRadnika(session, "2040507080904");
		HibernateAutibuskaLinija hal = new HibernateAutibuskaLinija();
		hal.dodajAutobuskuLiniju(session,"dmkp","dmko",a,r,2015,9,12,6,54,5,130,130,12,32.00,60.00,true);
		linija = hal.nadjiAutobuskuLiniju(session, 12); //linija 12
		//prije dodavanja
		Query q = session.createQuery("SELECT COUNT(*) FROM MedjunarodnaKarta");
		Long count = (Long)q.uniqueResult();
		int prije = count.intValue();
		hmk.dodajKartu(session, linija, 2015, 9, 12, 6, 54, tip, 32.00, "Dinamk", "Dinamk");
		//poslije dodavanja
		Query q2 = session.createQuery("SELECT COUNT(*) FROM MedjunarodnaKarta");
		Long count2 = (Long)q.uniqueResult();
		int poslije = count2.intValue();
		assertEquals(poslije, prije+1);
		Query q3 = session.createQuery("DELETE FROM MedjunarodnaKarta WHERE ime='Dinamk'");
		q3.executeUpdate();
		hal.brisiAutobuskuLiniju(session, 12);
		ha.brisanjeAutobusa(session, "K77-O-357");
		hr.brisiRadnika(session, "2040507080904");
		session.close();
	}

	@Test
	public void testSveMedjunarodneKarte() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query q = session.createQuery("SELECT COUNT(*) FROM MedjunarodnaKarta");
		Long count = (Long)q.uniqueResult();
		int izBaze = count.intValue();
		java.util.List karte;
		karte = hmk.sveMedjunarodneKarte(session);
		int broj = karte.size();
		assertEquals(broj, izBaze); 
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDodajMKartuPrevelikaCijena() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		hmk.dodajKartu(session, linija, 2015, 05, 20, 14, 30, tip, 301.00, "Mirhat", "B");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDodajMKartuNegativnaCijena() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		hmk.dodajKartu(session, linija, 2015, 05, 20, 14, 30, tip, -10.00, "Mirhat", "B");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDodajMKartuPogresnoIme() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		hmk.dodajKartu(session, linija, 2015, 05, 20, 14, 30, tip, 15.00, "123", "A");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDodajMKartuPogresnoPrezime() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		hmk.dodajKartu(session, linija, 2015, 05, 20, 14, 30, tip, 15.00, "Dina", "123");
	}

}
