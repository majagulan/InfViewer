package model;

/**
 * Klasa koja predstavlja odnos izmedju dve tabele,
 * kao i uvid u to koje kolone tabela se mapiraju jedna na drugu.
 */

public class Relations {	

	private String parentColumn;
	private String childColumn;
	private String kodStranogKljuca;
	
	/**
	 * Konstruktor sa parametrima {@link Relations}.
	 * @param parentColumn
	 * @param childColumn
	 * @param kodStranogKljuca
	 */
	
	public Relations(String parentColumn, String childColumn,String kodStranogKljuca) {
		this.parentColumn = parentColumn;
		this.childColumn = childColumn;
		this.kodStranogKljuca = kodStranogKljuca;
	}
	
	/**
	 * Preuzimanje vrednosti komponente kodStranogKljuca.
	 * @return kodStranogKljuca
	 */
	
	public String getKodStranogKljuca() {
		return kodStranogKljuca;
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente kodStranogKljuca.
	 *  @param kodStranogKljuca
	 *  @return void
	 */
	
	public void setKodStranogKljuca(String kodStranogKljuca) {
		this.kodStranogKljuca = kodStranogKljuca;
	}
	
	/**
	 * Preuzimanje vrednosti komponente parentColumn.
	 * @return parentColumn
	 */
	
	public String getParentColumn() {
		return parentColumn;
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente parentColumn.
	 *  @param parentColumn
	 *  @return void
	 */
	
	public void setParentColumn(String parentColumn) {
		this.parentColumn = parentColumn;
	}
	
	/**
	 * Preuzimanje vrednosti komponente childColumn.
	 * @return childColumn
	 */
	
	public String getChildColumn() {
		return childColumn;
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente childColumn.
	 *  @param childColumn
	 *  @return void
	 */
	
	public void setChildColumn(String childColumn) {
		this.childColumn = childColumn;
	}
	
	@Override
	public String toString() {
		return "Relations [parentColumn=" + parentColumn + ", childColumn=" + childColumn + "]";
	}
		
}
