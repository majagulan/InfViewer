package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Table;
import tree.Node;

/**
 * Klasa {@code TabPanel} nasledjuje {@link JTabbedPane} 
 *
 */
@SuppressWarnings("serial")
public class TabPanel extends JTabbedPane{
	
	/**
	 * Konstruktor bez parametara
	 */
	
	public TabPanel() {
		
	}

	/**
	 * DOdaje tab panel u graficki interfejs
	 * @param o
	 * @see Tab
	 */
	
	public void add(Object o){
	 if(o instanceof Node){
		int pos = -1;
		for(int i=0;i<this.getTabCount();i++){
			if(((Tab)this.getComponentAt(i)).getTabId().equals(((Node)o).getTable().getIdLabelTable())){
				pos = i;
				break;
			}
		}
		if(pos == -1){

			Tab tab = new Tab(((Node)o).getTable(),true);
			addTab(tab.getName(),tab);
			setSelectedComponent(tab);
		
			setTabComponentAt(this.getTabCount()-1, new Tab(tab.getTableRows(),tab.getTable()));
			addChangeListener(new ChangeListener() { //add the Listener

		        public void stateChanged(ChangeEvent e) {

		            
		        }
		    });

		}else{
			setSelectedIndex(pos);
			Tab tab = new Tab(((Node)o).getTable(),true);

		}
	}else if(o instanceof Table){
		int pos = -1;
		for(int i=0;i<this.getTabCount();i++){
			if(((Tab)this.getComponentAt(i)).getTabId().equals(((Table)o).getIdLabelTable())){
				pos = i;
				break;
			}
		}
		
		
		if(pos == -1){

			Tab tab = new Tab(((Table)o),true);
			addTab(tab.getName(),tab);
			setSelectedComponent(tab);
			setTabComponentAt(this.getTabCount()-1, new Tab(tab.getTableRows(),tab.getTable()));
			addChangeListener(new ChangeListener() { //add the Listener

		        public void stateChanged(ChangeEvent e) {
		        	try{
		        	MyApp.getInstance().getTableById(((Tab)getSelectedComponent()).getTabId());
		        		
		        	
		            Table t = MyApp.getInstance().getTableById(((Tab)getSelectedComponent()).getTabId());
		        	
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
					
					}}
		        catch(NullPointerException e1){
		        	
		        }}
		    });

		}else{
			setSelectedIndex(pos);
			Tab tab = new Tab(((Table)o),true);
		}

	}	
	
	}
}
