package ba.unsa.etf.si.projekt.entiteti;

public class KorisnickiRacun implements java.io.Serializable{
	long id;
	String korisnickoIme;
	String sifra;
	Radnik radnik;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	public String getSifra() {
		return sifra;
	}
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
	public TipKorisnickogRacuna getTipKorisnickogRacuna() {
		return tipKorisnickogRacuna;
	}
	public void setTipKorisnickogRacuna(TipKorisnickogRacuna tipKorisnickogRacuna) {
		this.tipKorisnickogRacuna = tipKorisnickogRacuna;
	}
	public Radnik getRadnik() {
		return radnik;
	}
	public void setRadnik(Radnik radnik) {
		this.radnik = radnik;
	}
	TipKorisnickogRacuna tipKorisnickogRacuna;
	
	public KorisnickiRacun() {}
	public TipKorisnickogRacuna dajTipRacuna() 
	{
		KorisnickiRacun racun = new KorisnickiRacun();
		return racun.tipKorisnickogRacuna;
	}
}
