package de.hdm.itprojekt.client.gui;

import java.util.ArrayList;
import java.util.Vector;

import com.google.gwt.cell.client.AbstractEditableCell;
import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.itprojekt.shared.SocialMediaAdminAsync;
import de.hdm.itprojekt.shared.bo.Abonnement;
import de.hdm.itprojekt.shared.bo.Nutzer;
import de.hdm.itprojekt.shared.bo.Textbeitrag;


public class AbonniertePinnwand extends VerticalPanel {

	private Label nicknameLabel = new Label();
	private String nickname = null;
	private int aboID = 0;
	private VerticalPanel beitragPanel = new VerticalPanel();
	private VerticalPanel mainPanel = new VerticalPanel();
	private Button aboLoeschen = new Button("Entfolgen");
	private TextArea beitragTb = new TextArea();
	private Button postBt = new Button("Posten");
	private Nutzer nutzer = new Nutzer();
//	private Textbeitrag textbeitrag = new Textbeitrag();
	private Textbeitrag textbeitrag = null;
	private SingleSelectionModel<Textbeitrag> ssm = new SingleSelectionModel<Textbeitrag>();

	// Create a CellList that uses the cell.
	private CellList<Textbeitrag> cellList = new CellList<Textbeitrag>(new CellListTextbeitrag());
	
	//Create a CellTable
	private HorizontalPanel allTextbeitragCellTableContainer = new HorizontalPanel();
	private EditTextCell editTextCell = new EditTextCell();
	private CellTableTextbeitrag.DateColumn dateColumn = null;
	private CellTableTextbeitrag.BeitragColumn beitragColumn = null;
	private Textbeitrag tb = null;
	private TextCell textCell = new TextCell();
	private CellTableTextbeitrag allTextbeitragCellTable = null;



	
	private static SocialMediaAdminAsync socialMediaVerwaltung = de.hdm.itprojekt.client.ClientsideSettings
			.getSocialMediaVerwaltung();
	
	public AbonniertePinnwand(final int nutzerID) {
		
		nutzer.setId(Integer.parseInt(Cookies.getCookie("id")));

		socialMediaVerwaltung.findTextbeitragByNutzerID(nutzerID, new CellListCallback());
		socialMediaVerwaltung.findTextbeitragByNutzerID(nutzerID, new CellTableCallback());


//		beitragColumn.addKeyDownHandler(new KeyDownHandler() {
//			
//			@Override
//			public void onKeyDown(KeyDownEvent event) {
//
//					 if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
//					 if (beitragColumn.getValue(textbeitrag).equals("")){
//					 socialMediaVerwaltung.deleteTextbeitrag(textbeitrag, new
//					 AsyncCallback<Void>(){
//					
//					 @Override
//					 public void onFailure(Throwable caught) {
//					 // TODO Auto-generated method stub
//					
//					 }
//					
//					 @Override
//					 public void onSuccess(Void result) {
//					 AbonniertePinnwand abonniertePinnwand = new
//					 AbonniertePinnwand(nutzerID);
//					 }
//					
//					 });
//					 }
//					 }
//					 }
//			
//		});
		
		
		cellList.setSelectionModel(ssm);
		ssm.addSelectionChangeHandler(new Handler() {

			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				if(nutzerID == nutzer.getId()){
				}
			}
		});
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


		// socialMediaVerwaltung.findTextbeitragByNutzerID(nutzerID, new
		// AsyncCallback<Vector<Textbeitrag>>() {
		//
		// @Override
		// public void onFailure(Throwable caught) {
		// Window.alert("Fehler beim Laden " + caught.getMessage());
		//
		// }
		//
		// @Override
		// public void onSuccess(Vector<Textbeitrag> result) {
		//
		// // nutzer.setId(Integer.parseInt(Cookies.getCookie("id")));
		// for (final Textbeitrag textbeitrag : result) {
		//
		// HorizontalPanel beitraege = new HorizontalPanel();
		// final FlexTable flexTable = new FlexTable();
		// int numRows = flexTable.getRowCount();
		// Label erzDatumLb = new Label("" + textbeitrag.getErzeugungsdatum());
		// final TextBox beitragTb = new TextBox();
		// beitragTb.setText(textbeitrag.getInhalt());
		// beitragTb.setVisibleLength(beitragTb.getText().length());
		// beitragTb.setEnabled(false);
		//
		// flexTable.setWidget(numRows, 0, erzDatumLb);
		// flexTable.setWidget(numRows, 1, beitragTb);
		//
		// /**
		// * Durch diese Abfrage wird verhindert, dass die Beiträge
		// * der abonnierten Nutzer clickable sind
		// */
		//
		// if (nutzerID == nutzer.getId()) {
		// beitragTb.setEnabled(true);
		// // beitragTb.addClickHandler(new ClickHandler() {
		// //
		// // @Override
		// // public void onClick(ClickEvent event) {
		// // Window.alert("hier editieren DialogBox");
		// //
		// // }
		// // });
//		 beitragTb.addKeyDownHandler(new KeyDownHandler() {
//		
//		
//		
//		 @Override
//		 public void onKeyDown(KeyDownEvent event) {
//		 // TODO Auto-generated method stub
//		 if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
//		 if (beitragTb.getValue().equals("")){
//		 socialMediaVerwaltung.deleteTextbeitrag(textbeitrag, new
//		 AsyncCallback<Void>(){
//		
//		 @Override
//		 public void onFailure(Throwable caught) {
//		 // TODO Auto-generated method stub
//		
//		 }
//		
//		 @Override
//		 public void onSuccess(Void result) {
//		 AbonniertePinnwand abonniertePinnwand = new
//		 AbonniertePinnwand(nutzerID);
//		 }
//		
//		 });
//		 }
//		 }
//		 }
//		 });
//		 }
		//
		// beitraege.add(flexTable);
		// mainPanel.add(beitraege);
		// }
		// }
		// });

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
		allTextbeitragCellTable = new CellTableTextbeitrag(textbeitrag);
		dateColumn = allTextbeitragCellTable.new DateColumn(textCell);
		
		
		
		beitragColumn = allTextbeitragCellTable.new BeitragColumn(editTextCell){
			
			@Override
			public void onBrowserEvent(Context context, Element elem, Textbeitrag object, NativeEvent event) {
				// TODO Auto-generated method stub
				super.onBrowserEvent(context, elem, object, event);
								
				 if(event.getKeyCode() == KeyCodes.KEY_ENTER) {
				if (object.getInhalt()=="") {
					socialMediaVerwaltung.deleteTextbeitrag(object, new AsyncCallback<Void>() {
						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Fehler: " + caught.getMessage());
						}

						@Override
						public void onSuccess(Void result) {
							 AbonniertePinnwand abonniertePinnwand = new AbonniertePinnwand(nutzerID);

						}
						
					});
				}
				 }
			}
			
		};
			
//				 if(event.getKeyCode() == KeyCodes.KEY_ENTER) {
//
//				if(object.getInhalt().equals("")){
//					 socialMediaVerwaltung.deleteTextbeitrag(textbeitrag, new
//					 AsyncCallback<Void>(){
//					
//					 @Override
//					 public void onFailure(Throwable caught) {
//						 Window.alert("Fehler: " + caught.getMessage()); 
//					 }
//					
//					 @Override
//					 public void onSuccess(Void result) {
//
//					 AbonniertePinnwand abonniertePinnwand = new AbonniertePinnwand(nutzerID);
//					 }
//					
//					 });
//				}
//			}
//				 }
		
//				 if(event.getKeyCode() == KeyCodes.KEY_ENTER) {
//					 if (object.getInhalt().equals("")){
//					 socialMediaVerwaltung.deleteTextbeitrag(textbeitrag, new
//					 AsyncCallback<Void>(){
//					
//					 @Override
//					 public void onFailure(Throwable caught) {
//					 // TODO Auto-generated method stub
//					
//					 }
//					
//					 @Override
//					 public void onSuccess(Void result) {
//
//					 AbonniertePinnwand abonniertePinnwand = new
//					 AbonniertePinnwand(nutzerID);
//					 }
//					
//					 });
//					 }
//					 }
//					 }
//				
//			
		
		
		allTextbeitragCellTableContainer.clear();
		allTextbeitragCellTableContainer.add(allTextbeitragCellTable);
		allTextbeitragCellTable.addColumn(dateColumn);
		allTextbeitragCellTable.addColumn(beitragColumn);
		
		
		allTextbeitragCellTableContainer.clear();
		allTextbeitragCellTableContainer.add(allTextbeitragCellTable);
		// vpanel.add(hpanel);
		mainPanel.add(beitragPanel);
		mainPanel.add(allTextbeitragCellTableContainer);
		mainPanel.add(cellList);
		this.add(mainPanel);
		super.onLoad();
		RootPanel.get("content").clear();
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

	class CellListCallback implements AsyncCallback<Vector<Textbeitrag>> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Laden " + caught.getMessage());

		}

		@Override
		public void onSuccess(Vector<Textbeitrag> result) {
			for (Textbeitrag textbeitrag : result) {
				cellList.setRowData(0, result);
				cellList.setRowCount(result.size(), true);
			}

			CellListTextbeitrag cellTableTextbeitrag = new CellListTextbeitrag();
		}

	}
	
	class CellTableCallback implements AsyncCallback<Vector<Textbeitrag>> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Beim Laden der Daten ist ein Fehler aufgetreten" + caught.getMessage());
			
		}

		@Override
		public void onSuccess(Vector<Textbeitrag> result) {
			Range range = new Range(0, result.size());
			allTextbeitragCellTable.setVisibleRangeAndClearData(range, true);

			for (Textbeitrag textbeitrag : result) {
				CellTableTextbeitrag cellTableTextbeitrag = new CellTableTextbeitrag(textbeitrag); 

			}
			allTextbeitragCellTable.setRowCount(result.size(), true);
			allTextbeitragCellTable.setRowData(0, result);
		}
		
	}
}
