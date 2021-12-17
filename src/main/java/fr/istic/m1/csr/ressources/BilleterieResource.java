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

/**
 * Classe ressource pour la manipulation de la billeterie
 */
public class BilleterieResource extends ServerResource {

    final static Logger log = LoggerFactory.getLogger(BilleterieResource.class);

    /** Backend. */
    private Backend backend_;

    public BilleterieResource() {
        backend_ = (Backend) getApplication().getContext().getAttributes().get("backend");
    }

    /**
     * Retourne le nombre de billet acheté
     * @return Nombre de billet acheté
     * @throws JSONException
     */
    @Get("json")
    public Representation getNumberOfTicket() throws JSONException {

        JSONObject res = new JSONObject();
        res.put("number", Billeterie.getNbrBilletVendu());

        return new JsonRepresentation(res);
    }
}
