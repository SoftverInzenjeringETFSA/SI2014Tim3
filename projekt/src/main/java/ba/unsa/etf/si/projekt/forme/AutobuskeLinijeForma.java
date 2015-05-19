package ba.unsa.etf.si.projekt.forme;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.UIManager;

import ba.unsa.etf.si.projekt.entiteti.Autobus;
import ba.unsa.etf.si.projekt.entiteti.AutobuskaLinija;
import ba.unsa.etf.si.projekt.entiteti.Radnik;
import ba.unsa.etf.si.projekt.hibernate.HibernateAutibuskaLinija;
import ba.unsa.etf.si.projekt.hibernate.HibernateAutobus;
import ba.unsa.etf.si.projekt.hibernate.HibernateRadnik;
import ba.unsa.etf.si.projekt.hibernate.HibernateUtil;

import com.toedter.calendar.JDateChooser;

import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

import org.hibernate.Session;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;

public class AutobuskeLinijeForma {

	private JFrame frmAutobuskeLinije;
	private JTextField polazisteDodaj;
	private JTextField odredisteDodaj;
	private JTextField vrijemeDodaj;
	private JTextField trajanjeDodaj;
	private JTextField jednosmjernaDodaj;
	private JTextField dvosmjernaDodaj;
	private JTextField polazisteModifikuj;
	private JTextField odredisteModifikujBtn;
	private JTextField vrijemeModifikuj;
	private JTextField dvosmjernaModifikuj;
	private JTextField jednosmjernaModifikuj;
	private JTextField trajanjeModifikuj;
	private JTextField trajanjeIzbrisi;
	private JTextField jednosmjernaIzbrisi;
	private JTextField dvosmjernaIzbrisi;
	private JTextField polazisteIzbrisi;
	private JTextField odredisteIzbrisi;
	private JTextField vrijemeIzbrisi;
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
					
					AutobuskeLinijeForma window = new AutobuskeLinijeForma();
					window.frmAutobuskeLinije.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AutobuskeLinijeForma() {
		initialize();
	}

	public AutobuskeLinijeForma(String tipKorisnika) {
		// TODO Auto-generated constructor stub
		this.tipKorisnika = tipKorisnika;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAutobuskeLinije = new JFrame();
		frmAutobuskeLinije.setTitle("Autobuske linije");
		frmAutobuskeLinije.setBounds(100, 100, 551, 506);
		frmAutobuskeLinije.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAutobuskeLinije.getContentPane().setLayout(null);
		
		JButton odjavaBtn = new JButton("Odjava");
		odjavaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrijavaForma p = new PrijavaForma();
				p.setVisible(true);
				setVisible(false);
			}
		});
		odjavaBtn.setBounds(433, 11, 89, 23);
		frmAutobuskeLinije.getContentPane().add(odjavaBtn);
		
		JButton nazadBtn = new JButton("Nazad");
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
		nazadBtn.setBounds(334, 11, 89, 23);
		frmAutobuskeLinije.getContentPane().add(nazadBtn);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(20, 64, 502, 400);
		frmAutobuskeLinije.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Dodaj", null, panel, null);
		panel.setLayout(null);
		
		polazisteDodaj = new JTextField();
		polazisteDodaj.setBounds(109, 29, 116, 20);
		panel.add(polazisteDodaj);
		polazisteDodaj.setColumns(10);
		
		JLabel lblPolazite = new JLabel("Polazište:");
		lblPolazite.setBounds(53, 32, 46, 14);
		panel.add(lblPolazite);
		
		odredisteDodaj = new JTextField();
		odredisteDodaj.setBounds(109, 68, 116, 20);
		panel.add(odredisteDodaj);
		odredisteDodaj.setColumns(10);
		
		JLabel lblOdredite = new JLabel("Odredište:");
		lblOdredite.setBounds(48, 71, 51, 14);
		panel.add(lblOdredite);
		
		JLabel lblRegistracije = new JLabel("Autobus:");
		lblRegistracije.setBounds(53, 112, 46, 14);
		panel.add(lblRegistracije);
		
		vrijemeDodaj = new JTextField();
		vrijemeDodaj.setBounds(109, 236, 116, 20);
		panel.add(vrijemeDodaj);
		vrijemeDodaj.setColumns(10);
		
		final JDateChooser datumDodajDate = new JDateChooser();
		datumDodajDate.setBounds(109, 193, 116, 20);
		panel.add(datumDodajDate);
		
		JLabel lblImeVozaa = new JLabel("Vozač:");
		lblImeVozaa.setBounds(67, 154, 32, 14);
		panel.add(lblImeVozaa);
		
		JLabel lblDatumPolaska = new JLabel("Datum polaska:");
		lblDatumPolaska.setBounds(21, 195, 78, 14);
		panel.add(lblDatumPolaska);
		
		JLabel lblVrijemePolaska = new JLabel("Vrijeme polaska:");
		lblVrijemePolaska.setBounds(21, 239, 78, 14);
		panel.add(lblVrijemePolaska);
		
		JLabel lblPeron = new JLabel("Peron:");
		lblPeron.setBounds(294, 32, 32, 14);
		panel.add(lblPeron);
		
		final JSpinner peronDodajSpinner = new JSpinner();
		peronDodajSpinner.setBounds(336, 29, 116, 20);
		panel.add(peronDodajSpinner);
		
		JLabel lblDistanca = new JLabel("Distanca:");
		lblDistanca.setBounds(280, 71, 46, 14);
		panel.add(lblDistanca);
		
		final JSpinner distancaDodajSpinner = new JSpinner();
		distancaDodajSpinner.setBounds(336, 68, 116, 20);
		panel.add(distancaDodajSpinner);
		
		JLabel lblKm = new JLabel("km");
		lblKm.setBounds(463, 71, 46, 14);
		panel.add(lblKm);
		
		trajanjeDodaj = new JTextField();
		trajanjeDodaj.setBounds(336, 109, 116, 20);
		panel.add(trajanjeDodaj);
		trajanjeDodaj.setColumns(10);
		
		JLabel lblTrajanje = new JLabel("Trajanje:");
		lblTrajanje.setBounds(280, 112, 46, 14);
		panel.add(lblTrajanje);
		
		JLabel lblH = new JLabel("h");
		lblH.setBounds(463, 112, 46, 14);
		panel.add(lblH);
		
		final JSpinner brojDodajSpinner = new JSpinner();
		brojDodajSpinner.setBounds(336, 151, 116, 20);
		panel.add(brojDodajSpinner);
		
		JLabel lblBrojLinije = new JLabel("Broj linije:");
		lblBrojLinije.setBounds(275, 154, 51, 14);
		panel.add(lblBrojLinije);
		
		jednosmjernaDodaj = new JTextField();
		jednosmjernaDodaj.setColumns(10);
		jednosmjernaDodaj.setBounds(336, 193, 116, 20);
		panel.add(jednosmjernaDodaj);
		
		JLabel lblCijenaJednosmjerne = new JLabel("Jednosmjerna:");
		lblCijenaJednosmjerne.setBounds(255, 196, 71, 14);
		panel.add(lblCijenaJednosmjerne);
		
		JLabel lblKm_1 = new JLabel("KM");
		lblKm_1.setBounds(463, 196, 46, 14);
		panel.add(lblKm_1);
		
		JLabel lblDvosmjerna = new JLabel("Dvosmjerna:");
		lblDvosmjerna.setBounds(265, 239, 71, 14);
		panel.add(lblDvosmjerna);
		
		dvosmjernaDodaj = new JTextField();
		dvosmjernaDodaj.setColumns(10);
		dvosmjernaDodaj.setBounds(336, 236, 116, 20);
		panel.add(dvosmjernaDodaj);
		
		JLabel label_1 = new JLabel("KM");
		label_1.setBounds(463, 239, 46, 14);
		panel.add(label_1);
		
		final JRadioButton medunarodnaDodaj = new JRadioButton("Međunarodna");
		medunarodnaDodaj.setBounds(108, 282, 109, 23);
		panel.add(medunarodnaDodaj);
		
        final JComboBox autobusDodajCombo = new JComboBox(); //svi autobusiiiiiii
		
		HibernateAutobus a1=new HibernateAutobus();
		Session session = HibernateUtil.getSessionFactory().openSession();
		java.util.List sviautobusi=a1.sviAutobusi(session);
		for(int i=0;i<sviautobusi.size();i++)
		{
			Autobus a=(Autobus)sviautobusi.get(i);
		   autobusDodajCombo.addItem(a.getRegistracija());	
		}
		autobusDodajCombo.setBounds(109, 109, 116, 20);
		panel.add(autobusDodajCombo);
		
		final JComboBox vozacDodajCombo = new JComboBox();//svi radniciiii
		HibernateRadnik r1=new HibernateRadnik();
		java.util.List sviradnici=r1.sviRadnici(session);
		for(int i=0;i<sviradnici.size();i++)
		{
			Radnik r=(Radnik)sviradnici.get(i);
			if(r.dajTipRadnogMjesta().toString()=="Vozac")
			vozacDodajCombo.addItem(r.getIme());
		}
		vozacDodajCombo.setBounds(109, 151, 116, 20);
		panel.add(vozacDodajCombo);
		
		
		final JButton dodajBtn = new JButton("Dodaj");
		dodajBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //dodavanje linija
				
				try
				{
				Session session = HibernateUtil.getSessionFactory().openSession();
				HibernateAutibuskaLinija novalinija=new HibernateAutibuskaLinija();
				
				Autobus autobuslinije=new Autobus();//selektivani autobus
				HibernateAutobus autobus1=new HibernateAutobus();
				autobuslinije=autobus1.nadjiAutobus(session, autobusDodajCombo.getSelectedItem().toString());
				
				Radnik radnik=new Radnik(); //selektovani radnik
				HibernateRadnik rad1=new HibernateRadnik();
				radnik=rad1.nadjiRadnikaPoImenu(session, vozacDodajCombo.getSelectedItem().toString());
				
				Date datum=datumDodajDate.getDate();
				int godina1=datum.getYear();
				int mjesec1=datum.getMonth();
				int dan1=datum.getDay();
				int sati=datum.getHours();
				int minute=datum.getMinutes();
				
				String peron=peronDodajSpinner.getValue().toString();
				int brojperona=Integer.parseInt(peron);
				String distanca=distancaDodajSpinner.getValue().toString();
				int distanca1=Integer.parseInt(distanca);
				int trajanje=Integer.parseInt(trajanjeDodaj.getText());
				int brojlinije=Integer.parseInt(brojDodajSpinner.getValue().toString());
				
				int jednosmjerna=Integer.parseInt(jednosmjernaDodaj.getText());
				int dvosmjerna=Integer.parseInt(dvosmjernaDodaj.getText());
				
				boolean medjunarodnaLinija=false;
				if(medunarodnaDodaj.isSelected())
				{
					medjunarodnaLinija=true;
				}
				
				novalinija.dodajAutobuskuLiniju(session, polazisteDodaj.getText(), odredisteDodaj.getText(),autobuslinije , radnik, godina1, mjesec1, dan1, sati, minute, brojperona, distanca1, trajanje, brojlinije, jednosmjerna, dvosmjerna, medjunarodnaLinija);
				JOptionPane.showMessageDialog(dodajBtn, "Uspješno je dodata autobuska linija.");
				polazisteDodaj.setText("");
				odredisteDodaj.setText("");
				peronDodajSpinner.setValue(0);
				distancaDodajSpinner.setValue(0);
				brojDodajSpinner.setValue(0);
				trajanjeDodaj.setText("");
				jednosmjernaDodaj.setText("");
				dvosmjernaDodaj.setText("");
				
				}
				catch(Exception e11)
				{
					JOptionPane.showMessageDialog(dodajBtn, "Neuspješno dodavanje autobuske linije.");
					JOptionPane.showMessageDialog(dodajBtn, e11);
				}
			}
		});
		dodajBtn.setBounds(398, 338, 89, 23);
		panel.add(dodajBtn);
		
		
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Modifikuj", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("Broj linije:");
		label.setBounds(115, 28, 51, 14);
		panel_1.add(label);
		
		final JSpinner brojPretragaSpinner = new JSpinner();
		brojPretragaSpinner.setBounds(176, 25, 116, 20);
		panel_1.add(brojPretragaSpinner);
		
		final JComboBox autobusModifikujCombo = new JComboBox();
		HibernateAutobus a2=new HibernateAutobus();
		//Session session = HibernateUtil.getSessionFactory().openSession();
		java.util.List sviautobusi1=a2.sviAutobusi(session);
		for(int i=0;i<sviautobusi1.size();i++)
		{
			Autobus a=(Autobus)sviautobusi1.get(i);
		   autobusModifikujCombo.addItem(a.getRegistracija());	
		}
		autobusModifikujCombo.setBounds(98, 159, 116, 20);
		panel_1.add(autobusModifikujCombo);
		
		JLabel label_5 = new JLabel("Vozač:");
		label_5.setBounds(56, 204, 32, 14);
		panel_1.add(label_5);
		
		final JComboBox vozacModifikujCombo = new JComboBox();
		HibernateRadnik r2=new HibernateRadnik();
		java.util.List sviradnici1=r2.sviRadnici(session);
		for(int i=0;i<sviradnici1.size();i++)
		{
			Radnik r=(Radnik)sviradnici1.get(i);
			if(r.dajTipRadnogMjesta().toString()=="Vozac")
			vozacModifikujCombo.addItem(r.getIme());
		}
		vozacModifikujCombo.setBounds(98, 201, 116, 20);
		panel_1.add(vozacModifikujCombo);
		
		final JRadioButton medunarodnaModifikuj = new JRadioButton("Međunarodna");
		medunarodnaModifikuj.setBounds(332, 285, 109, 23);
		panel_1.add(medunarodnaModifikuj);
		
		final JSpinner peronModifikujSpinner = new JSpinner();
		peronModifikujSpinner.setBounds(325, 79, 116, 20);
		panel_1.add(peronModifikujSpinner);
		
		final JSpinner distancaModifikujSpinner = new JSpinner();
		distancaModifikujSpinner.setBounds(325, 118, 116, 20);
		panel_1.add(distancaModifikujSpinner);
		
		final JButton pronadjiModifikujBtn = new JButton("Pronađi");
		pronadjiModifikujBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //pronadji liniju
				try
				{
				Session session = HibernateUtil.getSessionFactory().openSession();
				HibernateAutibuskaLinija trazenjelinija=new HibernateAutibuskaLinija();
				AutobuskaLinija lin=new AutobuskaLinija();
				int brojlinijepretraga=Integer.parseInt(brojPretragaSpinner.getValue().toString());
				lin=trazenjelinija.nadjiAutobuskuLiniju(session, brojlinijepretraga);
				if(trazenjelinija!=null)
				{
					polazisteModifikuj.setText(lin.getPolaziste());
					odredisteModifikujBtn.setText(lin.getOdrediste());
					String imevozaca=lin.getVozac().getIme();
					vozacModifikujCombo.setSelectedItem(imevozaca);
					String aut=lin.getAutobus().getRegistracija();
					autobusModifikujCombo.setSelectedItem(aut);
					//datum ludiii
					peronModifikujSpinner.setValue(lin.getPeron());
					//brojModifikujSpinner.setValue(lin.getBrojLinije());
					distancaModifikujSpinner.setValue(lin.getDistanca());
					
					String t=Double.toString(lin.getTrajanje());
					trajanjeModifikuj.setText(t);
					String jedk=Double.toString(lin.getCijenaJednosmjerna());
					jednosmjernaModifikuj.setText(jedk);
					String dvk=Double.toString(lin.getCijenaDvosmjerna());
					dvosmjernaModifikuj.setText(dvk);
					if(lin.isMedjunarodna()==true)
					{
						medunarodnaModifikuj.setSelected(true);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(pronadjiModifikujBtn, "Ne postoji autobuska linija s tim brojem linije.");
				}
				}
				catch(Exception e12)
				{
					JOptionPane.showMessageDialog(pronadjiModifikujBtn, "Neuspješna pretraga.");
					JOptionPane.showMessageDialog(pronadjiModifikujBtn, e12);
				}
				
			}
		});
		pronadjiModifikujBtn.setBounds(304, 24, 89, 23);
		panel_1.add(pronadjiModifikujBtn);
		
		JLabel label_2 = new JLabel("Polazište:");
		label_2.setBounds(42, 82, 46, 14);
		panel_1.add(label_2);
		
		polazisteModifikuj = new JTextField();
		polazisteModifikuj.setColumns(10);
		polazisteModifikuj.setBounds(98, 79, 116, 20);
		panel_1.add(polazisteModifikuj);
		
		odredisteModifikujBtn = new JTextField();
		odredisteModifikujBtn.setColumns(10);
		odredisteModifikujBtn.setBounds(98, 118, 116, 20);
		panel_1.add(odredisteModifikujBtn);
		
		JLabel label_3 = new JLabel("Odredište:");
		label_3.setBounds(37, 121, 51, 14);
		panel_1.add(label_3);
		
		JLabel label_4 = new JLabel("Autobus:");
		label_4.setBounds(42, 162, 46, 14);
		panel_1.add(label_4);
		
		
		
		final JDateChooser polazakModifikujDate = new JDateChooser();
		polazakModifikujDate.setBounds(98, 243, 116, 20);
		panel_1.add(polazakModifikujDate);
		
		JLabel label_6 = new JLabel("Datum polaska:");
		label_6.setBounds(10, 245, 78, 14);
		panel_1.add(label_6);
		
		vrijemeModifikuj = new JTextField();
		vrijemeModifikuj.setColumns(10);
		vrijemeModifikuj.setBounds(98, 286, 116, 20);
		panel_1.add(vrijemeModifikuj);
		
		JLabel label_7 = new JLabel("Vrijeme polaska:");
		label_7.setBounds(10, 289, 78, 14);
		panel_1.add(label_7);
		
		
		
		JLabel label_8 = new JLabel("Dvosmjerna:");
		label_8.setBounds(254, 246, 71, 14);
		panel_1.add(label_8);
		
		dvosmjernaModifikuj = new JTextField();
		dvosmjernaModifikuj.setColumns(10);
		dvosmjernaModifikuj.setBounds(325, 243, 116, 20);
		panel_1.add(dvosmjernaModifikuj);
		
		jednosmjernaModifikuj = new JTextField();
		jednosmjernaModifikuj.setColumns(10);
		jednosmjernaModifikuj.setBounds(325, 200, 116, 20);
		panel_1.add(jednosmjernaModifikuj);
		
		JLabel label_9 = new JLabel("Jednosmjerna:");
		label_9.setBounds(244, 203, 71, 14);
		panel_1.add(label_9);
		
		trajanjeModifikuj = new JTextField();
		trajanjeModifikuj.setColumns(10);
		trajanjeModifikuj.setBounds(325, 159, 116, 20);
		panel_1.add(trajanjeModifikuj);
		
		JLabel label_11 = new JLabel("Trajanje:");
		label_11.setBounds(269, 162, 46, 14);
		panel_1.add(label_11);
		
		
		
		JLabel label_12 = new JLabel("Distanca:");
		label_12.setBounds(269, 121, 46, 14);
		panel_1.add(label_12);
		
		
		
		JLabel label_13 = new JLabel("Peron:");
		label_13.setBounds(283, 82, 32, 14);
		panel_1.add(label_13);
		
		final JButton modifikujBtn = new JButton("Spasi izmjene");
		modifikujBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //modifikovanje linije
				try
				{
				Session session = HibernateUtil.getSessionFactory().openSession();
				HibernateAutibuskaLinija izmjenalinija=new HibernateAutibuskaLinija();
				
				Autobus autobuslinije=new Autobus();//selektivani autobus
				HibernateAutobus autobus1=new HibernateAutobus();
				autobuslinije=autobus1.nadjiAutobus(session, autobusModifikujCombo.getSelectedItem().toString());
				
				Radnik radnik=new Radnik(); //selektovani radnik
				HibernateRadnik rad1=new HibernateRadnik();
				radnik=rad1.nadjiRadnikaPoImenu(session, vozacModifikujCombo.getSelectedItem().toString());
				
				Date datum=polazakModifikujDate.getDate();
				int godina1=datum.getYear();
				int mjesec1=datum.getMonth();
				int dan1=datum.getDay();
				int sati=datum.getHours();
				int minute=datum.getMinutes();
				
				String peron=peronModifikujSpinner.getValue().toString();
				double per=Double.parseDouble(peron);
				int brojperona=(int)Math.round(per);
				String distanca=distancaModifikujSpinner.getValue().toString();
				double distanca1=Double.parseDouble(distanca);
				int trajanje=Integer.parseInt(trajanjeModifikuj.getText());
				int brojlinije=Integer.parseInt(brojDodajSpinner.getValue().toString());
				
				int jednosmjerna=Integer.parseInt(jednosmjernaModifikuj.getText());
				int dvosmjerna=Integer.parseInt(dvosmjernaModifikuj.getText());
				
				boolean medjunarodnaLinija=false;
				if(medunarodnaModifikuj.isSelected())
				{
					medjunarodnaLinija=true;
				}
				izmjenalinija.modifikujAutobuskuLiniju(session, polazisteModifikuj.getText(), odredisteModifikujBtn.getText(), autobuslinije, radnik, godina1, mjesec1, dan1, sati, minute, brojperona, distanca1, trajanje, brojlinije, jednosmjerna, dvosmjerna);
				JOptionPane.showMessageDialog(modifikujBtn,"Uspješno modifikovanje autobuske linije." );
				
				polazisteModifikuj.setText("");
				odredisteModifikujBtn.setText("");
				peronModifikujSpinner.setValue(0);
				distancaModifikujSpinner.setValue(0);
				//brojModifikujSpinner.setValue(0);
				trajanjeModifikuj.setText("");
				jednosmjernaModifikuj.setText("");
				dvosmjernaModifikuj.setText("");
				
				}
				catch(Exception e13)
				{
					JOptionPane.showMessageDialog(modifikujBtn,"Nespješno modifikovanje autobuske linije." );
					JOptionPane.showMessageDialog(modifikujBtn, e13 );
				}
				
				
			}
		});
		modifikujBtn.setBounds(378, 338, 109, 23);
		panel_1.add(modifikujBtn);
		
		JLabel label_14 = new JLabel("KM");
		label_14.setBounds(451, 204, 46, 14);
		panel_1.add(label_14);
		
		JLabel label_15 = new JLabel("KM");
		label_15.setBounds(451, 249, 46, 14);
		panel_1.add(label_15);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Izbriši", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel label_10 = new JLabel("Broj linije:");
		label_10.setBounds(115, 30, 51, 14);
		panel_2.add(label_10);
		
		final JSpinner pronadiIzbrisiSpinner = new JSpinner();
		pronadiIzbrisiSpinner.setBounds(176, 27, 116, 20);
		panel_2.add(pronadiIzbrisiSpinner);
		
		final JComboBox autobusIzbrisiCombo = new JComboBox();
		HibernateAutobus a3=new HibernateAutobus();
		java.util.List sviautobusi2=a3.sviAutobusi(session);
		for(int i=0;i<sviautobusi2.size();i++)
		{
			Autobus a=(Autobus)sviautobusi2.get(i);
		   autobusIzbrisiCombo.addItem(a.getRegistracija());	
		}
		autobusIzbrisiCombo.setBounds(98, 161, 116, 20);
		panel_2.add(autobusIzbrisiCombo);
		
		final JComboBox vozacIzbrisiCombo = new JComboBox();
		HibernateRadnik r3=new HibernateRadnik();
		java.util.List sviradnici3=r3.sviRadnici(session);
		for(int i=0;i<sviradnici3.size();i++)
		{
			Radnik r=(Radnik)sviradnici3.get(i);
			if(r.dajTipRadnogMjesta().toString()=="Vozac")
			vozacIzbrisiCombo.addItem(r.getIme());
		}
		vozacIzbrisiCombo.setBounds(98, 203, 116, 20);
		panel_2.add(vozacIzbrisiCombo);
		
		final JSpinner peronIzbrisiSpinner = new JSpinner();
		peronIzbrisiSpinner.setBounds(325, 81, 116, 20);
		panel_2.add(peronIzbrisiSpinner);
		
		final JSpinner distancaIzbrisiSpinner = new JSpinner();
		distancaIzbrisiSpinner.setBounds(325, 120, 116, 20);
		panel_2.add(distancaIzbrisiSpinner);
		
		final JRadioButton medunarodnaIzbrisi = new JRadioButton("Međunarodna");
		medunarodnaIzbrisi.setBounds(332, 287, 109, 23);
		panel_2.add(medunarodnaIzbrisi);
		
		trajanjeIzbrisi = new JTextField();
		trajanjeIzbrisi.setColumns(10);
		trajanjeIzbrisi.setBounds(325, 161, 116, 20);
		panel_2.add(trajanjeIzbrisi);
		
		jednosmjernaIzbrisi = new JTextField();
		jednosmjernaIzbrisi.setColumns(10);
		jednosmjernaIzbrisi.setBounds(325, 202, 116, 20);
		panel_2.add(jednosmjernaIzbrisi);
		
		dvosmjernaIzbrisi = new JTextField();
		dvosmjernaIzbrisi.setColumns(10);
		dvosmjernaIzbrisi.setBounds(325, 245, 116, 20);
		panel_2.add(dvosmjernaIzbrisi);
		
		final JButton pronadjiIzbrisiBtn = new JButton("Pronađi");
		pronadjiIzbrisiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //brisanje linije
				try
				{
				Session session = HibernateUtil.getSessionFactory().openSession();
				HibernateAutibuskaLinija brisanjelinija=new HibernateAutibuskaLinija();
				AutobuskaLinija linija=new AutobuskaLinija();
				int broj=Integer.parseInt(pronadiIzbrisiSpinner.getValue().toString());
				linija=brisanjelinija.nadjiAutobuskuLiniju(session, broj);
				if(linija!=null)
				{
					polazisteIzbrisi.setText(linija.getPolaziste());
					odredisteIzbrisi.setText(linija.getOdrediste());
					String imevozaca=linija.getVozac().getIme();
					vozacIzbrisiCombo.setSelectedItem(imevozaca);
					String aut=linija.getAutobus().getRegistracija();
					autobusIzbrisiCombo.setSelectedItem(aut);
					//datum ludiii
					peronIzbrisiSpinner.setValue(linija.getPeron());
					//brojModifikujSpinner.setValue(lin.getBrojLinije());
					distancaIzbrisiSpinner.setValue(linija.getDistanca());
					
					String t=Double.toString(linija.getTrajanje());
					trajanjeIzbrisi.setText(t);
					String jedk=Double.toString(linija.getCijenaJednosmjerna());
					jednosmjernaIzbrisi.setText(jedk);
					String dvk=Double.toString(linija.getCijenaDvosmjerna());
					dvosmjernaIzbrisi.setText(dvk);
					if(linija.isMedjunarodna()==true)
					{
						medunarodnaIzbrisi.setSelected(true);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(pronadjiIzbrisiBtn, "Ne postoji autobuska linija ciji ste broj unijeli" );
				}
				
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(pronadjiIzbrisiBtn, "Neuspješna pretraga." );
					JOptionPane.showMessageDialog(pronadjiIzbrisiBtn, ex);
					
				}
				
			}
		});
		pronadjiIzbrisiBtn.setBounds(304, 26, 89, 23);
		panel_2.add(pronadjiIzbrisiBtn);
		
	

		
		
		
		JLabel label_16 = new JLabel("KM");
		label_16.setBounds(451, 206, 46, 14);
		panel_2.add(label_16);
		
		JLabel label_17 = new JLabel("KM");
		label_17.setBounds(451, 251, 46, 14);
		panel_2.add(label_17);
		
		JLabel label_18 = new JLabel("Peron:");
		label_18.setBounds(283, 84, 32, 14);
		panel_2.add(label_18);
		
		JLabel label_19 = new JLabel("Distanca:");
		label_19.setBounds(269, 123, 46, 14);
		panel_2.add(label_19);
		
		JLabel label_20 = new JLabel("Trajanje:");
		label_20.setBounds(269, 164, 46, 14);
		panel_2.add(label_20);
		
		JLabel label_21 = new JLabel("Jednosmjerna:");
		label_21.setBounds(244, 205, 71, 14);
		panel_2.add(label_21);
		
		JLabel label_22 = new JLabel("Dvosmjerna:");
		label_22.setBounds(254, 248, 71, 14);
		panel_2.add(label_22);
		
		JLabel label_23 = new JLabel("Polazište:");
		label_23.setBounds(42, 84, 46, 14);
		panel_2.add(label_23);
		
		polazisteIzbrisi = new JTextField();
		polazisteIzbrisi.setColumns(10);
		polazisteIzbrisi.setBounds(98, 81, 116, 20);
		panel_2.add(polazisteIzbrisi);
		
		odredisteIzbrisi = new JTextField();
		odredisteIzbrisi.setColumns(10);
		odredisteIzbrisi.setBounds(98, 120, 116, 20);
		panel_2.add(odredisteIzbrisi);
		
		JLabel label_24 = new JLabel("Odredište:");
		label_24.setBounds(37, 123, 51, 14);
		panel_2.add(label_24);
		
		JLabel label_25 = new JLabel("Autobus:");
		label_25.setBounds(42, 164, 46, 14);
		panel_2.add(label_25);
		
		
		
		JLabel label_26 = new JLabel("Vozač:");
		label_26.setBounds(56, 206, 32, 14);
		panel_2.add(label_26);
		
		JLabel label_27 = new JLabel("Datum polaska:");
		label_27.setBounds(10, 247, 78, 14);
		panel_2.add(label_27);
		
		JDateChooser polazakIzbrisiDate = new JDateChooser();
		polazakIzbrisiDate.setBounds(98, 245, 116, 20);
		panel_2.add(polazakIzbrisiDate);
		
		vrijemeIzbrisi = new JTextField();
		vrijemeIzbrisi.setColumns(10);
		vrijemeIzbrisi.setBounds(98, 288, 116, 20);
		panel_2.add(vrijemeIzbrisi);
		
		JLabel label_28 = new JLabel("Vrijeme polaska:");
		label_28.setBounds(10, 291, 78, 14);
		panel_2.add(label_28);
		
		final JButton izbrisiBtn = new JButton("Izbriši");
		izbrisiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //brisanje autobusa
				
				try
				{
				Session session = HibernateUtil.getSessionFactory().openSession();
				HibernateAutibuskaLinija brisanjelinija=new HibernateAutibuskaLinija();
				AutobuskaLinija linija=new AutobuskaLinija();
				int broj=Integer.parseInt(pronadiIzbrisiSpinner.getValue().toString());
				linija=brisanjelinija.nadjiAutobuskuLiniju(session, broj);
				brisanjelinija.brisiAutobuskuLiniju(session,broj);
				JOptionPane.showMessageDialog(izbrisiBtn, "Uspješno ste izbrisali autobusku liniju.");
				polazisteIzbrisi.setText("");
				odredisteIzbrisi.setText("");
				peronIzbrisiSpinner.setValue(0);
				distancaIzbrisiSpinner.setValue(0);
				//brojModifikujSpinner.setValue(0);
				trajanjeIzbrisi.setText("");
				jednosmjernaIzbrisi.setText("");
				dvosmjernaIzbrisi.setText("");
				
				}
				catch(Exception ex1)
				{
					JOptionPane.showMessageDialog(izbrisiBtn, "Neuspješno brisanje autobuske linije.");
					JOptionPane.showMessageDialog(izbrisiBtn, ex1);
				}
				
			}
		});
		izbrisiBtn.setBounds(398, 338, 89, 23);
		panel_2.add(izbrisiBtn);
	}
	
	public void setVisible(boolean visible) {
		frmAutobuskeLinije.setVisible(visible);
	}
}
