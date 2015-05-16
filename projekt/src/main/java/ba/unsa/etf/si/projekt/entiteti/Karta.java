package ba.unsa.etf.si.projekt.entiteti;

public class Karta implements java.io.Serializable{
	long id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getOdrediste() {
		return odrediste;
	}
	public void setOdrediste(String odrediste) {
		this.odrediste = odrediste;
	}
	public double getVrijemePolaska_sati() {
		return vrijemePolaska_sati;
	}
	public void setVrijemePolaska_sati(double vrijemePolaska_sati) {
		this.vrijemePolaska_sati = vrijemePolaska_sati;
	}
	public double getVrijemePolaska_minute() {
		return vrijemePolaska_minute;
	}
	public void setVrijemePolaska_minute(double vrijemePolaska_minute) {
		this.vrijemePolaska_minute = vrijemePolaska_minute;
	}
	public TipKarte getTipKarte() {
		return tipKarte;
	}
	public void setTipKarte(TipKarte tipKarte) {
		this.tipKarte = tipKarte;
	}
	public int getDatumPolaska_dan() {
		return datumPolaska_dan;
	}
	public void setDatumPolaska_dan(int datumPolaska_dan) {
		this.datumPolaska_dan = datumPolaska_dan;
	}
	public int getDatumPolaska_mjesec() {
		return datumPolaska_mjesec;
	}
	public void setDatumPolaska_mjesec(int datumPolaska_mjesec) {
		this.datumPolaska_mjesec = datumPolaska_mjesec;
	}
	public int getDatumPolaska_godina() {
		return datumPolaska_godina;
	}
	public void setDatumPolaska_godina(int datumPolaska_godina) {
		this.datumPolaska_godina = datumPolaska_godina;
	}
	public double getCijena() {
		return cijena;
	}
	public void setCijena(double cijena) {
		this.cijena = cijena;
	}
	String odrediste;
	double vrijemePolaska_sati;
	double vrijemePolaska_minute;
	TipKarte tipKarte;
	int datumPolaska_dan;
	int datumPolaska_mjesec;
	int datumPolaska_godina;
	double cijena;
	public Karta () {}

}
