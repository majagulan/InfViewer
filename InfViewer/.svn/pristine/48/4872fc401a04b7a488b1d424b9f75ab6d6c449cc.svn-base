package editorSeme;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;
import javax.swing.undo.UndoManager;

import gui.MyApp;


/**
 *  <code>textEditor<code> klasa putem koje korisnik komunicira sa editorom JSON seme. 
 * <p> Nasledjuje  {@link JFrame} klasu i bazira se na <code> Singlton </code> dizajn sablonu.</p>
 */

public class textEditor extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Preuzimanje vrednosti komponente TextArea.
	 * @see JTextArea
	 * @return textArea
	 */
	
	public JTextArea getTextArea() {
		return textArea;
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente TextArea.
	 *  @see JTextArea
	 *  @param textArea
	 *  @return void
	 */

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	private JTextArea textArea = new JTextArea();
	private JTextArea linije = new JTextArea("1");
    private JScrollPane areaScrollPane;
    private JMenuBar menuBar = new JMenuBar();    
    private JMenu operacije = new JMenu();
    
    private UndoManager manager = new UndoManager();
    
    /**
	 * Preuzimanje vrednosti komponente UndoManager.
	 * @see UndoManager
	 * @return manager
	 */
    
    public UndoManager getManager() {
		return manager;
	}
    
    /**
	 *  Dodeljivanje vrednosti komponente UndoManager.
	 *  @see UndoManager
	 *  @param manager
	 *  @return void
	 */

	public void setManager(UndoManager manager) {
		this.manager = manager;
	}

	/**
	 * Instanca koja obezbedjuje implementaciju <code> Singlton </code> sablona.
	 */
	
	private static textEditor instance = null;
	
	/**
	 * Preuzimanje vrednosti komponente instance.
	 * @return instance
	 */
	
    public static textEditor getInstance() {
		if (instance == null)
		{
			instance = new textEditor();
			instance.initTextEditor();
		}

		return instance;
	}
    
    /**
	 * Inicijalizovanje komponenti glavnog prozora editora JSON seme 
	 * @throws IOException - ukoliko nije ucitan pravilno JSON
	 * @return void
	 */
    
    public void initTextEditor() {
    	
    	
        this.setSize(700, 500);
        this.setTitle(MyApp.getInstance().getResourceBundle().getString("naslovtextEditor"));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(textArea);
        this.areaScrollPane = new JScrollPane(textArea);
        this.areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.areaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.areaScrollPane.setPreferredSize(new Dimension(250, 250));
        this.areaScrollPane.setRowHeaderView(linije);
        this.getContentPane().add(areaScrollPane);
 
        this.setJMenuBar(this.menuBar);
        this.menuBar.add(this.operacije);     
        
        linije.setBackground(Color.YELLOW);
		
        this.operacije.setText(MyApp.getInstance().getResourceBundle().getString("tekstOperacije"));
      
        operacije.add(menadzerAkcija.getInstance().getSave());
        operacije.add(menadzerAkcija.getInstance().getValidate());
        operacije.add(menadzerAkcija.getInstance().getClose());

        try {
			BufferedReader fileOut = new BufferedReader(new FileReader("JSONprimer.json"));    	  
			textArea.read(fileOut, fileOut);
			fileOut.close(); 
		} 
        
        catch (IOException e) {
		}  	    	    
	    
        
        textArea.getDocument().addDocumentListener(new DocumentListener(){
        	
        	public String getText(){
    			int caretPosition = textArea.getDocument().getLength();
    			Element root = textArea.getDocument().getDefaultRootElement();
    			String text = "1" + System.getProperty("line.separator");
    			for(int i = 2; i < root.getElementIndex( caretPosition ) + 2; i++){
    				text += i + System.getProperty("line.separator");
    			}
    			return text;
    		}

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				//linije.setText(getText());
				
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				linije.setText(getText());
				
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				//linije.setText(getText());
				
			}
        
        });
		
        textArea.addMouseListener(new MouseListener(){


			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				textArea.getHighlighter().removeAllHighlights();
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
        this.areaScrollPane.setRowHeaderView(linije);
        textArea.getDocument().addUndoableEditListener(manager);
        
    }
   

}
