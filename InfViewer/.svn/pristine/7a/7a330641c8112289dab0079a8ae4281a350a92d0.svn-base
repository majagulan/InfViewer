package model;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 * Klasa koja se sluzi za upravljanje svim akcijama za 
 * <p> Nasledjuje  {@link DefaultMutableTreeNode} klasu.</p>
 *
 */

public class Table extends DefaultMutableTreeNode{	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Column> listOfColumns = new ArrayList<>();
	private String tableName;
	private String idLabelTable;
	private String idPackageOfTable;
	private ArrayList<String> parentsOfTable = new ArrayList<String>();
	private ArrayList<String> childOfTable = new ArrayList<String>();
	
	private ArrayList<ForeignKeyConstraint> parentFKC = new ArrayList<>();
	private ArrayList<ForeignKeyConstraint> childFKC = new ArrayList<>();
	
	/**
	 * Preuzimanje vrednosti komponente parentFKC.
	 * @see ArrayList
	 * @return parentFKC
	 */

	public ArrayList<ForeignKeyConstraint> getParentFKC() {
		return parentFKC;
	}

	/**
	 *  Dodeljivanje vrednosti komponente parentFKC.
	 *  @see ArrayList
	 *  @param parentFKC
	 *  @return void
	 */
	
	public void setParentFKC(ArrayList<ForeignKeyConstraint> parentFKC) {
		this.parentFKC = parentFKC;
	}
	
	/**
	 * Preuzimanje vrednosti komponente childFKC.
	 * @see ArrayList
	 * @return childFKC
	 */

	public ArrayList<ForeignKeyConstraint> getChildFKC() {
		return childFKC;
	}

	/**
	 *  Dodeljivanje vrednosti komponente childFKC.
	 *  @see ArrayList
	 *  @param childFKC
	 *  @return void
	 */
	
	public void setChildFKC(ArrayList<ForeignKeyConstraint> childFKC) {
		this.childFKC = childFKC;
	}
	
	/**
	 * Preuzimanje vrednosti komponente parentsOfTable.
	 * @see ArrayList
	 * @return parentsOfTable
	 */

	public ArrayList<String> getParentsOfTable() {
		return parentsOfTable;
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente parentsOfTable.
	 *  @see ArrayList
	 *  @param parentsOfTable
	 *  @return void
	 */

	public void setParentsOfTable(ArrayList<String> parentsOfTable) {
		this.parentsOfTable = parentsOfTable;
	}

	/**
	 * Preuzimanje vrednosti komponente childOfTable.
	 * @see ArrayList
	 * @return childOfTable
	 */
	
	public ArrayList<String> getChildOfTable() {
		return childOfTable;
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente childOfTable.
	 *  @see ArrayList
	 *  @param childOfTable
	 *  @return void
	 */

	public void setChildOfTable(ArrayList<String> childOfTable) {
		this.childOfTable = childOfTable;
	}
	
	/**
	 * Konstruktor sa parametrima {@link Table}.
	 * @param listOfColumns
	 * @param tableName
	 * @param idLabelTable
	 * @param idPackageOfTable
	 */
	
	public Table(ArrayList<Column> listOfColumns, String tableName, String idLabelTable, String idPackageOfTable){
		this.listOfColumns = listOfColumns;
		this.tableName = tableName;
		this.idLabelTable = idLabelTable;
		this.idPackageOfTable = idPackageOfTable;	
	}
	
	/**
	 * Konstruktor sa parametrima {@link Table}.
	 * @param listOfColumns
	 * @param tableName
	 * @param idLabelTable
	 * @param idPackageOfTable
	 * @param parentFKC
	 * @param childFKC
	 */

	public Table(ArrayList<Column> listOfColumns, String tableName, String idLabelTable,String idPackageOfTable,
			ArrayList<ForeignKeyConstraint> parentFKC, ArrayList<ForeignKeyConstraint> childFKC) {
		
		this.listOfColumns = listOfColumns;
		this.tableName = tableName;
		this.idLabelTable = idLabelTable;
		this.idPackageOfTable = idPackageOfTable;
		this.listOfKeys = listOfKeys;
		this.parentFKC = parentFKC;
		this.childFKC = childFKC;
	}

	/**
	 * Preuzimanje vrednosti komponente idPackageOfTable.
	 * @return idPackageOfTable
	 */
	
	public String getIdPackageOfTable() {
		return idPackageOfTable;
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente idPackageOfTable.
	 *  @param idPackageOfTable
	 *  @return void
	 */

	public void setIdPackageOfTable(String idPackageOfTable) {
		this.idPackageOfTable = idPackageOfTable;
	}

	ArrayList<String> listOfKeys = new ArrayList<>();
	
	

	/**
	 * Konstruktor bez parametara {@link Table}.
	 */

	public Table() {
		super();
	}

	/**
	 * Preuzimanje vrednosti komponente listOfColumns.
	 * @see ArrayList
	 * @return listOfColumns
	 */
	
	public ArrayList<Column> getListOfColumns() {
		return listOfColumns;
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente listOfColumns.
	 *  @see ArrayList
	 *  @param listOfColumns
	 *  @return void
	 */

	public void setListOfColumns(ArrayList<Column> listOfColumns) {
		this.listOfColumns = listOfColumns;
	}

	/**
	 * Preuzimanje vrednosti komponente tableName.
	 * @return tableName
	 */
	
	public String getTableName() {
		return tableName;
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente tableName.
	 *  @param tableName
	 *  @return void
	 */

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * Preuzimanje vrednosti komponente idLabelTable.
	 * @return idLabelTable
	 */
	
	public String getIdLabelTable() {
		return idLabelTable;
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente idLabelTable.
	 *  @param idLabelTable
	 *  @return void
	 */

	public void setIdLabelTable(String idLabelTable) {
		this.idLabelTable = idLabelTable;
	}

	/**
	 * Preuzimanje vrednosti komponente listOfKeys.
	 * @see ArrayList
	 * @return listOfKeys
	 */
	
	public ArrayList<String> getListOfKeys() {
		return listOfKeys;
	}

	/**
	 *  Dodeljivanje vrednosti komponente listOfKeys.
	 *  @see ArrayList
	 *  @param listOfKeys
	 *  @return void
	 */
	
	public void setListOfKeys(ArrayList<String> listOfKeys) {
		this.listOfKeys = listOfKeys;
	}
	
	/**
	 *  Dodavanje novog potomka u stablo.
	 *  @see MutableTreeNode
	 *  @param newChild
	 *  @return void
	 */
	
	public void add(MutableTreeNode newChild){
		super.add(newChild);
	}
	
	
	public TreeNode getChildAt(int ind){
		return super.getChildAt(ind);
	}
	

	public int getChildCount(){
		return super.getChildCount();
	}
	
	/**
	 * Dodavanje kolone u {@link listOfColumns}.
	 * @return void
	 */
	
	public void addColumn(Column column){
		listOfColumns.add(column);
		
	}
	
	@Override
	public String toString() {
		return tableName ;
	}
	

}
