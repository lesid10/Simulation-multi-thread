package fr.istic.m1.csr.internals;

import fr.istic.m1.csr.utils.EtatBus;

public class Bus extends Thread {

	/**
	 * Nombre de place dans le bus
	 */
	static int NBRE_PLACE_LIMITE = 15;

	/**
	 * Temps de stationnement du bus à l'arret en ms
	 */
	static int TEMPS_STATIONNEMENT = 10000;

	/**
	 * Temps de stationnement du bus à l'arret en ms
	 */
	int TEMPS_VOYAGES = 15_000;

	/*
	 * L'arret
	 */
	private Arret arret;
	private int nb;
	private EtatBus etat;

	/**
	 * Construit un objet instance de Bus
	 * 
	 * @param arret L'arret
	 */
	public Bus(Arret arret, int nb) {
		this.arret = arret;
		this.nb = nb;
	}

	void arriverBusArret() {
		// System.out.println("le bus " + Thread.currentThread().toString() + " est
		// arrivé à l'arret et essai de se stationner");
		arret.stationner(this);
	}

	void departBusArret() {
		// System.out.println("le thread " + Thread.currentThread().toString() + "
		// quitte l'arret");
		arret.depart();
	}

	void voyager() {
		// System.out.println("le thread " + Thread.currentThread().toString() + "
		// effectue un voyage de " + TEMPS_VOYAGES + "ms");
		try {
			this.etat = EtatBus.EN_VOYAGE;
			Thread.sleep(TEMPS_VOYAGES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		System.out.println("Nouveau bus démarré " + currentThread().toString() + ".");
		while (true) {
			arriverBusArret();
			departBusArret();
			voyager();
		}
	}

	public EtatBus getEtat() {
		return etat;
	}

	public void setEtat(EtatBus etat) {
		this.etat = etat;
	}

	public int getNb() {
		return nb;
	}

	@Override
	public String toString() {
		return "Bus " + nb;
	}

}
