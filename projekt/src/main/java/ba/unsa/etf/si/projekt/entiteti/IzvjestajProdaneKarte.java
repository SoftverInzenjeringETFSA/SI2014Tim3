package ba.unsa.etf.si.projekt.entiteti;

public class IzvjestajProdaneKarte   implements java.io.Serializable
{
	
	int pocetniDatumDan;
	int pocetniDatumGodina;
	int pocetniDatumMjesec;
	int krajnjiDatumDan;
	int krajnjiDatumGodina;
	int krajnjiDatumMjesec;
	
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
	public IzvjestajProdaneKarte () {} 
	
}


