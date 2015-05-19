package ba.unsa.etf.si.projekt.forme;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.UIManager;

import java.awt.Font;

import ba.unsa.etf.si.projekt.entiteti.AutobuskaLinija;
import ba.unsa.etf.si.projekt.hibernate.HibernateAutibuskaLinija;
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

public class NaloziForma {

	private JFrame frmPravljenjeNaloga;
	private JTextField vrijeme;
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
					NaloziForma window = new NaloziForma();
					window.frmPravljenjeNaloga.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NaloziForma() {
		initialize();
	}
	
	public NaloziForma(String tipKorisnika) {
		initialize();
		this.tipKorisnika = tipKorisnika;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPravljenjeNaloga = new JFrame();
		frmPravljenjeNaloga.setTitle("Pravljenje naloga");
		frmPravljenjeNaloga.setBounds(100, 100, 552, 392);
		frmPravljenjeNaloga.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmPravljenjeNaloga.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 86, 277, 256);
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
					//return a.getOdrediste()+" "+a.getPolaziste()+" "+a.getBrojLinije()+" "+a.getDatumPolaska_dan()+" "+a.getDatumPolaska_mjesec()+" "+a.getDatumPolaska_godina();
				return "Odredište:"+a.getOdrediste()+" "+"Polazište:"+a.getPolaziste()+" "+"Broj linije:"+" "+a.getBrojLinije()+"Datum polaska:"+ " "+a.getDatumPolaska_dan()+"."+a.getDatumPolaska_mjesec()+"."+ a.getDatumPolaska_godina();
				}
			});
			
		
		/*linijeList.setModel(new AbstractListModel() {
			String[] values = new String[] {"Sarajevo-Kakanj A00-A-000 Meho Mehic Peron 3 12:30", "Sarajevo-Kakanj A00-A-000 Meho Mehic Peron 3 12:30", "Sarajevo-Kakanj A00-A-000 Meho Mehic Peron 3 12:30", "Sarajevo-Kakanj A00-A-000 Meho Mehic Peron 3 12:30"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});*/
		scrollPane.setViewportView(linijeList);
		
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
		nazadBtn.setBounds(338, 11, 89, 23);
		frmPravljenjeNaloga.getContentPane().add(nazadBtn);
		
		JButton odjavaBtn = new JButton("Odjava");
		odjavaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrijavaForma p = new PrijavaForma();
				p.setVisible(true);
				setVisible(false);
			}
		});
		odjavaBtn.setBounds(437, 11, 89, 23);
		frmPravljenjeNaloga.getContentPane().add(odjavaBtn);
		
		JLabel lblAutobuskeLinije = new JLabel("Autobuske linije:");
		lblAutobuskeLinije.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAutobuskeLinije.setBounds(23, 55, 112, 20);
		frmPravljenjeNaloga.getContentPane().add(lblAutobuskeLinije);
		
		final JDateChooser datumDate = new JDateChooser();
		datumDate.setBounds(410, 86, 116, 20);
		frmPravljenjeNaloga.getContentPane().add(datumDate);
		
		vrijeme = new JTextField();
		vrijeme.setColumns(10);
		vrijeme.setBounds(410, 129, 116, 20);
		frmPravljenjeNaloga.getContentPane().add(vrijeme);
		
		JLabel label = new JLabel("Vrijeme polaska:");
		label.setBounds(322, 132, 78, 14);
		frmPravljenjeNaloga.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Datum polaska:");
		label_1.setBounds(322, 88, 78, 14);
		frmPravljenjeNaloga.getContentPane().add(label_1);
		
		final JButton napraviBtn = new JButton("Napravi nalog");
		napraviBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //pravljenje nalogaaa
				
				try
				{
					Session session = HibernateUtil.getSessionFactory().openSession();
					HibernateNalog n=new HibernateNalog();
					HibernateAutibuskaLinija linija1=new HibernateAutibuskaLinija();
					AutobuskaLinija linija=new AutobuskaLinija();
					String selektovano=(linijeList.getSelectedValue().toString());
					java.util.List listasvihlinija=linija1.sveLinije(session);
					int broj=0;
					for(int i=0;i<listasvihlinija.size();i++)
					{
						AutobuskaLinija a=(AutobuskaLinija)listasvihlinija.get(i);
						String uporedi="Odredište:"+a.getOdrediste()+" "+"Polazište:"+a.getPolaziste()+" "+"Broj linije:"+" "+a.getBrojLinije()+"Datum polaska:"+ " "+a.getDatumPolaska_dan()+"."+a.getDatumPolaska_mjesec()+"."+ a.getDatumPolaska_godina();
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
					int sati=d.getHours();
					int minute=d.getMinutes();
					n.dodajNalog(session, linija, dan, mjesec, godina, sati, minute);
					JOptionPane.showMessageDialog(napraviBtn, "Uspješno ste kreirali nalog.");

				}
				catch(Exception ec)
				{
					JOptionPane.showMessageDialog(napraviBtn, "Neuspješno kreiranje naloga.");
					JOptionPane.showMessageDialog(napraviBtn, "ec");
				}
			}
		});
		napraviBtn.setBounds(410, 319, 116, 23);
		frmPravljenjeNaloga.getContentPane().add(napraviBtn);
	}
	
	public void setVisible(boolean visible) {
		frmPravljenjeNaloga.setVisible(visible);
	}
}
