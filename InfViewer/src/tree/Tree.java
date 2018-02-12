package tree;

import java.util.ArrayList;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import gui.MyApp;
import model.Packages;
import model.Table;


/**
 * Ova klasa nasledjuje {@link JTree} i koristi se za inicijalizovanje
 * {@link Tree} i kreiranje strukture paketa.
 * 
 * @see JTree
 */

public class Tree extends JTree{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * Instanca koja obezbedjuje implementaciju <code> Singlton </code> sablona.
	 */
	
	private static Tree instance = null;
	
	 
	ArrayList<TreeNode> packageTreeMap = new ArrayList<>();
	
	/**
	 * Raspusta instancu koja posledicno pokrece kreiranje nove
	 * instance na poziv metode {@link getInstance}.
	 * @return void
	 */
	
	public static void destroy() {
		instance = null;
	}
	
	/**
	 * Preuzimanje vrednosti komponente instance.
	 * @return instance
	 */
	
	public static Tree getInstance(){
		if(instance==null)
			instance = new Tree();
		return instance;
	}
	
	/**
	 * Konstruktor bez parametara {@link Tree}.
	 * Poziva metodu {@link initializeJSON}.
	 */
	
	public Tree(){
			
		initializeJSON();
		
	}
	
	/**
	 * Interpretira JSON fajl i proverava da li je sistem ucitan.
	 * @throws Exception
	 * @return void
	 */	
	
	private void initializeJSON() {
		
		getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);


		try {
		
			if(MyApp.getInstance().getSystem() == null){
				System.out.println("NIJE UCITAN SISTEM");
				return;
			}
			
			
			DefaultMutableTreeNode top = new DefaultMutableTreeNode(MyApp.getInstance().getSystem());
			DefaultTreeModel model = new DefaultTreeModel(top);
			this.setModel(model);
			setCellRenderer(new TreeCellRendered());
			
			
			for(Packages p : MyApp.getInstance().getSystem().getListaPaketaUSistemu()) {
				DefaultMutableTreeNode rootPackage = new DefaultMutableTreeNode(p);	
				
				
			    TreeNode n = new TreeNode(p.getIdPackage(), rootPackage);
			    packageTreeMap.add(n);
				top.add(rootPackage);
				addChildren(rootPackage);
			}
			
			

			for(Table t : MyApp.getInstance().getSystem().getListaSvihTabelaUSistemu()){
				if(nadjiCvor(t.getIdPackageOfTable()) && t.getParentsOfTable().isEmpty()) {
					DefaultMutableTreeNode node = getCvorById(t.getIdPackageOfTable());
					System.out.println(t.getChildOfTable());
					node.add(new Node(t));
					
				}
			}
			
			 TreeListenerShowTab tabListener = new TreeListenerShowTab(this);
			 addMouseListener(tabListener);
		
		}catch(Exception e){
			
		}
	}
	
	/**
	 * Pronalazi cvor ako postoji u {@link packageTreeMap}. Poredi njihove vrednosti id-a.
	 * @param s
	 * @return boolean
	 */
	
	public boolean nadjiCvor(String s){
		for(int i=0;i<packageTreeMap.size();i++){
			if(packageTreeMap.get(i).getIdNode().equals(s)){
				return true;
			}
		}
		
		return false;
	}
	
	/** 
	 * Preuzimanje vrednosti komponente s (id cvora).
	 * @see DefaultMutableTreeNode
	 * @param s
	 * @return
	 */
	
	public DefaultMutableTreeNode getCvorById(String s){
		for(int i=0;i<packageTreeMap.size();i++){
			if(packageTreeMap.get(i).getIdNode().equals(s)){
				return packageTreeMap.get(i).getNode();
			}
		}
		
		return null;
	}
	
	/**
	 * Dodaje novi cvor u stablo u zavisnosti od tipa cvora (tabela ili paket).
	 * Metoda je rekurzivna i popunjava celu dubinu grana drveta.
	 * @see DefaultMutableTreeNode
	 * @param node
	 * @return void
	 */
	
	private void addChildren(DefaultMutableTreeNode node) {
		Packages p = (Packages) node.getUserObject(); 
		if(p.getSubPackages().size() == 0) {
			return; 
		}
		
		for(Packages p1 : p.getSubPackages()) {
			DefaultMutableTreeNode subNode = new DefaultMutableTreeNode(p1);
			
			TreeNode n = new TreeNode(p1.getIdPackage(), subNode);
			packageTreeMap.add(n);
			node.add(subNode);
			addChildren(subNode);
			
		}
		 
	}

}
