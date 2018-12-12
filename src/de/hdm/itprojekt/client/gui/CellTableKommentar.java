package de.hdm.itprojekt.client.gui;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.shared.SocialMediaAdminAsync;
import de.hdm.itprojekt.shared.bo.Kommentar;
import de.hdm.itprojekt.shared.bo.Nutzer;

public class CellTableKommentar extends CellTable<Kommentar> {

	public CellTableKommentar(Kommentar kommentar) {
	}

	public class NutzerColumn extends Column<Kommentar, String> {

		public NutzerColumn(Cell<String> cell) {
			super(cell);
		}

		@Override
		public String getValue(Kommentar object) {
			return object.getNickname();
		}

	}

	public class DateColumn extends Column<Kommentar, String> {

		public DateColumn(Cell<String> cell) {
			super(cell);
		}

		@Override
		public String getValue(Kommentar object) {
			if (object.getModifikationsdatum() != null) {
				return object.getModifikationsdatum().toString();
			}
			return object.getErzeugungsdatum().toString();
		}

	}

	public class KommentarColumn extends Column<Kommentar, String> {

		public KommentarColumn(Cell<String> cell) {
			super(cell);
		}

		@Override
		public String getValue(Kommentar object) {
			return object.getInhalt();
		}

	}
}
