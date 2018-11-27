package de.hdm.itprojekt.client.gui;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

//package de.hdm.itprojekt.client.gui;
//
//import java.util.Vector;
//
//import com.google.gwt.event.dom.client.ClickEvent;
//import com.google.gwt.event.dom.client.ClickHandler;
//import com.google.gwt.i18n.client.DateTimeFormat;
//import com.google.gwt.user.client.Cookies;
//import com.google.gwt.user.client.Window;
//import com.google.gwt.user.client.rpc.AsyncCallback;
//import com.google.gwt.user.client.ui.Button;
//import com.google.gwt.user.client.ui.FlexTable;
//import com.google.gwt.user.client.ui.Grid;
//import com.google.gwt.user.client.ui.HTML;
//import com.google.gwt.user.client.ui.HorizontalPanel;
//import com.google.gwt.user.client.ui.Label;
//import com.google.gwt.user.client.ui.RootPanel;
//import com.google.gwt.user.client.ui.TextArea;
//import com.google.gwt.user.client.ui.TextBox;
//import com.google.gwt.user.client.ui.VerticalPanel;
//
//import de.hdm.itprojekt.shared.SocialMediaAdminAsync;
//import de.hdm.itprojekt.shared.bo.Nutzer;
//import de.hdm.itprojekt.shared.bo.Pinnwand;
//import de.hdm.itprojekt.shared.bo.Textbeitrag;
//
///**
// * Die Klasse StartseiteForm fungiert als Startseite. Erbt von der Basis-Klasse
// * MainFrame
// * 
// * @author giuseppegalati
// *
// */
//public class StartseiteForm extends MainFrame {
//
//	/**
//	 * Instanziierung des Proxy Objekts für den Editor
//	 */
//	private static SocialMediaAdminAsync socialMediaVerwaltung = de.hdm.itprojekt.client.ClientsideSettings
//			.getSocialMediaVerwaltung();
//	
//	/**
//	 * Instanziierung der GUI Elemente
//	 */
//	private VerticalPanel vpanel = new VerticalPanel();
////	private HTML headline = new HTML("Willkommen auf deiner Pinnwand");
//	private Label user = new Label();
//	private TextArea beitragsArea = new TextArea();
//	private TextArea textbeitragVerfassen = new TextArea();
//	private Button textbeitragPosten = new Button("Beitrag teilen");
//	private FlexTable textbeitragFlex = new FlexTable();
//	private Button neuBtn = new Button();	
//	public StartseiteForm(){
//	}
//
//	public StartseiteForm(final Nutzer nutzer){
//	    
//	socialMediaVerwaltung.findPinnwandByNutzerID(nutzer.getId(), new AsyncCallback<Pinnwand>() {
//
//		
//		@Override
//		public void onFailure(Throwable caught) {
//			Window.alert("Fehler beim Laden"+ caught.getMessage());
//		}
//
//		@Override
//		public void onSuccess(Pinnwand result) {
//			user.setText("Willkommen auf der Pinnwand von: " + nutzer.getNickname());
//			socialMediaVerwaltung.findTextbeitragByPinnwandID(result.getId(), new AsyncCallback<Vector<Textbeitrag>>() {
//
//				@Override
//				public void onFailure(Throwable caught) {
//					Window.alert("Fehler beim Laden"+ caught.getMessage());
//					
//				}
//
//				@Override
//				public void onSuccess(Vector<Textbeitrag> result) {
//					
//					for (Textbeitrag textbeitrag : result) {
//					int numRows = textbeitragFlex.getRowCount();
//					Label testLabel = new Label();
//					Label datum = new Label();
//					Button kmtr = new Button("kommentieren");
//					Button edit = new Button("edit");
//					testLabel.setText(textbeitrag.getInhalt());	
//					final int test = textbeitrag.getId();
//					
//					datum.setText(DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss").format(textbeitrag.getErzeugungsdatum()));
//					
//					Grid eins = new Grid(2,2);
//					eins.setWidget(0, 0, datum);
//					eins.setWidget(1, 0, testLabel);
//					eins.setWidget(0, 1, edit);
//					
//					HorizontalPanel btnpanel = new HorizontalPanel();
//					VerticalPanel beitragPanel = new VerticalPanel();
//					beitragPanel.add(eins);
//					btnpanel.add(kmtr);
//					beitragPanel.add(btnpanel);
//					edit.addClickHandler(new ClickHandler(){
//
//						@Override
//						public void onClick(ClickEvent event) {
//							// TODO Auto-generated method stub
//							
//							
//						}
//						
//					});
//					textbeitragFlex.setWidget(numRows, 0, beitragPanel);
//
//					textbeitragVerfassen.setHeight("1000");
//					textbeitragVerfassen.setWidth("1000");
//					textbeitragPosten.addClickHandler(new PostenHandler());
//
////					textbeitragFlex.setWidget(0, 0, textbeitragVerfassen);
////					textbeitragFlex.setWidget(1, 0, textbeitragPosten);
////					vpanel.add(textbeitragFlex);
////					RootPanel.get("content").add(vpanel);
//
//					}
//				}
//			});
//			
//			
//		}
//	});
//	neuBtn.addClickHandler(new ClickHandler(){
//
//		@Override
//		public void onClick(ClickEvent event) {
//			// TODO Auto-generated method stub						
//			String eingabe = beitragsArea.getValue();						
//			socialMediaVerwaltung.createTextbeitrag(pinnwandID, nutzerID, kommentarID, eingabe, CreateTextbeitragCallback);
//			
//			
//			
//			
//		}					
//	});
//	
//	
//	
//	vpanel.add(user);
//	vpanel.add(beitragsArea);	
//	vpanel.add(neuBtn);							
//	this.add(vpanel);
//	this.add(textbeitragFlex);
//	}
//	
//	public class CreateTextbeitragCallback implements AsyncCallback<Textbeitrag>{
//
//		@Override
//		public void onFailure(Throwable caught) {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public void onSuccess(Textbeitrag result) {
//			// TODO Auto-generated method stub
//			Textbeitrag t = new Textbeitrag();
//			t= result;
//			TextBox tb = new TextBox();
//			VerticalPanel vpanel = new VerticalPanel();
//			tb.setValue(t.getInhalt());
//			
//			vpanel.add(tb);
//			
//			RootPanel.get("content").add(vpanel);
//
//			
//
//		}
//		
//	}
//	class PostenHandler implements ClickHandler{
//
//		@Override
//		public void onClick(ClickEvent event) {
//			Textbeitrag textbeitrag = new Textbeitrag();
//			textbeitrag.setId(Integer.parseInt(Cookies.getCookie("id")));
//			socialMediaVerwaltung.createTextbeitrag(textbeitrag.getPinnwandID(), textbeitrag.getNutzerID(), 
//					textbeitrag.getKommentarID(), textbeitragVerfassen.getValue(), new CreateTextbeitragCallback());
//			Window.alert("funktioniert");
//		}
//		
//	}	
//		super.onLoad();
//	
//
//	@Override
//	protected void run() {
//
////		vpanel.add(headline);
////		vpanel.add(textbeitragVerfassen);
////		vpanel.add(textbeitragPosten);
////		vpanel.add(textbeitragFlex);
////		RootPanel.get("content").add(vpanel);
//
//
//			
//	}
//
//
//
//
//	
//}





//package de.hdm.itprojekt.client.gui;
//
//import java.util.Vector;
//
//import com.google.gwt.event.dom.client.ClickEvent;
//import com.google.gwt.event.dom.client.ClickHandler;
//import com.google.gwt.i18n.client.DateTimeFormat;
//import com.google.gwt.user.client.Cookies;
//import com.google.gwt.user.client.Window;
//import com.google.gwt.user.client.rpc.AsyncCallback;
//import com.google.gwt.user.client.ui.Button;
//import com.google.gwt.user.client.ui.FlexTable;
//import com.google.gwt.user.client.ui.Grid;
//import com.google.gwt.user.client.ui.HTML;
//import com.google.gwt.user.client.ui.Label;
//import com.google.gwt.user.client.ui.RootPanel;
//import com.google.gwt.user.client.ui.TextArea;
//import com.google.gwt.user.client.ui.VerticalPanel;
//
//import de.hdm.itprojekt.shared.SocialMediaAdminAsync;
//import de.hdm.itprojekt.shared.bo.Nutzer;
//import de.hdm.itprojekt.shared.bo.Pinnwand;
//import de.hdm.itprojekt.shared.bo.Textbeitrag;
//
///**
// * Die Klasse StartseiteForm fungiert als Startseite. Erbt von der Basis-Klasse
// * MainFrame
// * 
// * @author giuseppegalati
// *
// */
//public class StartseiteForm extends MainFrame {
//
//	/**
//	 * Instanziierung des Proxy Objekts für den Editor
//	 */
//	private static SocialMediaAdminAsync socialMediaVerwaltung = de.hdm.itprojekt.client.ClientsideSettings
//			.getSocialMediaVerwaltung();
//
//	/**
//	 * Instanziierung der GUI Elemente
//	 */
//	private VerticalPanel mainVpanel = new VerticalPanel();
//	private HTML headline = new HTML("Willkommen auf deiner Pinnwand");
//	private TextArea textbeitragVerfassen = new TextArea();
//	private Button textbeitragPosten = new Button("Beitrag teilen");
//	private FlexTable ft = new FlexTable();
//	
//	
//	@Override
//	protected void run() {
//
//		textbeitragVerfassen.setHeight("1000");
//		textbeitragVerfassen.setWidth("1000");
//		textbeitragPosten.addClickHandler(new PostenHandler());
//		// vpanel.add(headline);
//		mainVpanel.add(headline);
//		mainVpanel.add(textbeitragVerfassen);
//		mainVpanel.add(textbeitragPosten);
//		mainVpanel.add(ft);
//
//		// RootPanel.get("content").add(vpanel);
//
//		Window.alert("Run-Methode");
//
//		// mainVpanel.add(headline);
//		// this.add(mainVpanel);
//		// this.add(ft);
//		this.add(mainVpanel);
//		this.add(ft);
////		RootPanel.get("content").add(mainVpanel);
////		RootPanel.get("content").add(ft);
//
//		
//	}
//	
////	public StartseiteForm() {
////	}
//
//	public StartseiteForm(final Nutzer nutzer) {
//
//		socialMediaVerwaltung.findPinnwandByNutzerID(nutzer.getId(), new AsyncCallback<Pinnwand>() {
//
//			@Override
//			public void onFailure(Throwable caught) {
//				Window.alert("Fehler beim Laden der Pinnwand" + caught.getMessage());
//			}
//
//			@Override
//			public void onSuccess(Pinnwand result) {
//				Window.alert("aktuelle nutzerid: " + result.getNutzerID());
//				socialMediaVerwaltung.findTextbeitragByPinnwandID(result.getId(),
//						new AsyncCallback<Vector<Textbeitrag>>() {
//
//							@Override
//							public void onFailure(Throwable caught) {
//								Window.alert("Fehler beim Laden der Pinnwand" + caught.getMessage());
//
//							}
//
//							@Override
//							public void onSuccess(Vector<Textbeitrag> result) {
//
//								for (Textbeitrag textbeitrag : result) {
//									int numRows = ft.getRowCount();
//									Label testArea = new Label();
//									Label erzDatum = new Label();
//									Grid grid = new Grid(2,2);
//									testArea.setText(textbeitrag.getInhalt());
//									erzDatum.setText(DateTimeFormat.getFormat("yyyy-MM-dd HH:mm").format(textbeitrag.getErzeugungsdatum()));
//
//									Window.alert("Hier1: " + textbeitrag.getInhalt());
//
//									
//									grid.setWidget(0, 0, erzDatum);
//									grid.setWidget(1, 0, testArea);
//									VerticalPanel beitragPanel = new VerticalPanel();
//									beitragPanel.add(grid);
//									ft.setWidget(numRows, 0, beitragPanel);
//
//									RootPanel.get("content").add(mainVpanel);
//									RootPanel.get("content").add(ft);
//								}
//							}
//
//						});
//
//			}
//
//		});
//
//		super.onLoad();
//	}
//
//
//
//	class PostenHandler implements ClickHandler {
//
//		@Override
//		public void onClick(ClickEvent event) {
//			Textbeitrag textbeitrag = new Textbeitrag();
//			textbeitrag.setId(Integer.parseInt(Cookies.getCookie("id")));
//
//			socialMediaVerwaltung.createTextbeitrag(textbeitrag.getId(), textbeitrag.getNutzerID(),
//					textbeitrag.getKommentarID(), textbeitragVerfassen.getValue(), new CreateTextbeitragCallback());
//			Window.alert("funktioniert"+ textbeitrag.getInhalt());
//		}
//
//	}
//
//	public class CreateTextbeitragCallback implements AsyncCallback<Textbeitrag> {
//
//		@Override
//		public void onFailure(Throwable caught) {
//			// TODO Auto-generated method stub
//
//		}
//
//		@Override
//		public void onSuccess(Textbeitrag result) {
//			// TODO Auto-generated method stub
//			Window.alert("Callback" + result.getInhalt());
//			Textbeitrag t = new Textbeitrag();
//			t = result;
//			t.setId(Integer.parseInt(Cookies.getCookie("id")));
//			Label tb = new Label();
//			Label erzDatum = new Label();
//			VerticalPanel vpanel = new VerticalPanel();
//			erzDatum.setText(DateTimeFormat.getFormat("yyyy-MM-dd HH:mm").format(result.getErzeugungsdatum()));
//			tb.setText(t.getInhalt());
//			textbeitragVerfassen.setValue(null);
//
//			vpanel.add(erzDatum);
//			vpanel.add(tb);
//			
//
////			RootPanel.get("content").add(mainVpanel);
//			RootPanel.get("content").add(vpanel);
//			RootPanel.get("content").add(ft);
//
//
//
//		}
//
//	}
//	
//}


public class StartseiteForm extends MainFrame{


	public StartseiteForm() {
		/**
		 * Anlegen der GUI-Elemente
		 */
		VerticalPanel homePanel = new VerticalPanel();
		VerticalPanel inputPanel = new VerticalPanel();
		
		Label welcomeLabel = new Label("Herzlich Willkommen auf Gesichtsbuch");
		
		
		welcomeLabel.setStylePrimaryName("willkommen_label");
		
		
		inputPanel.setSpacing(8);
		homePanel.setSpacing(10);
		this.setSpacing(8);
		this.add(welcomeLabel);
		this.add(homePanel);
		this.add(inputPanel);

	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		
	}
}