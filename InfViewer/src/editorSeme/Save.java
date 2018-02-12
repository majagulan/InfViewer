package editorSeme;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.AbstractAction;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import gui.MyApp;
import parsing.JSONParser;
import tree.Tree;

/**
 *  <code>Save<code> klasa putem koje korisnik snima izmenjen editor JSON seme. 
 * <p> Nasledjuje  {@link AbstractAction} klasu.</p> 
 */

public class Save extends AbstractAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JSONParser jsonParser;
	
	/**
	 * Konstruktor bez parametara {@link Save}.
	 */

	public Save(){
		putValue(NAME, MyApp.getInstance().getResourceBundle().getString("btnSnimi"));
		putValue(MNEMONIC_KEY, KeyEvent.VK_S);
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

	}
	
	/**
	 * Poziva parsiranje nove JSON seme i snima izmene u editoru seme.
	 * @see ActionEvent
	 * @throws Exception
	 * @param e
	 * @return void
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			jsonParser = new JSONParser();	
			jsonParser.setJsonFile(textEditor.getInstance().getTextArea().getText());	
			jsonParser.parseJsonIzKlaseSave();	
			
			if (JSONParser.nemojSnimiti == true){
				return;
			}
			
			
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("JSONprimer.json")));
			out.write(textEditor.getInstance().getTextArea().getText());
	        out.close();
	        
	        
	        MyApp.getInstance().getTree();
			Tree.destroy();
	       
	        MyApp.getInstance().initializeTree();

	        JScrollPane scroll = new JScrollPane(MyApp.getInstance().getTree(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
	 			   JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	 	    scroll.setPreferredSize(new Dimension(250, 500));
	 	    MyApp.getInstance().add(scroll, BorderLayout.WEST);
	 	  
			MyApp myApp = MyApp.getInstance();
			myApp.getInstance().getScroll().setVisible(false);
			myApp.setVisible(true);
	 
			 
		}
		
		catch (Exception ex) {
		}
		
	}

}
