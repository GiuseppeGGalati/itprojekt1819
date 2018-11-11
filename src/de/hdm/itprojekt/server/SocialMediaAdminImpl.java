package de.hdm.itprojekt.server;

import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itprojekt.server.db.AbonnementMapper;
import de.hdm.itprojekt.server.db.KommentarMapper;
import de.hdm.itprojekt.server.db.NutzerMapper;
import de.hdm.itprojekt.server.db.PinnwandMapper;
import de.hdm.itprojekt.server.db.TextbeitragMapper;
import de.hdm.itprojekt.shared.SocialMediaAdmin;
import de.hdm.itprojekt.shared.bo.Abonnement;
import de.hdm.itprojekt.shared.bo.Nutzer;
import de.hdm.itprojekt.shared.bo.Pinnwand;

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
	public Nutzer createNutzer(String email) throws IllegalArgumentException{
		Nutzer nutzer = new Nutzer();
		nutzer.setEmail(email);
		return this.nutzerMapper.createNutzer(nutzer);
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
	public Vector<Abonnement> findAbonnementByNutzerID(int nutzerID) throws IllegalArgumentException{
		return this.abonnementMapper.findAbonnementByNutzerID(nutzerID);
		
	}
	@Override
	public Vector<Pinnwand> findAllPinnwand() throws IllegalArgumentException{
		return this.pinnwandMapper.findAllPinnwand();
	}
	public Vector<Nutzer> findNutzerByAbonnement() throws IllegalArgumentException{
		return null;
		//noch machen
	}
		
//		findallaboby nutzer id 
//		findallpinnwände
//		findnutzerbyabo
//		getnickname
	}


