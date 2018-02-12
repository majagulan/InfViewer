package parsing;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import gui.MyApp;
import model.Column;
import model.FK;
import model.ForeignKeyConstraint;
import model.Packages;
import model.Relations;
import model.Sistem;
import model.Table;

/**
 * Klasa koja parsira prethodno odabrani JSON fajl. 
 * Ona omogucava kreiranje tabela i relacija izmedju tabela.
 */

public class JSONParser {

	public static boolean nemojSnimiti = false;
	private String file;
	private JSONArray paket = new JSONArray();
	public JSONParser(){
		
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente file.
	 *  @param fileName
	 *  @return void
	 */
	
	public void setJsonFile(String fileName){
		this.file = new String(fileName);
	}
	
	/**
	 * Ucitava JSON fajl koji je odabran i objedinjavanje
	 * parsiranja tabela i ogranicenja izmedju njih.
	 * @throws Exception
	 * @return void
	 */
			
	public void parseJson() {
			try {
					
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF8"));
				JSONTokener tokener = new JSONTokener(br);
				JSONObject o = new JSONObject(tokener);
				br.close();
				
				parsirajCvor(o);
				
	
				System.out.println("Validan je!!!");		
			
			} catch (Exception e) {
				
			}		
		}
	
	/**
	 * Ucitava JSON fajl koji je odabran i objedinjavanje
	 * parsiranja tabela i ogranicenja izmedju njih.
	 * Ova metoda se poziva samo kada se trazi validacija editora JSON seme.
	 * @throws Exception
	 * @return void
	 */
	
	public void parseJsonIzKlaseValidate() {
		
		try {
				
			JSONTokener tokener = new JSONTokener(file);
			JSONObject o = new JSONObject(tokener);
			
			
			parsirajCvor(o);
			
			System.out.println("Validan je!");		
			JOptionPane.showMessageDialog(null, MyApp.getInstance().getResourceBundle().getString("porValidan"));
				
		} catch (Exception e) {
			System.out.println("Nevalidan je!");
			JOptionPane.showMessageDialog(null, MyApp.getInstance().getResourceBundle().getString("porNevalidan"));
		}		
	}
	
	/**
	 * Ucitava JSON fajl koji je odabran i objedinjavanje
	 * parsiranja tabela i ogranicenja izmedju njih.
	 * Ova metoda se poziva samo kada se trazi snimanje promena u editoru JSON seme.
	 * @throws Exception
	 * @return void
	 */
	
	public void parseJsonIzKlaseSave() {
		try {
				
			JSONTokener tokener = new JSONTokener(file);
			JSONObject o = new JSONObject(tokener);
			
		
			parsirajCvor(o);
	
			System.out.println("Validan i snimljen!");
			JOptionPane.showMessageDialog(null, MyApp.getInstance().getResourceBundle().getString("porSnimljen"));

			
		} catch (Exception e) {
			System.out.println("Nevalidan i nije snimljen!");
			JOptionPane.showMessageDialog(null, MyApp.getInstance().getResourceBundle().getString("porNijeSnimljen"));
			nemojSnimiti = true;
		}		
	}
	
	/**
	 * Vrši konkretno parsiranje JSON objekta, dobijenog iz JSON fajla tako sto prvo parsira cvor.
	 *
	 * @param jsonObject - JSON objekat koji sadrži celu pocetnu strukturu JSON fajla. 
	 * @return void
	 */
			
	public void parsirajCvor(JSONObject jsonObject){
		paket = jsonObject.getJSONArray("package");//ovo je onaj sto sadrzi psw
		MyApp.getInstance().setSystem(new Sistem());
		MyApp.getInstance().setListaSvihPaketa(new ArrayList<>());
		MyApp.getInstance().setListaSvihTabela(new ArrayList<>());
		MyApp.getInstance().setBioSamOvde(new ArrayList<>());
		

		for(int i = 0; i<paket.length();i++) {
			String name = ((JSONObject) paket.get(i)).getString("packageName");			
			String labela = ((JSONObject) paket.get(i)).getString("labela");	
			String tip = ((JSONObject) paket.get(i)).getString("type");
			String komePripada = ((JSONObject) paket.get(i)).getString("komePripada");
			System.out.println(name);
			Packages p = new Packages(name,labela,tip,komePripada);
			boolean g = false;
			for(int j=0;j<MyApp.getInstance().getListaSvihPaketa().size();j++){
				if(MyApp.getInstance().getListaSvihPaketa().get(j).getIdPackage().equals(p.getIdPackage())){
					g = true;
				}
			}
			if(!g){
			MyApp.getInstance().getListaSvihPaketa().add(p);//lista svih sistema,podsistema i paketa
			}
			if(tip.equals("system")){
				Sistem s = new Sistem(name,
						labela,tip,
						new ArrayList<>());
				MyApp.getInstance().setSystem(s);
				
				
			}		

			if(((JSONObject) paket.get(i)).length()>1){
				parsirajPodsistem((JSONObject) paket.get(i));
			}
			
		}
	}
	
	/**
	 * Vrši konkretno parsiranje JSON objekta, dobijenog iz JSON fajla.
	 * Parsira podsistem (pakete).
	 *
	 * @param jsonObject - JSON objekat koji sadrži celu pocetnu strukturu JSON fajla. 
	 * @return void
	 */
		
	public void parsirajPodsistem(JSONObject jsonObject){
		
		JSONArray paket1 = jsonObject.getJSONArray("package");//ovo je psw

		for(int i = 0; i<paket1.length();i++) {
			String name = ((JSONObject) paket1.get(i)).getString("packageName");			
			String labela = ((JSONObject) paket1.get(i)).getString("labela");	
			String tip = ((JSONObject) paket1.get(i)).getString("type");
			String komePripada = ((JSONObject) paket1.get(i)).getString("komePripada");
			Packages p = new Packages(name,labela,tip,komePripada);
			boolean f = false;
			for(int j=0;j<MyApp.getInstance().getListaSvihPaketa().size();j++){
				if(MyApp.getInstance().getListaSvihPaketa().get(j).getIdPackage().equals(p.getIdPackage())){
					f = true;
				}
			}
			if(!f){
			MyApp.getInstance().getListaSvihPaketa().add(p);
			}//lista svih sistema,podsistema i paketa
		
			if(((JSONObject) paket1.get(i)).length()>1){
			parsirajPaket((JSONObject) paket1.get(i));
			}
			
		}
		
	}
	
	/**
	 * Vrši konkretno parsiranje JSON objekta, dobijenog iz JSON fajla, 
	 * u ovoj metodi ce parsirati pakete.
	 *
	 * @param jsonObject - JSON objekat koji sadrži celu pocetnu strukturu JSON fajla. 
	 * @return void
	 */

	public void parsirajPaket(JSONObject jsonObject) {
			boolean postoji = false;
			if(jsonObject.has("package")){
				paket = jsonObject.getJSONArray("package");
				
				for(int i = 0; i<paket.length();i++) {
					String name = ((JSONObject) paket.get(i)).getString("packageName");			
					String labela = ((JSONObject) paket.get(i)).getString("labela");	
					String tip = ((JSONObject) paket.get(i)).getString("type");
					String komePripada = ((JSONObject) paket.get(i)).getString("komePripada");
				
					Packages p = new Packages(name,labela,tip,komePripada);
					for(int k=0;k<MyApp.getInstance().getListaSvihPaketa().size();k++){
						if(name.equals(MyApp.getInstance().getListaSvihPaketa().get(k).getIdPackage())){
							postoji = true;
						}
					}
					if(!postoji){
					MyApp.getInstance().getListaSvihPaketa().add(p);//lista svih sistema,podsistema i paketa
					}
					parsirajPaket((JSONObject) paket.get(i));
					
				}
							
		
			}else{//punim listu svih paketa
		
				
				
				for(int i =0;i<MyApp.getInstance().getListaSvihPaketa().size();i++){
					
					if(MyApp.getInstance().getListaSvihPaketa().get(i).getType().equals("system")){
						for(int j =0;j<MyApp.getInstance().getListaSvihPaketa().size();j++){
							if(MyApp.getInstance().getListaSvihPaketa().get(j).getType().equals("subsystem") && MyApp.getInstance().getListaSvihPaketa().get(j).getKomePripada().equals(MyApp.getInstance().getListaSvihPaketa().get(i).getIdPackage())){
								boolean postoji3= false;
								boolean postoji4 = false;
								for(int r =0;r<MyApp.getInstance().getSystem().getListaPaketaUSistemu().size();r++){
									if(MyApp.getInstance().getSystem().getListaPaketaUSistemu().get(r).getIdPackage().equals(MyApp.getInstance().getListaSvihPaketa().get(j).getIdPackage()))
										postoji3 = true;
								}
								
								for(int s =0;s<MyApp.getInstance().getListaSvihPaketa().get(i).getSubPackages().size();s++){
									if(MyApp.getInstance().getListaSvihPaketa().get(i).getSubPackages().get(s).getIdPackage().equals(MyApp.getInstance().getListaSvihPaketa().get(j).getIdPackage()))
										postoji4 = true;
								}
								if(!postoji3 && !postoji4){
								MyApp.getInstance().getListaSvihPaketa().get(i).getSubPackages().add(MyApp.getInstance().getListaSvihPaketa().get(j));
								MyApp.getInstance().getSystem().getListaPaketaUSistemu().add(MyApp.getInstance().getListaSvihPaketa().get(j));
								}
							}
						}
					}
						
				
				else if(MyApp.getInstance().getListaSvihPaketa().get(i).getType().equals("subsystem")){
				for(int j =0;j<MyApp.getInstance().getListaSvihPaketa().size();j++){
				   boolean postoji1 = false;
					if(MyApp.getInstance().getListaSvihPaketa().get(j).getType().equals("package") && MyApp.getInstance().getListaSvihPaketa().get(j).getKomePripada().equals(MyApp.getInstance().getListaSvihPaketa().get(i).getIdPackage())){
					  for(int k=0;k<MyApp.getInstance().getListaSvihPaketa().get(i).getSubPackages().size();k++){
						  if(MyApp.getInstance().getListaSvihPaketa().get(i).getSubPackages().get(k).getIdPackage().equals(MyApp.getInstance().getListaSvihPaketa().get(j).getIdPackage())){
							  postoji1 = true;
						  }
					  }
					  if(!postoji1){
					  MyApp.getInstance().getListaSvihPaketa().get(i).getSubPackages().add(MyApp.getInstance().getListaSvihPaketa().get(j));
					  }
					  }
					  
				  	
				}
				
			}else if(MyApp.getInstance().getListaSvihPaketa().get(i).getType().equals("package")){
				boolean postoji5 = false;
				for(int g = 0;g<MyApp.getInstance().getBioSamOvde().size();g++){
					if(MyApp.getInstance().getBioSamOvde().get(g).equals(MyApp.getInstance().getListaSvihPaketa().get(i).getIdPackage()))
						postoji5 = true;
				
				}
				if(!postoji5){
				MyApp.getInstance().getBioSamOvde().add(MyApp.getInstance().getListaSvihPaketa().get(i).getIdPackage());
				parsirajTabelu(jsonObject);
				parsirajRelacije(jsonObject);
				return;
				}
			}
			}
		}
	
	}
	
	/**
	 * Formiranje tabela sa svojima atributima, njihovim tipovima i ogranicenjima, 
	 * kao i ucitavanje primarnih kljuceva tabela.
	 * <p> JSON objekta se daljim parsiranjem dobijaju se tabele, njeni atributi
	 * i primarni ključevi </p>
	 * 
	 * @param jsonObject - JSON objekat koji sadrži celu pocetnu strukturu JSON fajla. 
	 * @return void
	 */
		
	public void parsirajTabelu(JSONObject jsonObject){
		JSONArray listaTabela = jsonObject.getJSONArray("tables");				
		
		//tabela pocetak
		for(int i2 = 0; i2<listaTabela.length(); i2++) {
			
			String name = ((JSONObject) listaTabela.get(i2)).getString("name");				
			String labela = ((JSONObject) listaTabela.get(i2)).getString("labela");	
			String komePripada = ((JSONObject) listaTabela.get(i2)).getString("komePripada");	
			JSONArray listaKolona = ((JSONObject) listaTabela.get(i2)).getJSONArray("columns");
			
			Table t = new Table(new ArrayList<Column>(),name,labela,komePripada);
			
			for(int j3 = 0; j3<listaKolona.length(); j3++) {
				
				String nazivKolone = ((JSONObject)listaKolona.get(j3)).getString("name");
				String labelaKolone = ((JSONObject)listaKolona.get(j3)).getString("labela");
				String tipKolone = ((JSONObject)listaKolona.get(j3)).getString("dataType");
				int preciznost = ((JSONObject)listaKolona.get(j3)).getInt("precision");
				int duzinaKolone = ((JSONObject)listaKolona.get(j3)).getInt("length");
				
				String primerniKljuc = ((JSONObject)listaKolona.get(j3)).getString("primary key");
				boolean JePrimarni = (primerniKljuc.equals("true"))? true : false;
				
				String nulaVrednost = ((JSONObject)listaKolona.get(j3)).getString("nullValue");
				boolean jeNula = (nulaVrednost.equals("true"))? true : false;
				
				Column c = new Column(nazivKolone, labelaKolone, tipKolone, preciznost , duzinaKolone ,JePrimarni,jeNula);
				t.addColumn(c);
				
			}	//kolone kraj
			
			
			MyApp.getInstance().addToListOfTables(t);
			
			MyApp.getInstance().getSystem().getListaSvihTabelaUSistemu().add(t);
		} //kraj tabele
	}
	
	/**
	 * Metoda koja vrsi konkretno parsiranje JSON objekta, dobijenog iz JSON fajla,
	 * gdje se ucitavaju ogranicenja i iz njih formiraju jake i slabe veze između tabela.
	 *
	 * @param jsonObject - JSON objekat koji sadrži cijelu početnu strukturu JSON fajla.
	 * @return void
	 */
	
	public void parsirajRelacije(JSONObject jsonObject) {
	
				JSONArray listaRelacija = jsonObject.getJSONArray("relations");	
				
				//pocetak relacija
				for(int i6 = 0; i6<listaRelacija.length(); i6++){
					
					String nazivRelacije = ((JSONObject)listaRelacija.get(i6)).getString("name");
					String labelaRelacije = ((JSONObject)listaRelacija.get(i6)).getString("labela");
					
					String roditeljTabela = ((JSONObject) listaRelacija.get(i6)).getString("parentTable");
					String potomakTabela = ((JSONObject) listaRelacija.get(i6)).getString("childTable");
					
					
					ForeignKeyConstraint fk = new ForeignKeyConstraint(nazivRelacije, labelaRelacije,roditeljTabela,potomakTabela,new ArrayList<Relations>(),false);
					JSONArray listaVezaUKolonama = ((JSONObject) listaRelacija.get(i6)).getJSONArray("relationship");
					
					//pocetak lista veza u kolonama
					for(int j = 0; j<listaVezaUKolonama.length(); j++) {	
						
						String roditeljKolona = ((JSONObject)listaVezaUKolonama.get(j)).getString("parentColumn");
						String potomakKolona = ((JSONObject)listaVezaUKolonama.get(j)).getString("childColumn");
						Relations r  = new Relations(roditeljKolona,potomakKolona,nazivRelacije);
						fk.getColumnFKList().add(r);
					    MyApp.getInstance().getListaSvihOgranicenja().add(fk);
					    FK fkk = new FK(roditeljTabela,potomakTabela,roditeljKolona,potomakKolona);
					    MyApp.getInstance().getListaStranihKljuceva().add(fkk);
					    System.out.println(fkk);
					    

					}	//kraj lista veza u kolonama
					
					
					String zavisnost = ((JSONObject)listaRelacija.get(i6)).getString("dependent");
					boolean jeZavistan;
					if(zavisnost.equals("true")){
						jeZavistan = true;
						fk.setJeZavistan(true);
					}else{
						jeZavistan = false;
						fk.setJeZavistan(false);
					}
					
					for(int i=0;i<MyApp.getInstance().getSystem().getListaSvihTabelaUSistemu().size();i++){
						if(MyApp.getInstance().getSystem().getListaSvihTabelaUSistemu().get(i).getIdLabelTable().equals(potomakTabela) && jeZavistan){
							MyApp.getInstance().getSystem().getListaSvihTabelaUSistemu().get(i).getParentsOfTable().add(MyApp.getInstance().findTableById(roditeljTabela));
						}else if(MyApp.getInstance().getSystem().getListaSvihTabelaUSistemu().get(i).getIdLabelTable().equals(roditeljTabela) && jeZavistan){
							MyApp.getInstance().getSystem().getListaSvihTabelaUSistemu().get(i).getChildOfTable().add(MyApp.getInstance().findTableById(potomakTabela));
						}
					}					
					
					
				}	//kraj relacija	
				
				System.out.println("ISPIS SVIH LISTI!!");
				for(int i=0;i<MyApp.getInstance().getSystem().getListaSvihTabelaUSistemu().size();i++){
					System.out.println(MyApp.getInstance().getSystem().getListaSvihTabelaUSistemu().get(i).getChildOfTable());
				}
		}
			
}	
	



