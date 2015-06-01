package ba.unsa.etf.si.projekt.hibernate;

import static org.junit.Assert.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.junit.Before;
import org.junit.Test;

import ba.unsa.etf.si.projekt.entiteti.Autobus;
import ba.unsa.etf.si.projekt.entiteti.AutobuskaLinija;
import ba.unsa.etf.si.projekt.entiteti.MedjunarodnaKarta;
import ba.unsa.etf.si.projekt.entiteti.Radnik;
import ba.unsa.etf.si.projekt.entiteti.Rezervacija;
import ba.unsa.etf.si.projekt.entiteti.TipKarte;
import ba.unsa.etf.si.projekt.entiteti.TipRadnogMjesta;

public class HibernateRezervacijaTest {

	HibernateRezervacija hrez;
	
	@Before
	public void priprema() {
		hrez = new HibernateRezervacija();
	}
	
	@Test
	public void testSveRezervacije() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Long count = (Long) session.createCriteria(Rezervacija.class).setProjection(Projections.rowCount()).uniqueResult();
		
		int izBaze = count.intValue();
		java.util.List rez;
		rez = hrez.sveRezervacije(session);
		int broj = rez.size();
		assertEquals(broj, izBaze);
	}

	@Test
	public void testModifikujRezervaciju() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		//stara linija 17, peron 5
		HibernateAutobus ha3 = new HibernateAutobus();
		ha3.dodajAutobus(session, 55, "T24-T-951", "Testmodel200");
		HibernateRadnik hr3 = new HibernateRadnik();
		TipRadnogMjesta vozac = TipRadnogMjesta.Vozac;
		hr3.dodajRadnika(session, "RadnikTestMod", "Mod", "1204507809907", vozac);
		Autobus a3 = ha3.nadjiAutobus(session, "T24-T-951");  
		Radnik r3 = hr3.nadjiRadnika(session, "1204507809907");
		HibernateAutibuskaLinija stara = new HibernateAutibuskaLinija();
		stara.dodajAutobuskuLiniju(session,"mst","zst",a3,r3,2015,8,15,22,10,5,200,111,17,35.00,70.00,true);
		//nova
		HibernateAutibuskaLinija nova = new HibernateAutibuskaLinija();
		//linija postaje 19, peron 2
		nova.dodajAutobuskuLiniju(session,"mstnovo","zstnovo",a3,r3,2015,8,15,22,10,2,201,112,19,36.00,71.00,true);
		AutobuskaLinija linijaS = stara.nadjiAutobuskuLiniju(session, 17);
		AutobuskaLinija linijaN = nova.nadjiAutobuskuLiniju(session, 19);
		TipKarte tk = TipKarte.jednosmjerna;
		//prije
		hrez.dodajRezervaciju(session, linijaS, 2015, 8, 15, 22, 10, tk, 35.00, "Dinaatestmod", "Aamod");
		Long count = (Long) session.createCriteria(Rezervacija.class).setProjection(Projections.rowCount()).uniqueResult();
		
		int prije = count.intValue();
		//poslije
		hrez.ModifikujRezervaciju(session, linijaS, linijaN, "Dinaatestmod", "Aamod", "Dinaamodnovo", "Aamodnovo", 36.00, tk);
		Long count2 = (Long) session.createCriteria(Rezervacija.class).setProjection(Projections.rowCount()).uniqueResult();
		
		int poslije = count2.intValue();
		assertEquals(poslije, prije);	//treba ostati isto
		hrez.brisanjeRezervacije(session, linijaN, "Dinaamodnovo", "Aamodnovo");
		stara.brisiAutobuskuLiniju(session, 17);
		nova.brisiAutobuskuLiniju(session, 19);
		ha3.brisanjeAutobusa(session, "T24-T-951");
		hr3.brisiRadnika(session, "1204507809907");
		session.close();
	}
	
	@Test
	public void testBrisanjeRezervacije() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateAutobus ha2 = new HibernateAutobus();
		ha2.dodajAutobus(session, 52, "K44-T-010", "Testmodel100");
		HibernateRadnik hr2 = new HibernateRadnik();
		TipRadnogMjesta vozac = TipRadnogMjesta.Vozac;
		hr2.dodajRadnika(session, "RadnikTestBrisanje", "Bbb", "1122337788994", vozac);
		Autobus a2 = ha2.nadjiAutobus(session, "K44-T-010");  
		Radnik r2 = hr2.nadjiRadnika(session, "1122337788994");
		HibernateAutibuskaLinija hal2 = new HibernateAutibuskaLinija();
		hal2.dodajAutobuskuLiniju(session,"bst","bzt",a2,r2,2015,7,21,16,16,5,60,60,8,20,40,true);
		//broj linije 8, peron 5
		AutobuskaLinija linija2 = hal2.nadjiAutobuskuLiniju(session, 8);
		//HibernateRezervacija hrez = new HibernateRezervacija();
		TipKarte tk = TipKarte.jednosmjerna;
		//prije brisanja
		hrez.dodajRezervaciju(session, linija2, 2015, 7, 21, 16, 16, tk, 20.00, "Dinatestbrisanje", "Brisanje");
		Long count = (Long) session.createCriteria(Rezervacija.class).setProjection(Projections.rowCount()).uniqueResult();
		
		int prije = count.intValue();
		//poslije brisanja
		hrez.brisanjeRezervacije(session, linija2, "Dinatestbrisanje", "Brisanje");
		Long count2 = (Long) session.createCriteria(Rezervacija.class).setProjection(Projections.rowCount()).uniqueResult();
		
		int poslije = count2.intValue();
		assertEquals(poslije, prije-1);
		hal2.brisiAutobuskuLiniju(session, 8);
		ha2.brisanjeAutobusa(session, "K44-T-010");
		hr2.brisiRadnika(session, "1122337788994");
		session.close();	
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testBrisanjeRezervacijeNevalidnoIme() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		AutobuskaLinija al = new AutobuskaLinija();
		hrez.brisanjeRezervacije(session, al, "456*", "A");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testBrisanjeRezervacijeNevalidnoPrezime() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		AutobuskaLinija al = new AutobuskaLinija();
		hrez.brisanjeRezervacije(session, al, "Dina", "123");
	}	

	@Test
	public void testDodajRezervaciju() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateAutobus ha1 = new HibernateAutobus();
		ha1.dodajAutobus(session, 40, "E11-J-234", "modeltest1");
		HibernateRadnik hr1 = new HibernateRadnik();
		TipRadnogMjesta vozac = TipRadnogMjesta.Vozac;
		hr1.dodajRadnika(session, "TestniRadnikD", "D", "1010101010101", vozac);
		Autobus a1 = ha1.nadjiAutobus(session, "E11-J-234");  
		Radnik r1 = hr1.nadjiRadnika(session, "1010101010101");
		HibernateAutibuskaLinija hal1 = new HibernateAutibuskaLinija();
		hal1.dodajAutobuskuLiniju(session,"STest","ZTest",a1,r1,2015,6,29,15,20,1,50,50,3,10.00,20.00,true);
		//broj linije 3, peron linije 1
		AutobuskaLinija linija1 = hal1.nadjiAutobuskuLiniju(session, 3);
		TipKarte tk = TipKarte.jednosmjerna;
		//prije dodavanja
		Long count = (Long) session.createCriteria(Rezervacija.class).setProjection(Projections.rowCount()).uniqueResult();
		
		int prije = count.intValue();
		hrez.dodajRezervaciju(session, linija1, 2015, 6, 29, 15, 20, tk, 10.00, "DodajTest", "Dodaj");
		//poslije dodavanja
		Long count2 = (Long) session.createCriteria(Rezervacija.class).setProjection(Projections.rowCount()).uniqueResult();
		
		int poslije = count2.intValue();
		assertEquals(poslije, prije+1);
		hrez.brisanjeRezervacije(session, linija1, "DodajTest", "Dodaj");
		hal1.brisiAutobuskuLiniju(session, 3);
		ha1.brisanjeAutobusa(session, "E11-J-234");
		hr1.brisiRadnika(session, "1010101010101");
		session.close();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDodajRezervacijuNevalidnoIme() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		AutobuskaLinija al = new AutobuskaLinija();
		TipKarte tk = TipKarte.jednosmjerna;
		hrez.dodajRezervaciju(session, al, 2015, 5, 20, 14, 30, tk, 20.00, "!Dina", "A");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDodajRezervacijuNevalidnoPrezime() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		AutobuskaLinija al = new AutobuskaLinija();
		TipKarte tk = TipKarte.jednosmjerna;
		hrez.dodajRezervaciju(session, al, 2015, 5, 20, 14, 30, tk, 20.00, "Dina", "/A");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDodajRezervacijuNegativnaCijena() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		AutobuskaLinija al = new AutobuskaLinija();
		TipKarte tk = TipKarte.jednosmjerna;
		hrez.dodajRezervaciju(session, al, 2015, 5, 20, 14, 30, tk, -20.00, "Dina", "A");
	}

}
