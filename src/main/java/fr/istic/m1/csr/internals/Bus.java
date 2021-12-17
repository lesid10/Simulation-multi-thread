package fr.istic.m1.csr.internals;

import fr.istic.m1.csr.utils.EtatBus;

public class Bus extends Thread {

	/**
	 * Nombre de place dans le bus
	 */
	static int NBRE_PLACE_LIMITE = 15;

	/**
	 * Temps de stationnement du bus � l'arret en ms
	 */
	static int TEMPS_STATIONNEMENT = 2000;
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
		System.out.println(
				"le thread " + Thread.currentThread().toString() + " est arrivé à l'arret et essai de ce stationne");
		arret.stationner(this);

	}

	void departBusArret() {
		System.out.println("le thread " + Thread.currentThread().toString() + " quitte l'arret");
		arret.depart();

	}

	void voyager() {
		System.out.println("le thread Bus " + Thread.currentThread().toString() + " effectue un voyage de 4000 ms");
		this.etat = EtatBus.EN_VOYAGE;
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		System.out.println("Le run des Bus est lancé");

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

	@Override
	public String toString() {
		return "Bus " + nb;
	}

}
