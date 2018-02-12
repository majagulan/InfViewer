package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
/**
 * 
* Nasledjuje {@link JMenuBar} klasu.
* Korisniku olaksava rad sa aplikacijom. 
* @see ResourceBundle
* @see Locale
*/
@SuppressWarnings("serial")
public class Meni extends JMenuBar{
	private JMenu operacije;
	private JMenu pomoc;
	private JMenu jezik;
	private JMenuItem otvori;
	private JMenuItem zatvori;
	private JMenuItem sacuvaj;
	private JMenuItem izadji;
	private JMenuItem logout;
	private JMenuItem srpski;
	private JMenuItem engleski;
	
	
	/**
	 * Konstruktor bez parametara
	 */
public Meni(){
		
	operacije = new JMenu(MyApp.getInstance().getResourceBundle().getString("meniOperacije"));
	pomoc = new JMenu(MyApp.getInstance().getResourceBundle().getString("meniPomoc"));
	jezik = new JMenu(MyApp.getInstance().getResourceBundle().getString("meniJezik"));
	
	otvori = new JMenuItem(MyApp.getInstance().getResourceBundle().getString("meniOtvori"));
	zatvori = new JMenuItem(MyApp.getInstance().getResourceBundle().getString("meniZatvori"));
	sacuvaj = new JMenuItem(MyApp.getInstance().getResourceBundle().getString("meniSacuvaj"));
	logout = new JMenuItem(MyApp.getInstance().getResourceBundle().getString("meniLogOut"));
	izadji = new JMenuItem(MyApp.getInstance().getResourceBundle().getString("meniIzadji"));
	srpski = new JMenuItem(MyApp.getInstance().getResourceBundle().getString("meniSrpski"));
	engleski = new JMenuItem(MyApp.getInstance().getResourceBundle().getString("meniEngleski"));
	
		
		operacije.add(otvori);
		operacije.addSeparator();
		operacije.add(zatvori);
		operacije.addSeparator();
		operacije.add(sacuvaj);
		operacije.addSeparator();
		operacije.add(logout);
		operacije.addSeparator();
		operacije.add(izadji);
		
		jezik.add(srpski);
		jezik.addSeparator();
		jezik.add(engleski);
		
		add(operacije);
		add(pomoc);
		add(jezik);
		
		srpski.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Locale.setDefault(new Locale("sr", "RS"));
				MyApp.getInstance().changeLanguage();
				
			}
			
		});
		
		engleski.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Locale.setDefault(new Locale("en", "US"));
				MyApp.getInstance().changeLanguage();
				
			}
			
		});
		
		logout.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				MyApp.getInstance().dispose();
				PocetniDijalog pocetniDijalog = new PocetniDijalog();
				pocetniDijalog.setVisible(true);
				
			}
			
		});
		
		izadji.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
		        System.exit(0);
				
			}
			
		});

}
/**
 * Kreiranje svih komponenti menija i njihova lokalizacija 
 */

	public void initComponents(){
		
		operacije.setText(MyApp.getInstance().getResourceBundle().getString("meniOperacije"));
		pomoc.setText(MyApp.getInstance().getResourceBundle().getString("meniPomoc"));
		jezik.setText(MyApp.getInstance().getResourceBundle().getString("meniJezik"));
		otvori.setText(MyApp.getInstance().getResourceBundle().getString("meniOtvori"));
		zatvori.setText(MyApp.getInstance().getResourceBundle().getString("meniZatvori"));
		sacuvaj.setText(MyApp.getInstance().getResourceBundle().getString("meniSacuvaj"));
		izadji.setText(MyApp.getInstance().getResourceBundle().getString("meniIzadji"));
		srpski.setText(MyApp.getInstance().getResourceBundle().getString("meniSrpski"));
		engleski.setText(MyApp.getInstance().getResourceBundle().getString("meniEngleski"));
	
}

}
	


