package ba.unsa.etf.si.projekt.forme;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import ba.unsa.etf.si.projekt.dodatno.GenerisanjePDF;
import ba.unsa.etf.si.projekt.entiteti.Karta;
import ba.unsa.etf.si.projekt.hibernate.HibernateAutibuskaLinija;
import ba.unsa.etf.si.projekt.hibernate.HibernateKarta;
import ba.unsa.etf.si.projekt.hibernate.HibernateUtil;

import com.toedter.calendar.JDateChooser;

import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;

import org.hibernate.Session;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class IzvjestajiForma {

	private JFrame frmIzvjetaji;
	private JTextField pocetnoVrijemeSati;
	private JTextField pocetnoVrijemeMinute;
	private JTextField krajnjeVrijemeMinute;
	private JTextField krajnjeVrijemeSati;
	private JTextField imeVozac;
	private JTextField prezimeVozac;

	private String tipKorisnika;
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
	
	public IzvjestajiForma(String tipKorisnika) {
		initialize();
		this.tipKorisnika = tipKorisnika;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIzvjetaji = new JFrame();
		frmIzvjetaji.setTitle("Izvještaji");
		frmIzvjetaji.setBounds(100, 100, 502, 393);
		frmIzvjetaji.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmIzvjetaji.getContentPane().setLayout(null);
		
		JButton nazadBtn = new JButton("Nazad");
		
		// Klik na dugme nazad
		nazadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tipKorisnika == "administrator") {
					AdministratorPocetna a = new AdministratorPocetna();
					a.setVisible(true);
					setVisible(false);
				} else if (tipKorisnika == "menadjer") {
					MenadzerPocetna m = new MenadzerPocetna();
					m.setVisible(true);
					setVisible(false);
				}				
			}
		});
		nazadBtn.setBounds(282, 11, 89, 23);
		frmIzvjetaji.getContentPane().add(nazadBtn);
		
		JButton odjavaBtn = new JButton("Odjava");
		
		// Klik na dugme odjava
		odjavaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrijavaForma p = new PrijavaForma();
				p.setVisible(true);
				setVisible(false);
			}
		});
		odjavaBtn.setBounds(387, 11, 89, 23);
		frmIzvjetaji.getContentPane().add(odjavaBtn);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(21, 73, 438, 270);
		frmIzvjetaji.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Prodane karte", null, panel, null);
		panel.setLayout(null);
		
		final JDateChooser pocetniProdaneDate = new JDateChooser();
		pocetniProdaneDate.setDateFormatString("dd/MM/yyyy");
		pocetniProdaneDate.setBounds(175, 50, 178, 20);
		panel.add(pocetniProdaneDate);
		
		JLabel label_8 = new JLabel("Početni datum:");
		label_8.setBounds(93, 53, 72, 14);
		panel.add(label_8);
		
		JLabel label_9 = new JLabel("Krajnji datum:");
		label_9.setBounds(94, 100, 72, 14);
		panel.add(label_9);
		
		final JDateChooser krajnjiProdaneDate = new JDateChooser();
		krajnjiProdaneDate.setDateFormatString("dd/MM/yyyy");
		krajnjiProdaneDate.setBounds(176, 97, 178, 20);
		panel.add(krajnjiProdaneDate);
		
		final JButton generisiProdaneBtn = new JButton("Generiši izvještaj");
		generisiProdaneBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//izvjestaj o kartama
				try
				{
					Session session = HibernateUtil.getSessionFactory().openSession();
					HibernateKarta k=new HibernateKarta();
					Date datum1=pocetniProdaneDate.getDate();
					Calendar cal=Calendar.getInstance();
					cal.setTime(datum1);
					int godina1=cal.get(Calendar.YEAR);
					int mjesec1=cal.get(Calendar.MONTH);
					int dan1=cal.get(Calendar.DAY_OF_MONTH);
					Date datum2=krajnjiProdaneDate.getDate();
					Calendar cal1=Calendar.getInstance();
					int godina2=cal1.get(Calendar.YEAR);
					int mjesec2=cal1.get(Calendar.MONTH);
					int dan2=cal1.get(Calendar.DAY_OF_MONTH);
					GenerisanjePDF.prodaneKartePDF(k.IzvjestajOProdanimKartama(session, godina1, mjesec1, dan1, godina2, mjesec2, dan2), 
							datum1, datum2);
					
					JOptionPane.showMessageDialog(generisiProdaneBtn, "Uspješno ste kreirali izvještaj o prodanim kartama.");
					
				}
				catch(Exception ex4)
				{
					JOptionPane.showMessageDialog(generisiProdaneBtn, "Niste kreirali izvjestaj.");
					JOptionPane.showMessageDialog(generisiProdaneBtn, "Uspješno ste kreirali izvještaj o vozačima.");
				}
				
			}
		});
		generisiProdaneBtn.setBounds(241, 208, 113, 23);
		panel.add(generisiProdaneBtn);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Autobuske linije", null, panel_1, null);
		panel_1.setLayout(null);
		
		final JDateChooser pocetniLinijeDate = new JDateChooser();
		pocetniLinijeDate.setDateFormatString("dd/MM/yyyy");
		pocetniLinijeDate.setBounds(171, 11, 178, 20);
		panel_1.add(pocetniLinijeDate);
		
		JLabel label = new JLabel("Početni datum:");
		label.setBounds(89, 14, 72, 14);
		panel_1.add(label);
		
		JLabel label_1 = new JLabel("Krajnji datum:");
		label_1.setBounds(90, 61, 72, 14);
		panel_1.add(label_1);
		
		final JDateChooser krajnjiLinijeDate = new JDateChooser();
		krajnjiLinijeDate.setDateFormatString("dd/MM/yyyy");
		krajnjiLinijeDate.setBounds(172, 58, 178, 20);
		panel_1.add(krajnjiLinijeDate);
		
		pocetnoVrijemeSati = new JTextField();
		pocetnoVrijemeSati.setColumns(10);
		pocetnoVrijemeSati.setBounds(171, 103, 67, 20);
		panel_1.add(pocetnoVrijemeSati);
		
		JLabel label_2 = new JLabel("Početno vrijeme:");
		label_2.setBounds(72, 106, 89, 14);
		panel_1.add(label_2);
		
		pocetnoVrijemeMinute = new JTextField();
		pocetnoVrijemeMinute.setColumns(10);
		pocetnoVrijemeMinute.setBounds(270, 103, 61, 20);
		panel_1.add(pocetnoVrijemeMinute);
		
		krajnjeVrijemeMinute = new JTextField();
		krajnjeVrijemeMinute.setColumns(10);
		krajnjeVrijemeMinute.setBounds(270, 149, 61, 20);
		panel_1.add(krajnjeVrijemeMinute);
		
		krajnjeVrijemeSati = new JTextField();
		krajnjeVrijemeSati.setColumns(10);
		krajnjeVrijemeSati.setBounds(171, 149, 67, 20);
		panel_1.add(krajnjeVrijemeSati);
		
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
		
		final JButton generisiLinijeBtn = new JButton("Generiši izvještaj");
		generisiLinijeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { //generisanje izvjestaja o Autobuskim linijama
				try
				{
					Session session = HibernateUtil.getSessionFactory().openSession();
					
					HibernateAutibuskaLinija linija=new HibernateAutibuskaLinija();
					Date datum1=pocetniLinijeDate.getDate();
					Calendar cal= Calendar.getInstance();
		            cal.setTime(datum1);
					int godina1=cal.get(Calendar.YEAR);
					int mjesec1=cal.get(Calendar.MONTH);
					int dan1=cal.get(Calendar.DAY_OF_MONTH);
					Date datum2=krajnjiLinijeDate.getDate();
					Calendar cal1=Calendar.getInstance();
					int godina2=cal1.get(Calendar.YEAR);
					int mjesec2=cal1.get(Calendar.MONTH);
					int dan2=cal1.get(Calendar.DAY_OF_MONTH);
					int pocetnisati=Integer.parseInt(pocetnoVrijemeSati.getText());
					int pocetneminute=Integer.parseInt(pocetnoVrijemeMinute.getText());
					int krajnjisati=Integer.parseInt(krajnjeVrijemeSati.getText());
					int krajnjeminute=Integer.parseInt(krajnjeVrijemeMinute.getText());
					
					
					linija.IzvjestajOAutobuskimLinijama(session, godina1, mjesec1, dan1, godina2, mjesec2, dan2, pocetnisati, pocetneminute, krajnjisati, krajnjeminute);
					JOptionPane.showMessageDialog(generisiLinijeBtn, "Uspješno ste kreirali izvještaj o autobuskim linijama.");
				}
				catch(Exception ex1)
				{
					JOptionPane.showMessageDialog(generisiLinijeBtn, "Neuspješno kreiranje izvještaja.");
					JOptionPane.showMessageDialog(generisiLinijeBtn, ex1);
				}
				
			}
		});
		generisiLinijeBtn.setBounds(236, 208, 113, 23);
		panel_1.add(generisiLinijeBtn);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Vozači", null, panel_2, null);
		panel_2.setLayout(null);
		
		imeVozac = new JTextField();
		imeVozac.setColumns(10);
		imeVozac.setBounds(204, 67, 113, 20);
		panel_2.add(imeVozac);
		
		JLabel lblImeVozaa = new JLabel("Ime vozača:");
		lblImeVozaa.setBounds(135, 70, 59, 14);
		panel_2.add(lblImeVozaa);
		
		prezimeVozac = new JTextField();
		prezimeVozac.setColumns(10);
		prezimeVozac.setBounds(204, 113, 113, 20);
		panel_2.add(prezimeVozac);
		
		JLabel lblPrezimeVozaa = new JLabel("Prezime vozača:");
		lblPrezimeVozaa.setBounds(116, 116, 78, 14);
		panel_2.add(lblPrezimeVozaa);
		
		final JButton generisiVozaciBtn = new JButton("Generiši izvještaj");
		generisiVozaciBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					Session session = HibernateUtil.getSessionFactory().openSession();
					HibernateAutibuskaLinija linija=new HibernateAutibuskaLinija();
					java.util.List radnici=linija.IzvjestajORadnicima(session,imeVozac.getText() , prezimeVozac.getText());
					JOptionPane.showMessageDialog(generisiVozaciBtn, "Uspješno ste kreirali izvještaj o vozačima.");

				}
				catch(Exception ex3)
				{
					JOptionPane.showMessageDialog(generisiVozaciBtn, "Niste uspješno kreirali izvještaj.");
					JOptionPane.showMessageDialog(generisiVozaciBtn, ex3);
				}
			}
		});
		generisiVozaciBtn.setBounds(204, 208, 113, 23);
		panel_2.add(generisiVozaciBtn);
		
		
		/*
		frmIzvjetaji.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(frmIzvjetaji, 
		            "Da li želite zatvoriti prozor?", 
		            "Zatvaranje", JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		        	AdministratorPocetna a = new AdministratorPocetna();
		        	a.frmAdministratorPoetna.setVisible(true);
		            frmIzvjetaji.setVisible(false);
		        }
		    }
		});
		*/
	}
	
	public void setVisible(boolean visible) {
		frmIzvjetaji.setVisible(visible);
	}
	
}
