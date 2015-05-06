package ba.unsa.etf.si.projekt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.UIManager;

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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEvidencijaRadnika = new JFrame();
		frmEvidencijaRadnika.setTitle("Evidencija radnika");
		frmEvidencijaRadnika.setBounds(100, 100, 479, 373);
		frmEvidencijaRadnika.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		JComboBox tipDodajCombo = new JComboBox();
		tipDodajCombo.setBounds(151, 146, 193, 20);
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
		
		JButton dodajBtn = new JButton("Dodaj");
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
		
		JComboBox tipModifikujCombo = new JComboBox();
		tipModifikujCombo.setBounds(161, 164, 183, 20);
		modifikujRadnikaTab.add(tipModifikujCombo);
		
		JButton modifikujBtn = new JButton("Spasi promjene");
		modifikujBtn.setBounds(271, 214, 109, 23);
		modifikujRadnikaTab.add(modifikujBtn);
		
		JLabel lblRadnik = new JLabel("JMBG radnika:");
		lblRadnik.setBounds(52, 36, 68, 14);
		modifikujRadnikaTab.add(lblRadnik);
		
		jmbgModifikujPronadji = new JTextField();
		jmbgModifikujPronadji.setColumns(10);
		jmbgModifikujPronadji.setBounds(130, 33, 151, 20);
		modifikujRadnikaTab.add(jmbgModifikujPronadji);
		
		JButton pronadjiModifikujBtn = new JButton("Pronađi");
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
		
		JButton pronadiIzbrisiBtn = new JButton("Pronađi");
		pronadiIzbrisiBtn.setBounds(285, 32, 89, 23);
		izbrisiRadnikaTab.add(pronadiIzbrisiBtn);
		
		imeIzbrisi = new JTextField();
		imeIzbrisi.setColumns(10);
		imeIzbrisi.setBounds(155, 81, 183, 20);
		izbrisiRadnikaTab.add(imeIzbrisi);
		
		prezimeIzbrisi = new JTextField();
		prezimeIzbrisi.setColumns(10);
		prezimeIzbrisi.setBounds(155, 119, 183, 20);
		izbrisiRadnikaTab.add(prezimeIzbrisi);
		
		JComboBox tipIzbrisiCombo = new JComboBox();
		tipIzbrisiCombo.setBounds(155, 162, 183, 20);
		izbrisiRadnikaTab.add(tipIzbrisiCombo);
		
		JButton izbrisiBtn = new JButton("Izbriši");
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
		odjavaBtn.setBounds(338, 11, 89, 23);
		frmEvidencijaRadnika.getContentPane().add(odjavaBtn);
		
		JButton nazadBtn = new JButton("Nazad");
		nazadBtn.setBounds(239, 11, 89, 23);
		frmEvidencijaRadnika.getContentPane().add(nazadBtn);
	}
}
