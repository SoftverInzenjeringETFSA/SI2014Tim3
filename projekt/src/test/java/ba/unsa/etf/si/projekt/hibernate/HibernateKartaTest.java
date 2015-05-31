package ba.unsa.etf.si.projekt.hibernate;

import static org.junit.Assert.*;

import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.junit.Test;

import ba.unsa.etf.si.projekt.entiteti.Autobus;
import ba.unsa.etf.si.projekt.entiteti.AutobuskaLinija;
import ba.unsa.etf.si.projekt.entiteti.Karta;
import ba.unsa.etf.si.projekt.entiteti.Radnik;
import ba.unsa.etf.si.projekt.entiteti.TipKarte;
import ba.unsa.etf.si.projekt.entiteti.TipRadnogMjesta;

public class HibernateKartaTest {
	@Test
	public void testDodajKartu() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateKarta karta=new HibernateKarta();
		HibernateRadnik.dodajRadnika(session, "Radnik", "Radnikovic", "5097061470350", TipRadnogMjesta.Administrator);
		Radnik r = HibernateRadnik.nadjiRadnika(session, "5097061470350");
		//Radnik r=HibernateRadnik.nadjiRadnikaPoImenu(session, "Radnik");
		HibernateAutobus.dodajAutobus(session, 60, "A22-J-161", "model98");
		Autobus a=HibernateAutobus.nadjiAutobus(session, "A22-J-161");
		HibernateAutibuskaLinija.dodajAutobuskuLiniju(session, "Sarajevo", "Tuzla", a, r, 2015, 05, 23, 13, 55, 2, 330, 3,16,16.50, 31.00, false);
		AutobuskaLinija al=HibernateAutibuskaLinija.nadjiAutobuskuLiniju(session, 16);
		//prijedodavanja
		Long count = (Long) session.createCriteria("Karta").setProjection(Projections.rowCount()).uniqueResult();		
		int prije=count.intValue();
		karta.dodajKartu(session,al,2015,05,22,12,0,TipKarte.jednosmjerna,25);    
		//poslije dodavanja
		Long count2 = (Long) session.createCriteria("Karta").setProjection(Projections.rowCount()).uniqueResult();
		int poslije=count2.intValue();
		assertEquals(poslije,prije+1);
		long idL=al.getId();
		Query q3=session.createQuery("Select id from Karta where linija="+idL);
		Long count3 =(Long)q3.uniqueResult();
		int idK=count3.intValue();
	    
		//Karta k=HibernateKarta.nadjiKartu(session, idK);
	    karta.brisanjeKarte(session,idK);
	    HibernateAutibuskaLinija.brisiAutobuskuLiniju(session, 16);
        HibernateRadnik.brisiRadnika(session, "5097061470350");
		HibernateAutobus.brisanjeAutobusa(session, "A22-J-161");	
		session.close();
	}

	@Test
	public void testdodajKartuPopunjenoException() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Autobus autobus = new Autobus();
			autobus.setKapacitet(55);
			Radnik vozac = new Radnik();		
			AutobuskaLinija linija= new AutobuskaLinija();
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
		Query q = session.createQuery("SELECT COUNT(*) FROM	Karta");
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
		Query q = session.createQuery("SELECT COUNT(*) FROM	Karta");
		Long count =(Long)q.uniqueResult();
		int izBaze=count.intValue();
		HibernateKarta hk= new HibernateKarta();
		java.util.List izvjestaj;
		izvjestaj=hk.IzvjestajOProdanimKartama(session, 2015, 01, 01, 2015, 05, 31);
		int br=izvjestaj.size();
		assertEquals(br,izBaze);
		session.close();
	}

	
}
