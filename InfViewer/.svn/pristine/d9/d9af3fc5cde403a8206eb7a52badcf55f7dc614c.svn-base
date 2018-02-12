package editorSeme;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import gui.MyApp;
import parsing.JSONParser;

/**
 *  <code>Validate<code> klasa putem koje se proverava sintaksna i semanticka ispravnost JSON seme. 
 * <p> Nasledjuje  {@link AbstractAction} klasu.</p>
 */

public class Validate extends AbstractAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JSONParser jsonParser; 
	
	/**
	 * Konstruktor bez parametara {@link Validate}.
	 */
	
	public Validate(){
		 putValue(NAME, MyApp.getInstance().getResourceBundle().getString("btnValidate"));
		 putValue(MNEMONIC_KEY, KeyEvent.VK_V);
		 putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		
	}
	
	/**
	 * Poziva parsiranje nove JSON seme i validira izmenjenu semu.
	 * @see ActionEvent
	 * @throws Exception
	 * @param e
	 * @return void
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		jsonParser = new JSONParser();	
		jsonParser.setJsonFile(textEditor.getInstance().getTextArea().getText());	
		jsonParser.parseJsonIzKlaseValidate();		
		
	}

}
