
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
 * Nasledjuje {@link JDialog} klasu.
 * Koristi se prijavu na odgovarajuci server baze podataka 
 * Sadrzi polja server, login i sifra  
 * @see ResourceBundle
 * @see Locale
 *
 */
@SuppressWarnings("serial")
public class BazeLogIn extends JDialog{
	
	private ResourceBundle resourceBundle =  ResourceBundle.getBundle("MessageResources.MessageResources", Locale.getDefault());
	private Locale locale;
	
	private JFrame frame = null;
	
	private JLabel labelaServer;
	private JTextField server;
	private JLabel labelaLogin;
	private JTextField login;
	private JLabel labelaSifra;
	private JTextField sifra;
	
	private JButton konektujSe;
	private JButton odustani;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel;
	/**
	 * Konstruktor {@link BazeLogIn} klase
	 * 
	 */
	public BazeLogIn() {
	    this(null, true);
	}
	
	/**
	 * Konstruktor sa parametrima {@link BazeLogIn} klase.
	 *<p> Kreira dijalog sa parametrima potrebnim za konekciju na bazu
	 */
	public BazeLogIn(final JFrame parent, boolean modal){
		
		super(parent, modal);
	   
		
		setSize(new Dimension(350,330));
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		setTitle(MyApp.getInstance().getResourceBundle().getString("naslovIzaberiteOpcije"));
		
		labelaServer = new JLabel(MyApp.getInstance().getResourceBundle().getString("labelaJson"));
		server = new JTextField(20);
		server.setText("147.91.175.155/psw-2017-tim2-1");
		
		labelaLogin = new JLabel(MyApp.getInstance().getResourceBundle().getString("labelaJson"));
		login = new JTextField(20);
		login.setText("psw-2017-tim2-1");
		
		labelaSifra = new JLabel(MyApp.getInstance().getResourceBundle().getString("labelaJson"));
		sifra = new JTextField(20);
		sifra.setText("tim2-115903598");
	        
		
		konektujSe = new JButton(MyApp.getInstance().getResourceBundle().getString("konektujSe"));
		odustani = new JButton(MyApp.getInstance().getResourceBundle().getString("odustani"));
		
		panel1 = new JPanel(new GridLayout(6,1));
		panel1.add(labelaServer);
		panel1.add(server);
		panel1.add(labelaLogin);
		panel1.add(login);
		panel1.add(labelaSifra);
		panel1.add(sifra);
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
				
				String serverName = server.getText();
				String loginName = login.getText();
				String password = sifra.getText();
				
				if((serverName.equals("")) || (loginName.equals("")) || (password.equals(""))){
					server.setText("147.91.175.155/psw-2017-tim2-1");
					login.setText("psw-2017-tim2-1");
					sifra.setText("tim2-115903598");
					JOptionPane.showMessageDialog(frame, resourceBundle.getString("upozorenje2"), resourceBundle.getString("upozorenje3"),2);												
				}
				else {
					MyApp.getInstance().setConnectionString(serverName);
					MyApp.getInstance().setUsername(loginName);
					MyApp.getInstance().setPassword(password);
    				parent.setVisible(true);
        			setVisible(false);
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
		setTitle(resourceBundle.getString("konektujNaServer"));
		labelaServer.setText(resourceBundle.getString("labelaServer"));
		labelaLogin.setText(resourceBundle.getString("labelaLogin"));
		labelaSifra.setText(resourceBundle.getString("labelaSifra"));
		konektujSe.setText(resourceBundle.getString("konektujSe"));
		odustani.setText(resourceBundle.getString("odustani"));
		resourceBundle.getString("upozorenje2");
		resourceBundle.getString("upozorenje3");		
	
	}
	/**
	 * Preuzimanje vrednosti polja servera baze podataka
	 * @see JTextField
	 * @return server
	 */
	public JTextField getServer() {
		return server;
	}
	/**
	 * Postavljanje nove vrednosti polja servera baze podataka
	 * @see JTextField
	 * @param server
	 * @return void
	 */
	public void setServer(JTextField server) {
		this.server = server;
	}
	/**
	 * Preuzimanje vrednosti polja 
	 * @see JTextField
	 * @return login
	 */
	public JTextField getLogin() {
		return login;
	}
	/**
	 * Postavljanje nove vrednosti polja 
	 * @see JTextField
	 * @param login
	 * @return void
	 */
	public void setLogin(JTextField login) {
		this.login = login;
	}

	/**
	 * Preuzimanje vrednosti polja lozinka
	 * @see JTextField
	 * @return sifra
	 */
	public JTextField getSifra() {
		return sifra;
	}
	/**
	 * Postavljanje nove vrednosti polja lozinka
	 * @see JTextField
	 * @param sifra
	 * @return void
	 */
	public void setSifra(JTextField sifra) {
		this.sifra = sifra;
	}
	
	
	

}
