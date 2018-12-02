package de.hdm.itprojekt.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.Abonnement;

/**
 * Allgemeine Erklärungen zur Mapper-Klasse siehe <NutzerMapper>
 * 
 * @author giuseppegalati
 *
 */
public class AbonnementMapper {

	private static AbonnementMapper abonnementMapper = null;

	protected AbonnementMapper() {

	};

	public static AbonnementMapper abonnementMapper() {
		if (abonnementMapper == null) {
			abonnementMapper = new AbonnementMapper();
		}
		return abonnementMapper;
	}

	public Abonnement createAbonnement(Abonnement abonnement) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid FROM abonnement");

			if (rs.next()) {
				PreparedStatement stmt1 = con.prepareStatement(
						"INSERT INTO abonnement(id, nutzerid, pinnwandid) VALUES(?, ?, ?) ",
						Statement.RETURN_GENERATED_KEYS);

				stmt1.setInt(1, abonnement.getId());
				stmt1.setInt(2, abonnement.getNutzerID());
				stmt1.setInt(3, abonnement.getPinnwandID());
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
		return abonnement;
	}

	public Abonnement updateAbonnement(Abonnement abonnement) {

		Connection con = DBConnection.connection();

		try {
			PreparedStatement stmt = con.prepareStatement(
					"UPDATE FROM `abonnement` " + "SET `id`=?, `nutzerid`=?, `pinnwandid`=? WHERE id=?");

			stmt.setInt(1, abonnement.getId());
			stmt.setInt(2, abonnement.getNutzerID());
			stmt.setInt(3, abonnement.getPinnwandID());
			stmt.executeUpdate();
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
		return abonnement;

	}

	public void deleteAbonnement(Abonnement abonnement) {

		Connection con = DBConnection.connection();

		try {
			PreparedStatement stmt = con.prepareStatement("DELETE FROM abonnement WHERE nutzerid=? AND pinnwandid=?");

			stmt.setInt(1, abonnement.getNutzerID());
			stmt.setInt(2, abonnement.getPinnwandID());

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

	public Vector <Abonnement> findAbonnementByNutzerID(int nutzerID) {

		Connection con = DBConnection.connection();

		
		Vector <Abonnement> a = new Vector <Abonnement>();

		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM `abonnement` WHERE `nutzerid`=?");
			stmt.setInt(1, nutzerID);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Abonnement abonnement = new Abonnement();

				abonnement.setId(rs.getInt("id"));
				abonnement.setNutzerID(rs.getInt("nutzerid"));
				abonnement.setPinnwandID(rs.getInt("pinnwandid"));

				a.addElement(abonnement);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		/**
		 * Ergebnisvektor zurückgeben
		 */
		finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return a;

	}
	
	

}