package ba.unsa.etf.si.projekt.entiteti;

public class MedjunarodnaKarta extends Karta implements java.io.Serializable{
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
	String ime;
	String prezime;
	public MedjunarodnaKarta() {} 
}
