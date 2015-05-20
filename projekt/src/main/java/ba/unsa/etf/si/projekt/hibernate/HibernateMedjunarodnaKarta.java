
package ba.unsa.etf.si.projekt.hibernate;

import org.hibernate.Transaction;
import org.hibernate.Session;

import ba.unsa.etf.si.projekt.entiteti.AutobuskaLinija;
import ba.unsa.etf.si.projekt.entiteti.Karta;
import ba.unsa.etf.si.projekt.entiteti.TipKarte;
import ba.unsa.etf.si.projekt.entiteti.MedjunarodnaKarta;

public class HibernateMedjunarodnaKarta {
	
	public static void main( String[] args)
	{
		
	}
	
	public static java.util.List sveMedjunarodneKarte(Session session)
	{
		Transaction t = session.beginTransaction();
		java.util.List karte;
		karte=session.createQuery("FROM MedjunarodnaKarta").list();
		t.commit();
		return karte;
		
	}
	public HibernateMedjunarodnaKarta(){}
	
	public static void dodajKartu(Session session, AutobuskaLinija linija,
			int godina, int mjesec, int dan, int sati, int minute, TipKarte tip,
			double cijena, String ime, String prezime) {
		
		Transaction t = session.beginTransaction();
		linija.setZauzeto(linija.getZauzeto()+1);
		MedjunarodnaKarta k=new MedjunarodnaKarta();
		k.setLinija(linija);
		k.setVrijemePolaska_sati(sati);
		k.setVrijemePolaska_minute(minute);
		k.setTipKarte(tip);
		k.setDatumPolaska_godina(godina);
		k.setDatumPolaska_mjesec(mjesec);
		k.setDatumPolaska_dan(dan);
		k.setCijena(cijena);
		k.setIme(ime);
		k.setPrezime(prezime);
		
		Long id=(Long) session.save(k);
		t.commit();
		
	}

}
