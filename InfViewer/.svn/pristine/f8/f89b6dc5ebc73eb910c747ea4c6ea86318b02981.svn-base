package model;

import java.util.ArrayList;

/**
 * Klasa koja modeluje jedan red u tabeli. Njen zadatak je da enkapsulira 
 * sve vrednosti koje određuje jedan red tabele.
 *
 */

public class Row {
	
	private ArrayList<String> nazivPolja;	//kljuc je naziv kolone vrednost je vrednost tog polja
	private ArrayList<Object> sadrzajPolja;
	
	/**
	 * Konstruktor bez parametara {@link Row}.
	 * @see ArrayList
	 */
	
	public Row() {
		nazivPolja = new ArrayList<>();
		sadrzajPolja = new ArrayList<>();
	}
	
	/**
	 * Dodavanje naziva polja u {@link nazivPolja}
	 * @return void
	 */

	public void addUListuNaziva(String naziv){
		nazivPolja.add(naziv);		
	}
	
	/**
	 * Dodavanje sadrzaj polja u {@link sadrzajPolja}.
	 * @return void
	 */
	
	public void addUListuSadrzaja(Object obj){
		sadrzajPolja.add(obj);
	}
	
	/**
	 * Preuzimanje vrednosti komponente nazivPolja.
	 * @return nazivPolja
	 */

	public ArrayList<String> getNazivPolja() {
		return nazivPolja;
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente nazivPolja.
	 *  @param nazivPolja
	 *  @return void
	 */

	public void setNazivPolja(ArrayList<String> nazivPolja) {
		this.nazivPolja = nazivPolja;
	}

	/**
	 * Preuzimanje vrednosti komponente sadrzajPolja.
	 * @return sadrzajPolja
	 */
	
	public ArrayList<Object> getSadrzajPolja() {
		return sadrzajPolja;
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente sadrzajPolja.
	 *  @param sadrzajPolja
	 *  @return void
	 */

	public void setSadrzajPolja(ArrayList<Object> sadrzajPolja) {
		this.sadrzajPolja = sadrzajPolja;
	}	
	

}
