package ba.unsa.etf.si.projekt.hibernate;

import static org.junit.Assert.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ba.unsa.etf.si.projekt.entiteti.Autobus;
import ba.unsa.etf.si.projekt.entiteti.AutobuskaLinija;
import ba.unsa.etf.si.projekt.entiteti.Radnik;
import ba.unsa.etf.si.projekt.entiteti.Rezervacija;
import ba.unsa.etf.si.projekt.entiteti.TipKarte;
import ba.unsa.etf.si.projekt.entiteti.TipRadnogMjesta;

public class HibernateRezervacijaTest {
/*
	Rezervacija rezervacija;
	AutobuskaLinija linija;
	Autobus a;
	Radnik r;
	
	@Before
	public void priprema(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		rezervacija = new Rezervacija();
		//autobus
		a.setKapacitet(50);
		a.setModel("model");
		a.setRegistracija("A45-M-789");
		
		Long idAutobus=(Long) session.save(a);
		a.setId(idAutobus);
		
		//radnik
		r.setIme("Ilvana");
		r.setPrezime("B");
		r.setJmbg("9512463845124");
		TipRadnogMjesta mjesto = TipRadnogMjesta.Vozac;
		
		Long idRadnik=(Long) session.save(r);
		r.setId(idRadnik);
		
		r.setTipRadnogMjesta(mjesto);
		//autobuska linija
		linija = new AutobuskaLinija();
		linija.setAutobus(a);
		linija.setBrojLinije(5);
		linija.setCijenaDvosmjerna(30.00);
		linija.setCijenaJednosmjerna(15.00);
		linija.setDatumPolaska_dan(20);
		linija.setDatumPolaska_mjesec(5);
		linija.setDatumPolaska_godina(2015);
		linija.setDistanca(100.00);
		linija.setOdrediste("nesto");
		linija.setPeron(2);
		linija.setPolaziste("Sarajevo");
		linija.setTrajanje(30.00);
		linija.setVrijemePolaska_minute(30);
		linija.setVrijemePolaska_sati(14);
		linija.setVozac(r);
		linija.setMedjunarodna(true);
		linija.setZauzeto(5);
		
		Long idLinija=(Long) session.save(linija);
		linija.setId(idLinija);
		
		//rezervacija
		rezervacija.setCijena(30.00);
		rezervacija.setDatumPolaska_dan(20);
		rezervacija.setDatumPolaska_mjesec(5);
		rezervacija.setDatumPolaska_godina(2015);
		rezervacija.setIme("DinaSamoZaTest");
		rezervacija.setPrezime("Ahmic");
		rezervacija.setLinija(linija);
		TipKarte tipk = TipKarte.jednosmjerna;
		rezervacija.setTipKarte(tipk);
		rezervacija.setVrijemePolaska_minute(30);
		rezervacija.setVrijemePolaska_sati(14);
		
		Long idRez=(Long) session.save(rezervacija);
		rezervacija.setId(idRez);
		t.commit();
		session.close();
	}
	
	@After
	public void kraj(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(a);
		session.delete(r);
		session.delete(linija);
		session.delete(rezervacija);
		t.commit();
		session.close();
	}
	*/
	@Test
	public void testSveRezervacije() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query q = session.createQuery("SELECT COUNT(*) FROM Rezervacija");
		Long count = (Long)q.uniqueResult();
		int izBaze = count.intValue();
		HibernateRezervacija hr = new HibernateRezervacija();
		java.util.List rez;
		rez = hr.sveRezervacije(session);
		int broj = rez.size();
		assertEquals(broj, izBaze);
	}

	@Test
	public void testModifikujRezervaciju() {
		//fail("Not yet implemented"); // TODO
	}
	
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
		//fail("Not yet implemented"); // TODO
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testBrisanjeRezervacijeNevalidnoIme() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateRezervacija hr = new HibernateRezervacija();
		AutobuskaLinija al = new AutobuskaLinija();
		hr.brisanjeRezervacije(session, al, "456*", "A");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testBrisanjeRezervacijeNevalidnoPrezime() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateRezervacija hr = new HibernateRezervacija();
		AutobuskaLinija al = new AutobuskaLinija();
		hr.brisanjeRezervacije(session, al, "Dina", "123");
	}

	@Test
	public void testNadjiRezervaciju() {
		/*Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateRezervacija hr = new HibernateRezervacija();
		Rezervacija nadjena = hr.nadjiRezervaciju(session, "Sarajevo", "DinaSamoZaTest", "Ahmic");
		assertEquals("DinaSamoZaTest", nadjena.getIme());
		*/
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNadjiRezervacijuNevalidnoIme() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateRezervacija hr = new HibernateRezervacija();
		AutobuskaLinija al = new AutobuskaLinija();
		hr.nadjiRezervaciju(session, "Sarajevo", "*", "A");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNadjiRezervacijuNevalidnoPrezime() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateRezervacija hr = new HibernateRezervacija();
		AutobuskaLinija al = new AutobuskaLinija();
		hr.nadjiRezervaciju(session, "Sarajevo", "Dinaaa", "++");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNadjiRezervacijuBezOdredista() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateRezervacija hr = new HibernateRezervacija();
		AutobuskaLinija al = new AutobuskaLinija();
		hr.nadjiRezervaciju(session, "", "Dinaaa", "A");
	}

	@Test
	public void testDodajRezervaciju() {
		//fail("Not yet implemented"); // TODO
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDodajRezervacijuNevalidnoIme() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateRezervacija hr = new HibernateRezervacija();
		AutobuskaLinija al = new AutobuskaLinija();
		TipKarte tk = TipKarte.jednosmjerna;
		hr.dodajRezervaciju(session, al, 2015, 5, 20, 14, 30, tk, 20.00, "!Dina", "A");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDodajRezervacijuNevalidnoPrezime() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateRezervacija hr = new HibernateRezervacija();
		AutobuskaLinija al = new AutobuskaLinija();
		TipKarte tk = TipKarte.jednosmjerna;
		hr.dodajRezervaciju(session, al, 2015, 5, 20, 14, 30, tk, 20.00, "Dina", "/A");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDodajRezervacijuNegativnaCijena() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateRezervacija hr = new HibernateRezervacija();
		AutobuskaLinija al = new AutobuskaLinija();
		TipKarte tk = TipKarte.jednosmjerna;
		hr.dodajRezervaciju(session, al, 2015, 5, 20, 14, 30, tk, -20.00, "Dina", "A");
	}

}
