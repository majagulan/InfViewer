package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
/**
*
* Nasledjuje {@link JDialog} klasu.
* Koristi se za prijavu na sistem, odabir podataka iz Jsona ili iz Baze podataka i odabir jezika
* @see ResourceBundle
* @see Locale
* @see JsonLogIn
* @see JFrame
* @see JComboBox
*
*/
@SuppressWarnings("serial")
public class PocetniDijalog extends JDialog{
	
	private ResourceBundle resourceBundle =  ResourceBundle.getBundle("MessageResources.MessageResources", Locale.getDefault());
	private Locale locale;
	
	private JsonLogIn jsonLogIn = new JsonLogIn();
	
	private JFrame frame = null;
	
	//labele i polja
	private JLabel labela1;
	private JTextField korisnickoIme;
	private JLabel labela2;
	private JPasswordField lozinka;
	private JLabel labela3;
	
	
	private static final String[] izvor1 = {"JSON", "BAZA"};
	private static final int JSON_ODABERI_INDEX = 0;
	private static final int BAZA_ODABERI_INDEX = 1;
	
	private JComboBox<String> izvor2;
	private JLabel labela4;
	
	//dugmici
	private JRadioButton srpski;
	private JRadioButton engleski;
	private JButton ulogujSe;
	private JButton izadji;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel;
	private ButtonGroup jezici;
	
	/**
	 * Konstruktor {@link  PocetniDijalog} klase
	 * 
	 */
	public PocetniDijalog() {
	    this(null, true);
	}

	/**
	 * Konstruktor sa parametrima {@link JsonLogIn} klase.
	 *<p> Ukoliko su odabrani podaci iz Json fajla ucitava prozor za odabir Json fajla
	 *<p> Ukoliko su odabrani podaci iz Baze podatak ucitava prozor za unos potrebnih parametara za konekciju na server
	 */
	
	public PocetniDijalog(final JFrame parent, boolean modal){
		
		super(parent, modal);
	   
		
		setSize(new Dimension(350,330));
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		setTitle(MyApp.getInstance().getResourceBundle().getString("naslovIzaberiteOpcije"));
		
		labela1 = new JLabel(MyApp.getInstance().getResourceBundle().getString("lab1"));
		korisnickoIme = new JTextField(20);
	    korisnickoIme.setText("admin");
		labela2 = new JLabel(MyApp.getInstance().getResourceBundle().getString("lab2"));
		lozinka = new JPasswordField(8);
		lozinka.setText("admin");
		labela3 = new JLabel(MyApp.getInstance().getResourceBundle().getString("lab3"));
		izvor2 = new JComboBox<String>();
		
		for(String i : izvor1){
			izvor2.addItem(i);
		}
		
		labela4 = new JLabel(MyApp.getInstance().getResourceBundle().getString("lab4"));
		srpski = new JRadioButton(MyApp.getInstance().getResourceBundle().getString("btnSrpski"));
		engleski = new JRadioButton(MyApp.getInstance().getResourceBundle().getString("btnEngleski"));
		engleski.setSelected(true);
		jezici = new ButtonGroup();
		
		ulogujSe = new JButton(MyApp.getInstance().getResourceBundle().getString("btnUloguj"));
		izadji = new JButton(MyApp.getInstance().getResourceBundle().getString("btnIzadji"));
		
		panel1 = new JPanel(new GridLayout(9,1));
		panel1.add(labela1);
		panel1.add(korisnickoIme);
		panel1.add(labela2);
		panel1.add(lozinka);
		panel1.add(labela3);
		panel1.add(izvor2);
		panel1.add(labela4);
		panel1.add(srpski);
		panel1.add(engleski);
		panel1.setBorder(new EmptyBorder(10,5,10,70));
		
		panel2 = new JPanel(new GridLayout(1,2));
		jezici.add(srpski);
		jezici.add(engleski);
		panel2 = new JPanel(new GridLayout(1,2));
		panel2.add(ulogujSe);
		panel2.add(izadji);
		
		panel = new JPanel(new BorderLayout());
		panel.add(panel1, BorderLayout.NORTH);
		panel.add(panel2, BorderLayout.SOUTH);
		add(panel);
		
		addWindowListener(new WindowAdapter() {  
	        @Override
	        public void windowClosing(WindowEvent e) {  
	        	System.exit(0);  
	        }  
	    });
		
		srpski.addActionListener(new ActionListener(){
			/**
			 * Setuje za podrazumevani jezik srpski ukoliko je odabrano dugme {@code srpski}
			 */
			public void actionPerformed(ActionEvent arg0) {
				
				Locale.setDefault(new Locale("sr", "RS"));
				locale = new Locale("sr", "RS");
				resourceBundle = ResourceBundle.getBundle("MessageResources.MessageResources", locale);
				initComponents();
	
			}
			
		});
		
		engleski.addActionListener(new ActionListener(){
			/**
			 * Setuje za podrazumevani jezik engleski ukoliko je odabrano dugme {@code engleski}
			 */
			public void actionPerformed(ActionEvent e) {
				
				Locale.setDefault(new Locale("en", "US"));
				locale = new Locale("en", "US");
				resourceBundle = ResourceBundle.getBundle("MessageResources.MessageResources", locale);
				initComponents();
			}
			
		});

		ulogujSe.addActionListener(new ActionListener(){
			/**
			 * Ukoliko je pritisnuto dugme {@code ulogujSe} vrsi autentifikaciju i autorizaciju
			 * @trows SQLException ukoliko je unos netacan
			 */
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if (!korisnickoIme.getText().isEmpty() && lozinka.getPassword().length > 0) {
					
					korisnickoIme.setEnabled(false);
					lozinka.setEnabled(false);
					setEnabled(false);
			
					setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
					pack();
					/**
					 * kada je uneto ispravno korisncko ime i lozinka dodeljuje se nova nit
					 */
					Runnable runnable = new Runnable() {
						@Override
						public void run() {
							try {
								PreparedStatement statement;
								statement = MyApp.getInstance().getConnection()
										.prepareStatement("SELECT * FROM KORISNICI WHERE username = ? AND password = ?");
								statement.setString(1, korisnickoIme.getText());
								statement.setString(2, new String(lozinka.getPassword()));
								ResultSet resultSet = statement.executeQuery();
								
								if (resultSet.next()) {
							
									if(izvor2.getSelectedIndex() == JSON_ODABERI_INDEX){
										MyApp.getInstance().setOdaberi("json");
										parent.setVisible(true);
										setVisible(false);
										
						        	}
						    		
						    		else if(izvor2.getSelectedIndex() == BAZA_ODABERI_INDEX){
						    			MyApp.getInstance().setOdaberi("bazapodataka");
						    			parent.setVisible(true);
						    			setVisible(false);	    			
						        	}								
						    		
								} 
								else {
									// netacan unos
									JOptionPane.showMessageDialog(frame, resourceBundle.getString("upozorenje1"), resourceBundle.getString("upozorenje3"),2);
									korisnickoIme.setText("");
									lozinka.setText("");
									setVisible(true);
									korisnickoIme.setEnabled(true);
									lozinka.setEnabled(true);
									setEnabled(true);
								}
								
							} catch (SQLException e2) {
								//greska
								JOptionPane.showMessageDialog(frame, resourceBundle.getString("upozorenje6"), resourceBundle.getString("upozorenje3"),2);								
								korisnickoIme.setText("");
								lozinka.setText("");
							}

						}
					};
					
					Thread thread = new Thread(runnable);
					thread.start();
				}
				else {
					//nisu popunjena oba polja
					JOptionPane.showMessageDialog(frame, resourceBundle.getString("upozorenje2"), resourceBundle.getString("upozorenje3"),2);		
				}
				
			}
			
		});
		
		/**
		 * odabirom dugmeta {@code izadji} napusta se aplikacija 
		 */
		izadji.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				parent.dispose();
		        System.exit(0);
				
			}
			
		});

		
	
	}
	/**
	 * Inicijalizacija svih komponenti koje se nalaze unutar pocetnog dijaloga
	 * <p>Lokalizacija svih komponenti koje se nalaze unutar pocetnog dijaloga
	 * @return void
	 * @see ResourceBundle
	 */
	public void initComponents(){
		setTitle(resourceBundle.getString("naslovIzaberiteOpcije"));
		labela1.setText(resourceBundle.getString("lab1"));
		labela2.setText(resourceBundle.getString("lab2"));
		labela3.setText(resourceBundle.getString("lab3"));
		labela4.setText(resourceBundle.getString("lab4"));
		srpski.setText(resourceBundle.getString("btnSrpski"));
		engleski.setText(resourceBundle.getString("btnEngleski"));
		ulogujSe.setText(resourceBundle.getString("btnUloguj"));
		izadji.setText(resourceBundle.getString("btnIzadji"));
		resourceBundle.getString("upozorenje1");
		resourceBundle.getString("upozorenje2");
		
	
	}
	/**
	 * Preuzimanje vrednosti komponente korisnickoIme
	 * @see JTextField
	 * @return korisnickoIme 
	 */
	public JTextField getKorisnickoIme() {
		return korisnickoIme;
	}
	/**
	 * Postavljanje nove vrednosti komponente korisnickoIme
	 * @param korisnickoIme
	 * @return void
	 * @see JTextField
	 * 
	 */
	public void setKorisnickoIme(JTextField korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	/**
	 * Preuzimanje vrednosti komponente lozinka
	 * @see JPasswordField
	 * @return lozinka
	 */
	public JPasswordField getLozinka() {
		return lozinka;
	}
	/**
	 * Postavljanje nove vrednosti komponente lozinka
	 * @param lozinka
	 * @return void
	 * @see JPasswordField
	 * 
	 */
	public void setLozinka(JPasswordField lozinka) {
		this.lozinka = lozinka;
	}
	
	
	
}
