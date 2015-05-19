package ba.unsa.etf.si.projekt.forme;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.UIManager;





import org.hibernate.Session;

import ba.unsa.etf.si.projekt.hibernate.HibernateKorisnickiRacuni;
import ba.unsa.etf.si.projekt.entiteti.KorisnickiRacun;
import ba.unsa.etf.si.projekt.entiteti.TipKorisnickogRacuna;
import ba.unsa.etf.si.projekt.entiteti.TipRadnogMjesta;
import ba.unsa.etf.si.projekt.hibernate.HibernateUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class KorisniciForma {

	private JFrame frmKorisnikiRauni;
	private JTextField korisnickoDodaj;
	private JTextField sifraDodaj;
	private JTextField pronadiModifikuj;
	private JTextField korisnickoModifikuj;
	private JTextField sifraModifikuj;
	private JTextField korisnickoIzbrisi;
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
					KorisniciForma window = new KorisniciForma();
					window.frmKorisnikiRauni.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public KorisniciForma() {
		initialize();
	}

	public KorisniciForma(String tipKorisnika) {
		// TODO Auto-generated constructor stub
		this.tipKorisnika = tipKorisnika;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmKorisnikiRauni = new JFrame();
		frmKorisnikiRauni.setTitle("Korisnički računi");
		frmKorisnikiRauni.setBounds(100, 100, 533, 412);
		frmKorisnikiRauni.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmKorisnikiRauni.getContentPane().setLayout(null);
		
		JButton button = new JButton("Nazad");
		button.addActionListener(new ActionListener() {
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
		button.setBounds(319, 11, 89, 23);
		frmKorisnikiRauni.getContentPane().add(button);
		
		JButton button_1 = new JButton("Odjava");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrijavaForma p = new PrijavaForma();
				p.setVisible(true);
				setVisible(false);
			}
		});
		button_1.setBounds(418, 11, 89, 23);
		frmKorisnikiRauni.getContentPane().add(button_1);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(25, 60, 465, 297);
		frmKorisnikiRauni.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Dodaj", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblRadnik = new JLabel("Radnik:");
		lblRadnik.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRadnik.setBounds(55, 49, 111, 14);
		panel.add(lblRadnik);
		
		final JComboBox tipDodajCombo = new JComboBox();
		TipKorisnickogRacuna k=TipKorisnickogRacuna.administrator;
		tipDodajCombo.addItem(k);
		TipKorisnickogRacuna k1=TipKorisnickogRacuna.menadzer;
		tipDodajCombo.addItem(k1);
		TipKorisnickogRacuna k2=TipKorisnickogRacuna.salterskiRadnik;
		tipDodajCombo.addItem(k2);
		
		tipDodajCombo.setBounds(186, 91, 193, 20);
		
		panel.add(tipDodajCombo);
		
		JLabel label_3 = new JLabel("Tip radnog mjesta:");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(40, 94, 128, 14);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Korisničko ime:");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setBounds(40, 136, 120, 14);
		panel.add(label_4);
		
		korisnickoDodaj = new JTextField();
		korisnickoDodaj.setColumns(10);
		korisnickoDodaj.setBounds(186, 133, 193, 20);
		panel.add(korisnickoDodaj);
		
		sifraDodaj = new JTextField();
		sifraDodaj.setColumns(10);
		sifraDodaj.setBounds(186, 171, 193, 20);
		panel.add(sifraDodaj);
		
		JLabel label_5 = new JLabel("Šifra:");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setBounds(55, 174, 111, 14);
		panel.add(label_5);
		
		
		final JButton dodajBtn = new JButton("Dodaj novog korisnika");
		/*
		dodajBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {//dodavanje korisnika
				
				try
				{
					Session session = HibernateUtil.getSessionFactory().openSession();
					HibernateKorisnickiRacuni noviracun=new HibernateKorisnickiRacuni();
					if(tipDodajCombo.getSelectedItem().toString()=="administrator")
					{
				    TipKorisnickogRacuna r=TipKorisnickogRacuna.administrator;
					noviracun.dodajKorisnickiRacun(session, imeDodaj.getText(), prezimeDodaj.getText(), jmbgDodaj.getText(), korisnickoDodaj.getText(), sifraDodaj.getText(),r);
					JOptionPane.showMessageDialog(dodajBtn, "Uspješno ste dodali korisnički račun.");
					}
					if(tipDodajCombo.getSelectedItem().toString()=="menadzer")
					{
				    TipKorisnickogRacuna r=TipKorisnickogRacuna.menadzer;
					noviracun.dodajKorisnickiRacun(session, imeDodaj.getText(), prezimeDodaj.getText(), jmbgDodaj.getText(), korisnickoDodaj.getText(), sifraDodaj.getText(),r);
					JOptionPane.showMessageDialog(dodajBtn, "Uspješno ste dodali korisnički račun.");
					}
					if(tipDodajCombo.getSelectedItem().toString()=="salterskiRadnik")
					{
				    TipKorisnickogRacuna r=TipKorisnickogRacuna.salterskiRadnik;
					noviracun.dodajKorisnickiRacun(session, imeDodaj.getText(), prezimeDodaj.getText(), jmbgDodaj.getText(), korisnickoDodaj.getText(), sifraDodaj.getText(),r);
					JOptionPane.showMessageDialog(dodajBtn, "Uspješno ste dodali korisnički račun.");
					}
					imeDodaj.setText("");
					prezimeDodaj.setText("");
					korisnickoDodaj.setText("");
					sifraDodaj.setText("");
					jmbgDodaj.setText("");
					
				}
				catch(Exception e7)
				{
					JOptionPane.showMessageDialog(dodajBtn, "Neuspješno kreiranje korisničkog računa.");
					JOptionPane.showMessageDialog(dodajBtn, e7);
				}
					
			
			}
		});
		
		*/
		dodajBtn.setBounds(242, 227, 137, 23);
		panel.add(dodajBtn);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(186, 46, 193, 20);
		panel.add(comboBox);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Modifikuj", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblKorisnikoIme = new JLabel("Korisničko ime:");
		lblKorisnikoIme.setBounds(67, 33, 70, 14);
		panel_1.add(lblKorisnikoIme);
		
		pronadiModifikuj = new JTextField();
		pronadiModifikuj.setColumns(10);
		pronadiModifikuj.setBounds(147, 30, 151, 20);
		panel_1.add(pronadiModifikuj);
		
		final JButton pronadiBtn = new JButton("Pronađi");
		/*
		pronadiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { //pronalazenje korisnika za modifikovanje
				try
				{
					Session session = HibernateUtil.getSessionFactory().openSession();
					HibernateKorisnickiRacuni pronadjeniracun=new HibernateKorisnickiRacuni();
					KorisnickiRacun kr=new KorisnickiRacun();
					kr=pronadjeniracun.nadjiKorisnickiRacun(session,pronadiModifikuj.getText());
					if(kr!=null)
					{
						imeModifikuj.setText(kr.getIme());
						prezimeModifikuj.setText(kr.getPrezime());
						korisnickoModifikuj.setText(kr.getKorisnickoIme());
						sifraModifikuj.setText(kr.getSifra());
					}
					else
					{
						JOptionPane.showMessageDialog(pronadiBtn, "Ne postoji korisnički račun, sa unesenim korisničkim imenom.");
					}
					
					
				}
				catch(Exception e8)
				{
					JOptionPane.showMessageDialog(pronadiBtn, "Neuspješna pretraga korisničkih računa.");
				}
				
			}
		});
		*/
		pronadiBtn.setBounds(308, 29, 89, 23);
		panel_1.add(pronadiBtn);
		TipKorisnickogRacuna k4=TipKorisnickogRacuna.administrator;
		TipKorisnickogRacuna k5=TipKorisnickogRacuna.menadzer;
		TipKorisnickogRacuna k6=TipKorisnickogRacuna.salterskiRadnik;
		
		final JButton modifikujBtn = new JButton("Modifikuj korisnika");
		/*
		modifikujBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {  //modifikovanje korisnickih racuna
				try
				{
					Session session = HibernateUtil.getSessionFactory().openSession();
					HibernateKorisnickiRacuni promjenaracun=new HibernateKorisnickiRacuni();
					if(tipModifikuj.getSelectedItem().toString()=="administrator")
					{
				    TipKorisnickogRacuna r=TipKorisnickogRacuna.administrator;
				    promjenaracun.modifikujKorisnickiRacun(session, imeModifikuj.getText(),pronadiModifikuj.getText(), prezimeModifikuj.getText(), korisnickoModifikuj.getText(), sifraModifikuj.getText(),r);
					JOptionPane.showMessageDialog(modifikujBtn, "Uspješno ste dodali korisnički račun.");
					}
					if(tipModifikuj.getSelectedItem().toString()=="menadzer")
					{
				    TipKorisnickogRacuna r=TipKorisnickogRacuna.menadzer;
				    promjenaracun.modifikujKorisnickiRacun(session,imeModifikuj.getText(),pronadiModifikuj.getText(), prezimeModifikuj.getText(), korisnickoModifikuj.getText(), sifraModifikuj.getText(),r);
					JOptionPane.showMessageDialog(modifikujBtn, "Uspješno ste dodali korisnički račun.");
					}
					if(tipModifikuj.getSelectedItem().toString()=="salterskiRadnik")
					{
				    TipKorisnickogRacuna r=TipKorisnickogRacuna.salterskiRadnik;
				    promjenaracun.modifikujKorisnickiRacun(session, imeModifikuj.getText(),pronadiModifikuj.getText(), prezimeModifikuj.getText(), korisnickoModifikuj.getText(), sifraModifikuj.getText(),r);
					JOptionPane.showMessageDialog(modifikujBtn, "Uspješno ste dodali korisnički račun.");
					}
					imeModifikuj.setText("");
					prezimeModifikuj.setText("");
					korisnickoModifikuj.setText("");
					sifraModifikuj.setText("");
					pronadiModifikuj.setText("");
				}
				catch(Exception e9)
				{
					JOptionPane.showMessageDialog(modifikujBtn, "Neuspješno modifikovanje korisničkih računa.");
					JOptionPane.showMessageDialog(modifikujBtn, e9);
				}
				
			}
		});
		*/
		modifikujBtn.setBounds(273, 177, 119, 23);
		panel_1.add(modifikujBtn);
		
		korisnickoModifikuj = new JTextField();
		korisnickoModifikuj.setColumns(10);
		korisnickoModifikuj.setBounds(163, 85, 193, 20);
		panel_1.add(korisnickoModifikuj);
		
		JLabel label_6 = new JLabel("Korisničko ime:");
		label_6.setBounds(67, 88, 70, 14);
		panel_1.add(label_6);
		
		JLabel label_10 = new JLabel("Šifra:");
		label_10.setBounds(109, 126, 34, 14);
		panel_1.add(label_10);
		
		sifraModifikuj = new JTextField();
		sifraModifikuj.setColumns(10);
		sifraModifikuj.setBounds(163, 123, 193, 20);
		panel_1.add(sifraModifikuj);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Izbriši", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel label_11 = new JLabel("Korisničko ime:");
		label_11.setBounds(105, 60, 70, 14);
		panel_2.add(label_11);
		
		korisnickoIzbrisi = new JTextField();
		korisnickoIzbrisi.setColumns(10);
		korisnickoIzbrisi.setBounds(185, 57, 151, 20);
		panel_2.add(korisnickoIzbrisi);
		
		final JButton izbrisiBtn = new JButton("Izbriši korisnika");
		izbrisiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//brisanje korisnickog racuna
				
				try
				{
					Session session = HibernateUtil.getSessionFactory().openSession();
					HibernateKorisnickiRacuni brisanjeracun=new HibernateKorisnickiRacuni();
					brisanjeracun.brisiKorisnickiRacun(session, korisnickoIzbrisi.getText());
					JOptionPane.showMessageDialog(izbrisiBtn, "Uspješno brisanje korisničkog računa");
					
					korisnickoIzbrisi.setText("");
				}
				catch(Exception e10)
				{
					JOptionPane.showMessageDialog(izbrisiBtn, "Neuspješno brisanje korisničkog računa");
					JOptionPane.showMessageDialog(izbrisiBtn, e10);
				}
			}
		});
		izbrisiBtn.setBounds(219, 115, 112, 23);
		panel_2.add(izbrisiBtn);
	}
	public void setVisible(boolean visible) {
		frmKorisnikiRauni.setVisible(visible);
	}
}
