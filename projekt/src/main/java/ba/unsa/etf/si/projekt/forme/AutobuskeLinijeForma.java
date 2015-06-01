package ba.unsa.etf.si.projekt.forme;

import org.apache.log4j.Logger;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.UIManager;

import ba.unsa.etf.si.projekt.dodatno.Validacija;
import ba.unsa.etf.si.projekt.entiteti.Autobus;
import ba.unsa.etf.si.projekt.entiteti.AutobuskaLinija;
import ba.unsa.etf.si.projekt.entiteti.Karta;
import ba.unsa.etf.si.projekt.entiteti.KorisnickiRacun;
import ba.unsa.etf.si.projekt.entiteti.MedjunarodnaKarta;
import ba.unsa.etf.si.projekt.entiteti.Nalog;
import ba.unsa.etf.si.projekt.entiteti.Radnik;
import ba.unsa.etf.si.projekt.entiteti.Rezervacija;
import ba.unsa.etf.si.projekt.hibernate.HibernateAutibuskaLinija;
import ba.unsa.etf.si.projekt.hibernate.HibernateAutobus;
import ba.unsa.etf.si.projekt.hibernate.HibernateKarta;
import ba.unsa.etf.si.projekt.hibernate.HibernateKorisnickiRacuni;
import ba.unsa.etf.si.projekt.hibernate.HibernateMedjunarodnaKarta;
import ba.unsa.etf.si.projekt.hibernate.HibernateNalog;
import ba.unsa.etf.si.projekt.hibernate.HibernateRadnik;
import ba.unsa.etf.si.projekt.hibernate.HibernateRezervacija;
import ba.unsa.etf.si.projekt.hibernate.HibernateUtil;

import com.toedter.calendar.JDateChooser;

import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

import org.hibernate.Session;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.Date;

import javax.swing.SwingConstants;

import java.awt.Toolkit;

import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

public class AutobuskeLinijeForma {
	final static Logger logger = Logger.getLogger(AutobuskeLinijeForma.class);
	private JFrame frmAutobuskeLinije;
	private JTextField polazisteDodaj;
	private JTextField odredisteDodaj;
	//private JTextField vrijemeDodaj;
	private JTextField trajanjeDodaj;
	private JTextField jednosmjernaDodaj;
	private JTextField dvosmjernaDodaj;
	private JTextField polazisteModifikuj;
	private JTextField odredisteModifikujBtn;
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
	 * @wbp.parser.entryPoint
	 */
	public AutobuskeLinijeForma() {
		initialize();
	}

	public AutobuskeLinijeForma(KorisnickiRacun kr) {
		// TODO Auto-generated constructor stub
		this.tipKorisnika = kr.getTipKorisnickogRacuna().toString();
		korisnik = kr;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAutobuskeLinije = new JFrame();
		frmAutobuskeLinije.setResizable(false);
		frmAutobuskeLinije.setIconImage(Toolkit.getDefaultToolkit().getImage(AutobuskeLinijeForma.class.getResource("/ba/unsa/etf/si/projekt/dodatno/icon.png")));
		frmAutobuskeLinije.setTitle("Autobuske linije");
		frmAutobuskeLinije.setBounds(100, 100, 583, 506);
		frmAutobuskeLinije.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAutobuskeLinije.getContentPane().setLayout(null);

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
		odjavaBtn.setBounds(468, 11, 89, 23);
		frmAutobuskeLinije.getContentPane().add(odjavaBtn);

		JButton nazadBtn = new JButton("Nazad");
		nazadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tipKorisnika == "administrator") {
					AdministratorPocetna a = new AdministratorPocetna(korisnik);
					a.setVisible(true);
					setVisible(false);
				} else if (tipKorisnika == "menadjer") {
					MenadzerPocetna m = new MenadzerPocetna(korisnik);
					m.setVisible(true);
					setVisible(false);
				}
			}
		});
		nazadBtn.setBounds(369, 11, 89, 23);
		frmAutobuskeLinije.getContentPane().add(nazadBtn);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 64, 547, 400);
		frmAutobuskeLinije.getContentPane().add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Dodaj", null, panel, null);
		panel.setLayout(null);

		polazisteDodaj = new JTextField();
		polazisteDodaj.setBounds(138, 29, 116, 20);
		panel.add(polazisteDodaj);
		polazisteDodaj.setColumns(10);

		JLabel lblPolazite = new JLabel("Polazište:");
		lblPolazite.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPolazite.setBounds(50, 32, 78, 14);
		panel.add(lblPolazite);

		odredisteDodaj = new JTextField();
		odredisteDodaj.setBounds(138, 68, 116, 20);
		panel.add(odredisteDodaj);
		odredisteDodaj.setColumns(10);

		JLabel lblOdredite = new JLabel("Odredište:");
		lblOdredite.setHorizontalAlignment(SwingConstants.TRAILING);
		lblOdredite.setBounds(50, 71, 78, 14);
		panel.add(lblOdredite);

		JLabel lblRegistracije = new JLabel("Autobus:");
		lblRegistracije.setHorizontalAlignment(SwingConstants.TRAILING);
		lblRegistracije.setBounds(39, 112, 89, 14);
		panel.add(lblRegistracije);

		MaskFormatter maska = new MaskFormatter();
		try{
			maska = new MaskFormatter("##:##");
		}
		catch( Exception e)
		{

		}
		maska.setPlaceholderCharacter('_');

		final JFormattedTextField formattedTextFieldVrijeme = new JFormattedTextField(maska);
		formattedTextFieldVrijeme.setBounds(138, 236, 116, 20);
		panel.add(formattedTextFieldVrijeme);

		final JDateChooser datumDodajDate = new JDateChooser();
		datumDodajDate.setBounds(138, 193, 116, 20);
		panel.add(datumDodajDate);

		JLabel lblImeVozaa = new JLabel("Vozač:");
		lblImeVozaa.setHorizontalAlignment(SwingConstants.TRAILING);
		lblImeVozaa.setBounds(50, 154, 78, 14);
		panel.add(lblImeVozaa);

		JLabel lblDatumPolaska = new JLabel("Datum polaska:");
		lblDatumPolaska.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDatumPolaska.setBounds(0, 195, 128, 14);
		panel.add(lblDatumPolaska);

		JLabel lblVrijemePolaska = new JLabel("Vrijeme polaska:");
		lblVrijemePolaska.setHorizontalAlignment(SwingConstants.TRAILING);
		lblVrijemePolaska.setBounds(0, 239, 128, 14);
		panel.add(lblVrijemePolaska);

		JLabel lblPeron = new JLabel("Peron:");
		lblPeron.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPeron.setBounds(306, 32, 71, 14);
		panel.add(lblPeron);

		final JSpinner peronDodajSpinner = new JSpinner();
		peronDodajSpinner.setBounds(384, 29, 116, 20);
		panel.add(peronDodajSpinner);

		JLabel lblDistanca = new JLabel("Distanca:");
		lblDistanca.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDistanca.setBounds(299, 71, 78, 14);
		panel.add(lblDistanca);

		final JSpinner distancaDodajSpinner = new JSpinner();
		distancaDodajSpinner.setBounds(384, 68, 116, 20);
		panel.add(distancaDodajSpinner);

		JLabel lblKm = new JLabel("km");
		lblKm.setBounds(511, 71, 21, 14);
		panel.add(lblKm);

		trajanjeDodaj = new JTextField();
		trajanjeDodaj.setBounds(384, 109, 116, 20);
		panel.add(trajanjeDodaj);
		trajanjeDodaj.setColumns(10);

		JLabel lblTrajanje = new JLabel("Trajanje:");
		lblTrajanje.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTrajanje.setBounds(306, 112, 71, 14);
		panel.add(lblTrajanje);

		JLabel lblH = new JLabel("h");
		lblH.setBounds(511, 112, 21, 14);
		panel.add(lblH);

		final JSpinner brojDodajSpinner = new JSpinner();
		brojDodajSpinner.setBounds(384, 151, 116, 20);
		panel.add(brojDodajSpinner);

		JLabel lblBrojLinije = new JLabel("Broj linije:");
		lblBrojLinije.setHorizontalAlignment(SwingConstants.TRAILING);
		lblBrojLinije.setBounds(306, 154, 71, 14);
		panel.add(lblBrojLinije);

		jednosmjernaDodaj = new JTextField();
		jednosmjernaDodaj.setColumns(10);
		jednosmjernaDodaj.setBounds(384, 193, 116, 20);
		panel.add(jednosmjernaDodaj);

		JLabel lblCijenaJednosmjerne = new JLabel("Jednosmjerna:");
		lblCijenaJednosmjerne.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCijenaJednosmjerne.setBounds(249, 196, 128, 14);
		panel.add(lblCijenaJednosmjerne);

		JLabel lblKm_1 = new JLabel("KM");
		lblKm_1.setBounds(511, 196, 21, 14);
		panel.add(lblKm_1);

		JLabel lblDvosmjerna = new JLabel("Dvosmjerna:");
		lblDvosmjerna.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDvosmjerna.setBounds(264, 239, 113, 14);
		panel.add(lblDvosmjerna);

		dvosmjernaDodaj = new JTextField();
		dvosmjernaDodaj.setColumns(10);
		dvosmjernaDodaj.setBounds(384, 236, 116, 20);
		panel.add(dvosmjernaDodaj);

		JLabel label_1 = new JLabel("KM");
		label_1.setBounds(511, 239, 21, 14);
		panel.add(label_1);

		final JRadioButton medunarodnaDodaj = new JRadioButton("Međunarodna");
		medunarodnaDodaj.setBounds(137, 282, 140, 23);
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
		autobusDodajCombo.setBounds(138, 109, 116, 20);
		panel.add(autobusDodajCombo);

		final JComboBox vozacDodajCombo = new JComboBox();//svi radniciiii
		HibernateRadnik r1=new HibernateRadnik();
		java.util.List sviradnici=r1.sviRadnici(session);
		for(int i=0;i<sviradnici.size();i++)
		{
			Radnik r=(Radnik)sviradnici.get(i);
			if(r.dajTipRadnogMjesta().toString()=="Vozac"){
				vozacDodajCombo.addItem(r);
			}

		}
		vozacDodajCombo.setBounds(138, 151, 116, 20);
		panel.add(vozacDodajCombo);



		final JButton dodajBtn = new JButton("Dodaj");
		dodajBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //dodavanje linija
				int d1=0,v1=0, t1=0, j1=0,d2=0;


				String izuzetak="";
				boolean postoji=false;
				try
				{
					if(datumDodajDate.getDate()==null)
					{
						d1=1;
						izuzetak+="Morate unijeti datum.";
					}
					if(formattedTextFieldVrijeme.getText().equals("__:__") ) // ne radi ovo za vrijemeee Larisino 
					{
						v1=1;
						izuzetak+="Morate unijeti vrijeme i mora biti u formatu 12:12.";
					}
					if(trajanjeDodaj.getText().length()==0)
					{
						t1=1;
						izuzetak+="Morate unijeti trajanje vožnje.";
					}
					if(jednosmjernaDodaj.getText().length()==0)
					{
						j1=1;
						izuzetak+="Morate unijeti cijenu jednosmjerne karte.";
					}
					if(dvosmjernaDodaj.getText().length()==0)
					{
						d2=1;
						izuzetak+="Morate unijeti cijenu dvosmjerne karte.";
					}

					if(d1==0 && v1==0 && t1==0 && d2==0 && j1==0)
					{
						Session session = HibernateUtil.getSessionFactory().openSession();
						HibernateAutibuskaLinija novalinija=new HibernateAutibuskaLinija();

						Autobus autobuslinije=new Autobus();//selektovani autobus
						HibernateAutobus autobus1=new HibernateAutobus();
						autobuslinije=autobus1.nadjiAutobus(session, autobusDodajCombo.getSelectedItem().toString());

						Radnik radnik=new Radnik(); //selektovani radnik
						HibernateRadnik rad1=new HibernateRadnik();
						String string=vozacDodajCombo.getSelectedItem().toString();
						String ime = null;
						if(string.contains(" ")){
							ime= string.substring(0, string.indexOf(" "));
						}
						radnik=rad1.nadjiRadnika(session, ((Radnik)vozacDodajCombo.getSelectedItem()).getJmbg());

						Date datum=datumDodajDate.getDate();
						Calendar cal=Calendar.getInstance();
						cal.setTime(datum);
						int godina1=cal.get(Calendar.YEAR);
						int mjesec1=cal.get(Calendar.MONTH)+1;
						int dan1=cal.get(Calendar.DAY_OF_MONTH);

						String[] vrijeme= formattedTextFieldVrijeme.getText().split(":");
						int sati=Integer.valueOf(vrijeme[0]);
						int minute=Integer.valueOf(vrijeme[1]);

						String peron=peronDodajSpinner.getValue().toString();
						int brojperona=Integer.parseInt(peron);
						String distanca=distancaDodajSpinner.getValue().toString();
						int distanca1=Integer.parseInt(distanca);
						int trajanje=Integer.parseInt(trajanjeDodaj.getText());
						int brojlinije=Integer.parseInt(brojDodajSpinner.getValue().toString());

						double jednosmjerna=Double.valueOf(jednosmjernaDodaj.getText());
						double dvosmjerna=Double.valueOf(dvosmjernaDodaj.getText());

						boolean medjunarodnaLinija=false;

						if(medunarodnaDodaj.isSelected())
						{
							medjunarodnaLinija=true;
						}
						HibernateAutibuskaLinija svelinije=new HibernateAutibuskaLinija();
						java.util.List lista=svelinije.sveLinije(session);
						for(int i=0;i<lista.size();i++)
						{
							AutobuskaLinija a=new AutobuskaLinija();
							if(a.getBrojLinije()==brojlinije)postoji=true;
						}
						if(postoji==false)
						{
							novalinija.dodajAutobuskuLiniju(session, polazisteDodaj.getText(), odredisteDodaj.getText(),autobuslinije , radnik, godina1, mjesec1, dan1, sati, minute, brojperona, distanca1, trajanje, brojlinije, jednosmjerna, dvosmjerna, medjunarodnaLinija);
							JOptionPane.showMessageDialog(dodajBtn, "Uspješno je dodana autobuska linija.");
							polazisteDodaj.setText("");
							odredisteDodaj.setText("");
							peronDodajSpinner.setValue(0);
							distancaDodajSpinner.setValue(0);
							brojDodajSpinner.setValue(0);
							trajanjeDodaj.setText("");
							jednosmjernaDodaj.setText("");
							dvosmjernaDodaj.setText("");
							formattedTextFieldVrijeme.setText("");
							datumDodajDate.setDate(null);
						}
						else
						{
							JOptionPane.showMessageDialog(dodajBtn, "Već postoji linija sa tim rednim brojem linije.");   
							brojDodajSpinner.setValue(0);

						}
					}
					else
					{
						JOptionPane.showMessageDialog(dodajBtn, izuzetak);     
					}
				}
				catch(Exception e11)
				{
					JOptionPane.showMessageDialog(dodajBtn, "Neuspješno dodavanje autobuske linije.\n"+ e11.getMessage());
				}
			}
		});
		dodajBtn.setBounds(443, 338, 89, 23);
		panel.add(dodajBtn);





		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Modifikuj", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel label = new JLabel("Broj linije:");
		label.setHorizontalAlignment(SwingConstants.TRAILING);
		label.setBounds(77, 28, 89, 14);
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
		autobusModifikujCombo.setBounds(141, 159, 116, 20);
		panel_1.add(autobusModifikujCombo);

		JLabel label_5 = new JLabel("Vozač:");
		label_5.setHorizontalAlignment(SwingConstants.TRAILING);
		label_5.setBounds(66, 202, 60, 14);
		panel_1.add(label_5);

		final JFormattedTextField formattedTextFieldVrijemeMod = new JFormattedTextField(maska);
		formattedTextFieldVrijemeMod.setBounds(141, 286, 116, 20);
		panel_1.add(formattedTextFieldVrijemeMod);

		final JComboBox vozacModifikujCombo = new JComboBox();
		HibernateRadnik r2=new HibernateRadnik();
		java.util.List sviradnici1=r2.sviRadnici(session);
		for(int i=0;i<sviradnici1.size();i++)
		{
			Radnik r=(Radnik)sviradnici1.get(i);
			if(r.dajTipRadnogMjesta().toString()=="Vozac"){

				vozacModifikujCombo.addItem(r);
			}

		}
		vozacModifikujCombo.setBounds(141, 201, 116, 20);
		panel_1.add(vozacModifikujCombo);

		final JRadioButton medunarodnaModifikuj = new JRadioButton("Međunarodna");
		medunarodnaModifikuj.setBounds(381, 285, 149, 23);
		panel_1.add(medunarodnaModifikuj);

		final JSpinner peronModifikujSpinner = new JSpinner();
		peronModifikujSpinner.setBounds(374, 79, 116, 20);
		panel_1.add(peronModifikujSpinner);

		final JSpinner distancaModifikujSpinner = new JSpinner();
		distancaModifikujSpinner.setBounds(374, 118, 116, 20);
		panel_1.add(distancaModifikujSpinner);

		final JDateChooser polazakModifikujDate = new JDateChooser();
		polazakModifikujDate.setBounds(141, 243, 116, 20);
		panel_1.add(polazakModifikujDate);

		final JButton pronadjiModifikujBtn = new JButton("Pronađi");
		pronadjiModifikujBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //pronadji liniju
				try
				{
					Session session = HibernateUtil.getSessionFactory().openSession();
					HibernateAutibuskaLinija trazenjelinija=new HibernateAutibuskaLinija();
					AutobuskaLinija lin;
					int brojlinijepretraga=Integer.parseInt(brojPretragaSpinner.getValue().toString());
					
					
					lin=trazenjelinija.nadjiAutobuskuLiniju(session, brojlinijepretraga);
					
					if(trazenjelinija!=null)
					{
						polazisteModifikuj.setText(lin.getPolaziste());
						odredisteModifikujBtn.setText(lin.getOdrediste());

						String imevozaca=lin.getVozac().getIme();
						String jmbgvozaca=lin.getVozac().getJmbg();
						String lista=imevozaca+" "+jmbgvozaca;
						vozacModifikujCombo.setSelectedItem(lista);

						String aut=lin.getAutobus().getRegistracija();
						autobusModifikujCombo.setSelectedItem(aut);
						Date d=new Date();
						Calendar cal=Calendar.getInstance();
						cal.set(Calendar.YEAR,lin.getDatumPolaska_godina());
						cal.set(Calendar.MONTH, lin.getDatumPolaska_mjesec()-1);
						cal.set(Calendar.DAY_OF_MONTH,lin.getDatumPolaska_dan());
						d=cal.getTime();
						polazakModifikujDate.setDate(d);
						peronModifikujSpinner.setValue(lin.getPeron());
						//brojModifikujSpinner.setValue(lin.getBrojLinije());
						distancaModifikujSpinner.setValue(lin.getDistanca());
						formattedTextFieldVrijemeMod.setText(lin.getVrijemePolaska_sati()+":"+lin.getVrijemePolaska_minute());

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
					JOptionPane.showMessageDialog(pronadjiModifikujBtn, "Neuspješna pretraga.\n"+e12.getMessage());
				}

			}
		});
		pronadjiModifikujBtn.setBounds(304, 24, 89, 23);
		panel_1.add(pronadjiModifikujBtn);

		JLabel label_2 = new JLabel("Polazište:");
		label_2.setHorizontalAlignment(SwingConstants.TRAILING);
		label_2.setBounds(52, 80, 74, 14);
		panel_1.add(label_2);

		polazisteModifikuj = new JTextField();
		polazisteModifikuj.setColumns(10);
		polazisteModifikuj.setBounds(141, 79, 116, 20);
		panel_1.add(polazisteModifikuj);

		odredisteModifikujBtn = new JTextField();
		odredisteModifikujBtn.setColumns(10);
		odredisteModifikujBtn.setBounds(141, 118, 116, 20);
		panel_1.add(odredisteModifikujBtn);

		JLabel label_3 = new JLabel("Odredište:");
		label_3.setHorizontalAlignment(SwingConstants.TRAILING);
		label_3.setBounds(47, 119, 79, 14);
		panel_1.add(label_3);

		JLabel label_4 = new JLabel("Autobus:");
		label_4.setHorizontalAlignment(SwingConstants.TRAILING);
		label_4.setBounds(52, 160, 74, 14);
		panel_1.add(label_4);




		JLabel label_6 = new JLabel("Datum polaska:");
		label_6.setHorizontalAlignment(SwingConstants.TRAILING);
		label_6.setBounds(0, 243, 126, 14);
		panel_1.add(label_6);



		JLabel label_7 = new JLabel("Vrijeme polaska:");
		label_7.setHorizontalAlignment(SwingConstants.TRAILING);
		label_7.setBounds(0, 287, 126, 14);
		panel_1.add(label_7);



		JLabel label_8 = new JLabel("Dvosmjerna:");
		label_8.setHorizontalAlignment(SwingConstants.TRAILING);
		label_8.setBounds(248, 245, 116, 14);
		panel_1.add(label_8);

		dvosmjernaModifikuj = new JTextField();
		dvosmjernaModifikuj.setColumns(10);
		dvosmjernaModifikuj.setBounds(374, 243, 116, 20);
		panel_1.add(dvosmjernaModifikuj);

		jednosmjernaModifikuj = new JTextField();
		jednosmjernaModifikuj.setColumns(10);
		jednosmjernaModifikuj.setBounds(374, 200, 116, 20);
		panel_1.add(jednosmjernaModifikuj);

		JLabel label_9 = new JLabel("Jednosmjerna:");
		label_9.setHorizontalAlignment(SwingConstants.TRAILING);
		label_9.setBounds(248, 202, 116, 14);
		panel_1.add(label_9);

		trajanjeModifikuj = new JTextField();
		trajanjeModifikuj.setColumns(10);
		trajanjeModifikuj.setBounds(374, 159, 116, 20);
		panel_1.add(trajanjeModifikuj);

		JLabel label_11 = new JLabel("Trajanje:");
		label_11.setHorizontalAlignment(SwingConstants.TRAILING);
		label_11.setBounds(292, 161, 72, 14);
		panel_1.add(label_11);



		JLabel label_12 = new JLabel("Distanca:");
		label_12.setHorizontalAlignment(SwingConstants.TRAILING);
		label_12.setBounds(292, 120, 72, 14);
		panel_1.add(label_12);



		JLabel label_13 = new JLabel("Peron:");
		label_13.setHorizontalAlignment(SwingConstants.TRAILING);
		label_13.setBounds(306, 81, 58, 14);
		panel_1.add(label_13);

		final JButton modifikujBtn = new JButton("Spasi izmjene");
		modifikujBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //modifikovanje linije
				int d1=0,v1=0, t1=0, j1=0,d2=0;
				String izuzetak="";
				try
				{
					Session session = HibernateUtil.getSessionFactory().openSession();
					
					if(polazakModifikujDate.getDate()==null)
					{
						d1=1;
						izuzetak+="Morate unijeti datum.";
					}
					if(formattedTextFieldVrijemeMod.getText().equals("__:__") ) // ne radi ovo za vrijemeee Larisino 
					{
						v1=1;
						izuzetak+="Vrijeme mora biti u formatu 12:12.";
					}
					if(trajanjeModifikuj.getText().length()==0)
					{
						t1=1;
						izuzetak+="Morate unijeti trajanje vožnje.";
					}
					if(jednosmjernaModifikuj.getText().length()==0)
					{
						j1=1;
						izuzetak+="Morate unijeti cijenu jednosmjerne karte.";
					}
					if(dvosmjernaModifikuj.getText().length()==0)
					{
						d2=1;
						izuzetak+="Morate unijeti cijenu dvosmjerne karte.";
					}
					if(d1==0 && v1==0 && t1==0 && d2==0 && j1==0)
					{

						HibernateAutibuskaLinija izmjenalinija=new HibernateAutibuskaLinija();

						Autobus autobuslinije=new Autobus();//selektivani autobus
						HibernateAutobus autobus1=new HibernateAutobus();
						autobuslinije=autobus1.nadjiAutobus(session, autobusModifikujCombo.getSelectedItem().toString());

						Radnik radnik=new Radnik(); //selektovani radnik
						HibernateRadnik rad1=new HibernateRadnik();
						String string=vozacModifikujCombo.getSelectedItem().toString();
						String ime = null;
						if(string.contains(" ")){
							ime= string.substring(0, string.indexOf(" "));
						}
						radnik=rad1.nadjiRadnika(session, ((Radnik)vozacModifikujCombo.getSelectedItem()).getJmbg());


						Date datum=polazakModifikujDate.getDate();
						Calendar cal=Calendar.getInstance();
						cal.setTime(datum);
						int godina1=cal.get(Calendar.YEAR);
						int mjesec1=cal.get(Calendar.MONTH)+1;
						int dan1=cal.get(Calendar.DAY_OF_MONTH);
						String[] vrijeme = formattedTextFieldVrijemeMod.getText().split(":");
						int sati=Integer.valueOf(vrijeme[0]);
						int minute=Integer.valueOf(vrijeme[1]);


						String peron=peronModifikujSpinner.getValue().toString();
						double per=Double.parseDouble(peron);
						int brojperona=(int)Math.round(per);
						String distanca=distancaModifikujSpinner.getValue().toString();
						double distanca1=Double.parseDouble(distanca);
						double trajanje=Double.parseDouble(trajanjeModifikuj.getText());
						int brojlinije=Integer.parseInt(brojPretragaSpinner.getValue().toString());

						double jednosmjerna=Double.parseDouble(jednosmjernaModifikuj.getText());
						double dvosmjerna=Double.parseDouble(dvosmjernaModifikuj.getText());

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
						formattedTextFieldVrijemeMod.setText("");
						polazakModifikujDate.setDate(null);
					}
					else
					{
						JOptionPane.showMessageDialog(modifikujBtn, izuzetak); 
					}
				}
				catch(Exception e13)
				{
					JOptionPane.showMessageDialog(modifikujBtn,"Nespješno modifikovanje autobuske linije.\n"+e13.getMessage() );
				}


			}
		});
		modifikujBtn.setBounds(423, 338, 109, 23);
		panel_1.add(modifikujBtn);

		JLabel label_14 = new JLabel("KM");
		label_14.setBounds(500, 204, 32, 14);
		panel_1.add(label_14);

		JLabel label_15 = new JLabel("KM");
		label_15.setBounds(500, 249, 32, 14);
		panel_1.add(label_15);

		JLabel label_29 = new JLabel("km");
		label_29.setBounds(499, 121, 21, 14);
		panel_1.add(label_29);

		JLabel label_30 = new JLabel("h");
		label_30.setBounds(499, 162, 21, 14);
		panel_1.add(label_30);




		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Izbriši", null, panel_2, null);
		panel_2.setLayout(null);

		JLabel label_10 = new JLabel("Broj linije:");
		label_10.setHorizontalAlignment(SwingConstants.TRAILING);
		label_10.setBounds(77, 30, 89, 14);
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
		autobusIzbrisiCombo.setBounds(138, 164, 116, 20);
		panel_2.add(autobusIzbrisiCombo);

		final JComboBox vozacIzbrisiCombo = new JComboBox();
		HibernateRadnik r3=new HibernateRadnik();
		java.util.List sviradnici3=r3.sviRadnici(session);
		for(int i=0;i<sviradnici3.size();i++)
		{
			Radnik r=(Radnik)sviradnici3.get(i);
			if(r.dajTipRadnogMjesta().toString()=="Vozac"){
				vozacIzbrisiCombo.addItem(r);
			}


		}
		vozacIzbrisiCombo.setBounds(138, 206, 116, 20);
		panel_2.add(vozacIzbrisiCombo);

		final JSpinner peronIzbrisiSpinner = new JSpinner();
		peronIzbrisiSpinner.setBounds(380, 84, 116, 20);
		panel_2.add(peronIzbrisiSpinner);

		final JSpinner distancaIzbrisiSpinner = new JSpinner();
		distancaIzbrisiSpinner.setBounds(380, 123, 116, 20);
		panel_2.add(distancaIzbrisiSpinner);

		final JRadioButton medunarodnaIzbrisi = new JRadioButton("Međunarodna");
		medunarodnaIzbrisi.setBounds(387, 290, 139, 23);
		panel_2.add(medunarodnaIzbrisi);

		trajanjeIzbrisi = new JTextField();
		trajanjeIzbrisi.setEditable(false);
		trajanjeIzbrisi.setColumns(10);
		trajanjeIzbrisi.setBounds(380, 164, 116, 20);
		panel_2.add(trajanjeIzbrisi);

		jednosmjernaIzbrisi = new JTextField();
		jednosmjernaIzbrisi.setEditable(false);
		jednosmjernaIzbrisi.setColumns(10);
		jednosmjernaIzbrisi.setBounds(380, 205, 116, 20);
		panel_2.add(jednosmjernaIzbrisi);

		dvosmjernaIzbrisi = new JTextField();
		dvosmjernaIzbrisi.setEditable(false);
		dvosmjernaIzbrisi.setColumns(10);
		dvosmjernaIzbrisi.setBounds(380, 248, 116, 20);
		panel_2.add(dvosmjernaIzbrisi);

		final JDateChooser polazakIzbrisiDate = new JDateChooser();
		polazakIzbrisiDate.setBounds(138, 248, 116, 20);
		panel_2.add(polazakIzbrisiDate);

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
						String jmbgvozaca=linija.getVozac().getJmbg();
						String lista=imevozaca+" "+jmbgvozaca;
						vozacIzbrisiCombo.setSelectedItem(lista);

						String aut=linija.getAutobus().getRegistracija();
						autobusIzbrisiCombo.setSelectedItem(aut);
						Date d=new Date();
						Calendar cal=Calendar.getInstance();
						cal.set(Calendar.YEAR,linija.getDatumPolaska_godina());
						cal.set(Calendar.MONTH,linija.getDatumPolaska_mjesec()-1);
						cal.set(Calendar.DAY_OF_MONTH,linija.getDatumPolaska_dan());
						d=cal.getTime();
						polazakIzbrisiDate.setDate(d);
						peronIzbrisiSpinner.setValue(linija.getPeron());
						distancaIzbrisiSpinner.setValue(linija.getDistanca());
						vrijemeIzbrisi.setText(linija.getVrijemePolaska_sati()+":"+linija.getVrijemePolaska_minute());

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
					JOptionPane.showMessageDialog(pronadjiIzbrisiBtn, "Neuspješna pretraga.\n"+ex.getMessage() );

				}

			}
		});
		pronadjiIzbrisiBtn.setBounds(304, 26, 89, 23);
		panel_2.add(pronadjiIzbrisiBtn);


		JLabel label_16 = new JLabel("KM");
		label_16.setBounds(506, 209, 26, 14);
		panel_2.add(label_16);

		JLabel label_17 = new JLabel("KM");
		label_17.setBounds(506, 254, 26, 14);
		panel_2.add(label_17);

		JLabel label_18 = new JLabel("Peron:");
		label_18.setHorizontalAlignment(SwingConstants.TRAILING);
		label_18.setBounds(311, 86, 58, 14);
		panel_2.add(label_18);

		JLabel label_19 = new JLabel("Distanca:");
		label_19.setHorizontalAlignment(SwingConstants.TRAILING);
		label_19.setBounds(297, 125, 72, 14);
		panel_2.add(label_19);

		JLabel label_20 = new JLabel("Trajanje:");
		label_20.setHorizontalAlignment(SwingConstants.TRAILING);
		label_20.setBounds(297, 166, 72, 14);
		panel_2.add(label_20);

		JLabel label_21 = new JLabel("Jednosmjerna:");
		label_21.setHorizontalAlignment(SwingConstants.TRAILING);
		label_21.setBounds(253, 207, 116, 14);
		panel_2.add(label_21);

		JLabel label_22 = new JLabel("Dvosmjerna:");
		label_22.setHorizontalAlignment(SwingConstants.TRAILING);
		label_22.setBounds(264, 250, 105, 14);
		panel_2.add(label_22);

		JLabel label_23 = new JLabel("Polazište:");
		label_23.setHorizontalAlignment(SwingConstants.TRAILING);
		label_23.setBounds(48, 86, 79, 14);
		panel_2.add(label_23);

		polazisteIzbrisi = new JTextField();
		polazisteIzbrisi.setEditable(false);
		polazisteIzbrisi.setColumns(10);
		polazisteIzbrisi.setBounds(138, 84, 116, 20);
		panel_2.add(polazisteIzbrisi);

		odredisteIzbrisi = new JTextField();
		odredisteIzbrisi.setEditable(false);
		odredisteIzbrisi.setColumns(10);
		odredisteIzbrisi.setBounds(138, 123, 116, 20);
		panel_2.add(odredisteIzbrisi);

		JLabel label_24 = new JLabel("Odredište:");
		label_24.setHorizontalAlignment(SwingConstants.TRAILING);
		label_24.setBounds(43, 125, 84, 14);
		panel_2.add(label_24);

		JLabel label_25 = new JLabel("Autobus:");
		label_25.setHorizontalAlignment(SwingConstants.TRAILING);
		label_25.setBounds(48, 166, 79, 14);
		panel_2.add(label_25);



		JLabel label_26 = new JLabel("Vozač:");
		label_26.setHorizontalAlignment(SwingConstants.TRAILING);
		label_26.setBounds(62, 208, 65, 14);
		panel_2.add(label_26);

		JLabel label_27 = new JLabel("Datum polaska:");
		label_27.setHorizontalAlignment(SwingConstants.TRAILING);
		label_27.setBounds(16, 249, 111, 14);
		panel_2.add(label_27);



		vrijemeIzbrisi = new JTextField();
		vrijemeIzbrisi.setEditable(false);
		vrijemeIzbrisi.setColumns(10);
		vrijemeIzbrisi.setBounds(138, 291, 116, 20);
		panel_2.add(vrijemeIzbrisi);

		JLabel label_28 = new JLabel("Vrijeme polaska:");
		label_28.setHorizontalAlignment(SwingConstants.TRAILING);
		label_28.setBounds(2, 293, 125, 14);
		panel_2.add(label_28);

		final JButton izbrisiBtn = new JButton("Izbriši");
		izbrisiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //brisanje autobusa
				String izuzetak="";
				try
				{
					boolean brisi=true;
					Session session = HibernateUtil.getSessionFactory().openSession();
					HibernateAutibuskaLinija brisanjelinija=new HibernateAutibuskaLinija();
					int broj=Integer.parseInt(pronadiIzbrisiSpinner.getValue().toString());

					//ako je linija dodijeljena rezervaciji ili prodaji
					java.util.List rezervacije=HibernateRezervacija.sveRezervacije(session);
					for(int i=0;i<rezervacije.size();i++)
					{
						Rezervacija r=(Rezervacija)rezervacije.get(i);
						AutobuskaLinija linija1=HibernateAutibuskaLinija.nadjiAutobuskuLiniju(session,broj );
						if(r.getLinija()==linija1)
						{
							brisi=false;
							izuzetak+="Ne možete brisati liniju, jer postoje rezervacije vezena za nju.";
							break;
						}
					}

					java.util.List karte=HibernateKarta.sveKarte(session);
					for(int i=0;i<karte.size();i++)
					{
						if (!brisi) {
							break;
						}
						Karta k=(Karta)karte.get(i);
						AutobuskaLinija linija1=HibernateAutibuskaLinija.nadjiAutobuskuLiniju(session,broj );
						if(k.getLinija()==linija1)
						{
							brisi=false;
							izuzetak+="Ne možete brisati liniju, jer su za nju izdate karte.";
							break;
						}
						
					}

					java.util.List nalog=HibernateNalog.sviNalozi(session);
					for(int i=0;i<nalog.size();i++)
					{
						if (!brisi) {
							break;
						}
						Nalog n=(Nalog)nalog.get(i);
						AutobuskaLinija linija1=HibernateAutibuskaLinija.nadjiAutobuskuLiniju(session,broj );
						if(n.getAutobuskaLinija()==linija1)
						{
							brisi=false;
							izuzetak+="Ne možete brisati linije, jer postoji nalog kreiran za nju.";
							break;
						}
						
					}

					java.util.List medjunarodne=HibernateMedjunarodnaKarta.sveMedjunarodneKarte(session);
					for(int i=0;i<medjunarodne.size();i++)
					{
						if (!brisi) {
							break;
						}
						MedjunarodnaKarta k=(MedjunarodnaKarta)medjunarodne.get(i);
						AutobuskaLinija linija1=HibernateAutibuskaLinija.nadjiAutobuskuLiniju(session,broj );
						if(k.getLinija()==linija1)
						{
							brisi=false;
							izuzetak+="Ne možete brisati linju, jer je prodata karta za nju.";
							break;
						}

					}

					AutobuskaLinija linija=new AutobuskaLinija();
					if(brisi==true)
					{
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
						vrijemeIzbrisi.setText("");
						polazakIzbrisiDate.setDate(null);
						pronadiIzbrisiSpinner.setValue(0);
					}
					else
					{
						JOptionPane.showMessageDialog(izbrisiBtn, izuzetak);
					}
				}
				catch(Exception ex1)
				{
					JOptionPane.showMessageDialog(izbrisiBtn, "Neuspješno brisanje autobuske linije.\n"+ ex1.getMessage());
				}

			}
		});
		izbrisiBtn.setBounds(443, 338, 89, 23);
		panel_2.add(izbrisiBtn);

		JLabel label_31 = new JLabel("km");
		label_31.setBounds(507, 126, 21, 14);
		panel_2.add(label_31);

		JLabel label_32 = new JLabel("h");
		label_32.setBounds(507, 167, 21, 14);
		panel_2.add(label_32);
	}

	public void setVisible(boolean visible) {
		frmAutobuskeLinije.setVisible(visible);
	}
}