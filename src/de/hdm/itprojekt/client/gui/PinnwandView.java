package de.hdm.itprojekt.client.gui;

import de.hdm.itprojekt.shared.SocialMediaAdminAsync;

/**
 * Die Klasse PinnwandView fungiert als Startseite. Erbt von der Basis-Klasse
 * MainFrame
 * 
 * @author giuseppegalati
 *
 */
public class PinnwandView extends MainFrame {

	/**
	 * Instanziierung des Proxy Objekts für den Editor
	 */
	private static SocialMediaAdminAsync socialMediaVerwaltung = de.hdm.itprojekt.client.ClientsideSettings
			.getSocialMediaVerwaltung();

	@Override
	protected void run() {
		// TODO Auto-generated method stub

	}

}
