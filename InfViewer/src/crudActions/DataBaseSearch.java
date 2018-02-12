package crudActions;

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
import java.util.ResourceBundle;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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

import gui.MyApp;
import gui.Polje;
import gui.Tab;
import model.Column;
import model.Row;
import model.Table;

/**
 * <code>DataBaseSearch</code> implementira {@link Search} interfejs.
 * Koristi se za implementaciju metoda za pretragu u tabelama.
 *
 */

public class DataBaseSearch implements Search{

	private int selectedButton;
	
	HashMap<String,ArrayList<String>> uneteVrednosti = new HashMap<String,ArrayList<String>>(); //mapa kojom preuzimamo vrednosti iz guija
	//i popunjavamo samo popunjena mesta da bismo mogli da kreiramo upit u zavisnosti od tipa
	//kljuc je idColumn, a vrednost je string
	HashMap<String, Polje> guiElements = new HashMap<String, Polje>();//u ovoj mapi cuvamo kao kljuc
	//naziv kolone a kao polja (ako ih ima vise) cuvamo poziciju i tekst polje
	
	private  Connection connection;
	private PreparedStatement prep_st;
	private int i;
	
	/**
	 * Konstruktor sa parametrima {@link DataBaseSearch} klase
	 * @param selectedButton
	 */
	
	public DataBaseSearch(int selectedButton){
		this.selectedButton = selectedButton;
		connection = MyApp.getConnection();
		i=0;
		
	}

	/**
	 * Implementacija pretrage u tabelama
	 * @see Tab
	 * @throws java.lang.NullPointerException ako nije oznacena tabela koja se zeli pretraziti
	 */
	
	@Override
	public void search() {//metoda koja objedinjuje ostale metode koje sluze za kreiranje gui a za search u zavisnoti od 
		//tipa kolone, ukoliko je brojna vrednost unosi se interval od do,dok ukoliko je varchar char unosi se samo jedno 
		//polje
		
		
		Tab tab = new Tab();
		
		if(selectedButton==0){
			try{
				 tab= (Tab)MyApp.getInstance().getTabPanelGornji().getSelectedComponent();
				
				}
				catch(java.lang.NullPointerException exception){
				JOptionPane.showMessageDialog(new JFrame(), MyApp.getInstance().getResourceBundle().getString("upozorenje23"));
			}
		
		}else if(selectedButton==1){
			try{
				 tab= (Tab)MyApp.getInstance().getTabPanelDonji().getSelectedComponent();
				 
				}
				catch(java.lang.NullPointerException exception){
				JOptionPane.showMessageDialog(new JFrame(), MyApp.getInstance().getResourceBundle().getString("upozorenje23"));
			}
		}
		
		try{
			Table t = MyApp.getInstance().getTableById(tab.getTabId());
			System.out.println(tab.getTabId());
		
	
			JDialog addDijalog = new JDialog();
			
			JPanel panel = new JPanel();
			JScrollPane scrollPane = new JScrollPane(panel);
			
			scrollPane.setHorizontalScrollBarPolicy(
					   JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
					scrollPane.setVerticalScrollBarPolicy(
					   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
			
			GridLayout layout = new GridLayout(t.getListOfColumns().size()+1,4);
			panel.setLayout(layout);
			addDijalog.setVisible(true);
			addDijalog.setTitle("Pretrazi tabelu");
			addDijalog.setSize(500,500);
			addDijalog.setLocationRelativeTo(null);
			addDijalog.setResizable(false);
			addDijalog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			
				for(int i=0;i<t.getListOfColumns().size();i++){
					JLabel labela; 
					if(t.getListOfColumns().get(i).isPrimaryKey()){
						labela = new JLabel(t.getListOfColumns().get(i).getIdLabelColmun()+"*");
					}else{
						labela = new JLabel(t.getListOfColumns().get(i).getIdLabelColmun());
					}
					
					
					dodajNaGuiUZavisnostiOdTipa(labela,t,t.getListOfColumns().get(i),panel);
					
					}

				JButton searchDugme = new JButton("Pretrazi");
				JButton cancelDugme = new JButton("Zatvori");
				panel.add(searchDugme);
				panel.add(cancelDugme);
				
				cancelDugme.addActionListener(new ActionListener() { 
					  public void actionPerformed(ActionEvent e) { 
						  	addDijalog.dispose();
					  } 
					} );
				
				
				searchDugme.addActionListener(new ActionListener() { 
					  public void actionPerformed(ActionEvent e) { 
						  	searchRow(t,addDijalog);
					  }

					
					} );
				
				addDijalog.add(panel);
		

				}catch(java.lang.NullPointerException exception){
					JOptionPane.showMessageDialog(new JFrame(), MyApp.getInstance().getResourceBundle().getString("upozorenje23"));
				}
		}
		
		
	public void searchRow(Table t, JDialog addDijalog) {//metoda u kojoj se puni lista unetih vrednosti za pretragu,takodje
		//su podrzane provere da mora da unese obe vrednosti ako je popunio neku od od-do vrednosti,
		//na osnovu ovih vrednosti kreirace se upit za pretragu
		//vrsi se select upit nad bazom gde pravimo kombinovanu pretragu na taj nacin sto uzimamo sve vrednosti od popunjenih polja
		//i na osvovu naredbi LIKE i BETWEEN kreiramo upit
		//kada nam se vrati rezultat nakon upita ka bazi iscrtavamo tabelu i refreshujemo view
		
		for(HashMap.Entry<String, Polje> entry : guiElements.entrySet()) {
			if(((Polje)entry.getValue()).getListaPolja().size()>1){
				ArrayList<String> lista = new ArrayList<>();
				
				if((!((String)((Polje)entry.getValue()).getListaPolja().get(0).getText()).equals("") && ((String)((Polje)entry.getValue()).getListaPolja().get(1).getText()).equals(""))
						 || (((String)((Polje)entry.getValue()).getListaPolja().get(0).getText()).equals("") && !((String)((Polje)entry.getValue()).getListaPolja().get(1).getText()).equals(""))){
					 JOptionPane.showMessageDialog(new JFrame() ,MyApp.getInstance().getResourceBundle().getString("upozorenje24"));
					 return;
				 }
				
				 if(!((String)((Polje)entry.getValue()).getListaPolja().get(0).getText()).equals("")){
					 lista.add((String)((Polje)entry.getValue()).getListaPolja().get(0).getText());
				 }
				 
				 if(!((String)((Polje)entry.getValue()).getListaPolja().get(1).getText()).equals("")){
					 lista.add((String)((Polje)entry.getValue()).getListaPolja().get(1).getText());

				 }
				 if(!((String)((Polje)entry.getValue()).getListaPolja().get(0).getText()).equals("") || !((String)((Polje)entry.getValue()).getListaPolja().get(1).getText()).equals("")){
					 	uneteVrednosti.put(entry.getKey(),lista);
				 }
				 
				 
				
			}else{
				ArrayList<String> lista = new ArrayList<String>();
				 if(!((String)((Polje)entry.getValue()).getListaPolja().get(0).getText()).equals("")){
					 lista.add((String)((Polje)entry.getValue()).getListaPolja().get(0).getText());
					 uneteVrednosti.put(entry.getKey(),lista);
					}
				 }
				 
				 
		}
		
		for(Entry<String, ArrayList<String>> entry : uneteVrednosti.entrySet()) {
			System.out.println("Kljuc"+entry.getKey()+"vrednost"+entry.getValue());
		}
		
		String upit = "SELECT * ";
		
		
		upit+=" FROM "+t.getIdLabelTable()+" WHERE ";
		
		int brojac2=0;
		for(Entry<String, ArrayList<String>> entry : uneteVrednosti.entrySet()) {
			upit+=entry.getKey();
			if(entry.getValue().size()==1){
				upit+=" LIKE '"+entry.getValue().get(0)+"%'";
				
			}
			
			
			
			if(entry.getValue().size()==2){
				upit+=" BETWEEN "+entry.getValue().get(0)+" AND "+entry.getValue().get(1);
				
			}
			
			if(brojac2!=uneteVrednosti.size()-1){
				upit+=" AND ";
			}
			brojac2++;
		}
		System.out.println("UPIT"+upit);
		ResultSet rezultat;
    	
    	ArrayList<String> values = new ArrayList<>();
    	ArrayList<Row> listaRedova = new ArrayList<>();
    	try {
			prep_st = connection.prepareStatement(upit);
			rezultat = prep_st.executeQuery();
			
			int brojac3 = 0;
			while(rezultat.next()){
				
				
				for(int j=0;j<t.getListOfColumns().size();j++){
					
					String s = rezultat.getString(t.getListOfColumns().get(j).getIdLabelColmun());
					System.out.println("ovo vrati"+s);
					
					if(j<t.getListOfColumns().size()){
						
						values.add(s);
					}
					if(j==t.getListOfColumns().size()-1){
						Row row = new Row();
						for(int k=0;k<values.size();k++){
							if(!(values.get(i).equals("admin"))){
								
									row.getSadrzajPolja().add(values.get(k));
									if(!vecPostoji(listaRedova,row)){
									listaRedova.add(row);
									
							}
							}
						}
						values = new ArrayList<>();
					}
				
					}
				}
	    	}
				catch(SQLException e1){
				
					i=0;
					}
	    	
	    		catch(NullPointerException ee){
	    			i=0;
	    		}catch(java.lang.IndexOutOfBoundsException eee){ i=0;}
	    	
	    	ResourceBundle rb = ResourceBundle.getBundle("MessageResources.MessageResources", Locale.getDefault());
			DefaultTableModel model=new DefaultTableModel();
			JTable table = new JTable(model){
					@Override
			        public boolean isCellEditable(int row, int column) {                
		                return false;               
					};
			};		
			
			
			for(Column kolona : t.getListOfColumns()){	
				
				model.addColumn(kolona.getIdLabelColmun());
				System.out.println("Dodao");

			}
			
			for(Row redovi : listaRedova){
				
				model.addRow(redovi.getSadrzajPolja().toArray());
			}
			
			
			table.setFillsViewportHeight(true);
			table.setColumnSelectionAllowed(true);//da moze da se selektuje vise kolona ako je slozeni kljuc
			table.getTableHeader().setReorderingAllowed(false);
		if(selectedButton == 0){
		for(int j=0;j<MyApp.getInstance().getTabPanelGornji().getTabCount();j++){
			if(((Tab)MyApp.getInstance().getTabPanelGornji().getComponentAt(j)).getTabId().equals(t.getIdLabelTable())){
				
		((Tab)MyApp.getInstance().getTabPanelGornji().getComponentAt(j)).removeAll();
		((Tab)MyApp.getInstance().getTabPanelGornji().getComponentAt(j)).add(table);
		((Tab)MyApp.getInstance().getTabPanelGornji().getComponentAt(j)).getTableRows().repaint();
		((Tab)MyApp.getInstance().getTabPanelGornji().getComponentAt(j)).getTableRows().revalidate();
		((Tab)MyApp.getInstance().getTabPanelGornji().getComponentAt(j)).repaint();
		((Tab)MyApp.getInstance().getTabPanelGornji().getComponentAt(j)).revalidate();
		break;
			}
		}
		}else{
			for(int j=0;j<MyApp.getInstance().getTabPanelDonji().getTabCount();j++){
				if(((Tab)MyApp.getInstance().getTabPanelDonji().getComponentAt(j)).getTabId().equals(t.getIdLabelTable())){
					
			((Tab)MyApp.getInstance().getTabPanelDonji().getComponentAt(j)).removeAll();
			((Tab)MyApp.getInstance().getTabPanelDonji().getComponentAt(j)).add(table);
			((Tab)MyApp.getInstance().getTabPanelDonji().getComponentAt(j)).getTableRows().repaint();
			((Tab)MyApp.getInstance().getTabPanelDonji().getComponentAt(j)).getTableRows().revalidate();
			((Tab)MyApp.getInstance().getTabPanelDonji().getComponentAt(j)).repaint();
			((Tab)MyApp.getInstance().getTabPanelDonji().getComponentAt(j)).revalidate();
			break;
				}
		}
		}
	} 
	
	public void dodajNaGuiUZavisnostiOdTipa(JLabel labelaaa,Table t,Column c,JPanel panel){
		//pomocna metoda koja se poziva unutar metode search proverava tip i uredjuje gui prozora za pretragu
		
		
		if(c.getDataType().equals("numeric") || c.getDataType().equals("datetime") || c.getDataType().equals("int") || c.getDataType().equals("bigint")){
	
			panel.add(labelaaa);
			JTextField odopseg = new JTextField();
			Polje p1 = new Polje();
			p1.getListaPolja().add(odopseg);
			p1.getPosition().add(1);
			p1.getType().add(c.getDataType());
			JTextField doopseg = new JTextField();
			p1.getListaPolja().add(doopseg);
			p1.getPosition().add(2);
			p1.getType().add(c.getDataType());
			guiElements.put(c.getIdLabelColmun(),p1);
			JLabel labela = new JLabel(MyApp.getInstance().getResourceBundle().getString("upozorenje25"));
			
			panel.add(odopseg);
			panel.add(doopseg);
			panel.add(labela);
			
			
		}else if(c.getDataType().equals("bit")){
			panel.add(labelaaa);
			JCheckBox trazi_true = new JCheckBox();
			JCheckBox trazi_false = new JCheckBox();
			JCheckBox trazi_ignore = new JCheckBox();
			panel.add(trazi_true);
			panel.add(trazi_false);
			panel.add(trazi_ignore);
			
		}else{
			panel.add(labelaaa);
		
			JTextField polje = new JTextField();
			Polje p1 = new Polje();
			p1.getListaPolja().add(polje);
			p1.getPosition().add(1);
			p1.getType().add(c.getDataType());
			guiElements.put(c.getIdLabelColmun(),p1);
			panel.add(polje);
			JButton dugmeZaProstor = new JButton();
			dugmeZaProstor.setVisible(false);
			dugmeZaProstor.setEnabled(false);
			
			JButton dugmeZaProstor1 = new JButton();
			dugmeZaProstor1.setVisible(false);
			dugmeZaProstor1.setEnabled(false);
			panel.add(dugmeZaProstor);
			panel.add(dugmeZaProstor1);
		}	
		
		
	}				
	
	public boolean vecPostoji(ArrayList<Row> listaRedova, Row red){
		boolean ret = false;
		for(int i=0;i<listaRedova.size();i++){
			if(listaRedova.get(i).equals(red)){
				ret = true;
				break;
			}
			
			
		}
		return ret;
	}
							
}
