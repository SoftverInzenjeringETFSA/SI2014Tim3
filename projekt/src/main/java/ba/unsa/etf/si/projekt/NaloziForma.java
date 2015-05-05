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

public class NaloziForma {

	private JFrame frmPravljenjeNaloga;
	private JTextField textField;

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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPravljenjeNaloga = new JFrame();
		frmPravljenjeNaloga.setTitle("Pravljenje naloga");
		frmPravljenjeNaloga.setBounds(100, 100, 552, 392);
		frmPravljenjeNaloga.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPravljenjeNaloga.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 86, 277, 256);
		frmPravljenjeNaloga.getContentPane().add(scrollPane);
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Sarajevo-Kakanj A00-A-000 Meho Mehic Peron 3 12:30", "Sarajevo-Kakanj A00-A-000 Meho Mehic Peron 3 12:30", "Sarajevo-Kakanj A00-A-000 Meho Mehic Peron 3 12:30", "Sarajevo-Kakanj A00-A-000 Meho Mehic Peron 3 12:30"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane.setViewportView(list);
		
		JButton button = new JButton("Nazad");
		button.setBounds(338, 11, 89, 23);
		frmPravljenjeNaloga.getContentPane().add(button);
		
		JButton button_1 = new JButton("Odjava");
		button_1.setBounds(437, 11, 89, 23);
		frmPravljenjeNaloga.getContentPane().add(button_1);
		
		JLabel lblAutobuskeLinije = new JLabel("Autobuske linije:");
		lblAutobuskeLinije.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAutobuskeLinije.setBounds(23, 55, 112, 20);
		frmPravljenjeNaloga.getContentPane().add(lblAutobuskeLinije);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(410, 86, 116, 20);
		frmPravljenjeNaloga.getContentPane().add(dateChooser);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(410, 129, 116, 20);
		frmPravljenjeNaloga.getContentPane().add(textField);
		
		JLabel label = new JLabel("Vrijeme polaska:");
		label.setBounds(322, 132, 78, 14);
		frmPravljenjeNaloga.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Datum polaska:");
		label_1.setBounds(322, 88, 78, 14);
		frmPravljenjeNaloga.getContentPane().add(label_1);
		
		JButton btnNapraviNalog = new JButton("Napravi nalog");
		btnNapraviNalog.setBounds(410, 319, 116, 23);
		frmPravljenjeNaloga.getContentPane().add(btnNapraviNalog);
	}
}
