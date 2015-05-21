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
		Autobus a = HibernateAutobus.nadjiAutobus(session, "abc-d-efg");
		Radnik r = HibernateRadnik.nadjiRadnika(session, "2412993178512");
		
		 dodajAutobuskuLiniju(session,"Saudin","Sarajevo",a,r,2015,4,17,2,2,25,100,20,10,20,40,true);
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
		String string="";
		Validacija v = new Validacija();
		if(v.praznoPolje(polazistelinije))
		{po=1;
			string+="Polaziste linije ne smije biti prazno";
		}
		if(v.praznoPolje(odredistelinije))
		{    od=1;
			string+=" Odrediste linije ne smije biti prazno";
		}

		
			if( peronlinije<=0) {
				peron=1;
				string+=" Peron mora biti pozitivan broj";
				
			}
		if( distancalinije<=0) {
			distanca=1;
			string+=" Distanca mora biti pozitivan broj";
			
		}
		if( trajanjelinije<=0) {
			trajanje=1;
			string+=" Trajanje mora biti pozitivan broj";
			
		}
		if( cijenajednosmjernakarta<=0) {
			jkarta=1;
			string+=" Cijena karte mora biti pozitivan broj";
			
		}
		
		if( cijenadvosmjernakarta<=0) {
			dkarta=1;
			string+=" Cjena karte biti pozitivan broj";
			
		}
		if( brojlinije<=0) {
			brlinije=1;
			string+=" Broj linije biti pozitivan broj";
			
		}
		
		
		if(po==0 && od==0 && peron==0 && distanca==0 && trajanje==0 && jkarta==0 && dkarta==0 && brlinije==0){
		
		
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
		String string="";
		Validacija v = new Validacija();
		if(v.praznoPolje(polazistelinije))
		{po=1;
			string+="Polaziste linije ne smije biti prazno";
		}
		if(v.praznoPolje(odredistelinije))
		{    od=1;
			string+=" Odrediste linije ne smije biti prazno";
		}

		
			if( peronlinije<=0) {
				peron=1;
				string+=" Peron mora biti pozitivan broj";
				
			}
		if( distancalinije<=0) {
			distanca=1;
			string+=" Distanca mora biti pozitivan broj";
			
		}
		if( trajanjelinije<=0) {
			trajanje=1;
			string+=" Trajanje mora biti pozitivan broj";
			
		}
		if( cijenajednosmjernakarta<=0) {
			jkarta=1;
			string+=" Cijena karte mora biti pozitivan broj";
			
		}
		
		if( cijenadvosmjernakarta<=0) {
			dkarta=1;
			string+=" Cjena karte biti pozitivan broj";
			
		}
		if( brojlinije<=0) {
			brlinije=1;
			string+=" Broj linije biti pozitivan broj";
			
		}
		
		if(po==0 && od==0 && peron==0 && distanca==0 && trajanje==0 && jkarta==0 && dkarta==0 && brlinije==0){
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
		
		/*String s="delete from AutobuskaLinija where brojLinije=:brojLinije";
		session.createQuery(s).setParameter("brojLinije", brojlinije).executeUpdate();*/
		Criteria k=session.createCriteria(AutobuskaLinija.class);
		k.add(Restrictions.eq("brojLinije", brojlinije));
		AutobuskaLinija izmjenjenalinija=(AutobuskaLinija) k.uniqueResult();
		session.delete(izmjenjenalinija);
		
		t.commit();
	}
	
	public static AutobuskaLinija nadjiAutobuskuLiniju(Session session, int brojlinije)
	{
		Transaction t = session.beginTransaction();
		Criteria k=session.createCriteria(AutobuskaLinija.class);
		k.add(Restrictions.eq("brojLinije", brojlinije));
		AutobuskaLinija izmjenjenalinija=(AutobuskaLinija) k.uniqueResult();
		t.commit();
		return izmjenjenalinija;
		
	}
	
	
	
	public static AutobuskaLinija NadjiAutobuskuLinijuOdrediste(Session session, String odrediste, int godina, int mjesec, int dan, int sati, int minute)
	{
		 //Transaction t = session.beginTransaction();
		
		 Criteria k=session.createCriteria(AutobuskaLinija.class);
		k.add(Restrictions.eq("odrediste",odrediste)).add(Restrictions.eq("datumPolaska_godina",godina)).add(Restrictions.eq("datumPolaska_mjesec", mjesec)).add(Restrictions.eq("datumPolaska_dan",dan)).add(Restrictions.eq("vrijemePolaska_sati",sati)).add(Restrictions.eq("vrijemePolaska_minute",minute));
		AutobuskaLinija kr= (AutobuskaLinija) k.uniqueResult();
		//t.commit();
		return kr;
		
		
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
	{
		HibernateRadnik radnik=new HibernateRadnik();
		Radnik r=radnik.nadjiRadnikaPoImenu(session, imevozaca);
		java.util.List linije;
		linije=session.createQuery("FROM AutobuskaLinija where vozac=:vozac1").setParameter("vozac1", r).list();
        System.out.println(linije.size());
		return linije;
		
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
}