package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import crudActions.CloseButtonAction;
import model.Column;
import model.Row;
import model.Table;


/**
 * Klasa {@code Tab} nasledjuje {@link JPanel} i
 * predstavlja sadrzaj tabova glavnog prozora aplikacije
 * sadrzi {@link JTable}, {@link TabPanel}, {@link Table}
 *@see JScrollPane
 */


@SuppressWarnings("serial")
public class Tab extends JPanel{
	
	private JTable tableRows;
	private Table table;
	private String tabName;
	private boolean allowChild = false;
	private TabPanel child = null;
	private JScrollPane scroll;
	private ArrayList<String> primarniKljuceviIzBaze= new ArrayList<>();
	
	private  Connection connection;
	private PreparedStatement prep_st;
	private int i;
	
	/**
	 * Konstruktor {@link  Tab} klase
	 * 
	 */
	
	public Tab(){
		this. tableRows = tableRows;
		this.table = table;
		
		}
	
	/**
	 * Konstruktor sa parametrima klase{@link  Tab} 
	 * @param t
	 *  * @return {@link Tab}
	 * 
	 * 
	 */
	
	public Tab(Table t){
		this. table = t;
		iscrtajTabele(t);
		
		connection = MyApp.getConnection();
		i=0;
	}
	
	/**
	 * Konstruktor sa parametrima {@link  Tab} vrsi iscrtavanje tabela 
	 * @param t {@link Table} 
	 * @param  getChildren boolean
	 * 
	 */

	public Tab(Table t,boolean getChildren){
		
		
		connection = MyApp.getConnection();
		
		allowChild = getChildren;
		table = t;
		setLayout(new java.awt.BorderLayout());
		iscrtajTabele(t);
		scroll  = new JScrollPane(tableRows, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(scroll);
		if(allowChild){
			if(table.getChildOfTable().isEmpty()){
				scroll  = new JScrollPane(tableRows, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				add(scroll);
			}else{
				MyApp.getInstance().getTabPanelDonji().removeAll();
				MyApp.getInstance().getTabPanelDonji().revalidate();
				MyApp.getInstance().getTabPanelDonji().repaint();
				child = new TabPanel();
				for(int i=0;i<table.getChildOfTable().size();i++){
					Table table2 = MyApp.getInstance().getTableById(table.getChildOfTable().get(i));
					Tab tab = new Tab(table2,false);
					
					child.addTab(table2.getIdLabelTable(), tab);
					child.setTabComponentAt(i, new Tab(tab.getTableRows(),tab.getTable()));
					
					MyApp.getInstance().setTabPanelDonji(child);
					
					BorderLayout layout = (BorderLayout) MyApp.getInstance().getPan().getDonjiPanel().getLayout();
					if(layout.getLayoutComponent(BorderLayout.CENTER)!=null){
						MyApp.getInstance().getPan().getDonjiPanel().remove(layout.getLayoutComponent(BorderLayout.CENTER));
					}
					
					MyApp.getInstance().getPan().getDonjiPanel().add(child);
					MyApp.getInstance().getPan().getDonjiPanel().setVisible(true);
					MyApp.getInstance().getPan().getDonjiPanel().revalidate();
					MyApp.getInstance().getPan().getDonjiPanel().repaint();
					
					
					
				}
				
			}
		}else{
			scroll  = new JScrollPane(tableRows, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			add(scroll);
		}
		
		
	}
	
	/**
	 * Preuzimanje vrednosti komponente TabId
	 * @see TabId
	 */

	
	public String getTabId(){
		return table.getIdLabelTable();
	}
	
	/**
	 * Konstruktor sa parametrima {@link  Tab} izvrsava zatvaranje selektovanog TabPanela 
	 * @param tableRows {@link JTable} 
	 * @param table {@link Table} 
	 * @see JButton
	 * @see JLabel
	 * 
	 */


	public Tab(JTable tableRows, Table table) {
		
		
		this.table = table;
		this.tableRows = tableRows;
		this.tabName = table.getIdLabelTable();
		iscrtajTabele(table);
		this.setLayout(new FlowLayout(FlowLayout.LEFT,10,0));
		JLabel name = new JLabel(tabName);
		
		CloseButtonAction closeAction = new CloseButtonAction(table,tableRows);
		JButton closeTabButton = new JButton(closeAction);
		closeTabButton.setAlignmentX(LEFT_ALIGNMENT);
		closeTabButton.setPreferredSize(new Dimension(10,10));
		closeTabButton.setOpaque(false);
		closeTabButton.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				TabPanel tp = (TabPanel) ((JButton)e.getSource()).getParent().getParent().getParent();
				int i = tp.getSelectedIndex();
				tp.remove(i);
				tp.repaint();
				tp.revalidate();
				
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		add(name);
		add(closeTabButton);
			
	}
	
	/**

	

	 * Prikaz tabela iz baze podatka i lokalizovanje kolona tabele
	 * @param t {@link Table}
	 * @return void
	 * @throws SQLException
     * @see ArrayList
	 * @see ResourceBundle
	 */

	
	 public void iscrtajTabele(Table t){
		  System.out.println("USOOOOOOOOOOOOOOO");
		 	ResultSet rezultat;
			
	    	String upit = "SELECT * FROM ";
	    	String tabela = t.getIdLabelTable();
	    	upit+=tabela;
	    	
	    	ArrayList<String> values = new ArrayList<>();
	    	ArrayList<Row> listaRedova = new ArrayList<>();
	    	try {
				prep_st = connection.prepareStatement(upit);
				rezultat = prep_st.executeQuery();
				
				
				while(rezultat.next()){
					
					for(int j=0;j<t.getListOfColumns().size();j++){
					
					String s = rezultat.getString(t.getListOfColumns().get(j).getIdLabelColmun());
					if(t.getListOfColumns().get(j).isPrimaryKey()){
						primarniKljuceviIzBaze.add(s);
						
					}
					if(s.equals("")){
						
					}else{
					
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
	    	}
				catch(SQLException e1){
				
					i=0;
					}
	    	
	    		catch(NullPointerException ee){
	    			i=0;
	    		}catch(java.lang.IndexOutOfBoundsException eee){ i=0;}
	    	
	    	ResourceBundle rb = ResourceBundle.getBundle("MessageResources.MessageResources", Locale.getDefault());
			DefaultTableModel model=new DefaultTableModel();
			tableRows = new JTable(model){
					@Override
			        public boolean isCellEditable(int row, int column) {                
		                return false;               
					};
			};		
			for(Column kolona : t.getListOfColumns()){	
				
				model.addColumn(kolona.getIdLabelColmun());


			}
			for(Row redovi : listaRedova){
				
				model.addRow(redovi.getSadrzajPolja().toArray());
			}
			
			tableRows.setFillsViewportHeight(true);
			tableRows.setColumnSelectionAllowed(true);//da moze da se selektuje vise kolona ako je slozeni kljuc
			tableRows.getTableHeader().setReorderingAllowed(false);
			
			
	 }
	 
	 /**
	  * Prikaz tabela iz baze podatka i lokalizovanje kolona tabele
	  * @param t
	  * @param table
	  * @return
	  */
	 
	 public JTable iscrtajTabeleProsledjeno(Table t,JTable table){

		 	ResultSet rezultat;
			
	    	String upit = "SELECT * FROM ";
	    	String tabela = t.getIdLabelTable();
	    	upit+=tabela;
	    	
	    	ArrayList<String> values = new ArrayList<>();
	    	ArrayList<Row> listaRedova = new ArrayList<>();
	    	try {
				prep_st = connection.prepareStatement(upit);
				rezultat = prep_st.executeQuery();
				
				
				while(rezultat.next()){
					
					for(int j=0;j<t.getListOfColumns().size();j++){
					
					String s = rezultat.getString(t.getListOfColumns().get(j).getIdLabelColmun());
					if(t.getListOfColumns().get(j).isPrimaryKey()){
						primarniKljuceviIzBaze.add(s);
						
					}
					if(s.equals("")){
						
					}else{
					
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
	    	}
				catch(SQLException e1){
				
					i=0;
					}
	    	
	    		catch(NullPointerException ee){
	    			i=0;
	    		}catch(java.lang.IndexOutOfBoundsException eee){ i=0;}
	    	
	    	ResourceBundle rb = ResourceBundle.getBundle("MessageResources.MessageResources", Locale.getDefault());
			DefaultTableModel model=new DefaultTableModel();
			table = new JTable(model){
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
			
			return table;
	 }
	 
	 /**
	  * Proverava da li postojii vec trazeni red tako sto pretrazuje kroz listu redova
	  * @param listaRedova
	  * @param red
	  * @return
	  */
	 
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
				
	/**
	 * Preuzimanje vrednosti komponente tableRows
	 * @return tableRows
	 */

	public JTable getTableRows() {
		return tableRows;
	}
	
	/**
	 * Postavljanje nove vrednosti komponente tableRows
	 * @param tableRows
	 * @return void
	 * 
	 */

	public void setTableRows(JTable tableRows) {
		this.tableRows = tableRows;
	}

	/**
	 * Preuzimanje vrednosti komponente table
	 * @return table
	 */
	
	public Table getTable() {
		return table;
	}
	
	/**
	 * Postavljanje nove vrednosti komponente table
	 * @param table
	 * @return void
	 * 
	 */

	public void setTable(Table table) {
		this.table = table;
	}

	/**
	 * Preuzimanje vrednosti komponente primarniKljuceviIzBaze
	 * @return primarniKljuceviIzBaze
	 */
	
	public ArrayList<String> getPrimarniKljuceviIzBaze() {
		return primarniKljuceviIzBaze;
	}
	
	/**
	 * Postavljanje nove vrednosti komponente primarniKljuceviIzBaze
	 * @param primarniKljuceviIzBaze
	 * @return void
	 * 
	 */

	public void setPrimarniKljuceviIzBaze(ArrayList<String> primarniKljuceviIzBaze) {
		this.primarniKljuceviIzBaze = primarniKljuceviIzBaze;
	}

}
