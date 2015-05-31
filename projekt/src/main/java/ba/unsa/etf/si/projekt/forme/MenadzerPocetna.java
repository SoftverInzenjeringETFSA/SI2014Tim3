package ba.unsa.etf.si.projekt.forme;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import ba.unsa.etf.si.projekt.entiteti.KorisnickiRacun;
import ba.unsa.etf.si.projekt.hibernate.HibernateKorisnickiRacuni;
import ba.unsa.etf.si.projekt.hibernate.HibernateUtil;

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
import java.awt.Toolkit;

public class MenadzerPocetna {
	final static Logger logger = Logger.getLogger(MenadzerPocetna.class);
	private KorisnickiRacun korisnik;
	private JFrame frmMenaderPoetna;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MenadzerPocetna(KorisnickiRacun kr) {
		korisnik = kr;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMenaderPoetna = new JFrame();
		frmMenaderPoetna.setIconImage(Toolkit.getDefaultToolkit().getImage(MenadzerPocetna.class.getResource("/ba/unsa/etf/si/projekt/dodatno/icon.png")));
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
				Session session = HibernateUtil.getSessionFactory().openSession();
				
				HibernateKorisnickiRacuni.modifikujKorisnickiRacun(session, korisnik.getKorisnickoIme(), korisnik.getKorisnickoIme(), 
						korisnik.getSifra(), korisnik.getTipKorisnickogRacuna(), false);

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
				IzvjestajiForma i = new IzvjestajiForma(korisnik);
				i.setVisible(true);
				setVisible(false);
			}
		});
		panel.add(izvjestajiBtn);
		
		JButton radniciBtn = new JButton("Evidencija radnika");
		
		// Klik na dugme za evidenciju radnika
		radniciBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RadniciForma i = new RadniciForma(korisnik);
				i.setVisible(true);
				setVisible(false);
			}
		});
		panel.add(radniciBtn);
		
		JButton linijeBtn = new JButton("Evidencija autobuskih linija");
		
		// Klik na dugme za evidenciju autobuskih linija
		linijeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AutobuskeLinijeForma i = new AutobuskeLinijeForma(korisnik);
				i.setVisible(true);
				setVisible(false);
			}
		});
		panel.add(linijeBtn);
		
		JButton autobusiBtn = new JButton("Evidencija autobusa");
		
		// Klik na dugme za evidenciju autobusa
		autobusiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AutobusiForma i = new AutobusiForma(korisnik);
				i.setVisible(true);
				setVisible(false);
			}
		});
		panel.add(autobusiBtn);
		
		JButton naloziBtn = new JButton("Pravljenje naloga");
		
		// Klik na dugme za pravljenje naloga
		naloziBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NaloziForma i = new NaloziForma(korisnik);
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
