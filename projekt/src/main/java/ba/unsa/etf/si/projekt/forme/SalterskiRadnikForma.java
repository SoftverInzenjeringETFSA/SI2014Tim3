package ba.unsa.etf.si.projekt.forme;

import java.awt.EventQueue;

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

import ba.unsa.etf.si.projekt.dodatno.Java2sAutoComboBox;
import ba.unsa.etf.si.projekt.entiteti.AutobuskaLinija;
import ba.unsa.etf.si.projekt.entiteti.TipKarte;
import ba.unsa.etf.si.projekt.hibernate.HibernateAutibuskaLinija;
import ba.unsa.etf.si.projekt.hibernate.HibernateKarta;
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
import java.util.List;

public class SalterskiRadnikForma implements ActionListener{

	private JFrame frmalterskiRadnik;
	private JTextField imeProdaja;
	private JTextField prezimeProdaja;
	private JTextField odredisteRezervacija;
	private JTextField vrijemeRezervacija;
	private JTextField imeRezervacija;
	private JTextField prezimeRezervacija;
	private JTextField odredisteModifikacija;
	private JTextField vrijemeModifikacija;
	private JTextField imeModifikacije;
	private JTextField prezimeModifikacije;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JPanel panel;
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
					SalterskiRadnikForma window = new SalterskiRadnikForma();
					window.frmalterskiRadnik.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
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
		frmalterskiRadnik.setTitle("Šalterski radnik");
		frmalterskiRadnik.setBounds(100, 100, 629, 495);
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
		tabbedPane.setBounds(10, 45, 593, 400);
		frmalterskiRadnik.getContentPane().add(tabbedPane);
		
		panel = new JPanel();
		tabbedPane.addTab("Prodaja", null, panel, null);
		panel.setLayout(null);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<String> odredista = new ArrayList<String>();
		
		
		
		for (AutobuskaLinija linija : (List<AutobuskaLinija>) HibernateAutibuskaLinija.sveLinije(session)) {
			if (!odredista.contains(linija.getOdrediste())) {
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
		lblOdredite.setBounds(51, 37, 51, 14);
		panel.add(lblOdredite);
		
		
		
		JLabel lblVrijeme = new JLabel("Vrijeme:");
		lblVrijeme.setBounds(63, 84, 39, 14);
		panel.add(lblVrijeme);
		
		final JRadioButton jednosmjernaProdaja = new JRadioButton("Jednosmjerna");
		jednosmjernaProdaja.setSelected(true);
		jednosmjernaProdaja.setBounds(112, 124, 109, 23);
		panel.add(jednosmjernaProdaja);
		
		JRadioButton povratnaProdaja = new JRadioButton("Povratna");
		povratnaProdaja.setBounds(112, 151, 109, 23);
		panel.add(povratnaProdaja);
		
		JLabel lblTipKarte = new JLabel("Tip karte:");
		lblTipKarte.setBounds(56, 128, 46, 14);
		panel.add(lblTipKarte);
		
		final JDateChooser datumProdajaDate = new JDateChooser();
		datumProdajaDate.setDateFormatString("dd/M/yyy");
		datumProdajaDate.setBounds(112, 189, 142, 20);
		panel.add(datumProdajaDate);
		
		JLabel label = new JLabel("Datum polaska:");
		label.setBounds(28, 191, 74, 14);
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setName("");
		panel_1.setToolTipText("");
		panel_1.setBorder(BorderFactory.createTitledBorder("Međunarodni saobraćaj"));
		panel_1.setBounds(311, 27, 238, 143);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label_1 = new JLabel("Ime:");
		label_1.setBounds(23, 49, 22, 14);
		panel_1.add(label_1);
		
		imeProdaja = new JTextField();
		imeProdaja.setColumns(10);
		imeProdaja.setBounds(62, 46, 160, 20);
		panel_1.add(imeProdaja);
		
		prezimeProdaja = new JTextField();
		prezimeProdaja.setColumns(10);
		prezimeProdaja.setBounds(62, 93, 160, 20);
		panel_1.add(prezimeProdaja);
		
		JLabel label_2 = new JLabel("Prezime:");
		label_2.setBounds(6, 96, 46, 14);
		panel_1.add(label_2);
		
		final JLabel cijenaProdajaLabel = new JLabel("30 KM");
		cijenaProdajaLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cijenaProdajaLabel.setBounds(150, 245, 104, 33);
		panel.add(cijenaProdajaLabel);
		
		JLabel label_4 = new JLabel("Cijena:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 25));
		label_4.setBounds(63, 241, 77, 41);
		panel.add(label_4);
		
		final JButton prodajaBtn = new JButton("Završi");
		prodajaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { //dodavanje kartii
				
				try
				{
					Session session = HibernateUtil.getSessionFactory().openSession();
					HibernateKarta karta=new HibernateKarta();
					TipKarte k=TipKarte.jednosmjerna;
					
					Date datum1=datumProdajaDate.getDate();
					Calendar cal=Calendar.getInstance();
					cal.setTime(datum1);
					int godina=cal.get(Calendar.YEAR);
					int mjesec=cal.get(Calendar.MONTH) + 1;
					int dan=cal.get(Calendar.DAY_OF_MONTH);
					String[] vrijeme=comboBox_1.getSelectedItem().toString().split(":");
					int sati=cal.get(Calendar.HOUR);
					int minute=cal.get(Calendar.MINUTE);
					AutobuskaLinija linija=new AutobuskaLinija();
					HibernateAutibuskaLinija linija1=new HibernateAutibuskaLinija();
					linija=linija1.NadjiAutobuskuLinijuOdrediste(session, comboBox.getSelectedItem().toString(), godina, mjesec, dan, 
							Integer.valueOf(vrijeme[0]), Integer.valueOf(vrijeme[1]));
					double cijena=linija.getCijenaJednosmjerna();
					if(jednosmjernaProdaja.isSelected()==false)
					{
						k=TipKarte.dvosmjerna;
						//cijenu u labelu upisat
						cijena=linija.getCijenaDvosmjerna();
					}
					karta.dodajKartu(session, linija, godina, mjesec, dan, sati, minute, k, cijena);
					JOptionPane.showMessageDialog(prodajaBtn, "Karta je prodata.");
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(prodajaBtn, "Neuspješna prodaja.");
					JOptionPane.showMessageDialog(prodajaBtn, ex);
				}
			}
		});
		prodajaBtn.setBounds(415, 314, 134, 33);
		panel.add(prodajaBtn);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Rezervacija", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel label_5 = new JLabel("Odredište:");
		label_5.setBounds(56, 36, 51, 14);
		panel_2.add(label_5);
		
		odredisteRezervacija = new JTextField();
		odredisteRezervacija.setColumns(10);
		odredisteRezervacija.setBounds(117, 33, 117, 20);
		panel_2.add(odredisteRezervacija);
		
		JLabel label_6 = new JLabel("Vrijeme:");
		label_6.setBounds(68, 83, 39, 14);
		panel_2.add(label_6);
		
		vrijemeRezervacija = new JTextField();
		vrijemeRezervacija.setColumns(10);
		vrijemeRezervacija.setBounds(117, 80, 117, 20);
		panel_2.add(vrijemeRezervacija);
		
		JLabel label_7 = new JLabel("Tip karte:");
		label_7.setBounds(61, 127, 46, 14);
		panel_2.add(label_7);
		
		final JRadioButton jednosmjernaRezervacija = new JRadioButton("Jednosmjerna");
		jednosmjernaRezervacija.setSelected(true);
		jednosmjernaRezervacija.setBounds(117, 123, 109, 23);
		panel_2.add(jednosmjernaRezervacija);
		
		JRadioButton povratnaRezervacija = new JRadioButton("Povratna");
		povratnaRezervacija.setBounds(117, 150, 109, 23);
		panel_2.add(povratnaRezervacija);
		
		final JDateChooser datumRezervacijaDate = new JDateChooser();
		datumRezervacijaDate.setDateFormatString("dd/M/yyy");
		datumRezervacijaDate.setBounds(117, 188, 142, 20);
		panel_2.add(datumRezervacijaDate);
		
		JLabel label_8 = new JLabel("Datum polaska:");
		label_8.setBounds(33, 190, 74, 14);
		panel_2.add(label_8);
		
		final JLabel cijenaRezervacija = new JLabel("30 KM");
		cijenaRezervacija.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cijenaRezervacija.setBounds(155, 244, 104, 33);
		panel_2.add(cijenaRezervacija);
		
		JLabel label_10 = new JLabel("Cijena:");
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 25));
		label_10.setBounds(68, 240, 77, 41);
		panel_2.add(label_10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(BorderFactory.createTitledBorder("Međunarodni saobraćaj"));
		panel_3.setBounds(316, 26, 238, 143);
		panel_2.add(panel_3);
		
		JLabel label_12 = new JLabel("Ime:");
		label_12.setBounds(23, 49, 22, 14);
		panel_3.add(label_12);
		
		imeRezervacija = new JTextField();
		imeRezervacija.setColumns(10);
		imeRezervacija.setBounds(62, 46, 160, 20);
		panel_3.add(imeRezervacija);
		
		prezimeRezervacija = new JTextField();
		prezimeRezervacija.setColumns(10);
		prezimeRezervacija.setBounds(62, 93, 160, 20);
		panel_3.add(prezimeRezervacija);
		
		JLabel label_13 = new JLabel("Prezime:");
		label_13.setBounds(6, 96, 46, 14);
		panel_3.add(label_13);
		
		final JButton rezervisiBtn = new JButton("Rezerviši");
		rezervisiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { //dodavanje rezervacijeee
				
				try
				{
					Session session = HibernateUtil.getSessionFactory().openSession();
					TipKarte k=TipKarte.jednosmjerna;
					vrijemeRezervacija.getText();
					Date datum1=datumRezervacijaDate.getDate();
					Calendar cal=Calendar.getInstance();
					cal.setTime(datum1);
					int godina=cal.get(Calendar.YEAR);
					int mjesec=cal.get(Calendar.MONTH);
					int dan=cal.get(Calendar.DAY_OF_MONTH);
					String[] vrijeme=vrijemeRezervacija.getText().split(".");
					int sati=datum1.getHours();
					int minute=datum1.getMinutes();
					AutobuskaLinija linija=new AutobuskaLinija();
					HibernateAutibuskaLinija linija1=new HibernateAutibuskaLinija();
					linija=linija1.NadjiAutobuskuLinijuOdrediste(session, comboBox.getSelectedItem().toString(), godina, mjesec, dan, sati, minute);
					double cijena=linija.getCijenaJednosmjerna();
					if(jednosmjernaRezervacija.isSelected()==false)
					{
						k=TipKarte.dvosmjerna;
						cijena=linija.getCijenaDvosmjerna();
						
						//ovu cijenu i u labelu upisat
					}
					HibernateRezervacija r=new HibernateRezervacija();
					r.dodajRezervaciju(session, comboBox.getSelectedItem().toString(), sati, minute, k, godina, mjesec, dan, cijena,imeRezervacija.getText(),prezimeRezervacija.getText());
					JOptionPane.showMessageDialog(rezervisiBtn, "Karta je rezervisana.");
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(rezervisiBtn, "Karta nije rezervisana.");
					JOptionPane.showMessageDialog(rezervisiBtn, ex);
				}
			}
		});
		rezervisiBtn.setBounds(420, 313, 134, 33);
		panel_2.add(rezervisiBtn);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(231, 33, 28, 20);
		panel_2.add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(231, 80, 28, 20);
		panel_2.add(comboBox_3);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Modifikacija i brisanje", null, panel_4, null);
		panel_4.setLayout(null);
		
		odredisteModifikacija = new JTextField();
		odredisteModifikacija.setColumns(10);
		odredisteModifikacija.setBounds(126, 30, 117, 20);
		panel_4.add(odredisteModifikacija);
		
		vrijemeModifikacija = new JTextField();
		vrijemeModifikacija.setColumns(10);
		vrijemeModifikacija.setBounds(126, 77, 117, 20);
		panel_4.add(vrijemeModifikacija);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setBounds(240, 77, 28, 20);
		panel_4.add(comboBox_4);
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setBounds(240, 30, 28, 20);
		panel_4.add(comboBox_5);
		
		JLabel label_14 = new JLabel("Odredište:");
		label_14.setBounds(65, 33, 51, 14);
		panel_4.add(label_14);
		
		JLabel label_15 = new JLabel("Vrijeme:");
		label_15.setBounds(77, 80, 39, 14);
		panel_4.add(label_15);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 149, 270, 212);
		panel_4.add(scrollPane);
		
		JList rezervacijeList = new JList();
		scrollPane.setViewportView(rezervacijeList);
		
		JLabel label_16 = new JLabel("Rezervacije");
		label_16.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_16.setBounds(23, 118, 105, 20);
		panel_4.add(label_16);
		
		JRadioButton jednosmjernaModifikacije = new JRadioButton("Jednosmjerna");
		jednosmjernaModifikacije.setSelected(true);
		jednosmjernaModifikacije.setBounds(418, 30, 109, 23);
		panel_4.add(jednosmjernaModifikacije);
		
		JLabel label_17 = new JLabel("Tip karte:");
		label_17.setBounds(362, 34, 46, 14);
		panel_4.add(label_17);
		
		JRadioButton povratnaModifikacije = new JRadioButton("Povratna");
		povratnaModifikacije.setBounds(418, 57, 109, 23);
		panel_4.add(povratnaModifikacije);
		
		JDateChooser datumModifikacijeDate = new JDateChooser();
		datumModifikacijeDate.setDateFormatString("dd/M/yyy");
		datumModifikacijeDate.setBounds(418, 95, 142, 20);
		panel_4.add(datumModifikacijeDate);
		
		JLabel label_18 = new JLabel("Datum polaska:");
		label_18.setBounds(334, 97, 74, 14);
		panel_4.add(label_18);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBorder(BorderFactory.createTitledBorder("Međunarodni saobraćaj"));
		panel_5.setBounds(334, 149, 238, 143);
		panel_4.add(panel_5);
		
		JLabel label_20 = new JLabel("Ime:");
		label_20.setBounds(23, 49, 22, 14);
		panel_5.add(label_20);
		
		imeModifikacije = new JTextField();
		imeModifikacije.setColumns(10);
		imeModifikacije.setBounds(62, 46, 160, 20);
		panel_5.add(imeModifikacije);
		
		prezimeModifikacije = new JTextField();
		prezimeModifikacije.setColumns(10);
		prezimeModifikacije.setBounds(62, 93, 160, 20);
		panel_5.add(prezimeModifikacije);
		
		JLabel label_21 = new JLabel("Prezime:");
		label_21.setBounds(6, 96, 46, 14);
		panel_5.add(label_21);
		
		JButton modifikujBtn = new JButton("Modifikuj");
		modifikujBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //modifikovanje rezervacije
			
				try
				{
					
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(rezervisiBtn, "Karta nije rezervisana.");
					JOptionPane.showMessageDialog(rezervisiBtn, ex);
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
					Session session = HibernateUtil.getSessionFactory().openSession();
					HibernateRezervacija r=new HibernateRezervacija();
					r.brisanjeRezervacije(session, odredisteModifikacija.getText(), imeModifikacije.getText(), prezimeModifikacije.getText());
					JOptionPane.showMessageDialog(obrisiBtn, "Rezervacija obrisana.");
				}
				catch(Exception ew)
				{
					JOptionPane.showMessageDialog(obrisiBtn, "Rezervacija nije obrisana.");
					JOptionPane.showMessageDialog(obrisiBtn, "ew.");
				}
					
				}
			
		});
		obrisiBtn.setBounds(334, 327, 98, 34);
		panel_4.add(obrisiBtn);
	}
	
	public void setVisible(boolean visible) {
		frmalterskiRadnik.setVisible(visible);
	}

	public void actionPerformed(ActionEvent e) {
		List<String> vremena = new ArrayList<String>();
    	Session session = HibernateUtil.getSessionFactory().openSession();
        for (AutobuskaLinija autobuskaLinija : (List<AutobuskaLinija>) HibernateAutibuskaLinija.sveLinije(session)) {
			if (autobuskaLinija.getOdrediste().equals(((JComboBox<String>)e.getSource()).getSelectedItem())) {
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
