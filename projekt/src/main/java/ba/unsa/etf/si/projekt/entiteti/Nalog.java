package ba.unsa.etf.si.projekt.entiteti;

public class Nalog implements java.io.Serializable{
	long id;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public AutobuskaLinija getAutobuskaLinija() {
		return autobuskaLinija;
	}

	public void setAutobuskaLinija(AutobuskaLinija autobuskaLinija) {
		this.autobuskaLinija = autobuskaLinija;
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

	AutobuskaLinija autobuskaLinija;
	int datumPolaska_dan;
	int datumPolaska_mjesec;
	int datumPolaska_godina;
	int vrijemePolaska_sati;
	int vrijemePolaska_minute;
	
	public Nalog() {}
}
