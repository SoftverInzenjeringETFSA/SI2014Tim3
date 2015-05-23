package ba.unsa.etf.si.projekt.hibernate;

import static org.junit.Assert.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ba.unsa.etf.si.projekt.entiteti.Autobus;
import ba.unsa.etf.si.projekt.entiteti.AutobuskaLinija;
import ba.unsa.etf.si.projekt.entiteti.Radnik;
import ba.unsa.etf.si.projekt.entiteti.TipKarte;
import ba.unsa.etf.si.projekt.entiteti.TipRadnogMjesta;

public class HibernateMedjunarodnaKartaTest {
	
	HibernateMedjunarodnaKarta hmk;
	HibernateAutobus ha;
	HibernateRadnik hr;
	HibernateAutibuskaLinija hal;
	Autobus a;
	Radnik r;
	AutobuskaLinija linija;
	TipKarte tk;
	
	@Before
	public void priprema() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		hmk = new HibernateMedjunarodnaKarta();
		ha = new HibernateAutobus();
		ha.dodajAutobus(session, 50, "A23-M-111", "Testmodel");
		hr = new HibernateRadnik();
		TipRadnogMjesta vozac = TipRadnogMjesta.Vozac;
		hr.dodajRadnika(session, "SumejaTest", "B", "1223493827144", vozac);
		a = ha.nadjiAutobus(session, "A23-M-111");  
		r = hr.nadjiRadnika(session, "1223493827144");
		hal = new HibernateAutibuskaLinija();
		hal.dodajAutobuskuLiniju(session,"SarajevoTest","Zenica",a,r,2015,4,17,2,2,5,100,100,1,20,40,true);
		linija = hal.nadjiAutobuskuLiniju(session, 1);
		tk = TipKarte.jednosmjerna;
		session.close();
	}
	
	@After
	public void kraj() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		hal.brisiAutobuskuLiniju(session, 1);
		ha.brisanjeAutobusa(session, "A23-M-111");
		hr.brisiRadnika(session, "1223493827144");
		session.close();
	}
	
	@Test
	public void testDodajKartu() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		//prije dodavanja
		Query q = session.createQuery("SELECT COUNT(*) FROM MedjunarodnaKarta");
		Long count = (Long)q.uniqueResult();
		int prije = count.intValue();
		hmk.dodajKartu(session, linija, 2015, 5, 20, 14, 50, tk, 25.00, "Dinatest", "mk");
		//poslije dodavanja
		Query q2 = session.createQuery("SELECT COUNT(*) FROM MedjunarodnaKarta");
		Long count2 = (Long)q.uniqueResult();
		int poslije = count2.intValue();
		assertEquals(poslije, prije+1);
		Query q3 = session.createQuery("DELETE FROM MedjunarodnaKarta WHERE ime='Dinatest'");
		q3.executeUpdate();
		Query q4 = session.createQuery("DELETE FROM Karta WHERE linija="+linija.getId());
		q4.executeUpdate();
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
	public void testDodajKartuPrevelikaCijena() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		hmk.dodajKartu(session, linija, 2015, 05, 20, 14, 30, tk, 301.00, "Mirhat", "B");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDodajKartuNegativnaCijena() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		hmk.dodajKartu(session, linija, 2015, 05, 20, 14, 30, tk, -10.00, "Mirhat", "B");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDodajKartuPogresnoIme() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		hmk.dodajKartu(session, linija, 2015, 05, 20, 14, 30, tk, 15.00, "123", "A");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDodajKartuPogresnoPrezime() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		hmk.dodajKartu(session, linija, 2015, 05, 20, 14, 30, tk, 15.00, "Dina", "123");
	}

}
