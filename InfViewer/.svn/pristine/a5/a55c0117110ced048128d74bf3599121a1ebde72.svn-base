package crudActions;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import gui.DateLabelFormatter;
import gui.MyApp;
import gui.Tab;
import gui.TabPanel;
import gui.Time;
import model.Column;
import model.Korisnici;
import model.Row;
import model.Table;


/**
 * <code>DataBaseCRUD</code> implementira {@link CRUDActions} interfejs.
 * Koristi se za implementaciju metoda za dodavanje, citanje,
 * brisanje i osvezavanje torki u tabele.
 * @see PreparedStatement
 * @see Connection
 *
 */

public class DataBaseCRUD implements CRUDActions{
	
	private int selectedButton;// da bismo znali koji Panel se gleda roditeljski, ili deciji
	HashMap<String,Object> guiElements = new HashMap<>();
	private ArrayList<String> pomocnaLista = new ArrayList<>();//ovde dve liste sluze da bismo mogli da znamo iz kog polja uzimamo unetu vrednost
	 ArrayList<String> listaPrimarnihKljuceva = new ArrayList<>();
	
	private  Connection connection;
	private PreparedStatement prep_st;//
	
	private ArrayList<String> slozenKljucProvera = new ArrayList<>();//ukoliko je slozeni kljuc u pitanju drugacije se vrse
	//brisanje i dodavanje ova lista sadrzi sva obelezja primarnog kljuca za konkretnu tabelu
	private String tip= new String();

	/**
	 * Konstruktor sa parametrima {@link DataBaseCRUD} klase
	 * @param selectedButton
	 */
	
	public DataBaseCRUD(int selectedButton){
		this.selectedButton = selectedButton;
		connection = MyApp.getConnection();
		slozenKljucProvera = new ArrayList<>();
		
	}

	/**
	 *  Dodaje nove torke u tabelu, gde se preko selektovane table uzimaju sve informacije o toj tabeli i 
	 *  u kojoj su sadrzane sve informacije kako ne bismo narusili neko od ogranicenja, (na primer ogranicenje primarnog 
	 *  kljuca ne sme biti null i ne sme da se unese dvaputa ista vrednost za primarni kljuc). Takodje klikom na dugme 
	 *  add otvara nam se odgovarajuci gui u zavisnosti od toga koje kolone poseduje odredjena tabela.
	 *  
	 *  @see Tab
	 *  @see JDatePickerImpl
	 *  @throws java.lang.NullPointerException ako nije oznacena tabela u koju zelite da dodate red
	 *  @return
	 */
	
	@Override
	public void add() {
		
		Tab tab = new Tab();
		
		if(selectedButton==0){
			try{
				 tab= (Tab)MyApp.getInstance().getTabPanelGornji().getSelectedComponent();
				
				}
				catch(java.lang.NullPointerException exception){
				JOptionPane.showMessageDialog(new JFrame(), MyApp.getInstance().getResourceBundle().getString("upozorenje9"));
			}
		
		}else if(selectedButton==1){
			try{
				 tab= (Tab)MyApp.getInstance().getTabPanelDonji().getSelectedComponent();
				 
				}
				catch(java.lang.NullPointerException exception){
				JOptionPane.showMessageDialog(new JFrame(), MyApp.getInstance().getResourceBundle().getString("upozorenje9"));
			}
		}
		
			try{
			Table t = MyApp.getInstance().getTableById(tab.getTabId());
			Tab tabb = tab;
	
			JDialog addDijalog = new JDialog();
			
			JPanel panel = new JPanel();
			JScrollPane scrollPane = new JScrollPane(panel);
			
			scrollPane.setHorizontalScrollBarPolicy(
					   JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
					scrollPane.setVerticalScrollBarPolicy(
					   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
			GridLayout layout = new GridLayout(t.getListOfColumns().size()+1,3);
			panel.setLayout(layout);
			addDijalog.setVisible(true);
			addDijalog.setTitle(MyApp.getInstance().getResourceBundle().getString("upozorenje22"));
			addDijalog.setSize(500,500);
			addDijalog.setLocationRelativeTo(null);
			addDijalog.setResizable(false);
			addDijalog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			MyApp.getInstance().getAddDugmeGornje().setEnabled(false);
			MyApp.getInstance().getAddDugmeDonje().setEnabled(false);
			
				for(int i=0;i<t.getListOfColumns().size();i++){
					String tip = t.getListOfColumns().get(i).getDataType();
					
					JLabel labela; 
					if(t.getListOfColumns().get(i).isPrimaryKey()){
						labela = new JLabel(t.getListOfColumns().get(i).getIdLabelColmun()+"*");
					}else{
						labela = new JLabel(t.getListOfColumns().get(i).getIdLabelColmun());
					}
					panel.add(labela);
					if(tip.equals("datetime")){
						SqlDateModel model = new SqlDateModel();
						Properties p = new Properties();
						p.put("text.today", "Today");
						p.put("text.month", "Month");
						p.put("text.year", "Year");
						JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
						JDatePickerImpl datePicker = new JDatePickerImpl(datePanel,new DateLabelFormatter());
						
						panel.add(datePicker);
						model.setSelected(true);
						
						
						
						JPanel vreme = new JPanel();
						
						 JTextField sati = new JTextField();
					
						 JTextField minuti = new JTextField();
					
						 JTextField sekunde  = new JTextField();
						 Time time = new Time(datePicker,sati,minuti,sekunde);
						 guiElements.put(t.getListOfColumns().get(i).getIdLabelColmun(), time);
						pomocnaLista.add(t.getListOfColumns().get(i).getIdLabelColmun());
						//treba dodati da se ne sme uneti vise od 60 za svako od polja
						vreme.setLayout(new BoxLayout(vreme,BoxLayout.X_AXIS));
						vreme.add(sati);
						vreme.add(new JLabel(":"));
						vreme.add(minuti);
						vreme.add(new JLabel(":"));
						vreme.add(sekunde);
						
						
						
						panel.add(vreme);
						

					}else{
					
					
					JTextField polje = new JTextField();
					polje.setSize(new Dimension(10,10));
					panel.add(polje);
					guiElements.put(t.getListOfColumns().get(i).getIdLabelColmun(), polje);
					pomocnaLista.add(t.getListOfColumns().get(i).getIdLabelColmun());
					
						if(daLiJeStraniKljuc(t,t.getListOfColumns().get(i),panel,polje)){
							polje.setEditable(true);
						}
						
					}
					
					
					
					
					
					}
				

			
			JButton addDugme = new JButton("Dodaj");
			JButton cancelDugme = new JButton("Zatvori");
			panel.add(addDugme);
			panel.add(cancelDugme);
			
			cancelDugme.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) { 
					  MyApp.getInstance().getAddDugmeGornje().setEnabled(true);
						MyApp.getInstance().getAddDugmeDonje().setEnabled(true);
					  	addDijalog.dispose();
					  
				  } 
				} );
			
			
			addDugme.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) { 
					  MyApp.getInstance().getAddDugmeGornje().setEnabled(true);
						MyApp.getInstance().getAddDugmeDonje().setEnabled(true);
					  addRow(t,addDijalog,selectedButton,tabb);
					  
				  } 
				} );
			
			addDijalog.add(panel);
	
				
			}catch(java.lang.NullPointerException exception){
				JOptionPane.showMessageDialog(new JFrame(), MyApp.getInstance().getResourceBundle().getString("upozorenje9"));
			}
	}
	
	
	/**
	 *  Formiranje delete upita nad bazom u zavisnosti od oznacenih kolona. Neophodno je da ukoliko tabela ima slozeni kljuc da se oznace sve
	 *  vrednosti koje pripadaju slozenom kljucu. Brisanje se vrsi na osnovu primarnog kljuca, mora biti ispostovano ogranicenje da ne sme
	 *  da se obrisu podaci koji imaju vezane podatke u drugim tabelama.  
	 *  @see Tab
	 *  @throws java.lang.NullPointerException ako nije oznacen red koji se brise
	 *  @throws java.lang.ArrayIndexOutOfBoundsException ako se ne oznace sve kolone koje pripadaju kljucu
	 *  @return
	 */

	@Override
	public void delete() {
		
		if(selectedButton ==0){
			try{
				 Tab tab= (Tab)MyApp.getInstance().getTabPanelGornji().getSelectedComponent();
				 JTable tableWithRows = tab.getTableRows();
				 
				
				
				 if(tab==null){
					 JOptionPane.showMessageDialog(new JFrame(), MyApp.getInstance().getResourceBundle().getString("upozorenje10"));
				 }
				}
				catch(java.lang.NullPointerException exception){
				JOptionPane.showMessageDialog(new JFrame(), MyApp.getInstance().getResourceBundle().getString("upozorenje10"));
				}
			}else{
				try{
					 Tab tab= (Tab)MyApp.getInstance().getTabPanelDonji().getSelectedComponent();
					 JTable tableWithRows = tab.getTableRows();
					
					 if(tab==null){
						 JOptionPane.showMessageDialog(new JFrame(), MyApp.getInstance().getResourceBundle().getString("upozorenje10"));
					 }
					}
					catch(java.lang.NullPointerException exception){
					
					JOptionPane.showMessageDialog(new JFrame(),MyApp.getInstance().getResourceBundle().getString("upozorenje10"));
					}
			}
		
			
			
			try{
				Tab tab;
				String deleteCommand;
				
				if(selectedButton == 0){
					tab= (Tab)MyApp.getInstance().getTabPanelGornji().getSelectedComponent();
				}else{
					tab= (Tab)MyApp.getInstance().getTabPanelDonji().getSelectedComponent();
				}
				
				 ArrayList<String> slozenKljucTabele = new ArrayList<>();
				 Table tt = MyApp.getInstance().getTableById(tab.getTabId());
				 slozenKljucTabele = daLiTabelaImaSlozenKljuc(tt);
				
				JTable tableWithRows = tab.getTableRows();
				tableWithRows.setColumnSelectionAllowed(true);
				int indeks = tableWithRows.getSelectedRow();//ovo je indeks oznacenog reda
				
				String primarniKljuc= "";//ovo ce mozda biti niz stringova za sad ovako
				String value="";
				int[] indeksColumn = tableWithRows.getSelectedColumns();
				
				Table t = MyApp.getInstance().getTableById(tab.getTabId());
				int counter = 0;
				
				for(int i=0;i<t.getListOfColumns().size();i++){
					if(t.getListOfColumns().get(i).isPrimaryKey()){
						tip = t.getListOfColumns().get(i).getDataType();
						counter++;
					}
				}//broji koliko imamo primarnih kljuceva da bismo znali koliko and da dodamo
				
				for(int i=0;i<t.getListOfColumns().size();i++){
					if(t.getListOfColumns().get(i).isPrimaryKey()){
						primarniKljuc+=t.getListOfColumns().get(i).getIdLabelColmun()+"=";
						counter--;
						//int indeksColumn = tableWithRows.getSelectedColumn();
						try{
						value = tableWithRows.getModel().getValueAt(indeks,indeksColumn[i]).toString();
						}catch(java.lang.ArrayIndexOutOfBoundsException ee){
							JOptionPane.showMessageDialog(new JFrame(), MyApp.getInstance().getResourceBundle().getString("upozorenje11"));
							return;
						}
						
							
						if(counter !=0){
						primarniKljuc+=value;
						primarniKljuc+="AND ";
						}else{
							primarniKljuc+=value;
							primarniKljuc+=";";
							break;
						}
						
						
						
						
					}
				}
				deleteCommand = "DELETE FROM "+tab.getTabId()+" WHERE "+primarniKljuc;
				System.out.println(deleteCommand);
				
				//trazenje primarnog kljuca da bi se moglo izvrsiti brisanje
				
				if(indeks!=-1){
				
				String s = primarniKljuc;
				System.out.println("PRIMARNI KLJUC"+s);
				JDialog addDijalog = new JDialog();
				
				JPanel panel = new JPanel();
				JScrollPane scrollPane = new JScrollPane(panel);
				
				scrollPane.setHorizontalScrollBarPolicy(
						   JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
						scrollPane.setVerticalScrollBarPolicy(
						   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
				
				JLabel labela = new JLabel(MyApp.getInstance().getResourceBundle().getString("upozorenje12"));
				BorderLayout layout = new BorderLayout();
				addDijalog.setLayout(layout);
				addDijalog.setVisible(true);
				addDijalog.setTitle("Obrisi vrstu");
				addDijalog.setSize(300,200);
				addDijalog.setLocationRelativeTo(null);
				addDijalog.setResizable(false);
				addDijalog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				addDijalog.add(labela,BorderLayout.CENTER);
				JButton deleteDugme = new JButton("OK");
				JButton dugmeCancel = new JButton("Cancel");
				deleteDugme.setSize(new Dimension(50,50));
				dugmeCancel.setSize(new Dimension(50,50));
				JPanel panelSouth = new JPanel();
				panelSouth.add(deleteDugme);
				panelSouth.add(dugmeCancel);
				addDijalog.add(panelSouth,BorderLayout.SOUTH);
				
				dugmeCancel.addActionListener(new ActionListener() { 
					  public void actionPerformed(ActionEvent e) { 
						  	addDijalog.dispose();
					  } 
					} );
				
				
				deleteDugme.addActionListener(new ActionListener() { 
					  public void actionPerformed(ActionEvent e) { //brise vrstu po kljucu
						 
						  isprazniRedIzBaze(deleteCommand,tab,indeks,s,tableWithRows,selectedButton,tip);
						  addDijalog.dispose();
						  
					  } 
					} );
				
				
				}else{//ovo je kad nije oznacio nijedan red
					
					JOptionPane.showMessageDialog(new JFrame(),MyApp.getInstance().getResourceBundle().getString("upozorenje10"));
					
				}
			
			
			}catch(NullPointerException ee){
				
				JOptionPane.showMessageDialog(new JFrame(), MyApp.getInstance().getResourceBundle().getString("upozorenje10"));
			}
		}	
		

	/**
	 * Omogucava promenu vrednosti polja roditelja da se promene sva deca. Update nad detetom nije moguc. 
	 * Takodje je podrzano ogranicenje ukoliko je u pitanju primarni kljuc koji se menja da se poklapa sa nekim primarnim kljucem.
	 * U tom slucaju se se obustavlja unos, a promena je podrzana za sve tipove sem datuma.
	 * @see JDatePickerImpl
	 * @see DateLabelFormatter
	 * @throws java.lang.NullPointerException ako nije oznacena tabela gde se radi update
	 * @throws java.lang.NumberFormatException 
	 * @return 
	 */
	
	@Override
	public void update() {
		
		Tab tab = new Tab();
		JDialog updateDijalog = new JDialog();
		
		if(selectedButton==0){
			try{
				 tab= (Tab)MyApp.getInstance().getTabPanelGornji().getSelectedComponent();
				
				}
				catch(java.lang.NullPointerException exception){
				JOptionPane.showMessageDialog(new JFrame(),MyApp.getInstance().getResourceBundle().getString("upozorenje13"));
			}
		
		}else if(selectedButton==1){
			try{
				 tab= (Tab)MyApp.getInstance().getTabPanelDonji().getSelectedComponent();
				 
				}
				catch(java.lang.NullPointerException exception){
				JOptionPane.showMessageDialog(new JFrame(), MyApp.getInstance().getResourceBundle().getString("upozorenje13"));
			}
		}
		
			try{
			Table t = MyApp.getInstance().getTableById(tab.getTabId());
			Tab tabb = tab;
			JTable tableWithRows = tab.getTableRows();
			tableWithRows.setRowSelectionAllowed(true);
			int indeks = tableWithRows.getSelectedRow();//indeks oznacenog reda da bismo dobili sadrzaj tih polja
			if(indeks == -1){
				JOptionPane.showMessageDialog(new JFrame(),MyApp.getInstance().getResourceBundle().getString("upozorenje14"));
				  MyApp.getInstance().getUpdateDugmeGornje().setEnabled(true);
					 MyApp.getInstance().getUpdateDugmeDonje().setEnabled(true);
			}else{
				
			JPanel panel = new JPanel();
			JScrollPane scrollPane = new JScrollPane(panel);
			
			scrollPane.setHorizontalScrollBarPolicy(
					   JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
					scrollPane.setVerticalScrollBarPolicy(
					   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
			GridLayout layout = new GridLayout(t.getListOfColumns().size()+1,3);
			panel.setLayout(layout);
			
			updateDijalog.setTitle("Dodaj vrstu");
			updateDijalog.setSize(500,500);
			updateDijalog.setResizable(false);
			
			updateDijalog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			MyApp.getInstance().getUpdateDugmeGornje().setEnabled(false);
			MyApp.getInstance().getUpdateDugmeDonje().setEnabled(false);
			
				for(int i=0;i<t.getListOfColumns().size();i++){
					String tip = t.getListOfColumns().get(i).getDataType();
					
					JLabel labela; 
					if(t.getListOfColumns().get(i).isPrimaryKey()){
						labela = new JLabel(t.getListOfColumns().get(i).getIdLabelColmun()+"*");
					}else{
						labela = new JLabel(t.getListOfColumns().get(i).getIdLabelColmun());
					}
					panel.add(labela);
					if(tip.equals("datetime")){
						SqlDateModel model = new SqlDateModel();
						Properties p = new Properties();
						p.put("text.today", "Today");
						p.put("text.month", "Month");
						p.put("text.year", "Year");
						JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
						JDatePickerImpl datePicker = new JDatePickerImpl(datePanel,new DateLabelFormatter());
						
						panel.add(datePicker);
						model.setSelected(true);
						
						
						
						JPanel vreme = new JPanel();
						
						 JTextField sati = new JTextField();
					
						 JTextField minuti = new JTextField();
					
						 JTextField sekunde  = new JTextField();
						 Time time = new Time(datePicker,sati,minuti,sekunde);
						 guiElements.put(t.getListOfColumns().get(i).getIdLabelColmun(), time);
						pomocnaLista.add(t.getListOfColumns().get(i).getIdLabelColmun());
						//treba dodati da se ne sme uneti vise od 60 za svako od polja
						vreme.setLayout(new BoxLayout(vreme,BoxLayout.X_AXIS));
						vreme.add(sati);
						vreme.add(new JLabel(":"));
						vreme.add(minuti);
						vreme.add(new JLabel(":"));
						vreme.add(sekunde);
						
						
						panel.add(vreme);
						

					}else{
					
					
					JTextField polje = new JTextField();
					polje.setSize(new Dimension(10,10));
					panel.add(polje);
					listaPrimarnihKljuceva = primarniKljuceviTabele(t,MyApp.getInstance().getStareVrednosti());
					napuniPolje(polje,i,tableWithRows,indeks,MyApp.getInstance().getStareVrednosti());//ova metoda puni elemente gui-ja vrednostima iz selektovanog reda
					
					
					guiElements.put(t.getListOfColumns().get(i).getIdLabelColmun(), polje);
					pomocnaLista.add(t.getListOfColumns().get(i).getIdLabelColmun());
				
						if(daLiJeKolonaPrimarniKljuc(t,t.getListOfColumns().get(i).getIdLabelColmun())){
							polje.setEditable(false);
						}
						
						if(daLiJeStraniKljucBezZoom(t,t.getListOfColumns().get(i),panel,polje)){
							//ako je strani kljuc u detetu onda ne sme da se menja
							polje.setEditable(false);
						}
						
					}

					}
				

			
			JButton updateDugme = new JButton("Izmeni");
			JButton cancelDugme = new JButton("Zatvori");
			panel.add(updateDugme);
			panel.add(cancelDugme);
			updateDijalog.add(panel);
			
			updateDijalog.setVisible(true);
			
			
			
			cancelDugme.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) { 
					  MyApp.getInstance().getUpdateDugmeGornje().setEnabled(true);
						MyApp.getInstance().getUpdateDugmeDonje().setEnabled(true);
					  	updateDijalog.dispose();
					  
				  } 
				} );
			
			
			updateDugme.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) { 
					  MyApp.getInstance().getUpdateDugmeGornje().setEnabled(true);
					 MyApp.getInstance().getUpdateDugmeDonje().setEnabled(true);
					
					  updateRow(listaPrimarnihKljuceva,t,updateDijalog,selectedButton,tabb,MyApp.getInstance().getStareVrednosti(),selectedButton);
					  
				  } 
				} );
			
			
			}
				
			}catch(java.lang.NullPointerException exception){
				updateDijalog.dispose();
				JOptionPane.showMessageDialog(new JFrame(), MyApp.getInstance().getResourceBundle().getString("upozorenje14"));
				
			}
		
	}
	
	/**
	 * Vraca listu primarnih kljuceva zeljene tabele
	 * @param t
	 * @param stareVrednosti
	 * @return
	 * @see HashMap
	 */
	
	public ArrayList<String> primarniKljuceviTabele(Table t,HashMap<String,Integer> stareVrednosti){
		ArrayList ret = new ArrayList<String>();
		for(int i=0;i<t.getListOfColumns().size();i++){
			if(t.getListOfColumns().get(i).isPrimaryKey()){
				ret.add(t.getListOfColumns().get(i).getIdLabelColmun());
				stareVrednosti.put(t.getListOfColumns().get(i).getIdLabelColmun(),Integer.parseInt("0"));
				System.out.println(stareVrednosti);
			}
		}
		
		return ret;
	}
	
	/**
	 * Uzima vrednost iz tabele i postavlja u graficki interfejs.
	 * @param polje
	 * @param pozicijaPolja
	 * @param tabela
	 * @param indeks
	 * @param stareVrednosti
	 */
	
	public void napuniPolje(JTextField polje,int pozicijaPolja,JTable tabela,int indeks,HashMap<String,Integer> stareVrednosti){
		int kolona = pozicijaPolja;
		int red = indeks;
		polje.setText((String) tabela.getModel().getValueAt(red, kolona));
		try{
		for(HashMap.Entry<String, Integer> entry : stareVrednosti.entrySet()) {
			
			entry.setValue(Integer.parseInt(polje.getText().trim()));
			System.out.println("MAPA IZGLEDA OVAKO:"+entry.setValue(Integer.parseInt(polje.getText().trim())));
		}}catch(java.lang.NumberFormatException ee){
			
		}

	}
	
	/**
	 * Vraca boolean tip u zavisnosti od toga da li je zeljena kolona upravo primarni kljuc ili ne.
	 * @param t
	 * @param kolona
	 * @return
	 */
	
	public boolean daLiJeKolonaPrimarniKljuc(Table t,String kolona){
		boolean ret = false;
		for(int i=0;i<t.getListOfColumns().size();i++){
			if(kolona.equals(t.getListOfColumns().get(i).getIdLabelColmun())){
				if(t.getListOfColumns().get(i).isPrimaryKey()){
					ret = true;
				}
			}
		}
		
		return ret;
	}
	
	/**
	 * Kreira update upit na bazom gde imaju dve provere da li je u pitanju prost ili slozen kljuc.
	 * @param listaPrimarnihKljuceva
	 * @param t
	 * @param updateDijalog
	 * @param selectetButton
	 * @param tab
	 * @param stareVrednosti
	 * @param selected
	 * @see HashMap
	 * @throws SQLException
	 */
	
	public void updateRow(ArrayList<String> listaPrimarnihKljuceva,Table t,JDialog updateDijalog,int selectetButton,Tab tab,
			HashMap<String,Integer> stareVrednosti,int selected){
		
	
		
		if(selectedButton == 0){
			tab= (Tab)MyApp.getInstance().getTabPanelGornji().getSelectedComponent();
		}else{
			tab= (Tab)MyApp.getInstance().getTabPanelDonji().getSelectedComponent();
		}
		
		String updateCommand="UPDATE "+t.getIdLabelTable()+" SET ";
		
		String kolone = new String();
		
		if(listaPrimarnihKljuceva.size()==1){
			int brojac = 0;
			String vrednostPrimarnog = new String();
			for(HashMap.Entry<String, Object> entry : guiElements.entrySet()) {
				
					kolone+=entry.getKey()+" = ";
					String tip = nadjiTipKolone(t,entry.getKey());
					if(tip.equals("varchar") || tip.equals("char")){
						kolone+="'";
					}
					kolone+=((JTextField)entry.getValue()).getText()+"'";
					if(daLiJeKolonaPrimarniKljuc(t,entry.getKey())){
						vrednostPrimarnog = ((JTextField)entry.getValue()).getText();
					}
					
					
						if(brojac!=guiElements.size()-1){
						kolone+=",";
						}
						
						brojac++;
			
			}
			
			kolone+=" WHERE "+listaPrimarnihKljuceva.get(0)+" = ";
			kolone+=vrednostPrimarnog;
		
			
		}else{
			int brojac = 0;
			int brojac2 = 0;
			String vrednostPrimarnog = new String();
			for(HashMap.Entry<String, Object> entry : guiElements.entrySet()) {
				
					kolone+=entry.getKey()+" = ";
					String tip = nadjiTipKolone(t,entry.getKey());
					if(tip.equals("varchar") || tip.equals("char")){
						kolone+="'";
					}
					kolone+=((JTextField)entry.getValue()).getText();
					if(tip.equals("varchar") || tip.equals("char")){
						kolone+="'";
					}
					
					
					if(daLiJeKolonaPrimarniKljuc(t,entry.getKey())){
						vrednostPrimarnog = ((JTextField)entry.getValue()).getText();
					}
					
					
						if(brojac!=guiElements.size()-1){
						kolone+=",";
						}
						
						brojac++;
			
			}
			
			kolone+=" WHERE ";
			for(int i=0;i<listaPrimarnihKljuceva.size();i++){
			 kolone+=listaPrimarnihKljuceva.get(i)+" = ";
			 kolone+=nadjiVrednostPrimarnog(listaPrimarnihKljuceva.get(i));
			 
			 if(brojac2!=listaPrimarnihKljuceva.size()-1){
				 kolone+=" AND ";
			 }
			 
			 brojac2++;
			}
			
			
			
			
		}
		
		updateCommand+=kolone;
		System.out.println(updateCommand);
		/*UPDATE table_name
		SET column1 = value1, column2 = value2, ...
		WHERE condition; ovog oblika je naredba*/
		
		try{
			
			
			prep_st = connection.prepareStatement(updateCommand);
			prep_st.executeUpdate();
			
			if(selected==0){
				ArrayList<String> koloneee = new ArrayList<String>();
				for(int j=0;j<MyApp.getInstance().getTabPanelGornji().getTabCount();j++){
					if(((Tab)MyApp.getInstance().getTabPanelGornji().getComponentAt(j)).getTabId().equals(t.getIdLabelTable())){
						
						for(int i=0;i<t.getListOfColumns().size();i++){
							koloneee.add(((Tab)MyApp.getInstance().getTabPanelGornji().getComponentAt(j)).getTableRows().getColumnName(i));
							
						}
						((Tab)MyApp.getInstance().getTabPanelGornji().getComponentAt(j)).removeAll();
						
						JTable table = ((Tab)MyApp.getInstance().getTabPanelGornji().getComponentAt(j)).iscrtajTabeleProsledjeno(t, ((Tab)MyApp.getInstance().getTabPanelGornji().getComponentAt(j)).getTableRows());						
						((Tab)MyApp.getInstance().getTabPanelGornji().getComponentAt(j)).add(table);
						((Tab)MyApp.getInstance().getTabPanelGornji().getComponentAt(j)).getTableRows().repaint();
						((Tab)MyApp.getInstance().getTabPanelGornji().getComponentAt(j)).getTableRows().revalidate();
						((Tab)MyApp.getInstance().getTabPanelGornji().getComponentAt(j)).repaint();
						((Tab)MyApp.getInstance().getTabPanelGornji().getComponentAt(j)).revalidate();
						break;
						}
					}
				koloneee = new ArrayList<String>();
			}else{
				for(int j=0;j<MyApp.getInstance().getTabPanelDonji().getTabCount();j++){
					if(((Tab)MyApp.getInstance().getTabPanelDonji().getComponentAt(j)).getTabId().equals(t.getIdLabelTable())){
						
					
						((Tab)MyApp.getInstance().getTabPanelDonji().getComponentAt(j)).removeAll();
						
						JTable table = ((Tab)MyApp.getInstance().getTabPanelDonji().getComponentAt(j)).iscrtajTabeleProsledjeno(t, ((Tab)MyApp.getInstance().getTabPanelGornji().getComponentAt(j)).getTableRows());						
						((Tab)MyApp.getInstance().getTabPanelDonji().getComponentAt(j)).add(table);
						((Tab)MyApp.getInstance().getTabPanelDonji().getComponentAt(j)).getTableRows().repaint();
						((Tab)MyApp.getInstance().getTabPanelDonji().getComponentAt(j)).getTableRows().revalidate();
						((Tab)MyApp.getInstance().getTabPanelDonji().getComponentAt(j)).repaint();
						((Tab)MyApp.getInstance().getTabPanelDonji().getComponentAt(j)).revalidate();
						break;
						}
					}
				
			}
			


			
		 }catch (SQLException e) {
			return;
		}

	}
	
	
	/**
	 * Pronalazi ako je kljuc slozen vrednosti koje odgovaraju tom kljucu
	 * @param kljuca
	 * @return
	 * @see HashMap
	 */
	
	public String nadjiVrednostPrimarnog(String kljuca){
		String ret = new String();
		int brojac = 0;
		for(HashMap.Entry<String, Object> entry : guiElements.entrySet()){
			if(kljuca.equals(entry.getKey())){
				System.out.println("Usao ovde ZA GUI");
				ret = ((JTextField)entry.getValue()).getText();
				System.out.println(ret);
				return ret;
			}
		}
		brojac++;
		
		return ret;
	}
	
	/**
	 * Pronalazi tip kolone pretrazujuci listu kolona
	 * @param t
	 * @param s
	 * @return
	 */
	
	public String nadjiTipKolone(Table t,String s){
		String ret = new String();
		for(int i=0;i<t.getListOfColumns().size();i++){
			if(t.getListOfColumns().get(i).getIdLabelColmun().equals(s)){
				ret = t.getListOfColumns().get(i).getDataType();
			}
		}
		
		return ret;
	}
	
	/**
	 * Poziva se ova metoda unutar add metoda i ona vrsi kreiranje INSERT upita nad bazom uzimanjem konkretnih vrednosti koje je korisnik uneo.
	 * Pri unosu se mora ispostovati tip, ogranicenje primarnog, ogranicenje stranog kljuca. Ukoliko postoje strani kljucevi korisniku se nudi zoom dugme
	 * gde se otvaraju svi podaci iz roditeljske tabele. Pored dodavanja reda u bazu, mora da se uradi i osvezavanje View-va odnosno prikaz na tabeli u odredjenom tabu.
	 * Takodje u ovoj metodi se nalaze provera za sva ogranicenja.
	 * @param t
	 * @param dijalog
	 * @param selected
	 * @param tab
	 * @see java.sql.Timestamp
	 * @see java.sql.Date
	 * @see java.sql.Time
	 * @throws NumberFormatException ako se ne ispostuje tip i preciznost
	 * @throws java.lang.ClassCastException
	 * @throws SQLException
	 */
	
	public void addRow(Table t,JDialog dijalog,int selected,Tab tab){
		Row row = new Row();
		//u ovoj metodi kreiraj red koji se salje i posalji ga metodi za dodavanje redova
		String insertCommand = "INSERT INTO "+t.getIdLabelTable()+" (";
		for(int i=0;i<t.getListOfColumns().size();i++){
			insertCommand +=t.getListOfColumns().get(i).getIdLabelColmun();
			row.addUListuNaziva(t.getListOfColumns().get(i).getIdLabelColmun());
			if(i!=t.getListOfColumns().size()-1){
				insertCommand+=", ";
			}
		}
		
		insertCommand+=") VALUES (";
		for(int i=0;i<pomocnaLista.size();i++){
			try{
			 try{
				 System.out.println(t.getListOfColumns().get(i).getDataType());
				 if(t.getListOfColumns().get(i).getDataType().equals("numeric") ||
						 t.getListOfColumns().get(i).getDataType().equals("int")	||
						 t.getListOfColumns().get(i).getDataType().equals("decimal")){
				 int broj = Integer.parseInt(((JTextField)guiElements.get(pomocnaLista.get(i))).getText());
				 }}catch(NumberFormatException ee){
					 JOptionPane.showMessageDialog(new JFrame(), MyApp.getInstance().getResourceBundle().getString("upozorenje15"));
						return;
				 }
			 
			 row.addUListuSadrzaja(((JTextField)guiElements.get(pomocnaLista.get(i))).getText());
			}catch(java.lang.ClassCastException ee){
			
				Time time = (Time)guiElements.get(pomocnaLista.get(i));
				JDatePickerImpl datePicker = time.getDatePicker();
				try{
				int sati = Integer.parseInt(time.getSati().getText());
				int minuti =Integer.parseInt(time.getMinuti().getText());
				int sekunde =Integer.parseInt(time.getSekunde().getText());
				java.sql.Time sqlTime = new java.sql.Time(sati,minuti,sekunde);
				java.sql.Date selectedDate = (java.sql.Date) datePicker.getModel().getValue();
			    java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(selectedDate.toString()+" "+sqlTime.toString());
			    
			    if(sati<=0 || sati>24 || minuti<=0 || minuti>=60 || sekunde<=0 || sekunde>=60){
			    	JOptionPane.showMessageDialog(new JFrame(), MyApp.getInstance().getResourceBundle().getString("upozorenje16"));
			    	return;
			    }
			     
			     row.addUListuSadrzaja(timestamp);
				}
				catch (NumberFormatException e){
					
					JOptionPane.showMessageDialog(new JFrame(),MyApp.getInstance().getResourceBundle().getString("upozorenje15"));
					return;
				} 
				
			}
			insertCommand +="?";
			if(i!=pomocnaLista.size()-1){
				insertCommand+=", ";
			}
		}
		
		insertCommand+=");";
		System.out.println(insertCommand);
		if(tab.getTabId().equals("STRUKTURA")){
			insertCommand = "INSERT INTO STRUKTURA (POL_VTO_OZNAKA, POL_TPO_OZNAKA2, VTO_OZNAKA, TPO_OZNAKA) VALUES (?, ?, ?, ?)";
		}
		try{
			
			
			prep_st = connection.prepareStatement(insertCommand);
			if(napuniRedUBazi(prep_st,row,t,selected,tab)){
				return;
			}else{
				
			prep_st.executeUpdate();
			}
		}  catch (SQLException e) {
			JOptionPane.showMessageDialog(new JFrame(),MyApp.getInstance().getResourceBundle().getString("upozorenje17"));
			return;
		}
		
		dijalog.dispose();
		
	}//insert naredba da dodavanje reda
	
	
	/**
	 * Proverava postojanje kljuca u bazi.
	 * @param t
	 * @param kljuc
	 * @return
	 */
	
	public boolean postojiUBaziTajKljucJedan(Tab t,String kljuc){
		boolean ret = false;//ako je jedan primaran kljuc ako ih je vise za svaku kolonu posebno
		for(int i=0;i<t.getPrimarniKljuceviIzBaze().size();i++){
			if(kljuc.trim().equals(t.getPrimarniKljuceviIzBaze().get(i).trim())){
				
				ret = true;
				break;
				}
			}
		
		
		return ret;
	}
	
	/**
	 * Poziva se unutar metode {@link DataBaseCRUD#addRow} i predstavlja konkretnu logiku dodavanja reda u bazu.
	 * @param pr
	 * @param rowToAdd
	 * @param t
	 * @param selected
	 * @param tab
	 * @return
	 * 
	 * @see PreparedStatement
	 * @see Table
	 * @see Tab
	 * @throws NumberFormatException ako se ne ispostuje tip i preciznost
	 * @throws SQLException
	 */
	
	
	public boolean napuniRedUBazi(PreparedStatement pr, Row rowToAdd, Table t,int selected,Tab tab){
		boolean ret = false;
		ArrayList<String> slozenKljucTabele = new ArrayList<>();
		ArrayList<String> uneteVrednosti = new ArrayList<>();
		slozenKljucTabele = daLiTabelaImaSlozenKljuc(t);
		for(int i=0;i<t.getListOfColumns().size();i++){
			
			try {
				if(t.getListOfColumns().get(i).getDataType().equals("int") || 
				   t.getListOfColumns().get(i).getDataType().equals("bigint") ||
				   t.getListOfColumns().get(i).getDataType().equals("numeric") ||
				   t.getListOfColumns().get(i).getDataType().equals("decimal")){
					try{
					int pukniException = Integer.parseInt((String) rowToAdd.getSadrzajPolja().get(i));
					}catch(NumberFormatException ee){
						JOptionPane.showMessageDialog(new JFrame(),MyApp.getInstance().getResourceBundle().getString("upozorenje15"));
						ret = true;
						break;
					}
				}
					
					
				if(t.getListOfColumns().get(i).isPrimaryKey()){
					if(rowToAdd.getSadrzajPolja().get(i).equals("")){
						JOptionPane.showMessageDialog(new JFrame(),MyApp.getInstance().getResourceBundle().getString("upozorenje18"));
						ret = true;
						break;
					}
					if(slozenKljucTabele.size()==1){
						if(postojiUBaziTajKljucJedan(tab,(String)rowToAdd.getSadrzajPolja().get(i))){
							JOptionPane.showMessageDialog(new JFrame(),MyApp.getInstance().getResourceBundle().getString("upozorenje19"));
							ret = true;
							break;
						}
					}else{
						//formira parove iz baze
						//uzima vrednosti iz tih polja
						uneteVrednosti.add((String)rowToAdd.getSadrzajPolja().get(i));
						
						
						}
					
				}
				if(t.getListOfColumns().get(i).getDataType().equals("char") || t.getListOfColumns().get(i).getDataType().equals("varchar")){
					if(t.getListOfColumns().get(i).getDataLength()<((String)rowToAdd.getSadrzajPolja().get(i)).length()){
						JOptionPane.showMessageDialog(new JFrame(),MyApp.getInstance().getResourceBundle().getString("upozorenje20")+t.getListOfColumns().get(i).getDataLength());
						ret = true;
						break;
					}
				}
				
				pr.setObject(i+1, rowToAdd.getSadrzajPolja().get(i));
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			}
		}
		if(slozenKljucTabele.size()>1){
			 ArrayList<ArrayList<String>> listaParova = postojiUBaziTajKljucSlozen(slozenKljucTabele,tab);
			 System.out.println(uneteVrednosti);
			 for(int i=0;i<listaParova.size();i++){
				
				if(listaParova.get(i).equals(uneteVrednosti)){
					JOptionPane.showMessageDialog(new JFrame(), MyApp.getInstance().getResourceBundle().getString("upozorenje19"));
					ret = true;
					break;
				}
			}
		}
		
		if(selected==0){
		for(int j=0;j<MyApp.getInstance().getTabPanelGornji().getTabCount();j++){
			if(((Tab)MyApp.getInstance().getTabPanelGornji().getComponentAt(j)).getTabId().equals(t.getIdLabelTable())){
				if(ret==false){
				iscrtajRed(((Tab)MyApp.getInstance().getTabPanelGornji().getComponentAt(j)),rowToAdd,t);
				((Tab)MyApp.getInstance().getTabPanelGornji().getComponentAt(j)).getTableRows().repaint();
				((Tab)MyApp.getInstance().getTabPanelGornji().getComponentAt(j)).getTableRows().revalidate();
				((Tab)MyApp.getInstance().getTabPanelGornji().getComponentAt(j)).repaint();
				((Tab)MyApp.getInstance().getTabPanelGornji().getComponentAt(j)).revalidate();
				break;
				}
			}
		}
		
		
		MyApp.getInstance().getTabPanelGornji().repaint();
		MyApp.getInstance().getTabPanelGornji().revalidate();
		}else{
			for(int j=0;j<MyApp.getInstance().getTabPanelDonji().getTabCount();j++){
				if(((Tab)MyApp.getInstance().getTabPanelDonji().getComponentAt(j)).getTabId().equals(t.getIdLabelTable())){
					if(ret==false){
					iscrtajRed(((Tab)MyApp.getInstance().getTabPanelDonji().getComponentAt(j)),rowToAdd,t);
					((Tab)MyApp.getInstance().getTabPanelDonji().getComponentAt(j)).getTableRows().repaint();
					((Tab)MyApp.getInstance().getTabPanelDonji().getComponentAt(j)).getTableRows().revalidate();
					((Tab)MyApp.getInstance().getTabPanelDonji().getComponentAt(j)).repaint();
					((Tab)MyApp.getInstance().getTabPanelDonji().getComponentAt(j)).revalidate();
					break;
				}
				}
			}
			
			
			MyApp.getInstance().getTabPanelDonji().repaint();
			MyApp.getInstance().getTabPanelDonji().revalidate();
		}
	 return ret;
	
	}
	
	/**
	 * Poziva se ova metoda u napuniReduBazi koja radi konkretno nad trenutno aktivnom JTable.
	 * @param tab
	 * @param row
	 * @param tabela
	 * @see ResourceBundle
	 * @see Locale
	 * @see Tab
	 * @see Row
	 * @see Table
	 */
	
	public void iscrtajRed(Tab tab,Row row,Table tabela){
		
		tab.getTableRows().removeAll();
		
		ResourceBundle rb = ResourceBundle.getBundle("MessageResources.MessageResources", Locale.getDefault());
		DefaultTableModel model=(DefaultTableModel)tab.getTableRows().getModel();
		JTable ajde = new JTable(model){
				@Override
		        public boolean isCellEditable(int row, int column) {                
	                return false;               
				};
		};		
		
		
		
		
		model.addRow(row.getSadrzajPolja().toArray());
		ajde.getTableHeader().setReorderingAllowed(false);
		tab.add(ajde);
		tab.getTableRows().setFillsViewportHeight(true);
		
	
		
		tab.repaint();
		tab.revalidate();
		
	}
	
	/**
	 * Poziva se ova metoda u metodi {@link DataBaseCRUD#isprazniRedIzBaze} u kojoj se radi konkretno brisanje reda iz JTable.
	 * @param tab
	 * @param indeksrow
	 * @param selected
	 * @see Tab
	 * @see ResourceBundle
	 * @see Locale
	 */
	
	
	public void isprazniRedIzTabele(Tab tab,int indeksrow,int selected){
		
		ResourceBundle rb = ResourceBundle.getBundle("MessageResources.MessageResources", Locale.getDefault());
		DefaultTableModel model=(DefaultTableModel)tab.getTableRows().getModel();
		JTable ajde = new JTable(model){
				@Override
		        public boolean isCellEditable(int row, int column) {                
	                return false;               
				};
		};		
		
		
		
	
		model.removeRow(indeksrow);
		
		tab.repaint();
		tab.revalidate();
	}
	
	/**
	 * Poziva se metoda unutar metode {@link DataBaseCRUD#delete} i vrsi konkretno brisanje, odnsono slanje prethodno kreiranog DELETE upita za bazu.
	 * Takodje se vodi racuna da se brisanje izvrsi na korektan nacin ukoliko smo u administratorskom rezimu
	 * i da pri brisanju odredjenih tipova moraju da se sprovedu modifikacije upita (kao npr kod varchar dodavanje '') da bi brisanje
	 * bilo izvrseno adekvatno.

	 * @param deleteCommand
	 * @param tab
	 * @param indeks
	 * @param primarniKljuc
	 * @param tableWithRows
	 * @param selected
	 * @param tip
	 * @see Tab
	 * @see JTable
	 * @throws SQLException ako ima vezane podatke u drugim tabelama
	 */
	
	public void isprazniRedIzBaze(String deleteCommand,Tab tab,int indeks,String primarniKljuc,JTable tableWithRows,int selected,String tip){
		
		try{
			if(selected==0){
			
				if(!MyApp.getInstance().getPocetniDijalog().getKorisnickoIme().getText().equals("admin")){	
					
						prep_st = connection.prepareStatement(deleteCommand);
						prep_st.executeUpdate();
						isprazniRedIzTabele(tab,indeks,selected);
						
					
				}
				else{
					
						
						String prviDeoUpita = "DELETE FROM KORISNICI WHERE USERNAME = ?;";
						String unetaVrednost = deleteCommand.split("=")[1];
						
						
						isprazniRedIzTabele(tab,indeks,selected);//ovo ako se ostavi ovde brise iz tabele ali ne iz baze
						
						prep_st = connection.prepareStatement(prviDeoUpita);
						prep_st.setString(1,unetaVrednost);
						prep_st.executeUpdate();
						
				}
			
			
			}else{
				
				
				
				prep_st = connection.prepareStatement(deleteCommand);
				prep_st.executeUpdate();
				isprazniRedIzTabele(tab,indeks,selected);//ovo ako se ostavi ovde brise iz tabele ali ne iz baze
				
			}
		}
		  catch (SQLException e) {
			
			if(!MyApp.getInstance().getPocetniDijalog().getKorisnickoIme().getText().equals("admin")){
				JOptionPane.showMessageDialog(new JFrame(), MyApp.getInstance().getResourceBundle().getString("upozorenje21"));
			}
		}
	}
	
	/**
	 * Dodaje zoom dugme ukoliko je u pitanju strani kljuc.
	 * @param t
	 * @param c
	 * @param panel
	 * @param polje
	 * @return
	 * @see Table
	 */
	
	
	public boolean daLiJeStraniKljuc(Table t,Column c,JPanel panel,JTextField polje){
		boolean ret = false;
		for(int i=0;i<MyApp.getInstance().getListaStranihKljuceva().size();i++){
			if(MyApp.getInstance().getListaStranihKljuceva().get(i).getChildTable().equals(t.getIdLabelTable())){
				if(MyApp.getInstance().getListaStranihKljuceva().get(i).getChildColumn().equals(c.getIdLabelColmun())){
					ZoomAction zoomAction = new ZoomAction(MyApp.getInstance().getListaStranihKljuceva().get(i).getParentTable(),
														   MyApp.getInstance().getListaStranihKljuceva().get(i).getChildTable(),
														   MyApp.getInstance().getListaStranihKljuceva().get(i).getParentColumn(),
														   MyApp.getInstance().getListaStranihKljuceva().get(i).getChildColumn(),
														   polje,guiElements);
					
					
					JButton dugmeZoomAction = new JButton(zoomAction);
					dugmeZoomAction.setSize(new Dimension(22, 22));
					dugmeZoomAction.setHideActionText(true);
					panel.add(dugmeZoomAction);
					ret = true;
					break;
				}
			}
			
		}
		if(!ret){
			JButton dugmeZaProstor = new JButton();
			dugmeZaProstor.setVisible(false);
			dugmeZaProstor.setEnabled(false);
			panel.add(dugmeZaProstor);
		}
		return ret;
	}
	
	/**
	 * Proverava da li je strani kljuc da bi se disablovalo dugme za update i ne dodaje zoom dugme.
	 * @param t
	 * @param c
	 * @param panel
	 * @param polje
	 * @return
	 */
	
	public boolean daLiJeStraniKljucBezZoom(Table t,Column c,JPanel panel,JTextField polje){
		boolean ret = false;
		for(int i=0;i<MyApp.getInstance().getListaStranihKljuceva().size();i++){
			if(MyApp.getInstance().getListaStranihKljuceva().get(i).getChildTable().equals(t.getIdLabelTable())){
				if(MyApp.getInstance().getListaStranihKljuceva().get(i).getChildColumn().equals(c.getIdLabelColmun())){
				  ret = true;
				  break;
				}
			}
		}
		
		return ret;
		
	}
	
	/**
	 * Poziva se unutar drugih metoda da bi se ispostovalo da se CRUD operacija validno sprovede ukoliko je u pitanju slozen kljuc.
	 * Povratna vrednost metode je lista koja u sebi sadrzi sva obelezja slozenog kljuca.
	 * @param t
	 * @return
	 */
	
	
	public ArrayList<String> daLiTabelaImaSlozenKljuc(Table t){
		ArrayList<String> listaPrimarnihKljucevaTabele = new ArrayList<>();
		for(int i=0;i<t.getListOfColumns().size();i++){
			if(t.getListOfColumns().get(i).isPrimaryKey()){
				listaPrimarnihKljucevaTabele.add(t.getListOfColumns().get(i).getIdLabelColmun());
			}
		}
		return listaPrimarnihKljucevaTabele;
	}
	
	/**
	 * Uzima sve kljuceve iz tabele, formira parove i proverava da li taj par za slozeni kljuc postoji u tabeli.
	 * @param slozenKljucTabele
	 * @param tab
	 * @return
	 * @see Tab
	 * @throws SQLException
	 */
	
	
	public ArrayList<ArrayList<String>> postojiUBaziTajKljucSlozen(ArrayList<String> slozenKljucTabele,Tab tab){
	
		
		ResultSet rezultat;
		ArrayList<ArrayList<String>> listaParova = new ArrayList<>();
		String kolone = new String();
		for(int i=0;i<slozenKljucTabele.size();i++){
			kolone+=slozenKljucTabele.get(i);
			if(i!=slozenKljucTabele.size()-1){
				kolone+=",";
			}
		}
		
		String selectCommand = new String();
		if(tab.getTabId().equals("STRUKTURA")){
			 selectCommand = "SELECT POL_VTO_OZNAKA,POL_TPO_OZNAKA2,VTO_OZNAKA,TPO_OZNAKA FROM STRUKTURA";
		}else{
			 selectCommand = "SELECT "+kolone+" FROM "+tab.getTabId()+";";

		}
		System.out.println(selectCommand);

		try{
		PreparedStatement prep_stnovo = connection.prepareStatement(selectCommand);
		rezultat = prep_stnovo.executeQuery();
		ArrayList<String> parovi = new ArrayList<>();
		
		while(rezultat.next()){
				String s = new String();
				for(int i=0;i<slozenKljucTabele.size();i++){
					System.out.println(slozenKljucTabele.get(i));
					if(slozenKljucTabele.get(i).equals("POL_TPO_OZNAKA")){
						 s = rezultat.getString("POL_TPO_OZNAKA2");
					}else{
					     s = rezultat.getString(slozenKljucTabele.get(i));
					}
					System.out.println("ovo je rez"+s);
					parovi.add(s);
				}
				System.out.println(parovi);
				listaParova.add(parovi);
				parovi = new ArrayList<>();
		}
		}catch(SQLException e){
			System.out.println("ovde je problem");
		}
		
		return listaParova;
	}
	
	/**
	 * Pronalazi staru vrednost primarnog kljuca.
	 * @param kljuc
	 * @return
	 * @see {@link HashMap}
	 */
	
	public String nadjiStaruVrednostPrimarnogKljuca(String kljuc){
		String ret = new String();
		
		for(HashMap.Entry<String, Integer> entry : MyApp.getInstance().getStareVrednosti().entrySet()) {
			
			if(entry.getKey().equals(kljuc)){
				ret = (entry.getValue()).toString();
				
			}
		}
		return ret;
	}
		
	

}
