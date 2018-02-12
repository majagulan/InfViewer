package crudActions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

/**
 * Nasledjuje {@link AbstractAction} klasu.
 * <code>UpdateAction</code> je klasa koja poziva metodu {@link DataBaseCRUD#update} koja dodaje nove torke u tabelu.
 * @see AbstractAction
 *
 */


@SuppressWarnings("serial")
public class UpdateAction extends AbstractAction{
	private int kojeDugme;
	
	/**
	 * Konstruktor sa parametrima {@link UpdateAction} klase
	 * @param kojeDugme
	 */
	
	public UpdateAction(int kojeDugme){
		
		putValue(NAME, "Update");
		putValue(SHORT_DESCRIPTION, "Update choosen item");
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_U, KeyEvent.CTRL_MASK));
		putValue(SMALL_ICON, new ImageIcon("src/images/updateIcon.png"));
		this.kojeDugme  = kojeDugme;
	}

	/**
	 * Metoda koja poziva metodu {@link DataBaseCRUD#update}.
	 * @see ActionEvent
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		DataBaseCRUD dbc = new DataBaseCRUD(kojeDugme);
		dbc.update();
	}

}
