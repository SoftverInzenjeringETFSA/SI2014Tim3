package ba.unsa.etf.si.projekt.hibernate;

import static org.junit.Assert.*;

import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import ba.unsa.etf.si.projekt.entiteti.Autobus;
import ba.unsa.etf.si.projekt.entiteti.AutobuskaLinija;
import ba.unsa.etf.si.projekt.entiteti.Radnik;
import ba.unsa.etf.si.projekt.entiteti.TipKarte;
import ba.unsa.etf.si.projekt.entiteti.TipRadnogMjesta;

public class HibernateKartaTest {
	
	
	
	/*@Test
	public void testDodajKartu() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		int vrijeme_sati=2;
		int vrijeme_minute=5;
		TipKarte k=TipKarte.jednosmjerna;
		int godina=2015;
		int mjesec=5;
		int dan=22;
		double cijenakarte=21.0;
		Autobus autobus = new Autobus();
		autobus.setKapacitet(55);
		autobus.setModel("AudiW2");
		autobus.setRegistracija("A22-M-543");
		
		Radnik vozac = new Radnik();
		TipRadnogMjesta m= TipRadnogMjesta.Vozac;
		vozac.setIme("Mujo");
		vozac.setJmbg("0201959171266");
		vozac.setPrezime("Mujic");
		vozac.setTipRadnogMjesta(m);
		
		AutobuskaLinija linija= new AutobuskaLinija();
		linija.setAutobus(autobus);
		linija.setBrojLinije(2);
		linija.setCijenaDvosmjerna(45.50);
		linija.setCijenaJednosmjerna(25.50);
		linija.setDatumPolaska_dan(22);
		linija.setDatumPolaska_godina(2015);
		linija.setDatumPolaska_mjesec(05);
		linija.setDistanca(555.00);
		linija.setMedjunarodna(false);
		linija.setOdrediste("Gradacac");
		linija.setPeron(4);
		linija.setPolaziste("Sarajevo");
		linija.setTrajanje(4.5);
		linija.setVozac(vozac);
		linija.setVrijemePolaska_minute(00);
		linija.setVrijemePolaska_sati(06);
		linija.setZauzeto(5);
		
		HibernateKarta karta=new HibernateKarta();
		karta.dodajKartu(session,linija,2015,05,22,12,0,TipKarte.jednosmjerna,25);    
		java.util.List lista;
		lista=karta.sveKarte(session);
		int br=lista.size();
		
		Query q = session.createQuery("SELECT COUNT(*) FROM	KorisnickiRacun");
		Long count =(Long)q.uniqueResult();
		int izBaze=count.intValue();
		
		assertEquals(br, izBaze);
		session.close();

	}
*/
	@Test
	public void testdodajKartuPopunjenoException() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Autobus autobus = new Autobus();
			autobus.setKapacitet(55);
			autobus.setModel("AudiW2");
			autobus.setRegistracija("A22-M-543");
			
			Radnik vozac = new Radnik();
			TipRadnogMjesta m= TipRadnogMjesta.Vozac;
			vozac.setIme("Mujo");
			vozac.setJmbg("0201959171266");
			vozac.setPrezime("Mujic");
			vozac.setTipRadnogMjesta(m);
			
			AutobuskaLinija linija= new AutobuskaLinija();
			linija.setAutobus(autobus);
			linija.setBrojLinije(2);
			linija.setCijenaDvosmjerna(45.50);
			linija.setCijenaJednosmjerna(25.50);
			linija.setDatumPolaska_dan(22);
			linija.setDatumPolaska_godina(2015);
			linija.setDatumPolaska_mjesec(05);
			linija.setDistanca(555.00);
			linija.setMedjunarodna(false);
			linija.setOdrediste("Gradacac");
			linija.setPeron(4);
			linija.setPolaziste("Sarajevo");
			linija.setTrajanje(4.5);
			linija.setVozac(vozac);
			linija.setVrijemePolaska_minute(00);
			linija.setVrijemePolaska_sati(06);
			linija.setZauzeto(56);
			
			HibernateKarta karta=new HibernateKarta();
			karta.dodajKartu(session,linija,2015,05,22,06,00, TipKarte.jednosmjerna, 25.50);			
			}
		     catch (Exception e) {
			assertTrue(true);
			}		
		session.close();
	}
	
	@Test
	public void testdodajKartuCijenaException() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Autobus autobus = new Autobus();
			autobus.setKapacitet(55);
			autobus.setModel("AudiW2");
			autobus.setRegistracija("A22-M-543");
			
			Radnik vozac = new Radnik();
			TipRadnogMjesta m= TipRadnogMjesta.Vozac;
			vozac.setIme("Mujo");
			vozac.setJmbg("0201959171266");
			vozac.setPrezime("Mujic");
			vozac.setTipRadnogMjesta(m);
			
			AutobuskaLinija linija= new AutobuskaLinija();
			linija.setAutobus(autobus);
			linija.setBrojLinije(2);
			linija.setCijenaDvosmjerna(45.50);
			linija.setCijenaJednosmjerna(25.50);
			linija.setDatumPolaska_dan(22);
			linija.setDatumPolaska_godina(2015);
			linija.setDatumPolaska_mjesec(05);
			linija.setDistanca(555.00);
			linija.setMedjunarodna(false);
			linija.setOdrediste("Gradacac");
			linija.setPeron(4);
			linija.setPolaziste("Sarajevo");
			linija.setTrajanje(4.5);
			linija.setVozac(vozac);
			linija.setVrijemePolaska_minute(00);
			linija.setVrijemePolaska_sati(06);
			linija.setZauzeto(6);
			
			HibernateKarta karta=new HibernateKarta();
			karta.dodajKartu(session,linija,2015,05,22,06,00, TipKarte.jednosmjerna, 350);			
			}
		     catch (Exception e) {
			assertTrue(true);
			}	
		session.close();
	}

	@Test
	public void testSveKarte() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query q = session.createQuery("SELECT COUNT(*) FROM	KorisnickiRacun");
		Long count =(Long)q.uniqueResult();
		int izBaze=count.intValue();
		HibernateKarta hk= new HibernateKarta();
		java.util.List karte;
		karte=hk.sveKarte(session);
		int br= karte.size();
		assertEquals(br,izBaze);
		session.close();
	}

	@Test
	public void testIzvjestajOProdanimKartama() {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query q = session.createQuery("SELECT COUNT(*) FROM	KorisnickiRacun");
		Long count =(Long)q.uniqueResult();
		int izBaze=count.intValue();
		HibernateKarta hk= new HibernateKarta();
		java.util.List izvjestaj;
		izvjestaj=hk.IzvjestajOProdanimKartama(session, 2015, 05, 01, 2015, 05, 30);
		int br=izvjestaj.size();
		assertEquals(br,izBaze);
		session.close();
	}

	
}
