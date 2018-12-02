package de.hdm.itprojekt.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.shared.bo.Abonnement;
import de.hdm.itprojekt.shared.bo.Nutzer;
import de.hdm.itprojekt.shared.bo.Pinnwand;
import de.hdm.itprojekt.shared.bo.Textbeitrag;

public interface SocialMediaAdminAsync {

	void init(AsyncCallback<Void> callback);
	
	void createNutzer(String email, AsyncCallback<Nutzer> callback);

	void createNutzer(String email, String vorname, String nachname, String nickname,
			AsyncCallback<Nutzer> callback);

	void checkEmail(String email, AsyncCallback<Nutzer> callback);

	void findAbonnementByNutzerID(int nutzerID, AsyncCallback<Vector<Abonnement>> callback);

	void findAllPinnwand(AsyncCallback<Vector<Pinnwand>> callback);

	void findNutzerByID(int nutzerID, AsyncCallback<Nutzer> callback);

	void deleteNutzer(Nutzer nutzer, AsyncCallback<Void> callback);

	void createPinnwand(int nutzerID, AsyncCallback<Pinnwand> callback);

	void deletePinnwand(Pinnwand pinnwand, AsyncCallback<Void> callback);

	void findNutzerByAbo(int nutzerID, AsyncCallback<Vector<Nutzer>> callback);

	void saveNutzer(Nutzer nutzer, AsyncCallback<Void> callback);

	void findPinnwandByNutzerID(int nutzerID, AsyncCallback<Pinnwand> callback);

	void findTextbeitragByPinnwandID(int pinnwandID, AsyncCallback<Vector<Textbeitrag>> callback);

	void findTextbeitragByNutzerID(int nutzerID, AsyncCallback<Vector<Textbeitrag>> callback);

	void deleteAbonnement(int nutzerID, int pinnwandID, AsyncCallback<Void> callback);

	void createTextbeitrag(int nutzerID, String inhalt, AsyncCallback<Textbeitrag> callback);

	void deleteTextbeitrag(Textbeitrag textbeitrag, AsyncCallback<Void> callback);

	void saveTextbeitrag(Textbeitrag textbeitrag, AsyncCallback<Void> callback);


}
