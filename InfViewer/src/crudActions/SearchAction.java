package crudActions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import gui.SearchDialog;

/**
 * Nasledjuje {@link AbstractAction} klasu.
 * <code>SearchAction</code> je klasa koja poziva metodu {@link DataBaseSearch#search} koja dodaje sluzi za pretragu.
 * @see AbstractAction
 *
 */

@SuppressWarnings("serial")
public class SearchAction extends AbstractAction {
	private int kojeDugme;
	
	/**
	 * Konstruktor sa parametrima {@link SearchAction} klase
	 * @param kojeDugme
	 */
	
	public SearchAction(int kojeDugme){
		putValue(NAME, "Search");
		putValue(SHORT_DESCRIPTION, "Search");
		putValue(SMALL_ICON, new ImageIcon("src/images/search.png"));
		this.kojeDugme = kojeDugme;
		
	}

	/**
	 * Metoda koja poziva metodu {@link DataBaseSearch#search}.
	 * @see ActionEvent
	 */
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		DataBaseSearch dbs = new DataBaseSearch(kojeDugme);
		dbs.search();
		
	}

}
