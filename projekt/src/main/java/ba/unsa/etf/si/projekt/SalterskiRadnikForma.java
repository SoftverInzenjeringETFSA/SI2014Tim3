package ba.unsa.etf.si.projekt;

import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;

import com.toedter.calendar.JDateChooser;

import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JList;

public class SalterskiRadnikForma {

	private JFrame frmalterskiRadnik;
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
	private JTextField textField_11;

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
					SalterskiRadnikForma window = new SalterskiRadnikForma();
					window.frmalterskiRadnik.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SalterskiRadnikForma() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmalterskiRadnik = new JFrame();
		frmalterskiRadnik.setTitle("Šalterski radnik");
		frmalterskiRadnik.setBounds(100, 100, 629, 495);
		frmalterskiRadnik.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmalterskiRadnik.getContentPane().setLayout(null);
		
		JButton button = new JButton("Odjava");
		button.setBounds(514, 11, 89, 23);
		frmalterskiRadnik.getContentPane().add(button);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 45, 593, 400);
		frmalterskiRadnik.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Prodaja", null, panel, null);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(112, 34, 117, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(226, 34, 28, 20);
		panel.add(comboBox);
		
		JLabel lblOdredite = new JLabel("Odredište:");
		lblOdredite.setBounds(51, 37, 51, 14);
		panel.add(lblOdredite);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(112, 81, 117, 20);
		panel.add(textField_1);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(226, 81, 28, 20);
		panel.add(comboBox_1);
		
		JLabel lblVrijeme = new JLabel("Vrijeme:");
		lblVrijeme.setBounds(63, 84, 39, 14);
		panel.add(lblVrijeme);
		
		JRadioButton rdbtnJednosmjerna = new JRadioButton("Jednosmjerna");
		rdbtnJednosmjerna.setSelected(true);
		rdbtnJednosmjerna.setBounds(112, 124, 109, 23);
		panel.add(rdbtnJednosmjerna);
		
		JRadioButton rdbtnPovratna = new JRadioButton("Povratna");
		rdbtnPovratna.setBounds(112, 151, 109, 23);
		panel.add(rdbtnPovratna);
		
		JLabel lblTipKarte = new JLabel("Tip karte:");
		lblTipKarte.setBounds(56, 128, 46, 14);
		panel.add(lblTipKarte);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd/M/yyy");
		dateChooser.setBounds(112, 189, 142, 20);
		panel.add(dateChooser);
		
		JLabel label = new JLabel("Datum polaska:");
		label.setBounds(28, 191, 74, 14);
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setName("");
		panel_1.setToolTipText("");
		panel_1.setBorder(BorderFactory.createTitledBorder("Međunarodni saobraćaj"));
		panel_1.setBounds(311, 27, 238, 143);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label_1 = new JLabel("Ime:");
		label_1.setBounds(23, 49, 22, 14);
		panel_1.add(label_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(62, 46, 160, 20);
		panel_1.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(62, 93, 160, 20);
		panel_1.add(textField_3);
		
		JLabel label_2 = new JLabel("Prezime:");
		label_2.setBounds(6, 96, 46, 14);
		panel_1.add(label_2);
		
		JLabel label_3 = new JLabel("30 KM");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 25));
		label_3.setBounds(150, 245, 104, 33);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Cijena:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 25));
		label_4.setBounds(63, 241, 77, 41);
		panel.add(label_4);
		
		JButton btnPregled = new JButton("Završi");
		btnPregled.setBounds(415, 314, 134, 33);
		panel.add(btnPregled);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Rezervacija", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel label_5 = new JLabel("Odredište:");
		label_5.setBounds(56, 36, 51, 14);
		panel_2.add(label_5);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(117, 33, 117, 20);
		panel_2.add(textField_4);
		
		JLabel label_6 = new JLabel("Vrijeme:");
		label_6.setBounds(68, 83, 39, 14);
		panel_2.add(label_6);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(117, 80, 117, 20);
		panel_2.add(textField_5);
		
		JLabel label_7 = new JLabel("Tip karte:");
		label_7.setBounds(61, 127, 46, 14);
		panel_2.add(label_7);
		
		JRadioButton radioButton = new JRadioButton("Jednosmjerna");
		radioButton.setSelected(true);
		radioButton.setBounds(117, 123, 109, 23);
		panel_2.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("Povratna");
		radioButton_1.setBounds(117, 150, 109, 23);
		panel_2.add(radioButton_1);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("dd/M/yyy");
		dateChooser_1.setBounds(117, 188, 142, 20);
		panel_2.add(dateChooser_1);
		
		JLabel label_8 = new JLabel("Datum polaska:");
		label_8.setBounds(33, 190, 74, 14);
		panel_2.add(label_8);
		
		JLabel label_9 = new JLabel("30 KM");
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 25));
		label_9.setBounds(155, 244, 104, 33);
		panel_2.add(label_9);
		
		JLabel label_10 = new JLabel("Cijena:");
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 25));
		label_10.setBounds(68, 240, 77, 41);
		panel_2.add(label_10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(BorderFactory.createTitledBorder("Međunarodni saobraćaj"));
		panel_3.setBounds(316, 26, 238, 143);
		panel_2.add(panel_3);
		
		JLabel label_12 = new JLabel("Ime:");
		label_12.setBounds(23, 49, 22, 14);
		panel_3.add(label_12);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(62, 46, 160, 20);
		panel_3.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(62, 93, 160, 20);
		panel_3.add(textField_7);
		
		JLabel label_13 = new JLabel("Prezime:");
		label_13.setBounds(6, 96, 46, 14);
		panel_3.add(label_13);
		
		JButton btnRezervii = new JButton("Rezerviši");
		btnRezervii.setBounds(420, 313, 134, 33);
		panel_2.add(btnRezervii);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(231, 33, 28, 20);
		panel_2.add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(231, 80, 28, 20);
		panel_2.add(comboBox_3);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Modifikacija i brisanje", null, panel_4, null);
		panel_4.setLayout(null);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(126, 30, 117, 20);
		panel_4.add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(126, 77, 117, 20);
		panel_4.add(textField_9);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setBounds(240, 77, 28, 20);
		panel_4.add(comboBox_4);
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setBounds(240, 30, 28, 20);
		panel_4.add(comboBox_5);
		
		JLabel label_14 = new JLabel("Odredište:");
		label_14.setBounds(65, 33, 51, 14);
		panel_4.add(label_14);
		
		JLabel label_15 = new JLabel("Vrijeme:");
		label_15.setBounds(77, 80, 39, 14);
		panel_4.add(label_15);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 149, 270, 212);
		panel_4.add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JLabel label_16 = new JLabel("Rezervacije");
		label_16.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_16.setBounds(23, 118, 105, 20);
		panel_4.add(label_16);
		
		JRadioButton radioButton_2 = new JRadioButton("Jednosmjerna");
		radioButton_2.setSelected(true);
		radioButton_2.setBounds(418, 30, 109, 23);
		panel_4.add(radioButton_2);
		
		JLabel label_17 = new JLabel("Tip karte:");
		label_17.setBounds(362, 34, 46, 14);
		panel_4.add(label_17);
		
		JRadioButton radioButton_3 = new JRadioButton("Povratna");
		radioButton_3.setBounds(418, 57, 109, 23);
		panel_4.add(radioButton_3);
		
		JDateChooser dateChooser_2 = new JDateChooser();
		dateChooser_2.setDateFormatString("dd/M/yyy");
		dateChooser_2.setBounds(418, 95, 142, 20);
		panel_4.add(dateChooser_2);
		
		JLabel label_18 = new JLabel("Datum polaska:");
		label_18.setBounds(334, 97, 74, 14);
		panel_4.add(label_18);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBorder(BorderFactory.createTitledBorder("Međunarodni saobraćaj"));
		panel_5.setBounds(334, 149, 238, 143);
		panel_4.add(panel_5);
		
		JLabel label_20 = new JLabel("Ime:");
		label_20.setBounds(23, 49, 22, 14);
		panel_5.add(label_20);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(62, 46, 160, 20);
		panel_5.add(textField_10);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(62, 93, 160, 20);
		panel_5.add(textField_11);
		
		JLabel label_21 = new JLabel("Prezime:");
		label_21.setBounds(6, 96, 46, 14);
		panel_5.add(label_21);
		
		JButton btnModifikuj = new JButton("Modifikuj");
		btnModifikuj.setBounds(474, 327, 98, 34);
		panel_4.add(btnModifikuj);
		
		JButton btnObrii = new JButton("Obriši");
		btnObrii.setBounds(334, 327, 98, 34);
		panel_4.add(btnObrii);
	}
}
