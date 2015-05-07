package ba.unsa.etf.si.projekt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AutobusiForma {

	private JFrame frmEvidencijaAutobusa;
	private JTextField modelDodaj;
	private JTextField registracijeDodaj;
	private JTextField modelModifikuj;
	private JTextField registracijeModifikuj;
	private JTextField modelIzbrisi;
	private JTextField registracijeIzbrisi;
	private JTextField registracijePretraga;
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
					AutobusiForma window = new AutobusiForma();
					window.frmEvidencijaAutobusa.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AutobusiForma() {
		initialize();
	}

	public AutobusiForma(String tipKorisnika) {
		// TODO Auto-generated constructor stub
		this.tipKorisnika = tipKorisnika;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEvidencijaAutobusa = new JFrame();
		frmEvidencijaAutobusa.setTitle("Evidencija autobusa");
		frmEvidencijaAutobusa.setBounds(100, 100, 557, 434);
		frmEvidencijaAutobusa.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmEvidencijaAutobusa.getContentPane().setLayout(null);
		
		JButton odjavaBtn = new JButton("Odjava");
		odjavaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrijavaForma p = new PrijavaForma();
				p.setVisible(true);
				setVisible(false);
			}
		});
		odjavaBtn.setBounds(428, 11, 89, 23);
		frmEvidencijaAutobusa.getContentPane().add(odjavaBtn);
		
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
		nazadBtn.setBounds(329, 11, 89, 23);
		frmEvidencijaAutobusa.getContentPane().add(nazadBtn);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(23, 53, 494, 331);
		frmEvidencijaAutobusa.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Dodaj", null, panel, null);
		panel.setLayout(null);
		
		modelDodaj = new JTextField();
		modelDodaj.setBounds(199, 71, 155, 20);
		panel.add(modelDodaj);
		modelDodaj.setColumns(10);
		
		JLabel lblModel = new JLabel("Model:");
		lblModel.setBounds(157, 74, 32, 14);
		panel.add(lblModel);
		
		JSpinner kapacitetDodajSpinner = new JSpinner();
		kapacitetDodajSpinner.setBounds(199, 107, 155, 20);
		panel.add(kapacitetDodajSpinner);
		
		JLabel lblKapacitet = new JLabel("Kapacitet:");
		lblKapacitet.setBounds(140, 110, 49, 14);
		panel.add(lblKapacitet);
		
		registracijeDodaj = new JTextField();
		registracijeDodaj.setBounds(199, 140, 155, 20);
		panel.add(registracijeDodaj);
		registracijeDodaj.setColumns(10);
		
		JLabel lblRegistracije = new JLabel("Registracije:");
		lblRegistracije.setBounds(129, 143, 60, 14);
		panel.add(lblRegistracije);
		
		JButton dodajBtn = new JButton("Unesi");
		dodajBtn.setBounds(265, 200, 89, 23);
		panel.add(dodajBtn);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Modifikuj", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 47, 238, 245);
		panel_1.add(scrollPane);
		
		JList autobusiModifikujLista = new JList();
		autobusiModifikujLista.setModel(new AbstractListModel() {
			String[] values = new String[] {"Mercedes xyz A00-A-000 Kapacitet 20", "Mercedes xyz A00-A-000 Kapacitet 20", "Mercedes xyz A00-A-000 Kapacitet 20", "Mercedes xyz A00-A-000 Kapacitet 20"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane.setViewportView(autobusiModifikujLista);
		
		JLabel lblAutobusi = new JLabel("Autobusi:");
		lblAutobusi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAutobusi.setBounds(10, 22, 72, 19);
		panel_1.add(lblAutobusi);
		
		JLabel label = new JLabel("Model:");
		label.setBounds(282, 50, 32, 14);
		panel_1.add(label);
		
		modelModifikuj = new JTextField();
		modelModifikuj.setColumns(10);
		modelModifikuj.setBounds(324, 47, 155, 20);
		panel_1.add(modelModifikuj);
		
		JSpinner kapacitetModifikujSpinner = new JSpinner();
		kapacitetModifikujSpinner.setBounds(324, 83, 155, 20);
		panel_1.add(kapacitetModifikujSpinner);
		
		JLabel label_1 = new JLabel("Kapacitet:");
		label_1.setBounds(265, 86, 49, 14);
		panel_1.add(label_1);
		
		JLabel label_2 = new JLabel("Registracije:");
		label_2.setBounds(254, 119, 60, 14);
		panel_1.add(label_2);
		
		registracijeModifikuj = new JTextField();
		registracijeModifikuj.setColumns(10);
		registracijeModifikuj.setBounds(324, 116, 155, 20);
		panel_1.add(registracijeModifikuj);
		
		JButton izmijeniBtn = new JButton("Izmijeni");
		izmijeniBtn.setBounds(390, 269, 89, 23);
		panel_1.add(izmijeniBtn);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Izbriši", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel label_3 = new JLabel("Model:");
		label_3.setBounds(144, 101, 32, 14);
		panel_2.add(label_3);
		
		modelIzbrisi = new JTextField();
		modelIzbrisi.setColumns(10);
		modelIzbrisi.setBounds(186, 98, 155, 20);
		panel_2.add(modelIzbrisi);
		
		JSpinner kapacitetIzbrisi = new JSpinner();
		kapacitetIzbrisi.setBounds(186, 134, 155, 20);
		panel_2.add(kapacitetIzbrisi);
		
		JLabel label_4 = new JLabel("Kapacitet:");
		label_4.setBounds(127, 137, 49, 14);
		panel_2.add(label_4);
		
		JLabel label_5 = new JLabel("Registracije:");
		label_5.setBounds(116, 170, 60, 14);
		panel_2.add(label_5);
		
		registracijeIzbrisi = new JTextField();
		registracijeIzbrisi.setColumns(10);
		registracijeIzbrisi.setBounds(186, 167, 155, 20);
		panel_2.add(registracijeIzbrisi);
		
		JButton izbrisiBtn = new JButton("Izbriši");
		izbrisiBtn.setBounds(252, 220, 89, 23);
		panel_2.add(izbrisiBtn);
		
		JLabel label_6 = new JLabel("Registracije:");
		label_6.setBounds(85, 46, 60, 14);
		panel_2.add(label_6);
		
		registracijePretraga = new JTextField();
		registracijePretraga.setColumns(10);
		registracijePretraga.setBounds(155, 43, 155, 20);
		panel_2.add(registracijePretraga);
		
		JButton pronadiIzbrisiBtn = new JButton("Pronađi");
		pronadiIzbrisiBtn.setBounds(320, 42, 89, 23);
		panel_2.add(pronadiIzbrisiBtn);
	}
	public void setVisible(boolean visible) {
		frmEvidencijaAutobusa.setVisible(visible);
	}
}
