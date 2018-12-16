package de.hdm.itprojekt.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.Textbeitrag;

/**
 * Allgemeine Erklärungen zur Mapper-Klasse siehe <NutzerMapper>
 * 
 * @author giuseppegalati
 *
 */
public class TextbeitragMapper {

	private static TextbeitragMapper textbeitragMapper = null;

	protected TextbeitragMapper() {
	};

	public static TextbeitragMapper textbeitragMapper() {
		if (textbeitragMapper == null) {
			textbeitragMapper = new TextbeitragMapper();
		}
		return textbeitragMapper;
	}

	public Textbeitrag createTextbeitrag(Textbeitrag textbeitrag) {

		Connection con = DBConnection.connection();
		java.sql.Timestamp sqlDate = new java.sql.Timestamp(textbeitrag.getErzeugungsdatum().getTime());

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid FROM `textbeitrag`");

			if (rs.next()) {

				PreparedStatement stmt1 = con.prepareStatement(
						"INSERT INTO textbeitrag(id, nutzerid, inhalt, erzeugungsdatum)" + " VALUES(?, ?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS);

				stmt1.setInt(1, textbeitrag.getId());
				stmt1.setInt(2, textbeitrag.getNutzerID());
				stmt1.setString(3, textbeitrag.getInhalt());
				stmt1.setTimestamp(4, sqlDate);

				System.out.println(stmt);
				stmt1.executeUpdate();
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return textbeitrag;
	}

	public Textbeitrag updateTextbeitrag(Textbeitrag textbeitrag) {
		String sql = "UPDATE textbeitrag SET inhalt=?, modifikationsdatum=? WHERE id=?";
		java.sql.Timestamp sqlDate1 = new java.sql.Timestamp(textbeitrag.getModifikationsdatum().getTime());

		Connection con = DBConnection.connection();

		try {
			PreparedStatement stmt1 = con.prepareStatement(sql);

			stmt1.setString(1, textbeitrag.getInhalt());
			stmt1.setTimestamp(2, sqlDate1);
			stmt1.setInt(3, textbeitrag.getId());
			stmt1.executeUpdate();

			System.out.println("Updated");

		} catch (SQLException e2) {
			e2.printStackTrace();
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return textbeitrag;

	}

	public void deleteTextbeitrag(Textbeitrag textbeitrag) {

		Connection con = DBConnection.connection();

		try {
			PreparedStatement stmt = con.prepareStatement("DELETE FROM `textbeitrag` WHERE id= ? ");

			stmt.setInt(1, textbeitrag.getId());
			stmt.executeUpdate();

		} catch (SQLException e2) {
			e2.printStackTrace();
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public Vector<Textbeitrag> findAllTextbeitrag() {

		Connection con = DBConnection.connection();

		Vector<Textbeitrag> result = new Vector<Textbeitrag>();

		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM `textbeitrag` ORDER BY `nutzerid` ASC");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Textbeitrag textbeitrag = new Textbeitrag();

				textbeitrag.setId(rs.getInt("id"));
				textbeitrag.setPinnwandID(rs.getInt("pinnwandid"));
				textbeitrag.setNutzerID(rs.getInt("nutzerid"));
				textbeitrag.setKommentarID(rs.getInt("kommentarid"));
				textbeitrag.setInhalt(rs.getString("inhalt"));
				textbeitrag.setErzeugungsdatum(rs.getDate("erzeugungsdatum"));
				textbeitrag.setModifikationsdatum(rs.getDate("modifikationsdatum"));

				result.addElement(textbeitrag);

			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	public Vector<Textbeitrag> findTextbeitragByNutzerId(int nutzerID) {

		Connection con = DBConnection.connection();

		Vector<Textbeitrag> result = new Vector<Textbeitrag>();

		try {
			PreparedStatement stmt = con
					.prepareStatement("SELECT * FROM `textbeitrag` WHERE `nutzerid`=? " + "ORDER BY id DESC");

			stmt.setInt(1, nutzerID);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Textbeitrag textbeitrag = new Textbeitrag();

				textbeitrag.setId(rs.getInt("id"));
				textbeitrag.setNutzerID(rs.getInt("nutzerid"));
				textbeitrag.setInhalt(rs.getString("inhalt"));
				textbeitrag.setErzeugungsdatum(rs.getDate("erzeugungsdatum"));
				textbeitrag.setModifikationsdatum(rs.getDate("modifikationsdatum"));

				result.addElement(textbeitrag);

			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	public Vector<Textbeitrag> findTextbeitragByKommentarId(int kommentarID) {

		Connection con = DBConnection.connection();

		Vector<Textbeitrag> result = new Vector<Textbeitrag>();

		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM `textbeitrag` WHERE `kommentarid`=? ");

			stmt.setInt(1, kommentarID);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Textbeitrag textbeitrag = new Textbeitrag();
				textbeitrag.setId(rs.getInt("id"));
				textbeitrag.setPinnwandID(rs.getInt("pinnwandid"));
				textbeitrag.setNutzerID(rs.getInt("nutzerid"));
				textbeitrag.setKommentarID(rs.getInt("kommentarid"));
				textbeitrag.setInhalt(rs.getString("inhalt"));
				textbeitrag.setErzeugungsdatum(rs.getDate("erzeugungsdatum"));
				textbeitrag.setModifikationsdatum(rs.getDate("modifikationsdatum"));

				result.addElement(textbeitrag);

			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	public Vector<Textbeitrag> findTextbeitragByPinnwandId(int pinnwandID) {

		Connection con = DBConnection.connection();

		Vector<Textbeitrag> result = new Vector<Textbeitrag>();

		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM `textbeitrag` WHERE `pinnwandid`=? ");

			stmt.setInt(1, pinnwandID);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Textbeitrag textbeitrag = new Textbeitrag();
				textbeitrag.setId(rs.getInt("id"));
				textbeitrag.setPinnwandID(rs.getInt("pinnwandid"));
				textbeitrag.setNutzerID(rs.getInt("nutzerid"));
				textbeitrag.setKommentarID(rs.getInt("kommentarid"));
				textbeitrag.setInhalt(rs.getString("inhalt"));
				textbeitrag.setErzeugungsdatum(rs.getDate("erzeugungsdatum"));
				textbeitrag.setModifikationsdatum(rs.getDate("modifikationsdatum"));

				result.addElement(textbeitrag);

			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}
}
