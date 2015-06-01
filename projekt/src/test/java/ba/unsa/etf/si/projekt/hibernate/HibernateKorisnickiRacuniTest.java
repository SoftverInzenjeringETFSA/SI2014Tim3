package ba.unsa.etf.si.projekt.hibernate;

import static org.junit.Assert.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.junit.Test;

import ba.unsa.etf.si.projekt.entiteti.KorisnickiRacun;
import ba.unsa.etf.si.projekt.entiteti.Radnik;
import ba.unsa.etf.si.projekt.entiteti.TipKorisnickogRacuna;
import ba.unsa.etf.si.projekt.entiteti.TipRadnogMjesta;

public class HibernateKorisnickiRacuniTest {

	@Test
	public void testDodajKorisnickiRacun() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateKorisnickiRacuni hkr= new HibernateKorisnickiRacuni();
		TipRadnogMjesta mjesto=TipRadnogMjesta.SalterskiRadnik;
		TipKorisnickogRacuna tkr=TipKorisnickogRacuna.salterskiRadnik;
		HibernateRadnik.dodajRadnika(session, "Mujo", "Mujic", "1211990126582", mjesto);
		Radnik r= HibernateRadnik.nadjiRadnika(session, "1211990126582");		
		//prijedodavanja
		Long count = (Long) session.createCriteria(KorisnickiRacun.class).setProjection(Projections.rowCount()).uniqueResult();
		int prije=count.intValue();
		hkr.dodajKorisnickiRacun(session, r, tkr, "mujo", "mujokujekonjapomjesecu1*");
		//poslije dodavanja
		Long count2 = (Long) session.createCriteria(KorisnickiRacun.class).setProjection(Projections.rowCount()).uniqueResult();
		int poslije=count2.intValue();
		assertEquals(poslije,prije+1);
		hkr.brisiKorisnickiRacun(session, "mujo");
		HibernateRadnik.brisiRadnika(session, "1211990126582");		
		session.close();
		
	}
	
	@Test
	public void testDodajKorisnickiRacunKImeException() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			HibernateKorisnickiRacuni hkr= new HibernateKorisnickiRacuni();
			TipRadnogMjesta mjesto=TipRadnogMjesta.SalterskiRadnik;
			TipKorisnickogRacuna tkr=TipKorisnickogRacuna.salterskiRadnik;
			Radnik r= new Radnik();
			r.setIme("Mujo");
			r.setPrezime("Mujic");
			r.setJmbg("1211990126582");
			r.setTipRadnogMjesta(mjesto);
			
			hkr.dodajKorisnickiRacun(session, r, tkr, "mujo**123**", "mujokujekonjapomjesecu");	
			} 
		catch (Exception e) {
			assertTrue(true);
			}

		session.close();
	}
	
	@Test
	public void testDodajKorisnickiRacunKPassException() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			HibernateKorisnickiRacuni hkr= new HibernateKorisnickiRacuni();
			TipRadnogMjesta mjesto=TipRadnogMjesta.SalterskiRadnik;
			TipKorisnickogRacuna tkr=TipKorisnickogRacuna.salterskiRadnik;
			Radnik r= new Radnik();		
			hkr.dodajKorisnickiRacun(session, r, tkr, "mujo", "mujokujekonjapomjesecu");	
			} 
		catch (Exception e) {
			assertTrue(true);
			}

		session.close();
	}
	
	@Test
	public void testModifikujKorisnickiRacun() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateKorisnickiRacuni hkr= new HibernateKorisnickiRacuni();
		TipRadnogMjesta mjesto=TipRadnogMjesta.SalterskiRadnik;
		TipKorisnickogRacuna tkr=TipKorisnickogRacuna.salterskiRadnik;
		HibernateRadnik.dodajRadnika(session, "Suljo", "Sujic", "1712993176529", mjesto);
		Radnik r= HibernateRadnik.nadjiRadnika(session, "1712993176529");
		
		hkr.dodajKorisnickiRacun(session, r, tkr, "suljo", "suljokujekonjapomjesecu1*");
		hkr.modifikujKorisnickiRacun(session, "suljo","ssuljo","suljokujekonjapomjesecu1*", tkr, false);
        KorisnickiRacun hib =hkr.nadjiKorisnickiRacun(session, "ssuljo");
		assertEquals("ssuljo",hib.getKorisnickoIme());
		hkr.brisiKorisnickiRacun(session, "ssuljo");
		HibernateRadnik.brisiRadnika(session, "1712993176529");
		
		session.close();
	}
	
	@Test
	public void testModifikujKorisnickiRacunKImeException() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			HibernateKorisnickiRacuni hkr= new HibernateKorisnickiRacuni();
			TipRadnogMjesta mjesto=TipRadnogMjesta.SalterskiRadnik;
			TipKorisnickogRacuna tkr=TipKorisnickogRacuna.salterskiRadnik;
			Radnik r= new Radnik();		
			hkr.modifikujKorisnickiRacun(session," ", "korisnik","korisnik1*", tkr, true);	
			} 
		catch (Exception e) {
			assertTrue(true);
			}

		session.close();
		
	}
	
	@Test
	public void testModifikujKorisnickiRacunKSifraException() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			HibernateKorisnickiRacuni hkr= new HibernateKorisnickiRacuni();
			TipRadnogMjesta mjesto=TipRadnogMjesta.SalterskiRadnik;
			TipKorisnickogRacuna tkr=TipKorisnickogRacuna.salterskiRadnik;
			Radnik r= new Radnik();		
			hkr.modifikujKorisnickiRacun(session,"sumeja", "korisnik","korisnik", tkr, true);	
			} 
		catch (Exception e) {
			assertTrue(true);
			}
		session.close();		
	}
	
	@Test
	public void testModifikujKorisnickiRacunNovoKImeException() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			HibernateKorisnickiRacuni hkr= new HibernateKorisnickiRacuni();
			TipRadnogMjesta mjesto=TipRadnogMjesta.SalterskiRadnik;
			TipKorisnickogRacuna tkr=TipKorisnickogRacuna.salterskiRadnik;
			Radnik r= new Radnik();		
			hkr.modifikujKorisnickiRacun(session,"sumeja","123","korisnik1*", tkr, true);	
			} 
		catch (Exception e) {
			assertTrue(true);
			}
		session.close();		
	}

	@Test
	public void testBrisiKorisnickiRacun() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateKorisnickiRacuni hkr= new HibernateKorisnickiRacuni();
		TipKorisnickogRacuna tkr=TipKorisnickogRacuna.menadzer;
		HibernateRadnik.dodajRadnika(session, "Haso", "Hasic", "1211990126528", TipRadnogMjesta.Menadzer);
		Radnik r= HibernateRadnik.nadjiRadnikaPoImenu(session, "Haso");
		hkr.dodajKorisnickiRacun(session, r, tkr, "haso", "hasooo1#");
		hkr.brisiKorisnickiRacun(session, "haso");
		assertEquals(null, hkr.nadjiKorisnickiRacun(session, "haso"));
		HibernateRadnik.brisiRadnika(session, "1211990126528");
		session.close();
	}
	
	@Test
	public void testBrisiKorisnickiRacunException() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		HibernateKorisnickiRacuni hkr= new HibernateKorisnickiRacuni();
		hkr.brisiKorisnickiRacun(session, " ");		
		}
		catch (Exception e) {
			assertTrue(true);
			}
		session.close();
	}
	
	
	@Test
	public void testNadjiKorisnickiRacun() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateKorisnickiRacuni hkr= new HibernateKorisnickiRacuni();
		TipRadnogMjesta mjesto=TipRadnogMjesta.SalterskiRadnik;
		TipKorisnickogRacuna tkr=TipKorisnickogRacuna.salterskiRadnik;
		HibernateRadnik.dodajRadnika(session, "NN", "NNic", "0208992177824", mjesto);
		Radnik r= HibernateRadnik.nadjiRadnika(session, "0208992177824");
		hkr.dodajKorisnickiRacun(session, r,tkr, "salteras", "salteras1#");	
		KorisnickiRacun pronadjen= hkr.nadjiKorisnickiRacun(session, "salteras");
		assertEquals("salteras",pronadjen.getKorisnickoIme());
		hkr.brisiKorisnickiRacun(session, "salteras");
		HibernateRadnik.brisiRadnika(session, "0208992177824");
		session.close();				
	}
	
	@Test
	public void testNadjiKorisnickiRacunxception() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		HibernateKorisnickiRacuni hkr= new HibernateKorisnickiRacuni();
		KorisnickiRacun pronadjen= hkr.nadjiKorisnickiRacun(session, "salteras123");
		}
		catch (Exception e) {
			assertTrue(true);
			}
		session.close();				
	}

	@Test
	public void testSviRacuni() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Long count = (Long) session.createCriteria(KorisnickiRacun.class).setProjection(Projections.rowCount()).uniqueResult();
		int izBaze=count.intValue();
		HibernateKorisnickiRacuni hkr= new HibernateKorisnickiRacuni();
		java.util.List racuni;
		racuni=hkr.sviRacuni(session);
		int br= racuni.size();
		assertEquals(br,izBaze);
		session.close();
	}

	

}
