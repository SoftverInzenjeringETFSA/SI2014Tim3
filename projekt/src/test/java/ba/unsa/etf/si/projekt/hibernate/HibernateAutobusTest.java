package ba.unsa.etf.si.projekt.hibernate;

import static org.junit.Assert.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import ba.unsa.etf.si.projekt.entiteti.Autobus;

public class HibernateAutobusTest {
	
	Autobus ab;
	HibernateAutobus a;
	
	@Before
	public void priprema() {
		ab = new Autobus();
		ab.setKapacitet(50);
		ab.setModel("model");
		ab.setRegistracija("A23-M-789");
		a = new HibernateAutobus();
	}
	
	@Test
	public void testDodajiNadjiAutobus() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		//Autobus ab = new Autobus();
		/*ab.setKapacitet(50);
		ab.setModel("model");
		ab.setRegistracija("A23-M-789");
		HibernateAutobus a = new HibernateAutobus();*/
		a.dodajAutobus(session, ab.getKapacitet(), ab.getRegistracija(), ab.getModel());
		Autobus nadjen = a.nadjiAutobus(session, ab.getRegistracija());
		assertEquals("A23-M-789", nadjen.getRegistracija());
		a.brisanjeAutobusa(session, ab.getRegistracija());
		session.close();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDodajAutobusNegativanKapacitet() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		//HibernateAutobus a = new HibernateAutobus();
		a.dodajAutobus(session, -5, "A23-M-789", "model");
		session.close();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDodajAutobusOgromanKapacitet() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		//HibernateAutobus a = new HibernateAutobus();
		a.dodajAutobus(session, 1000, "A23-M-789", "model");
		session.close();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDodajAutobusModelIzuzetak() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		a.dodajAutobus(session, 50, "A23-M-789", "");
		session.close();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDodajAutobusLoseTablice() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		a.dodajAutobus(session, 50, "123-456-789", "model");
		session.close();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDodajAutobusTabliceSpecijalniZnakovi() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		a.dodajAutobus(session, 50, "A23*M*424", "model");
		session.close();
	}

	@Test
	public void testModifikujAutobus() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		/*Autobus ab = new Autobus();
		ab.setKapacitet(50);
		ab.setModel("model");
		ab.setRegistracija("A23-M-789");
		HibernateAutobus a = new HibernateAutobus();*/
		a.dodajAutobus(session, ab.getKapacitet(), ab.getRegistracija(), ab.getModel());
		a.modifikujAutobus(session, "A23-J-489", "model3", 50, ab);
		Autobus nadjen = a.nadjiAutobus(session, "A23-J-489");
		assertEquals("A23-J-489", nadjen.getRegistracija());
		a.brisanjeAutobusa(session, "A23-J-489");
		session.close();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testModifikujAutobusKapacitetIzuzetak() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		a.modifikujAutobus(session, "A23-M-789", "model", 0, ab);
		session.close();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testModifikujAutobusLoseTablice() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		a.modifikujAutobus(session, "A23-M789", "model", 0, ab);
		session.close();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testModifikujAutobusModelIzuzetak() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		a.modifikujAutobus(session, "A23-M789", "", 50, ab);
		session.close();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNadjiAutobusLoseTablice() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		ab = a.nadjiAutobus(session, "abc-d-efg");
		session.close();
	}

	@Test
	public void testBrisanjeAutobusa() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		//HibernateAutobus a = new HibernateAutobus();
		a.dodajAutobus(session, 50, "A23-M-456", "model");
		a.brisanjeAutobusa(session, "A23-M-456");
		assertEquals(null, a.nadjiAutobus(session, "A23-M-456"));
		session.close();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testBrisanjeAutobusaLoseTablice() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		a.brisanjeAutobusa(session, "abc-d-efg");
		session.close();
	}

	@Test
	public void testSviAutobusi() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query q = session.createQuery("SELECT COUNT(*) FROM Autobus");
		Long count = (Long)q.uniqueResult();
		int izBaze = count.intValue();
		//HibernateAutobus a = new HibernateAutobus();
		java.util.List autobusi;
		autobusi = a.sviAutobusi(session);
		int broj = autobusi.size();
		assertEquals(broj, izBaze); 
	}

}
