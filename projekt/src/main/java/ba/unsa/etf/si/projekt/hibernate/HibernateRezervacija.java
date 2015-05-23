package ba.unsa.etf.si.projekt.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.projekt.entiteti.AutobuskaLinija;
import ba.unsa.etf.si.projekt.entiteti.KorisnickiRacun;
import ba.unsa.etf.si.projekt.entiteti.MedjunarodnaKarta;
import ba.unsa.etf.si.projekt.entiteti.Rezervacija;
import ba.unsa.etf.si.projekt.entiteti.TipKarte;
import ba.unsa.etf.si.projekt.dodatno.Validacija;

public class HibernateRezervacija {

	public static void main( String[] args) 
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		AutobuskaLinija a = HibernateAutibuskaLinija.nadjiAutobuskuLiniju(session, 1);
		AutobuskaLinija b = HibernateAutibuskaLinija.nadjiAutobuskuLiniju(session, 2);
		TipKarte t = TipKarte.jednosmjerna;
		TipKarte t1 = TipKarte.dvosmjerna;
		//dodajRezervaciju (session, a, 2015, 6, 20, 20, 30, t, 50, "Larisa", "Besic");
        //ModifikujRezervaciju (session, a, b, "NovoLarisa", "NovoBesic", "22", "11", 500, t1);
		brisanjeRezervacije (session, b, "NovoLarisa", "NovoBesic");
		session.close();
	}
	
	public static java.util.List sveRezervacije(Session session)
	{
		Transaction t = session.beginTransaction();
		java.util.List rezervacije;
		rezervacije=session.createQuery("FROM Rezervacija").list();
		t.commit();
		return rezervacije;
		
	}
	//pretraga po odredistu i ime i prezime
	public static void ModifikujRezervaciju(Session session, AutobuskaLinija staralinija,AutobuskaLinija novalinija,String staroime,String staroprezime,String novoime, String novoprezime, double cijena, TipKarte karta)
    {
		Criteria k=session.createCriteria(Rezervacija.class);
		k.add(Restrictions.eq("linija",staralinija)).add(Restrictions.eq("ime",staroime)).add(Restrictions.eq("prezime", staroprezime));
		Rezervacija r1= (Rezervacija) k.uniqueResult();
		Validacija v = new Validacija();
		int i = 0, p = 0, c = 0;
		String izuzetak = "";
	   Transaction t = session.beginTransaction();
		if(novalinija!=null)
		{
			System.out.println(novalinija.getAutobus().getKapacitet());
            System.out.println(novalinija.getZauzeto());
			if(novalinija.getAutobus().getKapacitet()>=novalinija.getZauzeto())
			{
		     novalinija.setZauzeto(novalinija.getZauzeto()+1);
		     staralinija.setZauzeto(staralinija.getZauzeto()-1);
			}
			else
			{
				throw new IllegalArgumentException("Nema više mjesta u autobusu.");
			}
			if (v.jeTekst(novoime) == false || v.praznoPolje(novoime))
			{
				i = 1;
				izuzetak += "Ime mora biti tekst i ne smije biti prazno polje!";
			}
			if (v.jeTekst(novoprezime) == false || v.praznoPolje(novoprezime))
			{
				p = 1;
				izuzetak += "Prezime mora biti tekst i ne smije biti prazno polje!";
			}
			if (v.validirajCijenuKarte(cijena) == false || v.praznoPolje(String.valueOf(cijena)))
			{
				c = 1;
				izuzetak += "Cijena karte mora biti pozitivan broj manji od 300 i ne smije biti prazno polje!";
			}
			if (i == 0 && p == 0 && c == 0)
			{
				r1.setLinija(novalinija);
			    r1.setCijena(cijena);
			    r1.setDatumPolaska_dan(novalinija.getDatumPolaska_dan());
			    r1.setDatumPolaska_godina(novalinija.getDatumPolaska_godina());
			    r1.setDatumPolaska_mjesec(novalinija.getDatumPolaska_mjesec());
			    r1.setTipKarte(karta);
			    r1.setVrijemePolaska_minute(novalinija.getVrijemePolaska_minute());
			    r1.setVrijemePolaska_sati(novalinija.getVrijemePolaska_sati());
				r1.setIme(novoime);
				r1.setPrezime(novoprezime);
			   
				session.save(r1);
			    t.commit();
			}
			else
				throw new IllegalArgumentException(izuzetak);
		}
		else
		{
			throw new NullPointerException("Ne postoji linija čije ste parametre unijeli.");
		}
    }
	
	public static void brisanjeRezervacije(Session session, AutobuskaLinija linija, String ime, String prezime)
	{
		Validacija v = new Validacija();
		int i = 0, p = 0;
		String izuzetak = "";
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
		if (i == 0 && p == 0)
		{
			Transaction t = session.beginTransaction();
			Criteria k=session.createCriteria(Rezervacija.class);
			k.add(Restrictions.eq("linija",linija)).add(Restrictions.eq("ime",ime)).add(Restrictions.eq("prezime", prezime));
			Rezervacija kr= (Rezervacija) k.uniqueResult();
			session.delete(kr);
			t.commit();
		}
		else
			throw new IllegalArgumentException(izuzetak);
	}
	
	/*public static Rezervacija nadjiRezervaciju(Session session, String odrediste, String ime, String Prezime)
	{
		Validacija v = new Validacija();
		int o = 0, i = 0, p = 0;
		String izuzetak = "";
		if (v.jeTekst(odrediste) == false || v.praznoPolje(odrediste))
		{
			o = 1;
			izuzetak += "Odredište mora biti tekst i ne smije biti prazno polje!";
		}
		if (v.jeTekst(ime) == false || v.praznoPolje(ime))
		{
			i = 1;
			izuzetak += "Ime mora biti tekst i ne smije biti prazno polje!";
		}
		if (v.jeTekst(Prezime) == false || v.praznoPolje(Prezime))
		{
			p = 1;
			izuzetak += "Prezime mora biti tekst i ne smije biti prazno polje!";
		}
		if (o == 0 && i == 0 && p == 0)
		{
			Criteria k=session.createCriteria(Rezervacija.class);
			k.add(Restrictions.eq("odrediste",odrediste)).add(Restrictions.eq("ime",ime)).add(Restrictions.eq("prezime", Prezime));
			Rezervacija kr= (Rezervacija) k.uniqueResult();
			return kr;
		}
		else
			throw new IllegalArgumentException(izuzetak);
	}*/
	public static void dodajRezervaciju(Session session,
			AutobuskaLinija linija, int godina, int mjesec, int dan, int sati,
			int minute, TipKarte tip, double cijena, String ime, String prezime)
	{
		Transaction t = session.beginTransaction();
		linija.setZauzeto(linija.getZauzeto()+1);
		Validacija v = new Validacija();
		int c = 0, i = 0, p = 0;
		Rezervacija k=new Rezervacija();
		String izuzetak = "";
		if (v.validirajCijenuKarte(cijena) == false)
		{
			c = 1;
			izuzetak += "Cijena karte mora biti pozitivan broj manji od 300!";
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
			throw new IllegalArgumentException(izuzetak);
	}

	
	
	
}