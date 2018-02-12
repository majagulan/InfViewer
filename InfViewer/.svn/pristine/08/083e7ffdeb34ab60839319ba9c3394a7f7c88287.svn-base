package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
/**
  * 
 * Nasledjuje {@link JDialog} klasu.
 * Koristi se prijavu na odabrani json fajl 
 * @see ResourceBundle
 * @see Locale
 *
 */
@SuppressWarnings("serial")
public class JsonLogIn extends JDialog{
	
	private ResourceBundle resourceBundle =  ResourceBundle.getBundle("MessageResources.MessageResources", Locale.getDefault());
	private Locale locale;	

	private JFrame frame = null;
	
	private JLabel labelaJson;
	private JTextField izabraniJson;
	
	private JButton konektujSe;
	private JButton odustani;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel;
	/**
	 * Konstruktor {@link  JsonLogIn} klase
	 * 
	 */
	public JsonLogIn() {
	    this(null, true);
	}

	/**
	 * Konstruktor sa parametrima {@link JsonLogIn} klase.
	 *<p> Kreira dijalog za izbor json fajla i povezivanje na taj fajl
	 */
	public JsonLogIn(final JFrame parent, boolean modal){
		
		super(parent, modal);
	   
		
		setSize(new Dimension(350,330));
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		setTitle(MyApp.getInstance().getResourceBundle().getString("naslovIzaberiteOpcije"));
		
		labelaJson = new JLabel(MyApp.getInstance().getResourceBundle().getString("labelaJson"));
		izabraniJson = new JTextField(20);
		izabraniJson.setText("JSONtpo.json");
	        
		
		konektujSe = new JButton(MyApp.getInstance().getResourceBundle().getString("konektujSe"));
		odustani = new JButton(MyApp.getInstance().getResourceBundle().getString("odustani"));
		
		panel1 = new JPanel(new GridLayout(2,1));
		panel1.add(labelaJson);
		panel1.add(izabraniJson);
		panel1.setBorder(new EmptyBorder(10,5,10,70));
		
		panel2 = new JPanel(new GridLayout(1,2));
		panel2.add(konektujSe);
		panel2.add(odustani);
		
		panel = new JPanel(new BorderLayout());
		panel.add(panel1, BorderLayout.NORTH);
		panel.add(panel2, BorderLayout.SOUTH);
		add(panel);
		
		pack();
		setLocationRelativeTo(null);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       
		
		
		addWindowListener(new WindowAdapter() {  
	        @Override
	        public void windowClosing(WindowEvent e) {  
	        	parent.dispose();                
	        	System.exit(0);  
	        }  
	    });
		
		konektujSe.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String jsonName = izabraniJson.getText();
				
				if((jsonName.equals(""))){
					izabraniJson.setText("JSONtpo.json");
					JOptionPane.showMessageDialog(frame, resourceBundle.getString("upozorenje2"), resourceBundle.getString("upozorenje3"),2);												
				}
				else if((jsonName.equals("JSONprimer.json")) || (jsonName.equals("JSONtpo.json"))){
					MyApp.getInstance().setJsonFile(jsonName);
    				parent.setVisible(true);
        			setVisible(false);
				}
				else {
					JOptionPane.showMessageDialog(frame, resourceBundle.getString("upozorenje8"), resourceBundle.getString("upozorenje3"),2);
				}       		
			}		
		});
		
		
		odustani.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                System.exit(0);
            }
        });
	
	}
	
	/**
	 * Inicijalizacija svih komponenti koje se nalaze unutar dijaloga
	 * <p>Lokalizacija svih komponenti koje se nalaze unutar dijaloga
	 * @return void
	 * @see ResourceBundle
	 */
	public void initComponents(){
		setTitle(resourceBundle.getString("konektujNaJson"));
		labelaJson.setText(resourceBundle.getString("labelaJson"));
		konektujSe.setText(resourceBundle.getString("konektujSe"));
		odustani.setText(resourceBundle.getString("odustani"));
		resourceBundle.getString("upozorenje2");
		resourceBundle.getString("upozorenje3");
		
	
	}
	/**
	 * Preuzimanje vrednosti polja 
	 * @see JTextField
	 * @return izabraniJson
	 */

	public JTextField getIzabraniJson() {
		return izabraniJson;
	}
	/**
	 * Postavljanje nove vrednosti polja
	 * @see JTextField
	 * @param izabraniJson
	 * @return void
	 */

	public void setIzabraniJson(JTextField izabraniJson) {
		this.izabraniJson = izabraniJson;
	}
	
	


}
