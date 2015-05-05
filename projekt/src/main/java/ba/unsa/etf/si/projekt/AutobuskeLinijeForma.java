package ba.unsa.etf.si.projekt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.UIManager;

import com.toedter.calendar.JDateChooser;

import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

public class AutobuskeLinijeForma {

	private JFrame frmDodavanjeAutobuskeLinije;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;

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
					
					AutobuskeLinijeForma window = new AutobuskeLinijeForma();
					window.frmDodavanjeAutobuskeLinije.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AutobuskeLinijeForma() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDodavanjeAutobuskeLinije = new JFrame();
		frmDodavanjeAutobuskeLinije.setTitle("Autobuske linije");
		frmDodavanjeAutobuskeLinije.setBounds(100, 100, 551, 506);
		frmDodavanjeAutobuskeLinije.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDodavanjeAutobuskeLinije.getContentPane().setLayout(null);
		
		JButton button = new JButton("Odjava");
		button.setBounds(433, 11, 89, 23);
		frmDodavanjeAutobuskeLinije.getContentPane().add(button);
		
		JButton button_1 = new JButton("Nazad");
		button_1.setBounds(334, 11, 89, 23);
		frmDodavanjeAutobuskeLinije.getContentPane().add(button_1);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(20, 64, 502, 400);
		frmDodavanjeAutobuskeLinije.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Dodaj", null, panel, null);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(109, 29, 116, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblPolazite = new JLabel("Polazište:");
		lblPolazite.setBounds(53, 32, 46, 14);
		panel.add(lblPolazite);
		
		textField_1 = new JTextField();
		textField_1.setBounds(109, 68, 116, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblOdredite = new JLabel("Odredište:");
		lblOdredite.setBounds(48, 71, 51, 14);
		panel.add(lblOdredite);
		
		JLabel lblRegistracije = new JLabel("Autobus:");
		lblRegistracije.setBounds(53, 112, 46, 14);
		panel.add(lblRegistracije);
		
		textField_3 = new JTextField();
		textField_3.setBounds(109, 236, 116, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(109, 193, 116, 20);
		panel.add(dateChooser);
		
		JLabel lblImeVozaa = new JLabel("Vozač:");
		lblImeVozaa.setBounds(67, 154, 32, 14);
		panel.add(lblImeVozaa);
		
		JLabel lblDatumPolaska = new JLabel("Datum polaska:");
		lblDatumPolaska.setBounds(21, 195, 78, 14);
		panel.add(lblDatumPolaska);
		
		JLabel lblVrijemePolaska = new JLabel("Vrijeme polaska:");
		lblVrijemePolaska.setBounds(21, 239, 78, 14);
		panel.add(lblVrijemePolaska);
		
		JLabel lblPeron = new JLabel("Peron:");
		lblPeron.setBounds(294, 32, 32, 14);
		panel.add(lblPeron);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(336, 29, 116, 20);
		panel.add(spinner);
		
		JLabel lblDistanca = new JLabel("Distanca:");
		lblDistanca.setBounds(280, 71, 46, 14);
		panel.add(lblDistanca);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(336, 68, 116, 20);
		panel.add(spinner_1);
		
		JLabel lblKm = new JLabel("km");
		lblKm.setBounds(463, 71, 46, 14);
		panel.add(lblKm);
		
		textField_6 = new JTextField();
		textField_6.setBounds(336, 109, 116, 20);
		panel.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblTrajanje = new JLabel("Trajanje:");
		lblTrajanje.setBounds(280, 112, 46, 14);
		panel.add(lblTrajanje);
		
		JLabel lblH = new JLabel("h");
		lblH.setBounds(463, 112, 46, 14);
		panel.add(lblH);
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setBounds(336, 151, 116, 20);
		panel.add(spinner_2);
		
		JLabel lblBrojLinije = new JLabel("Broj linije:");
		lblBrojLinije.setBounds(275, 154, 51, 14);
		panel.add(lblBrojLinije);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(336, 193, 116, 20);
		panel.add(textField_7);
		
		JLabel lblCijenaJednosmjerne = new JLabel("Jednosmjerna:");
		lblCijenaJednosmjerne.setBounds(255, 196, 71, 14);
		panel.add(lblCijenaJednosmjerne);
		
		JLabel lblKm_1 = new JLabel("KM");
		lblKm_1.setBounds(463, 196, 46, 14);
		panel.add(lblKm_1);
		
		JLabel lblDvosmjerna = new JLabel("Dvosmjerna:");
		lblDvosmjerna.setBounds(265, 239, 71, 14);
		panel.add(lblDvosmjerna);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(336, 236, 116, 20);
		panel.add(textField_8);
		
		JLabel label_1 = new JLabel("KM");
		label_1.setBounds(463, 239, 46, 14);
		panel.add(label_1);
		
		JRadioButton rdbtnMeunarodna = new JRadioButton("Međunarodna");
		rdbtnMeunarodna.setBounds(108, 282, 109, 23);
		panel.add(rdbtnMeunarodna);
		
		JButton btnNewButton = new JButton("Dodaj");
		btnNewButton.setBounds(398, 338, 89, 23);
		panel.add(btnNewButton);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(109, 109, 116, 20);
		panel.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(109, 151, 116, 20);
		panel.add(comboBox_1);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Modifikuj", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("Broj linije:");
		label.setBounds(115, 28, 51, 14);
		panel_1.add(label);
		
		JSpinner spinner_3 = new JSpinner();
		spinner_3.setBounds(176, 25, 116, 20);
		panel_1.add(spinner_3);
		
		JButton btnPretrai = new JButton("Pronađi");
		btnPretrai.setBounds(304, 24, 89, 23);
		panel_1.add(btnPretrai);
		
		JLabel label_2 = new JLabel("Polazište:");
		label_2.setBounds(42, 82, 46, 14);
		panel_1.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(98, 79, 116, 20);
		panel_1.add(textField_2);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(98, 118, 116, 20);
		panel_1.add(textField_4);
		
		JLabel label_3 = new JLabel("Odredište:");
		label_3.setBounds(37, 121, 51, 14);
		panel_1.add(label_3);
		
		JLabel label_4 = new JLabel("Autobus:");
		label_4.setBounds(42, 162, 46, 14);
		panel_1.add(label_4);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(98, 159, 116, 20);
		panel_1.add(comboBox_2);
		
		JLabel label_5 = new JLabel("Vozač:");
		label_5.setBounds(56, 204, 32, 14);
		panel_1.add(label_5);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(98, 201, 116, 20);
		panel_1.add(comboBox_3);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(98, 243, 116, 20);
		panel_1.add(dateChooser_1);
		
		JLabel label_6 = new JLabel("Datum polaska:");
		label_6.setBounds(10, 245, 78, 14);
		panel_1.add(label_6);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(98, 286, 116, 20);
		panel_1.add(textField_5);
		
		JLabel label_7 = new JLabel("Vrijeme polaska:");
		label_7.setBounds(10, 289, 78, 14);
		panel_1.add(label_7);
		
		JRadioButton radioButton = new JRadioButton("Međunarodna");
		radioButton.setBounds(332, 285, 109, 23);
		panel_1.add(radioButton);
		
		JLabel label_8 = new JLabel("Dvosmjerna:");
		label_8.setBounds(254, 246, 71, 14);
		panel_1.add(label_8);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(325, 243, 116, 20);
		panel_1.add(textField_9);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(325, 200, 116, 20);
		panel_1.add(textField_10);
		
		JLabel label_9 = new JLabel("Jednosmjerna:");
		label_9.setBounds(244, 203, 71, 14);
		panel_1.add(label_9);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(325, 159, 116, 20);
		panel_1.add(textField_11);
		
		JLabel label_11 = new JLabel("Trajanje:");
		label_11.setBounds(269, 162, 46, 14);
		panel_1.add(label_11);
		
		JSpinner spinner_5 = new JSpinner();
		spinner_5.setBounds(325, 118, 116, 20);
		panel_1.add(spinner_5);
		
		JLabel label_12 = new JLabel("Distanca:");
		label_12.setBounds(269, 121, 46, 14);
		panel_1.add(label_12);
		
		JSpinner spinner_6 = new JSpinner();
		spinner_6.setBounds(325, 79, 116, 20);
		panel_1.add(spinner_6);
		
		JLabel label_13 = new JLabel("Peron:");
		label_13.setBounds(283, 82, 32, 14);
		panel_1.add(label_13);
		
		JButton btnSpasiIzmjene = new JButton("Spasi izmjene");
		btnSpasiIzmjene.setBounds(378, 338, 109, 23);
		panel_1.add(btnSpasiIzmjene);
		
		JLabel label_14 = new JLabel("KM");
		label_14.setBounds(451, 204, 46, 14);
		panel_1.add(label_14);
		
		JLabel label_15 = new JLabel("KM");
		label_15.setBounds(451, 249, 46, 14);
		panel_1.add(label_15);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Izbriši", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel label_10 = new JLabel("Broj linije:");
		label_10.setBounds(115, 30, 51, 14);
		panel_2.add(label_10);
		
		JSpinner spinner_4 = new JSpinner();
		spinner_4.setBounds(176, 27, 116, 20);
		panel_2.add(spinner_4);
		
		JButton button_2 = new JButton("Pronađi");
		button_2.setBounds(304, 26, 89, 23);
		panel_2.add(button_2);
		
		JSpinner spinner_7 = new JSpinner();
		spinner_7.setBounds(325, 81, 116, 20);
		panel_2.add(spinner_7);
		
		JSpinner spinner_8 = new JSpinner();
		spinner_8.setBounds(325, 120, 116, 20);
		panel_2.add(spinner_8);
		
		textField_12 = new JTextField();
		textField_12.setColumns(10);
		textField_12.setBounds(325, 161, 116, 20);
		panel_2.add(textField_12);
		
		textField_13 = new JTextField();
		textField_13.setColumns(10);
		textField_13.setBounds(325, 202, 116, 20);
		panel_2.add(textField_13);
		
		textField_14 = new JTextField();
		textField_14.setColumns(10);
		textField_14.setBounds(325, 245, 116, 20);
		panel_2.add(textField_14);
		
		JRadioButton radioButton_1 = new JRadioButton("Međunarodna");
		radioButton_1.setBounds(332, 287, 109, 23);
		panel_2.add(radioButton_1);
		
		JLabel label_16 = new JLabel("KM");
		label_16.setBounds(451, 206, 46, 14);
		panel_2.add(label_16);
		
		JLabel label_17 = new JLabel("KM");
		label_17.setBounds(451, 251, 46, 14);
		panel_2.add(label_17);
		
		JLabel label_18 = new JLabel("Peron:");
		label_18.setBounds(283, 84, 32, 14);
		panel_2.add(label_18);
		
		JLabel label_19 = new JLabel("Distanca:");
		label_19.setBounds(269, 123, 46, 14);
		panel_2.add(label_19);
		
		JLabel label_20 = new JLabel("Trajanje:");
		label_20.setBounds(269, 164, 46, 14);
		panel_2.add(label_20);
		
		JLabel label_21 = new JLabel("Jednosmjerna:");
		label_21.setBounds(244, 205, 71, 14);
		panel_2.add(label_21);
		
		JLabel label_22 = new JLabel("Dvosmjerna:");
		label_22.setBounds(254, 248, 71, 14);
		panel_2.add(label_22);
		
		JLabel label_23 = new JLabel("Polazište:");
		label_23.setBounds(42, 84, 46, 14);
		panel_2.add(label_23);
		
		textField_15 = new JTextField();
		textField_15.setColumns(10);
		textField_15.setBounds(98, 81, 116, 20);
		panel_2.add(textField_15);
		
		textField_16 = new JTextField();
		textField_16.setColumns(10);
		textField_16.setBounds(98, 120, 116, 20);
		panel_2.add(textField_16);
		
		JLabel label_24 = new JLabel("Odredište:");
		label_24.setBounds(37, 123, 51, 14);
		panel_2.add(label_24);
		
		JLabel label_25 = new JLabel("Autobus:");
		label_25.setBounds(42, 164, 46, 14);
		panel_2.add(label_25);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setBounds(98, 161, 116, 20);
		panel_2.add(comboBox_4);
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setBounds(98, 203, 116, 20);
		panel_2.add(comboBox_5);
		
		JLabel label_26 = new JLabel("Vozač:");
		label_26.setBounds(56, 206, 32, 14);
		panel_2.add(label_26);
		
		JLabel label_27 = new JLabel("Datum polaska:");
		label_27.setBounds(10, 247, 78, 14);
		panel_2.add(label_27);
		
		JDateChooser dateChooser_2 = new JDateChooser();
		dateChooser_2.setBounds(98, 245, 116, 20);
		panel_2.add(dateChooser_2);
		
		textField_17 = new JTextField();
		textField_17.setColumns(10);
		textField_17.setBounds(98, 288, 116, 20);
		panel_2.add(textField_17);
		
		JLabel label_28 = new JLabel("Vrijeme polaska:");
		label_28.setBounds(10, 291, 78, 14);
		panel_2.add(label_28);
		
		JButton btnIzbrii = new JButton("Izbriši");
		btnIzbrii.setBounds(398, 338, 89, 23);
		panel_2.add(btnIzbrii);
	}
}
