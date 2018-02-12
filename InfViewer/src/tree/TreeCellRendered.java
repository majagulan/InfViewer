package tree;

import java.awt.Component;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import gui.MyApp;
import model.Table;

/**
 * Ova klasa predstavlja prilagodjenu implementaciju rendera stabla 
 * (nasledjuje {@link DefaultTreeCellRenderer}) koji stvara 
 * prikaz stabla razlicitih klasa iz modela.
 * 
 *@see DefaultTreeCellRenderer
 */

public class TreeCellRendered extends DefaultTreeCellRenderer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor bez parametara {@link TreeCellRendered}.
	 */
	
	public TreeCellRendered() {
	}

	/**
	 * Preuzimanje prilagodjene komponente renderovanog stabla. 
	 * 
	 * @param tree
	 * @param value
	 * @param sel
	 * @param expanded
	 * @param leaf
	 * @param row
	 * @param hasFocus
	 * 
	 * @see Component
	 * @see JTree
	 * @see Object
	 * 
	 * @return Component
	 */
	
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf,
			int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		
		for(int i=0;i<MyApp.getInstance().getListaSvihPaketa().size();i++){
			if(value.toString().equals(MyApp.getInstance().getListaSvihPaketa().get(i).getName())){
				if(MyApp.getInstance().getListaSvihPaketa().get(i).getType().equals("system") ||
						MyApp.getInstance().getListaSvihPaketa().get(i).getType().equals("S")){
					URL imageURL = getClass().getResource("../images/rsz_systemicon.png");

					Icon icon = null;
					if (imageURL != null)
						icon = new ImageIcon(imageURL);
					setIcon(icon);
				}else if(MyApp.getInstance().getListaSvihPaketa().get(i).getType().equals("subsystem") ||
						MyApp.getInstance().getListaSvihPaketa().get(i).getType().equals("P")){
					URL imageURL = getClass().getResource("../images/subsystemIcon.png");

					Icon icon = null;
					if (imageURL != null)
						icon = new ImageIcon(imageURL);
					setIcon(icon);

				}else if(MyApp.getInstance().getListaSvihPaketa().get(i).getType().equals("package") ||
						MyApp.getInstance().getListaSvihPaketa().get(i).getType().equals("K")){
					URL imageURL = getClass().getResource("../images/packageIcon.png");
					Icon icon = null;
					if (imageURL != null)
						icon = new ImageIcon(imageURL);
					setIcon(icon);

				}
			}
		}
	

		 if (value instanceof Node || value instanceof Table) {
			URL imageURL = getClass().getResource("../images/rsz_tablesNode.png");
			Icon icon = null;
			if (imageURL != null)
				icon = new ImageIcon(imageURL);
				
			setIcon(icon);

		}
		
		return this;

	}
}
