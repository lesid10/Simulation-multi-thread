package fr.istic.m1.csr.internals;

import fr.istic.m1.csr.utils.EtatBus;
import fr.istic.m1.csr.utils.EtatVoyageur;

public class Arret {

	private boolean busPresent = false;
	private boolean busIsPass = false;
	private int nbrPlaceOccupee = 0;
	private String nbBus = "";

	public synchronized void stationner(Bus bus) {

		while (busPresent) {

			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		this.busPresent = true;

		this.nbBus = Thread.currentThread().toString();

		System.out.println("Le bus stationné est: " + Thread.currentThread().toString() + " et attend pendant "
				+ Bus.TEMPS_STATIONNEMENT + " à l arret " + this.busPresent);

		try {
			if (busIsPass) {
				notifyAll();
			}
			bus.setEtat(EtatBus.STATIONNE);
			Thread.sleep(Bus.TEMPS_STATIONNEMENT);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public synchronized void monter() {

		System.out.println(
				"1) Le voyageur  : " + Thread.currentThread().toString() + " dans le " + this.nbBus + " busPresent: "
						+ this.busPresent);

		while (!this.busPresent || nbrPlaceOccupee >= Bus.NBRE_PLACE_LIMITE) {

			System.out.println("2) Le voyageur : " + Thread.currentThread().toString() + "  part dormir "
					+ this.busPresent);
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// v.setEtat(EtatVoyageur.MONTE_DANS_UN_BUS);
		System.out.println("J'ai pu monter dans le bus");
		/* Occuper les places du bus */
		this.nbrPlaceOccupee++;

	}

	public synchronized void depart() {

		System.out.println("Le bus : " + Thread.currentThread().toString() + " quitte l'arrêt avec à son bord "
				+ this.nbrPlaceOccupee + " passager");

		this.nbrPlaceOccupee = 0;
		this.busPresent = false;
		this.busIsPass = true;
		// notifyAll();

	}

}
