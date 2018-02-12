package crudActions;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import gui.MyApp;
import gui.Tab;
import model.Table;

/**
 * Klasa {@code DepromoteAction} nasledjuje {@link AbstractAction}
 * Sluzi za promociju roditelja u dete 
 * @see AbstractAction
 */

@SuppressWarnings("serial")
public class DepromoteAction extends AbstractAction{
	
	/**
	 * Konstruktor {@link DepromoteAction } klase
	 * 
	 */
	
	public DepromoteAction(){
		
		putValue(NAME, "Depromote");
		putValue(SHORT_DESCRIPTION, "Depromote");
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_MASK));
		putValue(SMALL_ICON, new ImageIcon("src/images/depromote.png"));
	}
	
	/**
	 * Prikaz tabele u gornjem tabu, a direktnih potomaka u donjem ukoliko trenutni roditelj ima tacno jednog roditelja
     * Mogucnost odabira tabele za promovisanje ukoliko trenutni roditelj ima vise od jendog roditelja
	 
	 *@param e {@link ActionEvent}
	 *@return void
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
	Tab tab = (Tab)MyApp.getInstance().getTabPanelGornji().getSelectedComponent();
		if(tab!=null){
		tab.setName(tab.getTable().getIdLabelTable());
		if(tab.getTable().getParentsOfTable().size()==0){//nema roditelja
			JOptionPane.showMessageDialog(null, MyApp.getInstance().getResourceBundle().getString("upozorenje26"));
		}else if(tab.getTable().getParentsOfTable().size()==1){//ima samo jednog roditelja
			MyApp.getInstance().getPan().getGornjiPanel().setLayout(new BorderLayout());
			MyApp.getInstance().getPan().setLayout(new GridLayout(2,1));
			MyApp.getInstance().getPan().getGornjiPanel().setSize(MyApp.getInstance().getPan().getDonjiPanel().getSize());
			MyApp.getInstance().getTabPanel().setSize(MyApp.getInstance().getPan().getDonjiPanel().getSize());
			Tab parent = new Tab(MyApp.getInstance().getTableById(tab.getTable().getParentsOfTable().get(0)),true);
			parent.setName(tab.getTable().getParentsOfTable().get(0));
			
			for(int i=0;i<MyApp.getInstance().getTabPanel().getTabCount();i++){
				if(((Tab)MyApp.getInstance().getTabPanel().getComponentAt(i)).getTabId().equals(tab.getTable().getIdLabelTable())){
					MyApp.getInstance().getTabPanel().remove(i);
					break;
				}
			}
			
			
			boolean postoji = false;
			//MyApp.getInstance().getTabPanel().removeAll();
			for(int i=0;i<MyApp.getInstance().getTabPanel().getTabCount();i++){
				if(((Tab)MyApp.getInstance().getTabPanel().getComponentAt(i)).getTabId().equals(parent.getName())){
					MyApp.getInstance().getTabPanel().setSelectedIndex(i);
					
					postoji = true;
					break;
				}
			}
			if(!postoji){
			/*MyApp.getInstance().getTabPanel().addTab(parent.getTable().getIdLabelTable(),parent);
			MyApp.getInstance().getTabPanel().setTabComponentAt(0, new Tab(parent.getTableRows(), parent.getTable()));
			MyApp.getInstance().getTabPanel().revalidate();
			MyApp.getInstance().getTabPanel().repaint();*/
				Table t = MyApp.getInstance().getTableById(parent.getTable().getIdLabelTable());
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
				
			}
			
			}else if(tab.getTable().getParentsOfTable().size()>1){
				
				ChooseParentDialog cpd =  ChooseParentDialog.getInstance();
				cpd.setKoJeKliknuo(tab.getTable().getIdLabelTable());
				cpd.getCombo().removeAllItems();
				cpd.getCombo().revalidate();
				cpd.getCombo().repaint();
				for(int i=0;i<tab.getTable().getParentsOfTable().size();i++){
				cpd.getCombo().addItem(tab.getTable().getParentsOfTable().get(i));
				}
				
				cpd.setVisible(true);
	
		}

		}
	}

}
