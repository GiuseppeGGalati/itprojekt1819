package de.hdm.itprojekt.server;

import java.util.Date;
import java.util.Vector;

import com.google.appengine.api.utils.SystemProperty.Environment.Value;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itprojekt.client.KommentarNutzerWrapper;
import de.hdm.itprojekt.server.db.AbonnementMapper;
import de.hdm.itprojekt.server.db.KommentarMapper;
import de.hdm.itprojekt.server.db.NutzerMapper;
import de.hdm.itprojekt.server.db.PinnwandMapper;
import de.hdm.itprojekt.server.db.TextbeitragMapper;
import de.hdm.itprojekt.shared.SocialMediaAdmin;
import de.hdm.itprojekt.shared.bo.Abonnement;
import de.hdm.itprojekt.shared.bo.Kommentar;
import de.hdm.itprojekt.shared.bo.Nutzer;
import de.hdm.itprojekt.shared.bo.Pinnwand;
import de.hdm.itprojekt.shared.bo.Textbeitrag;

/**
 * Diese Klasse ist die Implementierungsklasse des Interface SocialMediaAdmin.
 * Hier befindet sich die Applikationslogik.
 * 
 * @see SocialMediaAdmin
 * @see SocialMediaAdminAsync
 * @see RemoteServiceServlet
 */
@SuppressWarnings("serial")
public class SocialMediaAdminImpl extends RemoteServiceServlet implements SocialMediaAdmin {

	/**
	 * Referenz auf den KommentarMapper, der Kommentarobjekte mit der Datenbank
	 * abgleicht.
	 */
	private KommentarMapper kommentarMapper = null;
	/**
	 * Referenz auf den NutzerMapper, der Nutzerobjekte mit der Datenbank
	 * abgleicht.
	 */
	private NutzerMapper nutzerMapper = null;
	/**
	 * Referenz auf den PinnwandMapper, der Pinnwandobjekte mit der Datenbank
	 * abgleicht.
	 */
	private PinnwandMapper pinnwandMapper = null;
	/**
	 * Referenz auf den TextbeitragMapper, der Textbeitragobjekte mit der
	 * Datenbank abgleicht.
	 */
	private TextbeitragMapper textbeitragMapper = null;
	/**
	 * Referenz auf den AbonnementMapper, der Abonnementobjekte mit der
	 * Datenbank abgleicht.
	 */
	private AbonnementMapper abonnementMapper = null;

	/**
	 * Ein No-Argument-Konstruktor für die Client-seitige Erzeugung von
	 * GWT.create
	 * 
	 * @see #init()
	 * @throws IllegalArgumentException
	 */
	public SocialMediaAdminImpl() throws IllegalArgumentException {

	}

	/**
	 * Initialsierungsmethode. Diese Methode muss für jede Instanz von
	 * SocialMediaAdminImpl aufgerufen werden.
	 */
	@Override
	public void init() throws IllegalArgumentException {
		this.kommentarMapper = KommentarMapper.kommentarMapper();
		this.nutzerMapper = NutzerMapper.nutzerMapper();
		this.pinnwandMapper = PinnwandMapper.pinnwandMapper();
		this.textbeitragMapper = TextbeitragMapper.textbeitragMapper();
		this.abonnementMapper = AbonnementMapper.abonnementMapper();

	}

	/**
	 * Anlegen eines Nutzers
	 * 
	 * @param email;
	 *            ist die Google E-Mail Adresse des Nutzers
	 * @return Nutzer; Zurückgegeben wird ein Objekt der Klasse Nutzer
	 */
	@Override
	public Nutzer createNutzer(String email, String vorname, String nachname, String nickname)
			throws IllegalArgumentException {
		Nutzer nutzer = new Nutzer();
		nutzer.setEmail(email);
		nutzer.setVorname(vorname);
		nutzer.setNachname(nachname);
		nutzer.setNickname(nickname);
		return this.nutzerMapper.createNutzer(nutzer);
	}

	@Override
	public Pinnwand createPinnwand(int nutzerID) throws IllegalArgumentException {
		Pinnwand pinnwand = new Pinnwand();
		pinnwand.setNutzerID(nutzerID);

		return this.pinnwandMapper.createPinnwand(pinnwand);
	}

	@Override
	public Nutzer createNutzer(String email) throws IllegalArgumentException {
		Nutzer nutzer = new Nutzer();
		nutzer.setEmail(email);
		return this.nutzerMapper.createNutzer(nutzer);
	}

	@Override
	public Abonnement createAbonnement(int nutzerID, int pinnwandID) throws IllegalArgumentException {
		Abonnement abonnement = new Abonnement();
		abonnement.setNutzerID(nutzerID);
		abonnement.setPinnwandID(pinnwandID);

		Abonnement abo = new Abonnement();
		abo = this.abonnementMapper.findAllAbonnement(nutzerID, pinnwandID);

		if (abo.getId() != 0) {

			return null;
		} else {

			return this.abonnementMapper.createAbonnement(abonnement);
		}
	}

	@Override
	public Nutzer checkEmail(String email) throws IllegalArgumentException {
		Nutzer nutzer = new Nutzer();
		nutzer = this.nutzerMapper.findNutzerByEmail(email);

		if (nutzer.getId() == 0) {
			return null;
		} else {
			return nutzer;
		}
	}

	@Override
	public Vector<Abonnement> findAbonnementByNutzerID(int nutzerID) throws IllegalArgumentException {
		return this.abonnementMapper.findAbonnementByNutzerID(nutzerID);

	}

	@Override
	public Abonnement findAllAbonnement(int nutzerID, int pinnwandID) throws IllegalArgumentException {
		return this.abonnementMapper.findAllAbonnement(nutzerID, pinnwandID);
	}

	@Override
	public Vector<Pinnwand> findAllPinnwand() throws IllegalArgumentException {
		return this.pinnwandMapper.findAllPinnwand();
	}

	@Override
	public Vector<Nutzer> findAllNutzer() throws IllegalArgumentException {
		return this.nutzerMapper.findAllNutzer();
	}

	@Override
	public Vector<Nutzer> findNutzerByAbo(int nutzerID) throws IllegalArgumentException {

		Vector<Abonnement> aboVector = findAbonnementByNutzerID(nutzerID);

		Vector<Pinnwand> pwVector = findAllPinnwand();

		Vector<Nutzer> nutzerVector = new Vector<Nutzer>();

		nutzerVector.add(findNutzerByID(nutzerID));

		for (int i = 0; i < aboVector.size(); i++) {
			for (int o = 0; o < pwVector.size(); o++) {
				if (aboVector.elementAt(i).getNutzerID() == pwVector.elementAt(o).getNutzerID()) {
					nutzerVector.add(findNutzerByID(aboVector.elementAt(i).getPinnwandID()));
				}

			}
		}

		return nutzerVector;
	}

	@Override
	public Vector<Nutzer> findAllNutzerByID() throws IllegalArgumentException {

		return this.nutzerMapper.findAllNutzerById();
	}

	@Override
	public Nutzer findNutzerByID(int nutzerID) throws IllegalArgumentException {
		return this.nutzerMapper.findNutzerById(nutzerID);
	}

	@Override
	public Pinnwand findPinnwandByNutzerID(int nutzerID) throws IllegalArgumentException {
		return this.pinnwandMapper.findPinnwandByNutzerId(nutzerID);
	}

	@Override
	public Vector<Textbeitrag> findTextbeitragByPinnwandID(int pinnwandID) throws IllegalArgumentException {
		return this.textbeitragMapper.findTextbeitragByPinnwandId(pinnwandID);
	}

	@Override
	public Vector<Textbeitrag> findTextbeitragByNutzerID(int nutzerID) throws IllegalArgumentException {
		return this.textbeitragMapper.findTextbeitragByNutzerId(nutzerID);
	}

	@Override
	public Vector<KommentarNutzerWrapper> findKommentarByTextbeitragId(int textbeitragID)
			throws IllegalArgumentException {
		Vector<Kommentar> kommentarVector = this.kommentarMapper.findKommentarByTextbeitragId(textbeitragID);
		Vector<KommentarNutzerWrapper> wrapperVector = new Vector<KommentarNutzerWrapper>();

		for (Kommentar kommentar : kommentarVector) {
			wrapperVector.add(new KommentarNutzerWrapper(kommentar, findNutzerByID(kommentar.getNutzerID())));
		}

		return wrapperVector;
	}

	@Override
	public Textbeitrag createTextbeitrag(int nutzerID, String inhalt) throws IllegalArgumentException {
		Textbeitrag textbeitrag = new Textbeitrag();
		textbeitrag.setPinnwandID(nutzerID);
		textbeitrag.setNutzerID(nutzerID);
		textbeitrag.setInhalt(inhalt);
		textbeitrag.setErzeugungsdatum(new Date());
		textbeitrag.setModifikationsdatum(new Date());
		return this.textbeitragMapper.createTextbeitrag(textbeitrag);
	}

	@Override
	public Kommentar createKommentar(int textbeitragID, int nutzerID, String inhalt) throws IllegalArgumentException {
		Kommentar kommentar = new Kommentar();
		kommentar.setTextbeitragID(textbeitragID);
		kommentar.setNutzerID(nutzerID);
		kommentar.setInhalt(inhalt);
		kommentar.setErzeugungsdatum(new Date());
		return this.kommentarMapper.createKommentar(kommentar);
	}

	@Override
	public void deleteNutzer(Nutzer nutzer) throws IllegalArgumentException {
		Pinnwand pw = new Pinnwand();
		pw.setNutzerID(nutzer.getId());

		this.pinnwandMapper.deletePinnwandByNutzerID(pw);
		this.nutzerMapper.deleteNutzer(nutzer);
	}

	@Override
	public void deletePinnwand(Pinnwand pinnwand) throws IllegalArgumentException {
		pinnwand.setId(pinnwand.getId());

		this.pinnwandMapper.deletePinnwand(pinnwand);
	}

	@Override
	public void deleteAbonnement(int nutzerID, int pinnwandID) throws IllegalArgumentException {
		Nutzer n = findNutzerByID(nutzerID);
		// Pinnwand p = findPinnwandByNutzerID(n.getId());
		Abonnement abo = new Abonnement();
		abo.setNutzerID(nutzerID);
		abo.setPinnwandID(pinnwandID);

		this.abonnementMapper.deleteAbonnement(abo);

	}

	@Override
	public void deleteTextbeitrag(Textbeitrag textbeitrag) throws IllegalArgumentException {
		this.textbeitragMapper.deleteTextbeitrag(textbeitrag);

	}

	@Override
	public void deleteKommentar(Kommentar kommentar) throws IllegalArgumentException {
		this.kommentarMapper.deleteKommentar(kommentar);
	}

	@Override
	public void saveNutzer(Nutzer nutzer) throws IllegalArgumentException {
		this.nutzerMapper.updateNutzer(nutzer);
	}

	@Override
	public void saveTextbeitrag(Textbeitrag textbeitrag) throws IllegalArgumentException {
		textbeitrag.setModifikationsdatum(new Date());
		textbeitragMapper.updateTextbeitrag(textbeitrag);
	}

	@Override
	public void saveKommentar(Kommentar kommentar) throws IllegalArgumentException {
		kommentar.setModifikationsdatum(new Date());
		kommentarMapper.updateKommentar(kommentar);
	}

}
