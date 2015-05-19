package ba.unsa.etf.si.projekt.dodatno;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;

import ba.unsa.etf.si.projekt.entiteti.Karta;

public class GenerisanjePDF {
	public static void main(String[] args) {
		List<Karta> k = new ArrayList<Karta>();
		k.add(new Karta());
	}
	
	
	public static void prodaneKartePDF(List<Karta> karte, Date pocetak, Date kraj)
	{
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		
	}
}
