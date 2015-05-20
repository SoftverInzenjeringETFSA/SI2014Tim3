package ba.unsa.etf.si.projekt.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.projekt.entiteti.Autobus;
import ba.unsa.etf.si.projekt.entiteti.AutobuskaLinija;
import ba.unsa.etf.si.projekt.entiteti.KorisnickiRacun;
import ba.unsa.etf.si.projekt.entiteti.Radnik;
import ba.unsa.etf.si.projekt.entiteti.TipKorisnickogRacuna;

public class HibernateKorisnickiRacuni {
 
	public static void main( String[] args)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		TipKorisnickogRacuna r=TipKorisnickogRacuna.menadzer;
		//dodajKorisnickiRacun(session, "Ilvana","Brankovic","2412993178512","Ive","ilv123",r);
		//modifikujKorisnickiRacun(session, "Ilvana","Ive","Branković","2412993178512","Ive1","ilv123",r);
		//brisiKorisnickiRacun(session,"Ive1");
		nadjiKorisnickiRacun(session,"Ive");
		java.util.List racuni;
		racuni=sviRacuni(session);
		for(int i=0;i<racuni.size();i++)
		{
			KorisnickiRacun au=(KorisnickiRacun)racuni.get(i);
			System.out.println(au.getKorisnickoIme());
		}
		session.close();
	}
	
	public static void dodajKorisnickiRacun(Session session, Radnik radnik, TipKorisnickogRacuna tipracuna, String korisnickoimekorisnika, String passkorisnika)
	{
		Transaction t = session.beginTransaction();
		
		KorisnickiRacun kr=new KorisnickiRacun();
		kr.setIme(radnik.getIme());
		kr.setPrezime(radnik.getPrezime());
		kr.setJmbg(radnik.getJmbg());
		kr.setKorisnickoIme(korisnickoimekorisnika);
		kr.setTipKorisnickogRacuna(tipracuna);
		kr.setSifra(passkorisnika);
		
		Long id=(Long) session.save(kr);
		t.commit();
	}
	
	public static void modifikujKorisnickiRacun(Session session, String starokorisnickoime, String korisnickoimekorisnika, String passkorisnika, TipKorisnickogRacuna tipracunakorisnika)
	{
		Transaction t = session.beginTransaction();
		
		Criteria k=session.createCriteria(KorisnickiRacun.class);
		k.add(Restrictions.eq("korisnickoIme",starokorisnickoime));
		KorisnickiRacun kr= (KorisnickiRacun) k.uniqueResult();
		
		if(kr!=null)
		{
		kr.setTipKorisnickogRacuna(tipracunakorisnika);
		kr.setSifra(passkorisnika);
		kr.setKorisnickoIme(korisnickoimekorisnika);
		
		session.save(kr);
		t.commit();
		}
	}
	
	public static void brisiKorisnickiRacun(Session session, String korisnickoimekorisnika)
	{
       Transaction t = session.beginTransaction();
		
		Criteria k=session.createCriteria(KorisnickiRacun.class);
		k.add(Restrictions.eq("korisnickoIme",korisnickoimekorisnika));
		KorisnickiRacun kr= (KorisnickiRacun) k.uniqueResult();
		session.delete(kr);
		t.commit();
	}
	 
	public static KorisnickiRacun nadjiKorisnickiRacun(Session session, String korisnickoimekorisnika)
	{
        Transaction t = session.beginTransaction();
		
		Criteria k=session.createCriteria(KorisnickiRacun.class);
		k.add(Restrictions.eq("korisnickoIme",korisnickoimekorisnika));
		KorisnickiRacun kr= (KorisnickiRacun) k.uniqueResult();
		t.commit();
		
		return kr;
	}
	
	public static java.util.List sviRacuni(Session session)
	{
		Transaction t = session.beginTransaction();
		java.util.List racuni;
		racuni=session.createQuery("FROM KorisnickiRacun").list();
		t.commit();
		return racuni;
		
	}
	public HibernateKorisnickiRacuni(){}
}
