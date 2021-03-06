package ba.unsa.etf.si.projekt.dodatno;
import org.apache.log4j.Logger;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import ba.unsa.etf.si.projekt.entiteti.AutobuskaLinija;
import ba.unsa.etf.si.projekt.entiteti.Karta;
import ba.unsa.etf.si.projekt.entiteti.Radnik;

public class GenerisanjePDF {

	final static Logger logger = Logger.getLogger(GenerisanjePDF.class);
	public static void prodaneKartePDF(List<Karta> karte, Date pocetak, Date kraj) throws DocumentException, IOException
	{
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("IKarte.pdf"));
		document.open();
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		document.add(new Paragraph("Izvještaj o prodanim kartama između " + df.format(pocetak) + " i " + df.format(kraj), 
				FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD,	new CMYKColor(89, 78, 0, 100))));
		PdfPTable t = new PdfPTable(5);
		t.setSpacingBefore(25);
		t.setSpacingAfter(25);
		PdfPCell c1 = new PdfPCell(new Phrase("Odredište"));  
		t.addCell(c1);
		PdfPCell c2 = new PdfPCell(new Phrase("Vrijeme"));  
		t.addCell(c2);
		PdfPCell c3 = new PdfPCell(new Phrase("Datum"));  
		t.addCell(c3);
		PdfPCell c4 = new PdfPCell(new Phrase("Tip karte"));  
		t.addCell(c4);
		PdfPCell c5 = new PdfPCell(new Phrase("Cijena"));  
		t.addCell(c5);
		int brojac = 0;
		for (Karta karta : karte){
			Calendar cal = Calendar.getInstance();
			cal.set(karta.getDatumPolaska_godina(), karta.getDatumPolaska_mjesec()-1, karta.getDatumPolaska_dan(),
					karta.getVrijemePolaska_sati(), karta.getVrijemePolaska_minute());
			Date poc = cal.getTime();
			if (poc.getTime() < pocetak.getTime() || poc.getTime() > kraj.getTime()) {
				continue;
			}
			brojac++;
			t.addCell(karta.getLinija().getOdrediste());
			t.addCell(String.valueOf(karta.getVrijemePolaska_sati()) + ":" + String.valueOf(karta.getVrijemePolaska_minute()));
			t.addCell(String.valueOf(karta.getDatumPolaska_dan())+"."+String.valueOf(karta.getDatumPolaska_mjesec())
					+"."+String.valueOf(karta.getDatumPolaska_godina()));
			t.addCell(karta.getTipKarte().toString());
			t.addCell(String.valueOf(karta.getCijena()));
		}
		document.add(new Paragraph("Ukupno prodano u tom periodu: "+ brojac+ " karti"));
		document.add(t);
		document.close();
		if (Desktop.isDesktopSupported()) {
			File myFile = new File("IKarte.pdf");
			Desktop.getDesktop().open(myFile);
		}
	}

	public static void radniciPDF(List<AutobuskaLinija> linije, Radnik vozac) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("IRadnik.pdf"));
		document.open();
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		document.add(new Paragraph("Izvještaj o autobuskim linijama koje je vozio "+ vozac.getIme() + " " + vozac.getPrezime(),
				FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD,	new CMYKColor(89, 78, 0, 100))));
		PdfPTable t = new PdfPTable(5);
		t.setSpacingBefore(25);
		t.setSpacingAfter(25);
		PdfPCell c1 = new PdfPCell(new Phrase("Odredište"));  
		t.addCell(c1);
		PdfPCell c2 = new PdfPCell(new Phrase("Autobus"));  
		t.addCell(c2);
		PdfPCell c3 = new PdfPCell(new Phrase("Datum polaska"));  
		t.addCell(c3);
		PdfPCell c4 = new PdfPCell(new Phrase("Vrijeme polaska"));  
		t.addCell(c4);
		PdfPCell c5 = new PdfPCell(new Phrase("Broj linije"));  
		t.addCell(c5);
		int brojac = 0;
		for (AutobuskaLinija linija : linije){
			Calendar cal = Calendar.getInstance();

			cal.set(linija.getDatumPolaska_godina(), linija.getDatumPolaska_mjesec(), linija.getDatumPolaska_dan(),
					linija.getVrijemePolaska_sati(), linija.getVrijemePolaska_minute());
			Date poc = cal.getTime();
			t.addCell(linija.getOdrediste());
			t.addCell(linija.getAutobus().getRegistracija());
			t.addCell(String.valueOf(linija.getDatumPolaska_dan())+"."+String.valueOf(linija.getDatumPolaska_mjesec())
					+"."+String.valueOf(linija.getDatumPolaska_godina()));
			t.addCell(String.valueOf(linija.getVrijemePolaska_sati()) + ":" + String.valueOf(linija.getVrijemePolaska_minute()));
			t.addCell(String.valueOf(linija.getBrojLinije()));
		}

		document.add(new Paragraph("Ukupno vozio: " + linije.size()));
		document.add(t);
		document.close();
		if (Desktop.isDesktopSupported()) {
			File myFile = new File("IRadnik.pdf");
			Desktop.getDesktop().open(myFile);
		}
	}

	public static void autobuskeLinijePDF(List<AutobuskaLinija> linije, Date pocetak, Date kraj) throws IOException, DocumentException
	{
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);

		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("ILinije.pdf"));

		document.open();

		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

		document.add(new Paragraph("Izvještaj o autobuskim linijama između " + df.format(pocetak) + " i " + df.format(kraj), 
				FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD,	new CMYKColor(89, 78, 0, 100))));
		PdfPTable t = new PdfPTable(5);

		t.setSpacingBefore(25);

		t.setSpacingAfter(25);

		PdfPCell c1 = new PdfPCell(new Phrase("Odrediste"));  

		t.addCell(c1);

		PdfPCell c2 = new PdfPCell(new Phrase("Autobus"));  

		t.addCell(c2);
		PdfPCell c3 = new PdfPCell(new Phrase("Datum polaska"));  

		t.addCell(c3);
		PdfPCell c4 = new PdfPCell(new Phrase("Vrijeme polaska"));  

		t.addCell(c4);
		PdfPCell c5 = new PdfPCell(new Phrase("Broj linije"));  

		t.addCell(c5);

		int brojac = 0;

		for (AutobuskaLinija linija : linije){
			Calendar cal = Calendar.getInstance();

			cal.set(linija.getDatumPolaska_godina(), linija.getDatumPolaska_mjesec()-1, linija.getDatumPolaska_dan(),
					linija.getVrijemePolaska_sati(), linija.getVrijemePolaska_minute());

			Date poc = cal.getTime();
			
			if (poc.getTime() < pocetak.getTime() || poc.getTime() > kraj.getTime()) {
				continue;
			}
			t.addCell(linija.getOdrediste());
			t.addCell(linija.getAutobus().getRegistracija());
			t.addCell(String.valueOf(linija.getDatumPolaska_dan())+"."+String.valueOf(linija.getDatumPolaska_mjesec())
					+"."+String.valueOf(linija.getDatumPolaska_godina()));
			t.addCell(String.valueOf(linija.getVrijemePolaska_sati()) + ":" + String.valueOf(linija.getVrijemePolaska_minute()));
			t.addCell(String.valueOf(linija.getBrojLinije()));
		}
		document.add(new Paragraph("Ukupno vozio: " + linije.size()));
		document.add(t);
		document.close();
		if (Desktop.isDesktopSupported()) {
			File myFile = new File("ILinije.pdf");
			Desktop.getDesktop().open(myFile);
		}
	}

}
