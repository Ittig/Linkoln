package it.cnr.ittig.linkoln.ref;

public abstract class LegalRef {

	/**
	 * Classe astratta che rappresenta un generico riferimento normativo
	 * relativo ad una citazione testuale.
	 * 
	 */
	
	//Campi normalizzati che compongono il riferimento
	protected String tipo = "";
	
	protected String emanante = "";
	
	protected String numero = "";
	
	protected String mixed = "";

	protected String anno = "";
	
	protected String data = ""; 
	
	protected String partizione = "";
	
	//il testo di questa specifica citazione
	protected String text = "";
	
	//il testo complessivo all'interno di una citazione multipla
	protected String mtext = "";
	
	//id del riferimento
	protected String id = "";
	
	//Metodi di serializzione - Da sovrascrivere nelle varie implementazioni
	public abstract String getUrnLex();
	
	//Personalizzione del tag <a href...> nel codice Html
	public abstract String getUrnLexHtml();
	
	public abstract String getCelex();
	
	public abstract String getCelexHtml();
	
	public abstract String getEli();
	
	public abstract String getEliHtml();

	public abstract String getCode(); //può essere un URN, CELEX o ELI
	
	public abstract String getHtml(); //può essere il metodo html per URN, CELEX o ELI
	
	//Getters and Setters
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEmanante() {
		return emanante;
	}

	public void setEmanante(String emanante) {
		this.emanante = emanante;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getMixed() {
		return mixed;
	}

	public void setMixed(String mixed) {
		this.mixed = mixed;
	}

	public String getAnno() {
		return anno;
	}

	public void setAnno(String anno) {
		this.anno = anno;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getPartizione() {
		return partizione;
	}

	public void setPartizione(String partizione) {
		this.partizione = partizione;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public String getMtext() {
		return mtext;
	}

	public void setMtext(String mtext) {
		this.mtext = mtext;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
