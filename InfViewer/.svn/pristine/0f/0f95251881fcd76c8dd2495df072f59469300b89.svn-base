package tree;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

import gui.MyApp;
import model.Table;

/**
 * Klasa nasledjuje {@link DefaultMutableTreeNode} koja nosi dodatne
 * informacije o referenciranoj {@link Table}
 * 
 *@see DefaultMutableTreeNode
 */

public class Node extends DefaultMutableTreeNode {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Table table;
	
	/**
	 * Konstruktor sa parametrima
	 * @param table
	 */
	
	public Node(Table table){
		
		super(table);
		this.table = table;
		if(table.getChildOfTable().isEmpty()){
			
		}else{
		for(String s : table.getChildOfTable()) {
			if(MyApp.getInstance().getMapaSvihTabela().get(s).getChildOfTable().isEmpty()){
				this.add((MutableTreeNode) MyApp.getInstance().getMapaSvihTabela().get(s).clone());
			}else{
				this.add(new Node(MyApp.getInstance().getMapaSvihTabela().get(s)));
				}
			}
		}
	}
	
	/**
	 * Preuzimanje vrednosti komponente table.
	 * @return table
	 */

	public Table getTable() {
		return table;
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente table.
	 *  @param table
	 *  @return void
	 */
	
	public void setTable(Table table) {
		this.table = table;
	}
}

