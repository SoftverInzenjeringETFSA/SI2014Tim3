package ba.unsa.etf.si.projekt.entiteti;

public class Rezervacija implements java.io.Serializable{
	public Rezervacija() {}
	long id;
	AutobuskaLinija linija;
	int vrijemePolaska_sati;
	int vrijemePolaska_minute;
	TipKarte tipKarte;
	int datumPolaska_dan;
	int datumPolaska_mjesec;
	int datumPolaska_godina;
	double cijena;
	String ime;
	String prezime;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getVrijemePolaska_sati() {
		return vrijemePolaska_sati;
	}
	public void setVrijemePolaska_sati(int vrijemePolaska_sati) {
		this.vrijemePolaska_sati = vrijemePolaska_sati;
	}
	public int getVrijemePolaska_minute() {
		return vrijemePolaska_minute;
	}
	public void setVrijemePolaska_minute(int vrijemePolaska_minute) {
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

	public AutobuskaLinija getLinija() {
		return linija;
	}
	public void setLinija(AutobuskaLinija linija) {
		this.linija = linija;
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
	
}
