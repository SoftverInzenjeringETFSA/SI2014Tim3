
package ba.unsa.etf.si.projekt.hibernate;

import org.hibernate.Transaction;
import org.hibernate.Session;

import ba.unsa.etf.si.projekt.entiteti.AutobuskaLinija;
import ba.unsa.etf.si.projekt.entiteti.TipKarte;
import ba.unsa.etf.si.projekt.entiteti.MedjunarodnaKarta;

public class HibernateMedjunarodnaKarta {
	
	public static void main( String[] args)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		TipKarte k=TipKarte.jednosmjerna;
		dodajMedjunarodnuKartu(session, "Mostar", 2,2,k,2015,5,2,21.0,"Ilvana","Brankovic");
		java.util.List karte;
		karte=sveMedjunarodneKarte(session);
		for(int i=0;i<karte.size();i++)
		{
			MedjunarodnaKarta au=(MedjunarodnaKarta)karte.get(i);
			System.out.println(au.getOdrediste());
		}
		session.close();
	}
	public static void dodajMedjunarodnuKartu(Session session, String odrediste, int vrijeme_sati, int vrijeme_minute, TipKarte tipkarte, int godina, int mjesec, int dan, double cijenakarte, String imekupca, String prezimekupca )
	{
        Transaction t = session.beginTransaction();
		
		MedjunarodnaKarta k=new MedjunarodnaKarta();
		k.setOdrediste(odrediste);
		k.setVrijemePolaska_sati(vrijeme_sati);
		k.setVrijemePolaska_minute(vrijeme_minute);
		k.setTipKarte(tipkarte);
		k.setDatumPolaska_godina(godina);
		k.setDatumPolaska_mjesec(mjesec);
		k.setDatumPolaska_dan(dan);
		k.setCijena(cijenakarte);
		k.setIme(imekupca);
		k.setPrezime(prezimekupca);
		
		Long id=(Long) session.save(k);
		t.commit();
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

}
