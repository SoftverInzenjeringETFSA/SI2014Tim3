package ba.unsa.etf.si.projekt;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class MenadzerPocetna {

	private JFrame frmMenaderPoetna;

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
					MenadzerPocetna window = new MenadzerPocetna();
					window.frmMenaderPoetna.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MenadzerPocetna() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMenaderPoetna = new JFrame();
		frmMenaderPoetna.setTitle("Menadžer početna");
		frmMenaderPoetna.setResizable(false);
		frmMenaderPoetna.setBounds(100, 100, 472, 342);
		frmMenaderPoetna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenaderPoetna.getContentPane().setLayout(null);
		
		JLabel lblPrijavljeniSteKao = new JLabel("Dobrodošli!");
		lblPrijavljeniSteKao.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrijavljeniSteKao.setBounds(41, 17, 249, 23);
		frmMenaderPoetna.getContentPane().add(lblPrijavljeniSteKao);
		
		JButton odjavaBtn = new JButton("Odjava");
		odjavaBtn.setBounds(333, 17, 89, 23);
		frmMenaderPoetna.getContentPane().add(odjavaBtn);
		
		JPanel panel = new JPanel();
		panel.setBounds(41, 94, 381, 208);
		frmMenaderPoetna.getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 5));
		
		JButton izvjestajiBtn = new JButton("Izvještaji");
		panel.add(izvjestajiBtn);
		
		JButton radniciBtn = new JButton("Evidencija radnika");
		panel.add(radniciBtn);
		
		JButton linijeBtn = new JButton("Evidencija autobuskih linija");
		panel.add(linijeBtn);
		
		JButton autobusiBtn = new JButton("Evidencija autobusa");
		panel.add(autobusiBtn);
		
		JButton naloziBtn = new JButton("Pravljenje naloga");
		panel.add(naloziBtn);
		
		JLabel lblPrijavljeniSteKao_1 = new JLabel("Prijavljeni ste kao menadžer!");
		lblPrijavljeniSteKao_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrijavljeniSteKao_1.setBounds(41, 47, 266, 23);
		frmMenaderPoetna.getContentPane().add(lblPrijavljeniSteKao_1);
	}

}
