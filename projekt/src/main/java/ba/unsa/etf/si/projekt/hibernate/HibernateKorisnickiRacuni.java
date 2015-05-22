package ba.unsa.etf.si.projekt.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.projekt.entiteti.Autobus;
import ba.unsa.etf.si.projekt.entiteti.AutobuskaLinija;
import ba.unsa.etf.si.projekt.entiteti.KorisnickiRacun;
import ba.unsa.etf.si.projekt.entiteti.Radnik;
import ba.unsa.etf.si.projekt.entiteti.TipKorisnickogRacuna;
import ba.unsa.etf.si.projekt.entiteti.TipRadnogMjesta;
import ba.unsa.etf.si.projekt.dodatno.Validacija;

public class HibernateKorisnickiRacuni {
 
	public static void main( String[] args)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		TipKorisnickogRacuna r=TipKorisnickogRacuna.menadzer;
		Radnik radnik = HibernateRadnik.nadjiRadnika(session, "2412993178512");
		//dodajKorisnickiRacun(session, "Ilvana","Brankovic","2412993178512","Ive","ilv123",r);
		//dodajKorisnickiRacun(session, radnik, r, "Lara", "pass1-aaaaa");
		//modifikujKorisnickiRacun(session, "Ilvana","Ive","Branković","2412993178512","Ive1","ilv123",r);
		modifikujKorisnickiRacun(session, "Lara", "Lara1", "pass1-aaadd", r);
		//brisiKorisnickiRacun(session,"2");
	/*	nadjiKorisnickiRacun(session,"Ive");
		java.util.List racuni;
		racuni=sviRacuni(session);
		for(int i=0;i<racuni.size();i++)
		{
			KorisnickiRacun au=(KorisnickiRacun)racuni.get(i);
			System.out.println(au.getKorisnickoIme());
		}*/
		session.close();
	}
	
	public static void dodajKorisnickiRacun(Session session, Radnik radnik, TipKorisnickogRacuna tipracuna, String korisnickoimekorisnika, String passkorisnika)
	{
		Transaction t = session.beginTransaction();
		Validacija v = new Validacija();
		int i = 0, p = 0;
		String izuzetak = "";
		KorisnickiRacun kr=new KorisnickiRacun();
		if (v.jeTekst(korisnickoimekorisnika) == false || v.praznoPolje(korisnickoimekorisnika))
		{
			i = 1;
			izuzetak += "Korisničko ime mora biti tekst i ne smije biti prazno polje!";
		}
		if (v.validirajPass(passkorisnika) == false)
		{
			p = 1;
			izuzetak += "Šifra mora sadržavati barem jedan broj i znak, imati najmanje 8 karaktera i ne smije biti prazno polje!";
		}
		if (i == 0 && p == 0)
		{
			kr.setRadnik(radnik);
			kr.setKorisnickoIme(korisnickoimekorisnika);
			kr.setTipKorisnickogRacuna(tipracuna);
			kr.setSifra(passkorisnika);
			
			Long id=(Long) session.save(kr);
			t.commit();
		}
		else
			throw new IllegalArgumentException(izuzetak);
	}
	
	public static void modifikujKorisnickiRacun(Session session, String starokorisnickoime, String korisnickoimekorisnika, String passkorisnika, TipKorisnickogRacuna tipracunakorisnika)
	{
		Transaction t = session.beginTransaction();
		
		Criteria k=session.createCriteria(KorisnickiRacun.class);
		k.add(Restrictions.eq("korisnickoIme",starokorisnickoime));
		KorisnickiRacun kr= (KorisnickiRacun) k.uniqueResult();
		Validacija v = new Validacija();
		int i = 0, p = 0;
		String izuzetak = "";
		if(kr!=null)
		{
			if (v.validirajPass(passkorisnika) == false)
			{
				p = 1;
				izuzetak += "Šifra mora sadržavati barem jedan broj i znak, imati najmanje 8 karaktera i ne smije biti prazno polje!";
			}
			if (v.jeTekst(korisnickoimekorisnika) == false || v.praznoPolje(korisnickoimekorisnika))
			{
				i = 1;
				izuzetak += "Korisničko ime mora biti tekst i ne smije biti prazno polje!";
			}
			if (p == 0 && i == 0)
			{
				kr.setTipKorisnickogRacuna(tipracunakorisnika);
				kr.setSifra(passkorisnika);
				kr.setKorisnickoIme(korisnickoimekorisnika);
				
				session.save(kr);
				t.commit();
			}
			else
				throw new IllegalArgumentException(izuzetak);
		}
	}
	
	public static void brisiKorisnickiRacun(Session session, String korisnickoimekorisnika)
	{
        Transaction t = session.beginTransaction();
        Validacija v = new Validacija();
		if (v.jeTekst(korisnickoimekorisnika) == false || v.praznoPolje(korisnickoimekorisnika))
		{
			throw new IllegalArgumentException("Korisničko ime mora biti tekst i ne smije biti prazno polje!");
		}
		else
		{
			Criteria k=session.createCriteria(KorisnickiRacun.class);
			k.add(Restrictions.eq("korisnickoIme",korisnickoimekorisnika));
			KorisnickiRacun kr= (KorisnickiRacun) k.uniqueResult();
			session.delete(kr);
			t.commit();
		}
	}
	 
	public static KorisnickiRacun nadjiKorisnickiRacun(Session session, String korisnickoimekorisnika)
	{
        Transaction t = session.beginTransaction();
		Validacija v = new Validacija();
		if (v.jeTekst(korisnickoimekorisnika) == false || v.praznoPolje(korisnickoimekorisnika))
		{
			throw new IllegalArgumentException("Korisničko ime mora biti tekst i ne smije biti prazno polje!");
		}
		else
		{
			Criteria k=session.createCriteria(KorisnickiRacun.class);
			k.add(Restrictions.eq("korisnickoIme",korisnickoimekorisnika));
			KorisnickiRacun kr= (KorisnickiRacun) k.uniqueResult();
			t.commit();
			
			return kr;
		}
	}
	
	public static java.util.List sviRacuni(Session session)
	{
		Transaction t = session.beginTransaction();
		java.util.List racuni;
		racuni=session.createQuery("FROM KorisnickiRacun").list();
		t.commit();
		return racuni;
		
	}
	public HibernateKorisnickiRacuni(){}
}
