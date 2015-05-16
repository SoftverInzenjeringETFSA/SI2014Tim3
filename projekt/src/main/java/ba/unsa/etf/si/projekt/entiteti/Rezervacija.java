package ba.unsa.etf.si.projekt.entiteti;

public class Rezervacija extends Karta implements java.io.Serializable{
	long id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Rezervacija() {}
}
