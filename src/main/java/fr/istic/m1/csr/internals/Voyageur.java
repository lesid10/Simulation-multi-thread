package fr.istic.m1.csr.internals;

import fr.istic.m1.csr.utils.EtatVoyageur;

public class Voyageur extends Thread {

	/**
	 * Temps que mettent les voyageur pour se rendre a l arret en ms
	 */
	static int TEMPS_DEPLACEMENT = 2000;

	/*
	 * La billeterie
	 */
	private Billeterie billeterie;
	private String vName;
	private int id;

	/*
	 * L'arret
	 */
	private Arret arret;

	private EtatVoyageur etat;

	/**
	 * Construit un objet instance de Voyager
	 * 
	 * @param billeterie La billeterie
	 * @param arret      L'arret
	 */
	public Voyageur(Billeterie billeterie, Arret arret, String nom) {
		this.billeterie = billeterie;
		this.arret = arret;
		this.vName = nom;
		this.etat = EtatVoyageur.SANS_BILLET;
	}

	void acheterBillet() {
		System.out.println("le thread " + Thread.currentThread().toString() + " achète un billet de bus");
		billeterie.vendre();
		this.etat = EtatVoyageur.AVEC_BILLET;
	}

	void allerArret() {
		// System.out.println("le thread " + Thread.currentThread().toString() + " se
		// rend a l arret en " + TEMPS_DEPLACEMENT + " ms");
		try {
			Thread.sleep(TEMPS_DEPLACEMENT);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	void monterDansBus() {
		// System.out.println("le thread " + Thread.currentThread().toString() + "
		// essaie de monter dans le bus");
		arret.monter(this);
		// arret.monter(this);
	}

	@Override
	public void run() {
		// System.out.println("Le run des voyageurs est lancé");
		acheterBillet();
		allerArret();
		monterDansBus();

		// System.out.println("Je suis monté dans le bus hein donc il faut changer mon
		// état");
		// this.etat = EtatVoyageur.MONTE_DANS_UN_BUS;
	}

	public EtatVoyageur getEtat() {
		return etat;
	}

	public void setEtat(EtatVoyageur etat) {
		this.etat = etat;
	}

	public String getVoyageurName() {
		return vName;
	}

	public int get_id() {
		return id;
	}

	public void set_id(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Voyageur " + vName;
	}

}
