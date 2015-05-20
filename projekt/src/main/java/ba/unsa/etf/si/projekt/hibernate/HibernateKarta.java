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
		Session session = HibernateUtil.getSessionFactory().openSession();
		TipKarte k=TipKarte.jednosmjerna;
		//dodajKartu(session, "Mostar", 2,2,k,2015,5,22,21.0);
		//dodajKartu(session, "Sarajevo", 2,2,k,2015,6,21,21.0);
		dodajKartu(session,"Mostar",2,2,k,2015,4,17,12.0);		
		java.util.List karte1;
		karte1=sveKarte(session);
		/*java.util.List karte=IzvjestajOProdanimKartama(session,2014,2,2,2015,7,30);
		for(int i=0;i<karte.size();i++)
		{
			Karta au=(Karta)karte.get(i);
			System.out.println(au.getOdrediste());
		}*/
		session.close();
	}
	public static void dodajKartu(Session session, String odrediste, int vrijeme_sati, int vrijeme_minute, TipKarte tipkarte, int godina, int mjesec, int dan, double cijenakarte )
	{
		Transaction t = session.beginTransaction();
		HibernateAutibuskaLinija linija=new HibernateAutibuskaLinija();
		AutobuskaLinija trazenaLinija=new AutobuskaLinija();
		trazenaLinija=linija.NadjiAutobuskuLinijuOdrediste(session, odrediste, godina, mjesec, dan, vrijeme_sati, vrijeme_minute);
		linija.ModifikujZauzetostLinije(session,trazenaLinija.getBrojLinije());
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
	
	public static java.util.List IzvjestajOProdanimKartama(Session session,int pocetnagodina, int pocetnimjesec, int pocetnidan, int krajnjagodina, int krajnjimjesec, int krajnjidan)
	{
		Transaction t = session.beginTransaction();
		HibernateRadnik radnik=new HibernateRadnik();
		java.util.List linije;
		linije=session.createQuery("FROM Karta where datumPolaska_dan>="+pocetnidan+" and datumPolaska_dan<="+krajnjidan+" and datumPolaska_mjesec>="+pocetnimjesec+" and datumPolaska_mjesec<="+krajnjimjesec+"and datumPolaska_dan>="+pocetnidan+" and datumPolaska_dan<="+krajnjidan+"").list();
        System.out.println(linije.size());
		return linije;
	
	}

	
	public HibernateKarta(){}

}
