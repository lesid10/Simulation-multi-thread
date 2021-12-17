package fr.istic.m1.csr.database;

import java.util.HashMap;
import java.util.Map;

import fr.istic.m1.csr.internals.Arret;
import fr.istic.m1.csr.internals.Billeterie;
import fr.istic.m1.csr.internals.Bus;
import fr.istic.m1.csr.internals.Voyageur;

/**
 *
 * In-memory database
 *
 * @author ctedeschi
 * @author msimonin
 *
 */
public class InMemoryDatabase {

    static int NB_BUS = 10;

    private Arret arret;

    private Billeterie billeterie;

    /** User Hashmap. */
    private Map<Integer, Voyageur> voyageurs;

    /** Tweet Hashmap. */
    private Map<Integer, Bus> bus;

    // private Bus[] bus = new Bus[NB_BUS];

    private Integer voyageurCount;
    private Integer busCount;

    public InMemoryDatabase(Billeterie billeterie, Arret arret) {
        this.voyageurs = new HashMap<Integer, Voyageur>();
        this.bus = new HashMap<Integer, Bus>();
        this.billeterie = billeterie;
        this.arret = arret;
        this.voyageurCount = 0;
        this.busCount = 0;
    }

    public synchronized Voyageur createVoyageur(int number) {
        Voyageur v = new Voyageur(billeterie, arret, number);
        this.voyageurCount++;
        return v;
    }

    public Map<Integer, Voyageur> getAllVoyageurs() {

        return this.voyageurs;
    }

    public synchronized Map<Integer, Bus> createBus() {
        // Bus b = new Bus(this.arret, number);
        // this.busCount++;

        System.out.println("Création des bus");

        this.arret = new Arret();

        for (int i = 0; i < NB_BUS; i++) {

            bus.put(i, new Bus(this.arret, i));
            bus.get(i).setDaemon(true);
            bus.get(i).start();

            this.busCount++;
        }

        System.out.println("Fin création des bus: " + this.busCount);

        return bus;
    }

}
