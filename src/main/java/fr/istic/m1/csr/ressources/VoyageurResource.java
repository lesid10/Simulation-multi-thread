package fr.istic.m1.csr.ressources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import fr.istic.m1.csr.backend.Backend;
import fr.istic.m1.csr.interfaces.OurResource;
import fr.istic.m1.csr.internals.Voyageur;

/**
 * Classe ressource pour la manipulation de voyageurs
 */
public class VoyageurResource extends ServerResource implements OurResource<Voyageur> {

    /** Backend. */
    private Backend backend_;

    public VoyageurResource() {
        backend_ = (Backend) getApplication().getContext().getAttributes().get("backend");
    }

    /**
     * Permet de retrouver l'etat d'un voyageur à partir de son id
     * @param representation id
     * @return un json comportant le nom et l'etat du voyageur
     * @throws JSONException
     */
    @Override
    public Representation retrieve(JsonRepresentation representation) throws JSONException {

        String voyageurId = (String) getRequest().getAttributes().get("voyageurId");

        System.out.println("Voyageur store: " + voyageurId);

        Voyageur v = this.backend_.getDatabase().getVoyageur(Integer.parseInt(voyageurId));

        System.out.println("Voyageur: " + v);

        JSONObject vObject = new JSONObject();
        vObject.put("Nom", v.getVoyageurName());
        vObject.put("etat", v.getEtat());

        return new JsonRepresentation(vObject);
    }

    /**
     * Permet d'enregistrer un voyageur dans la Bd et le demarrer
     * @param representation corps de la requête
     * @return un status
     * @throws JSONException
     */
    @Override
    public Representation store(JsonRepresentation representation) throws JSONException {

        JSONObject object = representation.getJsonObject();
        String nom = object.getString("name");

        // System.out.println("Voyageur store: " + voyageurId);

        this.backend_.getDatabase().createVoyageur(nom);

        JSONObject res = new JSONObject();
        res.put("add", true);

        return new JsonRepresentation(res);
    }

}
