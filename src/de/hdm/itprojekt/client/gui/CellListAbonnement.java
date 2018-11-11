package de.hdm.itprojekt.client.gui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.itprojekt.shared.bo.Abonnement;
import de.hdm.itprojekt.shared.bo.Nutzer;

/**
 * Diese Klasse beinhaltet die CellTable, die die eigene sowie alle abonnierten
 * Pinnwände im LeftSideFrame anzeigt.
 * 
 * @author giuseppegalati
 *
 */
public class CellListAbonnement extends AbstractCell<Nutzer> {
	
	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context, Nutzer value, SafeHtmlBuilder sb) {
		// TODO Auto-generated method stub
	      if (value == null) {
        return;
	}
	      sb.appendEscaped(value.getNickname());
	    }
}