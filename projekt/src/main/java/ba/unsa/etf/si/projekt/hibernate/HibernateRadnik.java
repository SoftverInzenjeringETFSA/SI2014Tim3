package ba.unsa.etf.si.projekt.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.projekt.entiteti.Radnik;
import ba.unsa.etf.si.projekt.entiteti.TipRadnogMjesta;
import ba.unsa.etf.si.projekt.dodatno.Validacija;

public class HibernateRadnik {
	
	public static void main( String[] args)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		TipRadnogMjesta m=TipRadnogMjesta.Administrator;
		dodajRadnika(session, "Larisa","Besic","1223493827163", m);
	//	dodajRadnika(session, "Sumeja","Botulja","1223493827163",m);
	//	modifikujRadnika(session,"Sumeja1","Botulja1","1223493827163",m);
		//dodajRadnika(session,"Mirhat","Babic","2412993178514",m);
	//    brisiRadnika(session,"1223493827163");
		
	/*	nadjiRadnika(session,"1223493827164");
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
		Validacija v = new Validacija();
		int i = 0, p = 0, j = 0;
		String izuzetak = "";
		if (v.validirajJMBG(JMBGradnika) == false || v.praznoPolje(JMBGradnika))
		{
			j = 1;
			izuzetak += "JMBG mora biti validan (13 cifara) i ne smije biti prazno polje!\n";
		}
		if (v.jeTekst(imeradnika) == false || v.praznoPolje(imeradnika))
		{
			i = 1;
			izuzetak += "Ime radnika mora biti tekst i ne smije biti prazno polje!\n";
		}
		if (v.jeTekst(prezimeradnika) == false || v.praznoPolje(prezimeradnika))
		{
			p = 1;
			izuzetak += "Prezime radnika mora biti tekst i ne smije biti prazno polje!\n";
		}
		if (i == 0 && j == 0 && p == 0)
		{
			Radnik r=new Radnik();
			r.setIme(imeradnika);
			r.setPrezime(prezimeradnika);
			r.setJmbg(JMBGradnika);
			r.setTipRadnogMjesta(tipradnogmjestaradnika);
			
			Long id=(Long) session.save(r);
			t.commit();
		}
		else
		{
			throw new IllegalArgumentException(izuzetak);
		}
	}
	
	public static void modifikujRadnika(Session session, String imeradnika, String prezimeradnika, String JMBGRadnika, TipRadnogMjesta tipradnogmjestaradnika)
	{
		Transaction t = session.beginTransaction();
		Validacija v = new Validacija();
		int i = 0, p = 0;
		String izuzetak = "";
		Criteria k=session.createCriteria(Radnik.class);
		k.add(Restrictions.eq("jmbg", JMBGRadnika));
		Radnik r=(Radnik) k.uniqueResult();

		if (v.jeTekst(imeradnika) == false || v.praznoPolje(imeradnika))
		{
			i = 1;
			izuzetak += "Ime radnika mora biti tekst i ne smije biti prazno polje!\n";
		}
		if (v.jeTekst(prezimeradnika) == false || v.praznoPolje(prezimeradnika))
		{
			p = 1;
			izuzetak += "Prezime radnika mora biti tekst i ne smije biti prazno polje!\n";
		}
		if (i == 0 && p == 0)
		{
			r.setIme(imeradnika);
			r.setPrezime(prezimeradnika);
			r.setTipRadnogMjesta(tipradnogmjestaradnika);
			session.save(r);
			t.commit();
		}
		else
		{
			throw new IllegalArgumentException(izuzetak);
		}	
	}
	
	public static void brisiRadnika(Session session, String JMBGRadnika)
	{
		Transaction t = session.beginTransaction();
		Validacija v = new Validacija();
		if (v.validirajJMBG(JMBGRadnika) == false || v.praznoPolje(JMBGRadnika))
		{
			throw new IllegalArgumentException("JMBG mora biti validan (13 cifara) i ne smije biti prazno polje!\n");
		}
		else
		{
			Criteria k=session.createCriteria(Radnik.class);
			k.add(Restrictions.eq("jmbg", JMBGRadnika));
			Radnik r=(Radnik) k.uniqueResult();
			session.delete(r);
			t.commit();
		}
	}
	
	public static Radnik nadjiRadnika(Session session, String JMBGRadnika)
	{
        Transaction t = session.beginTransaction();
		Validacija v = new Validacija();
		Criteria k=session.createCriteria(Radnik.class);
		k.add(Restrictions.eq("jmbg", JMBGRadnika));
		Radnik r=(Radnik) k.uniqueResult();
		if (v.validirajJMBG(JMBGRadnika) == false || v.praznoPolje(JMBGRadnika))
		{
			throw new IllegalArgumentException("JMBG mora biti validan (13 cifara) i ne smije biti prazno polje!\n");
		}
		else
		{
			t.commit();
			return r;
		}
	}
	
	public static Radnik nadjiRadnikaPoImenu(Session session, String ime)
	{
        Transaction t = session.beginTransaction();
		Validacija v = new Validacija();
		Criteria k=session.createCriteria(Radnik.class);
		k.add(Restrictions.eq("ime", ime));
		Radnik r=(Radnik) k.uniqueResult();
		if (v.jeTekst(ime) == false || v.praznoPolje(ime))
		{
			throw new IllegalArgumentException("Ime radnika mora biti tekst i ne smije biti prazno polje!\n");
		}
		else
		{
			t.commit();
			return r;
		}
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
