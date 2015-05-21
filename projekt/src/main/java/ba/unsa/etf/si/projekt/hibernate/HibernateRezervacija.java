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

public class HibernateRezervacija {

	public static void main( String[] args)
	{
		
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
	public static void ModifikujRezervaciju(Session session, String odrediste, int vrijeme_sati, int vrijeme_minute, TipKarte tipkarte, int godina, int mjesec, int dan, double cijenakarte,String ime,String prezime)
    {
		Criteria k=session.createCriteria(Rezervacija.class);
		k.add(Restrictions.eq("odrediste",odrediste)).add(Restrictions.eq("ime",ime)).add(Restrictions.eq("prezime", prezime));
		Rezervacija r1= (Rezervacija) k.uniqueResult();
		
	   Transaction t = session.beginTransaction();
	   HibernateAutibuskaLinija linija=new HibernateAutibuskaLinija();
		AutobuskaLinija trazenaLinija=new AutobuskaLinija();
		trazenaLinija=linija.NadjiAutobuskuLinijuOdrediste(session, odrediste, godina, mjesec, dan, vrijeme_sati, vrijeme_minute);
		if(trazenaLinija!=null)
		{
			if(trazenaLinija.getAutobus().getKapacitet()<=trazenaLinija.getZauzeto())
			{
		trazenaLinija.setZauzeto(trazenaLinija.getZauzeto()+1);
			}
			else
			{
				throw new IllegalArgumentException("Nema više mjesta u busu.");
			}
		r1.setLinija(trazenaLinija);
		r1.setVrijemePolaska_sati(vrijeme_sati);
		r1.setVrijemePolaska_minute(vrijeme_minute);
		r1.setTipKarte(tipkarte);
		r1.setDatumPolaska_godina(godina);
		r1.setDatumPolaska_mjesec(mjesec);
		r1.setDatumPolaska_dan(dan);
		r1.setCijena(cijenakarte);
		r1.setIme(ime);
		r1.setPrezime(prezime);
	   
		session.save(r1);
	   t.commit();
		}
		else
		{
			throw new NullPointerException("Ne postoji linija, čije ste parametre unijeli.");
		}
    }
	
	public static void brisanjeRezervacije(Session session, AutobuskaLinija linija, String ime, String prezime)
	{
		Transaction t = session.beginTransaction();
		Criteria k=session.createCriteria(Rezervacija.class);
		k.add(Restrictions.eq("linija",linija)).add(Restrictions.eq("ime",ime)).add(Restrictions.eq("prezime", prezime));
		Rezervacija kr= (Rezervacija) k.uniqueResult();
		session.delete(kr);
		t.commit();
	}
	
	public static Rezervacija nadjiRezervaciju(Session session, String odrediste, String ime, String Prezime)
	{
		Criteria k=session.createCriteria(Rezervacija.class);
		k.add(Restrictions.eq("odrediste",odrediste)).add(Restrictions.eq("ime",ime)).add(Restrictions.eq("prezime", Prezime));
		Rezervacija kr= (Rezervacija) k.uniqueResult();
		return kr;
	}
	public static void dodajRezervaciju(Session session,
			AutobuskaLinija linija, int godina, int mjesec, int dan, int sati,
			int minute, TipKarte tip, double cijena, String ime, String prezime) {
		Transaction t = session.beginTransaction();
		linija.setZauzeto(linija.getZauzeto()+1);
		Rezervacija k=new Rezervacija();
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