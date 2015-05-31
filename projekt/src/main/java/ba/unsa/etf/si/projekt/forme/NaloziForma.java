package ba.unsa.etf.si.projekt.forme;
import org.apache.log4j.Logger;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.UIManager;

import java.awt.Font;

import ba.unsa.etf.si.projekt.dodatno.Validacija;
import ba.unsa.etf.si.projekt.entiteti.AutobuskaLinija;
import ba.unsa.etf.si.projekt.entiteti.KorisnickiRacun;
import ba.unsa.etf.si.projekt.hibernate.HibernateAutibuskaLinija;
import ba.unsa.etf.si.projekt.hibernate.HibernateKorisnickiRacuni;
import ba.unsa.etf.si.projekt.hibernate.HibernateNalog;
import ba.unsa.etf.si.projekt.hibernate.HibernateUtil;

import com.toedter.calendar.JDateChooser;

import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.AbstractListModel;

import org.hibernate.Session;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.util.Calendar;

import javax.swing.SwingConstants;

import java.awt.Toolkit;

import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

public class NaloziForma {
	final static Logger logger = Logger.getLogger(NaloziForma.class);
	private JFrame frmPravljenjeNaloga;
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
	 */
	public NaloziForma() {
		initialize();
	}
	
	public NaloziForma(KorisnickiRacun kr) {
		initialize();
		this.tipKorisnika = kr.getTipKorisnickogRacuna().toString();
		korisnik = kr;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPravljenjeNaloga = new JFrame();
		frmPravljenjeNaloga.setResizable(false);
		frmPravljenjeNaloga.setIconImage(Toolkit.getDefaultToolkit().getImage(NaloziForma.class.getResource("/ba/unsa/etf/si/projekt/dodatno/icon.png")));
		frmPravljenjeNaloga.setTitle("Pravljenje naloga");
		frmPravljenjeNaloga.setBounds(100, 100, 622, 392);
		frmPravljenjeNaloga.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmPravljenjeNaloga.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 86, 282, 256);
		frmPravljenjeNaloga.getContentPane().add(scrollPane);
		
		final JList linijeList = new JList();
		
			//AutobuskaLinija l=(AutobuskaLinija)lista.get(i);
			linijeList.setModel(new AbstractListModel()
			{
				HibernateAutibuskaLinija listalinija=new HibernateAutibuskaLinija();
				Session session = HibernateUtil.getSessionFactory().openSession();
				java.util.List lista=listalinija.sveLinije(session);	
				public int getSize() {
					return lista.size();
				}
				public Object getElementAt(int index) {
					AutobuskaLinija a=(AutobuskaLinija)lista.get(index);
				return "Odredište:"+a.getOdrediste()+" "+"Polazište:"+a.getPolaziste()+" "+"Broj linije:"+" "+a.getBrojLinije()+" Datum polaska:"+ " "+a.getDatumPolaska_dan()+"."+a.getDatumPolaska_mjesec()+"."+ a.getDatumPolaska_godina();
				}
			});
			
		scrollPane.setViewportView(linijeList);
		
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
		nazadBtn.setBounds(408, 11, 89, 23);
		frmPravljenjeNaloga.getContentPane().add(nazadBtn);
		
		 MaskFormatter maska = new MaskFormatter();
 		try{
 			maska = new MaskFormatter("##:##");
 		}
 		catch( Exception e)
 		{
 			
 		}
 		maska.setPlaceholderCharacter('_');
		
		final JFormattedTextField formattedTextFieldNalogVrijeme = new JFormattedTextField(maska);
		formattedTextFieldNalogVrijeme.setBounds(468, 129, 116, 20);
		frmPravljenjeNaloga.getContentPane().add(formattedTextFieldNalogVrijeme);
		
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
		odjavaBtn.setBounds(507, 11, 89, 23);
		frmPravljenjeNaloga.getContentPane().add(odjavaBtn);
		
		JLabel lblAutobuskeLinije = new JLabel("Autobuske linije:");
		lblAutobuskeLinije.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAutobuskeLinije.setBounds(23, 55, 178, 20);
		frmPravljenjeNaloga.getContentPane().add(lblAutobuskeLinije);
		
		final JDateChooser datumDate = new JDateChooser();
		datumDate.setBounds(468, 86, 116, 20);
		frmPravljenjeNaloga.getContentPane().add(datumDate);
		
		JLabel label = new JLabel("Vrijeme polaska:");
		label.setHorizontalAlignment(SwingConstants.TRAILING);
		label.setBounds(324, 132, 134, 14);
		frmPravljenjeNaloga.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Datum polaska:");
		label_1.setHorizontalAlignment(SwingConstants.TRAILING);
		label_1.setBounds(324, 88, 134, 14);
		frmPravljenjeNaloga.getContentPane().add(label_1);
		
		final JButton napraviBtn = new JButton("Napravi nalog");
		napraviBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //pravljenje nalogaaa
				String string="";
				int v=0,d1=0,v1=0,s1=0,s2=0;
				try
				{
     				
					if(linijeList.getSelectedIndex()==-1)
					{
						JOptionPane.showMessageDialog(napraviBtn, "Morate selektovati autobusku liniju kako biste kreirali nalog.");
						return;
					}
					
					if(datumDate.getDate()==null)
					{
						string+="Morate unijeti datum. ";
						d1=1;
					}
					
					if(formattedTextFieldNalogVrijeme.getText().equals("__:__") ) // ne radi ovo za vrijemeee Larisino 
					{
						v1=1;
						string+="Morate unijeti vrijeme i mora biti u formatu 12:12.";
					}
					 
					Session session = HibernateUtil.getSessionFactory().openSession();
					HibernateNalog n=new HibernateNalog();
					HibernateAutibuskaLinija linija1=new HibernateAutibuskaLinija();
					AutobuskaLinija linija=new AutobuskaLinija();
					String selektovano=(linijeList.getSelectedValue().toString());
					java.util.List listasvihlinija=linija1.sveLinije(session);
					
					if(v==0 && d1==0 && v1==0 && s1==0 && s2==0)
					{
					int broj=0;
					
					for(int i=0;i<listasvihlinija.size();i++)
					{
						AutobuskaLinija a=(AutobuskaLinija)listasvihlinija.get(i);
						String uporedi="Odredište:"+a.getOdrediste()+" "+"Polazište:"+a.getPolaziste()+" "+"Broj linije:"+" "+a.getBrojLinije()+" Datum polaska:"+ " "+a.getDatumPolaska_dan()+"."+a.getDatumPolaska_mjesec()+"."+ a.getDatumPolaska_godina();
					    if(selektovano.equals(uporedi)==true)
					    	broj=a.getBrojLinije();
					}
					
					linija=linija1.nadjiAutobuskuLiniju(session, broj);
					java.util.Date d=datumDate.getDate();
					Calendar cal=Calendar.getInstance();
					cal.setTime(d);
					int godina=cal.get(Calendar.YEAR);
					int dan=cal.get(Calendar.DAY_OF_MONTH);
					int mjesec=cal.get(Calendar.MONTH);
					String[] nizvremena=formattedTextFieldNalogVrijeme.getText().split(":");
					int h=Integer.valueOf(nizvremena[0]);
					int min=Integer.valueOf(nizvremena[1]);
					
					n.dodajNalog(session, linija, dan, mjesec, godina,h, min);
					JOptionPane.showMessageDialog(napraviBtn, "Uspješno ste kreirali nalog.");
					formattedTextFieldNalogVrijeme.setText("");
					datumDate.setDate(null);
					linijeList.setSelectedIndex(-1);
					}
					
					else
					{
						JOptionPane.showMessageDialog(napraviBtn, string);
					}

				}
				catch(Exception ec)
				{
					JOptionPane.showMessageDialog(napraviBtn, "Neuspješno kreiranje naloga:\n"+ec.getMessage());
				}
			}
		});
		napraviBtn.setBounds(480, 319, 116, 23);
		frmPravljenjeNaloga.getContentPane().add(napraviBtn);
		
		
	}
	
	public void setVisible(boolean visible) {
		frmPravljenjeNaloga.setVisible(visible);
	}
}

