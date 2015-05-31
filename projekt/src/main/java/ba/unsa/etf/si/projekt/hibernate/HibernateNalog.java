package ba.unsa.etf.si.projekt.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.projekt.entiteti.AutobuskaLinija;
import ba.unsa.etf.si.projekt.entiteti.Nalog;
import ba.unsa.etf.si.projekt.entiteti.Autobus;
import ba.unsa.etf.si.projekt.entiteti.Radnik;

public class HibernateNalog {
	
	public static void main( String[] args)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		AutobuskaLinija l=new AutobuskaLinija();
		l.setId(3);
		l.setBrojLinije(24);
		l.setCijenaDvosmjerna(30);
		l.setCijenaJednosmjerna(23);
		l.setDatumPolaska_dan(21);
		l.setDatumPolaska_godina(2015);
		l.setDatumPolaska_mjesec(2);
		l.setDistanca(100.0);
		Autobus a1=new Autobus();
		a1.setId(13);
		Radnik v1=new Radnik();
		v1.setId(14);
		l.setAutobus(a1);
		l.setVozac(v1);
		dodajNalog(session, l,23,2,2015,2,2);
		session.close();
	}
	
	public static void dodajNalog(Session session, AutobuskaLinija linija, int dan, int mjesec, int godina, int sati, int minute)
	{   int s1=0,s2=0,s3=0;
	String string="";
				
			 if( sati>23) {
					s1=1;
					string+=" Sati ne mogu biti veći od 23h!";
					
				}
				if( minute>59) {
					s2=1;
					string+=" Minute ne mogu biti veće od 59 min!";
					
				}
				
		if(s1==0 && s2==0){
		Transaction t = session.beginTransaction();
		
		Nalog n=new Nalog();
		n.setAutobuskaLinija(linija);
		n.setDatumPolaska_dan(dan);
		n.setDatumPolaska_godina(godina);
		n.setDatumPolaska_mjesec(mjesec);
		n.setVrijemePolaska_minute(minute);
		n.setVrijemePolaska_sati(sati);
		
		Long id=(Long) session.save(n);
		t.commit();
		}
		else { throw new IllegalArgumentException(string);}
	}
	
	public static void brisiNalog(Session session, AutobuskaLinija linija, int dan, int mjesec, int godina, int sati, int minute)
	{
		Transaction t = session.beginTransaction();
		Criteria k=session.createCriteria(Nalog.class);
		k.add(Restrictions.eq("autobuskaLinija", linija)).add(Restrictions.eq("datumPolaska_dan",dan)).add(Restrictions.eq("datumPolaska_mjesec", mjesec)).add(Restrictions.eq("datumPolaska_godina", godina)).add(Restrictions.eq("vrijemePolaska_sati", sati)).add(Restrictions.eq("vrijemePolaska_minute", minute));
		Nalog r=(Nalog) k.uniqueResult();
		session.delete(r);
		t.commit();
	}
	public static java.util.List sviNalozi(Session session)
	{
		Transaction t = session.beginTransaction();
		java.util.List nalozi;
		nalozi=session.createQuery("FROM Nalog").list();
		t.commit();
		return nalozi;
		
	}
	public HibernateNalog(){}

}
