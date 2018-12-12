package de.hdm.itprojekt.client.gui;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.client.KommentarNutzerWrapper;
import de.hdm.itprojekt.shared.SocialMediaAdminAsync;
import de.hdm.itprojekt.shared.bo.Kommentar;
import de.hdm.itprojekt.shared.bo.Nutzer;

public class CellTableKommentar extends CellTable<KommentarNutzerWrapper> {

	public CellTableKommentar(KommentarNutzerWrapper kommentar) {
	}

	public class NutzerColumn extends Column<KommentarNutzerWrapper, String> {

		public NutzerColumn(Cell<String> cell) {
			super(cell);
		}

		@Override
		public String getValue(KommentarNutzerWrapper object) {
			return object.getNutzer().getNickname();
		}

	}

	public class DateColumn extends Column<KommentarNutzerWrapper, String> {

		public DateColumn(Cell<String> cell) {
			super(cell);
		}

		@Override
		public String getValue(KommentarNutzerWrapper object) {
			if (object.getKommentar().getModifikationsdatum() != null) {
				return object.getKommentar().getModifikationsdatum().toString();
			}
			return object.getKommentar().getErzeugungsdatum().toString();
		}

	}

	public class KommentarColumn extends Column<KommentarNutzerWrapper, String> {

		public KommentarColumn(Cell<String> cell) {
			super(cell);
		}

		@Override
		public String getValue(KommentarNutzerWrapper object) {
			return object.getKommentar().getInhalt();
		}

	}
}
