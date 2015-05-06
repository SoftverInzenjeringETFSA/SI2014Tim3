package ba.unsa.etf.si.projekt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.UIManager;

import java.awt.Font;

import javax.swing.JButton;

public class PrijavaForma {

	private JFrame frmPrijava;
	private JTextField ime;
	private JPasswordField sifra;

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
					PrijavaForma window = new PrijavaForma();
					window.frmPrijava.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PrijavaForma() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPrijava = new JFrame();
		frmPrijava.setTitle("Prijava");
		frmPrijava.setBounds(100, 100, 353, 264);
		frmPrijava.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrijava.getContentPane().setLayout(null);
		
		ime = new JTextField();
		ime.setBounds(165, 91, 118, 20);
		frmPrijava.getContentPane().add(ime);
		ime.setColumns(10);
		
		sifra = new JPasswordField();
		sifra.setBounds(165, 137, 118, 20);
		frmPrijava.getContentPane().add(sifra);
		
		JLabel lblNewLabel = new JLabel("Korisničko ime:");
		lblNewLabel.setBounds(71, 94, 70, 14);
		frmPrijava.getContentPane().add(lblNewLabel);
		
		JLabel lblifra = new JLabel("Šifra:");
		lblifra.setBounds(115, 140, 26, 14);
		frmPrijava.getContentPane().add(lblifra);
		
		JLabel lblDobrodoli = new JLabel("Dobrodošli!");
		lblDobrodoli.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDobrodoli.setBounds(115, 28, 102, 25);
		frmPrijava.getContentPane().add(lblDobrodoli);
		
		JButton prijavaBtn = new JButton("Prijava");
		prijavaBtn.setBounds(194, 179, 89, 23);
		frmPrijava.getContentPane().add(prijavaBtn);
	}
}
