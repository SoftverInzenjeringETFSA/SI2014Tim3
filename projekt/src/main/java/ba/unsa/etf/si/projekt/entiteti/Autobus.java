package ba.unsa.etf.si.projekt.entiteti;

public class Autobus implements java.io.Serializable{
	long id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getKapacitet() {
		return kapacitet;
	}
	public void setKapacitet(int kapacitet) {
		this.kapacitet = kapacitet;
	}
	public String getRegistracija() { 
		return registracija;
	}
	public void setRegistracija(String registracija) {
		this.registracija = registracija;
	}
	String model;
	int kapacitet;
	String registracija;
	public Autobus() {} 
}
