package de.hdm.itprojekt.client;

import de.hdm.itprojekt.shared.LoginService;
import de.hdm.itprojekt.shared.LoginServiceAsync;
import de.hdm.itprojekt.shared.SocialMediaAdminAsync;
import de.hdm.itprojekt.shared.bo.Nutzer;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
@SuppressWarnings("unused")
public class Itprojekt1819 implements EntryPoint {

	/**
	 * Deklarierung der Klasse LoginInfo für die Google API
	 */
	private LoginInfo loginInfo = null;

	/**
	 * Instanzierung der Objekte
	 */
	private VerticalPanel loginPanel = new VerticalPanel();
	private Label welcomeLabel = new Label("Herzlich Willkommen");
	private Label loginLabel = new Label("Bitte loggen Sie sich mit Ihrem Google Account ein.");
	private Button loginButton = new Button("Login");
	private Anchor signInAnchor = new Anchor();
	private Anchor signOutAnchor = new Anchor();

	/**
	 * Instanziierung des Proxys
	 */
	private static SocialMediaAdminAsync socialMediaVerwaltung = ClientsideSettings.getSocialMediaVerwaltung();

	//
	//
	// /**
	// * The message displayed to the user when the server cannot be reached or
	// * returns an error.
	// */
	// private static final String SERVER_ERROR = "An error occurred while "
	// + "attempting to contact the server. Please check your network " +
	// "connection and try again.";
	//
	// /**
	// * Create a remote service proxy to talk to the server-side Greeting
	// service.
	// */
	// private final GreetingServiceAsync greetingService =
	// GWT.create(GreetingService.class);
	//
	// /**
	// * This is the entry point method.
	// */

	/**
	 * onModuleLoad des Moduls: itprojekt1819 Ist die main-Methode in einem
	 * GWT-Projekt
	 */
	@Override
	public void onModuleLoad() {

		LoginServiceAsync loginService = GWT.create(LoginService.class);
		loginService.login(GWT.getHostPageBaseURL() + "itprojekt1819.html", new LoginCallback());
	}

	/**
	 * Die loadLogin() Methode wird verwendet für das Anzeigen der API
	 */
	private void loadLogin() {

		loginButton.addClickHandler(new loginButtonClickHandler());
		loginPanel.add(welcomeLabel);
		loginPanel.add(loginLabel);
		loginPanel.add(loginButton);
		RootPanel.get("content").add(loginPanel);

	}

	/**
	 * Die loadPinnwand() Methode wird aufgerufen wenn der User bereits
	 * existiert. Der User wird weitergeleitet auf die Pinnwand
	 */
	private void loadPinnwand() {

	}

	/**
	 * Nested Class für den Login Button
	 * 
	 */

	class loginButtonClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			signInAnchor.setHref(loginInfo.getLoginUrl());
			Window.open(signInAnchor.getHref(), "_self", "");
		}
	}

	/**
	 * Nested Class für den Login Callback In der onSuccess wird überprüft, ob
	 * der User eingeloggt. Wenn er eingeloggt ist, wird mit der checkEmail
	 * überprüft ob die E-Mail Adresse bereits in der Datenbank existiert.
	 */
	class LoginCallback implements AsyncCallback<LoginInfo> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Login");

		}

		@Override
		public void onSuccess(LoginInfo result) {
			loginInfo = result;
			if (loginInfo.isLoggedIn()) {
				socialMediaVerwaltung.checkEmail(loginInfo.getEmailAddress(), new FindNutzerCallback());
			} else {
				loadLogin();
			}
		}

	}

	/**
	 * Nested Class für den AsyncCallback checkEmail Wenn der User bereits
	 * existiert werden zwei Cookies erstellt und die Pinnwand geladen. Wenn
	 * nicht wird eine DialogBox geöffnet, für die Abfrage, ob der User sich
	 * registrieren will.
	 *
	 */
	class FindNutzerCallback implements AsyncCallback<Nutzer> {

		@Override
		public void onFailure(Throwable caught) {

			Window.alert("Fehler bei der Anmeldung" + caught.getMessage());
		}

		@Override
		public void onSuccess(Nutzer result) {

			if (result != null) {
				Cookies.setCookie("email", result.getEmail());
				Cookies.setCookie("vorname", result.getVorname());
				Cookies.setCookie("nachname", result.getNachname());
				Cookies.setCookie("nickname", result.getNickname());
				Cookies.setCookie("id", result.getId() + "");
				Cookies.setCookie("signout", loginInfo.getLogoutUrl());
				loadPinnwand();
			} else {
				CreateNutzerDialogBox nutzerDialogBox = new CreateNutzerDialogBox(loginInfo.getEmailAddress());
				nutzerDialogBox.center();
			}
		}

	}

	/**
	 * Nested Class einer DialogBox für die Nutzer Erstellung Abfrage.
	 *
	 */
	class CreateNutzerDialogBox extends DialogBox {

		/**
		 * Instanziierung der GUI-Elemente
		 */
		private Label frage = new Label(
				"Sie haben noch keinen Nutzer angelegt? Möchten Sie einen neuen Nutzer anlegen?");
		private Button ja = new Button("ja");
		private Button nein = new Button("nein");
		private VerticalPanel vpanel = new VerticalPanel();
		private HorizontalPanel buttonPanel = new HorizontalPanel();

		/**
		 * Instanziierung der googleMail. Diese speichert später die übergebene
		 * gmail Adresse.
		 */
		private String googleMail = "";
		private String vorname = "";
		private String nachname = "";
		private String nickname = "";
		private int id = 0;

		/**
		 * Konstruktor der aufgerufen wird.
		 * 
		 * @param mail:
		 *            Email Adresse des angemeldeten Nutzers.
		 */
		public CreateNutzerDialogBox(String email) {
			googleMail = email;
			ja.addClickHandler(new DoCreateClickHandler());
			nein.addClickHandler(new DontCreateClickHandler());
			vpanel.add(frage);
			buttonPanel.add(ja);
			buttonPanel.add(nein);
			this.add(vpanel);
		}

		class DoCreateClickHandler implements ClickHandler {

			@Override
			public void onClick(ClickEvent event) {

				socialMediaVerwaltung.createNutzer(googleMail, new CreateNutzerCallback());
			}

		}

		class DontCreateClickHandler implements ClickHandler {

			@Override
			public void onClick(ClickEvent event) {

				hide();
				signOutAnchor.setHref(loginInfo.getLogoutUrl());
				Window.open(signOutAnchor.getHref(), "_self", "");
			}

		}

		/**
		 * Nested Class in der DialogBox. Wenn Nutzer einen User erstellen
		 * möchte, gibt es diesen Callback Aufruf.
		 *
		 */
		class CreateNutzerCallback implements AsyncCallback<Nutzer> {

			@Override
			public void onFailure(Throwable caught) {

				Window.alert("Ihr User konnte nicht erstellt werden" + caught.getMessage());

			}

			@Override
			public void onSuccess(Nutzer result) {

				Window.alert("Ihr Nutzer wurde erfolgreich angelegt");
				Cookies.setCookie("signout", loginInfo.getLogoutUrl());
				Cookies.setCookie("email", result.getEmail());
				Cookies.setCookie("id", result.getId() + "");
				hide();
				loadPinnwand();

			}

		}
		// final Button sendButton = new Button("Send");
		// final TextBox nameField = new TextBox();
		// nameField.setText("GWT User");
		// final Label errorLabel = new Label();
		//
		// // We can add style names to widgets
		// sendButton.addStyleName("sendButton");
		//
		// // Add the nameField and sendButton to the RootPanel
		// // Use RootPanel.get() to get the entire body element
		// RootPanel.get("nameFieldContainer").add(nameField);
		// RootPanel.get("sendButtonContainer").add(sendButton);
		// RootPanel.get("errorLabelContainer").add(errorLabel);
		//
		// // Focus the cursor on the name field when the app loads
		// nameField.setFocus(true);
		// nameField.selectAll();
		//
		// // Create the popup dialog box
		// final DialogBox dialogBox = new DialogBox();
		// dialogBox.setText("Remote Procedure Call");
		// dialogBox.setAnimationEnabled(true);
		// final Button closeButton = new Button("Close");
		// // We can set the id of a widget by accessing its Element
		// closeButton.getElement().setId("closeButton");
		// final Label textToServerLabel = new Label();
		// final HTML serverResponseLabel = new HTML();
		// VerticalPanel dialogVPanel = new VerticalPanel();
		// dialogVPanel.addStyleName("dialogVPanel");
		// dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		// dialogVPanel.add(textToServerLabel);
		// dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		// dialogVPanel.add(serverResponseLabel);
		// dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		// dialogVPanel.add(closeButton);
		// dialogBox.setWidget(dialogVPanel);
		//
		// // Add a handler to close the DialogBox
		// closeButton.addClickHandler(new ClickHandler() {
		// public void onClick(ClickEvent event) {
		// dialogBox.hide();
		// sendButton.setEnabled(true);
		// sendButton.setFocus(true);
		// }
		// });
		//
		// // Create a handler for the sendButton and nameField
		// class MyHandler implements ClickHandler, KeyUpHandler {
		// /**
		// * Fired when the user clicks on the sendButton.
		// */
		// public void onClick(ClickEvent event) {
		// sendNameToServer();
		// }
		//
		// /**
		// * Fired when the user types in the nameField.
		// */
		// public void onKeyUp(KeyUpEvent event) {
		// if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
		// sendNameToServer();
		// }
		// }
		//
		// /**
		// * Send the name from the nameField to the server and wait for a
		// response.
		// */
		// private void sendNameToServer() {
		// // First, we validate the input.
		// errorLabel.setText("");
		// String textToServer = nameField.getText();
		// if (!FieldVerifier.isValidName(textToServer)) {
		// errorLabel.setText("Please enter at least four characters");
		// return;
		// }
		//
		// // Then, we send the input to the server.
		// sendButton.setEnabled(false);
		// textToServerLabel.setText(textToServer);
		// serverResponseLabel.setText("");
		// greetingService.greetServer(textToServer, new AsyncCallback<String>()
		// {
		// public void onFailure(Throwable caught) {
		// // Show the RPC error message to the user
		// dialogBox.setText("Remote Procedure Call - Failure");
		// serverResponseLabel.addStyleName("serverResponseLabelError");
		// serverResponseLabel.setHTML(SERVER_ERROR);
		// dialogBox.center();
		// closeButton.setFocus(true);
		// }
		//
		// public void onSuccess(String result) {
		// dialogBox.setText("Remote Procedure Call");
		// serverResponseLabel.removeStyleName("serverResponseLabelError");
		// serverResponseLabel.setHTML(result);
		// dialogBox.center();
		// closeButton.setFocus(true);
		// }
		// });
		// }
		// }
		//
		// // Add a handler to send the name to the server
		// MyHandler handler = new MyHandler();
		// sendButton.addClickHandler(handler);
		// nameField.addKeyUpHandler(handler);
	}
}
