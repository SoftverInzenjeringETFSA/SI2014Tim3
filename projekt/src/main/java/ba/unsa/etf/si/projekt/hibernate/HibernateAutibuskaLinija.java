package ba.unsa.etf.si.projekt.hibernate;


import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.projekt.entiteti.Radnik;
import ba.unsa.etf.si.projekt.entiteti.AutobuskaLinija;
import ba.unsa.etf.si.projekt.entiteti.Autobus;
import ba.unsa.etf.si.projekt.entiteti.Rezervacija;
import ba.unsa.etf.si.projekt.dodatno.Validacija; 

public class HibernateAutibuskaLinija {

	public static void main( String[] args)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Autobus a = HibernateAutobus.nadjiAutobus(session, "A23-M-424");  
		Radnik r = HibernateRadnik.nadjiRadnika(session, "1223493827163");
		
		//dodajAutobuskuLiniju(session,"Larisa","Zenica",a,r,2015,4,17,2,2,5,100,100,1,20,40,true);
		//dodajAutobuskuLiniju(session,"Larisa","Sarajevo",a,r,2015,4,17,2,2,5,100,100,2,20,40,true);
		// dodajAutobuskuLiniju(session,"saudin","Sarajevo",a,r,2015,4,17,2,2,5,100,100,1,20,40,true);
		//modifikujAutobuskuLiniju(session,"hghfgfhg","Sarajevo",a,r,2012,2,2,2,2,2,120,22,1,30,60);
		//brisiAutobuskuLiniju(session,1);
		
		
		/*AutobuskaLinija a1=NadjiAutobuskuLinijuOdrediste(session,"Mostar",2015,4,17,2,2);
		if(a1!=null)
		System.out.println(a1.getBrojLinije());
		java.util.List linije;
		java.util.List linije1=IzvjestajOAutobuskimLinijama(session,2014,3,2,2015,5,18,1,1,13,13);
		for(int i=0;i<linije1.size();i++)
		{
			AutobuskaLinija au=(AutobuskaLinija)linije1.get(i);
			System.out.println(au.getBrojLinije());
		}*/
		
		session.close();
	}
	
	public static void dodajAutobuskuLiniju(Session session, String polazistelinije, String odredistelinije, Autobus a,Radnik r, int godina, int mjesec, int dan, int sati,int minute,int peronlinije, double distancalinije, double trajanjelinije, int brojlinije, double cijenajednosmjernakarta, double cijenadvosmjernakarta,boolean medjunarodna1 )
	{
		
		Transaction t = session.beginTransaction();
		int po=0;
		int od=0;
		int trajanje=0;
		int distanca=0;
		int peron=0;
		int jkarta=0;
		int dkarta=0;
		int brlinije=0;
		int s1=0;
		int s2=0;
		String string="";
		Validacija v = new Validacija();
		String distanca1=String.valueOf(distancalinije);
		if(v.praznoPolje(polazistelinije) || v.jeTekst(polazistelinije) == false)
		{po=1;
			string+="Polazište linije mora biti tekst i ne smije biti prazno polje!";
		}
		if(v.praznoPolje(odredistelinije) || v.jeTekst(odredistelinije) == false)
		{    od=1;
			string+=" Odredište linije mora biti tekst i ne smije biti prazno polje!";
		}

		
			if((peronlinije<=0 || peronlinije>=6) || String.valueOf(peronlinije).length()==0) {
				peron=1;
				string+="Broj perona mora biti pozitivan broj i < od 6!";
				
			}
		if(  distancalinije<=0 || distanca1.length()==0) {
			distanca=1;
			string+=" Distanca mora biti pozitivan broj!";
			
		}
		if( trajanjelinije<=0 || String.valueOf(trajanjelinije).length()==0) {
			trajanje=1;
			string+=" Trajanje mora biti pozitivan broj!";
			
		}
		if( !v.validirajCijenuKarte(cijenajednosmjernakarta) || String.valueOf(cijenajednosmjernakarta).length()==0) {
			jkarta=1;
			string+=" Cijena jednosmjerne karte mora biti pozitivan broj i manja od 300!";
			
		}
		
		if(!v.validirajCijenuKarte(cijenadvosmjernakarta) || String.valueOf(cijenadvosmjernakarta).length()==0) {
			dkarta=1;
			string+=" Cjena dvosmjerne karte mora biti pozitivan broj!";
			
		}
		
		if( brojlinije<=0 ||  String.valueOf(brojlinije).length()==0) {
			brlinije=1;
			string+=" Broj linije mora biti pozitivan broj!";
			
		}
		
		if( sati>24) {
			s1=1;
			string+=" Sati ne mogu biti veći od 24h!";
			
		}
		if( minute>60) {
			s2=1;
			string+=" Minute ne mogu biti veće od 60 min!";
			
		}
		
		
		if(po==0 && od==0 && peron==0 && distanca==0 && trajanje==0 && jkarta==0 && dkarta==0 && brlinije==0 && s1==0 && s2==0){
		
		
		AutobuskaLinija linija=new AutobuskaLinija();
		linija.setPolaziste(polazistelinije);
		linija.setOdrediste(odredistelinije);
		linija.setAutobus(a);
		linija.setVozac(r);
		linija.setDatumPolaska_godina(godina);
		linija.setDatumPolaska_mjesec(mjesec);
		linija.setDatumPolaska_dan(dan);
		linija.setVrijemePolaska_sati(sati);
		linija.setVrijemePolaska_minute(minute);
		linija.setPeron(peronlinije);
		linija.setDistanca(distancalinije);
		linija.setTrajanje(trajanjelinije);
		linija.setCijenaJednosmjerna(cijenajednosmjernakarta);
		linija.setCijenaDvosmjerna(cijenadvosmjernakarta);
		linija.setBrojLinije(brojlinije);
		linija.setMedjunarodna(medjunarodna1);
		int broj=linija.getZauzeto();
		linija.setZauzeto(0);
		
		Long id=(Long) session.save(linija);
		t.commit();}
		
		else { throw new IllegalArgumentException(string);}
		
	}
	
	public static void modifikujAutobuskuLiniju(Session session, String polazistelinije, String odredistelinije, Autobus a,Radnik r, int godina, int mjesec, int dan, int sati,int minute,int peronlinije, double distancalinije, double trajanjelinije, int brojlinije, double cijenajednosmjernakarta, double cijenadvosmjernakarta)
	{
		Transaction t = session.beginTransaction();
		int po=0;
		int od=0;
		int trajanje=0;
		int distanca=0;
		int peron=0;
		int jkarta=0;
		int dkarta=0;
		int brlinije=0;
		int s1=0;
		int s2=0;
		
		String string="";
		Validacija v = new Validacija();
		if(v.praznoPolje(polazistelinije))
		{po=1;
			string+="Polazište linije mora biti tekst i ne smije biti prazno polje!";
		}
		if(v.praznoPolje(odredistelinije))
		{    od=1;
			string+=" Odredište linije mora biti tekst i ne smije biti prazno polje!";
		}

		
			if( !v.validirajBrojPerona(String.valueOf(peronlinije)) ) {
				peron=1;
				string+=" Peron mora biti pozitivan broj i <= od 6!";
				
			}
		if(  distancalinije<=0) {
			distanca=1;
			string+=" Distanca mora biti pozitivan broj!";
			
		}
		if( trajanjelinije<=0) {
			trajanje=1;
			string+=" Trajanje mora biti pozitivan broj!";
			
		}
		if( !v.validirajCijenuKarte(cijenajednosmjernakarta) ) {
			jkarta=1;
			string+=" Cijena jednosmjerne karte mora biti pozitivan broj manji od 300!";
			
		}
		
		if(!v.validirajCijenuKarte(cijenadvosmjernakarta) ) {
			dkarta=1;
			string+=" Cjena dvosmjerne karte biti pozitivan broj manji od 300!";
			
		}
		if( brojlinije<=0) {
			brlinije=1;
			string+=" Broj linije mora biti pozitivan broj!";
			
		}
		if( sati>24) {
			s1=1;
			string+=" Sati ne mogu biti veći od 24h!";
			
		}
		if( minute>60) {
			s2=1;
			string+=" Minute ne mogu biti veće od 60 min!";
			
		}
		
		
		if(po==0 && od==0 && peron==0 && distanca==0 && trajanje==0 && jkarta==0 && dkarta==0 && brlinije==0 && s1==0 && s2==0){
		Criteria k=session.createCriteria(AutobuskaLinija.class);
		k.add(Restrictions.eq("brojLinije", brojlinije));
		AutobuskaLinija izmjenjenalinija=(AutobuskaLinija) k.uniqueResult();
		
		if(izmjenjenalinija!=null)
		{
		izmjenjenalinija.setPolaziste(polazistelinije);
		izmjenjenalinija.setOdrediste(odredistelinije);
		izmjenjenalinija.setAutobus(a);
		izmjenjenalinija.setVozac(r);
		izmjenjenalinija.setDatumPolaska_godina(godina);
		izmjenjenalinija.setDatumPolaska_mjesec(mjesec);
		izmjenjenalinija.setVrijemePolaska_sati(sati);
		izmjenjenalinija.setVrijemePolaska_minute(minute);
		izmjenjenalinija.setPeron(peronlinije);
		izmjenjenalinija.setDistanca(distancalinije);
		izmjenjenalinija.setTrajanje(trajanjelinije);
		izmjenjenalinija.setCijenaJednosmjerna(cijenajednosmjernakarta);
		izmjenjenalinija.setCijenaDvosmjerna(cijenadvosmjernakarta);
		//izmjenjenalinija.setZauzeto(zauzeto);
		
		session.save(izmjenjenalinija);
		t.commit();
		}
		
	}
		else { throw new IllegalArgumentException(string);}
	}
	
	public static void brisiAutobuskuLiniju(Session session, int brojlinije)
	{
		Transaction t = session.beginTransaction();
		int brlinije=0;
		String string="";
		if( brojlinije<=0) {
			brlinije=1;
			string+=" Broj linije mora biti pozitivan broj!";
			
		}
		if(brlinije==0){
			
		/*String s="delete from AutobuskaLinija where brojLinije=:brojLinije";
		session.createQuery(s).setParameter("brojLinije", brojlinije).executeUpdate();*/
		Criteria k=session.createCriteria(AutobuskaLinija.class);
		k.add(Restrictions.eq("brojLinije", brojlinije));
		AutobuskaLinija izmjenjenalinija=(AutobuskaLinija) k.uniqueResult();
		session.delete(izmjenjenalinija);
		
		t.commit();}
		else { throw new IllegalArgumentException(string);}
	}
	
	public static AutobuskaLinija nadjiAutobuskuLiniju(Session session, int brojlinije)
	{
		Transaction t = session.beginTransaction();
		int brlinije=0;
		String string="";
		if( brojlinije<=0) {
			brlinije=1;
			string+=" Broj linije mora biti pozitivan broj!";
			
		}
		if(brlinije==0){
		Criteria k=session.createCriteria(AutobuskaLinija.class);
		k.add(Restrictions.eq("brojLinije", brojlinije));
		AutobuskaLinija izmjenjenalinija=(AutobuskaLinija) k.uniqueResult();
		t.commit();
		return izmjenjenalinija;}
		
		else { throw new IllegalArgumentException(string);}
	}
	
	
	
	public static AutobuskaLinija NadjiAutobuskuLinijuOdrediste(Session session, String odrediste, int godina, int mjesec, int dan, int sati, int minute)
	{
		 //Transaction t = session.beginTransaction();
		int od=0;
		String string="";
		Validacija v = new Validacija();
		if(v.praznoPolje(odrediste))
		{od=1;
			string+="Odredište linije ne smije biti prazno!";
		}
		if(od==0){
		 Criteria k=session.createCriteria(AutobuskaLinija.class);
		k.add(Restrictions.eq("odrediste",odrediste)).add(Restrictions.eq("datumPolaska_godina",godina)).add(Restrictions.eq("datumPolaska_mjesec", mjesec)).add(Restrictions.eq("datumPolaska_dan",dan)).add(Restrictions.eq("vrijemePolaska_sati",sati)).add(Restrictions.eq("vrijemePolaska_minute",minute));
		AutobuskaLinija kr= (AutobuskaLinija) k.uniqueResult();
		//t.commit();
		return kr;}
		
		
		else { throw new IllegalArgumentException(string);}
	}
	public static java.util.List sveLinije(Session session)
	{
		Transaction t = session.beginTransaction();
		java.util.List linije;
		linije=session.createQuery("FROM AutobuskaLinija").list();
		t.commit();
		return linije;
		
	}
	
	public static java.util.List IzvjestajORadnicima(Session session,String imevozaca, String prezimevozaca)
	{ int ime=0;
	int prezime=0;
	String string="";
	Validacija v = new Validacija();
	if(v.praznoPolje(imevozaca))
	{ime=1;
		string+="Ime ne smije biti prazno";
	}
	if(v.praznoPolje(prezimevozaca))
	{prezime=1;
		string+="Prezime ne smije biti prazno";
	}
	
	if(ime==0 && prezime==0){
		HibernateRadnik radnik=new HibernateRadnik();
		Radnik r=radnik.nadjiRadnikaPoImenu(session, imevozaca);
		java.util.List linije;
		linije=session.createQuery("FROM AutobuskaLinija where vozac=:vozac1").setParameter("vozac1", r).list();
        System.out.println(linije.size());
		return linije;}
	else { throw new IllegalArgumentException(string);}
	
		
	}
	
	public static java.util.List IzvjestajOAutobuskimLinijama(Session session, int pocetnagod, int pocetnimjes, int pocetnidan, int krajnjagod, int krajnjimjesec, int krajnjidan, int pocetnisati, int pocetneminute, int krajnjisati, int krajnjeminute)
	{
		HibernateRadnik radnik=new HibernateRadnik();
		java.util.List linije;
		linije=session.createQuery("FROM AutobuskaLinija where datumPolaska_dan>="+pocetnidan+" and datumPolaska_dan<="+krajnjidan+" and datumPolaska_mjesec>="+pocetnimjes+" and datumPolaska_mjesec<="+krajnjimjesec+" and datumPolaska_godina>="+pocetnagod+" and datumPolaska_godina<="+krajnjagod+"and vrijemePolaska_sati>="+pocetnisati+" and vrijemePolaska_sati<="+krajnjisati+" and vrijemePolaska_minute>="+pocetneminute+" and vrijemePolaska_minute<="+krajnjeminute+"").list();
        System.out.println(linije.size());
		return linije;
	}
		
	public HibernateAutibuskaLinija()
	{}
	public static void updateLinija(Session session,
			AutobuskaLinija linija) {
		Transaction t = session.beginTransaction();
		
		session.update(linija);
		
		t.commit();
		
	}
}