package ba.unsa.etf.si.projekt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.UIManager;

import java.awt.Font;

import javax.swing.AbstractListModel;

public class AutobusiForma {

	private JFrame frmEvidencijaAutobusa;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

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
					AutobusiForma window = new AutobusiForma();
					window.frmEvidencijaAutobusa.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AutobusiForma() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEvidencijaAutobusa = new JFrame();
		frmEvidencijaAutobusa.setTitle("Evidencija autobusa");
		frmEvidencijaAutobusa.setBounds(100, 100, 557, 434);
		frmEvidencijaAutobusa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEvidencijaAutobusa.getContentPane().setLayout(null);
		
		JButton button = new JButton("Odjava");
		button.setBounds(428, 11, 89, 23);
		frmEvidencijaAutobusa.getContentPane().add(button);
		
		JButton button_1 = new JButton("Nazad");
		button_1.setBounds(329, 11, 89, 23);
		frmEvidencijaAutobusa.getContentPane().add(button_1);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(23, 53, 494, 331);
		frmEvidencijaAutobusa.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Dodaj", null, panel, null);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(199, 71, 155, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblModel = new JLabel("Model:");
		lblModel.setBounds(157, 74, 32, 14);
		panel.add(lblModel);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(199, 107, 155, 20);
		panel.add(spinner);
		
		JLabel lblKapacitet = new JLabel("Kapacitet:");
		lblKapacitet.setBounds(140, 110, 49, 14);
		panel.add(lblKapacitet);
		
		textField_1 = new JTextField();
		textField_1.setBounds(199, 140, 155, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblRegistracije = new JLabel("Registracije:");
		lblRegistracije.setBounds(129, 143, 60, 14);
		panel.add(lblRegistracije);
		
		JButton btnUnesi = new JButton("Unesi");
		btnUnesi.setBounds(265, 200, 89, 23);
		panel.add(btnUnesi);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Modifikuj", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 47, 238, 245);
		panel_1.add(scrollPane);
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Mercedes xyz A00-A-000 Kapacitet 20", "Mercedes xyz A00-A-000 Kapacitet 20", "Mercedes xyz A00-A-000 Kapacitet 20", "Mercedes xyz A00-A-000 Kapacitet 20"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane.setViewportView(list);
		
		JLabel lblAutobusi = new JLabel("Autobusi:");
		lblAutobusi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAutobusi.setBounds(10, 22, 72, 19);
		panel_1.add(lblAutobusi);
		
		JLabel label = new JLabel("Model:");
		label.setBounds(282, 50, 32, 14);
		panel_1.add(label);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(324, 47, 155, 20);
		panel_1.add(textField_2);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(324, 83, 155, 20);
		panel_1.add(spinner_1);
		
		JLabel label_1 = new JLabel("Kapacitet:");
		label_1.setBounds(265, 86, 49, 14);
		panel_1.add(label_1);
		
		JLabel label_2 = new JLabel("Registracije:");
		label_2.setBounds(254, 119, 60, 14);
		panel_1.add(label_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(324, 116, 155, 20);
		panel_1.add(textField_3);
		
		JButton btnIzmijeni = new JButton("Izmijeni");
		btnIzmijeni.setBounds(390, 269, 89, 23);
		panel_1.add(btnIzmijeni);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Izbriši", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel label_3 = new JLabel("Model:");
		label_3.setBounds(144, 101, 32, 14);
		panel_2.add(label_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(186, 98, 155, 20);
		panel_2.add(textField_4);
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setBounds(186, 134, 155, 20);
		panel_2.add(spinner_2);
		
		JLabel label_4 = new JLabel("Kapacitet:");
		label_4.setBounds(127, 137, 49, 14);
		panel_2.add(label_4);
		
		JLabel label_5 = new JLabel("Registracije:");
		label_5.setBounds(116, 170, 60, 14);
		panel_2.add(label_5);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(186, 167, 155, 20);
		panel_2.add(textField_5);
		
		JButton btnIzbrii = new JButton("Izbriši");
		btnIzbrii.setBounds(252, 220, 89, 23);
		panel_2.add(btnIzbrii);
		
		JLabel label_6 = new JLabel("Registracije:");
		label_6.setBounds(85, 46, 60, 14);
		panel_2.add(label_6);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(155, 43, 155, 20);
		panel_2.add(textField_6);
		
		JButton btnPronai = new JButton("Pronađi");
		btnPronai.setBounds(320, 42, 89, 23);
		panel_2.add(btnPronai);
	}

}
