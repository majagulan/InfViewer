package model;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Klasa koja se sluzi za modelovanje paketa koja nasledjuje {@link DefaultMutableTreeNode}
 *
 */

public class Packages extends DefaultMutableTreeNode{	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Table> childrenTables = new ArrayList<Table>();
	private ArrayList<Packages> subPackages = new ArrayList<Packages>();
	private String name;
	private String idPackage;
	private String type;
	private String komePripada;
	
	/**
	 * Konstruktor bez parametara {@link Packages}.
	 */
	
	public Packages() {
		
	}
	
	/**
	 * Konstruktor sa parametrima {@link Packages}.
	 * @param name
	 * @param idPackage
	 * @param type
	 * @param komePripada
	 */
	
	public Packages(String name, String idPackage, String type, String komePripada) {
		
		this.name = name;
		this.idPackage = idPackage;
		this.type = type;
		this.komePripada = komePripada;
	}
	
	/**
	 * Konstruktor sa parametrima {@link Packages}.
	 * @param name
	 * @param idPackage
	 * @param type
	 */
	
	public Packages(String name, String idPackage,String type) {
		
		this.name = name;
		this.idPackage = idPackage;
		this.type = type;
	}
	
	/**
	 * Preuzimanje vrednosti komponente komePripada.
	 * @return komePripada
	 */
	
	public String getKomePripada() {
		return komePripada;
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente komePripada.
	 *  @param komePripada
	 *  @return void
	 */
	
	public void setKomePripada(String komePripada) {
		this.komePripada = komePripada;
	}
	
	/**
	 * Preuzimanje vrednosti komponente type.
	 * @return type
	 */
	
	public String getType() {
		return type;
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente type.
	 *  @param type
	 *  @return void
	 */
	
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Preuzimanje vrednosti komponente childrenTables.
	 * @see ArrayList
	 * @return childrenTables
	 */
	
	public ArrayList<Table> getChildrenTables() {
		return childrenTables;
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente childrenTables.
	 *  @see ArrayList
	 *  @param childrenTables
	 *  @return void
	 */
	
	public void setChildrenTables(ArrayList<Table> childrenTables) {
		this.childrenTables = childrenTables;
	}
	
	/**
	 * Preuzimanje vrednosti komponente subPackages.
	 * @see ArrayList
	 * @return subPackages
	 */
	
	public ArrayList<Packages> getSubPackages() {
		return subPackages;
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente subPackages.
	 *  @see ArrayList
	 *  @param subPackages
	 *  @return void
	 */
	
	public void setSubPackages(ArrayList<Packages> subPackages) {
		this.subPackages = subPackages;
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
	 * Preuzimanje vrednosti komponente idPackage.
	 * @return idPackage
	 */
	
	public String getIdPackage() {
		return idPackage;
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente idPackage.
	 *  @param idPackage
	 *  @return void
	 */
	
	public void setIdPackage(String idPackage) {
		this.idPackage = idPackage;
	}
	
	@Override
	public String toString() {
		return name;
	}

}
