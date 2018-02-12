package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
/**
 * {@code Paneli} naledjuje {@link JPanel} klasu. Sadrzi tab komponente  {@link JTabbedPane}
 * 
 * @see JTabbedPane
 * @see JPanel
 *
 */
public class Paneli extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel gornjiPanel;
	private JPanel donjiPanel;
	private JTabbedPane tp1;
	private JTabbedPane tp2;
	private ToolBarGornji tb1;
	private ToolBarDonji tb2;
	
	/**
	 * Konstruktor sa parametrima {@link Paneli} klase.
	 * @see GridLayout
	 */
	
	public Paneli(){
		
		gornjiPanel = new JPanel();
		donjiPanel = new JPanel();
		tp1 = new JTabbedPane();
		tp2 = new JTabbedPane();
		tb1 = new ToolBarGornji();
		tb2 = new ToolBarDonji();
		
		
		this.setLayout(new GridLayout(2,1));
		
		if ((MyApp.getKorisnickoIme().equals("admin"))) {
			gornjiPanel.setLayout(new BorderLayout());
			gornjiPanel.add(tb1, BorderLayout.NORTH);
			gornjiPanel.add(tp1);
			add(gornjiPanel);
			
		}
		else {	
			System.out.println("HAHAHAHA");
		    repaint();
			gornjiPanel.setLayout(new BorderLayout());
			gornjiPanel.add(tb1, BorderLayout.NORTH);
			gornjiPanel.add(tp1);
			add(gornjiPanel);

			donjiPanel.setLayout(new BorderLayout());
			donjiPanel.add(tb2, BorderLayout.NORTH);
			add(donjiPanel);
			
			validate();
		}
		
		
	}

	/**
	 * Preuzimanje vrednosti tp1
	 * @see JTabbedPane
	 * @return tp1
	 */
	public JTabbedPane getTp1() {
		return tp1;
	}

	/**
	 * Postavljanje nove vrednosti tp1
	 * @param tp1
	 * @return void
	 * @see JTabbedPane
	 * 
	 */
	public void setTp1(JTabbedPane tp1) {
		this.tp1 = tp1;
	}
	/**
	 * Preuzimanje vrednosti tp2
	 * @see JTabbedPane
	 * @return tp2
	 */

	public JTabbedPane getTp2() {
		return tp2;
	}
	/**
	 * Postavljanje nove vrednosti komponente tp2
	 * @param tp2
	 * @return void
	 * @see JTabbedPane
	 * 
	 */
	public void setTp2(JTabbedPane tp2) {
		this.tp2 = tp2;
	}
	/**
	 * Preuzimanje vrednosti tb1
	 * @return tb1
	 */

	public ToolBarGornji getTb1() {
		return tb1;
	}

	/**
	 * Postavljanje nove vrednosti komponente tp1
	 * @param tb1
	 * @return void
	 */
	public void setTb1(ToolBarGornji tb1) {
		this.tb1 = tb1;
	}

	/**
	 * Preuzimanje vrednosti tb2
	 * @return tb2
	 */
	public ToolBarDonji getTb2() {
		return tb2;
	}

	/**
	 * Postavljanje nove vrednosti komponente tb2
	 * @return void
	 * @param tb2
	 * 
	 */
	public void setTb2(ToolBarDonji tb2) {
		this.tb2 = tb2;
	}

	/**
	 * Preuzimanje vrednosti komponente gornjiPanel
	 * @see JPanel
	 * @return gornjiPanel
	 */
	public JPanel getGornjiPanel() {
		return gornjiPanel;
	}

	/**
	 * Postavljanje nove vrednosti komponente gornjiPanel
	 * @return void
	 * @see JPanel
	 * 
	 */
	public void setGornjiPanel(JPanel gornjiPanel) {
		this.gornjiPanel = gornjiPanel;
	}
	/**
	 * Preuzimanje vrednosti komponente gornjiPanel
	 * @see JPanel
	 * @return donjiPanel
	 */

	public JPanel getDonjiPanel() {
		return donjiPanel;
	}

	/**
	 * Postavljanje nove vrednosti komponente donjiPanel
	 * @param donjiPanel
	 * @return void
	 * @see JPanel
	 * 
	 */
	public void setDonjiPanel(JPanel donjiPanel) {
		this.donjiPanel = donjiPanel;
	}
	
	
	
		
		
	
	

}
