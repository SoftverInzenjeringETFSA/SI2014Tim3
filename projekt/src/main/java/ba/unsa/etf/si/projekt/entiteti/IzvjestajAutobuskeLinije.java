package ba.unsa.etf.si.projekt.entiteti;

public class IzvjestajAutobuskeLinije  implements java.io.Serializable
{
	
	int pocetniDatumDan;
	int pocetniDatumGodina;
	int pocetniDatumMjesec;
	int krajnjiDatumDan;
	int krajnjiDatumGodina;
	int krajnjiDatumMjesec;
	int pocetnoVrijemeSati;
	int pocetnoVrijemeMinute;
	int pocetnoVrijemeSekunde;
	int krejnjeVrijemeSati;
	int krejnjeVrijemeMinute;
	int krejnjeVrijemeSekunde;
	
	public int getPocetnoVrijemeSati() {
		return pocetnoVrijemeSati;
	}

	public void setPocetnoVrijemeSati(int pocetnoVrijemeSati) {
		this.pocetnoVrijemeSati = pocetnoVrijemeSati;
	}

	public int getPocetnoVrijemeMinute() {
		return pocetnoVrijemeMinute;
	}

	public void setPocetnoVrijemeMinute(int pocetnoVrijemeMinute) {
		this.pocetnoVrijemeMinute = pocetnoVrijemeMinute;
	}

	public int getPocetnoVrijemeSekunde() {
		return pocetnoVrijemeSekunde;
	}

	public void setPocetnoVrijemeSekunde(int pocetnoVrijemeSekunde) {
		this.pocetnoVrijemeSekunde = pocetnoVrijemeSekunde;
	}

	public int getKrejnjeVrijemeSati() {
		return krejnjeVrijemeSati;
	}

	public void setKrejnjeVrijemeSati(int krejnjeVrijemeSati) {
		this.krejnjeVrijemeSati = krejnjeVrijemeSati;
	}

	public int getKrejnjeVrijemeMinute() {
		return krejnjeVrijemeMinute;
	}

	public void setKrejnjeVrijemeMinute(int krejnjeVrijemeMinute) {
		this.krejnjeVrijemeMinute = krejnjeVrijemeMinute;
	}

	public int getKrejnjeVrijemeSekunde() {
		return krejnjeVrijemeSekunde;
	}

	public void setKrejnjeVrijemeSekunde(int krejnjeVrijemeSekunde) {
		this.krejnjeVrijemeSekunde = krejnjeVrijemeSekunde;
	}


	
	
	public int getPocetniDatumDan() {
		return pocetniDatumDan;
	}

	public void setPocetniDatumDan(int pocetniDatumDan) {
		this.pocetniDatumDan = pocetniDatumDan;
	}

	public int getPocetniDatumGodina() {
		return pocetniDatumGodina;
	}

	public void setPocetniDatumGodina(int pocetniDatumGodina) {
		this.pocetniDatumGodina = pocetniDatumGodina;
	}

	public int getPocetniDatumMjesec() {
		return pocetniDatumMjesec;
	}

	public void setPocetniDatumMjesec(int pocetniDatumMjesec) {
		this.pocetniDatumMjesec = pocetniDatumMjesec;
	}

	public int getKrajnjiDatumDan() {
		return krajnjiDatumDan;
	}

	public void setKrajnjiDatumDan(int krajnjiDatumDan) {
		this.krajnjiDatumDan = krajnjiDatumDan;
	}

	public int getKrajnjiDatumGodina() {
		return krajnjiDatumGodina;
	}

	public void setKrajnjiDatumGodina(int krajnjiDatumGodina) {
		this.krajnjiDatumGodina = krajnjiDatumGodina;
	}

	public int getKrajnjiDatumMjesec() {
		return krajnjiDatumMjesec;
	}

	public void setKrajnjiDatumMjesec(int krajnjiDatumMjesec) {
		this.krajnjiDatumMjesec = krajnjiDatumMjesec;
	}
	

	long id;
     

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
	
}
	public IzvjestajAutobuskeLinije() {} 
	
}

