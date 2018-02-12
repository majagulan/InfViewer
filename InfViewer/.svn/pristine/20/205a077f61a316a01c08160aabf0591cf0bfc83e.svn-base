package crudActions;

import java.awt.BorderLayout;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import gui.MyApp;
import gui.Tab;
import gui.TabPanel;
import model.Table;

/**
 * Klasa {@code PromoteAction} nasledjuje {@link AbstractAction}
 * Sluzi za promociju direktnog potomka u roditelja 
 * @see AbstractAction
 */

@SuppressWarnings("serial")
public class PromoteAction extends AbstractAction {
	

	/**
	 * Konstruktor {@link PromoteAction } klase
	 * 
	 */
	
	public PromoteAction(){
		
	
		putValue(NAME, "Promote");
		putValue(SHORT_DESCRIPTION, "Promote");
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_MASK));
		putValue(SMALL_ICON, new ImageIcon("src/images/promote.png"));
	}
	
	/**
	 * Odabrani direktni potomak otvara se u novom tab-u kao roditelj
	 * U slucaju da vec postoji tab sa datom tabelom, samo se selektuje taj tab. 
	 * U donjem delu prikazuju se njegovi direktni potomci.
	 * @param e {@link ActionEvent}
	 * @return void
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int ind=0;
		Tab tab = (Tab)MyApp.getInstance().getTabPanelDonji().getSelectedComponent();
		if(tab!=null){
		tab.setName(tab.getTable().getIdLabelTable());
		
		boolean postoji = false;
		for(int i=0;i<MyApp.getInstance().getTabPanelGornji().getTabCount();i++){
			if(((Tab)MyApp.getInstance().getTabPanelGornji().getComponentAt(i)).getTabId().equals(tab.getName())){
				MyApp.getInstance().getTabPanelGornji().setSelectedIndex(i);
				ind=i;
				postoji = true;
				break;
			}
		}
		
		if(!postoji){
			
			
			//MyApp.getInstance().getTabPanelGornji().removeAll();
			MyApp.getInstance().getTabPanelGornji().addTab(tab.getName(),tab);
			MyApp.getInstance().getTabPanelGornji().setTabComponentAt(MyApp.getInstance().getTabPanelGornji().getTabCount()-1, new Tab(tab.getTableRows(), tab.getTable()));
			MyApp.getInstance().getTabPanelGornji().setSelectedIndex(MyApp.getInstance().getTabPanelGornji().getTabCount()-1);
			MyApp.getInstance().getTabPanelGornji().revalidate();
			MyApp.getInstance().getTabPanelGornji().repaint();
		}
		
		if(!tab.getTable().getChildOfTable().isEmpty()){
			TabPanel child = new TabPanel();
			MyApp.getInstance().getPan().getGornjiPanel().setLayout(new BorderLayout());
			MyApp.getInstance().getPan().setLayout(new GridLayout(2,1));
			MyApp.getInstance().getPan().getGornjiPanel().setSize(MyApp.getInstance().getPan().getDonjiPanel().getSize());
			MyApp.getInstance().getTabPanel().setSize(MyApp.getInstance().getPan().getDonjiPanel().getSize());
			for(int i=0;i<tab.getTable().getChildOfTable().size();i++){
				
					Table table2 = MyApp.getInstance().getTableById(tab.getTable().getChildOfTable().get(i));
					Tab deteTab = new Tab(table2,false);
					child.addTab(table2.getIdLabelTable(), deteTab);
					
					child.setTabComponentAt(i, new Tab(deteTab.getTableRows(),deteTab.getTable()));
					
					
					//ovde dodati da se refreshuje donji panel sa novom decom opet repaint i revalidate
					MyApp.getInstance().setTabPanelDonji(child);
					BorderLayout layout = (BorderLayout) MyApp.getInstance().getPan().getDonjiPanel().getLayout();
					if(layout.getLayoutComponent(BorderLayout.CENTER)!=null){
						MyApp.getInstance().getPan().getDonjiPanel().remove(layout.getLayoutComponent(BorderLayout.CENTER));
					}
					MyApp.getInstance().getPan().getDonjiPanel().add(child);
					
			}
		}else{
			MyApp.getInstance().getPan().getDonjiPanel().setVisible(false);
			MyApp.getInstance().getPan().getGornjiPanel().setLayout(new BorderLayout());
			MyApp.getInstance().getPan().setLayout(new BorderLayout());
			MyApp.getInstance().getPan().getGornjiPanel().setSize(MyApp.getInstance().getPan().getSize());
			MyApp.getInstance().getTabPanel().setSize(MyApp.getInstance().getPan().getGornjiPanel().getSize());
			MyApp.getInstance().getTabPanelDonji().removeAll();
			MyApp.getInstance().getTabPanelDonji().revalidate();
			MyApp.getInstance().getTabPanelDonji().repaint();
		}
		
		
		}
	}

}
