package gui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JToolBar;

import crudActions.AddAction;
import crudActions.DeleteAction;
import crudActions.PromoteAction;
import crudActions.SearchAction;
import crudActions.UpdateAction;

/**
 * 
* Nasledjuje {@link JToolBar} klasu.
* Sadrzi komponete {@link JButton} za odabir CRUD akcija i promote
* @see PromoteAction 
*
*/

@SuppressWarnings("serial")
public class ToolBarDonji extends JToolBar {
	private JButton toolBarAddAction;
	private JButton toolBarUpdateAction;
	
	/**
	 * Konstruktor {@link ToolBarDonji } klase
	 * 
	 */
	
	public ToolBarDonji(){
	AddAction addAction = new AddAction(1);
	toolBarAddAction = new JButton(addAction);
	toolBarAddAction.setSize(new Dimension(22, 220));
	toolBarAddAction.setHideActionText(true);
	
	
	DeleteAction deleteAction = new DeleteAction(1);
	JButton toolBarDeleteAction = new JButton(deleteAction);
	toolBarDeleteAction.setSize(new Dimension(22, 220));
	toolBarDeleteAction.setHideActionText(true);
	
	UpdateAction updateAction = new UpdateAction(1);
	toolBarUpdateAction = new JButton(updateAction);
	toolBarUpdateAction.setSize(new Dimension(22, 220));
	toolBarUpdateAction.setHideActionText(true);

	PromoteAction promoteAction = new PromoteAction();
	JButton toolBarPromoteAction = new JButton(promoteAction);
	toolBarPromoteAction.setSize(new Dimension(22, 220));
	toolBarPromoteAction.setHideActionText(true);

	SearchAction searchAction = new SearchAction(1);
	JButton toolBarSearchAction = new JButton(searchAction);
	toolBarSearchAction.setSize(new Dimension(22, 220));
	toolBarSearchAction.setHideActionText(true);


	add(toolBarAddAction);
	add(toolBarDeleteAction);
	add(toolBarUpdateAction);
	add(toolBarPromoteAction);
	add(toolBarSearchAction);
	
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
