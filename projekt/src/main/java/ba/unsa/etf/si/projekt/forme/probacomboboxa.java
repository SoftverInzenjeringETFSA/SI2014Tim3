package ba.unsa.etf.si.projekt.forme;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JComboBox;

import org.hibernate.Session;

import ba.unsa.etf.si.projekt.dodatno.Java2sAutoComboBox;
import ba.unsa.etf.si.projekt.hibernate.HibernateAutibuskaLinija;
import ba.unsa.etf.si.projekt.hibernate.HibernateUtil;

public class probacomboboxa {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					probacomboboxa window = new probacomboboxa();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public probacomboboxa() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		JComboBox comboBox = new Java2sAutoComboBox(HibernateAutibuskaLinija.sveLinije(session));
		comboBox.setBounds(94, 78, 188, 20);
		frame.getContentPane().add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(85, 130, 259, 20);
		frame.getContentPane().add(comboBox_1);
	}
}
