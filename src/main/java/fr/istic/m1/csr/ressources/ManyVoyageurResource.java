package fr.istic.m1.csr.ressources;

import fr.istic.m1.csr.backend.Backend;
import fr.istic.m1.csr.interfaces.OurResource;
import fr.istic.m1.csr.internals.Voyageur;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ServerResource;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Classe ressource pour la manipulation de plusieurs voyageurs
 */
public class ManyVoyageurResource extends ServerResource implements OurResource<Voyageur> {

    /** Backend. */
    private Backend backend_;

    public ManyVoyageurResource() {
        backend_ = (Backend) getApplication().getContext().getAttributes().get("backend");
    }

    /**
     * Permet de sauvegarder dans la BD et de démarrer plusieurs voyagers d'un coup
     * @param representation le corps de la requete qui est un jsonArray
     * @return L'ensemble des voyageurs ajoutés
     * @throws JSONException
     */
    @Override
    public Representation store(JsonRepresentation representation) throws JSONException {

        JSONObject objects = representation.getJsonObject();
        JSONArray voyageurs = objects.getJSONArray("voyageurs");

        Collection<JSONObject> objectCollection = new ArrayList<>();
        JSONObject msg = new JSONObject().put("Resultat", voyageurs.length() + " voyageurs ajoutés.");
        objectCollection.add(msg);
        JSONObject voyageur_;

        for (int i = 0; i < voyageurs.length(); i++) {
            voyageur_ = voyageurs.getJSONObject(i);
            String nom = voyageur_.getString("name");

            // Sauvegarder chaque voyageur
            this.backend_.getDatabase().createVoyageur(nom);
            objectCollection.add(voyageur_);
        }

        // generate result
        JSONArray output = new JSONArray(objectCollection);
        return new JsonRepresentation(output);
    }

}
