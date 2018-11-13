package de.hdm.itprojekt.client.gui;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.shared.SocialMediaAdminAsync;
import de.hdm.itprojekt.shared.bo.Textbeitrag;

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
	private HTML headline = new HTML("Willkommen auf deiner Pinnwand");
	private TextArea textbeitragVerfassen = new TextArea();
	private Button textbeitragPosten = new Button("Beitrag teilen");
	


	public StartseiteForm(){
	    super.onLoad();

		
	}
	

	@Override
	protected void run() {
		
		AllAbonnementView allAbonnementview = new AllAbonnementView();
		Menubar menubar = new Menubar();
		textbeitragVerfassen.setHeight("1000");
		textbeitragVerfassen.setWidth("1000");
		textbeitragPosten.addClickHandler(new PostenHandler());
		vpanel.add(headline);
		vpanel.add(textbeitragVerfassen);
		vpanel.add(textbeitragPosten);
		vpanel.add(signOutLink);
		RootPanel.get("content").add(vpanel);

		
		
	}

	class PostenHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			Textbeitrag textbeitrag = new Textbeitrag();
			textbeitrag.setId(Integer.parseInt(Cookies.getCookie("id")));
			socialMediaVerwaltung.createTextbeitrag(textbeitrag.getPinnwandID(), textbeitrag.getNutzerID(), 
					textbeitrag.getKommentarID(), textbeitragVerfassen.getValue(), new CreateTextbeitragCallback());
			Window.alert("funktioniert");
		}
		
	}
	
	public class CreateTextbeitragCallback implements AsyncCallback<Textbeitrag>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Textbeitrag result) {
			// TODO Auto-generated method stub
			Textbeitrag t = new Textbeitrag();
			t= result;
			TextBox tb = new TextBox();
			VerticalPanel vpanel = new VerticalPanel();
			tb.setValue(t.getInhalt());
			
			vpanel.add(tb);
			
			RootPanel.get("content").add(vpanel);

			

		}
		
	}
}
