package de.hdm.itprojekt.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.Nutzer;
import de.hdm.itprojekt.shared.bo.Textbeitrag;

/**
 * Die Mapper-Klasse "NutzerMapper" ermöglicht das Abbilden von Objekten
 * "Nutzer" in einer relationalen Datenbank. Dabei sind in der Mapper-Klassen
 * mehrere Methoden wie das erstellen, löschen, modifizieren oder das Suchen
 * nach mehreren Möglichkeiten etc. implementiert. Somit kann ein Objekt für die
 * Datenbank-Struktur umgewandelt, aber es kann auch von der Datenbank-Struktur
 * als Objekt wieder umgewandelt werden.
 */

public class NutzerMapper {

	/**
	 * Die Klasse NutzerMapper wird nur einmal instantiiert. Hier spricht man
	 * von einem sogenannten Singleton. Durch static nur einmal vorhanden.
	 */
	private static NutzerMapper nutzerMapper = null;

	/**
	 * Geschützter Konstruktor - verhindert die Möglichkeit, mit "new" neue
	 * Instanzen dieser Klasse zu erzeugen.
	 * 
	 */
	protected NutzerMapper() {
	};

	/**
	 * Kann aufgerufen werden durch NutzerMapper nutzerMapper. Sie stellt die
	 * Singelton-Eigenschaft sicher. Methode soll nur über diese statische
	 * Methode aufgerufen werden
	 * 
	 * @return nutzerMapper
	 * @see createNutzer
	 */
	public static NutzerMapper nutzerMapper() {
		if (nutzerMapper == null) {
			nutzerMapper = new NutzerMapper();
		}
		return nutzerMapper;
	}

	/**
	 * Die Methode "createNutzer" ermöglicht das Einfügen von Objekten "Nutzer".
	 * 
	 * @param nutzer
	 * @return
	 * @see createNutzer
	 */
	public Nutzer createNutzer(Nutzer nutzer) {

		/**
		 * Verbindung zur Datenbank wird aufgebaut
		 */
		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();

			/**
			 * Zunächst schauen wir nach, welches der momentan höchste
			 * Primärschlüsselwert ist in der Tabelle Nutzer.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM nutzer");
			if (rs.next()) {
				nutzer.setId(rs.getInt("maxid") + 1);

				/**
				 * Druchführen der Einfüge Operation via Prepared Statement
				 */
				PreparedStatement stmt1 = con.prepareStatement(
						"INSERT INTO nutzer (id, vorname, nachname, nickname, email) VALUES (?, ?, ?, ?, ?) ",

						Statement.RETURN_GENERATED_KEYS);
				stmt1.setInt(1, nutzer.getId());
				stmt1.setString(2, nutzer.getVorname());
				stmt1.setString(3, nutzer.getNachname());
				stmt1.setString(4, nutzer.getNickname());
				stmt1.setString(5, nutzer.getEmail());

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
		return nutzer;
	}

	/**
	 * Mit dieser Methode "updateNutzer" wird das Aktualisieren eines Objektes
	 * von "Nutzer" ermöglicht.
	 * 
	 * @param nutzer
	 * @return nutzer vom Objekt Nutzer
	 */
	/**
	 * Verbindung zur Datenbank wird aufgebaut
	 */

	public Nutzer updateNutzer(Nutzer nutzer) {

		/**
		 * Verbindung zur Datenbank wird aufgebaut
		 */
		Connection con = DBConnection.connection();

		try {
			PreparedStatement stmt1 = con.prepareStatement(
					"UPDATE `nutzer` SET `vorname`= ?, `nachname`= ?,`nickname`= ?,`email`= ? WHERE id= ?");
			stmt1.setString(1, nutzer.getVorname());
			stmt1.setString(2, nutzer.getNachname());
			stmt1.setString(3, nutzer.getNickname());
			stmt1.setString(4, nutzer.getEmail());
			stmt1.setInt(5, nutzer.getId());

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

		return nutzer;
	}

	/**
	 * Die Methode "deleteNutzer" ermöglicht das Löschen von einem Objekt
	 * "Nutzer".
	 * 
	 * @param nutzer
	 */
	public void deleteNutzer(Nutzer nutzer) {

		/**
		 * Verbindung zur Datenbank wird aufgebaut
		 */
		Connection con = DBConnection.connection();

		try {
			/**
			 * Durchführen der Löschoperation
			 */
			PreparedStatement stmt = con.prepareStatement("DELETE FROM `nutzer` WHERE id= ?");
			stmt.setInt(1, nutzer.getId());
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

	/**
	 * Methode "findAllNutzer" um alle Nutzer aus dem Vector<Nutzer>
	 * zurückzugeben
	 * 
	 * @param nutzerid
	 * @return result - gibt als Result alle Nutzer zurück
	 */
	public Vector<Nutzer> findAllNutzer() {

		/**
		 * Verbindung zur Datenbank wird aufgebaut
		 */
		Connection con = DBConnection.connection();

		/**
		 * Es wird ein Vector angelegt, um Nutzer darin zu speichern
		 */
		Vector<Nutzer> result = new Vector<Nutzer>();

		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM `nutzer` ORDER BY `email` ASC");

			ResultSet rs = stmt.executeQuery();
			/**
			 * Für jeden Eintrag Nutzer ein Nutzer-Objekt erstellt.
			 */
			while (rs.next()) {
				Nutzer nutzer = new Nutzer();

				nutzer.setId(rs.getInt("id"));
				nutzer.setVorname(rs.getString("vorname"));
				nutzer.setNachname(rs.getString("nachname"));
				nutzer.setNickname(rs.getString("nickname"));
				nutzer.setEmail(rs.getString("email"));

				/**
				 * Hinzufügen des neuen Objekts zum Ergebnisvektor
				 */
				result.addElement(nutzer);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		/**
		 * Ergebnisvektor zurückgeben
		 */
		return result;
	}

	public Nutzer findNutzerByEmail(String email) {

		/**
		 * Verbindung zur Datenbank wird aufgebaut
		 */
		Connection con = DBConnection.connection();

		Nutzer n = new Nutzer();

		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM `nutzer` WHERE `email` = ?");

			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();

			/**
			 * Für jeden Eintrag im Suchergebnis wird nun ein Nutzer-Objekt
			 * erstellt.
			 */
			while (rs.next()) {
				Nutzer nutzer = new Nutzer();

				nutzer.setId(rs.getInt("id"));
				nutzer.setVorname(rs.getString("vorname"));
				nutzer.setNachname(rs.getString("nachname"));
				nutzer.setNickname(rs.getString("nickname"));
				nutzer.setEmail(rs.getString("email"));

				n = nutzer;
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
		return n;

	}

	public Nutzer findNutzerById(int nutzerID) {

		/**
		 * Verbindung zur Datenbank wird aufgebaut
		 */
		Connection con = DBConnection.connection();

		Nutzer n = new Nutzer();

		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM `nutzer` WHERE `id` = ?");
			stmt.setInt(1, nutzerID);
			ResultSet rs = stmt.executeQuery();

			/**
			 * Für jeden Eintrag im Suchergebnis wird nun ein Nutzer-Objekt
			 * erstellt.
			 */
			while (rs.next()) {
				Nutzer nutzer = new Nutzer();

				nutzer.setId(rs.getInt("id"));
				nutzer.setVorname(rs.getString("vorname"));
				nutzer.setNachname(rs.getString("nachname"));
				nutzer.setNickname(rs.getString("nickname"));
				nutzer.setEmail(rs.getString("email"));

				n = nutzer;

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
		return n;

	}

	public Vector<Nutzer> findAllNutzerById() {

		/**
		 * Verbindung zur Datenbank wird aufgebaut
		 */
		Connection con = DBConnection.connection();

		Vector<Nutzer> result = new Vector<Nutzer>();

		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM `nutzer`");
			// stmt.setInt(1, nutzerID);
			ResultSet rs = stmt.executeQuery();

			/**
			 * Für jeden Eintrag im Suchergebnis wird nun ein Nutzer-Objekt
			 * erstellt.
			 */
			while (rs.next()) {
				Nutzer nutzer = new Nutzer();

				nutzer.setId(rs.getInt("id"));
				nutzer.setVorname(rs.getString("vorname"));
				nutzer.setNachname(rs.getString("nachname"));
				nutzer.setNickname(rs.getString("nickname"));
				nutzer.setEmail(rs.getString("email"));

				result.addElement(nutzer);

			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;

	}
}