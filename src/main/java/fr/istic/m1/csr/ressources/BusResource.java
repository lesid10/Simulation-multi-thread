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
import org.restlet.resource.ServerResource;

import fr.istic.m1.csr.backend.Backend;
import fr.istic.m1.csr.internals.Bus;

/**
 * Classe ressource pour la manipulation des bus
 */
public class BusResource extends ServerResource {

    /** Backend. */
    private Backend backend_;

    public BusResource() {
        backend_ = (Backend) getApplication().getContext().getAttributes().get("backend");
    }

    /**
     * Donne le statut d'un bus
     * @param representation corps de la requête
     * @return le statut dudit bus
     * @throws JSONException
     */
    @Get("json")
    public Representation getBusState(JsonRepresentation representation) throws JSONException {
        String busId = (String) getRequest().getAttributes().get("busId");

        Bus b = this.backend_.getDatabase().getBus(Integer.parseInt(busId));

        JSONObject vObject = new JSONObject();
        vObject.put("id", b.getNb());
        vObject.put("etat", b.getEtat());

        return new JsonRepresentation(vObject);
    }

}
