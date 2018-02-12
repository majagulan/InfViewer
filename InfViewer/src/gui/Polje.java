package gui;

import java.util.ArrayList;

import javax.swing.JTextField;

/**
 * 
 * Pomocna klasa koja nam pomaze da cuvamo sve informacije sa gui-a
 *
 */
public class Polje {
	
	
	private ArrayList<JTextField> listaPolja;
	private ArrayList<Integer> position;
	private ArrayList<String> type;
	
	/**
	 * Konstuktor bez parametara
	 */
	
	public Polje(){
		listaPolja = new ArrayList<>();
		position = new ArrayList<>();
		type = new ArrayList<>();
	}

	/**
	 * Konstuktor sa parametrima
	 * @param listaPolja
	 * @param position
	 * @param type
	 */
	
	/**
	 * Konstruktor sa parametrima
	 * @param listaPolja
	 * @param position
	 * @param type
	 */
	
	public Polje(ArrayList<JTextField> listaPolja, ArrayList<Integer> position,ArrayList<String> type) {
		this.listaPolja = listaPolja;
		this.position = position;
		this.type = type;
	}

	/**
	 * Preuzimanje vrednosti komponente listaPolja.
	 * @return listaPolja
	 */
	
	public ArrayList<JTextField> getListaPolja() {
		return listaPolja;
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente listaPolja.
	 *  @param listaPolja
	 *  @return 
	 */

	public void setListaPolja(ArrayList<JTextField> listaPolja) {
		this.listaPolja = listaPolja;
	}

	/**
	 * Preuzimanje vrednosti komponente position.
	 * @return position
	 */
	
	public ArrayList<Integer> getPosition() {
		return position;
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente position.
	 *  @param position
	 *  @return 
	 */

	public void setPosition(ArrayList<Integer> position) {
		this.position = position;
	}

	/**
	 * Preuzimanje vrednosti komponente type.
	 * @return type
	 */
	
	public ArrayList<String> getType() {
		return type;
	}

	/**
	 *  Dodeljivanje vrednosti komponente type.
	 *  @param type
	 *  @return 
	 */
	
	public void setType(ArrayList<String> type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Polje [listaPolja=" + listaPolja + ", position=" + position + ", type=" + type + "]";
	}


}
