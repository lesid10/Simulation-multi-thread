package fr.istic.m1.csr.internals;

import fr.istic.m1.csr.utils.EtatBus;

public class Arret {

	private boolean busPresent = false;
	private int nbrPlaceOccupee = 0;

	public synchronized void stationner(Bus bus) {

		while (busPresent) {

			try {
				// System.out.println("2) stationner: " + Thread.currentThread().toString() + "
				// wait");
				wait();
				// System.out.println("3) stationner: " + Thread.currentThread().toString() + "
				// awake");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		busPresent = true;

		System.out.println("Le bus stationné est: " + Thread.currentThread().toString() + " et attend pendant "
				+ Bus.TEMPS_STATIONNEMENT + " à l arret");

		try {
			bus.setEtat(EtatBus.STATIONNE);
			Thread.sleep(Bus.TEMPS_STATIONNEMENT);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public synchronized void monter() {

		while (!busPresent || nbrPlaceOccupee >= Bus.NBRE_PLACE_LIMITE) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		System.out.println("J'ai pu monter dans le bus");
		/* Occuper les places du bus */
		nbrPlaceOccupee++;

	}

	public synchronized void depart() {

		nbrPlaceOccupee = 0;
		busPresent = false;
		notifyAll();

	}

}
