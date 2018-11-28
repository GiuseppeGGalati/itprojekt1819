package de.hdm.itprojekt.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.shared.SocialMediaAdminAsync;
import de.hdm.itprojekt.shared.bo.Abonnement;
import de.hdm.itprojekt.shared.bo.Nutzer;
import de.hdm.itprojekt.shared.bo.Textbeitrag;

public class AbonniertePinnwand extends MainFrame {

	private Label nicknameLabel = new Label();
	private String nickname = null;
	private int aboID = 0;
	private VerticalPanel beitragPanel = new VerticalPanel();
	private VerticalPanel mainPanel = new VerticalPanel();
	private Button aboLoeschen = new Button("Entfolgen");
	private TextArea beitragTb = new TextArea();
	private Button postBt = new Button("Posten");
	private Nutzer nutzer = new Nutzer();
	private Textbeitrag textbeitrag = new Textbeitrag();

	private static SocialMediaAdminAsync socialMediaVerwaltung = de.hdm.itprojekt.client.ClientsideSettings
			.getSocialMediaVerwaltung();

	public AbonniertePinnwand(final int nutzerID) {
		nutzer.setId(Integer.parseInt(Cookies.getCookie("id")));

		/**
		 * Hat zur Folge, dass das Erstellen von Textbeiträgen nur auf der
		 * eigenen Pinnwand möglich ist.
		 */
		if (nutzerID == nutzer.getId()) {
			beitragPanel.add(beitragTb);
			beitragPanel.add(postBt);
		}

		postBt.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				socialMediaVerwaltung.createTextbeitrag(nutzer.getId(), beitragTb.getValue(),
						new AsyncCallback<Textbeitrag>() {

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								Window.alert("Fehler: " + caught.getMessage());

							}

							@Override
							public void onSuccess(Textbeitrag result) {
								AbonniertePinnwand pw = new AbonniertePinnwand(nutzerID);
							}
						});
			}
		});

		socialMediaVerwaltung.findNutzerByID(nutzerID, new AsyncCallback<Nutzer>() {

			@Override
			public void onSuccess(Nutzer result) {
				nutzer.setId(Integer.parseInt(Cookies.getCookie("id")));
				if (nutzer.getId() == result.getId()) {
					nicknameLabel.setText("Du befindest dich auf deiner Pinnwand " + result.getNickname());
				} else {

					nicknameLabel.setText("Du befindest dich auf der Pinnwand von: " + result.getNickname());
				}
				nickname = result.getNickname();
				aboID = result.getId();
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler beim Laden " + caught.getMessage());
			}
		});

		/**
		 * Alle Textbeiträge des Nutzers
		 */
		socialMediaVerwaltung.findTextbeitragByNutzerID(nutzerID, new AsyncCallback<Vector<Textbeitrag>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler beim Laden " + caught.getMessage());

			}

			@Override
			public void onSuccess(Vector<Textbeitrag> result) {

				// nutzer.setId(Integer.parseInt(Cookies.getCookie("id")));
				for (final Textbeitrag textbeitrag : result) {

					HorizontalPanel beitraege = new HorizontalPanel();
					final FlexTable flexTable = new FlexTable();
					int numRows = flexTable.getRowCount();
					Label erzDatumLb = new Label("" + textbeitrag.getErzeugungsdatum());
					final TextBox beitragTb = new TextBox();
					beitragTb.setText(textbeitrag.getInhalt());
					beitragTb.setVisibleLength(beitragTb.getText().length());
					beitragTb.setEnabled(false);

					flexTable.setWidget(numRows, 0, erzDatumLb);
					flexTable.setWidget(numRows, 1, beitragTb);

					/**
					 * Durch diese Abfrage wird verhindert, dass die Beiträge
					 * der abonnierten Nutzer clickable sind
					 */

					if (nutzerID == nutzer.getId()) {
						beitragTb.setEnabled(true);
						// beitragTb.addClickHandler(new ClickHandler() {
						//
						// @Override
						// public void onClick(ClickEvent event) {
						// Window.alert("hier editieren DialogBox");
						//
						// }
						// });
						beitragTb.addKeyDownHandler(new KeyDownHandler() {

						  

							@Override
							public void onKeyDown(KeyDownEvent event) {
								// TODO Auto-generated method stub
								 if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
						                 if (beitragTb.getValue().equals("")){
						                	 socialMediaVerwaltung.deleteTextbeitrag(textbeitrag, new AsyncCallback<Void>(){

												@Override
												public void onFailure(Throwable caught) {
													// TODO Auto-generated method stub
													
												}

												@Override
												public void onSuccess(Void result) {
													AbonniertePinnwand abonniertePinnwand = new AbonniertePinnwand(nutzerID);
												}
						                		 
						                	 });
						                 }
						           }
							}
						});
					}
					
					beitraege.add(flexTable);
					mainPanel.add(beitraege);
				}
			}
		});

		aboLoeschen.setHTML("<img src = 'images/unfollow.png'/>");
		aboLoeschen.setPixelSize(40, 40);
		aboLoeschen.setStylePrimaryName("unfollowButton");
		mainPanel.add(nicknameLabel);
		if (nutzerID != nutzer.getId()) {
			aboLoeschen.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					Abonnement abonnement = new Abonnement();
					abonnement.setNutzerID(nutzer.getId());
					abonnement.setPinnwandID(aboID);

					Window.alert("msg" + nutzer.getId());
					Window.alert("" + abonnement.getId());
					socialMediaVerwaltung.deleteAbonnement(abonnement, new AbonnementLoeschenCallback());
				}
			});

			mainPanel.add(aboLoeschen);
		}
		// vpanel.add(hpanel);
		mainPanel.add(beitragPanel);
		this.add(mainPanel);
		super.onLoad();
		RootPanel.get("content").add(mainPanel);
	}

	// class AbonnementLoeschenClickhandler implements ClickHandler {
	//
	// @Override
	// public void onClick(ClickEvent event) {
	// Abonnement abonnement = new Abonnement();
	// abonnement.setId(Integer.parseInt(Cookies.getCookie("nutzerid")));
	// Window.alert(""+ abonnement.getId());
	// socialMediaVerwaltung.deleteAbonnement(abonnement, new
	// AbonnementLoeschenCallback());
	// }
	//
	// }

	class AbonnementLoeschenCallback implements AsyncCallback<Void> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Laden " + caught.getMessage());

		}

		@Override
		public void onSuccess(Void result) {
			Window.alert("User entfolgt");
			RootPanel.get("content").clear();
			RootPanel.get("leftmenutree").clear();
			RootPanel.get("leftmenutree").add(new Toolbar());
			RootPanel.get("leftmenutree").add(new AllAbonnementView());

		}

	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
	}
}
