package fr.istic.csr.simulation;

public class Billeterie {

	
	/**
	 * Nombre de billet vendus
	 */
	private int nbrBilletVendu = 0;
	
	
	public synchronized void vendre() {
		nbrBilletVendu++;
		System.out.println("Le nombre de billet disponible est: "+nbrBilletVendu);
	}
	
	
	
	
	
	
	
}
