package model;

import java.util.ArrayList;

/**
 * Klasa koja se sluzi za modelovanje stranog kljuca.
 *
 */

public class ForeignKeyConstraint {
	
	private String name;
	private String idLabelRelation;
	private String parentTableName;
	private String childTableName;
	private boolean jeZavistan;
	private ArrayList<Relations> columnFKList = new ArrayList<>();
	
	/**
	 * Konstruktor sa parametrima {@link ForeignKeyConstraint}.
	 * @see ArrayList
	 * @param name
	 * @param idLabelRelation
	 * @param parentTableName
	 * @param childTableName
	 * @param listOfForeignKeys
	 * @param jeZavistan
	 */
	
	public ForeignKeyConstraint(String name, String idLabelRelation, String parentTableName, String childTableName,
			ArrayList<Relations> listOfForeignKeys,boolean jeZavistan) {
		
		this.name = name;
		this.idLabelRelation = idLabelRelation;
		this.parentTableName = parentTableName;
		this.childTableName = childTableName;
		this.columnFKList = listOfForeignKeys;
		this.jeZavistan = jeZavistan;
	}
	
	/**
	 * Preuzimanje vrednosti komponente jeZavistan.
	 * @return jeZavistan
	 */
	
	public boolean isJeZavistan() {
		return jeZavistan;
	}

	/**
	 *  Dodeljivanje vrednosti komponente jeZavistan.
	 *  @param jeZavistan
	 *  @return void
	 */

	public void setJeZavistan(boolean jeZavistan) {
		this.jeZavistan = jeZavistan;
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
	 * Preuzimanje vrednosti komponente idLabelRelation.
	 * @return idLabelRelation
	 */
	
	public String getIdLabelRelation() {
		return idLabelRelation;
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente idLabelRelation.
	 *  @param idLabelRelation
	 *  @return void
	 */
	
	public void setIdLabelRelation(String idLabelRelation) {
		this.idLabelRelation = idLabelRelation;
	}
	
	/**
	 * Preuzimanje vrednosti komponente parentTableName.
	 * @return parentTableName
	 */
	
	public String getParentTableName() {
		return parentTableName;
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente parentTableName.
	 *  @param parentTableName
	 *  @return void
	 */
	
	public void setParentTableName(String parentTableName) {
		this.parentTableName = parentTableName;
	}
	
	/**
	 * Preuzimanje vrednosti komponente childTableName.
	 * @return childTableName
	 */
	
	public String getChildTableName() {
		return childTableName;
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente childTableName.
	 *  @param childTableName
	 *  @return void
	 */
	
	public void setChildTableName(String childTableName) {
		this.childTableName = childTableName;
	}

	/**
	 * Preuzimanje vrednosti komponente columnFKList.
	 * @return columnFKList
	 */

	public ArrayList<Relations> getColumnFKList() {
		return columnFKList;
	}


	@Override
	public String toString() {
		return "ForeignKeyConstraint [name=" + name + ", idLabelRelation=" + idLabelRelation + ", parentTableName="
				+ parentTableName + ", childTableName=" + childTableName + ", jeZavistan=" + jeZavistan
				+ ", columnFKList=" + columnFKList + "]";
	}

	/**
	 *  Dodeljivanje vrednosti komponente columnFKList.
	 *  @param columnFKList
	 */

	public void setColumnFKList(ArrayList<Relations> columnFKList) {
		this.columnFKList = columnFKList;
	}


}
