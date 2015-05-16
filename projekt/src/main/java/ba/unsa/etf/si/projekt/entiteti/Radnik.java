package ba.unsa.etf.si.projekt.entiteti;

public class Radnik implements java.io.Serializable {

	    long id;
		String ime;
		String prezime;
		String jmbg;
		TipRadnogMjesta tipRadnogMjesta;
		
		
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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



	
	public	
	TipRadnogMjesta dajTipRadnogMjesta()
	{
		return tipRadnogMjesta;}
	
	public Radnik(){}

}
