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

		while (!this.busPresent || nbrPlaceOccupee >= Bus.NBRE_PLACE_LIMITE) {

			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		System.out.println("Le voyageur " + voyageur.getVoyageurName() + " est monté dans le bus.");
		this.nbrPlaceOccupee++;
	}

	public synchronized void depart() {

		System.out.println("Le bus : " + Thread.currentThread().toString() + " quitte l'arrêt avec à son bord "
				+ this.nbrPlaceOccupee + " passager(s).");

		this.nbrPlaceOccupee = 0;
		this.busPresent = false;
		this.busIsPass = true;

	}

}
