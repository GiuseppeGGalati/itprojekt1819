package de.hdm.itprojekt.client.gui;

import com.google.gwt.i18n.client.Constants;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.client.ClientsideSettings;
import de.hdm.itprojekt.shared.SocialMediaAdminAsync;

public class Toolbar extends LeftSideFrame{

	  /**
	   * The constants used in this Content Widget.
	   */
	  public static interface CwConstants extends Constants {
	    String cwCustomButtonDescription();

	    String cwCustomButtonName();

	    String cwCustomButtonPush();
	  }
	/**
	 * Instanziierung des Proxy Objekts für den Editor
	 */
	private static SocialMediaAdminAsync socialmediaVerwaltung = ClientsideSettings.getSocialMediaVerwaltung();
	

	/**
	 * Instanzierung der GUI-Elemente
	 */
	private VerticalPanel vpanel = new VerticalPanel();
	
	
	@Override
	protected void run() {
		 
		final CwConstants constants = null;
		vpanel.setSpacing(10);
		
	    // Combine the panels
	    vpanel.add(new HTML(constants.cwCustomButtonPush()));
	    
	    // Add a normal PushButton
	    PushButton normalPushButton = new PushButton(new Image());
	    normalPushButton.ensureDebugId("cwCustomButton-push-normal");
	    vpanel.add(normalPushButton);
	    
	    // Add a disabled PushButton
	    PushButton disabledPushButton = new PushButton(
	        new Image());
	    disabledPushButton.ensureDebugId("cwCustomButton-push-disabled");
	    disabledPushButton.setEnabled(false);
	    
	    vpanel.add(disabledPushButton);
		this.add(vpanel);
		RootPanel.get("leftmenutree").add(vpanel);

	    

		

	
	}

}
