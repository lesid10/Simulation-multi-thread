package fr.istic.m1.csr.database;

import java.util.HashMap;
import java.util.Map;

import org.restlet.data.Status;

import fr.istic.m1.csr.internals.Arret;
import fr.istic.m1.csr.internals.Billeterie;
import fr.istic.m1.csr.internals.Bus;
import fr.istic.m1.csr.internals.Voyageur;
import fr.istic.m1.csr.utils.VoyageurException;

/**
 *
 * In-memory database
 *
 * @author ctedeschi
 * @author msimonin
 *
 */
public class InMemoryDatabase {

    static int NB_BUS = 15;

    private Arret arret;

    private Billeterie billeterie;

    /** User Hashmap. */
    private Map<String, Voyageur> voyageurs;

    /** Tweet Hashmap. */
    private Map<Integer, Bus> bus;

    // private Bus[] bus = new Bus[NB_BUS];

    private Integer voyageurCount;
    private Integer busCount;

    public InMemoryDatabase(Billeterie billeterie, Arret arret) {
        this.voyageurs = new HashMap<String, Voyageur>();
        this.bus = new HashMap<Integer, Bus>();
        this.billeterie = billeterie;
        this.arret = arret;
        this.voyageurCount = 0;
        this.busCount = 0;
    }

    public synchronized Voyageur createVoyageur(int number) {
        // if (number <= this.voyageurCount) {
        // throw new VoyageurException("Ce voyageur existe déjà", null);
        // }
        Voyageur v = new Voyageur(billeterie, arret, number);
        this.voyageurs.put(String.valueOf(number), v);
        this.voyageurCount++;
        v.setPriority(Thread.MAX_PRIORITY);

        v.start();

        System.out.println("Voyageur " + number + " crée");
        return v;
    }

    public Voyageur getVoyageur(String id) {
        return this.voyageurs.get(id);
    }

    public Map<String, Voyageur> getAllVoyageurs() {

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
            // TODO à commenter
            bus.get(i).setPriority(Thread.MIN_PRIORITY);
            bus.get(i).start();

            this.busCount++;
        }

        System.out.println("Fin création des bus: " + this.busCount);

        return bus;
    }

}
