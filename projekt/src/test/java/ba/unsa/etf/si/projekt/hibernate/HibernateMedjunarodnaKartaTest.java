package ba.unsa.etf.si.projekt.hibernate;

import static org.junit.Assert.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import ba.unsa.etf.si.projekt.entiteti.Autobus;
import ba.unsa.etf.si.projekt.entiteti.AutobuskaLinija;
import ba.unsa.etf.si.projekt.entiteti.Karta;
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
	public void testDodajKartu() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		hmk = new HibernateMedjunarodnaKarta();
		HibernateAutobus ha = new HibernateAutobus();
		ha.dodajAutobus(session, 50, "A23-M-000", "Testmodel");
		HibernateRadnik hr = new HibernateRadnik();
		TipRadnogMjesta vozac = TipRadnogMjesta.Vozac;
		hr.dodajRadnika(session, "DinaTestTest", "Aaa", "1223493827100", vozac);
		Autobus a = ha.nadjiAutobus(session, "A23-M-000");  
		Radnik r = hr.nadjiRadnika(session, "1223493827100");
		HibernateAutibuskaLinija hal = new HibernateAutibuskaLinija();
		hal.dodajAutobuskuLiniju(session,"SarajevoTest","ZenicaTest",a,r,2015,4,17,7,7,5,100,100,4,20,40,true);
		linija = hal.nadjiAutobuskuLiniju(session, 4); //linija 4
		tip = TipKarte.jednosmjerna;
		//prije dodavanja
		Query q = session.createQuery("SELECT COUNT(*) FROM MedjunarodnaKarta");
		Long count = (Long)q.uniqueResult();
		int prije = count.intValue();
		hmk.dodajKartu(session, linija, 2015, 4, 17, 7, 7, tip, 20.00, "Dinatesttest", "dk");
		//poslije dodavanja
		HibernateKarta hk = new HibernateKarta();
		Query q2 = session.createQuery("SELECT COUNT(*) FROM MedjunarodnaKarta");
		Long count2 = (Long)q.uniqueResult();
		int poslije = count2.intValue();
		assertEquals(poslije, prije+1);
		Query q3 = session.createQuery("DELETE FROM MedjunarodnaKarta WHERE ime='Dinatesttest'");
		q3.executeUpdate();
		//hk.brisanjeKarte(session, linija.getId());
		hal.brisiAutobuskuLiniju(session, 4);
		ha.brisanjeAutobusa(session, "A23-M-000");
		hr.brisiRadnika(session, "1223493827100");
		session.close();
	}

	@Test
	public void testSveMedjunarodneKarte() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query q = session.createQuery("SELECT COUNT(*) FROM MedjunarodnaKarta");
		Long count = (Long)q.uniqueResult();
		int izBaze = count.intValue();
		//HibernateMedjunarodnaKarta hmk = new HibernateMedjunarodnaKarta();
		java.util.List karte;
		karte = hmk.sveMedjunarodneKarte(session);
		int broj = karte.size();
		assertEquals(broj, izBaze); 
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDodajKartuPrevelikaCijena() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		//HibernateMedjunarodnaKarta hmk = new HibernateMedjunarodnaKarta();
		//AutobuskaLinija linija = new AutobuskaLinija();
		//TipKarte tip = TipKarte.jednosmjerna;
		hmk.dodajKartu(session, linija, 2015, 05, 20, 14, 30, tip, 301.00, "Mirhat", "B");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDodajKartuNegativnaCijena() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		//HibernateMedjunarodnaKarta hmk = new HibernateMedjunarodnaKarta();
		//AutobuskaLinija linija = new AutobuskaLinija();
		//TipKarte tip = TipKarte.jednosmjerna;
		hmk.dodajKartu(session, linija, 2015, 05, 20, 14, 30, tip, -10.00, "Mirhat", "B");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDodajKartuPogresnoIme() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		//HibernateMedjunarodnaKarta hmk = new HibernateMedjunarodnaKarta();
		//AutobuskaLinija linija = new AutobuskaLinija();
		//TipKarte tip = TipKarte.jednosmjerna;
		hmk.dodajKartu(session, linija, 2015, 05, 20, 14, 30, tip, 15.00, "123", "A");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDodajKartuPogresnoPrezime() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		//HibernateMedjunarodnaKarta hmk = new HibernateMedjunarodnaKarta();
		//AutobuskaLinija linija = new AutobuskaLinija();
		//TipKarte tip = TipKarte.jednosmjerna;
		hmk.dodajKartu(session, linija, 2015, 05, 20, 14, 30, tip, 15.00, "Dina", "123");
	}

}
