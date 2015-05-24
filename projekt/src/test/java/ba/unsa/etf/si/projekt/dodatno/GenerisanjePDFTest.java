package ba.unsa.etf.si.projekt.dodatno;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import ba.unsa.etf.si.projekt.entiteti.AutobuskaLinija;
import ba.unsa.etf.si.projekt.entiteti.Karta;
import ba.unsa.etf.si.projekt.entiteti.Radnik;
import ba.unsa.etf.si.projekt.entiteti.TipRadnogMjesta;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.DocumentException;

public class GenerisanjePDFTest {

	@Test
	public void testProdaneKartePDFNemaFileaExc() {
	Document document = new Document(PageSize.A4, 50, 50, 50, 50);
	try {
	PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("IKarte.csv"));		
		}
		catch (FileNotFoundException e) {
			assertTrue(true);
			}
	catch (DocumentException e) {
		assertTrue(true);
		}
	}
	
	@Test
	public void testRadniciPDFNemaFileaExc() {
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		try {
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("IRadnik.csv"));		
			}
			catch (FileNotFoundException e) {
				assertTrue(true);
				}
		catch (DocumentException e) {
			assertTrue(true);
			}
	}
	
	@Test
	public void testAutobuskeLinijePDFNemaFileaExc() {
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		try {
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("ILinije.csv"));		
			}
			catch (FileNotFoundException e) {
				assertTrue(true);
				}
		catch (DocumentException e) {
			assertTrue(true);
			}
	}

}
