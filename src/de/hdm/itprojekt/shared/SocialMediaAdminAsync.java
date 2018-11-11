package de.hdm.itprojekt.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.shared.bo.Abonnement;
import de.hdm.itprojekt.shared.bo.Nutzer;
import de.hdm.itprojekt.shared.bo.Pinnwand;

public interface SocialMediaAdminAsync {

	void init(AsyncCallback<Void> callback);
	
	void createNutzer(String email, AsyncCallback<Nutzer> callback);

	void createNutzer(String email, String vorname, String nachname, String nickname,
			AsyncCallback<Nutzer> callback);

	void checkEmail(String email, AsyncCallback<Nutzer> callback);

	void findAbonnementByNutzerID(int nutzerID, AsyncCallback<Vector<Abonnement>> callback);

	void findAllPinnwand(AsyncCallback<Vector<Pinnwand>> callback);


}
