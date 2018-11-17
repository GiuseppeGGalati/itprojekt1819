package de.hdm.itprojekt.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.itprojekt.client.LoginInfo;
import de.hdm.itprojekt.shared.LoginService;
import de.hdm.itprojekt.shared.LoginServiceAsync;
import de.hdm.itprojekt.shared.bo.Nutzer;

public class Menubar extends MenuBar{

	
	private LoginInfo loginInfo = null;

	private MenuBar menubarRightSide = new MenuBar();
	private MenuBar menubar = new MenuBar();
	private Anchor signOutLink = new Anchor("Logout");
	private Button meinProfil = new Button("Mein Profil");
	private HorizontalPanel hpanel = new HorizontalPanel();
	
	
	public Menubar() {
		
		RootPanel.get("menubar").clear();

		run();

	}
	
	public void run(){
		
		LoginServiceAsync loginService = GWT.create(LoginService.class);
		loginService.login(GWT.getHostPageBaseURL() + "Itprojekt1819.html", new LoginCallback());
		
		menubar.addSeparator();
		menubar.setStylePrimaryName("menuBarImage");
		menubarRightSide.addItem("Logout", new Command(){
			public void execute() {
				signOutLink.setHref(loginInfo.getLogoutUrl());
				Window.open(signOutLink.getHref(), "_self", "");
			}
		});
		meinProfil.addClickHandler(new meinProfilHandler());
		hpanel.add(meinProfil);
		hpanel.add(menubar);
		hpanel.add(menubarRightSide);

		RootPanel.get("menubar").clear();
		RootPanel.get("menubar").add(hpanel);
		
	}

	class LoginCallback implements AsyncCallback<LoginInfo> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Der Vorgang konnte nicht abgeschlossen werden: " + caught.getMessage());
		}

		@Override
		public void onSuccess(LoginInfo result) {
			loginInfo = result;
		}
	}
	
	class meinProfilHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
		
		DialogBoxNutzerUpdate dialogBoxNutzerUpdate = new DialogBoxNutzerUpdate();
		RootPanel.get("content").clear();
		RootPanel.get("content").add(dialogBoxNutzerUpdate);
		}
		
	}
}
