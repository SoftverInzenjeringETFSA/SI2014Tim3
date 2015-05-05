package ba.unsa.etf.si.projekt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.UIManager;

import com.toedter.calendar.JDateChooser;

import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;

public class IzvjestajiForma {

	private JFrame frmIzvjetaji;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_7;

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
					IzvjestajiForma window = new IzvjestajiForma();
					window.frmIzvjetaji.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public IzvjestajiForma() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIzvjetaji = new JFrame();
		frmIzvjetaji.setTitle("Izvještaji");
		frmIzvjetaji.setBounds(100, 100, 502, 393);
		frmIzvjetaji.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmIzvjetaji.getContentPane().setLayout(null);
		
		JButton button = new JButton("Nazad");
		button.setBounds(282, 11, 89, 23);
		frmIzvjetaji.getContentPane().add(button);
		
		JButton button_1 = new JButton("Odjava");
		button_1.setBounds(387, 11, 89, 23);
		frmIzvjetaji.getContentPane().add(button_1);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(21, 73, 438, 270);
		frmIzvjetaji.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Prodane karte", null, panel, null);
		panel.setLayout(null);
		
		JDateChooser dateChooser_2 = new JDateChooser();
		dateChooser_2.setDateFormatString("dd/MM/yyyy");
		dateChooser_2.setBounds(175, 50, 178, 20);
		panel.add(dateChooser_2);
		
		JLabel label_8 = new JLabel("Početni datum:");
		label_8.setBounds(93, 53, 72, 14);
		panel.add(label_8);
		
		JLabel label_9 = new JLabel("Krajnji datum:");
		label_9.setBounds(94, 100, 72, 14);
		panel.add(label_9);
		
		JDateChooser dateChooser_3 = new JDateChooser();
		dateChooser_3.setDateFormatString("dd/MM/yyyy");
		dateChooser_3.setBounds(176, 97, 178, 20);
		panel.add(dateChooser_3);
		
		JButton button_3 = new JButton("Generiši izvještaj");
		button_3.setBounds(241, 208, 113, 23);
		panel.add(button_3);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Autobuske linije", null, panel_1, null);
		panel_1.setLayout(null);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.setBounds(171, 11, 178, 20);
		panel_1.add(dateChooser);
		
		JLabel label = new JLabel("Početni datum:");
		label.setBounds(89, 14, 72, 14);
		panel_1.add(label);
		
		JLabel label_1 = new JLabel("Krajnji datum:");
		label_1.setBounds(90, 61, 72, 14);
		panel_1.add(label_1);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("dd/MM/yyyy");
		dateChooser_1.setBounds(172, 58, 178, 20);
		panel_1.add(dateChooser_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(171, 103, 67, 20);
		panel_1.add(textField);
		
		JLabel label_2 = new JLabel("Početno vrijeme:");
		label_2.setBounds(72, 106, 89, 14);
		panel_1.add(label_2);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(270, 103, 61, 20);
		panel_1.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(270, 149, 61, 20);
		panel_1.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(171, 149, 67, 20);
		panel_1.add(textField_3);
		
		JLabel label_3 = new JLabel("Krajnje vrijeme:");
		label_3.setBounds(79, 152, 82, 14);
		panel_1.add(label_3);
		
		JLabel label_4 = new JLabel("h");
		label_4.setBounds(248, 152, 6, 14);
		panel_1.add(label_4);
		
		JLabel label_5 = new JLabel("h");
		label_5.setBounds(248, 106, 6, 14);
		panel_1.add(label_5);
		
		JLabel label_6 = new JLabel("m");
		label_6.setBounds(341, 106, 8, 14);
		panel_1.add(label_6);
		
		JLabel label_7 = new JLabel("m");
		label_7.setBounds(341, 152, 8, 14);
		panel_1.add(label_7);
		
		JButton button_2 = new JButton("Generiši izvještaj");
		button_2.setBounds(236, 208, 113, 23);
		panel_1.add(button_2);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Vozači", null, panel_2, null);
		panel_2.setLayout(null);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(204, 67, 113, 20);
		panel_2.add(textField_4);
		
		JLabel lblImeVozaa = new JLabel("Ime vozača:");
		lblImeVozaa.setBounds(135, 70, 59, 14);
		panel_2.add(lblImeVozaa);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(204, 113, 113, 20);
		panel_2.add(textField_7);
		
		JLabel lblPrezimeVozaa = new JLabel("Prezime vozača:");
		lblPrezimeVozaa.setBounds(116, 116, 78, 14);
		panel_2.add(lblPrezimeVozaa);
		
		JButton button_4 = new JButton("Generiši izvještaj");
		button_4.setBounds(204, 208, 113, 23);
		panel_2.add(button_4);
	}
}
