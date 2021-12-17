package fr.istic.m1.csr.backend;

import fr.istic.m1.csr.database.InMemoryDatabase;
import fr.istic.m1.csr.internals.Arret;
import fr.istic.m1.csr.internals.Billeterie;

/**
 *
 * Backend for all resources.
 * 
 */
public class Backend {

    /** Database. */
    private InMemoryDatabase database_;

    public Backend() {
        database_ = new InMemoryDatabase(new Billeterie(), new Arret());
    }

    public InMemoryDatabase getDatabase() {
        return database_;
    }
}
