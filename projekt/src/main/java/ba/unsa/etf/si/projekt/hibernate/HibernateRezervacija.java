package ba.unsa.etf.si.projekt.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.projekt.entiteti.Rezervacija;

public class HibernateRezervacija {

	public static void main( String[] args)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
	}
	public static void dodajRezervaciju()
	{
		
	}
}
