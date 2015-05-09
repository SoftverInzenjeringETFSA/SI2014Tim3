package ba.unsa.etf.si.projekt.forme;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenadzerPocetna {

	private JFrame frmMenaderPoetna;

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
					MenadzerPocetna window = new MenadzerPocetna();
					window.frmMenaderPoetna.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MenadzerPocetna() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMenaderPoetna = new JFrame();
		frmMenaderPoetna.setTitle("Menadžer početna");
		frmMenaderPoetna.setResizable(false);
		frmMenaderPoetna.setBounds(100, 100, 472, 342);
		frmMenaderPoetna.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmMenaderPoetna.getContentPane().setLayout(null);
		
		JLabel lblPrijavljeniSteKao = new JLabel("Dobrodošli!");
		lblPrijavljeniSteKao.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrijavljeniSteKao.setBounds(41, 17, 249, 23);
		frmMenaderPoetna.getContentPane().add(lblPrijavljeniSteKao);
		
		JButton odjavaBtn = new JButton("Odjava");
		odjavaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrijavaForma p = new PrijavaForma();
				p.setVisible(true);
				setVisible(false);
			}
		});
		odjavaBtn.setBounds(333, 17, 89, 23);
		frmMenaderPoetna.getContentPane().add(odjavaBtn);
		
		JPanel panel = new JPanel();
		panel.setBounds(41, 94, 381, 208);
		frmMenaderPoetna.getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 5));
		
		JButton izvjestajiBtn = new JButton("Izvještaji");
		
		// Klik na dugme za pravljenje izvjestaja
		izvjestajiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IzvjestajiForma i = new IzvjestajiForma("menadjer");
				i.setVisible(true);
				setVisible(false);
			}
		});
		panel.add(izvjestajiBtn);
		
		JButton radniciBtn = new JButton("Evidencija radnika");
		
		// Klik na dugme za evidenciju radnika
		radniciBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RadniciForma i = new RadniciForma("menadjer");
				i.setVisible(true);
				setVisible(false);
			}
		});
		panel.add(radniciBtn);
		
		JButton linijeBtn = new JButton("Evidencija autobuskih linija");
		
		// Klik na dugme za evidenciju autobuskih linija
		linijeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AutobuskeLinijeForma i = new AutobuskeLinijeForma("menadjer");
				i.setVisible(true);
				setVisible(false);
			}
		});
		panel.add(linijeBtn);
		
		JButton autobusiBtn = new JButton("Evidencija autobusa");
		
		// Klik na dugme za evidenciju autobusa
		autobusiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AutobusiForma i = new AutobusiForma("menadjer");
				i.setVisible(true);
				setVisible(false);
			}
		});
		panel.add(autobusiBtn);
		
		JButton naloziBtn = new JButton("Pravljenje naloga");
		
		// Klik na dugme za pravljenje naloga
		naloziBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NaloziForma i = new NaloziForma("menadjer");
				i.setVisible(true);
				setVisible(false);
			}
		});
		panel.add(naloziBtn);
		
		JLabel lblPrijavljeniSteKao_1 = new JLabel("Prijavljeni ste kao menadžer!");
		lblPrijavljeniSteKao_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrijavljeniSteKao_1.setBounds(41, 47, 266, 23);
		frmMenaderPoetna.getContentPane().add(lblPrijavljeniSteKao_1);
	}
	
	public void setVisible(boolean visible) {
		frmMenaderPoetna.setVisible(visible);
	}
}
