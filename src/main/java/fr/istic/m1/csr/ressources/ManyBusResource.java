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

public class ManyBusResource extends ServerResource {

    /** Backend. */
    private Backend backend_;

    public ManyBusResource() {
        backend_ = (Backend) getApplication().getContext().getAttributes().get("backend");
    }

    @Get("json")
    public Representation getAllBusState(JsonRepresentation representation) throws JSONException {

        Collection<JSONObject> busStates = new ArrayList<JSONObject>();

        Collection<Bus> allBusState = backend_.getDatabase().getAllBusState();

        for (Bus b : allBusState) {
            JSONObject current = new JSONObject();
            current.put("id", b.getNb());
            current.put("etat", b.getEtat());
            busStates.add(current);
        }
        JSONArray jsonArray = new JSONArray(busStates);

        return new JsonRepresentation(jsonArray);
    }

}
