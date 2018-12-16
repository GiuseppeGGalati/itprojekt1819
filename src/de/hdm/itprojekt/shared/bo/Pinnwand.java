package de.hdm.itprojekt.shared.bo;

public class Pinnwand extends BusinessObject {

	/**
	 * Dient zum Serialisieren von Objekten für eine RPC fähigen Austausch
	 * zwischen Server und Client.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Deklarieren der Variablen
	 */
	private int nutzerID = 0;

	/**
	 * Methoden der Klasse Pinnwand
	 */
	public int getNutzerID() {
		return nutzerID;
	}

	public void setNutzerID(int nutzerID) {
		this.nutzerID = nutzerID;
	}
}
