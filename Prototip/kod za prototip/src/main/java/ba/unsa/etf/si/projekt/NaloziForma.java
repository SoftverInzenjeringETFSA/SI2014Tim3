package ba.unsa.etf.si.projekt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.UIManager;

import java.awt.Font;

import com.toedter.calendar.JDateChooser;

import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		
		JList linijeList = new JList();
		linijeList.setModel(new AbstractListModel() {
			String[] values = new String[] {"Sarajevo-Kakanj A00-A-000 Meho Mehic Peron 3 12:30", "Sarajevo-Kakanj A00-A-000 Meho Mehic Peron 3 12:30", "Sarajevo-Kakanj A00-A-000 Meho Mehic Peron 3 12:30", "Sarajevo-Kakanj A00-A-000 Meho Mehic Peron 3 12:30"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
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
		
		JDateChooser datumDate = new JDateChooser();
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
		
		JButton napraviBtn = new JButton("Napravi nalog");
		napraviBtn.setBounds(410, 319, 116, 23);
		frmPravljenjeNaloga.getContentPane().add(napraviBtn);
	}
	
	public void setVisible(boolean visible) {
		frmPravljenjeNaloga.setVisible(visible);
	}
}
