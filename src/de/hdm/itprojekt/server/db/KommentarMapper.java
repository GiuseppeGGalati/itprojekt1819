package de.hdm.itprojekt.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.Kommentar;

/**
 * Allgemeine Erklärungen zur Mapper-Klasse siehe <NutzerMapper>
 * 
 * @author giuseppegalati
 *
 */
public class KommentarMapper {

	private static KommentarMapper kommentarMapper = null;

	protected KommentarMapper() {
	};

	public static KommentarMapper kommentarMapper() {

		if (kommentarMapper == null) {
			kommentarMapper = new KommentarMapper();
		}
		return kommentarMapper;
	}

	public Kommentar createKommentar(Kommentar kommentar) {

		Connection con = DBConnection.connection();
		java.sql.Timestamp sqlDate = new java.sql.Timestamp(kommentar.getErzeugungsdatum().getTime());
		java.sql.Timestamp sqlDate1 = new java.sql.Timestamp(kommentar.getModifikationsdatum().getTime());

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid" + "FROM kommentar");

			if (rs.next()) {
				PreparedStatement stmt1 = con.prepareStatement(
						"INSERT INTO kommentar(id, textbeitragid, nutzerid, inhalt, erzeugungsdatum, modifikationsdatum) VALUES(?, ?, ?, ?, ?, ?) ",
						Statement.RETURN_GENERATED_KEYS);

				stmt1.setInt(1, kommentar.getId());
				stmt1.setInt(2, kommentar.getTextbeitragID());
				stmt1.setInt(3, kommentar.getNutzerID());
				stmt1.setString(4, kommentar.getInhalt());
				stmt1.setTimestamp(5, sqlDate);
				stmt1.setTimestamp(6, sqlDate1);

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
		return kommentar;
	}

	public Kommentar updateKommentar(Kommentar kommentar) {

		Connection con = DBConnection.connection();
		java.sql.Timestamp sqlDate = new java.sql.Timestamp(kommentar.getErzeugungsdatum().getTime());
		java.sql.Timestamp sqlDate1 = new java.sql.Timestamp(kommentar.getModifikationsdatum().getTime());

		try {
			PreparedStatement stmt1 = con
					.prepareStatement("UPDATE FROM `kommentar` SET `id`= ?, `textbeitragid`= ?, `nutzerid`= ?,"
							+ "`inhalt`= ?. `erzeugungsdatum`= ?, `modifikationsdatum`= ? WHERE id= ? ");

			stmt1.setInt(1, kommentar.getId());
			stmt1.setInt(2, kommentar.getTextbeitragID());
			stmt1.setInt(3, kommentar.getNutzerID());
			stmt1.setString(4, kommentar.getInhalt());
			stmt1.setTimestamp(5, sqlDate);
			stmt1.setTimestamp(6, sqlDate1);
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
		return kommentar;

	}

	public void deleteKommentat(Kommentar kommentar) {
		Connection con = DBConnection.connection();

		try {
			PreparedStatement stmt = con.prepareStatement("DELETE FROM `kommentar` WHERE id= ?");
			stmt.setInt(1, kommentar.getId());
			stmt.executeQuery();

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

	public Vector<Kommentar> findAllKommentar() {

		Connection con = DBConnection.connection();

		Vector<Kommentar> result = new Vector<Kommentar>();

		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM `kommentar` ORDER BY `nutzerid` ASC");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Kommentar kommentar = new Kommentar();

				kommentar.setId(rs.getInt("id"));
				kommentar.setTextbeitragID(rs.getInt("textbeitragid"));
				kommentar.setNutzerID(rs.getInt("nutzerid"));
				kommentar.setInhalt(rs.getString("inhalt"));
				kommentar.setErzeugungsdatum(rs.getDate("erzeugungsdatum"));
				kommentar.setModifikationsdatum(rs.getDate("modifikationsdatum"));

				result.addElement(kommentar);

			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	public Kommentar findKommentarByNutzerId(int nutzerID) {

		Connection con = DBConnection.connection();

		Kommentar k = new Kommentar();

		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM `kommentar` WHERE `nutzerid`= ?");

			stmt.setInt(1, nutzerID);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Kommentar kommentar = new Kommentar();
				kommentar.setId(rs.getInt("id"));
				kommentar.setTextbeitragID(rs.getInt("textbeitragid"));
				kommentar.setNutzerID(rs.getInt("nutzerid"));
				kommentar.setInhalt(rs.getString("inhalt"));
				kommentar.setErzeugungsdatum(rs.getDate("erzeugungsdatum"));
				kommentar.setModifikationsdatum(rs.getDate("modifikationsdatum"));

				k = kommentar;

			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return k;

	}

	public Kommentar findKommentarByTextbeitragId(int textbeitragID) {

		Connection con = DBConnection.connection();

		Kommentar k = new Kommentar();

		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM `kommentar` WHERE `textbeitragid`= ?");

			stmt.setInt(1, textbeitragID);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Kommentar kommentar = new Kommentar();
				kommentar.setId(rs.getInt("id"));
				kommentar.setTextbeitragID(rs.getInt("textbeitragid"));
				kommentar.setNutzerID(rs.getInt("nutzerid"));
				kommentar.setInhalt(rs.getString("inhalt"));
				kommentar.setErzeugungsdatum(rs.getDate("erzeugungsdatum"));
				kommentar.setModifikationsdatum(rs.getDate("modifikationsdatum"));

				k = kommentar;

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
		return k;

	}

}