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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SalterskiRadnikForma {

	private JFrame frmalterskiRadnik;
	private JTextField odredisteProdaja;
	private JTextField vrijemeProdaja;
	private JTextField imeProdaja;
	private JTextField prezimeProdaja;
	private JTextField odredisteRezervacija;
	private JTextField vrijemeRezervacija;
	private JTextField imeRezervacija;
	private JTextField prezimeRezervacija;
	private JTextField odredisteModifikacija;
	private JTextField vrijemeModifikacija;
	private JTextField imeModifikacije;
	private JTextField prezimeModifikacije;

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
		frmalterskiRadnik.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmalterskiRadnik.getContentPane().setLayout(null);
		
		JButton button = new JButton("Odjava");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrijavaForma p = new PrijavaForma();
				p.setVisible(true);
				setVisible(false);
			}
		});
		button.setBounds(514, 11, 89, 23);
		frmalterskiRadnik.getContentPane().add(button);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 45, 593, 400);
		frmalterskiRadnik.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Prodaja", null, panel, null);
		panel.setLayout(null);
		
		odredisteProdaja = new JTextField();
		odredisteProdaja.setBounds(112, 34, 117, 20);
		panel.add(odredisteProdaja);
		odredisteProdaja.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(226, 34, 28, 20);
		panel.add(comboBox);
		
		JLabel lblOdredite = new JLabel("Odredište:");
		lblOdredite.setBounds(51, 37, 51, 14);
		panel.add(lblOdredite);
		
		vrijemeProdaja = new JTextField();
		vrijemeProdaja.setColumns(10);
		vrijemeProdaja.setBounds(112, 81, 117, 20);
		panel.add(vrijemeProdaja);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(226, 81, 28, 20);
		panel.add(comboBox_1);
		
		JLabel lblVrijeme = new JLabel("Vrijeme:");
		lblVrijeme.setBounds(63, 84, 39, 14);
		panel.add(lblVrijeme);
		
		JRadioButton jednosmjernaProdaja = new JRadioButton("Jednosmjerna");
		jednosmjernaProdaja.setSelected(true);
		jednosmjernaProdaja.setBounds(112, 124, 109, 23);
		panel.add(jednosmjernaProdaja);
		
		JRadioButton povratnaProdaja = new JRadioButton("Povratna");
		povratnaProdaja.setBounds(112, 151, 109, 23);
		panel.add(povratnaProdaja);
		
		JLabel lblTipKarte = new JLabel("Tip karte:");
		lblTipKarte.setBounds(56, 128, 46, 14);
		panel.add(lblTipKarte);
		
		JDateChooser datumProdajaDate = new JDateChooser();
		datumProdajaDate.setDateFormatString("dd/M/yyy");
		datumProdajaDate.setBounds(112, 189, 142, 20);
		panel.add(datumProdajaDate);
		
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
		
		imeProdaja = new JTextField();
		imeProdaja.setColumns(10);
		imeProdaja.setBounds(62, 46, 160, 20);
		panel_1.add(imeProdaja);
		
		prezimeProdaja = new JTextField();
		prezimeProdaja.setColumns(10);
		prezimeProdaja.setBounds(62, 93, 160, 20);
		panel_1.add(prezimeProdaja);
		
		JLabel label_2 = new JLabel("Prezime:");
		label_2.setBounds(6, 96, 46, 14);
		panel_1.add(label_2);
		
		JLabel cijenaProdajaLabel = new JLabel("30 KM");
		cijenaProdajaLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cijenaProdajaLabel.setBounds(150, 245, 104, 33);
		panel.add(cijenaProdajaLabel);
		
		JLabel label_4 = new JLabel("Cijena:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 25));
		label_4.setBounds(63, 241, 77, 41);
		panel.add(label_4);
		
		JButton prodajaBtn = new JButton("Završi");
		prodajaBtn.setBounds(415, 314, 134, 33);
		panel.add(prodajaBtn);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Rezervacija", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel label_5 = new JLabel("Odredište:");
		label_5.setBounds(56, 36, 51, 14);
		panel_2.add(label_5);
		
		odredisteRezervacija = new JTextField();
		odredisteRezervacija.setColumns(10);
		odredisteRezervacija.setBounds(117, 33, 117, 20);
		panel_2.add(odredisteRezervacija);
		
		JLabel label_6 = new JLabel("Vrijeme:");
		label_6.setBounds(68, 83, 39, 14);
		panel_2.add(label_6);
		
		vrijemeRezervacija = new JTextField();
		vrijemeRezervacija.setColumns(10);
		vrijemeRezervacija.setBounds(117, 80, 117, 20);
		panel_2.add(vrijemeRezervacija);
		
		JLabel label_7 = new JLabel("Tip karte:");
		label_7.setBounds(61, 127, 46, 14);
		panel_2.add(label_7);
		
		JRadioButton jednosmjernaRezervacija = new JRadioButton("Jednosmjerna");
		jednosmjernaRezervacija.setSelected(true);
		jednosmjernaRezervacija.setBounds(117, 123, 109, 23);
		panel_2.add(jednosmjernaRezervacija);
		
		JRadioButton povratnaRezervacija = new JRadioButton("Povratna");
		povratnaRezervacija.setBounds(117, 150, 109, 23);
		panel_2.add(povratnaRezervacija);
		
		JDateChooser datumRezervacijaDate = new JDateChooser();
		datumRezervacijaDate.setDateFormatString("dd/M/yyy");
		datumRezervacijaDate.setBounds(117, 188, 142, 20);
		panel_2.add(datumRezervacijaDate);
		
		JLabel label_8 = new JLabel("Datum polaska:");
		label_8.setBounds(33, 190, 74, 14);
		panel_2.add(label_8);
		
		JLabel cijenaRezervacija = new JLabel("30 KM");
		cijenaRezervacija.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cijenaRezervacija.setBounds(155, 244, 104, 33);
		panel_2.add(cijenaRezervacija);
		
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
		
		imeRezervacija = new JTextField();
		imeRezervacija.setColumns(10);
		imeRezervacija.setBounds(62, 46, 160, 20);
		panel_3.add(imeRezervacija);
		
		prezimeRezervacija = new JTextField();
		prezimeRezervacija.setColumns(10);
		prezimeRezervacija.setBounds(62, 93, 160, 20);
		panel_3.add(prezimeRezervacija);
		
		JLabel label_13 = new JLabel("Prezime:");
		label_13.setBounds(6, 96, 46, 14);
		panel_3.add(label_13);
		
		JButton rezervisiBtn = new JButton("Rezerviši");
		rezervisiBtn.setBounds(420, 313, 134, 33);
		panel_2.add(rezervisiBtn);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(231, 33, 28, 20);
		panel_2.add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(231, 80, 28, 20);
		panel_2.add(comboBox_3);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Modifikacija i brisanje", null, panel_4, null);
		panel_4.setLayout(null);
		
		odredisteModifikacija = new JTextField();
		odredisteModifikacija.setColumns(10);
		odredisteModifikacija.setBounds(126, 30, 117, 20);
		panel_4.add(odredisteModifikacija);
		
		vrijemeModifikacija = new JTextField();
		vrijemeModifikacija.setColumns(10);
		vrijemeModifikacija.setBounds(126, 77, 117, 20);
		panel_4.add(vrijemeModifikacija);
		
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
		
		JList rezervacijeList = new JList();
		scrollPane.setViewportView(rezervacijeList);
		
		JLabel label_16 = new JLabel("Rezervacije");
		label_16.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_16.setBounds(23, 118, 105, 20);
		panel_4.add(label_16);
		
		JRadioButton jednosmjernaModifikacije = new JRadioButton("Jednosmjerna");
		jednosmjernaModifikacije.setSelected(true);
		jednosmjernaModifikacije.setBounds(418, 30, 109, 23);
		panel_4.add(jednosmjernaModifikacije);
		
		JLabel label_17 = new JLabel("Tip karte:");
		label_17.setBounds(362, 34, 46, 14);
		panel_4.add(label_17);
		
		JRadioButton povratnaModifikacije = new JRadioButton("Povratna");
		povratnaModifikacije.setBounds(418, 57, 109, 23);
		panel_4.add(povratnaModifikacije);
		
		JDateChooser datumModifikacijeDate = new JDateChooser();
		datumModifikacijeDate.setDateFormatString("dd/M/yyy");
		datumModifikacijeDate.setBounds(418, 95, 142, 20);
		panel_4.add(datumModifikacijeDate);
		
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
		
		imeModifikacije = new JTextField();
		imeModifikacije.setColumns(10);
		imeModifikacije.setBounds(62, 46, 160, 20);
		panel_5.add(imeModifikacije);
		
		prezimeModifikacije = new JTextField();
		prezimeModifikacije.setColumns(10);
		prezimeModifikacije.setBounds(62, 93, 160, 20);
		panel_5.add(prezimeModifikacije);
		
		JLabel label_21 = new JLabel("Prezime:");
		label_21.setBounds(6, 96, 46, 14);
		panel_5.add(label_21);
		
		JButton modifikujBtn = new JButton("Modifikuj");
		modifikujBtn.setBounds(474, 327, 98, 34);
		panel_4.add(modifikujBtn);
		
		JButton obrisiBtn = new JButton("Obriši");
		obrisiBtn.setBounds(334, 327, 98, 34);
		panel_4.add(obrisiBtn);
	}
	
	public void setVisible(boolean visible) {
		frmalterskiRadnik.setVisible(visible);
	}
}
