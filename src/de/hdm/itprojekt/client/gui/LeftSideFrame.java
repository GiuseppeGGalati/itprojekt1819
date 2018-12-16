package de.hdm.itprojekt.client.gui;

import com.google.gwt.user.client.ui.HorizontalPanel;

/**
 * Abstrakte Klasse für das LeftSideFrame, somit für die Baumstruktur.
 * 
 * @author giuseppegalati
 *
 */
public abstract class LeftSideFrame extends HorizontalPanel {
	/**
	 * Automatisch geladene Methode onLoad(). Löscht den Div-Container content
	 * und ruft die Methode run() auf.
	 */

	public void onLoad() {

		/*
		 * Bevor wir unsere eigene Formatierung veranslassen, überlassen wir es
		 * der Superklasse eine Initialisierung vorzunehmen.
		 */
		super.onLoad();
		// RootPanel.get("leftmenutree").clear();

		this.run();
	}

	protected abstract void run();

}
