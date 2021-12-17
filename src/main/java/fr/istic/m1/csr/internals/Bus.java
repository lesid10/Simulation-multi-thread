package fr.istic.m1.csr.internals;

import java.time.Instant;

import fr.istic.m1.csr.utils.EtatBus;

public class Bus extends Thread {

	/**
	 * Nombre de place dans le bus
	 */
	static int NBRE_PLACE_LIMITE = 15;

	/**
	 * Temps de stationnement du bus à l'arret en ms
	 */
	static int TEMPS_STATIONNEMENT = 5_000;

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
	public Bus(ThreadGroup tg, String tgName, Arret arret, int nb) {

		super(tg, tgName);

		this.arret = arret;
		this.nb = nb;
	}

	void arriverBusArret() {
		System.out.println(
				Instant.now() + " le " + Thread.currentThread().toString()
						+ " est arrivé à l'arret et essai de se stationner");
		arret.stationner(this);
	}

	void departBusArret() {
		// System.out.println("le thread " + Thread.currentThread().toString() + "
		// quitte l'arret");
		arret.depart();

	}

	void voyager() {
		System.out.println(Instant.now() + " le " + Thread.currentThread().toString() + " effectue un voyage de "
				+ TEMPS_VOYAGES + " ms");
		try {
			this.etat = EtatBus.EN_VOYAGE;
			Thread.sleep(TEMPS_VOYAGES);
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
