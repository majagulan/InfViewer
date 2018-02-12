package parsing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import gui.MyApp;
import model.Column;
import model.FK;
import model.ForeignKeyConstraint;
import model.Packages;
import model.Relations;
import model.Sistem;
import model.Table;

/**
 * Klasa koja omogucava formiranje meta seme na osnovu popunjenih tabela iz baze podataka na 
 * SQL serveru. Formiraju se paketi u {@link parsirajPakete}, tabele {@link parsirajTabele} 
 * i odnosi izmedju tabela {@link parsirajRelacije}.
 */

public class DataBaseParser {
	
	/**
	 * Konekcija ka bazi podataka koja se dobija iz {@link gui.MyApp MyApp} klase.
	 */
	
	private  Connection connection;
	
	/**
	 * Interface za uvodjenje parametara u SQL naredbu i pokretanja upita.
	 *  Objekat mu se pravi preko objekta {@link connection}.
	 */
	
	private PreparedStatement prep_st;	
										
	/**
	 * Konstruktor bez parametara.
	 */
	
	public DataBaseParser(){
		
		connection = MyApp.getConnection();
	}

	/**
	 * Poziva pojedinacne klase za parsiranje svake komponente odvojeno: paketa, tabela 
	 * i odnosa izmedju tabela (relacija).
	 * @return void
	 */
	
	public void parsiraj() {		
		
		parsirajPakete();
		parsirajTabele();
		parsirajRelacije();
		
	}
	
	/**
	 * Vraca boolean vrednost, da li je neka lista vec kreirana. Metoda prima bilo koju prosledjenu listu.
	 * @see ArrayList
	 * @param id
	 * @param lista
	 * @return ret
	 */
	
	public boolean vecPostoji(String id, ArrayList lista){
		boolean ret = false;
		for(int i=0;i<lista.size();i++){
			if(((Packages)lista.get(i)).getIdPackage().equals(id)){
				ret = true;
				break;
			}
		}
		
		return ret;
	}
	
	/**
	 * Vraca boolean vrednost, da li je neka tabela vec kreirana. Metoda prima bilo koju prosledjenu listu.
	 * @see ArrayList
	 * @param id
	 * @param lista
	 * @return ret
	 */
	
	public boolean vecPostojiTabela(String id,ArrayList lista){
		boolean ret=false;
		
		for(int i=0;i<lista.size();i++){
			if(lista.get(i).equals(id)){
				ret = true;
				break;
			}
		}
		
		return ret;
	}
	
	/**
	 * Omogucava ucitavanje paketa iz baze podataka i 
	 * njihovo razvrstavanje u hijerarhiju stabla.
	 * @throws SQLException
	 * @return void
	 */
	
	public void parsirajPakete() {
		
		ResultSet rezultat;
		
    	String podsistem = "SELECT * FROM PODSISTEM";
    	String strukturaPodsistema = "SELECT * FROM STRUKTURA_PODSISTEMA";
    	
    	
    	try {
			prep_st = connection.prepareStatement(podsistem);
			rezultat = prep_st.executeQuery();
			
			
			while(rezultat.next()){
				
				String oznakaPodsistema = rezultat.getString("PO_OZNAKA");
				String naziv  = rezultat.getString("PO_NAZIV");
				String tip  = rezultat.getString("PO_TIP");
				
				
				if(tip.equals("S")){
					Sistem s = new Sistem(oznakaPodsistema,naziv,tip,new ArrayList<>());
					MyApp.getInstance().setSystem(s);
					if(!vecPostoji(oznakaPodsistema,MyApp.getInstance().getListaSvihPaketa())){
					Packages p = new Packages(naziv,oznakaPodsistema,tip);
					MyApp.getInstance().getListaSvihPaketa().add(p);
					}
				}else if(tip.equals("P")){
					if(!vecPostoji(oznakaPodsistema,MyApp.getInstance().getListaSvihPaketa())){
						Packages p = new Packages(naziv,oznakaPodsistema,tip);
						MyApp.getInstance().getListaSvihPaketa().add(p);
					}
				}else if(tip.equals("K")){
					if(!vecPostoji(oznakaPodsistema,MyApp.getInstance().getListaSvihPaketa())){
						Packages p = new Packages(naziv,oznakaPodsistema,tip);
						MyApp.getInstance().getListaSvihPaketa().add(p);
						}
				}
				
				

			}
			
		} catch (SQLException e1) {
		}
    	
    	try {
			prep_st = connection.prepareStatement(strukturaPodsistema);
			rezultat = prep_st.executeQuery();
		
			while(rezultat.next()){
				
				String nadredjeniPodsistem = rezultat.getString("PO_OZNAKA"); 
				String podredjeniPodsistem  = rezultat.getString("POD_PO_OZNAKA");
				
				
				for(int i=0;i<MyApp.getInstance().getListaSvihPaketa().size();i++){
					if(MyApp.getInstance().getListaSvihPaketa().get(i).getIdPackage().equals(podredjeniPodsistem)){
						MyApp.getInstance().getListaSvihPaketa().get(i).setKomePripada(nadredjeniPodsistem);
						
					}
				}
				
				for(int i=0;i<MyApp.getInstance().getListaSvihPaketa().size();i++){
					
					if(MyApp.getInstance().getListaSvihPaketa().get(i).getIdPackage().equals(nadredjeniPodsistem) && 
							MyApp.getInstance().getListaSvihPaketa().get(i).getType().equals("S"))
					{
							if(!vecPostoji(podredjeniPodsistem,MyApp.getInstance().getSystem().getListaPaketaUSistemu())
									&& !vecPostoji(podredjeniPodsistem,MyApp.getInstance().getListaSvihPaketa().get(i).getSubPackages())){
								MyApp.getInstance().getListaSvihPaketa().get(i).getSubPackages().add(MyApp.getInstance().findPackageById(podredjeniPodsistem));
								MyApp.getInstance().getSystem().getListaPaketaUSistemu().add(MyApp.getInstance().findPackageById(podredjeniPodsistem));
							}//dodajem podsistem u sistem
						
					
					}
				}
				
			}
		} catch (SQLException e1) {
		}
    	
	}
	
	/**
	 * <p> Omogucava ucitavanje tabela iz baze podataka i 
	 * njihovo razvrstavanje u hijerarhiju stabla. </p>
	 * <p> Izvrsava upite nad tabelama i popunjava atribute svake tabele.</p>
	 * @throws SQLException
	 * @return void
	 */
	
	public void parsirajTabele(){
		
		ResultSet rezultat;
		
    	String tabele = "SELECT * FROM TABELE";
    	String atributi = "SELECT * FROM ATRIBUTI";
    	
    	try {
			prep_st = connection.prepareStatement(tabele);
			rezultat = prep_st.executeQuery();
			
			while(rezultat.next()){
			
				String oznakaPodpaketaKomTabelaPripada = rezultat.getString("PO_OZNAKA");
				String kodTabele = rezultat.getString("TAB_KOD");
				String nazivTabele =  rezultat.getString("TAB_NAZIV");
				
				Table t = new Table(new ArrayList<>(),nazivTabele,kodTabele,oznakaPodpaketaKomTabelaPripada);
				MyApp.getInstance().getListaSvihTabela().add(t);
				
				for(int i=0;i<MyApp.getInstance().getListaSvihPaketa().size();i++){
					if(MyApp.getInstance().getListaSvihPaketa().get(i).getIdPackage().equals(oznakaPodpaketaKomTabelaPripada)){
						MyApp.getInstance().getListaSvihPaketa().get(i).getChildrenTables().add(t);
					}
				}
				//if(!vecPostojiTabela(t.getIdLabelTable())){
				MyApp.getInstance().getSystem().getListaSvihTabelaUSistemu().add(t);
				//}
				
			}
			
		} catch (SQLException e1) {
		}
    	
    	try {
			prep_st = connection.prepareStatement(atributi);
			rezultat = prep_st.executeQuery();
			//parsiranje kolona
			while(rezultat.next()){
				
				String podsistem = rezultat.getString("PO_OZNAKA");//jel ovo paket kome
				//tabela pripada ?
				String kodTabele = rezultat.getString("TAB_KOD");
				String kodAtributa = rezultat.getString("ATR_KOD");
				String labela =  rezultat.getString("ATR_LABELA");
				String tip =  rezultat.getString("ATR_TIP");
				Boolean obavezno =  rezultat.getBoolean("ATR_OBAVEZNO");
				Integer duzina =  rezultat.getInt("ATR_DUZINA");
				Integer preciznost = rezultat.getInt("ATR_PRECIZNOST");
				Boolean deoPrimarnogKljuca = rezultat.getBoolean("ATR_DEO_PK");
				//System.out.println("TIP:"+tip+" koja kolona"+kodAtributa);
				System.out.println("TABELA"+kodTabele+"KOD ATRIBUTA"+labela);
				//ovde sam obrisala dodavanje u listu paketa
				
				for(int i=0;i<MyApp.getInstance().getSystem().getListaPaketaUSistemu().size();i++){
					if(MyApp.getInstance().getSystem().getListaPaketaUSistemu().get(i).getIdPackage().equals(podsistem)){
						for(int j=0;j<MyApp.getInstance().getSystem().getListaPaketaUSistemu().get(i).getChildrenTables().size();j++){
							if(MyApp.getInstance().getSystem().getListaPaketaUSistemu().get(i).getChildrenTables().get(j).getIdLabelTable().equals(kodTabele)){
								
								Column c = new Column(labela,kodAtributa,tip,preciznost,duzina,deoPrimarnogKljuca,obavezno);//ovde vidi dal je id ili name da
								//se prikaze
								MyApp.getInstance().getSystem().getListaPaketaUSistemu().get(i).getChildrenTables().get(j).getListOfColumns().add(c);
							}
						}
					}
				}
	
			}
			
		} catch (SQLException e1) {
		}
	}
	
	/**
	 * Metoda koja popunjava odnose izmedju tabela. Koriste se tabele iz baze koje 
	 * sadrze informacije o parent tabelama, child tabelama i atributima.
	 * 
	 * <p>Popunjavaju se samo tabele koju su u odnosu sa praznim listama kolona.
	 * Popunjava se ko je kome od tabela dete, a ko kome roditelj.
	 * Za prikaz u stablu, izdvaja ako su jake veze da se samo to prikaze, a puni liste stranih kljuceva
	 * za svaku tabelu bez obzira da li je jaka ili slaba.
	 * Razdvaja jake i slabe zavisnosti koriscenjem stranog kljuca. </p>
	 * @throws SQLException
	 * @return void
	 */
	
	public void parsirajRelacije(){
		
		ResultSet rezultat;
			
	    	String straniKljuc = "SELECT * FROM STRANI_KLJUC";
	    	String koloneUStranomKljucu = "SELECT * FROM KOLONE_U_STRANOM_KLJUCU";
	    	
	//ovo popunjava samo tabele koje su u odnosu sa praznim listama kolona    	
	    	try {
				prep_st = connection.prepareStatement(straniKljuc);
				rezultat = prep_st.executeQuery();
				
				while(rezultat.next()){				 
					
					String podsistemRoditelj = rezultat.getString("PO_OZNAKA");
    				String roditeljskaTabela = rezultat.getString("TAB_KOD");
    				String podsistemPotomak = rezultat.getString("TAB_PO_OZNAKA");
    				String tabelaPotomak = rezultat.getString("TAB_TAB_KOD");
    				String kod =  rezultat.getString("SK_KOD");
    				String labela =  rezultat.getString("SK_LABELA");
    				String jakaVeza =  rezultat.getString("SK_JAKA_VEZA");
    				
    				
    				boolean jeZavistan = false;
    				if(jakaVeza.equals("1")){
    					jeZavistan = true;
    				}else{
    					jeZavistan = false;
    				}
    				System.out.println("JACINA VEZE:"+jeZavistan);
    				ForeignKeyConstraint fk = new ForeignKeyConstraint(kod,labela,roditeljskaTabela,tabelaPotomak,new ArrayList<>(),jeZavistan);
    				/*		
    				-ove dve petlje sluze da se popuni ko je kome od tabela dete ko kome roditelj 
    				-za prikaz u stablu, izdvaja ako su jake veze da se samo to prikaze, a puni liste stranih
    				kljuceva za svaku tabelu bez obzira da li je jaka ili slaba
    				-parentFK ciji strani kljuc sadrzi
    				-childFK kome propagira svoj strani kljuc
    				 */
    				 
    				for(int i=0;i<MyApp.getInstance().getSystem().getListaSvihTabelaUSistemu().size();i++){
    				
					if(MyApp.getInstance().getSystem().getListaSvihTabelaUSistemu().get(i).getIdLabelTable().equals(tabelaPotomak)){
						MyApp.getInstance().getSystem().getListaSvihTabelaUSistemu().get(i).getParentFKC().add(fk);
						
    					if(jeZavistan){
						if(!vecPostojiTabela(roditeljskaTabela,MyApp.getInstance().getSystem().getListaSvihTabelaUSistemu().get(i).getParentsOfTable())){
						MyApp.getInstance().getSystem().getListaSvihTabelaUSistemu().get(i).getParentsOfTable().add(MyApp.getInstance().findTableById(roditeljskaTabela));
						}
    					}
					}else if(MyApp.getInstance().getSystem().getListaSvihTabelaUSistemu().get(i).getIdLabelTable().equals(roditeljskaTabela) && jeZavistan){
						MyApp.getInstance().getSystem().getListaSvihTabelaUSistemu().get(i).getChildFKC().add(fk);
						
						if(jeZavistan){
						if(!vecPostojiTabela(tabelaPotomak,MyApp.getInstance().getSystem().getListaSvihTabelaUSistemu().get(i).getChildOfTable())){
						MyApp.getInstance().getSystem().getListaSvihTabelaUSistemu().get(i).getChildOfTable().add(MyApp.getInstance().findTableById(tabelaPotomak));
						}
						}
					}
				}
    				
    				
				
				
				
				}
				
			
				//ovo popunjava koje se kolone medjusobno referenciraju
	      	try {
	    			prep_st = connection.prepareStatement(koloneUStranomKljucu);
	    			rezultat = prep_st.executeQuery();
	    			
	    			while(rezultat.next()){
	    			
	    				String podsistemRoditelj = rezultat.getString("PO_OZNAKA");
	    				String roditeljskaTabela = rezultat.getString("TAB_KOD");
	    				String podsistemPotomak = rezultat.getString("ATR_PO_OZNAKA2");
						String tabelaPotomak = rezultat.getString("ATR_TAB_KOD");
						String kodStranogKljuca =  rezultat.getString("SK_KOD");
						String kodAtributaRoditeljske =  rezultat.getString("ATR_KOD");
						String kodAtributaPotomka =  rezultat.getString("ATR_ATR_KOD");
						
						Relations relation = new Relations(kodAtributaRoditeljske,kodAtributaPotomka,kodStranogKljuca);
						FK fk = new FK(roditeljskaTabela,tabelaPotomak,kodAtributaRoditeljske,kodAtributaPotomka);
						System.out.println("STRANI KLJUCEVI"+fk);
						if(!postoji(roditeljskaTabela,tabelaPotomak,kodAtributaRoditeljske,kodAtributaPotomka)){
							MyApp.getInstance().getListaStranihKljuceva().add(fk);
						}}}
						//za svaku tabelu prolazimo kroz liste stranih kljuceva i popunjavamo kolone koje se referenciraju
						/*for(int i=0;i<MyApp.getInstance().getSystem().getListaSvihTabelaUSistemu().size();i++){
							if(MyApp.getInstance().getSystem().getListaSvihTabelaUSistemu().get(i).getParentFKC().size()!=0){
								for(int j=0;j<MyApp.getInstance().getSystem().getListaSvihTabelaUSistemu().get(i).getParentFKC().size();j++){
									if(MyApp.getInstance().getSystem().getListaSvihTabelaUSistemu().get(i).getParentFKC().get(j).getParentTableName().equals(roditeljskaTabela)){
										
										MyApp.getInstance().getSystem().getListaSvihTabelaUSistemu().get(i).getParentFKC().get(j).getColumnFKList().add(relation);
									}
								}
							}
							if(MyApp.getInstance().getSystem().getListaSvihTabelaUSistemu().get(i).getChildFKC().size()!=0){
									for(int k=0;k<MyApp.getInstance().getSystem().getListaSvihTabelaUSistemu().get(i).getChildFKC().size();k++){
							
										if(MyApp.getInstance().getSystem().getListaSvihTabelaUSistemu().get(i).getChildFKC().get(k).getChildTableName().equals(tabelaPotomak)){
											
											MyApp.getInstance().getSystem().getListaSvihTabelaUSistemu().get(i).getChildFKC().get(k).getColumnFKList().add(relation);
										}	
									}
								}
							
						}
	    			}
	    		} */catch (SQLException e1) {
	    		}
	    	}
	    catch (SQLException e1) {
			}
		}
	
	/**
	 * Proverava da li postoji vec trazena kolona.
	 * @param rodTab
	 * @param childTab
	 * @param rodCol
	 * @param childCol
	 * @return
	 */
	
	public boolean postoji(String rodTab,String childTab,String rodCol,String childCol){
		boolean ret = false;
		for(int i =0;i<MyApp.getInstance().getListaStranihKljuceva().size();i++){
			if(MyApp.getInstance().getListaStranihKljuceva().get(i).getParentTable().trim().equals(rodTab.trim()) &&
			   MyApp.getInstance().getListaStranihKljuceva().get(i).getChildTable().trim().equals(childTab.trim()) &&
			   MyApp.getInstance().getListaStranihKljuceva().get(i).getParentColumn().trim().equals(rodCol.trim()) &&
			   MyApp.getInstance().getListaStranihKljuceva().get(i).getChildColumn().trim().equals(childCol.trim())){
				ret = true;
				break;
			}
		}
		return ret;
	}
}
	
