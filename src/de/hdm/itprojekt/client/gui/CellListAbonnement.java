package de.hdm.itprojekt.client.gui;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

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