package fr.istic.m1.csr.internals;

public class Billeterie {

	/**
	 * Nombre de billet vendus
	 */
	private static int nbrBilletVendu = 0;

	/**
	 * Permet de vendre les billets aux voyageurs
	 */
	public synchronized void vendre() {
		nbrBilletVendu++;
	}

	public static int getNbrBilletVendu() {
		return nbrBilletVendu;
	}

}
