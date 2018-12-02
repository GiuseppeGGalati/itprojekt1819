package de.hdm.itprojekt.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.Pinnwand;

/**
 * Allgemeine Erklärungen zur Mapper-Klasse siehe <NutzerMapper>
 * 
 * @author giuseppegalati
 *
 */
public class PinnwandMapper {

	private static PinnwandMapper pinnwandMapper = null;

	protected PinnwandMapper() {
	};

	public static PinnwandMapper pinnwandMapper() {
		if (pinnwandMapper == null) {
			pinnwandMapper = new PinnwandMapper();
		}
		return pinnwandMapper;
	}

	public Pinnwand createPinnwand(Pinnwand pinnwand) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM pinnwand");
			if (rs.next()) {

				PreparedStatement stmt1 = con.prepareStatement("INSERT INTO pinnwand(id, nutzerid) VALUES (?, ?) ",

						Statement.RETURN_GENERATED_KEYS);
				stmt1.setInt(1, pinnwand.getId());
				stmt1.setInt(2, pinnwand.getNutzerID());

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
		return pinnwand;
	}

	public Pinnwand updatePinnwand(Pinnwand pinnwand) {

		Connection con = DBConnection.connection();

		try {
			PreparedStatement stmt1 = con
					.prepareStatement("UPDATE FROM `pinnwand` SET `id`= ?, `nutzerid` = ? WHERE id= ? ");
			stmt1.setInt(1, pinnwand.getId());
			stmt1.setInt(2, pinnwand.getNutzerID());

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
		return pinnwand;

	}

	public void deletePinnwand(Pinnwand pinnwand) {

		Connection con = DBConnection.connection();

		try {
			PreparedStatement stmt = con.prepareStatement("DELETE FROM `pinnwand` WHERE id= ?");
			stmt.setInt(1, pinnwand.getId());
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

	public void deletePinnwandByNutzerID(Pinnwand pinnwand) {

		Connection con = DBConnection.connection();

		try {
			PreparedStatement stmt = con.prepareStatement("DELETE FROM `pinnwand` WHERE nutzerid= ?");
			stmt.setInt(1, pinnwand.getNutzerID());
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
	
	public Vector<Pinnwand> findAllPinnwand() {

		Connection con = DBConnection.connection();

		Vector<Pinnwand> result = new Vector<Pinnwand>();

		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM `pinnwand` ORDER BY `nutzerid` ASC");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Pinnwand pinnwand = new Pinnwand();

				pinnwand.setId(rs.getInt("id"));
				pinnwand.setNutzerID(rs.getInt("nutzerid"));

				result.addElement(pinnwand);

			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	public Pinnwand findPinnwandByNutzerId(int nutzerID) {

		Connection con = DBConnection.connection();

		Pinnwand p = new Pinnwand();

		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM `pinnwand` WHERE `nutzerid`= ?");
			stmt.setInt(1, nutzerID);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Pinnwand pinnwand = new Pinnwand();
				pinnwand.setId(rs.getInt("id"));
				pinnwand.setNutzerID(rs.getInt("nutzerid"));

				p = pinnwand;

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
		return p;

	}

}
