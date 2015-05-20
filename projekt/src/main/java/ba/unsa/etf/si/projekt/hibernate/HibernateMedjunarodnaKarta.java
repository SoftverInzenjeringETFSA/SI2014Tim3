
package ba.unsa.etf.si.projekt.hibernate;

import org.hibernate.Transaction;
import org.hibernate.Session;

import ba.unsa.etf.si.projekt.entiteti.AutobuskaLinija;
import ba.unsa.etf.si.projekt.entiteti.TipKarte;
import ba.unsa.etf.si.projekt.entiteti.MedjunarodnaKarta;

public class HibernateMedjunarodnaKarta {
	
	public static void main( String[] args)
	{
		
	}
	public static void dodajMedjunarodnuKartu(Session session, AutobuskaLinija linija, int vrijeme_sati, int vrijeme_minute, TipKarte tipkarte, int godina, int mjesec, int dan, double cijenakarte, String imekupca, String prezimekupca )
	{
        Transaction t = session.beginTransaction();
		//linija.ModifikujZauzetostLinije(session, );
		MedjunarodnaKarta k=new MedjunarodnaKarta();
		k.setLinija(linija);
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
