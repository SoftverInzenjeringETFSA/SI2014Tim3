package ba.unsa.etf.si.projekt.hibernate;

import java.awt.List;


import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.projekt.entiteti.Autobus;
import ba.unsa.etf.si.projekt.entiteti.AutobuskaLinija;

public class HibernateAutobus {
	
	
	
	public static void main( String[] args)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		dodajAutobus(session, 32,"abc-d-efg","BMW");
	    //brisanjeAutobusa(session,"abc-d-efg");
		//modifikujAutobus(session,"eee-e-eee","audi",242);
		//Autobus a=nadjiAutobus(session, "eee-e-eed");
	    /*java.util.List autobusi;
		autobusi=sviAutobusi(session);
		for(int i=0;i<autobusi.size();i++)
		{
			Autobus au=(Autobus)autobusi.get(i);
			System.out.println(au.getRegistracija());
		}
		*/
		session.close();
	}
	public static void dodajAutobus(Session session,int kapacitetautobusa, String registracijaautobusa, String modelautobusa)
	{
		Transaction t = session.beginTransaction();
		
		Autobus a=new Autobus();
		if(kapacitetautobusa!=0 && modelautobusa!="")//dodati za tablice neki regex u javi 
		{
		a.setKapacitet(kapacitetautobusa);
		a.setModel(modelautobusa);
		a.setRegistracija(registracijaautobusa);
		
		Long id=(Long) session.save(a);
		t.commit();
		}
		else 
			throw new IllegalArgumentException("Morate unijeti kapacitet i model autobusa.");
	}
	public static void modifikujAutobus(Session session, String registracijaautobusa, String modelautobusa, int kapacitetautobusa, Autobus stari)
	{
		Transaction t = session.beginTransaction();
		
		if(modelautobusa!="" && kapacitetautobusa!=0)//dodati za tablice neki regex u javi
		{
			Criteria k=session.createCriteria(Autobus.class);
			k.add(Restrictions.eq("registracija", stari.getRegistracija()));
			Autobus a=(Autobus) k.uniqueResult();
			
			if(a!=null)
			{
			a.setKapacitet(kapacitetautobusa);
			a.setModel(modelautobusa);
			a.setRegistracija(registracijaautobusa);
			session.save(a);
			t.commit();
			}
		}
		else 
			throw new IllegalArgumentException("Morate unijeti kapacitet i model autobusa.");
		
	}
	
	public static Autobus nadjiAutobus(Session session,String registracijaautobusa)
	{
         Transaction t = session.beginTransaction();
		
		if(registracijaautobusa!="")//dodati za tablice neki regex u javi
		{
			Criteria k=session.createCriteria(Autobus.class);
			k.add(Restrictions.eq("registracija", registracijaautobusa));
			Autobus a=(Autobus) k.uniqueResult();
		    t.commit();	
			return a;
		}
		else 
			throw new IllegalArgumentException("Morate unijeti registraciju autobusa");
	}
	
	public static void brisanjeAutobusa(Session session,String registracijaautobusa)
	{
		Transaction t = session.beginTransaction();
		if(registracijaautobusa!="")
		{
		String s="delete from Autobus where registracija=:registracija";
		session.createQuery(s).setString("registracija", registracijaautobusa).executeUpdate();
         t.commit();
		}
		else 
			throw new IllegalArgumentException("Morate unijeti kapacitet i model autobusa.");
	}
	
	public static java.util.List sviAutobusi(Session session)
	{
		Transaction t = session.beginTransaction();
		java.util.List autobusi;
		autobusi=session.createQuery("FROM Autobus").list();
		t.commit();
		return autobusi;
		
	}
	
	public HibernateAutobus()
	{}
}
