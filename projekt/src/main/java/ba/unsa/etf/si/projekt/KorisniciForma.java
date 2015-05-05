package ba.unsa.etf.si.projekt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.UIManager;

public class KorisniciForma {

	private JFrame frmKorisnikiRauni;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;

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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmKorisnikiRauni = new JFrame();
		frmKorisnikiRauni.setTitle("Korisnički računi");
		frmKorisnikiRauni.setBounds(100, 100, 533, 469);
		frmKorisnikiRauni.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmKorisnikiRauni.getContentPane().setLayout(null);
		
		JButton button = new JButton("Nazad");
		button.setBounds(319, 11, 89, 23);
		frmKorisnikiRauni.getContentPane().add(button);
		
		JButton button_1 = new JButton("Odjava");
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
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(186, 26, 193, 20);
		panel.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(186, 64, 193, 20);
		panel.add(textField_1);
		
		JLabel label_1 = new JLabel("Prezime:");
		label_1.setBounds(114, 67, 46, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("JMBG:");
		label_2.setBounds(128, 105, 34, 14);
		panel.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(186, 102, 193, 20);
		panel.add(textField_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(186, 146, 193, 20);
		panel.add(comboBox);
		
		JLabel label_3 = new JLabel("Tip radnog mjesta:");
		label_3.setBounds(67, 149, 101, 14);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Korisničko ime:");
		label_4.setBounds(90, 191, 70, 14);
		panel.add(label_4);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(186, 188, 193, 20);
		panel.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(186, 226, 193, 20);
		panel.add(textField_4);
		
		JLabel label_5 = new JLabel("Šifra:");
		label_5.setBounds(132, 229, 34, 14);
		panel.add(label_5);
		
		JButton btnDodajNovogKorisnika = new JButton("Dodaj novog korisnika");
		btnDodajNovogKorisnika.setBounds(242, 297, 137, 23);
		panel.add(btnDodajNovogKorisnika);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Modifikuj", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblKorisnikoIme = new JLabel("Korisničko ime:");
		lblKorisnikoIme.setBounds(67, 33, 70, 14);
		panel_1.add(lblKorisnikoIme);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(147, 30, 151, 20);
		panel_1.add(textField_5);
		
		JButton button_3 = new JButton("Pronađi");
		button_3.setBounds(308, 29, 89, 23);
		panel_1.add(button_3);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(168, 78, 193, 20);
		panel_1.add(textField_6);
		
		JLabel label_7 = new JLabel("Ime:");
		label_7.setBounds(130, 81, 22, 14);
		panel_1.add(label_7);
		
		JLabel label_8 = new JLabel("Prezime:");
		label_8.setBounds(106, 119, 46, 14);
		panel_1.add(label_8);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(168, 116, 193, 20);
		panel_1.add(textField_7);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(168, 161, 193, 20);
		panel_1.add(comboBox_1);
		
		JLabel label_9 = new JLabel("Tip radnog mjesta:");
		label_9.setBounds(57, 164, 101, 14);
		panel_1.add(label_9);
		
		JButton btnModifikujKorisnika = new JButton("Modifikuj korisnika");
		btnModifikujKorisnika.setBounds(278, 297, 119, 23);
		panel_1.add(btnModifikujKorisnika);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(168, 205, 193, 20);
		panel_1.add(textField_8);
		
		JLabel label_6 = new JLabel("Korisničko ime:");
		label_6.setBounds(72, 208, 70, 14);
		panel_1.add(label_6);
		
		JLabel label_10 = new JLabel("Šifra:");
		label_10.setBounds(114, 246, 34, 14);
		panel_1.add(label_10);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(168, 243, 193, 20);
		panel_1.add(textField_9);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Izbriši", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel label_11 = new JLabel("Korisničko ime:");
		label_11.setBounds(105, 60, 70, 14);
		panel_2.add(label_11);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(185, 57, 151, 20);
		panel_2.add(textField_10);
		
		JButton btnIzbriiKorisnika = new JButton("Izbriši korisnika");
		btnIzbriiKorisnika.setBounds(219, 115, 112, 23);
		panel_2.add(btnIzbriiKorisnika);
	}

}
