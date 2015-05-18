package ba.unsa.etf.si.projekt.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.projekt.entiteti.Radnik;
import ba.unsa.etf.si.projekt.entiteti.TipRadnogMjesta;

public class HibernateRadnik {
	
	public static void main( String[] args)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		TipRadnogMjesta m=TipRadnogMjesta.Vozac;
		dodajRadnika(session, "Sumeja","Botulja","2412993178512",m);
		//modifikujRadnika(session,"Sumeja1","Botulja","2412993178512",m);
		//dodajRadnika(session,"Mirhat","Babic","2412993178514",m);
		//brisiRadnika(session,"2412993178514");
		/*
		nadjiRadnika(session,"2412993178512");
		java.util.List radnici;
		radnici=sviRadnici(session);
		for(int i=0;i<radnici.size();i++)
		{
			Radnik au=(Radnik)radnici.get(i);
			System.out.println(au.getIme());
		}
		*/
		session.close();
	}
	public static void dodajRadnika( Session session, String imeradnika, String prezimeradnika, String JMBGradnika, TipRadnogMjesta tipradnogmjestaradnika)
	{
		Transaction t = session.beginTransaction();
		
		Radnik r=new Radnik();
		r.setIme(imeradnika);
		r.setPrezime(prezimeradnika);
		r.setJmbg(JMBGradnika);
		r.setTipRadnogMjesta(tipradnogmjestaradnika);
		
		Long id=(Long) session.save(r);
		t.commit();
		
	}
	
	public static void modifikujRadnika(Session session, String imeradnika, String prezimeradnika, String JMBGRadnika, TipRadnogMjesta tipradnogmjestaradnika)
	{
		Transaction t = session.beginTransaction();
		
		Criteria k=session.createCriteria(Radnik.class);
		k.add(Restrictions.eq("jmbg", JMBGRadnika));
		Radnik r=(Radnik) k.uniqueResult();
		
		r.setIme(imeradnika);
		r.setPrezime(prezimeradnika);
		r.setTipRadnogMjesta(tipradnogmjestaradnika);
		session.save(r);
		t.commit();
	}
	
	public static void brisiRadnika(Session session, String JMBGRadnika)
	{
        Transaction t = session.beginTransaction();
		
		Criteria k=session.createCriteria(Radnik.class);
		k.add(Restrictions.eq("jmbg", JMBGRadnika));
		Radnik r=(Radnik) k.uniqueResult();
		session.delete(r);
		t.commit();
	}
	
	public static Radnik nadjiRadnika(Session session, String JMBGRadnika)
	{
       Transaction t = session.beginTransaction();
		
		Criteria k=session.createCriteria(Radnik.class);
		k.add(Restrictions.eq("jmbg", JMBGRadnika));
		Radnik r=(Radnik) k.uniqueResult();
		t.commit();
		return r;
	}
	
	public static Radnik nadjiRadnikaPoImenu(Session session, String ime)
	{
        Transaction t = session.beginTransaction();
		
		Criteria k=session.createCriteria(Radnik.class);
		k.add(Restrictions.eq("ime", ime));
		Radnik r=(Radnik) k.uniqueResult();
		t.commit();
		return r;
	}
	public static java.util.List sviRadnici(Session session)
	{
		Transaction t = session.beginTransaction();
		java.util.List radnici;
		radnici=session.createQuery("FROM Radnik").list();
		t.commit();
		return radnici;
		
	}
	
	public HibernateRadnik()
	{}
}
