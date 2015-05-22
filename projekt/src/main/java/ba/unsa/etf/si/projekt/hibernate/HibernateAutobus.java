package ba.unsa.etf.si.projekt.hibernate;

import java.awt.List;


import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.projekt.entiteti.Autobus;
import ba.unsa.etf.si.projekt.entiteti.AutobuskaLinija;

import ba.unsa.etf.si.projekt.dodatno.Validacija;

public class HibernateAutobus {
	
	
	
	public static void main( String[] args)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		dodajAutobus(session, 10,"A23-M-424","dasdsa");
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
		int kap=0;
		int mod=0;
		int tablice=0;
		Validacija v = new Validacija();
		String string="";
		if(kapacitetautobusa==0)
		{
			kap=1;
			string+="Kapacitet je 0. ";
		}
		if(modelautobusa=="")
		{
			mod=1;
			string+=" Model je prazan";
		}
		if(!v.validirajTablice(registracijaautobusa)){
			tablice=1;
			string+=" Neispravna registarska tablica primjer ispravne A23-M-424";
		}
		Autobus a=new Autobus();
		if(kap==0 && mod==0 && tablice==0)
		{
		a.setKapacitet(kapacitetautobusa);
		a.setModel(modelautobusa);
		a.setRegistracija(registracijaautobusa);
		
		Long id=(Long) session.save(a);
		t.commit();
		}
		else
		{
			throw new IllegalArgumentException(string);
		}
		
	}
	public static void modifikujAutobus(Session session, String registracijaautobusa, String modelautobusa, int kapacitetautobusa, Autobus stari)
	{
		Transaction t = session.beginTransaction();
		int kap=0;
		int mod=0;
		int tablice=0;
		Validacija v = new Validacija();
		String string="";
		if(kapacitetautobusa==0)
		{
			kap=1;
			string+="Kapacitet je 0. ";
		}
		if(modelautobusa=="")
		{
			mod=1;
			string+=" Model je prazan";
		}
		if(!v.validirajTablice(registracijaautobusa)){
			tablice=1;
			string+=" Neispravna registarska tablica primjer ispravne A23-M-424";
		}
		
		if(kap==0 && mod==0 && tablice==0)
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
			t.commit();}}
		else
		{
			throw new IllegalArgumentException(string);
		}
		
	}
	
	public static Autobus nadjiAutobus(Session session,String registracijaautobusa)
	{      Validacija v = new Validacija();
         Transaction t = session.beginTransaction();
		
		if(registracijaautobusa!="" && !v.validirajTablice(registracijaautobusa))//dodati za tablice neki regex u javi
		{
			Criteria k=session.createCriteria(Autobus.class);
			k.add(Restrictions.eq("registracija", registracijaautobusa));
			Autobus a=(Autobus) k.uniqueResult();
		    t.commit();	
			return a;
		}
		else 
			throw new IllegalArgumentException("Neispravne registarske tablice");
	}
	
	public static void brisanjeAutobusa(Session session,String registracijaautobusa)
	{	Validacija v = new Validacija();
		Transaction t = session.beginTransaction();
		if(registracijaautobusa!="" && v.validirajTablice(registracijaautobusa))
		{
		String s="delete from Autobus where registracija=:registracija";
		session.createQuery(s).setString("registracija", registracijaautobusa).executeUpdate();
         t.commit();
		}
		else 
			throw new IllegalArgumentException("Neispravne registarske tablice");
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
