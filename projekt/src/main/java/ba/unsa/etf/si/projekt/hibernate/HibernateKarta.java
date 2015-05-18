package ba.unsa.etf.si.projekt.hibernate;

import org.hibernate.Transaction;
import javax.management.Query;
import org.hibernate.Session;

import ba.unsa.etf.si.projekt.entiteti.AutobuskaLinija;
import ba.unsa.etf.si.projekt.entiteti.Karta;
import ba.unsa.etf.si.projekt.entiteti.TipKarte;

public class HibernateKarta {
	
	public static void main( String[] args)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		TipKarte k=TipKarte.jednosmjerna;
		//dodajKartu(session, "Tuzla", 2,2,k,2015,5,22,21.0);
		//dodajKartu(session, "Sarajevo", 2,2,k,2015,6,21,21.0);
		
		java.util.List karte1;
		karte1=sveKarte(session);
		java.util.List karte=IzvjestajOProdanimKartama(session,karte1,2014,2,2,2015,7,30);
		for(int i=0;i<karte.size();i++)
		{
			Karta au=(Karta)karte.get(i);
			System.out.println(au.getOdrediste());
		}
		session.close();
	}
	public static void dodajKartu(Session session, String odrediste, int vrijeme_sati, int vrijeme_minute, TipKarte tipkarte, int godina, int mjesec, int dan, double cijenakarte )
	{
		Transaction t = session.beginTransaction();
		
		Karta k=new Karta();
		k.setOdrediste(odrediste);
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
	
	public static java.util.List sveKarte(Session session)
	{
		Transaction t = session.beginTransaction();
		java.util.List karte;
		karte=session.createQuery("FROM Karta").list();
		t.commit();
		return karte;
		
	}
	
	public static java.util.List IzvjestajOProdanimKartama(Session session,java.util.List karte,int pocetnagodina, int pocetnimjesec, int pocetnidan, int krajnjagodina, int krajnjimjesec, int krajnjidan)
	{
		Transaction t = session.beginTransaction();
		//java.util.List karte;
		//karte=sveKarte(session);
		java.util.List<Karta> rezultujuceKarte=null;
		for(int i=0;i<karte.size();i++)
		{
			Karta a=(Karta)karte.get(i);
			if(a.getDatumPolaska_dan()>=pocetnidan && a.getDatumPolaska_dan()<=krajnjidan && a.getDatumPolaska_godina()>=pocetnagodina && a.getDatumPolaska_godina()<=krajnjagodina && a.getDatumPolaska_mjesec()>=pocetnimjesec && a.getDatumPolaska_mjesec()<=krajnjimjesec)
			{

				boolean da=rezultujuceKarte.add(a);
			}
		}
		
		return rezultujuceKarte;
	}

	
	public HibernateKarta(){}

}
