package fr.istic.m1.csr.ressources;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.istic.m1.csr.backend.Backend;
import fr.istic.m1.csr.internals.Billeterie;

public class ReseauResource extends ServerResource {

    final static Logger log = LoggerFactory.getLogger(ReseauResource.class);

    /** Backend. */
    private Backend backend_;

    public ReseauResource() {
        backend_ = (Backend) getApplication().getContext().getAttributes().get("backend");
    }

    @Get("json")
    public Representation startBus() throws JSONException {

        JSONObject res = new JSONObject();
        res.put("number", Billeterie.getNbrBilletVendu());

        return new JsonRepresentation(res);
    }
}
