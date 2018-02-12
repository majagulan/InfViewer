package editorSeme;


/**
 * Klasa koja se sluzi za upravljanje svim akcijama za snimanje, validiranje i zatvaranje prozora editora seme
 * i bazira se na <code> Singlton </code> sablonu.
 *
 */

public class menadzerAkcija {
	
	private Save save;
	private Validate validate;
	private Close close;
	
	/**
	 * Preuzimanje vrednosti komponente Save.
	 * @return save
	 */
	
	public Save getSave() {
		return save;
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente Save.
	 *  @param save
	 *  @return void
	 */
	
	public void setSave(Save save) {
		this.save = save;
	}
	
	/**
	 * Preuzimanje vrednosti komponente Validate.
	 * @return validate
	 */
	
	public Validate getValidate() {
		return validate;
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente Save.
	 *  @param validate
	 *  @return void
	 */
	
	public void setValidate(Validate validate) {
		this.validate = validate;
	}
	
	/**
	 * Preuzimanje vrednosti komponente Save.
	 * @return close
	 */
	
	public Close getClose() {
		return close;
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente Save.
	 *  @param close
	 *  @return void
	 */
	
	public void setClose(Close close) {
		this.close = close;
	}
	
	/**
	 * Instanca koja obezbedjuje implementaciju <code> Singlton </code> sablona.
	 */
	
	private static menadzerAkcija instance;
	
	/**
	 * Preuzimanje vrednosti komponente instance.
	 * @return instance
	 */
	
	public static menadzerAkcija getInstance() {
		if (instance == null)
			instance = new menadzerAkcija();

		return instance;
	}
	
	/**
	 * Konstruktor bez parametara {@link menadzerAkcija}.
	 */
	
	public menadzerAkcija(){
		
		save= new Save();
		validate=new Validate();		
		close=new Close();
		
	}

}
