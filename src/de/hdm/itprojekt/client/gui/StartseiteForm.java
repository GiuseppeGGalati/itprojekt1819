package de.hdm.itprojekt.client.gui;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class StartseiteForm extends VerticalPanel{


	public StartseiteForm() {
		/**
		 * Anlegen der GUI-Elemente
		 */
		VerticalPanel homePanel = new VerticalPanel();		
		Label welcomeLabel = new Label("Herzlich Willkommen auf deiner Startseite");
		

		homePanel.setSpacing(10);
		this.setSpacing(8);
		homePanel.add(welcomeLabel);
		this.add(homePanel);
		RootPanel.get("content").clear();
		RootPanel.get("content").add(homePanel);
	}

}