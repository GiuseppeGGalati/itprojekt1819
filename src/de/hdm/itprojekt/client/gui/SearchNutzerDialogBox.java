//package de.hdm.itprojekt.client.gui;
//
//import java.util.Vector;
//
//import com.google.gwt.cell.client.ClickableTextCell;
//import com.google.gwt.cell.client.TextCell;
//import com.google.gwt.user.client.Window;
//import com.google.gwt.user.client.rpc.AsyncCallback;
//import com.google.gwt.user.client.ui.RootPanel;
//import com.google.gwt.user.client.ui.VerticalPanel;
//import com.google.gwt.view.client.MultiSelectionModel;
//
//import de.hdm.itprojekt.client.ClientsideSettings;
//import de.hdm.itprojekt.shared.SocialMediaAdminAsync;
//import de.hdm.itprojekt.shared.bo.Nutzer;
//
//public class SearchNutzerDialogBox {
//
//	private static SocialMediaAdminAsync socialmediaVerwaltung = ClientsideSettings.getSocialMediaVerwaltung();
//
//	
//	private ClickableTextCell tCell = new ClickableTextCell();
//	private VerticalPanel mainPanel = new VerticalPanel();
//	private MultiSelectionModel<Nutzer> msm = new MultiSelectionModel<Nutzer>();
//	private CellTableNutzer nutzerCt = new CellTableNutzer();
//	private CellTableNutzer.VornameColumn vornameColumn = nutzerCt.new VornameColumn(tCell);
//	private CellTableNutzer.NachnameColumn nachnameColumn = nutzerCt.new NachnameColumn(tCell);
//	private CellTableNutzer.NicknameColumn nicknameColumn = nutzerCt.new NicknameColumn(tCell);
//	private CellTableNutzer.EmailColumn emailColumn = nutzerCt.new EmailColumn(tCell);
//
//	private TextCell textCell = new TextCell();
////	private Vector<Nutzer> resultNutzer = new Vector<Nutzer>();
//	
//
//	
//	public SearchNutzerDialogBox(final int nutzerID){
//		
//		
//
////		 socialmediaVerwaltung.findNutzerByID(nutzerID, new CellTableNutzerCallback());
//		 socialmediaVerwaltung.findAllNutzerByID(nutzerID, new CellTableNutzerCallback());
//
////				@Override
////				public void onFailure(Throwable caught) {
////					// TODO Auto-generated method stub
////					
////				}
////
////				@Override
////				public void onSuccess(Nutzer result) {
////
////					resultNutzer.addElement(result);
////					
////					Range range = new Range(0, resultNutzer.size());
////					nutzerCt.setVisibleRangeAndClearData(range, true);
////
////
////				
////					CellTableNutzer nutzerCt = new CellTableNutzer(resultNutzer);
////
////					
////					nutzerCt.setRowCount(resultNutzer.size(), true);
////					nutzerCt.setRowData(0, resultNutzer);
////				}
////
////				
////				
////				
////			
////		});
//
////		socialmediaVerwaltung.findNutzerByID(nutzerID, new AsyncCallback<Nutzer>() {
////			
////			@Override
////			public void onSuccess(Nutzer result) {
////				
////				resultNutzer.addElement(result);
////
////				CellTable<Nutzer> nutzerCt = new CellTable<Nutzer>();
////
////				
////				nutzerCt.setRowCount(resultNutzer.size(), true);
////				nutzerCt.setRowData(0, resultNutzer);
////			}
////			
////			@Override
////			public void onFailure(Throwable caught) {
////				Window.alert("Fehler: " + caught.getMessage());
////				
////			}
////		});
//		
//
//	        
//	        
//	    nutzerCt.setSelectionModel(msm);
//	    nutzerCt.addColumn(vornameColumn);
//	    nutzerCt.addColumn(nachnameColumn);
//	    nutzerCt.addColumn(nicknameColumn);
//	    nutzerCt.addColumn(emailColumn);
//
//		mainPanel.add(nutzerCt);
//		RootPanel.get("content").clear();
//		RootPanel.get("content").add(mainPanel);
//		
//	}
//	
//	class CellTableNutzerCallback implements AsyncCallback<Vector<Nutzer>>{
//
//		@Override
//		public void onFailure(Throwable caught) {
//			Window.alert("Fehler: " + caught.getMessage());
//		}
//
//		@Override
//		public void onSuccess(Vector<Nutzer> result) {
//
//				//Range range = new Range(0, result.size());
//				//nutzerCt.setVisibleRangeAndClearData(range, true);
//				
//				nutzerCt.setRowCount(result.size(), true);
//				nutzerCt.setRowData(0, result);
//			
//
//		}
//		
//		
//	}
//}
