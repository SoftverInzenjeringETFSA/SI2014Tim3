
package ba.unsa.etf.si.projekt.hibernate;

import org.hibernate.Transaction;
import org.hibernate.Session;

import ba.unsa.etf.si.projekt.entiteti.AutobuskaLinija;
import ba.unsa.etf.si.projekt.entiteti.Karta;
import ba.unsa.etf.si.projekt.entiteti.TipKarte;
import ba.unsa.etf.si.projekt.entiteti.MedjunarodnaKarta;
import ba.unsa.etf.si.projekt.dodatno.Validacija;

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
		Validacija v = new Validacija();
		int c = 0, i = 0, p = 0;
		String izuzetak = "";
		if (v.validirajCijenuKarte(cijena) == false || v.praznoPolje(String.valueOf(cijena)))
		{
			c = 1;
			izuzetak += "Cijena mora biti pozitivan cijeli broj manji od 300 i ne smije biti prazno polje!";
		}
		if (v.jeTekst(ime) == false || v.praznoPolje(ime))
		{
			i = 1;
			izuzetak += "Ime mora biti tekst i ne smije biti prazno polje!";
		}
		if (v.jeTekst(prezime) == false || v.praznoPolje(prezime))
		{
			p = 1;
			izuzetak += "Prezime mora biti tekst i ne smije biti prazno polje!";
		}
		if (c == 0 && i == 0 && p == 0)
		{	
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
		else
		{
			throw new IllegalArgumentException(izuzetak);
		}
	}

}
