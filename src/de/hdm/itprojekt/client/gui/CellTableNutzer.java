package de.hdm.itprojekt.client.gui;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.view.client.MultiSelectionModel;

import de.hdm.itprojekt.shared.bo.Nutzer;

public class CellTableNutzer extends CellTable<Nutzer> {

	final MultiSelectionModel<Nutzer> msm = new MultiSelectionModel<Nutzer>();

	public CellTableNutzer() {
		run();
	}

	public void run() {
		this.setSelectionModel(msm);
	}

	public class EmailColumn extends Column<Nutzer, String> {

		public EmailColumn(Cell<String> cell) {
			super(cell);
			// TODO Auto-generated constructor stub
		}

		@Override
		public String getValue(Nutzer object) {
			// TODO Auto-generated method stub
			return object.getEmail();
		}

	}

	public class NicknameColumn extends Column<Nutzer, String> {

		public NicknameColumn(Cell<String> cell) {
			super(cell);
			// TODO Auto-generated constructor stub
		}

		@Override
		public String getValue(Nutzer object) {
			// TODO Auto-generated method stub
			return object.getNickname();
		}

	}

	public class NachnameColumn extends Column<Nutzer, String> {

		public NachnameColumn(Cell<String> cell) {
			super(cell);
			// TODO Auto-generated constructor stub
		}

		@Override
		public String getValue(Nutzer object) {
			// TODO Auto-generated method stub
			return object.getNachname();
		}

	}

	public class VornameColumn extends Column<Nutzer, String> {

		public VornameColumn(Cell<String> cell) {
			super(cell);
			// TODO Auto-generated constructor stub
		}

		@Override
		public String getValue(Nutzer object) {
			// TODO Auto-generated method stub
			return object.getVorname();
		}

	}

}
