package ba.unsa.etf.si.projekt.entiteti;

public class AutobuskaLinija implements java.io.Serializable{
	long id;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPolaziste() {
		return polaziste;
	}

	public void setPolaziste(String polaziste) {
		this.polaziste = polaziste;
	}

	public String getOdrediste() {
		return odrediste;
	}

	public void setOdrediste(String odrediste) {
		this.odrediste = odrediste;
	}

	public Autobus getAutobus() {
		return autobus;
	}

	public void setAutobus(Autobus autobus) {
		this.autobus = autobus;
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

	public int getPeron() {
		return peron;
	}

	public void setPeron(int peron) {
		this.peron = peron;
	}

	public double getDistanca() {
		return distanca;
	}

	public void setDistanca(double distanca) {
		this.distanca = distanca;
	}

	public double getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(double trajanje) {
		this.trajanje = trajanje;
	}

	public int getBrojLinije() {
		return brojLinije;
	}

	public void setBrojLinije(int brojLinije) {
		this.brojLinije = brojLinije;
	}

	public double getCijenaJednosmjerna() {
		return cijenaJednosmjerna;
	}

	public void setCijenaJednosmjerna(double cijenaJednosmjerna) {
		this.cijenaJednosmjerna = cijenaJednosmjerna;
	}

	public double getCijenaDvosmjerna() {
		return cijenaDvosmjerna;
	}

	public void setCijenaDvosmjerna(double cijenaDvosmjerna) {
		this.cijenaDvosmjerna = cijenaDvosmjerna;
	}

	public boolean isMedjunarodna() {
		return medjunarodna;
	}

	public void setMedjunarodna(boolean medjunarodna) {
		this.medjunarodna = medjunarodna;
	}
	
	
	public Radnik getVozac() {
		return vozac;
	}

	public void setVozac(Radnik vozac) {
		this.vozac = vozac;
	}

	public int getZauzeto() {
		return zauzeto;
	}

	public void setZauzeto(int zauzeto) {
		this.zauzeto = zauzeto;
	}
	
	String polaziste;
	String odrediste;
	Autobus autobus;
	Radnik vozac;  
	int datumPolaska_dan;
	int datumPolaska_mjesec;
	int datumPolaska_godina;
	double vrijemePolaska_sati;
	double vrijemePolaska_minute;
	int peron;
	double distanca;
	double trajanje;
	int brojLinije;
	double cijenaJednosmjerna;
	double cijenaDvosmjerna;
	boolean medjunarodna;
	int zauzeto;
	
	

	public AutobuskaLinija () {} 
}
