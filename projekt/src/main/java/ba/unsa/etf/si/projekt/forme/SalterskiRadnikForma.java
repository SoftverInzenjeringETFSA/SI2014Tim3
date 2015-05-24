package ba.unsa.etf.si.projekt.forme;
import org.apache.log4j.Logger;

import java.awt.EventQueue;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ba.unsa.etf.si.projekt.dodatno.GenerisanjePDF;
import ba.unsa.etf.si.projekt.dodatno.Java2sAutoComboBox;
import ba.unsa.etf.si.projekt.dodatno.Validacija;
import ba.unsa.etf.si.projekt.entiteti.AutobuskaLinija;
import ba.unsa.etf.si.projekt.entiteti.Rezervacija;
import ba.unsa.etf.si.projekt.entiteti.TipKarte;
import ba.unsa.etf.si.projekt.hibernate.HibernateAutibuskaLinija;
import ba.unsa.etf.si.projekt.hibernate.HibernateKarta;
import ba.unsa.etf.si.projekt.hibernate.HibernateMedjunarodnaKarta;
import ba.unsa.etf.si.projekt.hibernate.HibernateRezervacija;
import ba.unsa.etf.si.projekt.hibernate.HibernateUtil;

import com.toedter.calendar.JDateChooser;

import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JList;

import org.hibernate.Session;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import javax.transaction.Transaction;

import java.awt.Toolkit;

public class SalterskiRadnikForma implements ActionListener{
	final static Logger logger = Logger.getLogger(SalterskiRadnikForma.class);
	private JFrame frmalterskiRadnik;
	private JTextField imeProdaja;
	private JTextField prezimeProdaja;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JPanel panel;
	private JComboBox comboBox_2;
	private JComboBox comboBox_3;
	private JPanel panel_2;
	private JLabel cijenaRezervacije;
	private JDateChooser datumRezervacijaDate;
	private JRadioButton jednosmjernaRezervacije;
	private JButton btnIsplati;
	private JComboBox comboBox_4;
	private JComboBox comboBox_5;
	private JRadioButton povratnaModifikacije;
	private JRadioButton jednosmjernaModifikacije;
	/**
	 * @wbp.nonvisual location=322,309
	 */
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField imeRezervacije;
	private JTextField prezimeRezervacije;
	private JTextField textField;
	private JTextField textField_1;
	private JPanel panel_4;
	private AutobuskaLinija staralinija;
	private String staroime;
	private String staroprezime;
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
					logger.error("Greška! " + e.getMessage() , e);
				}
				try {
					SalterskiRadnikForma window = new SalterskiRadnikForma();
					window.frmalterskiRadnik.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("Greška! " + e.getMessage() , e);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SalterskiRadnikForma() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmalterskiRadnik = new JFrame();
		frmalterskiRadnik.setIconImage(Toolkit.getDefaultToolkit().getImage(SalterskiRadnikForma.class.getResource("/ba/unsa/etf/si/projekt/dodatno/icon.png")));
		frmalterskiRadnik.setTitle("Šalterski radnik");
		frmalterskiRadnik.setBounds(100, 100, 629, 497);
		frmalterskiRadnik.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmalterskiRadnik.getContentPane().setLayout(null);
		
		JButton button = new JButton("Odjava");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrijavaForma p = new PrijavaForma();
				p.setVisible(true);
				setVisible(false);
			}
		});
		button.setBounds(514, 11, 89, 23);
		frmalterskiRadnik.getContentPane().add(button);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 45, 593, 402);
		frmalterskiRadnik.getContentPane().add(tabbedPane);
		
		panel = new JPanel();
		tabbedPane.addTab("Prodaja", null, panel, null);
		panel.setLayout(null);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<String> odredista = new ArrayList<String>();
		
		JPanel panel_7 = new JPanel();
        panel_7.setBorder(new TitledBorder(null, "Tip karte", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_7.setBounds(63, 127, 207, 90);
        panel.add(panel_7);
        panel_7.setLayout(null);
        
        final JRadioButton jednosmjernaProdaja = new JRadioButton("Jednosmjerna");
        jednosmjernaProdaja.setBounds(38, 22, 149, 23);
        panel_7.add(jednosmjernaProdaja);
        jednosmjernaProdaja.setSelected(true);
        
        JRadioButton povratnaProdaja = new JRadioButton("Povratna");
        povratnaProdaja.setBounds(38, 53, 149, 23);
        panel_7.add(povratnaProdaja);
        
        //Group the radio buttons.
        ButtonGroup group = new ButtonGroup();
        group.add(jednosmjernaProdaja);
        group.add(povratnaProdaja);
        
		
		Calendar cal = Calendar.getInstance();
		for (AutobuskaLinija linija : (List<AutobuskaLinija>) HibernateAutibuskaLinija.sveLinije(session)) {

			if (!odredista.contains(linija.getOdrediste()) && cal.get(Calendar.YEAR) == linija.getDatumPolaska_godina()
					&& (cal.get(Calendar.MONTH)+1) == linija.getDatumPolaska_mjesec()
					&& cal.get(Calendar.DAY_OF_MONTH) == linija.getDatumPolaska_dan()) {
				odredista.add(linija.getOdrediste());
			}
		}
		
		comboBox_1 = new Java2sAutoComboBox(new ArrayList<String>());
    	comboBox_1.setBounds(112, 81, 142, 20);
		panel.add(comboBox_1);
		
		comboBox = new Java2sAutoComboBox(odredista);
		
		comboBox.addActionListener(this);
		
		
		comboBox.setBounds(112, 34, 142, 20);
		panel.add(comboBox);
		
		
		JLabel lblOdredite = new JLabel("Odredište:");
		lblOdredite.setHorizontalAlignment(SwingConstants.TRAILING);
		lblOdredite.setBounds(24, 37, 78, 14);
		panel.add(lblOdredite);
		
		
		
		JLabel lblVrijeme = new JLabel("Vrijeme:");
		lblVrijeme.setHorizontalAlignment(SwingConstants.TRAILING);
		lblVrijeme.setBounds(36, 84, 66, 14);
		panel.add(lblVrijeme);
		
		JPanel panel_1 = new JPanel();
		panel_1.setName("");
		panel_1.setToolTipText("");
		panel_1.setBorder(BorderFactory.createTitledBorder("Međunarodni saobraćaj"));
		panel_1.setBounds(296, 27, 253, 143);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label_1 = new JLabel("Ime:");
		label_1.setHorizontalAlignment(SwingConstants.TRAILING);
		label_1.setBounds(27, 49, 38, 14);
		panel_1.add(label_1);
		
		imeProdaja = new JTextField();
		imeProdaja.setColumns(10);
		imeProdaja.setBounds(75, 46, 160, 20);
		panel_1.add(imeProdaja);
		
		prezimeProdaja = new JTextField();
		prezimeProdaja.setColumns(10);
		prezimeProdaja.setBounds(75, 93, 160, 20);
		panel_1.add(prezimeProdaja);
		
		JLabel label_2 = new JLabel("Prezime:");
		label_2.setHorizontalAlignment(SwingConstants.TRAILING);
		label_2.setBounds(4, 96, 61, 14);
		panel_1.add(label_2);
		
		final JLabel cijenaProdajaLabel = new JLabel("");
		cijenaProdajaLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cijenaProdajaLabel.setBounds(239, 256, 104, 33);
		panel.add(cijenaProdajaLabel);
		
		JLabel lblCijenakm = new JLabel("Cijena(KM):");
		lblCijenakm.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblCijenakm.setBounds(63, 252, 151, 41);
		panel.add(lblCijenakm);
		
		final JButton prodajaBtn = new JButton("Završi");
		prodajaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { //dodavanje kartii
				
				try
				{
					Session session = HibernateUtil.getSessionFactory().openSession();
					HibernateKarta karta=new HibernateKarta();
					TipKarte k=TipKarte.jednosmjerna;
								
					Calendar cal=Calendar.getInstance();
					int godina=cal.get(Calendar.YEAR);
					int mjesec=cal.get(Calendar.MONTH)+1;
					int dan=cal.get(Calendar.DAY_OF_MONTH);
					
					if (comboBox.getItemCount() == 0) {
						JOptionPane.showMessageDialog(prodajaBtn, "Nema odredišta!");
						return;
					}
					if (comboBox_1.getItemCount() == 0) {
						JOptionPane.showMessageDialog(prodajaBtn, "Nema autobuske linije za to odredište!");
						return;
					}
					
					String[] vrijeme=comboBox_1.getSelectedItem().toString().split(":");
					
					if (vrijeme.length != 2) {
						JOptionPane.showMessageDialog(prodajaBtn, "Vrijeme mora biti u formatu sati:minute!");
						return;
					} else if (!Validacija.jeInt(vrijeme[0]) || !Validacija.jeInt(vrijeme[1])) {
						JOptionPane.showMessageDialog(prodajaBtn, "Sati i minute moraju biti cijeli brojevi!");
						return;
					}
					
					int sati=cal.get(Calendar.HOUR);
					int minute=cal.get(Calendar.MINUTE);
					
					
					AutobuskaLinija linija=null;
					HibernateAutibuskaLinija linija1=new HibernateAutibuskaLinija();
					linija=linija1.NadjiAutobuskuLinijuOdrediste(session, comboBox.getSelectedItem().toString(), godina, mjesec, dan, 
							Integer.valueOf(vrijeme[0]), Integer.valueOf(vrijeme[1]));
					
					if (linija == null) {
						JOptionPane.showMessageDialog(prodajaBtn, "Ne postoji ta linija!");
						return;
					}
					
					double cijena=linija.getCijenaJednosmjerna();
					if(jednosmjernaProdaja.isSelected()==false)
					{
						k=TipKarte.dvosmjerna;
						//cijenu u labelu upisat
						cijena=linija.getCijenaDvosmjerna();
						
					}				
					String ime = imeProdaja.getText();
					String prezime = prezimeProdaja.getText();
					
					if (linija.getMedjunarodna() && (ime.isEmpty() || prezime.isEmpty())) {
						JOptionPane.showMessageDialog(prodajaBtn, "Morate unijeti ime i prezime za međunarodnu liniju!");
						return;
					}
					
					if (linija.getMedjunarodna() && (!Validacija.jeTekst(ime) || !Validacija.jeTekst(prezime))) {
						JOptionPane.showMessageDialog(prodajaBtn, "Ime i prezime sadrže samo slova!");
						return;
					}
					
					if (ime.isEmpty() && prezime.isEmpty() && !linija.getMedjunarodna()) {
						karta.dodajKartu(session, linija, godina, mjesec, dan, sati, minute, k, cijena);
					} else if(!ime.isEmpty() && !prezime.isEmpty()){
						HibernateMedjunarodnaKarta.dodajKartu(session, linija, godina, mjesec, dan, sati, minute, k, cijena, ime, prezime);
					}
					
					
					
					cijenaProdajaLabel.setText(String.valueOf(cijena));
					JOptionPane.showMessageDialog(prodajaBtn, "Karta je prodata.");
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(prodajaBtn, "Neuspješna prodaja.");
					//JOptionPane.showMessageDialog(prodajaBtn, ex);
					
				}
			}
		});
		prodajaBtn.setBounds(415, 302, 134, 33);
		panel.add(prodajaBtn);
		
		panel_2 = new JPanel();
		tabbedPane.addTab("Rezervacija", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel label_5 = new JLabel("Odredište:");
		label_5.setHorizontalAlignment(SwingConstants.TRAILING);
		label_5.setBounds(24, 36, 83, 14);
		panel_2.add(label_5);
		
		JLabel label_6 = new JLabel("Vrijeme:");
		label_6.setHorizontalAlignment(SwingConstants.TRAILING);
		label_6.setBounds(36, 83, 71, 14);
		panel_2.add(label_6);
		
		datumRezervacijaDate = new JDateChooser();
		datumRezervacijaDate.setDateFormatString("dd/M/yyy");
		datumRezervacijaDate.setBounds(117, 234, 142, 20);
		panel_2.add(datumRezervacijaDate);
		
		JLabel lblPolazak = new JLabel("Polazak:");
		lblPolazak.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPolazak.setBounds(14, 238, 94, 14);
		panel_2.add(lblPolazak);
		
		
		final JList rezervacijeList = new JList();   //popuni listu sa postojecim rezervacijama
		rezervacijeList.setModel(new AbstractListModel()
		{
			Session session = HibernateUtil.getSessionFactory().openSession();
			java.util.List rezervacijelista=HibernateRezervacija.sveRezervacije(session);
			public int getSize() {
				return rezervacijelista.size();
			}
			public Object getElementAt(int index) {
				Rezervacija r=(Rezervacija)rezervacijelista.get(index);
				return "Autobuska linija: "+r.getLinija().getBrojLinije()+ " Datum polaska: "+r.getDatumPolaska_dan()+"."+r.getDatumPolaska_mjesec()+"."+r.getDatumPolaska_godina()+"."+" Rezervisao: "+r.getIme()+" "+r.getPrezime()+"Tip karte: "+r.getTipKarte();
			}
		});
		
		final JButton rezervisiBtn = new JButton("Rezerviši");
		rezervisiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { //dodavanje rezervacijeee
				int d1=0;
				String izuzetak="";
				try
				{
					if(datumRezervacijaDate.getDate()==null)
					{
						d1=1;
						izuzetak+="Morate unijeti datum.";
					}
					
					if(d1==0)
					{
					Session session = HibernateUtil.getSessionFactory().openSession();
					TipKarte k=TipKarte.jednosmjerna;
					Calendar cal=Calendar.getInstance();
					cal.setTime(datumRezervacijaDate.getDate());
					int godina=cal.get(Calendar.YEAR);
					int mjesec=cal.get(Calendar.MONTH)+1;
					int dan=cal.get(Calendar.DAY_OF_MONTH);
					
					if (comboBox_2.getItemCount() == 0) {
						JOptionPane.showMessageDialog(rezervisiBtn, "Nema odredišta!");
						return;
					}
					if (comboBox_3.getItemCount() == 0) {
						JOptionPane.showMessageDialog(rezervisiBtn, "Nema autobuske linije za to odredište!");
						return;
					}
					
					String[] vrijeme=comboBox_3.getSelectedItem().toString().split(":");
					
					if (vrijeme.length != 2) {
						JOptionPane.showMessageDialog(prodajaBtn, "Vrijeme mora biti u formatu sati:minute!");
						return;
					} else if (!Validacija.jeInt(vrijeme[0]) || !Validacija.jeInt(vrijeme[1])) {
						JOptionPane.showMessageDialog(rezervisiBtn, "Sati i minute moraju biti cijeli brojevi!");
						return;
					}
					
					int sati=cal.get(Calendar.HOUR);
					int minute=cal.get(Calendar.MINUTE);
					AutobuskaLinija linija=new AutobuskaLinija();
					HibernateAutibuskaLinija linija1=new HibernateAutibuskaLinija();
					linija=linija1.NadjiAutobuskuLinijuOdrediste(session, comboBox_2.getSelectedItem().toString(), godina, mjesec, dan, 
							Integer.valueOf(vrijeme[0]), Integer.valueOf(vrijeme[1]));
					
					if (linija == null) {
						JOptionPane.showMessageDialog(rezervisiBtn, "Ne postoji ta linija!");
						return;
					}
					
					double cijena=linija.getCijenaJednosmjerna();
					if(jednosmjernaProdaja.isSelected()==false)
					{
						k=TipKarte.dvosmjerna;
						//cijenu u labelu upisat
						cijena=linija.getCijenaDvosmjerna();
						
					}
					String ime = imeRezervacije.getText();
					String prezime = prezimeRezervacije.getText();
					
					if (ime.isEmpty() || prezime.isEmpty()) {
						JOptionPane.showMessageDialog(rezervisiBtn, "Morate unijeti ime i prezime za rezervaciju!");
						return;
					}
					if (!Validacija.jeTekst(ime) || !Validacija.jeTekst(prezime)) {
						JOptionPane.showMessageDialog(rezervisiBtn, "Ime i prezime sadrže samo slova!");
						return;
					}
					
					HibernateRezervacija.dodajRezervaciju(session, linija, godina, mjesec, dan, Integer.valueOf(vrijeme[0]), Integer.valueOf(vrijeme[1]), k, cijena, ime, prezime);
					rezervacijeList.setModel(new AbstractListModel()
					{
						Session session = HibernateUtil.getSessionFactory().openSession();
						java.util.List rezervacijelista=HibernateRezervacija.sveRezervacije(session);
						
						
						
						public int getSize() {
							return rezervacijelista.size();
						}
						public Object getElementAt(int index) {
							Rezervacija r=(Rezervacija)rezervacijelista.get(index);
							return "Autobuska linija: "+r.getLinija().getBrojLinije()+ " Datum polaska: "+r.getDatumPolaska_dan()+"."+r.getDatumPolaska_mjesec()+"."+r.getDatumPolaska_godina()+"."+" Rezervisao: "+r.getIme()+" "+r.getPrezime()+"Tip karte: "+r.getTipKarte();
						}
					});
					cijenaRezervacije.setText(String.valueOf(cijena));
					JOptionPane.showMessageDialog(rezervisiBtn, "Karta je rezervisan.");
				}
					else
					{
				    	JOptionPane.showMessageDialog(rezervisiBtn, izuzetak);	
					}
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(prodajaBtn, "Neuspješna rezervacija.");
					
				}
			}
		});
		rezervisiBtn.setBounds(420, 301, 134, 33);
		panel_2.add(rezervisiBtn);
		
		List<AutobuskaLinija> mlinije = (List<AutobuskaLinija>)HibernateAutibuskaLinija.sveLinije(session);
		List modredista = new ArrayList<String>();
		for (AutobuskaLinija a : mlinije) {
			if (!modredista.contains(a.getOdrediste())) {
				modredista.add(a.getOdrediste());
			}
		}
		
		comboBox_2 = new Java2sAutoComboBox(modredista);
		comboBox_2.setBounds(117, 33, 142, 20);
		panel_2.add(comboBox_2);
		
		comboBox_2.addActionListener(this);
		
		comboBox_3 = new Java2sAutoComboBox(new ArrayList<AutobuskaLinija>());
		panel_2.add(comboBox_3);
		
		panel_4 = new JPanel();
		tabbedPane.addTab("Modifikacija i brisanje", null, panel_4, null);
		panel_4.setLayout(null);
		
		
		
		comboBox_4 = new Java2sAutoComboBox(modredista);
		comboBox_4.setBounds(126, 30, 142, 20);
		panel_4.add(comboBox_4);
		comboBox_4.addActionListener(this);
		
		comboBox_5 = new Java2sAutoComboBox(new ArrayList<String>());
		comboBox_5.setBounds(126, 77, 142, 20);
		panel_4.add(comboBox_5);
		comboBox_5.addActionListener(this);
		
		
		JLabel label_14 = new JLabel("Odredište:");
		label_14.setHorizontalAlignment(SwingConstants.TRAILING);
		label_14.setBounds(20, 33, 96, 14);
		panel_4.add(label_14);
		
		JLabel label_15 = new JLabel("Vrijeme:");
		label_15.setHorizontalAlignment(SwingConstants.TRAILING);
		label_15.setBounds(32, 80, 84, 14);
		panel_4.add(label_15);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 149, 270, 212);
		panel_4.add(scrollPane);
		
		final JDateChooser datumModifikacijeDate = new JDateChooser();
		datumModifikacijeDate.setDateFormatString("dd/M/yyy");
		datumModifikacijeDate.setBounds(419, 137, 142, 20);
		panel_4.add(datumModifikacijeDate);
		
		
		
		rezervacijeList.addListSelectionListener(new ListSelectionListener() //event za selektovani autobus
		{
			public void valueChanged(ListSelectionEvent arg0)
			{
				Session session = HibernateUtil.getSessionFactory().openSession();
				java.util.List listarezervacija=HibernateRezervacija.sveRezervacije(session);
				String selektovan=rezervacijeList.getSelectedValue().toString();
				for(int i=0;i<listarezervacija.size();i++)
				{
					Rezervacija r=(Rezervacija)listarezervacija.get(i);
					String s="Autobuska linija: "+r.getLinija().getBrojLinije()+ " Datum polaska: "+r.getDatumPolaska_dan()+"."+r.getDatumPolaska_mjesec()+"."+r.getDatumPolaska_godina()+"."+" Rezervisao: "+r.getIme()+" "+r.getPrezime()+"Tip karte: "+r.getTipKarte();
					if(selektovan.equals(s))
					{
						comboBox_4.setSelectedItem(r.getLinija().getOdrediste());
						comboBox_5.setSelectedItem(r.getVrijemePolaska_sati()+":"+r.getVrijemePolaska_minute());
						if(r.getTipKarte()==TipKarte.jednosmjerna)
						{
						    jednosmjernaModifikacije.setSelected(true);
						}
						else
						{
							povratnaModifikacije.setSelected(true);
						}
						
						//datum
						Date d=new Date();
						Date d1=new Date();
						staralinija=r.getLinija();
						staroime=r.getIme();
						staroprezime=r.getPrezime();
						Calendar cal=Calendar.getInstance();
						cal.set(Calendar.YEAR, r.getDatumPolaska_godina());
						cal.set(Calendar.MONTH,r.getDatumPolaska_mjesec()-1);
						cal.set(Calendar.DAY_OF_MONTH,r.getDatumPolaska_dan());
						d=cal.getTime();
						datumModifikacijeDate.setDate(d);
						textField.setText(r.getIme());
						textField_1.setText(r.getPrezime());
						
					}
					else
					{
						JOptionPane.showMessageDialog(rezervacijeList, "Morate selektovati autobusku liniju");
					}
				}
			}
		});
		scrollPane.setViewportView(rezervacijeList);
		
		JLabel label_16 = new JLabel("Rezervacije");
		label_16.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_16.setBounds(23, 118, 105, 20);
		panel_4.add(label_16);
		
		
		
		JLabel lblPolazak_1 = new JLabel("Polazak:");
		lblPolazak_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPolazak_1.setBounds(303, 139, 106, 14);
		panel_4.add(lblPolazak_1);
		
		final JButton modifikujBtn = new JButton("Modifikuj");
		modifikujBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //modifikovanje rezervacije
			 int d1 = 0,d2=0;
			 String izuzetak = "";
				try
				{
					if(datumModifikacijeDate.getDate()==null )
					{
						d2=1;
						izuzetak="Morate unijeti datum.";
					}
					
					if(datumModifikacijeDate.getDate()==null && textField.getText().length()==0 && textField_1.getText().length()==0)
					{
						d1=1;
						izuzetak="Morate izabrati autobusku liniju.";
					}
					
					
					if (d1 == 0 && d2==0)
					{
					Session session = HibernateUtil.getSessionFactory().openSession();
					String odrediste=comboBox_4.getSelectedItem().toString();
					String[] vrijeme=comboBox_5.getSelectedItem().toString().split(":");
					int sati=Integer.valueOf(vrijeme[0]);
					int minute=Integer.valueOf(vrijeme[1]);
					TipKarte r=TipKarte.dvosmjerna;
					
					Date d=new Date();
					Calendar cal=Calendar.getInstance();
					cal.setTime(datumModifikacijeDate.getDate());
					int godina=cal.get(Calendar.YEAR);
					int mjesec=cal.get(Calendar.MONTH)+1;
					int dan=cal.get(Calendar.DAY_OF_MONTH);
					AutobuskaLinija linija=HibernateAutibuskaLinija.NadjiAutobuskuLinijuOdrediste(session, odrediste, godina, mjesec, dan, sati, minute);
					double cijena=linija.getCijenaDvosmjerna();
					
					if(jednosmjernaModifikacije.isSelected()==true)
					{
					    r=TipKarte.jednosmjerna;
					    cijena=linija.getCijenaJednosmjerna();
					}
					JOptionPane.showMessageDialog(modifikujBtn, cijena);
					if(linija!=null)
					{
					HibernateRezervacija.ModifikujRezervaciju(session, staralinija, linija, staroime, staroprezime, textField.getText(), textField_1.getText(),cijena,r);
					JOptionPane.showMessageDialog(modifikujBtn, "Rezervacija je uspješno modifikovana.");
					}
					else
					{
						JOptionPane.showMessageDialog(modifikujBtn, "Ne postoji autobuska linija, sa parametrima koje ste unijeli.");
					}
					}
					else
					{
				    	JOptionPane.showMessageDialog(modifikujBtn, izuzetak);	
					}
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(modifikujBtn,"Neuspješno modifikovanje.");
					JOptionPane.showMessageDialog(modifikujBtn, ex);
					
				}
			}
		});
		modifikujBtn.setBounds(474, 327, 98, 34);
		panel_4.add(modifikujBtn);
		
		final JButton obrisiBtn = new JButton("Obriši");
		obrisiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //brisanje rezervacije
				try
				{
					if (rezervacijeList.getSelectedIndex() == -1) {
						JOptionPane.showMessageDialog(prodajaBtn, "Morate odabrati rezervaciju");
						return;
					}
					
					
					Session session = HibernateUtil.getSessionFactory().openSession();
					String odrediste=comboBox_4.getSelectedItem().toString();
					String[] vrijeme=comboBox_5.getSelectedItem().toString().split(":");
					int sati=Integer.valueOf(vrijeme[0]);
					int minute=Integer.valueOf(vrijeme[1]);
					TipKarte r=TipKarte.dvosmjerna;
					
					
					if (datumModifikacijeDate.getDate() == null) {
						JOptionPane.showMessageDialog(btnIsplati, "Morate unijeti datum!");
						return;
					}
					
					//datum
					Date d=new Date();
					Calendar cal=Calendar.getInstance();
					cal.setTime(datumModifikacijeDate.getDate());
					int godina=cal.get(Calendar.YEAR);
					int mjesec=cal.get(Calendar.MONTH)+1;
					int dan=cal.get(Calendar.DAY_OF_MONTH);
					
					
					
					AutobuskaLinija linija=HibernateAutibuskaLinija.NadjiAutobuskuLinijuOdrediste(session, odrediste, godina, mjesec, dan, sati, minute);
					
					if (linija == null) {
						JOptionPane.showMessageDialog(prodajaBtn, "Ne postoji ta linija!");
						return;
					}					
					double cijena=linija.getCijenaDvosmjerna();
					if(jednosmjernaModifikacije.isSelected()==true)
					{
					    r=TipKarte.jednosmjerna;
					    cijena=linija.getCijenaJednosmjerna();
					}
					
					String ime = textField.getText();
					String prezime = textField_1.getText();
					
					if (ime.isEmpty() || prezime.isEmpty()) {
						JOptionPane.showMessageDialog(prodajaBtn, "Morate unijeti ime i prezime!");
						return;
					}
					if (!Validacija.jeTekst(ime) || !Validacija.jeTekst(prezime)) {
						JOptionPane.showMessageDialog(prodajaBtn, "Ime i prezime sadrže samo slova!");
						return;
					}
					
					if(linija!=null)
					{
						HibernateRezervacija.brisanjeRezervacije(session, linija, textField.getText(), textField_1.getText());
						JOptionPane.showMessageDialog(obrisiBtn, "Uspješno brisanje");
						
					}
					else
					{
						JOptionPane.showMessageDialog(obrisiBtn, "Ne postoji linija.");
					}
				}
					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(obrisiBtn,"Neuspješno brisanje.");
						JOptionPane.showMessageDialog(obrisiBtn, ex);
						
					}
				}
			
		});
		obrisiBtn.setBounds(334, 327, 98, 34);
		panel_4.add(obrisiBtn);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(419, 191, 142, 20);
		panel_4.add(textField);
		
		JLabel label = new JLabel("Ime:");
		label.setHorizontalAlignment(SwingConstants.TRAILING);
		label.setBounds(348, 193, 61, 14);
		panel_4.add(label);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(419, 238, 142, 20);
		panel_4.add(textField_1);
		
		JLabel label_3 = new JLabel("Prezime:");
		label_3.setHorizontalAlignment(SwingConstants.TRAILING);
		label_3.setBounds(336, 240, 73, 14);
		panel_4.add(label_3);
		
		popuniVrijeme();
		popuniVrijeme1();
		popuniVrijeme2();
	}
	
	public void setVisible(boolean visible) {
		frmalterskiRadnik.setVisible(visible);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(comboBox)) {
			popuniVrijeme();
		} else if(e.getSource().equals(comboBox_2)){
			popuniVrijeme1();
		} else if(e.getSource().equals(comboBox_4)){
			popuniVrijeme2();
		} else if (e.getSource().equals(comboBox_5)) {
			popuniListuRezervacija();
		}
	}
	
	private void popuniListuRezervacija() {
	
	}

	private void popuniVrijeme2() {
		List<String> vremena = new ArrayList<String>();
    	Session session = HibernateUtil.getSessionFactory().openSession();
        for (AutobuskaLinija autobuskaLinija : (List<AutobuskaLinija>) HibernateAutibuskaLinija.sveLinije(session)) {
			if (autobuskaLinija.getOdrediste().equals(comboBox_4.getSelectedItem())) {
				if (!vremena.contains(autobuskaLinija.getVrijemePolaska_sati()+":"+autobuskaLinija.getVrijemePolaska_minute())) {
					vremena.add(autobuskaLinija.getVrijemePolaska_sati()+":"+autobuskaLinija.getVrijemePolaska_minute());
				}
			}
		}
        panel_4.remove(comboBox_5);
        comboBox_5 = new Java2sAutoComboBox(vremena);
        comboBox_5.setBounds(126, 77, 142, 20);
        
        panel_4.add(comboBox_5);
        
        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBorder(new TitledBorder(null, "Tip karte", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_1.setBounds(359, 25, 202, 83);
        panel_4.add(panel_1);
        
        jednosmjernaModifikacije = new JRadioButton("Jednosmjerna");
        jednosmjernaModifikacije.setSelected(true);
        jednosmjernaModifikacije.setBounds(38, 22, 144, 23);
        panel_1.add(jednosmjernaModifikacije);
        
        povratnaModifikacije = new JRadioButton("Povratna");
        povratnaModifikacije.setBounds(38, 53, 137, 23);
        panel_1.add(povratnaModifikacije);
        panel_4.revalidate();
        panel_4.repaint();
        
        //Group the radio buttons.
        ButtonGroup group3 = new ButtonGroup();
        group3.add(jednosmjernaModifikacije);
        group3.add(povratnaModifikacije);
        
	}
	
	private void popuniVrijeme1() {
		List<String> vremena = new ArrayList<String>();
    	Session session = HibernateUtil.getSessionFactory().openSession();
        for (AutobuskaLinija autobuskaLinija : (List<AutobuskaLinija>) HibernateAutibuskaLinija.sveLinije(session)) {
			if (autobuskaLinija.getOdrediste().equals(comboBox_2.getSelectedItem())) {
				if (!vremena.contains(autobuskaLinija.getVrijemePolaska_sati()+":"+autobuskaLinija.getVrijemePolaska_minute())) {
					vremena.add(autobuskaLinija.getVrijemePolaska_sati()+":"+autobuskaLinija.getVrijemePolaska_minute());
				}
			}
		}
        panel_2.remove(comboBox_3);
        
        comboBox_3 = new Java2sAutoComboBox(vremena);
        comboBox_3.setBounds(117, 80, 142, 20);
        
        panel_2.add(comboBox_3);
        
        
        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBorder(new TitledBorder(null, "Tip karte", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_1.setBounds(76, 124, 193, 83);
        panel_2.add(panel_1);
        
        jednosmjernaRezervacije = new JRadioButton("Jednosmjerna");
        jednosmjernaRezervacije.setSelected(true);
        jednosmjernaRezervacije.setBounds(38, 22, 135, 23);
        panel_1.add(jednosmjernaRezervacije);
        
        JRadioButton povratnaRezervacije = new JRadioButton("Povratna");
        povratnaRezervacije.setBounds(38, 53, 120, 23);
        panel_1.add(povratnaRezervacije);
        
        //Group the radio buttons.
        ButtonGroup group1 = new ButtonGroup();
        group1.add(jednosmjernaRezervacije);
        group1.add(povratnaRezervacije);
        
        
        JLabel label = new JLabel("Cijena(KM):");
        label.setFont(new Font("Tahoma", Font.PLAIN, 25));
        label.setBounds(34, 279, 151, 41);
        panel_2.add(label);
        
        cijenaRezervacije = new JLabel("");
        cijenaRezervacije.setFont(new Font("Tahoma", Font.PLAIN, 25));
        cijenaRezervacije.setBounds(211, 283, 104, 33);
        panel_2.add(cijenaRezervacije);
        
        imeRezervacije = new JTextField();
        imeRezervacije.setColumns(10);
        imeRezervacije.setBounds(383, 30, 160, 20);
        panel_2.add(imeRezervacije);
        
        JLabel label_2 = new JLabel("Ime:");
        label_2.setHorizontalAlignment(SwingConstants.TRAILING);
        label_2.setBounds(308, 33, 65, 14);
        panel_2.add(label_2);
        
        prezimeRezervacije = new JTextField();
        prezimeRezervacije.setColumns(10);
        prezimeRezervacije.setBounds(383, 77, 160, 20);
        panel_2.add(prezimeRezervacije);
        
        JLabel label_3 = new JLabel("Prezime:");
        label_3.setHorizontalAlignment(SwingConstants.TRAILING);
        label_3.setBounds(291, 80, 82, 14);
        panel_2.add(label_3);
        
        btnIsplati = new JButton("Isplati");
        btnIsplati.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent e) {
        		Session session = HibernateUtil.getSessionFactory().openSession();
        		Calendar cal=Calendar.getInstance();
        		
        		if (datumRezervacijaDate.getDate() == null) {
					JOptionPane.showMessageDialog(btnIsplati, "Morate unijeti datum!");
					return;
				}
        		
				cal.setTime(datumRezervacijaDate.getDate());
				int godina=cal.get(Calendar.YEAR);
				int mjesec=cal.get(Calendar.MONTH)+1;
				int dan=cal.get(Calendar.DAY_OF_MONTH);
				String[] vrijeme=comboBox_3.getSelectedItem().toString().split(":");
				
				if (vrijeme.length != 2) {
					JOptionPane.showMessageDialog(btnIsplati, "Vrijeme mora biti u formatu sati:minute!");
					return;
				} else if (!Validacija.jeInt(vrijeme[0]) || !Validacija.jeInt(vrijeme[1])) {
					JOptionPane.showMessageDialog(btnIsplati, "Sati i minute moraju biti cijeli brojevi!");
					return;
				}
				
				int sati = Integer.valueOf(vrijeme[0]);
				int minute= Integer.valueOf(vrijeme[1]);
        				
        		AutobuskaLinija linija = HibernateAutibuskaLinija.NadjiAutobuskuLinijuOdrediste(session, 
        				comboBox_2.getSelectedItem().toString(), godina, mjesec, dan, sati, minute);
        		
        		if (linija == null) {
					JOptionPane.showMessageDialog(btnIsplati, "Ne postoji ta linija!");
					return;
				}
        		
        		String ime = imeRezervacije.getText();
        		String prezime = prezimeRezervacije.getText();
        		
        		if (ime.isEmpty() || prezime.isEmpty()) {
					JOptionPane.showMessageDialog(btnIsplati, "Morate unijeti ime i prezime za rezervaciju!");
					return;
				}
				if (!Validacija.jeTekst(ime) || !Validacija.jeTekst(prezime)) {
					JOptionPane.showMessageDialog(btnIsplati, "Ime i prezime sadrže samo slova!");
					return;
				}
        		
        		linija.setZauzeto(linija.getZauzeto()-1);
        		
        		HibernateAutibuskaLinija.updateLinija(session, linija);
        		
        		try{       		
        			HibernateRezervacija.brisanjeRezervacije(session, linija, ime, prezime);
        		}
        		catch(Exception ex){
        			JOptionPane.showMessageDialog(btnIsplati, "Ne postoji ta rezervacija!");
        			
        		}
        		
        		TipKarte k = TipKarte.jednosmjerna;
        		
        		double cijena=linija.getCijenaJednosmjerna();
				if(jednosmjernaRezervacije.isSelected()==false)
				{
					k=TipKarte.dvosmjerna;
					//cijenu u labelu upisat
					cijena=linija.getCijenaDvosmjerna();
					
				}		
				
				
				if (!linija.getMedjunarodna()) {
					HibernateKarta.dodajKartu(session, linija, godina, mjesec, dan, cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), k, cijena);
				} else if(!ime.isEmpty() && !prezime.isEmpty()){
					HibernateMedjunarodnaKarta.dodajKartu(session, linija, godina, mjesec, dan, cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), k, cijena, ime, prezime);
				}
				
				cijenaRezervacije.setText(String.valueOf(cijena));
				JOptionPane.showMessageDialog(btnIsplati, "Karta je prodata.");
				
				
				
        	}
        });
        btnIsplati.setBounds(420, 124, 134, 33);
        panel_2.add(btnIsplati);
        comboBox_3.revalidate();
        comboBox_3.repaint();
		
	}

	private void popuniVrijeme() {
		List<String> vremena = new ArrayList<String>();
    	Session session = HibernateUtil.getSessionFactory().openSession();
        for (AutobuskaLinija autobuskaLinija : (List<AutobuskaLinija>) HibernateAutibuskaLinija.sveLinije(session)) {
			if (autobuskaLinija.getOdrediste().equals(comboBox.getSelectedItem())) {
				if (!vremena.contains(autobuskaLinija.getVrijemePolaska_sati()+":"+autobuskaLinija.getVrijemePolaska_minute())) {
					vremena.add(autobuskaLinija.getVrijemePolaska_sati()+":"+autobuskaLinija.getVrijemePolaska_minute());
				}
			}
		}
        panel.remove(comboBox_1);
        
        comboBox_1 = new Java2sAutoComboBox(vremena);
        comboBox_1.setBounds(112, 81, 142, 20);
        
        panel.add(comboBox_1);
        comboBox_1.revalidate();
        comboBox_1.repaint();
        
	}
}
