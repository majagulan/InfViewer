package crudActions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

/**
 * Nasledjuje {@link AbstractAction} klasu.
 * <code>DeleteActopn</code> je klasa koja poziva metodu {@link DataBaseCRUD#delete} koja brise torke iz tabele.
 * @see AbstractAction
 *
 */

@SuppressWarnings("serial")
public class DeleteAction extends AbstractAction{
	public int kojeDugme;
	
	/**
	 * Konstruktor sa parametrima {@link DeleteAction} klase
	 * @param kojeDugme
	 */
	
	public DeleteAction(int kojeDugme){
		
		putValue(NAME, "Delete");
		putValue(SHORT_DESCRIPTION, "Delete choosen item");
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_K, KeyEvent.CTRL_MASK));
		putValue(SMALL_ICON, new ImageIcon("src/images/deleteIcon.png"));
		this.kojeDugme  = kojeDugme;
	}


	/**
	 * Metoda koja poziva metodu {@link DataBaseCRUD#delete}.
	 * @see ActionEvent
	 */
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		DataBaseCRUD dbs = new DataBaseCRUD(kojeDugme);
		dbs.delete();
		
	}
}
