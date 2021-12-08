package fr.istic.csr.simulation;

public class Voyageur extends Thread {
	
	
	/**
	 *Temps que mettent les voyageur pour se rendre a l arret en ms
	 */
	static int TEMPS_DEPLACEMENT = 2000;
	
	/*
	 * La billeterie
	 * */
	private Billeterie billeterie;
	private int nb;
	/*
	 * L'arret
	 * */
	private Arret arret;
	
	/**
     * Construit un objet instance de Voyager
     * @param billeterie La billeterie
     * @param arret L'arret
     */
	public Voyageur(Billeterie billeterie, Arret arret,int nb) {
		
		this.billeterie = billeterie;
		this.arret = arret;
		this.nb = nb;
		
	}
	
	
	void acheterBillet() {
		System.out.println("le thread "+Thread.currentThread().toString()+" achete un billet de bus");
		billeterie.vendre();
	}
	
	
	void allerArret() {
		System.out.println("le thread "+Thread.currentThread().toString()+" se rend a l arret en "+TEMPS_DEPLACEMENT+" ms");
		 try {
			Thread.sleep(TEMPS_DEPLACEMENT);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
	void monterDansBus() {
		System.out.println("le thread "+Thread.currentThread().toString()+" monte dans le bus");
		arret.monter();
	}
	
	@Override
	public void run(){
		
		System.out.println("Le run des voyageurs est lancé");
		
		acheterBillet();
		
		allerArret();
		
		monterDansBus();
		
		
	}


	@Override
	public String toString() {
		return "Voyageur "+nb;
	}
	
	
	
	
	
}
