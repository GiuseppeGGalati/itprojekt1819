package de.hdm.itprojekt.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.itprojekt.client.ClientsideSettings;
import de.hdm.itprojekt.shared.SocialMediaAdminAsync;
import de.hdm.itprojekt.shared.bo.Nutzer;

public class AllAbonnementView extends LeftSideFrame{

	/**
	 * Instanziierung des Proxy Objekts für den Editor
	 */
	private static SocialMediaAdminAsync socialmediaVerwaltung = ClientsideSettings.getSocialMediaVerwaltung();
	
	
	/**
	 * Instanzierung der GUI-Elemente
	 */
	private VerticalPanel vpanel = new VerticalPanel();
	private Button abonnieren = new Button("Abonnieren");
	private Button meinePinnwand = new Button("Meine Pinnwand");
	private Vector<Nutzer> nutzerVector = new Vector<Nutzer>();
	private SingleSelectionModel<Nutzer> ssm = new SingleSelectionModel<Nutzer>();
    
	// Create a CellList that uses the cell.
    private CellList<Nutzer> cellList = new CellList<Nutzer>(new CellListAbonnement());
	
	@Override
	protected void run() {

		meinePinnwand.addClickHandler(new meinePinnwandClickHandler());

		//Später löschen und durch Vector mit Callback ersetzen 
		Nutzer nutzer1 = new Nutzer();
		Nutzer nutzer2 = new Nutzer();
		nutzer1.setNickname("dummynutzer");
		nutzer2.setNickname("StanLee");
		nutzerVector.add(nutzer1);
		nutzerVector.add(nutzer2);
		cellList.setRowData(0, nutzerVector);
		cellList.setRowCount(nutzerVector.size(), true);
		cellList.setSelectionModel(ssm);
		ssm.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
			Window.alert(ssm.getSelectedObject().getNickname());
				
			}
		});
		

		
		vpanel.add(abonnieren);
		vpanel.add(meinePinnwand);
		vpanel.add(cellList);
		RootPanel.get("leftmenutree").add(vpanel);
		
		
	}
	class meinePinnwandClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
		StartseiteForm startseiteForm = new StartseiteForm();
		Window.alert("funktioniert");
		RootPanel.get("content").clear();
		RootPanel.get("content").add(startseiteForm);
		
		}
		
	}
}
