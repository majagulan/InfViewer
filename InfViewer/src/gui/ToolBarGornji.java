package gui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JToolBar;

import crudActions.AddAction;
import crudActions.DeleteAction;
import crudActions.DepromoteAction;
import crudActions.SearchAction;
import crudActions.UpdateAction;

/**
 * 
* Nasledjuje {@link JToolBar} klasu.
* Sadrzi komponete {@link JButton} za odabir CRUD akcija i promote
*
*/


@SuppressWarnings("serial")
public class ToolBarGornji extends JToolBar {
	
	private JButton toolBarAddAction;
	private PocetniDijalog pocetniDijalog;
	private JButton toolBarUpdateAction;
	
	/**
	 * Konstruktor {@link ToolBarGornji } klase
	 * 
	 */
	
	public ToolBarGornji(){
		
		AddAction addAction = new AddAction(0);
		toolBarAddAction = new JButton(addAction);
		toolBarAddAction.setSize(new Dimension(22, 220));
		toolBarAddAction.setHideActionText(true);
		
		
		DeleteAction deleteAction = new DeleteAction(0);
		JButton toolBarDeleteAction = new JButton(deleteAction);
		toolBarDeleteAction.setSize(new Dimension(22, 220));
		toolBarDeleteAction.setHideActionText(true);
		
		UpdateAction updateAction = new UpdateAction(0);
		toolBarUpdateAction = new JButton(updateAction);
		toolBarUpdateAction.setSize(new Dimension(22, 220));
		toolBarUpdateAction.setHideActionText(true);

		DepromoteAction depromoteAction = new DepromoteAction();
		JButton toolBarDepromoteAction = new JButton(depromoteAction);
		toolBarDepromoteAction.setSize(new Dimension(22, 220));
		toolBarDepromoteAction.setHideActionText(true);
		
		SearchAction searchAction = new SearchAction(0);
		JButton toolBarSearchAction = new JButton(searchAction);
		toolBarSearchAction.setSize(new Dimension(22, 220));
		toolBarSearchAction.setHideActionText(true);
	    pocetniDijalog = new PocetniDijalog();
		
		if ((MyApp.getKorisnickoIme().equals("admin"))) {
			add(toolBarAddAction);
			add(toolBarDeleteAction);
			add(toolBarUpdateAction);
		}
		else {	
			repaint();
			add(toolBarAddAction);
			add(toolBarDeleteAction);
			add(toolBarUpdateAction);
			add(toolBarDepromoteAction);
			add(toolBarSearchAction);
			validate();
		}
	}
	
	/**
	 * Preuzimanje vrednosti komponente toolBarAddAction.
	 * @return toolBarAddAction
	 */
	
	public JButton getToolBarAddAction() {
		return toolBarAddAction;
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente toolBarAddAction.
	 *  @param toolBarAddAction
	 *  @return void
	 */

	public void setToolBarAddAction(JButton toolBarAddAction) {
		this.toolBarAddAction = toolBarAddAction;
	}
	
	/**
	 * Preuzimanje vrednosti komponente toolBarUpdateAction.
	 * @return toolBarUpdateAction
	 */

	public JButton getToolBarUpdateAction() {
		return toolBarUpdateAction;
	}

	/**
	 *  Dodeljivanje vrednosti komponente toolBarUpdateAction.
	 *  @param toolBarUpdateAction
	 *  @return void
	 */
	
	public void setToolBarUpdateAction(JButton toolBarUpdateAction) {
		this.toolBarUpdateAction = toolBarUpdateAction;
	}


}
