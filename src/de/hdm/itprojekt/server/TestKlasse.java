package de.hdm.itprojekt.server;

import java.util.Vector;

import de.hdm.itprojekt.shared.bo.Abonnement;
import de.hdm.itprojekt.shared.bo.Nutzer;

public class TestKlasse {
	public static void main(String[] args) {
		SocialMediaAdminImpl impl = new SocialMediaAdminImpl();
		impl.init();
		
		Vector <Abonnement> a = impl.findAbonnementByNutzerID(2);
		
		for (Abonnement abonnement : a) {
			System.out.println(abonnement.getPinnwandID());
		}	
		
		
		Vector <Nutzer> n = impl.findNutzerByAbo(2);
		
		for (Nutzer nutzer : n) {
			System.out.println(nutzer.getNickname());
		}
		
	}

}
