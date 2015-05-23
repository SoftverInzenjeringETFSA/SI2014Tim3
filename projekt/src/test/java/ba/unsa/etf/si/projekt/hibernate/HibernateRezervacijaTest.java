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

public class HibernateRezervacijaTest {

	HibernateRezervacija hrez ;
	
	@Before
	public void priprema() {
		hrez = new HibernateRezervacija();
	}
	
	@Test
	public void testSveRezervacije() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query q = session.createQuery("SELECT COUNT(*) FROM Rezervacija");
		Long count = (Long)q.uniqueResult();
		int izBaze = count.intValue();
		//HibernateRezervacija hr = new HibernateRezervacija();
		java.util.List rez;
		rez = hrez.sveRezervacije(session);
		int broj = rez.size();
		assertEquals(broj, izBaze);
	}
/*
	@Test
	public void testModifikujRezervaciju() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		//stara
		HibernateAutobus ha = new HibernateAutobus();
		ha.dodajAutobus(session, 50, "A23-M-555", "Testmodel");
		HibernateRadnik hr = new HibernateRadnik();
		TipRadnogMjesta vozac = TipRadnogMjesta.Vozac;
		hr.dodajRadnika(session, "SumejaTest", "B", "1223493827163", vozac);
		Autobus a = ha.nadjiAutobus(session, "A23-M-555");  
		Radnik r = hr.nadjiRadnika(session, "1223493827163");
		HibernateAutibuskaLinija stara = new HibernateAutibuskaLinija();
		stara.dodajAutobuskuLiniju(session,"SarajevoTest","Zenica",a,r,2015,4,17,2,2,5,100,100,1,20,40,true);
		//nova
		HibernateAutibuskaLinija nova = new HibernateAutibuskaLinija();
		//linija postaje 2, peron 2
		nova.dodajAutobuskuLiniju(session,"SarajevoTest","Tuzla",a,r,2015,4,17,2,2,2,100,100,2,20,40,true);
		AutobuskaLinija linijaS = stara.nadjiAutobuskuLiniju(session, 1);
		AutobuskaLinija linijaN = nova.nadjiAutobuskuLiniju(session, 2);
		HibernateRezervacija hrez = new HibernateRezervacija();
		TipKarte tk = TipKarte.jednosmjerna;
		//prije
		hrez.dodajRezervaciju(session, linijaS, 2015, 5, 20, 14, 35, tk, 20.00, "Dinatest", "A");
		Query q = session.createQuery("SELECT COUNT(*) FROM Rezervacija");
		Long count = (Long)q.uniqueResult();
		int prije = count.intValue();
		//poslije
		hrez.ModifikujRezervaciju(session, linijaS, linijaN, "Dinatest", "A", "Dinanovo", "A", 20.00, tk);
		Query q2 = session.createQuery("SELECT COUNT(*) FROM Rezervacija");
		Long count2 = (Long)q.uniqueResult();
		int poslije = count2.intValue();
		assertEquals(poslije, prije);	//treba ostati isto
		hrez.brisanjeRezervacije(session, linijaN, "Dinanovo", "A");
		stara.brisiAutobuskuLiniju(session, 1);
		stara.brisiAutobuskuLiniju(session, 2);
		ha.brisanjeAutobusa(session, "A23-M-555");
		hr.brisiRadnika(session, "1223493827163");
		session.close();
	}
*/	
	/*@Test(expected = IllegalArgumentException.class)
	public void testModifikujRezervacijuPunAutobus() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		AutobuskaLinija nova = new AutobuskaLinija();
		Autobus a = new Autobus();
		AutobuskaLinija stara = new AutobuskaLinija();
		a.setKapacitet(50);
		nova.setAutobus(a);
		nova.setZauzeto(50);
		HibernateRezervacija hr = new HibernateRezervacija();
		TipKarte k = TipKarte.jednosmjerna;
		hr.ModifikujRezervaciju(session, stara, nova, "staroime", "staroprezime", "novoime", "novoprezime", 30.00, k);
	
	}*/

	@Test
	public void testBrisanjeRezervacije() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateAutobus ha2 = new HibernateAutobus();
		ha2.dodajAutobus(session, 50, "A23-M-777", "Testmodel1");
		HibernateRadnik hr2 = new HibernateRadnik();
		TipRadnogMjesta vozac = TipRadnogMjesta.Vozac;
		hr2.dodajRadnika(session, "SumejaTest", "Br", "1223493827163", vozac);
		Autobus a2 = ha2.nadjiAutobus(session, "A23-M-777");  
		Radnik r2 = hr2.nadjiRadnika(session, "1223493827163");
		HibernateAutibuskaLinija hal2 = new HibernateAutibuskaLinija();
		hal2.dodajAutobuskuLiniju(session,"SarajevoTest","Zenica",a2,r2,2015,4,17,2,2,5,100,100,1,20,40,true);
		//broj linije 1, peron 5
		AutobuskaLinija linija2 = hal2.nadjiAutobuskuLiniju(session, 1);
		//HibernateRezervacija hrez = new HibernateRezervacija();
		TipKarte tk = TipKarte.jednosmjerna;
		//prije brisanja
		hrez.dodajRezervaciju(session, linija2, 2015, 4, 17, 2, 2, tk, 20.00, "Dinatest", "A");
		Query q = session.createQuery("SELECT COUNT(*) FROM Rezervacija");
		Long count = (Long)q.uniqueResult();
		int prije = count.intValue();
		//poslije brisanja
		hrez.brisanjeRezervacije(session, linija2, "Dinatest", "A");
		Query q2 = session.createQuery("SELECT COUNT(*) FROM Rezervacija");
		Long count2 = (Long)q.uniqueResult();
		int poslije = count2.intValue();
		assertEquals(poslije, prije-1);
		hal2.brisiAutobuskuLiniju(session, 1);
		ha2.brisanjeAutobusa(session, "A23-M-777");
		hr2.brisiRadnika(session, "1223493827163");
		session.close();
		
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testBrisanjeRezervacijeNevalidnoIme() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		//HibernateRezervacija hr = new HibernateRezervacija();
		AutobuskaLinija al = new AutobuskaLinija();
		hrez.brisanjeRezervacije(session, al, "456*", "A");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testBrisanjeRezervacijeNevalidnoPrezime() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		//HibernateRezervacija hr = new HibernateRezervacija();
		AutobuskaLinija al = new AutobuskaLinija();
		hrez.brisanjeRezervacije(session, al, "Dina", "123");
	}	
/*
	@Test
	public void testDodajRezervaciju() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateAutobus ha = new HibernateAutobus();
		ha.dodajAutobus(session, 50, "A23-M-555", "Testmodel");
		HibernateRadnik hr = new HibernateRadnik();
		TipRadnogMjesta vozac = TipRadnogMjesta.Vozac;
		hr.dodajRadnika(session, "SumejaTest", "B", "1223493827163", vozac);
		Autobus a = ha.nadjiAutobus(session, "A23-M-555");  
		Radnik r = hr.nadjiRadnika(session, "1223493827163");
		HibernateAutibuskaLinija hal = new HibernateAutibuskaLinija();
		hal.dodajAutobuskuLiniju(session,"SarajevoTest","Zenica",a,r,2015,4,17,2,2,5,100,100,1,20,40,true);
		//broj linije 1, peron linije 5
		AutobuskaLinija linija = hal.nadjiAutobuskuLiniju(session, 1);
		HibernateRezervacija hrez = new HibernateRezervacija();
		TipKarte tk = TipKarte.jednosmjerna;
		//prije dodavanja
		Query q = session.createQuery("SELECT COUNT(*) FROM Rezervacija");
		Long count = (Long)q.uniqueResult();
		int prije = count.intValue();
		hrez.dodajRezervaciju(session, linija, 2015, 5, 20, 14, 35, tk, 20.00, "Dinatest", "A");
		//poslije dodavanja
		Query q2 = session.createQuery("SELECT COUNT(*) FROM Rezervacija");
		Long count2 = (Long)q.uniqueResult();
		int poslije = count2.intValue();
		assertEquals(poslije, prije+1);
		hrez.brisanjeRezervacije(session, linija, "Dinatest", "A");
		hal.brisiAutobuskuLiniju(session, 1);
		ha.brisanjeAutobusa(session, "A23-M-555");
		hr.brisiRadnika(session, "1223493827163");
		session.close();
	}
	*/
	@Test(expected = IllegalArgumentException.class)
	public void testDodajRezervacijuNevalidnoIme() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		//HibernateRezervacija hr = new HibernateRezervacija();
		AutobuskaLinija al = new AutobuskaLinija();
		TipKarte tk = TipKarte.jednosmjerna;
		hrez.dodajRezervaciju(session, al, 2015, 5, 20, 14, 30, tk, 20.00, "!Dina", "A");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDodajRezervacijuNevalidnoPrezime() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		//HibernateRezervacija hr = new HibernateRezervacija();
		AutobuskaLinija al = new AutobuskaLinija();
		TipKarte tk = TipKarte.jednosmjerna;
		hrez.dodajRezervaciju(session, al, 2015, 5, 20, 14, 30, tk, 20.00, "Dina", "/A");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDodajRezervacijuNegativnaCijena() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		//HibernateRezervacija hr = new HibernateRezervacija();
		AutobuskaLinija al = new AutobuskaLinija();
		TipKarte tk = TipKarte.jednosmjerna;
		hrez.dodajRezervaciju(session, al, 2015, 5, 20, 14, 30, tk, -20.00, "Dina", "A");
	}

}
