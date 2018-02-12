package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import model.FK;
import model.ForeignKeyConstraint;
import model.Korisnici;
import model.Packages;
import model.Sistem;
import model.Table;
import parsing.DataBaseParser;
import parsing.JSONParser;

import tree.Tree;

/**
 *  <code>MyApp<code> klasa putem koje korisnik komunicira sa aplikacijom. 
 * <p> Unutar nje su smesteni svi elementi glavnog prozora.</p>
 * <p> Nasledjuje  {@link JFrame} klasu i bazira se na singlton dizajn sablonu.</p>
 * 
 * 
 */

public class MyApp extends JFrame {
	
	//promenljive
		/**
		 * <code>jsonLogIn</code> Dijlog za odabir json fajla
		 */
	private JsonLogIn jsonLogIn;
	/**
	 * <code>bazeLogIn</code> Dijlog za unos parametara za konekciju na bazu podtaka
	 */
	private BazeLogIn bazeLogIn;
	
	private JSONParser jsonParser;
	
	private String jsonFile;
	
	private String odaberi ="";
	
	private PocetniDijalog pocetniDijalog;
	
	
	ArrayList<Korisnici> listaKorisnika = new ArrayList<>();

	/**
	 * {@code connection} Komponenta koja uspostavlja konekciju sa serverom baze podataka
	 */
	public static Connection connection = null;	
	/**
	 * {@code connectionString} Komponenta koja sadrzi url baze
	 */
	private static  String connectionString = "";
	private static  String username = "";	
	private static  String password = "";
	private static String korisnickoIme;
	public static String getKorisnickoIme() {
		return korisnickoIme;
	}
	
	/**
	 * Postavljanje nove vrednosti komponente korisnickoIme
	 * @param korisnickoIme
	 * @return void
	 */

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

    private Sistem system;

	private DataBaseParser bazaParser;
	
	private static final long serialVersionUID = 1L;
	/**
	 * Instanca koja obezbedjuje implementaciju <code> Singlton </code> sablona.
	 */
	private static MyApp instance=null;
	private ArrayList<Table> listaSvihTabela = new ArrayList<Table>();
	private ArrayList<Packages> listaSvihPaketa = new ArrayList<Packages>();
	private HashMap<String,Table> mapaSvihTabela = new HashMap<String,Table>();
	private TabPanel tabPanelGornji = new TabPanel();
	private static ResourceBundle resourceBundle;
	private TabPanel tabPanelDonji = new TabPanel();
	private ArrayList<FK> listaStranihKljuceva = new ArrayList<>();
	HashMap<String,Integer> stareVrednosti = new HashMap<>();//mapa koja nam treba za update sluzi da bismo mogli da postavimo update upit i da imamo
	//vrednost starog kljuca

	
	private ArrayList<ForeignKeyConstraint> listaSvihOgranicenja = new ArrayList<>();
	
	/**
	 * Preuzimanje vrednosti komponente stareVrednosti
	 * @return stareVrednosti
	 */
	
	public HashMap<String, Integer> getStareVrednosti() {
		return stareVrednosti;
	}

	/**
	 * Postavljanje nove vrednosti komponente stareVrednosti
	 * @param stareVrednosti
	 * @return void
	 */
	
	public void setStareVrednosti(HashMap<String, Integer> stareVrednosti) {
		this.stareVrednosti = stareVrednosti;
	}

	private Sistem  s = new Sistem();
	/**
	 * <code>tree</code> stablo kojim je predstavljena sema podataka.
	 */
	private Tree tree;
	private Meni meni;
	private ToolBar tb;
	private Paneli pan;
	private JScrollPane scroll;
	private JButton addDugmeGornje;
	private JButton addDugmeDonje;
	private JButton updateDugmeGornje;
	private JButton updateDugmeDonje;
	
	
	private ArrayList<String> bioSamOvde = new ArrayList<String>();
	

	//konstruktori
	/**
	 * Konstruktor aplikacije.
	 * <p>Postavlja za podrazumevani jezik aplikacije engleski jezik.
	 */
	private MyApp(){
		Locale.setDefault(new Locale("en","US"));
		resourceBundle = ResourceBundle.getBundle("MessageResources.MessageResources", Locale.getDefault());
	}

	/**
	 * Vraca jedinu instancu {@link MyApp} klase ove aplikacije.
	 * Ako instanca ne postoji, kreira novu. Poziva metod 
	 * 
	 * @return {@link MyApp}
	 */
	public static MyApp getInstance() {
		if (instance == null) {
			instance = new MyApp();
			instance.initialise();
			MyApp.getInstance().changeLanguage();

		}
		return instance;
	}
	
	//metode

	/**
	 * {@code initialise} Inicijalizovanje komponenti glavnog prozora 
	 */
	private void initialise() {
		
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    setExtendedState(JFrame.MAXIMIZED_BOTH);
	    setSize(screenSize);
	    setBounds(1000, 1000, 1000, 600);
	    setResizable(false);//smanjiti ekran na odredjenu velicinu
	    setTitle(resourceBundle.getString("naslovInfViewer"));
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);	
		Image img = kit.getImage("src/images/icon.png");
		setIconImage(img);
		
		pocetniDijalog = new PocetniDijalog(this, true);
		pocetniDijalog.setVisible(true);
	
		
		if(odaberi.equals("json")){
			
			jsonLogIn = new JsonLogIn(this,true);
			jsonLogIn.initComponents();			

			jsonParser = new JSONParser();			
			
			if ((pocetniDijalog.getKorisnickoIme().getText().equals("admin")) ) {
				jsonLogIn.setVisible(false);
				jsonParser.setJsonFile("JSONkorisnici.json");
				jsonParser.parseJson();
			}
			else {
				jsonLogIn.setVisible(true);
				jsonParser.setJsonFile(jsonLogIn.getIzabraniJson().getText());
				jsonParser.parseJson();
				
				
			}
			
			for(int i=0;i<MyApp.getInstance().getSystem().getListaSvihTabelaUSistemu().size();i++)
				MyApp.getInstance().getMapaSvihTabela().put(MyApp.getInstance().getSystem().getListaSvihTabelaUSistemu().get(i).getIdLabelTable(), 
						MyApp.getInstance().getSystem().getListaSvihTabelaUSistemu().get(i));
			
		}
		
		else if(odaberi.equals("bazapodataka")){
			
			bazeLogIn = new BazeLogIn(this,true);
			bazeLogIn.initComponents();
			bazeLogIn.setVisible(true);

			bazaParser = new DataBaseParser();
			
			jsonParser = new JSONParser();
			
			
			
			MyApp.setConnectionString(bazeLogIn.getServer().getText());
			MyApp.setUsername(bazeLogIn.getLogin().getText());
			MyApp.setPassword(bazeLogIn.getSifra().getText());
			
			if ((pocetniDijalog.getKorisnickoIme().getText().equals("admin")) ) {
				jsonParser.setJsonFile("JSONkorisnici.json");
				jsonParser.parseJson();
			}
			else {
				bazaParser.parsiraj();
				for(int i=0;i<MyApp.getInstance().getSystem().getListaSvihTabelaUSistemu().size();i++)
					MyApp.getInstance().getMapaSvihTabela().put(MyApp.getInstance().getSystem().getListaSvihTabelaUSistemu().get(i).getIdLabelTable(), MyApp.getInstance().getSystem().getListaSvihTabelaUSistemu().get(i));
		
			}		
			
		}
		setKorisnickoIme(pocetniDijalog.getKorisnickoIme().getText());
		
		meni = new Meni();
		setJMenuBar(meni);
		
		pan = new Paneli();	
		pan.getGornjiPanel().add(tabPanelGornji);

		tb = new ToolBar();
		getContentPane().add(tb, BorderLayout.NORTH);	

		initializeTree();
		scroll = new JScrollPane(tree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    scroll.setPreferredSize(new Dimension(250, 500));
		add(scroll, BorderLayout.WEST);

		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, pan);
		sp.setDividerLocation(300);
		add(sp, BorderLayout.CENTER);
		
		setVisible(true);
		
		addDugmeGornje = pan.getTb1().getToolBarAddAction();
		addDugmeDonje = pan.getTb2().getToolBarAddAction();		//da bi mogla da disablujem dugme
		updateDugmeGornje = pan.getTb1().getToolBarUpdateAction();
		updateDugmeDonje = pan.getTb2().getToolBarUpdateAction();
	}
	

	/**
	 * Inicijalizacija stabla u kome se nalaze podaci iz json fajla ili iz baze podatka
	 * @return void
	 */
	public void initializeTree() {		
		tree = Tree.getInstance();	
		tree.setMinimumSize(new Dimension(300,100));				
	}	
	
	/**
	 * Dodavanje konkretne tabele u listu
	 * @param t 
	 * @return void
	 */
	public void addToListOfTables(Table t){
		listaSvihTabela.add(t);
	}
	
	/**
	 * Dodavanje konkretnog paketa u listu
	 * @param p
	 * @return void
	 */
	public void addToListOfPackage(Packages p){
		listaSvihPaketa.add(p);
	}
	
	/**
	 * Pronalazenje tabele unutar liste tabela po zadatoj id vrednosti
	 * @param idNameTable
	 * @return String
	 */
	public String findTableById(String idNameTable){
		for(int i=0;i<listaSvihTabela.size();i++){
			if(listaSvihTabela.get(i).getIdLabelTable().equals(idNameTable)){
				return  listaSvihTabela.get(i).getIdLabelTable();
			}
		}
		
		return null;

	}
	/**
	 * Pronalazenje paketa unutar liste paketa po zadatoj id vrednosti
	 * @param id
	 * @return Packages
	 */
	
	public Packages findPackageById(String id){
		for(int i=0;i<listaSvihPaketa.size();i++){
			if(listaSvihPaketa.get(i).getIdPackage().equals(id)){
				return  listaSvihPaketa.get(i);
			}
		}
		
		return null;
	}
	
	/**
	 * Provera unetih parametara {@code connectionString} {@code username} {@code password} 
	 * <p>Povezivanje sa serverom baze podataka
	 * @return {@link Connection}
	 * @throws SqlException ukoliko konekcija nije dobra
	 */
	public static Connection getConnection() {
		if (connection == null) 
		{
			try 
			{			
				if(connectionString.equals("") && username.equals("") && password.equals(""))
				{
					connectionString = "147.91.175.155/psw-2017-tim2-1";
					username = "psw-2017-tim2-1";
					password = "tim2-115903598";
					
					connection = DriverManager.getConnection("jdbc:jtds:sqlserver://"+ connectionString+"", username, password);
					
					System.out.println("Konektovan je na bazu podataka.");
				}
				
				else
				{				
					try
					{
						connection = DriverManager.getConnection("jdbc:jtds:sqlserver://"+ connectionString+"", username, password);
					}
					catch(SQLException e)
					{
						JOptionPane.showMessageDialog(new JFrame(), MyApp.getInstance().getResourceBundle().getString("upozorenje29"));
					}
				}
			} 
			
			catch (SQLException e) 
			{			
				
				JOptionPane.showMessageDialog(new JFrame(), MyApp.getInstance().getResourceBundle().getString("upozorenje30"));
			}
			
		}

		return connection;
	}
	/**
	 * Povezivanje sa serverom baze podataka
	 * @param log , imeServera, sifra String vrednosti koje se prosledjuju u {@code getConnection()} metodu 
	 * @return boolean
	 * @throws SqlException ukoliko nije doslo do uspesnog povezivanja sa serverom baze
	 */

	public boolean proveriKonekciju(String log, String imeServera, String sifra){
		
		/*System.out.println(imeServera);
		System.out.println(log);
		System.out.println("Password   "+sifra);
		System.out.println(sifra.contains("?"));*/
		try{
			 DriverManager.getConnection("jdbc:jtds:sqlserver://"+ imeServera+"", log, sifra);
			return true;
		}catch(SQLException e){
			return false;
		}
		
	}
	/**
	 * Promena jezika aplikacije inicijalizacija komponenta menija
	 * @return void
	 * @see ResourceBundle
	 * @see Locale
	 */
	public void changeLanguage(){
		
		resourceBundle = ResourceBundle.getBundle("MessageResources.MessageResources", Locale.getDefault());
		setTitle(resourceBundle.getString("naslovInfViewer"));
		meni.initComponents();

	}
	
	
	//getteri i setteri
	/**
	 * Postavljanje nove vrednosti parametra tabPanel	 * 
	 * @param tabPanel 
	 * @return void
	 */

	public void setTabPanel(TabPanel tabPanel) {
		this.tabPanelGornji = tabPanel;
	}

	/**
	 * Preuzimanje vrednosti parametra mapaSvihTabela
	 * @return mapaSvihTabela u kojem kljuc predstavlja String,a vrednost Table
	 * @see HashMap
	 */
	public HashMap<String, Table> getMapaSvihTabela() {
		return mapaSvihTabela;
	}

	/**
	 * Postavljanje nove vrednosti parametra mapaSvihTabela	  
	 * @param mapaSvihTabela u kojem kljuc predstavlja String,a vrednost Table
	 * @return void
	 */
	public void setMapaSvihTabela(HashMap<String, Table> mapaSvihTabela) {
		this.mapaSvihTabela = mapaSvihTabela;
	}
	
	/**
	 * Preuzimanje vrednosti komponente skrol klase
	 * @return scroll  
	 * @see JScrollPane
	 * 
	 */
	public JScrollPane getScroll(){
		return scroll;
	}
	/**
	 * Postavljanje nove vrednosti komponente skrol klase
	 * @param scroll  
	 * @return void
	 * @see JScrollPane
	 * 
	 */
	public void setScroll(JScrollPane scroll){
		this.scroll = scroll;
	}
	
	/**
	 * Preuzimanje vrednosti 
	 * @see ResourceBundle
	 * @return resourceBundle 
	 */
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}	
	/**
	 * Postavljanje nove vrednosti 
	 * @see ResourceBundle
	 * @param resourceBundle 
	 */
	public void setResourceBundle(ResourceBundle resourceBundle) {
		this.resourceBundle = resourceBundle;
	}	
	
	/**
	 * Preuzimanje vrednosti komponente stabla
	 * @see Tree
	 * @return tree
	 */
	public Tree getTree(){
		return tree;
	}
	/**
	 * Preuzimanje vrednosti liste ogranicenja stranog kljuca
	 * @see ArrayList
	 * @return listaSvihOgranicenja
	 */
	public ArrayList<ForeignKeyConstraint> getListaSvihOgranicenja() {
		return listaSvihOgranicenja;
	}
	/**
	 * Postavljanje nove vrednosti elemenata liste ogranicenja stranog kljuca
	 * @see ArrayList
	 * @param listaSvihOgranicenja
	 * @return void
	 */
	public void setListaSvihOgranicenja(ArrayList<ForeignKeyConstraint> listaSvihOgranicenja) {
		this.listaSvihOgranicenja = listaSvihOgranicenja;
	}
	/**
	 * Preuzimanje vrednosti liste tabela
	 * @see ArrayList
	 * @return listaSvihTabela
	 */
	public ArrayList<Table> getListaSvihTabela() {
		return listaSvihTabela;
	}

	/**
	 * Postavljanje nove vrednosti elemenata liste tabela
	 * @see ArrayList
	 * @param listaSvihTabela
	 * @return void
	 */
	public void setListaSvihTabela(ArrayList<Table> listaSvihTabela) {
		this.listaSvihTabela = listaSvihTabela;
	}

	/**
	 * Preuzimanje vrednosti liste paketa
	 * @see ArrayList
	 * @return listaSvihPaketa
	 */
	public ArrayList<Packages> getListaSvihPaketa() {
		return listaSvihPaketa;
	}
	/**
	 * Postavljanje nove vrednosti elemenata liste paketa
	 * @see ArrayList
	 * @param listaSvihPaketa
	 * @return void
	 */
	public void setListaSvihPaketa(ArrayList<Packages> listaSvihPaketa) {
		this.listaSvihPaketa = listaSvihPaketa;
	}
	/**
	 * Preuzimanje vrednosti komponente sistem 
	 * 
	 * @return system
	 */
	public Sistem getSystem() {
		return system;
	}
	/**
	 * Postavljanje nove vrednosti komponente sistem 
	 * @param system
	 * @return void
	 */

	public void setSystem(Sistem system) {
		this.system = system;
	}
	/**
	 * Preuzimanje vrednosti komponente BioSamOvde
	 * 
	 * @see ArrayList
	 * @return bioSamOvde
	 */
	public ArrayList<String> getBioSamOvde() {
		return bioSamOvde;
	}

	/**
	 * Postavljanje nove vrednosti komponente bioSamOvde
	 * @param ArrayList<String>
	 * @see ArrayList
	 * @return void
	 */

	public void setBioSamOvde(ArrayList<String> bioSamOvde) {
		this.bioSamOvde = bioSamOvde;
	}
	
	/**
	 * Preuzimanje vrednosti komponente TableById
	 * 
	 * @see ArrayList
	 * @param s
	 * @return odgovarajuci element u suprotnom null
	 */
	public Table getTableById(String s){
		for(int i=0;i<listaSvihTabela.size();i++){
			if(listaSvihTabela.get(i).getIdLabelTable().equals(s)){
				return  listaSvihTabela.get(i);
			}
		}
		
		return null;
	}
	/**
	 * Preuzimanje vrednosti komponente BioSamOvde
	 * @return tabPanelGornji
	 */
	public TabPanel getTabPanel(){
		return tabPanelGornji;
	}
	/**
	 * Preuzimanje vrednosti komponente BioSamOvde
	 * @return tabPanelGornji
	 */
	public TabPanel getTabPanelGornji() {
		return tabPanelGornji;
	}

	/**
	 * Postavljanje nove vrednosti komponente tabPanelGornji
	 * @param TabPanel
	 * @see TabPanel
	 * @return void
	 */
	public void setTabPanelGornji(TabPanel tabPanelGornji) {
		this.tabPanelGornji = tabPanelGornji;
	}
	/**
	 * Preuzimanje vrednosti komponente TabPanelDonji
	 * @return tabPanelDonji
	 */
	public TabPanel getTabPanelDonji() {
		return tabPanelDonji;
	}
	/**
	 * Postavljanje nove vrednosti komponente TabPanelDonji
	 * @param tabPanelDonji
	 * @see TabPanel
	 * @return void
	 */
	public void setTabPanelDonji(TabPanel tabPanelDonji) {
		this.tabPanelDonji = tabPanelDonji;
	}
	/**
	 * Preuzimanje vrednosti komponente Pan
	 * @return pan
	 */
	public Paneli getPan(){
		return pan;
	}
	/**
	 * Preuzimanje vrednosti komponente username
	 * @return username
	 */
	public static String getUsername() {
		return username;
	}
	/**
	 * Postavljanje nove vrednosti komponente username
	 * @param username
	 * @return void
	 */
	public static void setUsername(String username) {
		MyApp.username = username;
	}
	/**
	 * Preuzimanje vrednosti komponente password
	 * @return password;
	 */
	public static String getPassword() {
		return password;
	}
	/**
	 * Postavljanje nove vrednosti komponente password
	 * @param password;
	 * @return void
	 */
	public static void setPassword(String password) {
		MyApp.password = password;
	}
	/**
	 * Preuzimanje vrednosti komponente connectionString 
	 * @return connectionString
	 */
	public static String getConnectionString() {
		return connectionString;
	}
	/**
	 * Postavljanje nove vrednosti komponente connectionString
	 * @return void
	 */
	public static void setConnectionString(String connectionString) {
		MyApp.connectionString = connectionString;
	}
	/**
	 * Postavljanje nove vrednosti komponente jsonFile
	 * @return void
	 */
	public void setJsonFile(String jsonFile) {
		this.jsonFile = "files/"+jsonFile;
	}
	/**
	 * Preuzimanje vrednosti komponente odaberi
	 * @return odaberi
	 */
	public String getOdaberi() {
		return odaberi;
	}
	/**
	 * Postavljanje nove vrednosti komponente odaberi
	 * @return void
	 */
	public void setOdaberi(String odaberi) {
		this.odaberi = odaberi;
	}
	
	/**
	 * Preuzimanje vrednosti komponente addDugmeGornje
	 * @return addDugmeGornje
	 */
	
	public JButton getAddDugmeGornje() {
		return addDugmeGornje;
	}

	/**
	 * Postavljanje nove vrednosti komponente addDugmeGornje
	 * @return void
	 */
	
	public void setAddDugmeGornje(JButton addDugmeGornje) {
		this.addDugmeGornje = addDugmeGornje;
	}

	/**
	 * Preuzimanje vrednosti komponente addDugmeDonje
	 * @return addDugmeDonje
	 */
	
	public JButton getAddDugmeDonje() {
		return addDugmeDonje;
	}

	/**
	 * Postavljanje nove vrednosti komponente addDugmeDonje
	 * @return void
	 */
	
	public void setAddDugmeDonje(JButton addDugmeDonje) {
		this.addDugmeDonje = addDugmeDonje;
	}

	/**
	 * Preuzimanje vrednosti komponente listaStranihKljuceva
	 * @return listaStranihKljuceva
	 */
	
	public ArrayList<FK> getListaStranihKljuceva() {
		return listaStranihKljuceva;
	}

	/**
	 * Postavljanje nove vrednosti komponente odalistaStranihKljucevaberi
	 * @return void
	 */
	
	public void setListaStranihKljuceva(ArrayList<FK> listaStranihKljuceva) {
		this.listaStranihKljuceva = listaStranihKljuceva;
	}

	/**
	 * Preuzimanje vrednosti komponente listaKorisnika
	 * @return listaKorisnika
	 */
	
	public ArrayList<Korisnici> getListaKorisnika() {
		return listaKorisnika;
	}

	/**
	 * Postavljanje nove vrednosti komponente listaKorisnika
	 * @return void
	 */
	
	public void setListaKorisnika(ArrayList<Korisnici> listaKorisnika) {
		this.listaKorisnika = listaKorisnika;
	}

	/**
	 * Preuzimanje vrednosti komponente pocetniDijalog
	 * @return pocetniDijalog
	 */
	
	public PocetniDijalog getPocetniDijalog() {
		return pocetniDijalog;
	}

	/**
	 * Postavljanje nove vrednosti komponente pocetniDijalog
	 * @return void
	 */
	
	public void setPocetniDijalog(PocetniDijalog pocetniDijalog) {
		this.pocetniDijalog = pocetniDijalog;
	}

	/**
	 * Preuzimanje vrednosti komponente updateDugmeGornje
	 * @return updateDugmeGornje
	 */
	
	public JButton getUpdateDugmeGornje() {
		return updateDugmeGornje;
	}

	/**
	 * Postavljanje nove vrednosti komponente updateDugmeGornje
	 * @return void
	 */
	
	public void setUpdateDugmeGornje(JButton updateDugmeGornje) {
		this.updateDugmeGornje = updateDugmeGornje;
	}
	
	/**
	 * Preuzimanje vrednosti komponente updateDugmeDonje
	 * @return updateDugmeDonje
	 */

	public JButton getUpdateDugmeDonje() {
		return updateDugmeDonje;
	}

	/**
	 * Postavljanje nove vrednosti komponente updateDugmeDonje
	 * @return void
	 */
	
	public void setUpdateDugmeDonje(JButton updateDugmeDonje) {
		this.updateDugmeDonje = updateDugmeDonje;
	}
	
	
}
