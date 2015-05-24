package ba.unsa.etf.si.projekt.hibernate;

import static org.junit.Assert.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import ba.unsa.etf.si.projekt.entiteti.Autobus;
import ba.unsa.etf.si.projekt.entiteti.AutobuskaLinija;
import ba.unsa.etf.si.projekt.entiteti.Nalog;
import ba.unsa.etf.si.projekt.entiteti.Radnik;
import ba.unsa.etf.si.projekt.entiteti.TipRadnogMjesta;

public class HibernateNalogTest {

	@Test
	public void testDodajNalog() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateAutobus.dodajAutobus(session, 55, "A23-M-415", "m12");
		Autobus a=HibernateAutobus.nadjiAutobus(session, "A23-M-415");
		HibernateRadnik.dodajRadnika(session, "Sara", "Saric", "1911989147824", TipRadnogMjesta.Vozac);
		Radnik r=HibernateRadnik.nadjiRadnikaPoImenu(session, "Sara");
		HibernateAutibuskaLinija.dodajAutobuskuLiniju(session, "Sarajevo", "Bihac", a, r, 2015, 05, 23, 13, 55, 2, 800, 3,10,16.50, 31.00, false);
		AutobuskaLinija al=HibernateAutibuskaLinija.nadjiAutobuskuLiniju(session, 10);
		//prijedodavanja
		Query q = session.createQuery("SELECT COUNT(*) FROM	Nalog");
		Long count =(Long)q.uniqueResult();
		int prije=count.intValue();
		HibernateNalog.dodajNalog(session, al, 24, 5, 2015, 3, 45);
		//poslije dodavanja
		Query q2 = session.createQuery("SELECT COUNT(*) FROM Nalog");
		Long count2 =(Long)q2.uniqueResult();
		int poslije=count2.intValue();
		assertEquals(poslije,prije+1);
	
		HibernateNalog.brisiNalog(session, al, 24, 5, 2015, 3, 45);
		HibernateAutibuskaLinija.brisiAutobuskuLiniju(session, 10);
		HibernateRadnik.brisiRadnika(session, "1911989147824");
		HibernateAutobus.brisanjeAutobusa(session, "A23-M-415");	
		session.close();
		
	}

}
