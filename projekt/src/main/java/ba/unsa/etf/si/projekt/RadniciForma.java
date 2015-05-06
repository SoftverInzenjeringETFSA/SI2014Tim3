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
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_10;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

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
		
		textField_1 = new JTextField();
		textField_1.setBounds(151, 64, 193, 20);
		dodajRadnikaTab.add(textField_1);
		textField_1.setColumns(10);
		
		textField = new JTextField();
		textField.setBounds(151, 26, 193, 20);
		dodajRadnikaTab.add(textField);
		textField.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(151, 102, 193, 20);
		dodajRadnikaTab.add(textField_2);
		textField_2.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(151, 146, 193, 20);
		dodajRadnikaTab.add(comboBox);
		
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
		
		JButton btnDodajRadnika = new JButton("Dodaj");
		btnDodajRadnika.setBounds(263, 207, 81, 23);
		dodajRadnikaTab.add(btnDodajRadnika);
		
		JPanel modifikujRadnikaTab = new JPanel();
		tabbedPane.addTab("Modifikuj", null, modifikujRadnikaTab, null);
		modifikujRadnikaTab.setLayout(null);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(161, 81, 183, 20);
		modifikujRadnikaTab.add(textField_3);
		
		JLabel label = new JLabel("Ime:");
		label.setBounds(113, 84, 22, 14);
		modifikujRadnikaTab.add(label);
		
		JLabel label_1 = new JLabel("Prezime:");
		label_1.setBounds(89, 122, 46, 14);
		modifikujRadnikaTab.add(label_1);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(161, 119, 183, 20);
		modifikujRadnikaTab.add(textField_4);
		
		JLabel label_3 = new JLabel("Tip radnog mjesta:");
		label_3.setBounds(40, 167, 101, 14);
		modifikujRadnikaTab.add(label_3);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(161, 164, 183, 20);
		modifikujRadnikaTab.add(comboBox_1);
		
		JButton btnModifikujRadnika = new JButton("Spasi promjene");
		btnModifikujRadnika.setBounds(271, 214, 109, 23);
		modifikujRadnikaTab.add(btnModifikujRadnika);
		
		JLabel lblRadnik = new JLabel("JMBG radnika:");
		lblRadnik.setBounds(52, 36, 68, 14);
		modifikujRadnikaTab.add(lblRadnik);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(130, 33, 151, 20);
		modifikujRadnikaTab.add(textField_10);
		
		JButton btnProna = new JButton("Pronađi");
		btnProna.setBounds(291, 32, 89, 23);
		modifikujRadnikaTab.add(btnProna);
		
		JPanel izbrisiRadnikaTab = new JPanel();
		tabbedPane.addTab("Izbriši", null, izbrisiRadnikaTab, null);
		izbrisiRadnikaTab.setLayout(null);
		
		JLabel label_4 = new JLabel("JMBG radnika:");
		label_4.setBounds(46, 36, 68, 14);
		izbrisiRadnikaTab.add(label_4);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(124, 33, 151, 20);
		izbrisiRadnikaTab.add(textField_6);
		
		JButton button = new JButton("Pronađi");
		button.setBounds(285, 32, 89, 23);
		izbrisiRadnikaTab.add(button);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(155, 81, 183, 20);
		izbrisiRadnikaTab.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(155, 119, 183, 20);
		izbrisiRadnikaTab.add(textField_8);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(155, 162, 183, 20);
		izbrisiRadnikaTab.add(comboBox_2);
		
		JButton btnIzbripi = new JButton("Izbriši");
		btnIzbripi.setBounds(285, 214, 89, 23);
		izbrisiRadnikaTab.add(btnIzbripi);
		
		JLabel label_5 = new JLabel("Tip radnog mjesta:");
		label_5.setBounds(36, 165, 101, 14);
		izbrisiRadnikaTab.add(label_5);
		
		JLabel label_7 = new JLabel("Prezime:");
		label_7.setBounds(85, 122, 46, 14);
		izbrisiRadnikaTab.add(label_7);
		
		JLabel label_8 = new JLabel("Ime:");
		label_8.setBounds(107, 84, 22, 14);
		izbrisiRadnikaTab.add(label_8);
		
		JButton btnOdjava = new JButton("Odjava");
		btnOdjava.setBounds(338, 11, 89, 23);
		frmEvidencijaRadnika.getContentPane().add(btnOdjava);
		
		JButton btnNazad = new JButton("Nazad");
		btnNazad.setBounds(239, 11, 89, 23);
		frmEvidencijaRadnika.getContentPane().add(btnNazad);
	}
}
