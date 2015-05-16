package ba.unsa.etf.si.projekt.entiteti;

public class KorisnickiRacun implements java.io.Serializable{
	long id;
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
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getJmbg() {
		return jmbg;
	}
	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}
	public TipKorisnickogRacuna getTipKorisnickogRacuna() {
		return tipKorisnickogRacuna;
	}
	public void setTipKorisnickogRacuna(TipKorisnickogRacuna tipKorisnickogRacuna) {
		this.tipKorisnickogRacuna = tipKorisnickogRacuna;
	}
	String korisnickoIme;
	String sifra;
	String ime;
	String prezime;
	String jmbg;
	TipKorisnickogRacuna tipKorisnickogRacuna;
	
	public KorisnickiRacun() {}
	public TipKorisnickogRacuna dajTipRacuna() 
	{
		KorisnickiRacun racun = new KorisnickiRacun();
		return racun.tipKorisnickogRacuna;
	}
}
