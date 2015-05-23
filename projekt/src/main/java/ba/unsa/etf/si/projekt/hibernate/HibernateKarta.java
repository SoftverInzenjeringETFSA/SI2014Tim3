package ba.unsa.etf.si.projekt.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Transaction;

import javax.management.Query;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.projekt.entiteti.Autobus;
import ba.unsa.etf.si.projekt.entiteti.AutobuskaLinija;
import ba.unsa.etf.si.projekt.entiteti.Karta;
import ba.unsa.etf.si.projekt.entiteti.Radnik;
import ba.unsa.etf.si.projekt.entiteti.TipKarte;
import ba.unsa.etf.si.projekt.dodatno.Validacija;

public class HibernateKarta {
	
	public static void main( String[] args)
	{
		
	}
	public static void dodajKartu(Session session, AutobuskaLinija linija, int godina, int mjesec, int dan, int vrijeme_sati, int vrijeme_minute, TipKarte tipkarte,  double cijenakarte)
	{
		Transaction t = session.beginTransaction();
		Validacija v = new Validacija();
		if(linija.getAutobus().getKapacitet()>=linija.getZauzeto())
		{
		linija.setZauzeto(linija.getZauzeto()+1);
		}
		else
		{
			throw new IllegalArgumentException("Nema više mjesta u autobusu.");
		}
		if (v.validirajCijenuKarte(cijenakarte) == false)
			throw new IllegalArgumentException("Cijena karte mora biti pozitivan broj manji od 300!");
		else
		{
			Karta k=new Karta();
			k.setLinija(linija);
			k.setVrijemePolaska_sati(vrijeme_sati);
			k.setVrijemePolaska_minute(vrijeme_minute);
			k.setTipKarte(tipkarte);
			k.setDatumPolaska_godina(godina);
			k.setDatumPolaska_mjesec(mjesec);
			k.setDatumPolaska_dan(dan);
			k.setCijena(cijenakarte);
			
			Long id=(Long) session.save(k);
			t.commit();
		}
	}
	
	public static void brisanjeKarte(Session session, long kartaid)
	{
		  Transaction t = session.beginTransaction();
		  Criteria k=session.createCriteria(Karta.class);
			k.add(Restrictions.eq("id", kartaid));
			Karta karta=(Karta)k.uniqueResult();
			session.delete(karta);
		    t.commit();	
	}
	
	public static Karta nadjiKartu(Session session, long kartaid)
	{
		  Transaction t = session.beginTransaction();
		  Criteria k=session.createCriteria(Karta.class);
			k.add(Restrictions.eq("id", kartaid));
			Karta karta=(Karta)k.uniqueResult();
		    t.commit();	
		    return karta;
	}
	
	public static java.util.List sveKarte(Session session)
	{
		Transaction t = session.beginTransaction();
		java.util.List karte;
		karte=session.createQuery("FROM Karta").list();
		t.commit();
		return karte;
		
	}
	
	public static java.util.List IzvjestajOProdanimKartama(Session session,int pocetnagodina, int pocetnimjesec, int pocetnidan, int krajnjagodina, int krajnjimjesec, int krajnjidan)
	{
		Transaction t = session.beginTransaction();
		HibernateRadnik radnik=new HibernateRadnik();
		java.util.List linije;
		linije=session.createQuery("FROM Karta where datumkupovine_dan>="+pocetnidan+" and datumkupovine_dan<="+krajnjidan+" and datumkupovine_mjesec>="+pocetnimjesec+" and datumkupovine_mjesec<="+krajnjimjesec+"and datumkupovine_godina>="+pocetnagodina+" and datumkupovine_godina<="+krajnjagodina+"").list();
        System.out.println(linije.size());
		return linije;
	
	}

	
	public HibernateKarta(){}

}
