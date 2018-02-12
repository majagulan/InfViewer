package model;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Klasa koja se sluzi za upravljanje svim akcijama za dodavanje liste paketa u sistem
 * i liste tabela u sistem.
 * <p> Nasledjuje  {@link DefaultMutableTreeNode} klasu.</p>
 *
 */

public class Sistem extends DefaultMutableTreeNode{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String labelaPodsistema;
	private String tip;
	private ArrayList<Packages> listaPaketaUSistemu = new ArrayList<>();
	private ArrayList<Table> listaSvihTabelaUSistemu = new ArrayList<>();
	
	/**
	 * Konstruktor bez parametara {@link Sistem}.
	 */
	
	public Sistem() {
		
	}
	
	/**
	 * Konstruktor sa parametrima {@link Sistem}.
	 * @see ArrayList
	 * @param name
	 * @param labelaPodsistema
	 * @param tip
	 * @param listaPaketaUSistemu
	 */
	
	public Sistem(String name, String labelaPodsistema,String tip, ArrayList<Packages> listaPaketaUSistemu) {
		
		this.name = name;
		this.labelaPodsistema = labelaPodsistema;
		this.tip = tip;
		this.listaPaketaUSistemu = listaPaketaUSistemu;
	}
	
	/**
	 * Konstruktor sa parametrima {@link Sistem}.
	 * @param name
	 * @param labela
	 * @param tip
	 */
	
	public Sistem(String name, String labela, String tip){
		this.name = name;
		this.labelaPodsistema = labela;
		this.tip = tip;
	}
	
	/**
	 * Preuzimanje vrednosti komponente listaSvihTabelaUSistemu.
	 * @see ArrayList
	 * @return listaSvihTabelaUSistemu
	 */
	
	public ArrayList<Table> getListaSvihTabelaUSistemu() {
		return listaSvihTabelaUSistemu;
	}
	
	/**
	 * Dodeljivanje vrednosti komponente listaSvihTabelaUSistemu.
	 * @see ArrayList
	 * @return void
	 */
	
	public void setListaSvihTabelaUSistemu(ArrayList<Table> listaSvihTabelaUSistemu) {
		this.listaSvihTabelaUSistemu = listaSvihTabelaUSistemu;
	}
	
	/**
	 * Preuzimanje vrednosti komponente name.
	 * @return name
	 */
	
	public String getName() {
		return name;
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente name.
	 *  @param name
	 *  @return void
	 */
	
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Preuzimanje vrednosti komponente labelaPodsistema.
	 * @return labelaPodsistema
	 */
	
	public String getLabelaPodsistema() {
		return labelaPodsistema;
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente labelaPodsistema.
	 *  @param labelaPodsistema
	 *  @return void
	 */
	
	public void setLabelaPodsistema(String labelaPodsistema) {
		this.labelaPodsistema = labelaPodsistema;
	}
	
	/**
	 * Preuzimanje vrednosti komponente tip.
	 * @return tip
	 */
	
	public String getTip() {
		return tip;
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente tip.
	 *  @param tip
	 *  @return void
	 */
	
	public void setTip(String tip) {
		this.tip = tip;
	}
	
	/**
	 * Preuzimanje vrednosti komponente listaPaketaUSistemu.
	 * @see ArrayList
	 * @return listaPaketaUSistemu
	 */
	
	public ArrayList<Packages> getListaPaketaUSistemu() {
		return listaPaketaUSistemu;
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente listaPaketaUSistemu.
	 *  @see ArrayList
	 *  @param listaPaketaUSistemu
	 *  @return void
	 */
	
	public void setListaPaketaUSistemu(ArrayList<Packages> listaPaketaUSistemu) {
		this.listaPaketaUSistemu = listaPaketaUSistemu;
	}
	
	/**
	 *  Dodavanje paketa u {@link listaPaketaUSistemu}.
	 *  @param p
	 *  @return void
	 */
	
	public void addPackages(Packages p){
		listaPaketaUSistemu.add(p);
	} 
	
	@Override
	public String toString() {
		return name;
	}

}
