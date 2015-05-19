package ba.unsa.etf.si.projekt.forme;

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

import ba.unsa.etf.si.projekt.entiteti.Radnik;
import ba.unsa.etf.si.projekt.entiteti.TipRadnogMjesta;
import ba.unsa.etf.si.projekt.hibernate.HibernateRadnik;
import ba.unsa.etf.si.projekt.hibernate.HibernateUtil;

public class RadniciForma {

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
				}
				try {
					RadniciForma window = new RadniciForma();
					window.frmEvidencijaRadnika.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
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
		lblIme.setBounds(103, 29, 22, 14);
		dodajRadnikaTab.add(lblIme);
		
		JLabel lblPrezime = new JLabel("Prezime:");
		lblPrezime.setBounds(79, 67, 46, 14);
		dodajRadnikaTab.add(lblPrezime);
		
		JLabel lblJmbg = new JLabel("JMBG:");
		lblJmbg.setBounds(93, 105, 34, 14);
		dodajRadnikaTab.add(lblJmbg);
		
		JLabel lblTipRadnogMjesta = new JLabel("Tip radnog mjesta:");
		lblTipRadnogMjesta.setBounds(32, 149, 101, 14);
		dodajRadnikaTab.add(lblTipRadnogMjesta);
		
		final JButton dodajBtn = new JButton("Dodaj");
		dodajBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) { //dodavanje radnika novih
			
				try
				{
					Session session = HibernateUtil.getSessionFactory().openSession();
					HibernateRadnik noviradnik= new HibernateRadnik();
					
					if(tipDodajCombo.getSelectedItem().toString()=="Administrator")
					{
						TipRadnogMjesta r=TipRadnogMjesta.Administrator;
						noviradnik.dodajRadnika(session, imeDodaj.getText(), prezimeDodaj.getText(), jmbgDodaj.getText(), r);
						JOptionPane.showMessageDialog(dodajBtn, "Uspješno je dodat radnik.");
						
					}
					
					else if(tipDodajCombo.getSelectedItem().toString()=="Menadzer")
					{
					TipRadnogMjesta r1=TipRadnogMjesta.Menadzer;
					noviradnik.dodajRadnika(session, imeDodaj.getText(), prezimeDodaj.getText(), jmbgDodaj.getText(), r1);
					JOptionPane.showMessageDialog(dodajBtn, "Uspješno je dodat radnik.");
					}
					
					else if (tipDodajCombo.getSelectedItem().toString()=="Vozac") {
						TipRadnogMjesta r1 = TipRadnogMjesta.Vozac;
						noviradnik.dodajRadnika(session, imeDodaj.getText(), prezimeDodaj.getText(), jmbgDodaj.getText(), r1);
						JOptionPane.showMessageDialog(dodajBtn, "Uspješno je dodat radnik");
					}
					
					else
					{
					TipRadnogMjesta r2=TipRadnogMjesta.SalterskiRadnik;
					noviradnik.dodajRadnika(session, imeDodaj.getText(), prezimeDodaj.getText(), jmbgDodaj.getText(), r2);
					JOptionPane.showMessageDialog(dodajBtn, "Uspješno je dodat radnik.");
					}
				}
				catch(Exception e4)
				{
					JOptionPane.showMessageDialog(dodajBtn, "Dodavanje nije bilo uspješno.");
					JOptionPane.showMessageDialog(dodajBtn, "e4.");
				
					
					
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
		label.setBounds(113, 84, 22, 14);
		modifikujRadnikaTab.add(label);
		
		JLabel label_1 = new JLabel("Prezime:");
		label_1.setBounds(89, 122, 46, 14);
		modifikujRadnikaTab.add(label_1);
		
		prezimeModifikuj = new JTextField();
		prezimeModifikuj.setColumns(10);
		prezimeModifikuj.setBounds(161, 119, 183, 20);
		modifikujRadnikaTab.add(prezimeModifikuj);
		
		JLabel label_3 = new JLabel("Tip radnog mjesta:");
		label_3.setBounds(40, 167, 101, 14);
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
				}
				
				catch(Exception e6)
				{
					JOptionPane.showMessageDialog(modifikujBtn, "Neuspješno modifikovanje radnika.");
					JOptionPane.showMessageDialog(modifikujBtn, e6);
				}
			}
		});
		modifikujBtn.setBounds(271, 214, 109, 23);
		modifikujRadnikaTab.add(modifikujBtn);
		
		JLabel lblRadnik = new JLabel("JMBG radnika:");
		lblRadnik.setBounds(52, 36, 68, 14);
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
		label_4.setBounds(46, 36, 68, 14);
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
			try
			{
				Session session = HibernateUtil.getSessionFactory().openSession();
				HibernateRadnik brisanjeradnik= new HibernateRadnik();
				brisanjeradnik.brisiRadnika(session, jmbgIzbrisiPronadi.getText());
				JOptionPane.showMessageDialog(izbrisiBtn, "Uspješno brisanje.");
				
			}
			catch(Exception e7)
			{
				JOptionPane.showMessageDialog(izbrisiBtn, "Neuspješno brisanje.");
				JOptionPane.showMessageDialog(izbrisiBtn, e7);
			}
			}
		});
		izbrisiBtn.setBounds(285, 214, 89, 23);
		izbrisiRadnikaTab.add(izbrisiBtn);
		
		JLabel label_5 = new JLabel("Tip radnog mjesta:");
		label_5.setBounds(36, 165, 101, 14);
		izbrisiRadnikaTab.add(label_5);
		
		JLabel label_7 = new JLabel("Prezime:");
		label_7.setBounds(85, 122, 46, 14);
		izbrisiRadnikaTab.add(label_7);
		
		JLabel label_8 = new JLabel("Ime:");
		label_8.setBounds(107, 84, 22, 14);
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
