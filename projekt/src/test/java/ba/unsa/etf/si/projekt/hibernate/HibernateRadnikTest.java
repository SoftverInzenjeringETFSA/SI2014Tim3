package ba.unsa.etf.si.projekt.hibernate;

import static org.junit.Assert.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ba.unsa.etf.si.projekt.entiteti.Radnik;
import ba.unsa.etf.si.projekt.entiteti.TipRadnogMjesta;

public class HibernateRadnikTest {
	
	Radnik r;
	HibernateRadnik hr;
	TipRadnogMjesta m2;
	TipRadnogMjesta m1;
	TipRadnogMjesta m;
	
	@Before
	public void priprema(){
		hr = new HibernateRadnik();
		m2 = TipRadnogMjesta.Administrator;
		m1 = TipRadnogMjesta.SalterskiRadnik;
		m = TipRadnogMjesta.Menadzer;
		r = new Radnik();
		r.setIme("Dinaaa");
		r.setPrezime("A");
		r.setJmbg("1478523694512");
		r.setTipRadnogMjesta(m);
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Long id=(Long) session.save(r);
		r.setId(id);
		t.commit();
		session.close();
	}
	
	@After
	public void kraj(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(r);
		t.commit();
		session.close();
	}
	
	@Test
	public void testDodajiNadjiRadnika() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		hr.dodajRadnika(session, "Dinaa", "A", "1234567891478", m1);
		Radnik nadjen = hr.nadjiRadnika(session, "1234567891478");
		assertEquals("1234567891478", nadjen.getJmbg());
		hr.brisiRadnika(session, nadjen.getJmbg());
		session.close();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDodajRadnikaBezJMBG() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		hr.dodajRadnika(session, "Dina", "A", "", m);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDodajRadnikaBezImena() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		hr.dodajRadnika(session, "", "A", "1478523697456", m);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDodajRadnikaBezPrezimena() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		hr.dodajRadnika(session, "Dina", "", "1478523697456", m);
	}

	@Test
	public void testModifikujRadnika() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		hr.modifikujRadnika(session, "Dina", "A", "1478523694512", m2);
	}
	
	@Test(expected = NullPointerException.class)
	public void testModifikujRadnikaPogresanJMBG() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		hr.modifikujRadnika(session, "Dina", "A", "1478523694513", m2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testModifikujRadnikaBezImena() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		hr.modifikujRadnika(session, "", "A", "1478523697456", m);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testModifikujRadnikaBezPrezimena() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		hr.modifikujRadnika(session, "Dina", "", "1478523697456", m);
	}

	@Test
	public void testBrisiRadnika() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		hr.dodajRadnika(session, "Sumeja", "B", "7536124897561", m);
		hr.brisiRadnika(session, "7536124897561");
		assertEquals(null, hr.nadjiRadnika(session, "7536124897561"));
		session.close();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testBrisiRadnikaNevalidanJMBG() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		hr.brisiRadnika(session, "1458796");
	}

	@Test
	public void testNadjiRadnika() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Radnik nadjen = hr.nadjiRadnika(session, "1478523694512");
		assertEquals("1478523694512", nadjen.getJmbg());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNadjiRadnikaNevalidanJMBG() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		r = hr.nadjiRadnika(session, "***");
	}

	@Test
	public void testNadjiRadnikaPoImenu() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Radnik nadjen = hr.nadjiRadnikaPoImenu(session, "Dinaaa");
		assertEquals("Dinaaa", nadjen.getIme());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNadjiRadnikaPoImenuBezImena() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		r = hr.nadjiRadnika(session, "");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNadjiRadnikaPoImenuNevalidnoIme() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		r = hr.nadjiRadnika(session, "Dina123");
	}

	@Test
	public void testSviRadnici() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query q = session.createQuery("SELECT COUNT(*) FROM Radnik");
		Long count = (Long)q.uniqueResult();
		int izBaze = count.intValue();
		java.util.List radnici;
		radnici = hr.sviRadnici(session);
		int broj = radnici.size();
		assertEquals(broj, izBaze); 
	}

}
