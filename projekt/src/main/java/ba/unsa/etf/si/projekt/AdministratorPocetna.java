package ba.unsa.etf.si.projekt;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import net.miginfocom.swing.MigLayout;

import javax.swing.BoxLayout;
import javax.swing.JSplitPane;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;

public class AdministratorPocetna {

	private JFrame frmAdministratorPoetna;

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
					AdministratorPocetna window = new AdministratorPocetna();
					window.frmAdministratorPoetna.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdministratorPocetna() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdministratorPoetna = new JFrame();
		frmAdministratorPoetna.setTitle("Administrator početna");
		frmAdministratorPoetna.setResizable(false);
		frmAdministratorPoetna.setBounds(100, 100, 480, 390);
		frmAdministratorPoetna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdministratorPoetna.getContentPane().setLayout(null);
		
		JLabel lblPrijavljeniSteKao = new JLabel("Dobrodošli!");
		lblPrijavljeniSteKao.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrijavljeniSteKao.setBounds(41, 17, 249, 23);
		frmAdministratorPoetna.getContentPane().add(lblPrijavljeniSteKao);
		
		JButton btnOdjava = new JButton("Odjava");
		btnOdjava.setBounds(333, 17, 89, 23);
		frmAdministratorPoetna.getContentPane().add(btnOdjava);
		
		JPanel panel = new JPanel();
		panel.setBounds(41, 94, 381, 256);
		frmAdministratorPoetna.getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 5));
		
		JButton btnIzvjetaji = new JButton("Izvještaji");
		panel.add(btnIzvjetaji);
		
		JButton btnEvidencijaRadnika = new JButton("Evidencija radnika");
		panel.add(btnEvidencijaRadnika);
		
		JButton btnEvidencijaAutobuskihLinija = new JButton("Evidencija autobuskih linija");
		panel.add(btnEvidencijaAutobuskihLinija);
		
		JButton btnEvidencijaAutobusa = new JButton("Evidencija autobusa");
		panel.add(btnEvidencijaAutobusa);
		
		JButton btnKorisnikiRauni = new JButton("Korisnički računi");
		panel.add(btnKorisnikiRauni);
		
		JLabel lblPrijavljeniSteKao_1 = new JLabel("Prijavljeni ste kao administrator!");
		lblPrijavljeniSteKao_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrijavljeniSteKao_1.setBounds(41, 47, 266, 23);
		frmAdministratorPoetna.getContentPane().add(lblPrijavljeniSteKao_1);
	}
}
