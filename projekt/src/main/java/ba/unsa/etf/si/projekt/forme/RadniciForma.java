package ba.unsa.etf.si.projekt.forme;
import org.apache.log4j.Logger;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.UIManager;

import org.hibernate.Session;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import ba.unsa.etf.si.projekt.entiteti.AutobuskaLinija;
import ba.unsa.etf.si.projekt.entiteti.KorisnickiRacun;
import ba.unsa.etf.si.projekt.entiteti.Radnik;
import ba.unsa.etf.si.projekt.entiteti.TipRadnogMjesta;
import ba.unsa.etf.si.projekt.hibernate.HibernateAutibuskaLinija;
import ba.unsa.etf.si.projekt.hibernate.HibernateKorisnickiRacuni;
import ba.unsa.etf.si.projekt.hibernate.HibernateRadnik;
import ba.unsa.etf.si.projekt.hibernate.HibernateUtil;

import javax.swing.SwingConstants;

import java.awt.Toolkit;

public class RadniciForma {
	final static Logger logger = Logger.getLogger(RadniciForma.class);
	private JFrame frmEvidencijaRadnika;
	private JTextField imeDodaj;
	private JTextField prezimeDodaj;
	private JTextField jmbgDodaj;
	private JTextField imeModifikuj;
	private JTextField prezimeModifikuj;
	private JTextField jmbgModifikujPronadji;
	private JTextField jmbgIzbrisiPronadi;
	private JTextField imeIzbrisi;
	private JTextField prezimeIzbrisi;
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
					RadniciForma window = new RadniciForma();
					window.frmEvidencijaRadnika.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("Greška! " + e.getMessage() , e);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RadniciForma() {
		initialize();
	}

	public RadniciForma(String tipKorisnika) {
		// TODO Auto-generated constructor stub
		initialize();
		this.tipKorisnika = tipKorisnika;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEvidencijaRadnika = new JFrame();
		frmEvidencijaRadnika.setIconImage(Toolkit.getDefaultToolkit().getImage(RadniciForma.class.getResource("/ba/unsa/etf/si/projekt/dodatno/icon.png")));
		frmEvidencijaRadnika.setTitle("Evidencija radnika");
		frmEvidencijaRadnika.setBounds(100, 100, 479, 373);
		frmEvidencijaRadnika.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmEvidencijaRadnika.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(30, 50, 411, 277);
		frmEvidencijaRadnika.getContentPane().add(tabbedPane);
		
		JPanel dodajRadnikaTab = new JPanel();
		tabbedPane.addTab("Dodaj", null, dodajRadnikaTab, null);
		dodajRadnikaTab.setLayout(null);
		
		prezimeDodaj = new JTextField();
		prezimeDodaj.setBounds(151, 64, 193, 20);
		dodajRadnikaTab.add(prezimeDodaj);
		prezimeDodaj.setColumns(10);
		
		imeDodaj = new JTextField();
		imeDodaj.setBounds(151, 26, 193, 20);
		dodajRadnikaTab.add(imeDodaj);
		imeDodaj.setColumns(10);
		
		jmbgDodaj = new JTextField();
		jmbgDodaj.setBounds(151, 102, 193, 20);
		dodajRadnikaTab.add(jmbgDodaj);
		jmbgDodaj.setColumns(10);
		
		final JComboBox tipDodajCombo = new JComboBox();
		tipDodajCombo.setBounds(151, 146, 193, 20);
		TipRadnogMjesta administrator=TipRadnogMjesta.Administrator;
		TipRadnogMjesta menadzer=TipRadnogMjesta.Menadzer;
		TipRadnogMjesta salteras=TipRadnogMjesta.SalterskiRadnik;
		TipRadnogMjesta vozac=TipRadnogMjesta.Vozac;
		tipDodajCombo.addItem(menadzer);
		tipDodajCombo.addItem(salteras);
		tipDodajCombo.addItem(administrator);
		tipDodajCombo.addItem(vozac);
		dodajRadnikaTab.add(tipDodajCombo);
		
		JLabel lblIme = new JLabel("Ime:");
		lblIme.setHorizontalAlignment(SwingConstants.TRAILING);
		lblIme.setBounds(96, 29, 45, 14);
		dodajRadnikaTab.add(lblIme);
		
		JLabel lblPrezime = new JLabel("Prezime:");
		lblPrezime.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPrezime.setBounds(77, 67, 64, 14);
		dodajRadnikaTab.add(lblPrezime);
		
		JLabel lblJmbg = new JLabel("JMBG:");
		lblJmbg.setHorizontalAlignment(SwingConstants.TRAILING);
		lblJmbg.setBounds(88, 105, 53, 14);
		dodajRadnikaTab.add(lblJmbg);
		
		JLabel lblTipRadnogMjesta = new JLabel("Tip radnog mjesta:");
		lblTipRadnogMjesta.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTipRadnogMjesta.setBounds(0, 149, 141, 14);
		dodajRadnikaTab.add(lblTipRadnogMjesta);
		
		final JButton dodajBtn = new JButton("Dodaj");
		dodajBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) { //dodavanje radnika novih
			
				try
				{
					Session session = HibernateUtil.getSessionFactory().openSession();
					HibernateRadnik noviradnik= new HibernateRadnik();
					boolean postoji=false;
					java.util.List radnici=noviradnik.sviRadnici(session);
					for(int i=0;i<radnici.size();i++)
					{
						Radnik r=(Radnik)radnici.get(i);
						if(r.getJmbg().equals(jmbgDodaj.getText()))
							postoji=true;
					}
					
					if(tipDodajCombo.getSelectedItem().toString()=="Administrator")
					{
						TipRadnogMjesta r=TipRadnogMjesta.Administrator;
						if(postoji==false)
						{	
						noviradnik.dodajRadnika(session, imeDodaj.getText(), prezimeDodaj.getText(), jmbgDodaj.getText(), r);
						JOptionPane.showMessageDialog(dodajBtn, "Uspješno je dodat radnik.");	
						imeDodaj.setText("");
						prezimeDodaj.setText("");
						jmbgDodaj.setText("");
						}
						else
						{
							JOptionPane.showMessageDialog(dodajBtn, "Ponovo unesite jmbg.");	
							jmbgDodaj.setText("");
						}
					}
					
					else if(tipDodajCombo.getSelectedItem().toString()=="Menadzer")
					{
					TipRadnogMjesta r1=TipRadnogMjesta.Menadzer;
					if(postoji==false)
					{
					noviradnik.dodajRadnika(session, imeDodaj.getText(), prezimeDodaj.getText(), jmbgDodaj.getText(), r1);
					JOptionPane.showMessageDialog(dodajBtn, "Uspješno je dodat radnik.");
					imeDodaj.setText("");
					prezimeDodaj.setText("");
					jmbgDodaj.setText("");
					}
					else
					{
						JOptionPane.showMessageDialog(dodajBtn, "Ponovo unesite jmbg.");
						jmbgDodaj.setText("");
					}
					}
					
					else if (tipDodajCombo.getSelectedItem().toString()=="Vozac") {
						TipRadnogMjesta r1 = TipRadnogMjesta.Vozac;
						if(postoji==false)
						{
						noviradnik.dodajRadnika(session, imeDodaj.getText(), prezimeDodaj.getText(), jmbgDodaj.getText(), r1);
						JOptionPane.showMessageDialog(dodajBtn, "Uspješno je dodat radnik");
						imeDodaj.setText("");
						prezimeDodaj.setText("");
						jmbgDodaj.setText("");
						}
						else
						{
							JOptionPane.showMessageDialog(dodajBtn, "Ponovo unesite jmbg.");
							jmbgDodaj.setText("");
						}
					}
					
					else
					{
					TipRadnogMjesta r2=TipRadnogMjesta.SalterskiRadnik;
					if(postoji==false)
					{
					noviradnik.dodajRadnika(session, imeDodaj.getText(), prezimeDodaj.getText(), jmbgDodaj.getText(), r2);
					JOptionPane.showMessageDialog(dodajBtn, "Uspješno je dodat radnik.");
					imeDodaj.setText("");
					prezimeDodaj.setText("");
					jmbgDodaj.setText("");
					}
					else 
					{
						JOptionPane.showMessageDialog(dodajBtn, "Ponovo unesite jmbg.");
						jmbgDodaj.setText("");
					}
					}
					
				}
				catch(Exception e4)
				{
					JOptionPane.showMessageDialog(dodajBtn, "Dodavanje nije bilo uspješno.");
					JOptionPane.showMessageDialog(dodajBtn, e4);	
				}
			}
		});
		dodajBtn.setBounds(263, 207, 81, 23);
		dodajRadnikaTab.add(dodajBtn);
		
		JPanel modifikujRadnikaTab = new JPanel();
		tabbedPane.addTab("Modifikuj", null, modifikujRadnikaTab, null);
		modifikujRadnikaTab.setLayout(null);
		
		imeModifikuj = new JTextField();
		imeModifikuj.setColumns(10);
		imeModifikuj.setBounds(161, 81, 183, 20);
		modifikujRadnikaTab.add(imeModifikuj);
		
		JLabel label = new JLabel("Ime:");
		label.setHorizontalAlignment(SwingConstants.TRAILING);
		label.setBounds(97, 84, 54, 14);
		modifikujRadnikaTab.add(label);
		
		JLabel label_1 = new JLabel("Prezime:");
		label_1.setHorizontalAlignment(SwingConstants.TRAILING);
		label_1.setBounds(78, 122, 73, 14);
		modifikujRadnikaTab.add(label_1);
		
		prezimeModifikuj = new JTextField();
		prezimeModifikuj.setColumns(10);
		prezimeModifikuj.setBounds(161, 119, 183, 20);
		modifikujRadnikaTab.add(prezimeModifikuj);
		
		JLabel label_3 = new JLabel("Tip radnog mjesta:");
		label_3.setHorizontalAlignment(SwingConstants.TRAILING);
		label_3.setBounds(23, 167, 128, 14);
		modifikujRadnikaTab.add(label_3);
		
		final JComboBox tipModifikujCombo = new JComboBox();
		tipModifikujCombo.setBounds(161, 164, 183, 20);
		TipRadnogMjesta administrator1=TipRadnogMjesta.Administrator;
		TipRadnogMjesta menadzer1=TipRadnogMjesta.Menadzer;
		TipRadnogMjesta salteras1=TipRadnogMjesta.SalterskiRadnik;
		TipRadnogMjesta vozac1=TipRadnogMjesta.Vozac;
		tipModifikujCombo.addItem(administrator1);
		tipModifikujCombo.addItem(menadzer1);
		tipModifikujCombo.addItem(salteras1);
		tipModifikujCombo.addItem(vozac1);
		modifikujRadnikaTab.add(tipModifikujCombo);
		
		final JButton modifikujBtn = new JButton("Spasi promjene");
		modifikujBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {//izmjene radnika
			
				try
				{
					Session session = HibernateUtil.getSessionFactory().openSession();
					HibernateRadnik promjenaradnik= new HibernateRadnik();

					if(tipModifikujCombo.getSelectedItem().toString()=="Administrator")
					{
					TipRadnogMjesta k=TipRadnogMjesta.Administrator;
					promjenaradnik.modifikujRadnika(session, imeModifikuj.getText(), prezimeModifikuj.getText(), jmbgModifikujPronadji.getText(), k);
					JOptionPane.showMessageDialog(modifikujBtn, "Uspješno ste modifikovali radnika.");
					}
					else if(tipModifikujCombo.getSelectedItem().toString()=="Menadzer")
					{
					TipRadnogMjesta k=TipRadnogMjesta.Menadzer;
					promjenaradnik.modifikujRadnika(session, imeModifikuj.getText(), prezimeModifikuj.getText(), jmbgModifikujPronadji.getText(), k);
					JOptionPane.showMessageDialog(modifikujBtn, "Uspješno ste modifikovali radnika.");
					}
					else if(tipModifikujCombo.getSelectedItem().toString()=="SalterskiRadnik")
					{
					TipRadnogMjesta k=TipRadnogMjesta.SalterskiRadnik;
					promjenaradnik.modifikujRadnika(session, imeModifikuj.getText(), prezimeModifikuj.getText(), jmbgModifikujPronadji.getText(), k);
					JOptionPane.showMessageDialog(modifikujBtn, "Uspješno ste modifikovali radnika.");
					}
					else if (tipModifikujCombo.getSelectedItem().toString()=="Vozac") {
						TipRadnogMjesta v=TipRadnogMjesta.Vozac;
						promjenaradnik.modifikujRadnika(session, imeModifikuj.getText(), prezimeModifikuj.getText(), jmbgModifikujPronadji.getText(), v);
						JOptionPane.showMessageDialog(modifikujBtn, "Uspješno ste modifikovali radnika.");
						
					}
					imeModifikuj.setText("");
					prezimeModifikuj.setText("");
					jmbgModifikujPronadji.setText("");
				}
				
				catch(Exception e6)
				{
					JOptionPane.showMessageDialog(modifikujBtn, "Neuspješno modifikovanje radnika.");
					JOptionPane.showMessageDialog(modifikujBtn, e6);
				}
			}
		});
		modifikujBtn.setBounds(240, 214, 140, 23);
		modifikujRadnikaTab.add(modifikujBtn);
		
		JLabel lblRadnik = new JLabel("JMBG radnika:");
		lblRadnik.setHorizontalAlignment(SwingConstants.TRAILING);
		lblRadnik.setBounds(0, 36, 119, 14);
		modifikujRadnikaTab.add(lblRadnik);
		
		jmbgModifikujPronadji = new JTextField();
		jmbgModifikujPronadji.setColumns(10);
		jmbgModifikujPronadji.setBounds(130, 33, 151, 20);
		modifikujRadnikaTab.add(jmbgModifikujPronadji);
		
		final JButton pronadjiModifikujBtn = new JButton("Pronađi");
		pronadjiModifikujBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) { // pronađi odgovarajuceg radnika
				
				try
				{
					Session session = HibernateUtil.getSessionFactory().openSession();
					HibernateRadnik pronadjeniradnik= new HibernateRadnik();
					Radnik r=new Radnik();
					r=pronadjeniradnik.nadjiRadnika(session, jmbgModifikujPronadji.getText());
					
					if(r!=null)
					{
						imeModifikuj.setText(r.getIme());
						prezimeModifikuj.setText(r.getPrezime());
						tipModifikujCombo.setSelectedItem(r.dajTipRadnogMjesta());
					}
					
					else
					{
						JOptionPane.showMessageDialog(pronadjiModifikujBtn, "Ne postoji radnik, čiji ste JMBG unijeli.");
						jmbgModifikujPronadji.setText("");
					}
				}
				
				catch(Exception e5)
				{
					JOptionPane.showMessageDialog(pronadjiModifikujBtn, "Neusješna pretraga");
					JOptionPane.showMessageDialog(pronadjiModifikujBtn, e5);
				}
			
			}
		});
		pronadjiModifikujBtn.setBounds(291, 32, 89, 23);
		modifikujRadnikaTab.add(pronadjiModifikujBtn);
		
		JPanel izbrisiRadnikaTab = new JPanel();
		tabbedPane.addTab("Izbriši", null, izbrisiRadnikaTab, null);
		izbrisiRadnikaTab.setLayout(null);
		
		JLabel label_4 = new JLabel("JMBG radnika:");
		label_4.setHorizontalAlignment(SwingConstants.TRAILING);
		label_4.setBounds(0, 36, 110, 14);
		izbrisiRadnikaTab.add(label_4);
		
		jmbgIzbrisiPronadi = new JTextField();
		jmbgIzbrisiPronadi.setColumns(10);
		jmbgIzbrisiPronadi.setBounds(124, 33, 151, 20);
		izbrisiRadnikaTab.add(jmbgIzbrisiPronadi);
		
		final JComboBox tipIzbrisiCombo = new JComboBox();
		tipIzbrisiCombo.setBounds(155, 162, 183, 20);
		TipRadnogMjesta administrator2=TipRadnogMjesta.Administrator;
		TipRadnogMjesta menadzer2=TipRadnogMjesta.Menadzer;
		TipRadnogMjesta salteras2=TipRadnogMjesta.SalterskiRadnik;
		TipRadnogMjesta vozac2=TipRadnogMjesta.Vozac;
		tipIzbrisiCombo.addItem(menadzer2);
		tipIzbrisiCombo.addItem(salteras2);
		tipIzbrisiCombo.addItem(administrator2);
		tipIzbrisiCombo.addItem(vozac2);
		
		
		izbrisiRadnikaTab.add(tipIzbrisiCombo);
		
		final JButton pronadiIzbrisiBtn = new JButton("Pronađi");
		pronadiIzbrisiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //pronadji za brisanje radnika
				
				try
				{
					Session session = HibernateUtil.getSessionFactory().openSession();
					HibernateRadnik pronadjeniradnik= new HibernateRadnik();
					Radnik r=new Radnik();
					r=pronadjeniradnik.nadjiRadnika(session, jmbgIzbrisiPronadi.getText());
					
					if(r!=null)
					{
						imeIzbrisi.setText(r.getIme());
						prezimeIzbrisi.setText(r.getPrezime());
						tipIzbrisiCombo.setSelectedItem(r.dajTipRadnogMjesta()); 
					}
					
					else
					{
						JOptionPane.showMessageDialog(pronadiIzbrisiBtn, "Ne postoji radnik, čiji ste JMBG unijeli.");
						jmbgIzbrisiPronadi.setText("");
					}
				}
				catch(Exception e6)
				{
					JOptionPane.showMessageDialog(pronadiIzbrisiBtn, "Neusješna pretraga");
					JOptionPane.showMessageDialog(pronadiIzbrisiBtn, e6);
				}
				
			}
		});
		pronadiIzbrisiBtn.setBounds(285, 32, 89, 23);
		izbrisiRadnikaTab.add(pronadiIzbrisiBtn);
		
		imeIzbrisi = new JTextField();
		imeIzbrisi.setEditable(false);
		imeIzbrisi.setColumns(10);
		imeIzbrisi.setBounds(155, 81, 183, 20);
		izbrisiRadnikaTab.add(imeIzbrisi);
		
		prezimeIzbrisi = new JTextField();
		prezimeIzbrisi.setEditable(false);
		prezimeIzbrisi.setColumns(10);
		prezimeIzbrisi.setBounds(155, 119, 183, 20);
		izbrisiRadnikaTab.add(prezimeIzbrisi);
		
		
		
		final JButton izbrisiBtn = new JButton("Izbriši");
		izbrisiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//brisanjee radnika
				String izuzetak="";
			try
			{
				boolean brisi=true;
				Session session = HibernateUtil.getSessionFactory().openSession();
				HibernateRadnik brisanjeradnik= new HibernateRadnik();
				//radnik postoji u korisnickim racunima i u linijama 
				
				java.util.List racuni=HibernateKorisnickiRacuni.sviRacuni(session);
				for(int i=0;i<racuni.size();i++)
				{
					KorisnickiRacun k=(KorisnickiRacun)racuni.get(i);
					Radnik radnik=HibernateRadnik.nadjiRadnika(session, jmbgIzbrisiPronadi.getText());
					if(k.getRadnik()==radnik) {brisi=false;
					izuzetak="Ne možete brisati radnika, jer postoji korisnički račun napravljen za njega.";
					}
				}
				
				java.util.List linije=HibernateAutibuskaLinija.sveLinije(session);
				for(int i=0;i<linije.size();i++)
				{
					AutobuskaLinija l=(AutobuskaLinija)linije.get(i);
					Radnik radnik=HibernateRadnik.nadjiRadnika(session, jmbgIzbrisiPronadi.getText());
					if(l.getVozac()==radnik)
						{brisi=false;
					izuzetak="Ne možete brisati radnika, jer je dodijeljen liniji kao njen vozač.";
						}
				}
				if(brisi==true)
				{
				brisanjeradnik.brisiRadnika(session, jmbgIzbrisiPronadi.getText());
				JOptionPane.showMessageDialog(izbrisiBtn, "Uspješno brisanje.");
				jmbgIzbrisiPronadi.setText("");
				imeIzbrisi.setText("");
				prezimeIzbrisi.setText("");
				}
			}
			catch(Exception e7)
			{
				JOptionPane.showMessageDialog(izbrisiBtn, "Neuspješno brisanje.");
				JOptionPane.showMessageDialog(izbrisiBtn, izuzetak);
				JOptionPane.showMessageDialog(izbrisiBtn, e7);
				
			}
			}
		});
		izbrisiBtn.setBounds(285, 214, 89, 23);
		izbrisiRadnikaTab.add(izbrisiBtn);
		
		JLabel label_5 = new JLabel("Tip radnog mjesta:");
		label_5.setHorizontalAlignment(SwingConstants.TRAILING);
		label_5.setBounds(0, 165, 145, 14);
		izbrisiRadnikaTab.add(label_5);
		
		JLabel label_7 = new JLabel("Prezime:");
		label_7.setHorizontalAlignment(SwingConstants.TRAILING);
		label_7.setBounds(79, 122, 66, 14);
		izbrisiRadnikaTab.add(label_7);
		
		JLabel label_8 = new JLabel("Ime:");
		label_8.setHorizontalAlignment(SwingConstants.TRAILING);
		label_8.setBounds(98, 84, 47, 14);
		izbrisiRadnikaTab.add(label_8);
		
		JButton odjavaBtn = new JButton("Odjava");
		odjavaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrijavaForma p = new PrijavaForma();
				p.setVisible(true);
				setVisible(false);
			}
		});
		odjavaBtn.setBounds(338, 11, 89, 23);
		frmEvidencijaRadnika.getContentPane().add(odjavaBtn);
		
		JButton nazadBtn = new JButton("Nazad");
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
		nazadBtn.setBounds(239, 11, 89, 23);
		frmEvidencijaRadnika.getContentPane().add(nazadBtn);
	}
	
	public void setVisible(boolean visible) {
		frmEvidencijaRadnika.setVisible(visible);
	}
}
