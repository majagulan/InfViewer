package editorSeme;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import gui.MyApp;

/**
 *  <code>Close<code> klasa putem koje korisnik moze da zatvori glavni prozor aplikacije. 
 * <p> Nasledjuje {@link AbstractAction} klasu.</p>
 */

public class Close extends AbstractAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor bez parametara {@link Close}.
	 */
	
	public Close(){
		putValue(NAME, MyApp.getInstance().getResourceBundle().getString("btnClose"));
		putValue(MNEMONIC_KEY, KeyEvent.VK_F4);
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.CTRL_MASK));

	}	
	
	/**
	 * Zatvara glavni prozor aplikacije
	 * @see ActionEvent
	 * @throws Exception
	 * @param arg0
	 * @return void
	 */
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		textEditor.getInstance().dispose();	
		
	}

}
