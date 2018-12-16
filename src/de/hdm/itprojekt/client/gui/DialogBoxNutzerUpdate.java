package de.hdm.itprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.client.ClientsideSettings;
import de.hdm.itprojekt.shared.SocialMediaAdminAsync;
import de.hdm.itprojekt.shared.bo.Nutzer;

public class DialogBoxNutzerUpdate extends DialogBox {

	/**
	 * Instanziierung des Proxy Objekts für den Editor
	 */
	private static SocialMediaAdminAsync socialmediaVerwaltung = ClientsideSettings.getSocialMediaVerwaltung();

	/**
	 * Instanzierung der GUI-Elemente
	 */
	private VerticalPanel vpanel = new VerticalPanel();
	private TextBox vorname = new TextBox();
	private TextBox nachname = new TextBox();
	private TextBox nickname = new TextBox();
	private TextBox email = new TextBox();

	private Label updateLabel = new Label("Hier können Sie Ihre Daten ändern");
	private Label vornameLabel = new Label("Vorname: ");
	private Label nachnameLabel = new Label("Nachname: ");
	private Label nicknameLabel = new Label("Nickname: ");
	private Label emailLabel = new Label("Email: ");

	private Button speichern = new Button("speichern");
	private Button abbrechen = new Button("abbrechen");
	private Button nutzerLoeschen = new Button("Nutzer löschen");

	private FlexTable ft = new FlexTable();

	public DialogBoxNutzerUpdate() {

		Nutzer n = new Nutzer();
		n.setId(Integer.parseInt(Cookies.getCookie("id")));
		n.setEmail(Cookies.getCookie("email"));

		socialmediaVerwaltung.findNutzerByID(n.getId(), new FindNutzerCallback());

		ft.setWidget(0, 0, updateLabel);
		ft.setWidget(1, 0, vornameLabel);
		ft.setWidget(2, 0, vorname);
		ft.setWidget(3, 0, nachnameLabel);
		ft.setWidget(4, 0, nachname);
		ft.setWidget(5, 0, nicknameLabel);
		ft.setWidget(6, 0, nickname);
		ft.setWidget(7, 0, emailLabel);
		ft.setWidget(8, 0, email);
		ft.setWidget(9, 0, speichern);
		ft.setWidget(9, 1, abbrechen);
		ft.setWidget(9, 2, nutzerLoeschen);

		speichern.addClickHandler(new SpeichernClickhandler());
		abbrechen.addClickHandler(new AbbrechenClickhandler());
		nutzerLoeschen.addClickHandler(new DeleteNutzerClickHandler());

		vpanel.add(ft);
		this.add(vpanel);
	}

	public DialogBoxNutzerUpdate(Nutzer nutzer) {
		Nutzer n = new Nutzer();
		n.setId(Integer.parseInt(Cookies.getCookie("id")));

		speichern.addClickHandler(new SpeichernClickhandler());
		abbrechen.addClickHandler(new AbbrechenClickhandler());
	}

	class SpeichernClickhandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			Nutzer nutzer = new Nutzer();
			nutzer.setId(Integer.parseInt(Cookies.getCookie("id")));
			nutzer.setVorname(vorname.getValue());
			nutzer.setNachname(nachname.getValue());
			nutzer.setNickname(nickname.getValue());
			nutzer.setEmail(email.getValue());

			socialmediaVerwaltung.saveNutzer(nutzer, new SaveNutzerCallback());

		}

	}

	class AbbrechenClickhandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {

			StartseiteForm startseiteForm = new StartseiteForm();
		}

	}

	class FindNutzerCallback implements AsyncCallback<Nutzer> {

		@Override
		public void onFailure(Throwable caught) {

			Window.alert("Fehler beim Laden" + caught.getMessage());
		}

		@Override
		public void onSuccess(Nutzer result) {
			vorname.setValue(result.getVorname());
			nachname.setValue(result.getNachname());
			nickname.setValue(result.getNickname());
			email.setValue(result.getEmail());

		}

	}

	class SaveNutzerCallback implements AsyncCallback<Void> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Laden" + caught.getMessage());

		}

		@Override
		public void onSuccess(Void result) {
			Window.alert("Nutzer erfolgreich geändert");
			hide();
			StartseiteForm startseiteForm = new StartseiteForm();
		}

	}

	public class DeleteNutzerClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			boolean deleteNutzer = Window.confirm("Möchten Sie ihren Nutzer wirklich löschen");
			if (deleteNutzer == true) {
				Nutzer nutzer = new Nutzer();
				nutzer.setId(Integer.parseInt(Cookies.getCookie("id")));
				nutzer.setEmail(Cookies.getCookie("email"));

				socialmediaVerwaltung.deleteNutzer(nutzer, new DeleteNutzerCallback());
			}
		}

	}

	public class DeleteNutzerCallback implements AsyncCallback<Void> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Löschen " + caught.getMessage());
		}

		@Override
		public void onSuccess(Void result) {
			Anchor signOutLink = new Anchor();
			Window.alert("Nutzer wurde erfolgreich gelöscht");
			signOutLink.setHref(Cookies.getCookie("signout"));
			Window.open(signOutLink.getHref(), "_self", "");
		}

	}

}
