package ba.unsa.etf.si.projekt.hibernate;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import ba.unsa.etf.si.projekt.entiteti.Autobus;

public class HibernateAutobusTest {
	private static long id1;
	
	@Test
	public void testDodajiNadjiAutobus() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Autobus ab = new Autobus();
		ab.setKapacitet(50);
		ab.setModel("model");
		ab.setRegistracija("A23-M-789");
		HibernateAutobus a = new HibernateAutobus();
		a.dodajAutobus(session, ab.getKapacitet(), ab.getRegistracija(), ab.getModel());
		Autobus nadjen = a.nadjiAutobus(session, ab.getRegistracija());
		assertEquals("A23-M-789", nadjen.getRegistracija());
		a.brisanjeAutobusa(session, ab.getRegistracija());
		session.close();
	}
	
	@Test
	public void testDodajAutobusNegativanKapacitet() {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			HibernateAutobus a = new HibernateAutobus();
			a.dodajAutobus(session, -5, "A23-M-789", "model");
			fail("Nije bacen izuzetak");
		}
		catch (Exception e) {
			assertTrue(true);
		}
		session.close();
	}
	
	@Test
	public void testDodajAutobusOgromanKapacitet() {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			HibernateAutobus a = new HibernateAutobus();
			a.dodajAutobus(session, 1000, "A23-M-789", "model");
			fail("Nije bacen izuzetak");
		}
		catch (Exception e) {
			assertTrue(true);
		}
		session.close();
	}
	
	@Test
	public void testDodajAutobusModelIzuzetak() {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			HibernateAutobus a = new HibernateAutobus();
			a.dodajAutobus(session, 50, "A23-M-789", "");
			fail("Nije bacen izuzetak");
		}
		catch (Exception e) {
			assertTrue(true);
		}
		session.close();
	}
	
	@Test
	public void testDodajAutobusLoseTablice() {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			HibernateAutobus a = new HibernateAutobus();
			a.dodajAutobus(session, 50, "123-456-789", "model");
			fail("Nije bacen izuzetak");
		}
		catch (Exception e) {
			assertTrue(true);
		}
		session.close();
	}
	
	@Test
	public void testDodajAutobusTabliceSpecijalniZnakovi() {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			HibernateAutobus a = new HibernateAutobus();
			a.dodajAutobus(session, 50, "A23*M*424", "model");
			fail("Nije bacen izuzetak");
		}
		catch (Exception e) {
			assertTrue(true);
		}
		session.close();
	}

	@Test
	public void testModifikujAutobus() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Autobus ab = new Autobus();
		ab.setKapacitet(50);
		ab.setModel("model");
		ab.setRegistracija("A23-M-789");
		HibernateAutobus a = new HibernateAutobus();
		a.dodajAutobus(session, ab.getKapacitet(), ab.getRegistracija(), ab.getModel());
		a.modifikujAutobus(session, "A23-J-489", "model3", 50, ab);
		Autobus nadjen = a.nadjiAutobus(session, "A23-J-489");
		assertEquals("A23-J-489", nadjen.getRegistracija());
		a.brisanjeAutobusa(session, "A23-J-489");
		session.close();
	}
	
	@Test
	public void testModifikujAutobusKapacitetIzuzetak() {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			HibernateAutobus a = new HibernateAutobus();
			Autobus ab = new Autobus();
			a.modifikujAutobus(session, "A23-M-789", "model", 0, ab);
			fail("Nije bacen izuzetak");
		}
		catch (Exception e) {
			assertTrue(true);
		}
		session.close();
	}
	
	@Test
	public void testModifikujAutobusLoseTablice() {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			HibernateAutobus a = new HibernateAutobus();
			Autobus ab = new Autobus();
			a.modifikujAutobus(session, "A23-M789", "model", 0, ab);
			fail("Nije bacen izuzetak");
		}
		catch (Exception e) {
			assertTrue(true);
		}
		session.close();
	}
	
	@Test
	public void testModifikujAutobusModelIzuzetak() {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			HibernateAutobus a = new HibernateAutobus();
			Autobus ab = new Autobus();
			a.modifikujAutobus(session, "A23-M789", "", 50, ab);
			fail("Nije bacen izuzetak");
		}
		catch (Exception e) {
			assertTrue(true);
		}
		session.close();
	}

	@Test
	public void testNadjiAutobusLoseTablice() { //treba li throws IllegalArgumentException
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			HibernateAutobus a = new HibernateAutobus();
			Autobus ab = new Autobus();
			//Long id=(Long) session.save(ab);
			ab = a.nadjiAutobus(session, "abc-d-efg");
			//assertEquals("abc-d-efg", ab.getRegistracija());
			fail("Nije bacen izuzetak");
		}
		catch (Exception e) {
			assertTrue(true);
		}
		session.close();
	}

	@Test
	public void testBrisanjeAutobusa() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateAutobus a = new HibernateAutobus();
		a.dodajAutobus(session, 50, "A23-M-456", "model");
		a.brisanjeAutobusa(session, "A23-M-456");
		assertEquals(null, a.nadjiAutobus(session, "A23-M-456"));
		session.close();
	}
	
	@Test
	public void testBrisanjeAutobusaLoseTablice() {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			HibernateAutobus a = new HibernateAutobus();
			a.brisanjeAutobusa(session, "abc-d-efg");
			fail("Nije bacen izuzetak");
		}
		catch (Exception e) {
			assertTrue(true);
		}
		session.close();
	}

	@Test
	public void testSviAutobusi() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateAutobus a = new HibernateAutobus();
		java.util.List autobusi;
		autobusi = a.sviAutobusi(session);
		int broj = autobusi.size();
		assertEquals(3, broj); //bila 3 u bazi
	}

}
