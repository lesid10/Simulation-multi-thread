package fr.istic.csr.simulation;

public class Arret {
	
	
	
	private boolean busPresent = false;
	private int nbrPlaceOccupee = 0;
	
	
	public synchronized void stationner() {
		
		
		while(busPresent) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		busPresent = true;
		
		System.out.println("Le bus stationné est: "+Thread.currentThread().toString()+" et attend pendant "+Bus.TEMPS_STATIONNEMENT+" à l arret");
		
		try {
			Thread.sleep(Bus.TEMPS_STATIONNEMENT);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} 
	
	
	public synchronized void monter() {
		
		while(!busPresent || nbrPlaceOccupee >= Bus.NBRE_PLACE_LIMITE) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/*Occuper les places du bus*/
		nbrPlaceOccupee++ ;
		
	}
	
	
	public synchronized void depart() {
		
		nbrPlaceOccupee = 0;
		busPresent = false;
		notifyAll();
		
	}
	
	
}
