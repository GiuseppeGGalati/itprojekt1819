package de.hdm.itprojekt.client.gui;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.shared.SocialMediaAdminAsync;

/**
 * Die Klasse StartseiteForm fungiert als Startseite. Erbt von der Basis-Klasse
 * MainFrame
 * 
 * @author giuseppegalati
 *
 */
public class StartseiteForm extends MainFrame {

	/**
	 * Instanziierung des Proxy Objekts für den Editor
	 */
	private static SocialMediaAdminAsync socialMediaVerwaltung = de.hdm.itprojekt.client.ClientsideSettings
			.getSocialMediaVerwaltung();
	
	/**
	 * Instanziierung der GUI Elemente
	 */
	private VerticalPanel vpanel = new VerticalPanel();
	private Anchor signOutLink = new Anchor("Logout");
	private HTML headline = new HTML("Willkommen auf der Startseite");

	public StartseiteForm(){
	    super.onLoad();

		
	}
	

	@Override
	protected void run() {
		
		AllAbonnementView allAbonnementview = new AllAbonnementView();
		vpanel.add(headline);
		vpanel.add(signOutLink);
		RootPanel.get("content").add(vpanel);

		
		
	}

}
