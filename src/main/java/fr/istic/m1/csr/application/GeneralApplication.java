package fr.istic.m1.csr.application;

import fr.istic.m1.csr.ressources.ManyVoyageurResource;
import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import fr.istic.m1.csr.ressources.BilleterieResource;
import fr.istic.m1.csr.ressources.BusResource;
import fr.istic.m1.csr.ressources.ManyBusResource;
import fr.istic.m1.csr.ressources.VoyageurResource;

/**
 * C'est le point d'entrée de toutes les requêtes
 */
public class GeneralApplication extends Application {

    public GeneralApplication(Context context) {
        super(context);
    }

    // @Override
    // public synchronized void start() throws Exception {
    // super.start();
    // System.out.println("Demarrage de l'app");
    // }

    @Override
    public Restlet createInboundRoot() {
        Router router = new Router(getContext());
        router.attach("/bus/state/{busId}", BusResource.class);
        router.attach("/bus/all/state", ManyBusResource.class);

        router.attach("/voyageur/add", VoyageurResource.class);
        router.attach("/voyageur/addAll", ManyVoyageurResource.class);
        router.attach("/voyageur/state/{voyageurId}", VoyageurResource.class);

        router.attach("/billeterie/tickets", BilleterieResource.class);

        return router;
    }

}
