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
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.UIManager;

import java.awt.Font;

import javax.swing.AbstractListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.hibernate.Session;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import ba.unsa.etf.si.projekt.entiteti.Autobus;
import ba.unsa.etf.si.projekt.entiteti.AutobuskaLinija;
import ba.unsa.etf.si.projekt.entiteti.KorisnickiRacun;
import ba.unsa.etf.si.projekt.hibernate.HibernateAutibuskaLinija;
import ba.unsa.etf.si.projekt.hibernate.HibernateAutobus;
import ba.unsa.etf.si.projekt.hibernate.HibernateKorisnickiRacuni;
import ba.unsa.etf.si.projekt.hibernate.HibernateUtil;

import javax.swing.SwingConstants;

import java.awt.Toolkit;

public class AutobusiForma {
	final static Logger logger = Logger.getLogger(AutobusiForma.class);
	private JFrame frmEvidencijaAutobusa;
	private JTextField modelDodaj;
	private JTextField registracijeDodaj;
	private JTextField modelModifikuj;
	private JTextField registracijeModifikuj;
	private JTextField modelIzbrisi;
	private JTextField registracijeIzbrisi;
	private JTextField registracijePretraga;
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
	

	public AutobusiForma(KorisnickiRacun kr) {
		// TODO Auto-generated constructor stub
		this.tipKorisnika = kr.getTipKorisnickogRacuna().toString();
		korisnik = kr;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEvidencijaAutobusa = new JFrame();
		frmEvidencijaAutobusa.setResizable(false);
		frmEvidencijaAutobusa.setIconImage(Toolkit.getDefaultToolkit().getImage(AutobusiForma.class.getResource("/ba/unsa/etf/si/projekt/dodatno/icon.png")));
		frmEvidencijaAutobusa.setTitle("Evidencija autobusa");
		frmEvidencijaAutobusa.setBounds(100, 100, 603, 434);
		frmEvidencijaAutobusa.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmEvidencijaAutobusa.getContentPane().setLayout(null);
		
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
		odjavaBtn.setBounds(488, 11, 89, 23);
		frmEvidencijaAutobusa.getContentPane().add(odjavaBtn);
		
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
		nazadBtn.setBounds(389, 11, 89, 23);
		frmEvidencijaAutobusa.getContentPane().add(nazadBtn);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 53, 567, 331);
		frmEvidencijaAutobusa.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Dodaj", null, panel, null);
		panel.setLayout(null);
		
		modelDodaj = new JTextField();
		modelDodaj.setBounds(199, 71, 155, 20);
		panel.add(modelDodaj);
		modelDodaj.setColumns(10);
		
		JLabel lblModel = new JLabel("Model:");
		lblModel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblModel.setBounds(114, 74, 75, 14);
		panel.add(lblModel);
		
		 final JSpinner kapacitetDodajSpinner = new JSpinner();
		kapacitetDodajSpinner.setBounds(199, 107, 155, 20);
		panel.add(kapacitetDodajSpinner);
		
		JLabel lblKapacitet = new JLabel("Kapacitet:");
		lblKapacitet.setHorizontalAlignment(SwingConstants.TRAILING);
		lblKapacitet.setBounds(100, 110, 89, 14);
		panel.add(lblKapacitet);
		
		registracijeDodaj = new JTextField();
		registracijeDodaj.setBounds(199, 140, 155, 20);
		panel.add(registracijeDodaj);
		registracijeDodaj.setColumns(10);
		
		JLabel lblRegistracije = new JLabel("Registracije:");
		lblRegistracije.setHorizontalAlignment(SwingConstants.TRAILING);
		lblRegistracije.setBounds(78, 143, 111, 14);
		panel.add(lblRegistracije);
		
		
		final JList autobusiModifikujLista = new JList();
		autobusiModifikujLista.setModel(new AbstractListModel(){

			Session session = HibernateUtil.getSessionFactory().openSession();
			HibernateAutobus autobus=new HibernateAutobus();
			java.util.List autobusi=autobus.sviAutobusi(session);
			public int getSize() {
				return autobusi.size();
			}
			public Object getElementAt(int index) {
				Autobus a=(Autobus)autobusi.get(index);
				return "Model autobusa:"+" " +a.getModel()+" "+"Registracije autobus:"+" "+a.getRegistracija()+" "+"Kapacitet autobusa:"+a.getKapacitet();
			}
			
		});
		
		
		final JButton dodajBtn = new JButton("Unesi");
		dodajBtn.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent arg0) { //event za dodavanje novih autobusaaa
				
				try
				{
				HibernateAutobus noviautobus=new HibernateAutobus();
				Session session = HibernateUtil.getSessionFactory().openSession();
				boolean postoji=false;
				
				//provjera postojanja tablica
				java.util.List listaautobusa=noviautobus.sviAutobusi(session);
				
				for(int i=0;i<listaautobusa.size();i++)
				{
					Autobus a=(Autobus) listaautobusa.get(i);
					if(registracijeDodaj.getText().equals(a.getRegistracija()))
					{
						postoji=true;
					}
				}
				
				if(postoji==false)
				{
				int dodavanje=Integer.parseInt(kapacitetDodajSpinner.getValue().toString());
				noviautobus.dodajAutobus(session, dodavanje, registracijeDodaj.getText().trim().toUpperCase(), modelDodaj.getText());
				JOptionPane.showMessageDialog(dodajBtn, "Uspješno je dodat autobus.");
				autobusiModifikujLista.setModel(new AbstractListModel(){   //osvjezavanje liste

					Session session = HibernateUtil.getSessionFactory().openSession();
					HibernateAutobus autobus=new HibernateAutobus();
					java.util.List autobusi=autobus.sviAutobusi(session);
					public int getSize() {
						return autobusi.size();
					}
					public Object getElementAt(int index) {
						Autobus a=(Autobus)autobusi.get(index);
						return "Model autobusa:"+" " +a.getModel()+" "+"Registracije autobus:"+" "+a.getRegistracija()+" "+"Kapacitet autobusa:"+a.getKapacitet();
					}
					
				});
				kapacitetDodajSpinner.setValue(0);
				registracijeDodaj.setText("");
				modelDodaj.setText("");
				session.close();
				}
				
				else 
				{
					JOptionPane.showMessageDialog(dodajBtn, "Već postoji autobus, sa brojem registracija koje ste vi unijeli.");
					registracijeDodaj.setText("");
				}
				
				}
				
				catch(Exception e2)
				{
					JOptionPane.showMessageDialog(dodajBtn, e2.getMessage());
				}
			}
		});
		
		dodajBtn.setBounds(265, 200, 89, 23);
		panel.add(dodajBtn);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Modifikuj", null, panel_1, null);
		panel_1.setLayout(null);
		
		final JSpinner kapacitetModifikujSpinner = new JSpinner();
		kapacitetModifikujSpinner.setBounds(390, 83, 155, 20);
		panel_1.add(kapacitetModifikujSpinner);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 47, 267, 245);
		panel_1.add(scrollPane);
		
		
		
		autobusiModifikujLista.addListSelectionListener(new ListSelectionListener() //event za selektovani autobusa 
		{
			public void valueChanged(ListSelectionEvent arg0)
			{
				HibernateAutobus autobus=new HibernateAutobus();
				Session session = HibernateUtil.getSessionFactory().openSession();
				java.util.List autobusi=autobus.sviAutobusi(session);
				String selektovan=autobusiModifikujLista.getSelectedValue().toString();
				
				for(int i=0;i<autobusi.size();i++)
				{
					Autobus a=(Autobus) autobusi.get(i);
					String string="Model autobusa:"+" " +a.getModel()+" "+"Registracije autobus:"+" "+a.getRegistracija()+" "+"Kapacitet autobusa:"+a.getKapacitet();
					if(selektovan.equals(string))
					{
						registracijeModifikuj.setText(a.getRegistracija());
						modelModifikuj.setText(a.getModel());
						Object kap=(Integer)a.getKapacitet();
						kapacitetModifikujSpinner.setValue(kap);
					}
				}
			}
			
		
		});
		scrollPane.setViewportView(autobusiModifikujLista);
		
		JLabel lblAutobusi = new JLabel("Autobusi:");
		lblAutobusi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAutobusi.setBounds(10, 22, 72, 19);
		panel_1.add(lblAutobusi);
		
		JLabel label = new JLabel("Model:");
		label.setHorizontalAlignment(SwingConstants.TRAILING);
		label.setBounds(303, 50, 78, 14);
		panel_1.add(label);
		
		modelModifikuj = new JTextField();
		modelModifikuj.setColumns(10);
		modelModifikuj.setBounds(390, 47, 155, 20);
		panel_1.add(modelModifikuj);
				
		JLabel label_1 = new JLabel("Kapacitet:");
		label_1.setHorizontalAlignment(SwingConstants.TRAILING);
		label_1.setBounds(294, 86, 87, 14);
		panel_1.add(label_1);
		
		JLabel label_2 = new JLabel("Registracije:");
		label_2.setHorizontalAlignment(SwingConstants.TRAILING);
		label_2.setBounds(296, 119, 85, 14);
		panel_1.add(label_2);
		
		registracijeModifikuj = new JTextField();
		registracijeModifikuj.setColumns(10);
		registracijeModifikuj.setBounds(390, 116, 155, 20);
		panel_1.add(registracijeModifikuj);
		
		final JButton izmijeniBtn = new JButton("Izmijeni");
		izmijeniBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { //izmjena autobusa
				int d1=0;
				String izuzetak="";
				try
				{
					int broj=(Integer) kapacitetModifikujSpinner.getValue();
					
					if(modelModifikuj.getText().length()==0 && broj==0 && registracijeModifikuj.getText().length()==0)// da li je vrijednost selektovana
					{
						d1=1;
						izuzetak+="Morate selektovati autobusku liniju.";
					}
					
					if(d1==0)
					{
					Session session = HibernateUtil.getSessionFactory().openSession();
					HibernateAutobus autobus=new HibernateAutobus();
					java.util.List listaautobusa=autobus.sviAutobusi(session);
					String selektovan=autobusiModifikujLista.getSelectedValue().toString();
					String model="";
					String registracije="";
					int kapacitet=0;
					boolean postoji=false;
					Autobus a1=new Autobus();
					
					for(int i=0;i<listaautobusa.size();i++)
					{
						Autobus a=(Autobus)listaautobusa.get(i);
						String string="Model autobusa:"+" " +a.getModel()+" "+"Registracije autobus:"+" "+a.getRegistracija()+" "+"Kapacitet autobusa:"+a.getKapacitet();
						if(string.equals(selektovan))
						{
							model=a.getModel();
							registracije=a.getRegistracija();
							kapacitet=a.getKapacitet();
							a1=a;
						}
					}
					
					if(model!=""&& registracije!="" && kapacitet!=0)
					{
				        
						for(int i=0;i<listaautobusa.size();i++)
				        {
				        	Autobus a=(Autobus)listaautobusa.get(i);
				        	if(a.getRegistracija().equals(registracijeModifikuj.getText())&& registracijeModifikuj.getText().equals(registracije)==false)
				        		postoji=true;
				        }
						
				        int kap=Integer.parseInt(kapacitetModifikujSpinner.getValue().toString());
						
						if(postoji==false)
						{
						autobus.modifikujAutobus(session, registracijeModifikuj.getText(), modelModifikuj.getText(), kap, a1); // da ne bi unio postojece registracije
						JOptionPane.showMessageDialog(izmijeniBtn, "Uspješno je izmijenjen autobus.");
						
						kapacitetModifikujSpinner.setValue(0);
						registracijeModifikuj.setText("");
						modelModifikuj.setText("");
						session.close();
						}
						
						else
						{
							JOptionPane.showMessageDialog(izmijeniBtn, "Ponovo unesite registracije.");
							registracijeModifikuj.setText("");
						}
						
					}
					
					}
					else
					{
						JOptionPane.showMessageDialog(izmijeniBtn, "Morate selektovati autobus, koji mijenjate.");
					}
					
				}
				catch(Exception ex1)
				{
					
					//JOptionPane.showMessageDialog(izmijeniBtn, "Neuspješno mijenjena podataka o autobusu.");
					if(izuzetak!="")
					JOptionPane.showMessageDialog(izmijeniBtn, izuzetak);
					JOptionPane.showMessageDialog(izmijeniBtn, ex1.getMessage());
				}
				
				autobusiModifikujLista.setModel(new AbstractListModel(){  //osvjezavanje liste autobusa

					Session session = HibernateUtil.getSessionFactory().openSession();
					HibernateAutobus autobus=new HibernateAutobus();
					java.util.List autobusi=autobus.sviAutobusi(session);
					public int getSize() {
						return autobusi.size();
					}
					public Object getElementAt(int index) {
						Autobus a=(Autobus)autobusi.get(index);
						return "Model autobusa:"+" " +a.getModel()+" "+"Registracije autobus:"+" "+a.getRegistracija()+" "+"Kapacitet autobusa:"+a.getKapacitet();
					}
					
				});
			}
		});
		izmijeniBtn.setBounds(456, 269, 89, 23);
		panel_1.add(izmijeniBtn);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Izbriši", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel label_3 = new JLabel("Model:");
		label_3.setHorizontalAlignment(SwingConstants.TRAILING);
		label_3.setBounds(87, 101, 89, 14);
		panel_2.add(label_3);
		
		modelIzbrisi = new JTextField();
		modelIzbrisi.setEditable(false);
		modelIzbrisi.setColumns(10);
		modelIzbrisi.setBounds(186, 98, 155, 20);
		panel_2.add(modelIzbrisi);
		
		final JSpinner kapacitetIzbrisi = new JSpinner();
		kapacitetIzbrisi.setBounds(186, 134, 155, 20);
		panel_2.add(kapacitetIzbrisi);
		
		JLabel label_4 = new JLabel("Kapacitet:");
		label_4.setHorizontalAlignment(SwingConstants.TRAILING);
		label_4.setBounds(99, 137, 77, 14);
		panel_2.add(label_4);
		
		JLabel label_5 = new JLabel("Registracije:");
		label_5.setHorizontalAlignment(SwingConstants.TRAILING);
		label_5.setBounds(87, 170, 89, 14);
		panel_2.add(label_5);
		
		registracijeIzbrisi = new JTextField();
		registracijeIzbrisi.setEditable(false);
		registracijeIzbrisi.setColumns(10);
		registracijeIzbrisi.setBounds(186, 167, 155, 20);
		panel_2.add(registracijeIzbrisi);
		
		final JButton izbrisiBtn = new JButton("Izbriši");
		izbrisiBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) { //button za brisanjeeee
				String izuzetak="";
				if(registracijeIzbrisi.getText()!="")
				{
					try
					{
						boolean brisi=true;
				HibernateAutobus brisanjeautobus=new HibernateAutobus();
				Session session = HibernateUtil.getSessionFactory().openSession();
				//provjera da li je dodijeljen nekoj liniji
				java.util.List linije=HibernateAutibuskaLinija.sveLinije(session);
				for(int i=0;i<linije.size();i++)
				{
					AutobuskaLinija a=(AutobuskaLinija)linije.get(i);
					Autobus au=HibernateAutobus.nadjiAutobus(session,registracijeIzbrisi.getText());
					if(a.getAutobus()==au)
					{
						izuzetak="Ne možete brisati autobus, dodijeljen je nekoj liniji.";
								brisi=false;
					}
					
				}
				if(brisi==true)
				{
				brisanjeautobus.brisanjeAutobusa(session, registracijeIzbrisi.getText());
				JOptionPane.showMessageDialog(izbrisiBtn, "Uspješno ste izbrisali autobus.");
				registracijeIzbrisi.setText("");
				modelIzbrisi.setText("");
				kapacitetIzbrisi.setValue(0);
				registracijePretraga.setText("");
				}
				else
				{
					JOptionPane.showMessageDialog(izbrisiBtn, izuzetak);
				}
				session.close();
					}
					
					catch(Exception e1)
					{
						//JOptionPane.showMessageDialog(izbrisiBtn, );
						JOptionPane.showMessageDialog(izbrisiBtn, "Neuspješno brisanje.\n"+e1.getMessage());
					}
					
				}
				else
				{
					JOptionPane.showMessageDialog(izbrisiBtn, "Autobus čije ste registracije unijeli ne postoji!.");
				}
				
				autobusiModifikujLista.setModel(new AbstractListModel(){    //osvjezavanje

					Session session = HibernateUtil.getSessionFactory().openSession();
					HibernateAutobus autobus=new HibernateAutobus();
					java.util.List autobusi=autobus.sviAutobusi(session);
					public int getSize() {
						return autobusi.size();
					}
					public Object getElementAt(int index) {
						Autobus a=(Autobus)autobusi.get(index);
						return "Model autobusa:"+" " +a.getModel()+" "+"Registracije autobus:"+" "+a.getRegistracija()+" "+"Kapacitet autobusa:"+a.getKapacitet();
					}
					
				});
			}
		});
		izbrisiBtn.setBounds(252, 220, 89, 23);
		panel_2.add(izbrisiBtn);
		
		JLabel label_6 = new JLabel("Registracije:");
		label_6.setHorizontalAlignment(SwingConstants.TRAILING);
		label_6.setBounds(56, 46, 89, 14);
		panel_2.add(label_6);
		
		registracijePretraga = new JTextField();
		registracijePretraga.setColumns(10);
		registracijePretraga.setBounds(155, 43, 155, 20);
		panel_2.add(registracijePretraga);
		
		final JButton pronadiIzbrisiBtn = new JButton("Pronađi");
		pronadiIzbrisiBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {//event da pronadje autobus koji se briseeee
			    
				try
			    {
				HibernateAutobus brisanjeautobus=new HibernateAutobus();
				Session session = HibernateUtil.getSessionFactory().openSession();
				
				Autobus a=new Autobus();
				a=brisanjeautobus.nadjiAutobus(session,registracijePretraga.getText());
				
				if(a!=null)
				{
					
				modelIzbrisi.setText(a.getModel());
				registracijeIzbrisi.setText(a.getRegistracija());
				Object kap=(Integer)a.getKapacitet();
				kapacitetIzbrisi.setValue(kap);
				}
				
				else
				{
					JOptionPane.showMessageDialog(pronadiIzbrisiBtn, "Ne postoji autobus, čije ste registracije unijeli.");
				}
				
				session.close();
			    }
			    
				catch(Exception e)
			    {
			    	JOptionPane.showMessageDialog(pronadiIzbrisiBtn, "Neuspješna pretraga.\n"+e.getMessage());
			    }
			}
		});
		pronadiIzbrisiBtn.setBounds(320, 42, 89, 23);
		panel_2.add(pronadiIzbrisiBtn);
	}
	public void setVisible(boolean visible) {
		frmEvidencijaAutobusa.setVisible(visible);
	}
}
