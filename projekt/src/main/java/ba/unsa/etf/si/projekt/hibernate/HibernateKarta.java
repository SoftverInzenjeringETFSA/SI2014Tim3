package ba.unsa.etf.si.projekt.hibernate;

import org.hibernate.Transaction;

import javax.management.Query;

import org.hibernate.Session;

import ba.unsa.etf.si.projekt.entiteti.AutobuskaLinija;
import ba.unsa.etf.si.projekt.entiteti.Karta;
import ba.unsa.etf.si.projekt.entiteti.Radnik;
import ba.unsa.etf.si.projekt.entiteti.TipKarte;

public class HibernateKarta {
	
	public static void main( String[] args)
	{
		
	}
	public static void dodajKartu(Session session, AutobuskaLinija linija, int godina, int mjesec, int dan, int vrijeme_sati, int vrijeme_minute, TipKarte tipkarte,  double cijenakarte)
	{
		Transaction t = session.beginTransaction();
		linija.setZauzeto(linija.getZauzeto()+1);
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
