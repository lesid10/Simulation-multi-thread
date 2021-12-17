package fr.istic.m1.csr.internals;

import fr.istic.m1.csr.utils.EtatBus;

public class Arret {

	private boolean busPresent = false;
	private boolean busIsPass = false;
	private int nbrPlaceOccupee = 0;

	public synchronized void stationner(Bus bus) {

		while (busPresent) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		this.busPresent = true;

		System.out.println("Le bus " + Thread.currentThread().toString() + " est stationné.");

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

	public synchronized void monter(Voyageur voyageur) {
		// System.out.println(new StringBuilder().append("1) Le voyageur :
		// ").append(Thread.currentThread().toString()).append(" dans le
		// ").append(this.nbBus).append(" busPresent:
		// ").append(this.busPresent).toString());

		while (!this.busPresent || nbrPlaceOccupee >= Bus.NBRE_PLACE_LIMITE) {
			// System.out.println("Le voyageur : " + Thread.currentThread().toString() + "
			// part dormir " + this.busPresent);
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		// v.setEtat(EtatVoyageur.MONTE_DANS_UN_BUS);
		System.out.println("Le voyageur " + voyageur.getVoyageurName() + " est monté dans le bus.");
		/* Occuper les places du bus */
		this.nbrPlaceOccupee++;
	}

	public synchronized void depart() {

		System.out.println("Le bus : " + Thread.currentThread().toString() + " quitte l'arrêt avec à son bord "
				+ this.nbrPlaceOccupee + " passager(s).");

		this.nbrPlaceOccupee = 0;
		this.busPresent = false;
		this.busIsPass = true;
		// notifyAll();

	}

}
