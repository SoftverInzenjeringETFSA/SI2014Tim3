package ba.unsa.etf.si.projekt.forme;

import java.awt.EventQueue;

import javassist.expr.NewArray;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.UIManager;










import org.hibernate.Session;

import ba.unsa.etf.si.projekt.hibernate.HibernateKorisnickiRacuni;
import ba.unsa.etf.si.projekt.hibernate.HibernateRadnik;
import ba.unsa.etf.si.projekt.entiteti.KorisnickiRacun;
import ba.unsa.etf.si.projekt.entiteti.Radnik;
import ba.unsa.etf.si.projekt.entiteti.TipKorisnickogRacuna;
import ba.unsa.etf.si.projekt.entiteti.TipRadnogMjesta;
import ba.unsa.etf.si.projekt.hibernate.HibernateUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class KorisniciForma {

	private JFrame frmKorisnikiRauni;
	private JTextField korisnickoDodaj;
	private JPasswordField sifraDodaj;
	private JTextField pronadiModifikuj;
	private JTextField korisnickoModifikuj;
	private JPasswordField sifraModifikuj;
	private JTextField korisnickoIzbrisi;
	private String tipKorisnika;
	private JComboBox tipModifikuj;
	private JComboBox radnikDodajCombo;

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
		frmKorisnikiRauni.setIconImage(Toolkit.getDefaultToolkit().getImage(KorisniciForma.class.getResource("/ba/unsa/etf/si/projekt/dodatno/icon.png")));
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
		lblRadnik.setHorizontalAlignment(SwingConstants.TRAILING);
		lblRadnik.setBounds(65, 49, 111, 14);
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
		
		JLabel lblTipKorisnikogRauna = new JLabel("Tip korisničkog računa:");
		lblTipKorisnikogRauna.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTipKorisnikogRauna.setBounds(10, 94, 166, 14);
		panel.add(lblTipKorisnikogRauna);
		
		JLabel label_4 = new JLabel("Korisničko ime:");
		label_4.setHorizontalAlignment(SwingConstants.TRAILING);
		label_4.setBounds(56, 136, 120, 14);
		panel.add(label_4);
		
		korisnickoDodaj = new JTextField();
		korisnickoDodaj.setColumns(10);
		korisnickoDodaj.setBounds(186, 133, 193, 20);
		panel.add(korisnickoDodaj);
		
		sifraDodaj = new JPasswordField();
		sifraDodaj.setColumns(10);
		sifraDodaj.setBounds(186, 171, 193, 20);
		panel.add(sifraDodaj);
		
		JLabel label_5 = new JLabel("Šifra:");
		label_5.setHorizontalAlignment(SwingConstants.TRAILING);
		label_5.setBounds(65, 174, 111, 14);
		panel.add(label_5);
		
		
		final JButton dodajBtn = new JButton("Dodaj korisnika");
		
		dodajBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {//dodavanje korisnika
				
				try
				{
					Session session = HibernateUtil.getSessionFactory().openSession();
					TipKorisnickogRacuna r = null;
					boolean postoji=false;
					
					if(tipDodajCombo.getSelectedItem().toString()=="administrator")
					{
				     r=TipKorisnickogRacuna.administrator;
					}
					if(tipDodajCombo.getSelectedItem().toString()=="menadzer")
					{
				     r=TipKorisnickogRacuna.menadzer;
					}
					if(tipDodajCombo.getSelectedItem().toString()=="salterskiRadnik")
					{
				     r=TipKorisnickogRacuna.salterskiRadnik;
					}
					
					java.util.List racuni=HibernateKorisnickiRacuni.sviRacuni(session);
					for(int i=0;i<racuni.size();i++)
					{
						KorisnickiRacun k=(KorisnickiRacun)racuni.get(i);
						if(korisnickoDodaj.getText().equals(k.getKorisnickoIme())) 
							postoji=true;
						
					}
					
					if(postoji==false)
					{
					HibernateKorisnickiRacuni.dodajKorisnickiRacun(session,(Radnik) radnikDodajCombo.getSelectedItem(),(TipKorisnickogRacuna) tipDodajCombo.getSelectedItem(), korisnickoDodaj.getText(), sifraDodaj.getText());
					
					JOptionPane.showMessageDialog(dodajBtn, "Uspješno ste dodali korisnički račun.");
					}
					else 
					{
						JOptionPane.showMessageDialog(dodajBtn, "Morate unijeti drugo korisničko ime.");
					}
					
					radnikDodajCombo.setSelectedIndex(0);
					korisnickoDodaj.setText("");
					sifraDodaj.setText("");
						
				}
				catch(Exception e7)
				{
					JOptionPane.showMessageDialog(dodajBtn, "Neuspješno kreiranje korisničkog računa.");
					JOptionPane.showMessageDialog(dodajBtn, e7);
				}
					
			
			}
		});
		
		
		
		dodajBtn.setBounds(244, 227, 135, 23);
		panel.add(dodajBtn);
		
	    radnikDodajCombo = new JComboBox();
		radnikDodajCombo.setBounds(186, 46, 193, 20);
		panel.add(radnikDodajCombo);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Modifikuj", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblKorisnikoIme = new JLabel("Korisničko ime:");
		lblKorisnikoIme.setHorizontalAlignment(SwingConstants.TRAILING);
		lblKorisnikoIme.setBounds(25, 33, 112, 14);
		panel_1.add(lblKorisnikoIme);
		
		pronadiModifikuj = new JTextField();
		pronadiModifikuj.setColumns(10);
		pronadiModifikuj.setBounds(147, 30, 151, 20);
		panel_1.add(pronadiModifikuj);
		
		final JButton pronadiBtn = new JButton("Pronađi");
		
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
						tipModifikuj.setSelectedItem(kr.getTipKorisnickogRacuna());
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
		
		pronadiBtn.setBounds(308, 29, 89, 23);
		panel_1.add(pronadiBtn);
		TipKorisnickogRacuna k4=TipKorisnickogRacuna.administrator;
		TipKorisnickogRacuna k5=TipKorisnickogRacuna.menadzer;
		TipKorisnickogRacuna k6=TipKorisnickogRacuna.salterskiRadnik;
		
		final JButton modifikujBtn = new JButton("Modifikuj korisnika");
		
		
		
		modifikujBtn.setBounds(233, 220, 144, 23);
		panel_1.add(modifikujBtn);
		
		korisnickoModifikuj = new JTextField();
		korisnickoModifikuj.setColumns(10);
		korisnickoModifikuj.setBounds(184, 128, 193, 20);
		panel_1.add(korisnickoModifikuj);
		
		JLabel label_6 = new JLabel("Korisničko ime:");
		label_6.setHorizontalAlignment(SwingConstants.TRAILING);
		label_6.setBounds(79, 131, 96, 14);
		panel_1.add(label_6);
		
		JLabel label_10 = new JLabel("Šifra:");
		label_10.setHorizontalAlignment(SwingConstants.TRAILING);
		label_10.setBounds(115, 169, 60, 14);
		panel_1.add(label_10);
		
		sifraModifikuj = new JPasswordField();
		sifraModifikuj.setColumns(10);
		sifraModifikuj.setBounds(184, 166, 193, 20);
		panel_1.add(sifraModifikuj);
		
		tipModifikuj = new JComboBox();
		tipModifikuj.setBounds(185, 82, 193, 20);
		panel_1.add(tipModifikuj);
		
		JLabel label = new JLabel("Tip korisničkog računa:");
		label.setHorizontalAlignment(SwingConstants.TRAILING);
		label.setBounds(10, 85, 165, 14);
		panel_1.add(label);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Izbriši", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel label_11 = new JLabel("Korisničko ime:");
		label_11.setHorizontalAlignment(SwingConstants.TRAILING);
		label_11.setBounds(63, 60, 112, 14);
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
		izbrisiBtn.setBounds(198, 115, 133, 23);
		panel_2.add(izbrisiBtn);
		
	
	modifikujBtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {  //modifikovanje korisnickih racuna
			try
			{
				Session session = HibernateUtil.getSessionFactory().openSession();
				
				TipKorisnickogRacuna r = null;
				java.util.List sviracuni=HibernateKorisnickiRacuni.sviRacuni(session);
				boolean postoji=false;
				String staroIme = "";
				for(int i=0;i<sviracuni.size();i++)
				{
					KorisnickiRacun ra=(KorisnickiRacun)sviracuni.get(i);
					if(ra.getKorisnickoIme().equals(pronadiModifikuj.getText()))
					{
						postoji=true;
						staroIme = ra.getKorisnickoIme();
					}
						
				}
				if(tipModifikuj.getSelectedItem().toString()=="administrator")
				{
			     r=TipKorisnickogRacuna.administrator;
			    }
				else if(tipModifikuj.getSelectedItem().toString()=="menadzer")
				{
			     r=TipKorisnickogRacuna.menadzer;
			    }
				else if(tipModifikuj.getSelectedItem().toString()=="salterskiRadnik")
				{
			     r=TipKorisnickogRacuna.salterskiRadnik;
			   }
				
				if(postoji==false || korisnickoModifikuj.getText()=="" || sifraModifikuj.getText()=="" )
				{
			
				JOptionPane.showMessageDialog(modifikujBtn, "Neuspjesna modifikovanje korisničkog računa.");
				}
				 if(korisnickoModifikuj.getText()!="" && postoji==true  && sifraModifikuj.getText()!="" )
				{	
					
						boolean postojiIme=false;
						for(int i=0;i<sviracuni.size();i++)
						{
							KorisnickiRacun ra=(KorisnickiRacun)sviracuni.get(i);
							if(ra.getKorisnickoIme().equals(korisnickoModifikuj.getText()) && !staroIme.equals(korisnickoModifikuj.getText()))
								postojiIme=true;
								
						}
					 if(postojiIme){
						 JOptionPane.showMessageDialog(modifikujBtn, "Korisnicko ime vec postoji!"); 
						 
						 
					 }
					 
					 else{
					 HibernateKorisnickiRacuni.modifikujKorisnickiRacun(session, pronadiModifikuj.getText(), korisnickoModifikuj.getText(), sifraModifikuj.getText(), r);
					JOptionPane.showMessageDialog(modifikujBtn, "Uspjesna ste modifikovali korisnički račun.");}
				}
				

				tipModifikuj.setSelectedIndex(0);
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
	
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Radnik> sviRadnici = HibernateRadnik.sviRadnici(session);
		for (Radnik radnik : sviRadnici) {
			radnikDodajCombo.addItem(radnik);
		}
		
		tipModifikuj.addItem(TipKorisnickogRacuna.administrator);
		tipModifikuj.addItem(TipKorisnickogRacuna.menadzer);
		tipModifikuj.addItem(TipKorisnickogRacuna.salterskiRadnik);
	
	}
	
	
	public void setVisible(boolean visible) {
		frmKorisnikiRauni.setVisible(visible);
	}
}
