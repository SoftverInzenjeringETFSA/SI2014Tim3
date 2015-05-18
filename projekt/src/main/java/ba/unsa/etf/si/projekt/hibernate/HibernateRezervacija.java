package ba.unsa.etf.si.projekt.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.projekt.entiteti.Rezervacija;
import ba.unsa.etf.si.projekt.entiteti.TipKarte;

public class HibernateRezervacija {

	public static void main( String[] args)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		TipKarte k=TipKarte.jednosmjerna;
		dodajRezervaciju(session,"Gradacac",17,15,k,2015,5,12,23.0);
		
	}
	public static void dodajRezervaciju(Session session, String odrediste, int vrijeme_sati, int vrijeme_minute, TipKarte tipkarte, int godina, int mjesec, int dan, double cijenakarte )
	{
		Transaction t = session.beginTransaction();
		
		Rezervacija k=new Rezervacija();
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
}
