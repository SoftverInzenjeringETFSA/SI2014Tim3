package ba.unsa.etf.si.projekt.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.projekt.entiteti.AutobuskaLinija;
import ba.unsa.etf.si.projekt.entiteti.KorisnickiRacun;
import ba.unsa.etf.si.projekt.entiteti.Rezervacija;
import ba.unsa.etf.si.projekt.entiteti.TipKarte;

public class HibernateRezervacija {

	public static void main( String[] args)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		TipKarte k=TipKarte.jednosmjerna;
		dodajRezervaciju(session,"Mostar",2,2,k,2015,4,17,23.0,"Elma","Brankovic");
		//Rezervacija r=nadjiRezervaciju(session,"Gradacac","Ilvana","Brankovic");
		
		
	}
	public static void dodajRezervaciju(Session session, String odrediste, int vrijeme_sati, int vrijeme_minute, TipKarte tipkarte, int godina, int mjesec, int dan, double cijenakarte,String ime,String prezime)
	{
		Transaction t = session.beginTransaction();
		AutobuskaLinija trazenaLinija=new AutobuskaLinija();
		HibernateAutibuskaLinija linija=new HibernateAutibuskaLinija();
		trazenaLinija=linija.NadjiAutobuskuLinijuOdrediste(session, odrediste, godina, mjesec, dan, vrijeme_sati, vrijeme_minute);
		trazenaLinija.setZauzeto(trazenaLinija.getZauzeto()+1);
		Rezervacija k=new Rezervacija();
		k.setLinija(trazenaLinija);
		k.setVrijemePolaska_sati(vrijeme_sati);
		k.setVrijemePolaska_minute(vrijeme_minute);
		k.setTipKarte(tipkarte);
		k.setDatumPolaska_godina(godina);
		k.setDatumPolaska_mjesec(mjesec);
		k.setDatumPolaska_dan(dan);
		k.setCijena(cijenakarte);
		k.setIme(ime);
		k.setPrezime(prezime);
		
		Long id=(Long) session.save(k);
		t.commit();
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
	public static void ModifikujRezervaciju(Session session, String odrediste, int vrijeme_sati, int vrijeme_minute, TipKarte tipkarte, int godina, int mjesec, int dan, double cijenakarte,String ime,String prezime,Rezervacija r)
    {
		Criteria k=session.createCriteria(Rezervacija.class);
		k.add(Restrictions.eq("odrediste",odrediste)).add(Restrictions.eq("ime",ime)).add(Restrictions.eq("prezime", prezime));
		Rezervacija r1= (Rezervacija) k.uniqueResult();
		
	   Transaction t = session.beginTransaction();
	   HibernateAutibuskaLinija linija=new HibernateAutibuskaLinija();
		AutobuskaLinija trazenaLinija=new AutobuskaLinija();
		trazenaLinija=linija.NadjiAutobuskuLinijuOdrediste(session, odrediste, godina, mjesec, dan, vrijeme_sati, vrijeme_minute);
		trazenaLinija.setZauzeto(trazenaLinija.getZauzeto()+1);
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
	
	public static void brisanjeRezervacije(Session session, String odrediste, String ime, String prezime)
	{
		Transaction t = session.beginTransaction();
		 
		Criteria k=session.createCriteria(Rezervacija.class);
		k.add(Restrictions.eq("odrediste",odrediste)).add(Restrictions.eq("ime",ime)).add(Restrictions.eq("prezime", prezime));
		Rezervacija kr= (Rezervacija) k.uniqueResult();
		
		session.delete(k);
		t.commit();
	}
	
	public static Rezervacija nadjiRezervaciju(Session session, String odrediste, String ime, String Prezime)
	{
		Criteria k=session.createCriteria(Rezervacija.class);
		k.add(Restrictions.eq("odrediste",odrediste)).add(Restrictions.eq("ime",ime)).add(Restrictions.eq("prezime", Prezime));
		Rezervacija kr= (Rezervacija) k.uniqueResult();
		return kr;
	}
	
	
}