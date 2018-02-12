package model;

/**
 * Klasa koja modeluje jednu kolonu u tabeli. Njen zadatak je da enkapsulira sve vrednosti koje 
 * određuju jednu kolonu tabele.
 */

public class Column {


	private String nameColumn;
	private String idLabelColmun;
	private String dataType;
	private String password;
	private int dataPrecision;
	private int dataLength;
	
	private boolean primaryKey;
	private boolean nullValue;
	private boolean mandatory;
	
	/**
	 * Konstruktor bez parametara {@link Column}.
	 */
	
	public Column() {
		super();
	}

	/**
	 * Preuzimanje vrednosti komponente nameColumn.
	 * @return nameColumn
	 */

	public String getNameColumn() {
		return nameColumn;
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente nameColumn.
	 *  @param nameColumn
	 *  @return void
	 */

	public void setNameColumn(String nameColumn) {
		this.nameColumn = nameColumn;
	}
	
	/**
	 * Preuzimanje vrednosti komponente idLabelColmun.
	 * @return idLabelColmun
	 */

	public String getIdLabelColmun() {
		return idLabelColmun;
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente idLabelColmun.
	 *  @param idLabelColmun
	 *  @return void
	 */

	public void setIdLabelColmun(String idLabelColmun) {
		this.idLabelColmun = idLabelColmun;
	}
	
	/**
	 * Preuzimanje vrednosti komponente dataType.
	 * @return dataType
	 */

	public String getDataType() {
		return dataType;
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente dataType.
	 *  @param dataType
	 *  @return void
	 */

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	/**
	 * Preuzimanje vrednosti komponente dataPrecision.
	 * @return dataPrecision
	 */

	public int getDataPrecision() {
		return dataPrecision;
	}

	/**
	 *  Dodeljivanje vrednosti komponente dataPrecision.
	 *  @param dataPrecision
	 *  @return void
	 */

	public void setDataPrecision(int dataPrecision) {
		this.dataPrecision = dataPrecision;
	}
	
	/**
	 * Preuzimanje vrednosti komponente dataLength.
	 * @return dataLength
	 */

	public int getDataLength() {
		return dataLength;
	}

	/**
	 *  Dodeljivanje vrednosti komponente dataLength.
	 *  @param dataLength
	 *  @return void
	 */

	public void setDataLength(int dataLength) {
		this.dataLength = dataLength;
	}
	
	/**
	 * Preuzimanje vrednosti komponente primaryKey.
	 * @return primaryKey
	 */

	public boolean isPrimaryKey() {
		return primaryKey;
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente primaryKey.
	 *  @param primaryKey
	 *  @return void
	 */

	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}
	
	/**
	 * Preuzimanje vrednosti komponente nullValue.
	 * @return nullValue
	 */

	public boolean isNullValue() {
		return nullValue;
	}
	
	/**
	 *  Dodeljivanje vrednosti komponente nullValue.
	 *  @param nullValue
	 *  @return void
	 */

	public void setNullValue(boolean nullValue) {
		this.nullValue = nullValue;
	}

	/**
	 *  Konstruktor sa parametrima {@link Column}.
	 *  @param nameColumn
	 *  @param idLabelColmun
	 *  @param dataType
	 *  @param dataPrecision
	 *  @param dataLength 
	 *  @param primaryKey
	 *  @param nullValue
	 */

	public Column(String nameColumn, String idLabelColmun, String dataType, int dataPrecision, int dataLength,
			boolean primaryKey, boolean nullValue) {
		super();
		this.nameColumn = nameColumn;
		this.idLabelColmun = idLabelColmun;
		this.dataType = dataType;
		this.dataPrecision = dataPrecision;
		this.dataLength = dataLength;
		this.primaryKey = primaryKey;
		this.nullValue = nullValue;
	}
	
	public Column(String nameColumn, String password) {
		super();
		this.nameColumn = nameColumn;
		this.password = password;
		
	}
	
	@Override
	public String toString() {
		return "Column [nameColumn=" + nameColumn + ", idLabelColmun=" + idLabelColmun + ", dataType=" + dataType
				+ ", dataPrecision=" + dataPrecision + ", dataLength=" + dataLength + ", primaryKey=" + primaryKey
				+ ", nullValue=" + nullValue + ", mandatory=" + mandatory + "]";
	}


	

}
