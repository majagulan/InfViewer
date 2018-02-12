package main;

import gui.MyApp;

/**
 * Main klasa za pokretanje aplikacije.
 */

public class Main {
	
	/** 
	 * Pokrece rad aplikacije.
	 * @param args
	 * @return void
	 */

	public static void main(String[] args) {
		
		MyApp myApp = MyApp.getInstance();
		myApp.setVisible(true);
		
		
	}

}
