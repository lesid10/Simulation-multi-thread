package fr.istic.csr.simulation;


public class Reseau {
	
	static int NB_VOYAGEUR = 100;
	static int NB_BUS = 10;
	
	private Arret arret ;
	
	private Billeterie billeterie ;
	
	private Voyageur[] voyageurs = new Voyageur[NB_VOYAGEUR] ;
	
	private Bus[] bus = new Bus[NB_BUS];
	
	
	public Reseau() {
		
		this.arret = new Arret();
		this.billeterie = new Billeterie();
		
		/*
		 * Instantiation
		 * */
		for(int i=0; i<NB_VOYAGEUR; i++) {voyageurs[i] = new Voyageur(this.billeterie,this.arret,i); }
		
		for(int i=0; i<NB_BUS; i++) {
			
			bus[i] = new Bus(this.arret,i);
			bus[i].setDaemon(true);
		}
		
		
		
		/*
		 * Demarrages
		 * */
		
		for(int i=0; i<NB_BUS; i++) {bus[i].start();}
		
		for(int i=0; i<NB_VOYAGEUR; i++) {
			voyageurs[i].start();
			/*
			try {
				voyageurs[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
		}

		
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new Reseau();
		
		//System.out.println("Terminer ");

	}

}
