package de.hdm.itprojekt.shared;

import java.sql.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.itprojekt.shared.bo.Abonnement;
import de.hdm.itprojekt.shared.bo.Nutzer;
import de.hdm.itprojekt.shared.bo.Pinnwand;
import de.hdm.itprojekt.shared.bo.Textbeitrag;

@RemoteServiceRelativePath("socialmedia")

public interface SocialMediaAdmin extends RemoteService{

	void init() throws IllegalArgumentException;
	
	public Nutzer createNutzer(String email) throws IllegalArgumentException;
	
	public Nutzer createNutzer(String email, String vorname, String nachname, String nickname) throws IllegalArgumentException;
	
	public Nutzer checkEmail(String email) throws IllegalArgumentException;

	Vector<Abonnement> findAbonnementByNutzerID(int nutzerID) throws IllegalArgumentException;

	Vector<Pinnwand> findAllPinnwand() throws IllegalArgumentException;

	Nutzer findNutzerByID(int nutzerID);

	Textbeitrag createTextbeitrag(int pinnwandID, int nutzerID, int kommentarID, String inhalt) throws IllegalArgumentException;

	void deleteNutzer(Nutzer nutzer) throws IllegalArgumentException;

	Pinnwand createPinnwand(int nutzerID) throws IllegalArgumentException;

	void deletePinnwand(Pinnwand pinnwand) throws IllegalArgumentException;

	Vector<Nutzer> findNutzerByAbo(int nutzerID) throws IllegalArgumentException;

}
