package ba.unsa.etf.si.projekt.forme;

import org.apache.log4j.Logger;

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

import javax.swing.BoxLayout;
import javax.swing.JSplitPane;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import ba.unsa.etf.si.projekt.dodatno.GenerisanjePDF;
import ba.unsa.etf.si.projekt.entiteti.KorisnickiRacun;
import ba.unsa.etf.si.projekt.hibernate.HibernateKorisnickiRacuni;
import ba.unsa.etf.si.projekt.hibernate.HibernateUtil;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class AdministratorPocetna {
	final static Logger logger = Logger.getLogger(AdministratorPocetna.class);
	private JFrame frmAdministratorPoetna;
	private KorisnickiRacun korisnik;

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
	public AdministratorPocetna(KorisnickiRacun kr) {
		this.korisnik = kr;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdministratorPoetna = new JFrame();
		frmAdministratorPoetna.setResizable(false);
		frmAdministratorPoetna.setIconImage(Toolkit.getDefaultToolkit().getImage(AdministratorPocetna.class.getResource("/ba/unsa/etf/si/projekt/dodatno/icon.png")));
		frmAdministratorPoetna.setTitle("Administrator početna");
		frmAdministratorPoetna.setBounds(100, 100, 480, 390);
		frmAdministratorPoetna.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAdministratorPoetna.getContentPane().setLayout(null);
		
		JLabel lblPrijavljeniSteKao = new JLabel("Dobrodošli!");
		lblPrijavljeniSteKao.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrijavljeniSteKao.setBounds(41, 17, 249, 23);
		frmAdministratorPoetna.getContentPane().add(lblPrijavljeniSteKao);
		
		JButton odjavaAdminPocetna = new JButton("Odjava");
		odjavaAdminPocetna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Session session = HibernateUtil.getSessionFactory().openSession();
				
				HibernateKorisnickiRacuni.modifikujKorisnickiRacun(session, korisnik.getKorisnickoIme(), korisnik.getKorisnickoIme(), 
						korisnik.getSifra(), korisnik.getTipKorisnickogRacuna(), false);
			
				PrijavaForma p = new PrijavaForma();
				p.setVisible(true);
				setVisible(false);
			}
		});
		odjavaAdminPocetna.setBounds(333, 17, 89, 23);
		frmAdministratorPoetna.getContentPane().add(odjavaAdminPocetna);
		
		JPanel panel = new JPanel();
		panel.setBounds(41, 94, 381, 256);
		frmAdministratorPoetna.getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 5));
		
		JButton izvjestajiAdminPocetna = new JButton("Izvještaji");
		
		// Klik na dugme za pravljenje izvjestaja
		izvjestajiAdminPocetna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IzvjestajiForma i = new IzvjestajiForma(korisnik);
				i.setVisible(true);
				frmAdministratorPoetna.setVisible(false);
			}
		});
		panel.add(izvjestajiAdminPocetna);
		
		JButton radniciAdminPocetna = new JButton("Evidencija radnika");
		
		// Klik na dugme za evidenciju radnika
		radniciAdminPocetna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RadniciForma r = new RadniciForma(korisnik);
				r.setVisible(true);
				setVisible(false);
			}
		});
		panel.add(radniciAdminPocetna);
		
		JButton linijeAdminPocetna = new JButton("Evidencija autobuskih linija");
		
		// Klik na dugme za evidenciju autobuskih linija
		linijeAdminPocetna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AutobuskeLinijeForma a = new AutobuskeLinijeForma(korisnik);
				a.setVisible(true);
				setVisible(false);
			}
		});
		panel.add(linijeAdminPocetna);
		
		JButton autobusiAdminPocetna = new JButton("Evidencija autobusa");
		
		// Klik na dugme za evidenciju autobusa
		autobusiAdminPocetna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AutobusiForma a = new AutobusiForma(korisnik);
				a.setVisible(true);
				setVisible(false);
			}
		});
		panel.add(autobusiAdminPocetna);
		
		JButton racuniAdminPocetna = new JButton("Korisnički računi");
		
		// Klik na dugme za evidenciju korisničkih računa
		racuniAdminPocetna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KorisniciForma k = new KorisniciForma(korisnik);
				k.setVisible(true);
				setVisible(false);
			}
		});
		panel.add(racuniAdminPocetna);
		
		JLabel lblPrijavljeniSteKao_1 = new JLabel("Prijavljeni ste kao administrator!");
		lblPrijavljeniSteKao_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrijavljeniSteKao_1.setBounds(41, 47, 266, 23);
		frmAdministratorPoetna.getContentPane().add(lblPrijavljeniSteKao_1);
	}
	
	public void setVisible(boolean visible) {
		frmAdministratorPoetna.setVisible(visible);
	}
}
