package de.hdm.itprojekt.shared.bo;

import java.util.Date;

public class Kommentar extends BusinessObject{

	/**
	 * Dient zum Serialisieren von Objekten für eine RPC fähigen Austausch zwischen Server und Client.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Deklarieren der Variablen
	 */
	private int textbeitragID = 0;
	
	private int nutzerID = 0;
	
	private String inhalt = "";
	
	private Date erzeugungsdatum = null;
	
	private Date modifikationsdatum = null;
	
	/**
	 * Methoden der Klasse Kommentar
	 */
	public int getTextbeitragID() {
		return textbeitragID;
	}

	public void setTextbeitragID(int textbeitragID) {
		this.textbeitragID = textbeitragID;
	}

	public int getNutzerID() {
		return nutzerID;
	}

	public void setNutzerID(int nutzerID) {
		this.nutzerID = nutzerID;
	}

	public String getInhalt() {
		return inhalt;
	}

	public void setInhalt(String inhalt) {
		this.inhalt = inhalt;
	}

	public Date getErzeugungsdatum() {
		return erzeugungsdatum;
	}

	public void setErzeugungsdatum(Date erzeugungsdatum) {
		this.erzeugungsdatum = erzeugungsdatum;
	}

	public Date getModifikationsdatum() {
		return modifikationsdatum;
	}

	public void setModifikationsdatum(Date modifikationsdatum) {
		this.modifikationsdatum = modifikationsdatum;
	}
}
