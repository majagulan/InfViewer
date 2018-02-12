package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import editorSeme.textEditor;

/**
 * 
* Nasledjuje {@link JToolBar} klasu.
* Sadrzi dugme za Editor Json Seme 
* @see JButton
*
*/
@SuppressWarnings("serial")
public class ToolBar extends JToolBar {
	
	static JButton toolBarTextEditorAction;
	
	/**
	 * Konstruktor bez parametara
	 */
	
	public ToolBar(){
		
		super(SwingConstants.HORIZONTAL);
		
		toolBarTextEditorAction = new JButton();
		toolBarTextEditorAction.setToolTipText("Editor");
		
		toolBarTextEditorAction.addActionListener(new ActionListener(){

			@Override
			/**
			 * Inicijalizuje editor
			 */
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				textEditor editor = textEditor.getInstance();
				editor.setVisible(true);
				
			}
			
		});
		
	    toolBarTextEditorAction.setIcon(new ImageIcon("src/images/edit-json.png"));
		add(toolBarTextEditorAction);


	}

}
