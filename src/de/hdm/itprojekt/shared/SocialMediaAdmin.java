package de.hdm.itprojekt.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.itprojekt.shared.bo.Abonnement;
import de.hdm.itprojekt.shared.bo.Nutzer;
import de.hdm.itprojekt.shared.bo.Pinnwand;

@RemoteServiceRelativePath("socialmedia")

public interface SocialMediaAdmin extends RemoteService{

	void init() throws IllegalArgumentException;
	
	public Nutzer createNutzer(String email) throws IllegalArgumentException;
	
	public Nutzer createNutzer(String email, String vorname, String nachname, String nickname) throws IllegalArgumentException;
	
	public Nutzer checkEmail(String email) throws IllegalArgumentException;

	Vector<Abonnement> findAbonnementByNutzerID(int nutzerID) throws IllegalArgumentException;

	Vector<Pinnwand> findAllPinnwand() throws IllegalArgumentException;

}
