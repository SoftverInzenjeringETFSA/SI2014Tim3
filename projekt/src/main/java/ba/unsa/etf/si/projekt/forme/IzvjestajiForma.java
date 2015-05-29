package ba.unsa.etf.si.projekt.forme;

import org.apache.log4j.Logger;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import ba.unsa.etf.si.projekt.dodatno.GenerisanjePDF;
import ba.unsa.etf.si.projekt.entiteti.AutobuskaLinija;
import ba.unsa.etf.si.projekt.entiteti.Karta;
import ba.unsa.etf.si.projekt.entiteti.Radnik;
import ba.unsa.etf.si.projekt.entiteti.TipRadnogMjesta;
import ba.unsa.etf.si.projekt.hibernate.HibernateAutibuskaLinija;
import ba.unsa.etf.si.projekt.hibernate.HibernateKarta;
import ba.unsa.etf.si.projekt.hibernate.HibernateRadnik;
import ba.unsa.etf.si.projekt.hibernate.HibernateUtil;

import com.toedter.calendar.JDateChooser;

import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;

import org.hibernate.Session;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.SwingConstants;

import java.awt.Toolkit;

public class IzvjestajiForma {
	final static Logger logger = Logger.getLogger(IzvjestajiForma.class);
	private JFrame frmIzvjetaji;
	private JTextField pocetnoVrijemeSati;
	private JTextField pocetnoVrijemeMinute;
	private JTextField krajnjeVrijemeMinute;
	private JTextField krajnjeVrijemeSati;
	private JTextField JMBGVozac;

	private String tipKorisnika;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					// TODO: handle exception
					logger.error("Greška! " + e.getMessage() , e);
				}
				
				try {
					IzvjestajiForma window = new IzvjestajiForma();
					window.frmIzvjetaji.setVisible(true);
				} catch (Exception e) {
				/*	e.printStackTrace();
					logger.error("Greška! " + e.getMessage() , e);*/
					logger.log(null, e); 
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public IzvjestajiForma() {
		initialize();
	}
	
	public IzvjestajiForma(String tipKorisnika) {
		initialize();
		this.tipKorisnika = tipKorisnika;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIzvjetaji = new JFrame();
		frmIzvjetaji.setIconImage(Toolkit.getDefaultToolkit().getImage(IzvjestajiForma.class.getResource("/ba/unsa/etf/si/projekt/dodatno/icon.png")));
		frmIzvjetaji.setTitle("Izvještaji");
		frmIzvjetaji.setBounds(100, 100, 502, 393);
		frmIzvjetaji.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmIzvjetaji.getContentPane().setLayout(null);
		
		JButton nazadBtn = new JButton("Nazad");
		
		// Klik na dugme nazad
		nazadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tipKorisnika == "administrator") {
					AdministratorPocetna a = new AdministratorPocetna();
					a.setVisible(true);
					setVisible(false);
				} else if (tipKorisnika == "menadjer") {
					MenadzerPocetna m = new MenadzerPocetna();
					m.setVisible(true);
					setVisible(false);
				}				
			}
		});
		nazadBtn.setBounds(282, 11, 89, 23);
		frmIzvjetaji.getContentPane().add(nazadBtn);
		
		JButton odjavaBtn = new JButton("Odjava");
		
		// Klik na dugme odjava
		odjavaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrijavaForma p = new PrijavaForma();
				p.setVisible(true);
				setVisible(false);
			}
		});
		odjavaBtn.setBounds(387, 11, 89, 23);
		frmIzvjetaji.getContentPane().add(odjavaBtn);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(21, 73, 438, 270);
		frmIzvjetaji.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Prodane karte", null, panel, null);
		panel.setLayout(null);
		
		final JDateChooser pocetniProdaneDate = new JDateChooser();
		pocetniProdaneDate.setDateFormatString("dd/MM/yyyy");
		pocetniProdaneDate.setBounds(175, 50, 178, 20);
		panel.add(pocetniProdaneDate);
		
		JLabel label_8 = new JLabel("Početni datum:");
		label_8.setHorizontalAlignment(SwingConstants.TRAILING);
		label_8.setBounds(54, 53, 112, 14);
		panel.add(label_8);
		
		JLabel label_9 = new JLabel("Krajnji datum:");
		label_9.setHorizontalAlignment(SwingConstants.TRAILING);
		label_9.setBounds(55, 100, 111, 14);
		panel.add(label_9);
		
		final JDateChooser krajnjiProdaneDate = new JDateChooser();
		krajnjiProdaneDate.setDateFormatString("dd/MM/yyyy");
		krajnjiProdaneDate.setBounds(176, 97, 178, 20);
		panel.add(krajnjiProdaneDate);
		
		final JButton generisiProdaneBtn = new JButton("Generiši izvještaj");
		generisiProdaneBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//izvjestaj o kartama
				int d1=0,d2=0;
				String izuzetak="";
				try
				{
					if(pocetniProdaneDate.getDate()==null)
					{
						d1=1;
						izuzetak+="Morate unijeti početni datum. ";
					}
					if(krajnjiProdaneDate.getDate()==null)
					{
					d2=1;
					izuzetak+="Morate unijeti krajnji datum.";
					}
					if(d1==0 && d2==0)
					{
					Session session = HibernateUtil.getSessionFactory().openSession();
					HibernateKarta k=new HibernateKarta();
					Date datum1=pocetniProdaneDate.getDate();
					Calendar cal=Calendar.getInstance();
					cal.setTime(datum1);
					int godina1=cal.get(Calendar.YEAR);
					int mjesec1=cal.get(Calendar.MONTH)+1;
					int dan1=cal.get(Calendar.DAY_OF_MONTH);
					Date datum2=krajnjiProdaneDate.getDate();
					Calendar cal1=Calendar.getInstance();
					cal1.setTime(datum2);
					int godina2=cal1.get(Calendar.YEAR);
					int mjesec2=cal1.get(Calendar.MONTH)+1;
					int dan2=cal1.get(Calendar.DAY_OF_MONTH);
					
					Calendar currentDate = Calendar.getInstance(); //Get the current date
					Date danas=currentDate.getTime();
					if(datum1.before(danas )&& datum2.before(danas) && datum1.before(datum2))
					{
					List<Karta> karte = k.IzvjestajOProdanimKartama(session, godina1, mjesec1, dan1, godina2, mjesec2, dan2);
					GenerisanjePDF.prodaneKartePDF(karte,datum1,datum2);
					
					JOptionPane.showMessageDialog(generisiProdaneBtn, "Uspješno ste kreirali izvještaj o prodanim kartama.");
					pocetniProdaneDate.setDate(null);
					krajnjiProdaneDate.setDate(null);
					}
					else
					{
						JOptionPane.showMessageDialog(generisiProdaneBtn, "Ne možete kreirati izvještaje za budućnost i početni datum mora biti prije krajnjeg.");
					}
					}
					else
					{
						JOptionPane.showMessageDialog(generisiProdaneBtn, izuzetak);
					}
				}
				catch(Exception ex4)
				{
					JOptionPane.showMessageDialog(generisiProdaneBtn, "Niste kreirali izvjestaj.");
					logger.info("Greška!.", ex4);
				}
				
			}
		});
		generisiProdaneBtn.setBounds(214, 208, 140, 23);
		panel.add(generisiProdaneBtn);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Autobuske linije", null, panel_1, null);
		panel_1.setLayout(null);
		
		final JDateChooser pocetniLinijeDate = new JDateChooser();
		pocetniLinijeDate.setDateFormatString("dd/MM/yyyy");
		pocetniLinijeDate.setBounds(171, 11, 178, 20);
		panel_1.add(pocetniLinijeDate);
		
		JLabel label = new JLabel("Početni datum:");
		label.setHorizontalAlignment(SwingConstants.TRAILING);
		label.setBounds(50, 14, 112, 14);
		panel_1.add(label);
		
		JLabel label_1 = new JLabel("Krajnji datum:");
		label_1.setHorizontalAlignment(SwingConstants.TRAILING);
		label_1.setBounds(51, 61, 111, 14);
		panel_1.add(label_1);
		
		final JDateChooser krajnjiLinijeDate = new JDateChooser();
		krajnjiLinijeDate.setDateFormatString("dd/MM/yyyy");
		krajnjiLinijeDate.setBounds(172, 58, 178, 20);
		panel_1.add(krajnjiLinijeDate);
		
		pocetnoVrijemeSati = new JTextField();
		pocetnoVrijemeSati.setColumns(10);
		pocetnoVrijemeSati.setBounds(171, 103, 67, 20);
		panel_1.add(pocetnoVrijemeSati);
		
		JLabel label_2 = new JLabel("Početno vrijeme:");
		label_2.setHorizontalAlignment(SwingConstants.TRAILING);
		label_2.setBounds(33, 106, 129, 14);
		panel_1.add(label_2);
		
		pocetnoVrijemeMinute = new JTextField();
		pocetnoVrijemeMinute.setColumns(10);
		pocetnoVrijemeMinute.setBounds(270, 103, 61, 20);
		panel_1.add(pocetnoVrijemeMinute);
		
		krajnjeVrijemeMinute = new JTextField();
		krajnjeVrijemeMinute.setColumns(10);
		krajnjeVrijemeMinute.setBounds(270, 149, 61, 20);
		panel_1.add(krajnjeVrijemeMinute);
		
		krajnjeVrijemeSati = new JTextField();
		krajnjeVrijemeSati.setColumns(10);
		krajnjeVrijemeSati.setBounds(171, 149, 67, 20);
		panel_1.add(krajnjeVrijemeSati);
		
		JLabel label_3 = new JLabel("Krajnje vrijeme:");
		label_3.setHorizontalAlignment(SwingConstants.TRAILING);
		label_3.setBounds(40, 152, 122, 14);
		panel_1.add(label_3);
		
		JLabel label_4 = new JLabel("h");
		label_4.setBounds(248, 152, 12, 14);
		panel_1.add(label_4);
		
		JLabel label_5 = new JLabel("h");
		label_5.setBounds(248, 106, 12, 14);
		panel_1.add(label_5);
		
		JLabel label_6 = new JLabel("m");
		label_6.setBounds(341, 106, 27, 14);
		panel_1.add(label_6);
		
		JLabel label_7 = new JLabel("m");
		label_7.setBounds(341, 152, 19, 14);
		panel_1.add(label_7);
		
		final JButton generisiLinijeBtn = new JButton("Generiši izvještaj");
		generisiLinijeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { //generisanje izvjestaja o Autobuskim linijama
				int d1=0,d2=0,v1=0,v2=0;
				String izuzetak="";
				try
				{
					if(pocetniLinijeDate.getDate()==null)
					{
						d1=1;
						izuzetak+="Morate unijeti početni datum. ";
					}
					if(krajnjiLinijeDate.getDate()==null)
					{
						d2=1;
						izuzetak+="Morate unijeti krajnji datum. ";
					}
					if(pocetnoVrijemeSati.getText().length()==0 || pocetnoVrijemeMinute.getText().length()==0)
					{
						v1=1;
						izuzetak+="Morate unijeti pocetno vrijeme i sate i minute. ";
					}
					if(krajnjeVrijemeSati.getText().length()==0 || krajnjeVrijemeMinute.getText().length()==0)
					{
						v2=1;
						izuzetak+="Morate unijeti krajnje vrijeme.";
					}
					if(d1==0 && d2==0 && v1==0 && v2==0)
					{
					Session session = HibernateUtil.getSessionFactory().openSession();
					
					HibernateAutibuskaLinija linija=new HibernateAutibuskaLinija();
					Date datum1=pocetniLinijeDate.getDate();
					Calendar cal= Calendar.getInstance();
		            cal.setTime(datum1);
					int godina1=cal.get(Calendar.YEAR);
					int mjesec1=cal.get(Calendar.MONTH)+1;
					int dan1=cal.get(Calendar.DAY_OF_MONTH);
					Date datum2=krajnjiLinijeDate.getDate();
					Calendar cal1=Calendar.getInstance();
					cal1.setTime(datum2);
					int godina2=cal1.get(Calendar.YEAR);
					int mjesec2=cal1.get(Calendar.MONTH)+1;
					int dan2=cal1.get(Calendar.DAY_OF_MONTH);
					if(pocetnoVrijemeSati.getText().contains(".") || pocetnoVrijemeMinute.getText().contains(".") || krajnjeVrijemeSati.getText().contains(".") || krajnjeVrijemeMinute.getText().contains("."))
					{
						izuzetak+="Minute i sati ne smiju biti decimalni brojevi.";
					}
					int pocetnisati=Integer.parseInt(pocetnoVrijemeSati.getText());
					int pocetneminute=Integer.parseInt(pocetnoVrijemeMinute.getText());
					int krajnjisati=Integer.parseInt(krajnjeVrijemeSati.getText());
					int krajnjeminute=Integer.parseInt(krajnjeVrijemeMinute.getText());
					
					Calendar currentDate = Calendar.getInstance(); //Get the current date
					Date danas=currentDate.getTime();
					if(datum1.before(datum2) && datum1.before(danas)&& datum2.before(danas) && pocetnisati>=0 && pocetnisati<=24 && krajnjisati>=0 && krajnjisati<=24 && pocetneminute>=0 && pocetneminute<=60 && krajnjeminute>=0 && krajnjeminute<=60)
					{
					List<AutobuskaLinija> l = linija.IzvjestajOAutobuskimLinijama(session, godina1, mjesec1, dan1, godina2, mjesec2, dan2, pocetnisati, pocetneminute, krajnjisati, krajnjeminute);
					GenerisanjePDF.autobuskeLinijePDF(l, datum1, datum2);
					JOptionPane.showMessageDialog(generisiLinijeBtn, "Uspješno ste kreirali izvještaj o autobuskim linijama.");
					pocetnoVrijemeSati.setText("");
					pocetnoVrijemeMinute.setText("");
					krajnjeVrijemeSati.setText("");
					krajnjeVrijemeMinute.setText("");
					krajnjiLinijeDate.setDate(null);
					pocetniLinijeDate.setDate(null);
					}
					else
					{
						JOptionPane.showMessageDialog(generisiLinijeBtn, "Nemoguće je kreirati izvještaje za budućnost i početni datum mora biti prije krajnjeg i neispravne vrijednosti sati i minuta.");
					}
					
					}
					else
					{
						JOptionPane.showMessageDialog(generisiLinijeBtn, izuzetak);
					}
				}
				catch(Exception ex1)
				{
					JOptionPane.showMessageDialog(generisiLinijeBtn, "Neuspješno kreiranje izvještaja:\n"+izuzetak);
				}
				
			}
		});
		generisiLinijeBtn.setBounds(209, 208, 140, 23);
		panel_1.add(generisiLinijeBtn);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Vozači", null, panel_2, null);
		panel_2.setLayout(null);
		
		JMBGVozac = new JTextField();
		JMBGVozac.setColumns(10);
		JMBGVozac.setBounds(204, 67, 113, 20);
		panel_2.add(JMBGVozac);
		
		JLabel lblImeVozaa = new JLabel("JMBG:");
		lblImeVozaa.setHorizontalAlignment(SwingConstants.TRAILING);
		lblImeVozaa.setBounds(135, 70, 59, 14);
		panel_2.add(lblImeVozaa);
		
		final JButton generisiVozaciBtn = new JButton("Generiši izvještaj");
		generisiVozaciBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					if(JMBGVozac.getText().length()!=0)
					{
					Session session = HibernateUtil.getSessionFactory().openSession();
					HibernateAutibuskaLinija linija=new HibernateAutibuskaLinija();
					Radnik r = HibernateRadnik.nadjiRadnika(session, JMBGVozac.getText());
					TipRadnogMjesta r2=TipRadnogMjesta.Vozac;
					TipRadnogMjesta r1=r.dajTipRadnogMjesta();
					JOptionPane.showMessageDialog(generisiVozaciBtn, r.dajTipRadnogMjesta().equals(r));
					if(r!=null && r1==r2)
					{
					GenerisanjePDF.radniciPDF(linija.IzvjestajORadnicima(session, r.getIme() , r.getPrezime()), r);
					
					JOptionPane.showMessageDialog(generisiVozaciBtn, "Uspješno ste kreirali izvještaj o vozačima.");
					}
					else
					{
						JOptionPane.showMessageDialog(generisiVozaciBtn, "Ne postoji vozač, sa jmbg koji ste unijeli ili nije vozać.");
					}
					JMBGVozac.setText("");
					}
					else
					{
						JOptionPane.showMessageDialog(generisiVozaciBtn, "Morate unijeti JMBG vozaca.");
					}

				}
				catch(Exception ex3)
				{
					JOptionPane.showMessageDialog(generisiVozaciBtn, "Niste uspješno kreirali izvještaj:\n"+ex3.getMessage());
				}
			}
		});
		generisiVozaciBtn.setBounds(177, 208, 140, 23);
		panel_2.add(generisiVozaciBtn);
		
		
		/*
		frmIzvjetaji.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(frmIzvjetaji, 
		            "Da li želite zatvoriti prozor?", 
		            "Zatvaranje", JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		        	AdministratorPocetna a = new AdministratorPocetna();
		        	a.frmAdministratorPoetna.setVisible(true);
		            frmIzvjetaji.setVisible(false);
		        }
		    }
		});
		*/
	}
	
	public void setVisible(boolean visible) {
		frmIzvjetaji.setVisible(visible);
	}
	
}
