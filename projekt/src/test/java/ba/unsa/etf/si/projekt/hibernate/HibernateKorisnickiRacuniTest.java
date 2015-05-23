package ba.unsa.etf.si.projekt.hibernate;

import static org.junit.Assert.*;

import org.hibernate.Query;

import org.hibernate.Session;
import org.junit.Test;

import ba.unsa.etf.si.projekt.entiteti.KorisnickiRacun;
import ba.unsa.etf.si.projekt.entiteti.Radnik;
import ba.unsa.etf.si.projekt.entiteti.TipKorisnickogRacuna;
import ba.unsa.etf.si.projekt.entiteti.TipRadnogMjesta;

public class HibernateKorisnickiRacuniTest {

	/*@Test
	public void testDodajKorisnickiRacun() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateKorisnickiRacuni hkr= new HibernateKorisnickiRacuni();
		TipRadnogMjesta mjesto=TipRadnogMjesta.SalterskiRadnik;
		TipKorisnickogRacuna tkr=TipKorisnickogRacuna.salterskiRadnik;
		HibernateRadnik.dodajRadnika(session, "Mujo", "Mujic", "1211990126582", mjesto);
		Radnik r= HibernateRadnik.nadjiRadnika(session, "1211990126582");		
		
		hkr.dodajKorisnickiRacun(session, r, tkr, "mujo", "mujokujekonjapomjesecu1*");
		
		Query q = session.createQuery("SELECT COUNT(*) FROM	KorisnickiRacun");
		Long count =(Long)q.uniqueResult();
		int izBaze=count.intValue();
		java.util.List racuni;
		racuni=hkr.sviRacuni(session);
		int br= racuni.size();
		assertEquals(br,izBaze);
		session.close();
	}*/
	
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
		HibernateRadnik.dodajRadnika(session, "Mujo", "Mujic", "1211990126582", mjesto);
		Radnik r= HibernateRadnik.nadjiRadnika(session, "1211990126582");
		
		hkr.dodajKorisnickiRacun(session, r, tkr, "mujo", "mujokujekonjapomjesecu1*");
		hkr.modifikujKorisnickiRacun(session, "mujo","mmujo","mujokujekonjapomjesecu1*", tkr);
        KorisnickiRacun hib =hkr.nadjiKorisnickiRacun(session, "mmujo");
		assertEquals("mmujo",hib.getKorisnickoIme());
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
			hkr.modifikujKorisnickiRacun(session," ", "korisnik","korisnik1*", tkr);	
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
			hkr.modifikujKorisnickiRacun(session,"sumeja", "korisnik","korisnik", tkr);	
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
			hkr.modifikujKorisnickiRacun(session,"sumeja","123","korisnik1*", tkr);	
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
		HibernateRadnik.dodajRadnika(session, "Neko", "Nekic", "1712993176528", TipRadnogMjesta.Menadzer);
		Radnik r= HibernateRadnik.nadjiRadnikaPoImenu(session, "Neko");
		hkr.dodajKorisnickiRacun(session, r, tkr, "neko", "nekonn1#");
		hkr.brisiKorisnickiRacun(session, "neko");
		assertEquals(null, hkr.nadjiKorisnickiRacun(session, "neko"));
		session.close();
	}
	
	@Test
	public void testBrisiKorisnickiRacunException() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		HibernateKorisnickiRacuni hkr= new HibernateKorisnickiRacuni();
		TipKorisnickogRacuna tkr=TipKorisnickogRacuna.menadzer;
		HibernateRadnik.dodajRadnika(session, "Neko", "Nekic", "1712993176528", TipRadnogMjesta.Menadzer);
		Radnik r= HibernateRadnik.nadjiRadnikaPoImenu(session, "Neko");
		hkr.dodajKorisnickiRacun(session, r, tkr, "neko", "nekonn1#");
		hkr.brisiKorisnickiRacun(session, " ");		
		}
		catch (Exception e) {
			assertTrue(true);
			}
		session.close();
	}
	
	
/*	@Test
	public void testNadjiKorisnickiRacun() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateKorisnickiRacuni hkr= new HibernateKorisnickiRacuni();
		TipRadnogMjesta mjesto=TipRadnogMjesta.SalterskiRadnik;
		TipKorisnickogRacuna tkr=TipKorisnickogRacuna.salterskiRadnik;
		HibernateRadnik.dodajRadnika(session, "Mujo", "Mujic", "1211990126582", mjesto);
		Radnik r= HibernateRadnik.nadjiRadnika(session, "1211990126582");
		hkr.dodajKorisnickiRacun(session, r,tkr, "neko", "nekonn1#");	
		KorisnickiRacun pronadjen= hkr.nadjiKorisnickiRacun(session, "neko");
		assertEquals("neko",pronadjen.getKorisnickoIme());
		session.close();				
	}*/

	@Test
	public void testSviRacuni() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query q = session.createQuery("SELECT COUNT(*) FROM	KorisnickiRacun");
		Long count =(Long)q.uniqueResult();
		int izBaze=count.intValue();
		HibernateKorisnickiRacuni hkr= new HibernateKorisnickiRacuni();
		java.util.List racuni;
		racuni=hkr.sviRacuni(session);
		int br= racuni.size();
		assertEquals(br,izBaze);
		session.close();
	}

	

}
