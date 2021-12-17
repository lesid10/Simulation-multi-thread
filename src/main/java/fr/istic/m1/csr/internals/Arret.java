package fr.istic.m1.csr.internals;

import java.time.Instant;
import java.util.ArrayList;

import fr.istic.m1.csr.utils.EtatBus;
import fr.istic.m1.csr.utils.EtatVoyageur;

public class Arret {

	private boolean busPresent = false;
	private int nbrPlaceOccupee = 0;
	private ArrayList<Voyageur> listOfVoyageur = new ArrayList<>(15);

	public synchronized void stationner(Bus bus) {

		while (busPresent) {

			try {
				// System.out.println(Instant.now() + " Le " + Thread.currentThread().toString()
				// + " c'est endormi");
				wait();
				// System.out.println(Instant.now() + " Le " + Thread.currentThread().toString()
				// + " c'est réveillé");
				Thread.sleep(1_000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		this.busPresent = true;

		System.out.println(
				Instant.now() + " Le bus stationné est: " + Thread.currentThread().toString() + " et attend pendant "
						+ Bus.TEMPS_STATIONNEMENT + " à l arret " + this.listOfVoyageur.size());

		try {

			// notifyAll();
			bus.setEtat(EtatBus.STATIONNE);
			// Thread.sleep(500);
			// Thread.sleep(Bus.TEMPS_STATIONNEMENT);
			if (this.listOfVoyageur.size() == 0) {
				Thread.sleep(Bus.TEMPS_STATIONNEMENT);
			} else {
				this.listOfVoyageur.get(this.listOfVoyageur.size() - 1).join(Bus.TEMPS_STATIONNEMENT);
				// this.listOfVoyageur.forEach( v -> {

				// });
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void monter(Voyageur v) {

		while (!this.busPresent || listOfVoyageur.size() >= Bus.NBRE_PLACE_LIMITE) {

			try {
				System.out.println(
						Instant.now() + " 1) Le " + Thread.currentThread().toString() + " c'est endormi");
				wait();
				System.out.println(
						Instant.now() + " 2) Le " + Thread.currentThread().toString() + " c'est réveillé");
				// Thread.sleep(2_000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// v.setEtat(EtatVoyageur.MONTE_DANS_UN_BUS);

		listOfVoyageur.add(v);
		System.out.println(Instant.now() + " " + Thread.currentThread().toString()
				+ ": J'ai pu monter dans le bus et il y'a " + listOfVoyageur.size() + " personne");
		/* Occuper les places du bus */
		synchronized (this) {
			System.out
					.println(Instant.now() + " " + Thread.currentThread().toString()
							+ ": J'ai pu monter donc je mets à jour");
			this.nbrPlaceOccupee++;
		}

	}

	public synchronized void depart() {

		System.out.println(
				Instant.now() + " Le " + Thread.currentThread().toString() + " quitte l'arrêt avec à son bord "
						+ this.nbrPlaceOccupee + " passager");

		if (this.nbrPlaceOccupee > 0) {
			this.listOfVoyageur.clear();
		}

		this.nbrPlaceOccupee = 0;
		this.busPresent = false;

		notifyAll();

	}

}
