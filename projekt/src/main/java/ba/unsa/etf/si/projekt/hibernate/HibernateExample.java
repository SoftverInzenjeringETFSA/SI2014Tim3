package ba.unsa.etf.si.projekt.hibernate;
import org.hibernate.Transaction;
import org.hibernate.Session;

import ba.unsa.etf.si.projekt.entiteti.Autobus;

public class HibernateExample {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		dodajAutobus(session);
		nadjiAutobus(session, 1);
		session.close();
	}
	private static void dodajAutobus(Session session) {
		Transaction t = session.beginTransaction();
		
		Autobus a = new Autobus();
		a.setKapacitet(39);
		a.setModel("Mercedes");
		a.setRegistracija("aaa-aa-aaa");
		
		Long id = (Long) session.save(a);
		t.commit();
	}
	private static void nadjiAutobus(Session session, long id) {
		Transaction t = session.beginTransaction();
		Autobus s = (Autobus) session.get(Autobus.class, id);
		if (s==null) {
			System.out.println("Nema autobusa sa tim IDom u bazi");
		} else {
			System.out.println("Autobus: "+s.getKapacitet()+" "+s.getModel()+" "+s.getRegistracija());
		}
		t.commit();
	}
}
