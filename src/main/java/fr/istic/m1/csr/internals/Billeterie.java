package fr.istic.m1.csr.internals;

public class Billeterie {

	/**
	 * Nombre de billet vendus
	 */
	private static int nbrBilletVendu = 0;

	public synchronized void vendre() {
		nbrBilletVendu++;
		// System.out.println("Le nombre de billet vendu est: " + nbrBilletVendu);
	}

	public static int getNbrBilletVendu() {
		return nbrBilletVendu;
	}

}
