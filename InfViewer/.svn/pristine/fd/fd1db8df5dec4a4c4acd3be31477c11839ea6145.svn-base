package crudActions;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.MyApp;
import gui.Tab;
import model.Table;

/**
 * Nasledjuje {@link JFrame} klasu.
 * <code>ChooseParentDialog</code> je klasa koja sluzi da prikaze 
 * dijalog koji iskace da bi se birao roditelj ako ima vise roditelja.
 * @see JFrame
 * @see ResourceBundle
 * @see Locale
 */

@SuppressWarnings("serial")
public class ChooseParentDialog extends JFrame{
	
	private static ChooseParentDialog instance;
	JComboBox combo = new JComboBox();
	private JButton choose;
	private ResourceBundle resourceBundle;
	String koJeKliknuo;

	/**
	 * Vraca jedinu instancu {@link ChooseParentDialog} klase ove aplikacije.
	 * Ako instanca ne postoji, kreira novu. Poziva metod 
	 * 
	 * @return {@link ChooseParentDialog}
	 */
	
	public static ChooseParentDialog getInstance() {
		if (instance == null) {
			instance = new ChooseParentDialog();
			instance.initialise();

		}
		return instance;
	}
	
	/**
	 * {@code initialise} Inicijalizovanje komponenti ChooseParent prozora 
	 */
	
	public void initialise(){
		   
	    setExtendedState(JFrame.MAXIMIZED_BOTH);
	    setResizable(false);
	    setVisible(true);
	    setTitle(MyApp.getInstance().getResourceBundle().getString("naslovChoose"));
		setLocationRelativeTo(null);
		Dimension dim = new Dimension(300, 300);
		setSize(dim);

		JPanel glavni = new JPanel();
		add(glavni, BorderLayout.CENTER);
		glavni.setLayout(new BoxLayout(glavni, BoxLayout.PAGE_AXIS));
		combo.setPreferredSize(new Dimension(1,25));
		glavni.add(combo);
		
		choose = new JButton("Choose");
		
		resourceBundle = ResourceBundle.getBundle("MessageResources.MessageResources", Locale.getDefault());
		initComponents();
		
		
		choose.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  MyApp.getInstance().getPan().getGornjiPanel().setLayout(new BorderLayout());
					MyApp.getInstance().getPan().setLayout(new GridLayout(2,1));
					MyApp.getInstance().getPan().getGornjiPanel().setSize(MyApp.getInstance().getPan().getDonjiPanel().getSize());
					MyApp.getInstance().getTabPanel().setSize(MyApp.getInstance().getPan().getDonjiPanel().getSize());
					Tab parent = new Tab(MyApp.getInstance().getTableById(combo.getSelectedItem().toString()),true);
					parent.setName(combo.getSelectedItem().toString());
					
					for(int i=0;i<MyApp.getInstance().getTabPanel().getTabCount();i++){
						if(((Tab)MyApp.getInstance().getTabPanel().getComponentAt(i)).getTabId().equals(koJeKliknuo)){
							MyApp.getInstance().getTabPanel().remove(i);
							break;
						}
					}
					
					boolean postoji = false;
					//MyApp.getInstance().getTabPanel().removeAll();
					for(int i=0;i<MyApp.getInstance().getTabPanel().getTabCount();i++){
						if(((Tab)MyApp.getInstance().getTabPanel().getComponentAt(i)).getTabId().equals(parent.getName())){
							MyApp.getInstance().getTabPanel().setSelectedIndex(i);
							
							postoji = true;
							break;
						}
					}
					if(postoji==false){
					//MyApp.getInstance().getTabPanel().addTab(parent.getTable().getIdLabelTable(),parent);
					//MyApp.getInstance().getTabPanel().setTabComponentAt(0, new Tab(parent.getTableRows(), parent.getTable()));
					Table t = MyApp.getInstance().getTableById(parent.getTable().getIdLabelTable());
					MyApp.getInstance().getTabPanel().add(t);
					MyApp.getInstance().getTabPanel().revalidate();
					MyApp.getInstance().getTabPanel().repaint();
					MyApp.getInstance().getPan().getDonjiPanel().setVisible(true);
					MyApp.getInstance().getPan().getGornjiPanel().setLayout(new BorderLayout());
					MyApp.getInstance().getPan().setLayout(new GridLayout(2,1));
					MyApp.getInstance().getPan().getGornjiPanel().setSize(MyApp.getInstance().getPan().getDonjiPanel().getSize());
					MyApp.getInstance().getTabPanel().setSize(MyApp.getInstance().getPan().getDonjiPanel().getSize());
					if(t.getChildOfTable().isEmpty()){
						MyApp.getInstance().getPan().getDonjiPanel().setVisible(false);
						MyApp.getInstance().getPan().getGornjiPanel().setLayout(new BorderLayout());
						MyApp.getInstance().getPan().setLayout(new BorderLayout());
						MyApp.getInstance().getPan().getGornjiPanel().setSize(MyApp.getInstance().getPan().getSize());
						MyApp.getInstance().getTabPanel().setSize(MyApp.getInstance().getPan().getGornjiPanel().getSize());
						MyApp.getInstance().getTabPanelDonji().removeAll();
						MyApp.getInstance().getTabPanelDonji().revalidate();
						MyApp.getInstance().getTabPanelDonji().repaint();
						//ako nema decu treba da ostavi dole prazan
						
					}
					
					
					
				 
			  } dispose();
			  }
			 
			  }

	 );
		glavni.add(choose);
		
	}
	
	/**
	 * Preuzimanje vrednosti komponente koJeKliknuo.
	 * @return koJeKliknuo
	 */
	
	public String getKoJeKliknuo() {
		return koJeKliknuo;
	}

	/**
	 *  Dodeljivanje vrednosti komponente koJeKliknuo.
	 *  @param koJeKliknuo
	 *  @return void
	 */
	
	public void setKoJeKliknuo(String koJeKliknuo) {
		this.koJeKliknuo = koJeKliknuo;
	}
	
	/**
	 * Preuzimanje vrednosti komponente combo.
	 * @return combo
	 */
	
	public JComboBox getCombo() {
		return combo;
	}

	/**
	 *  Dodeljivanje vrednosti komponente combo.
	 *  @param combo
	 *  @return void
	 */
	
	public void setCombo(JComboBox combo) {
		this.combo = combo;
	}
	
	
	/**
	 * Inicijalizacija svih komponenti koje se nalaze unutar dijaloga
	 * <p>Lokalizacija svih komponenti koje se nalaze unutar dijaloga
	 * @return void
	 * @see ResourceBundle
	 */

	public void initComponents(){
		choose.setText(MyApp.getInstance().getResourceBundle().getString("btnChoose"));
		setTitle(MyApp.getInstance().getResourceBundle().getString("naslovChoose"));
	}
}
