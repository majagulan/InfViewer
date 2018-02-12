package crudActions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

/**
 * Nasledjuje {@link AbstractAction} klasu.
 * <code>AddAction</code> je klasa koja poziva metodu {@link DataBaseCRUD#add} koja dodaje nove torke u tabelu.
 * @see AbstractAction
 */

@SuppressWarnings("serial")
public class AddAction extends AbstractAction{
	
	private int kojeDugme;
	
	/**
	 * Konstruktor sa parametrima {@link AddAction} klase
	 * @param kojeDugme
	 */
	
	public AddAction(int kojeDugme){
		
		putValue(NAME, "Add");
		putValue(SHORT_DESCRIPTION, "Add new item");
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_MASK));
		putValue(SMALL_ICON, new ImageIcon("src/images/addIcon.png"));
		this.kojeDugme = kojeDugme;
		
	}

	/**
	 * Metoda koja poziva metodu {@link DataBaseCRUD#add}.
	 * @see ActionEvent
	 */
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		DataBaseCRUD dbc = new DataBaseCRUD(kojeDugme);
		dbc.add();
	}
}
