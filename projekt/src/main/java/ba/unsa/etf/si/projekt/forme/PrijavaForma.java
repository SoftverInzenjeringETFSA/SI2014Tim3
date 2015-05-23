package ba.unsa.etf.si.projekt.forme;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.UIManager;

import java.awt.Font;

import javax.swing.JButton;

import org.hibernate.Session;

import ba.unsa.etf.si.projekt.entiteti.KorisnickiRacun;

import ba.unsa.etf.si.projekt.entiteti.TipKorisnickogRacuna;
import ba.unsa.etf.si.projekt.hibernate.HibernateKorisnickiRacuni;
import ba.unsa.etf.si.projekt.hibernate.HibernateUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

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
		frmPrijava.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmPrijava.getContentPane().setLayout(null);
		
		ime = new JTextField();
		ime.setBounds(165, 91, 118, 20);
		frmPrijava.getContentPane().add(ime);
		ime.setColumns(10);
		
		sifra = new JPasswordField();
		sifra.setBounds(165, 137, 118, 20);
		frmPrijava.getContentPane().add(sifra);
		
		JLabel lblNewLabel = new JLabel("Korisničko ime:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setBounds(47, 94, 108, 14);
		frmPrijava.getContentPane().add(lblNewLabel);
		
		JLabel lblifra = new JLabel("Šifra:");
		lblifra.setHorizontalAlignment(SwingConstants.TRAILING);
		lblifra.setBounds(91, 140, 64, 14);
		frmPrijava.getContentPane().add(lblifra);
		
		JLabel lblDobrodoli = new JLabel("Dobrodošli!");
		lblDobrodoli.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDobrodoli.setBounds(115, 28, 102, 25);
		frmPrijava.getContentPane().add(lblDobrodoli);
		
		
		final JButton prijavaBtn = new JButton("Prijava");
		prijavaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					Session session = HibernateUtil.getSessionFactory().openSession();
					HibernateKorisnickiRacuni kr=new HibernateKorisnickiRacuni();
					KorisnickiRacun korisnik=new KorisnickiRacun();
					if(korisnik!=null)
					{
					korisnik=kr.nadjiKorisnickiRacun(session, ime.getText());
					
					String imekorisnika=korisnik.getKorisnickoIme();
					String sifrakorisnika=korisnik.getSifra();
					TipKorisnickogRacuna pravakorisnika=korisnik.getTipKorisnickogRacuna();
					
					TipKorisnickogRacuna r1=TipKorisnickogRacuna.menadzer;
					TipKorisnickogRacuna r2=TipKorisnickogRacuna.administrator;
					TipKorisnickogRacuna r3=TipKorisnickogRacuna.salterskiRadnik;
				
					if (ime.getText().equals(imekorisnika) && sifra.getText().equals(sifrakorisnika) && pravakorisnika==r2 ) {
					AdministratorPocetna a = new AdministratorPocetna();
					a.setVisible(true);
					setVisible(false);
				}
					else if (ime.getText().equals(imekorisnika) && sifra.getText().equals(sifrakorisnika) && pravakorisnika==r1) {
					MenadzerPocetna m = new MenadzerPocetna();
					m.setVisible(true);
					setVisible(false);
				}
					else if (ime.getText().equals(imekorisnika) && sifra.getText().equals(sifrakorisnika) && pravakorisnika==r3) {
					SalterskiRadnikForma s = new SalterskiRadnikForma();
					s.setVisible(true);
					setVisible(false);
				}
					JOptionPane.showMessageDialog(prijavaBtn, "Vaša prijava je bila uspješna.");
				session.close();
				}
				
				else
				{
					JOptionPane.showMessageDialog(prijavaBtn, "Unijeli ste pogrešno korisničko ime ili nemate kreiran korisnički račun.");
				}
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(prijavaBtn, "Neuspješna pretraga korisničkih računa.");
					JOptionPane.showMessageDialog(prijavaBtn, ex);
				}
			}
		});
		prijavaBtn.setBounds(194, 179, 89, 23);
		frmPrijava.getContentPane().add(prijavaBtn);
		
		
	}
	
	public void setVisible(boolean visible) {
		frmPrijava.setVisible(visible);
	}
}
