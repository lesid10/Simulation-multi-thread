package fr.istic.m1.csr.database;

import java.util.Collection;
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

    static int NB_BUS = 5;

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

    public synchronized Voyageur createVoyageur(String name) {
        Voyageur v = new Voyageur(billeterie, arret, name);
        v.set_id(voyageurCount);
        this.voyageurs.put(voyageurCount, v);
        this.voyageurCount++;
        v.start();

        return v;
    }

    public Voyageur getVoyageur(int id) {
        return this.voyageurs.get(id);
    }

    public Map<Integer, Voyageur> getAllVoyageurs() {
        return this.voyageurs;
    }

    public Bus getBus(int id) {
        return this.bus.get(id);
    }

    public Collection<Bus> getAllBusState() {
        return this.bus.values();
    }

    private synchronized void addNewBus() {
        // System.out.println("Création des bus");
        Bus b = new Bus(this.arret, busCount);
        bus.put(busCount, b);
        b.setDaemon(true);
        // TODO à commenter
        // b.setPriority(Thread.MIN_PRIORITY);
        b.start();
        this.busCount++;
        // System.out.println("Fin creation des bus: " + this.busCount);
    }

    public synchronized void addAllBus() {
        for (int i = 0; i < NB_BUS; i++) {
            addNewBus();
        }
    }

}
