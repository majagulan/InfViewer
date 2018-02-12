package gui;

import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePickerImpl;

/**
 * Klasa <code>Time</code> implementira JDatePickerImpl
 * @see JDatePickerImpl
 *
 */

public class Time {
	
	private JDatePickerImpl datePicker;
	private JTextField sati;
	private JTextField minuti;
	private JTextField sekunde;
	
	/**
	 * Konstruktor sa parametrima klase {@link Time}
	 * @param datePicker
	 * @param sati
	 * @param minuti
	 * @param sekunde
	 */
	
	public Time(JDatePickerImpl datePicker, JTextField sati, JTextField minuti, JTextField sekunde) {
	
		this.datePicker = datePicker;
		this.sati = sati;
		this.minuti = minuti;
		this.sekunde = sekunde;
	}
	
	/**
	 * Preuzimanje vrednosti komponente datePicker.
	 * @return datePicker
	 */
	
	public JDatePickerImpl getDatePicker() {
		return datePicker;
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente datePicker.
	 *  @param datePicker
	 *  @return void
	 */
	
	public void setDatePicker(JDatePickerImpl datePicker) {
		this.datePicker = datePicker;
	}
	
	/**
	 * Preuzimanje vrednosti komponente sati.
	 * @return sati
	 */
	
	public JTextField getSati() {
		return sati;
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente sati.
	 *  @param sati
	 *  @return void
	 */
	
	public void setSati(JTextField sati) {
		this.sati = sati;
	}
	
	/**
	 * Preuzimanje vrednosti komponente minuti.
	 * @return minuti
	 */
	
	public JTextField getMinuti() {
		return minuti;
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente minuti.
	 *  @param minuti
	 *  @return void
	 */
	
	public void setMinuti(JTextField minuti) {
		this.minuti = minuti;
	}
	
	/**
	 * Preuzimanje vrednosti komponente sekunde.
	 * @return sekunde
	 */
	
	public JTextField getSekunde() {
		return sekunde;
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente sekunde.
	 *  @param sekunde
	 *  @return void
	 */
	
	public void setSekunde(JTextField sekunde) {
		this.sekunde = sekunde;
	}
	

}
