package ba.unsa.etf.si.projekt.forme;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class KorisniciForma {

	private JFrame frmKorisnikiRauni;
	private JTextField imeDodaj;
	private JTextField prezimeDodaj;
	private JTextField jmbgDodaj;
	private JTextField korisnickoDodaj;
	private JTextField sifraDodaj;
	private JTextField pronadiModifikuj;
	private JTextField imeModifikuj;
	private JTextField prezimeModifikuj;
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
		frmKorisnikiRauni.setBounds(100, 100, 533, 469);
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
		tabbedPane.setBounds(25, 60, 465, 359);
		frmKorisnikiRauni.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Dodaj", null, panel, null);
		panel.setLayout(null);
		
		JLabel label = new JLabel("Ime:");
		label.setBounds(138, 29, 22, 14);
		panel.add(label);
		
		imeDodaj = new JTextField();
		imeDodaj.setColumns(10);
		imeDodaj.setBounds(186, 26, 193, 20);
		panel.add(imeDodaj);
		
		prezimeDodaj = new JTextField();
		prezimeDodaj.setColumns(10);
		prezimeDodaj.setBounds(186, 64, 193, 20);
		panel.add(prezimeDodaj);
		
		JLabel label_1 = new JLabel("Prezime:");
		label_1.setBounds(114, 67, 46, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("JMBG:");
		label_2.setBounds(128, 105, 34, 14);
		panel.add(label_2);
		
		jmbgDodaj = new JTextField();
		jmbgDodaj.setColumns(10);
		jmbgDodaj.setBounds(186, 102, 193, 20);
		panel.add(jmbgDodaj);
		
		JComboBox tipDodajCombo = new JComboBox();
		tipDodajCombo.setBounds(186, 146, 193, 20);
		panel.add(tipDodajCombo);
		
		JLabel label_3 = new JLabel("Tip radnog mjesta:");
		label_3.setBounds(67, 149, 101, 14);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Korisničko ime:");
		label_4.setBounds(90, 191, 70, 14);
		panel.add(label_4);
		
		korisnickoDodaj = new JTextField();
		korisnickoDodaj.setColumns(10);
		korisnickoDodaj.setBounds(186, 188, 193, 20);
		panel.add(korisnickoDodaj);
		
		sifraDodaj = new JTextField();
		sifraDodaj.setColumns(10);
		sifraDodaj.setBounds(186, 226, 193, 20);
		panel.add(sifraDodaj);
		
		JLabel label_5 = new JLabel("Šifra:");
		label_5.setBounds(132, 229, 34, 14);
		panel.add(label_5);
		
		JButton dodajBtn = new JButton("Dodaj novog korisnika");
		dodajBtn.setBounds(242, 297, 137, 23);
		panel.add(dodajBtn);
		
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
		
		JButton pronadiBtn = new JButton("Pronađi");
		pronadiBtn.setBounds(308, 29, 89, 23);
		panel_1.add(pronadiBtn);
		
		imeModifikuj = new JTextField();
		imeModifikuj.setColumns(10);
		imeModifikuj.setBounds(168, 78, 193, 20);
		panel_1.add(imeModifikuj);
		
		JLabel label_7 = new JLabel("Ime:");
		label_7.setBounds(130, 81, 22, 14);
		panel_1.add(label_7);
		
		JLabel label_8 = new JLabel("Prezime:");
		label_8.setBounds(106, 119, 46, 14);
		panel_1.add(label_8);
		
		prezimeModifikuj = new JTextField();
		prezimeModifikuj.setColumns(10);
		prezimeModifikuj.setBounds(168, 116, 193, 20);
		panel_1.add(prezimeModifikuj);
		
		JComboBox tipModifikuj = new JComboBox();
		tipModifikuj.setBounds(168, 161, 193, 20);
		panel_1.add(tipModifikuj);
		
		JLabel label_9 = new JLabel("Tip radnog mjesta:");
		label_9.setBounds(57, 164, 101, 14);
		panel_1.add(label_9);
		
		JButton modifikujBtn = new JButton("Modifikuj korisnika");
		modifikujBtn.setBounds(278, 297, 119, 23);
		panel_1.add(modifikujBtn);
		
		korisnickoModifikuj = new JTextField();
		korisnickoModifikuj.setColumns(10);
		korisnickoModifikuj.setBounds(168, 205, 193, 20);
		panel_1.add(korisnickoModifikuj);
		
		JLabel label_6 = new JLabel("Korisničko ime:");
		label_6.setBounds(72, 208, 70, 14);
		panel_1.add(label_6);
		
		JLabel label_10 = new JLabel("Šifra:");
		label_10.setBounds(114, 246, 34, 14);
		panel_1.add(label_10);
		
		sifraModifikuj = new JTextField();
		sifraModifikuj.setColumns(10);
		sifraModifikuj.setBounds(168, 243, 193, 20);
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
		
		JButton izbrisiBtn = new JButton("Izbriši korisnika");
		izbrisiBtn.setBounds(219, 115, 112, 23);
		panel_2.add(izbrisiBtn);
	}
	public void setVisible(boolean visible) {
		frmKorisnikiRauni.setVisible(visible);
	}
}
