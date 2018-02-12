package tree;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import gui.MyApp;
import model.Table;

/**
 * Ova klasa je mouse listener od {@link Tree}. Ona nasledjuje klasu {@link MouseAdapter}
 *
 *@see MouseAdapter
 */

public class TreeListenerShowTab extends MouseAdapter{
	
	private Tree tree;
	
	/**
	 * Konstruktor sa parametrima {@link TreeListenerShowTab}.
	 * @param tree
	 */
	
	public TreeListenerShowTab(Tree tree){
		this.tree = tree;
	}
	
	/**
	 * Setuje akciju kada je pritisnut mis.
	 * 
	 * @param e
	 * 
	 * @see MouseEvent
	 * @see getClickCount
	 * @see getLastSelectedPathComponent
	 * 
	 * @return void
	 */
	
	public void mouseClicked(MouseEvent e) { 
		if(e.getClickCount()==2 && tree.getLastSelectedPathComponent() instanceof Node){
				Node node = (Node)tree.getLastSelectedPathComponent();
					if(node != null){
						
						Table t = MyApp.getInstance().getTableById(node.getTable().getIdLabelTable());
						//MyApp.getInstance().getTabPanel().removeAll();
						MyApp.getInstance().getTabPanel().add(t);
						MyApp.getInstance().getTabPanel().revalidate();
						MyApp.getInstance().getTabPanel().repaint();
						MyApp.getInstance().getPan().getDonjiPanel().setVisible(true);
						MyApp.getInstance().getPan().getGornjiPanel().setLayout(new BorderLayout());
						MyApp.getInstance().getPan().setLayout(new GridLayout(2,1));
						MyApp.getInstance().getPan().getGornjiPanel().setSize(MyApp.getInstance().getPan().getDonjiPanel().getSize());
						MyApp.getInstance().getTabPanel().setSize(MyApp.getInstance().getPan().getDonjiPanel().getSize());
						
						if(t.getChildOfTable().isEmpty()){
							MyApp.getInstance().getPan().getDonjiPanel().setVisible(false);
							MyApp.getInstance().getPan().getGornjiPanel().setLayout(new BorderLayout());
							MyApp.getInstance().getPan().setLayout(new BorderLayout());
							MyApp.getInstance().getPan().getGornjiPanel().setSize(MyApp.getInstance().getPan().getSize());
							MyApp.getInstance().getTabPanel().setSize(MyApp.getInstance().getPan().getGornjiPanel().getSize());
							MyApp.getInstance().getTabPanelDonji().removeAll();
							MyApp.getInstance().getTabPanelDonji().revalidate();
							MyApp.getInstance().getTabPanelDonji().repaint();
							//ako nema decu treba da ostavi dole prazan
							
						}
					}else{
						return;
					}
			
		}else if(e.getClickCount()==2 && tree.getLastSelectedPathComponent() instanceof Table){
			Table node = (Table)tree.getLastSelectedPathComponent();
			
			if(node != null){
				//MyApp.getInstance().getTabPanel().removeAll();
				MyApp.getInstance().getTabPanel().add(node);
				MyApp.getInstance().getPan().getDonjiPanel().setVisible(true);
				MyApp.getInstance().getPan().getGornjiPanel().setLayout(new BorderLayout());
				MyApp.getInstance().getPan().setLayout(new GridLayout(2,1));
				MyApp.getInstance().getPan().getGornjiPanel().setSize(MyApp.getInstance().getPan().getDonjiPanel().getSize());
				MyApp.getInstance().getTabPanel().setSize(MyApp.getInstance().getPan().getDonjiPanel().getSize());
				MyApp.getInstance().getTabPanel().revalidate();
				MyApp.getInstance().getTabPanel().repaint();
				
				
				if(node.getChildOfTable().isEmpty()){
						
					//ako nema decu dole treba da ostavi prazan

					MyApp.getInstance().getPan().getDonjiPanel().setVisible(false);
					MyApp.getInstance().getPan().getGornjiPanel().setLayout(new BorderLayout());
					MyApp.getInstance().getPan().setLayout(new BorderLayout());
					MyApp.getInstance().getPan().getGornjiPanel().setSize(MyApp.getInstance().getPan().getSize());
					MyApp.getInstance().getTabPanel().setSize(MyApp.getInstance().getPan().getGornjiPanel().getSize());
					
					MyApp.getInstance().getTabPanelDonji().removeAll();
					MyApp.getInstance().getTabPanelDonji().revalidate();
					MyApp.getInstance().getTabPanelDonji().repaint();
						
				}
			}else{
				return;
			}
		}
	
	}
}
