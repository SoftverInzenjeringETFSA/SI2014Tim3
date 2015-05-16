package ba.unsa.etf.si.projekt.entiteti;

public class IzvjestajVozaci  implements java.io.Serializable
{String ime;
String prezime;
	
 long id;
 
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
	public IzvjestajVozaci() {} 
	
}







