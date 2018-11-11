package de.hdm.itprojekt.client.gui;

/*
 * Die Implementierung des TreeViewModel ermöglicht die Anzeige von Pinnwänden 
 * durch eine Baumstruktur in dem linken seitlichen Panel.
 * 
 * 
 */
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.TreeViewModel;

public class CustomTreeModel extends VerticalPanel implements TreeViewModel{

	@Override
	public <T> NodeInfo<?> getNodeInfo(T value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isLeaf(Object value) {
		// TODO Auto-generated method stub
		return false;
	}


}
