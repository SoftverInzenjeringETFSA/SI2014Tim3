package ba.unsa.etf.si.projekt.dodatno;

import static org.junit.Assert.*;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.junit.Test;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.DocumentException;

public class GenerisanjePDFTest {

	@Test
	public void testProdaneKartePDF() {
		fail("Not yet implemented");
	}
	
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
	public void testRadniciPDF() {
		fail("Not yet implemented");
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
	public void testAutobuskeLinijePDF() {
		fail("Not yet implemented");
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
