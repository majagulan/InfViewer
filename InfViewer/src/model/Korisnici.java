package model;

import java.util.ArrayList;

/**
 * Klasa koja se sluzi za modelovanje korisnika koji se nalaze u bazi.
 *
 */

public class Korisnici {

	private String username;
	private String pass;
	
	/**
	 * Konstruktor sa parametrima {@link  Korisnici}.
	 * @param username
	 * @param pass
	 */
	
	public Korisnici(String username, String pass) {
		
		this.username = username;
		this.pass = pass;
	}
	/**
	 * Preuzimanje vrednosti komponente username.
	 * @return username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 *  Dodeljivanje vrednosti komponente username.
	 *  @param username
	 *  @return void
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * Preuzimanje vrednosti komponente pass.
	 * @return pass
	 */
	public String getPass() {
		return pass;
	}
	/**
	 *  Dodeljivanje vrednosti komponente pass
	 *  @param pass
	 *  @return void
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public String toString() {
		return "Korisnici [username=" + username + ", pass=" + pass + "]";
	}
		
	
}
