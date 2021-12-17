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
import fr.istic.m1.csr.internals.Bus;

public class BusResource extends ServerResource {

    /** Backend. */
    private Backend backend_;

    public BusResource() {
        backend_ = (Backend) getApplication().getContext().getAttributes().get("backend");
    }

    @Get("json")
    public Representation getBusState(JsonRepresentation representation) throws JSONException {
        String userId = (String) getRequest().getAttributes().get("userId");
        Collection<JSONObject> jsonTweets = new ArrayList<JSONObject>();

        JSONArray jsonArray = new JSONArray(jsonTweets);

        return new JsonRepresentation(jsonArray);
    }

    @Post("json")
    public Representation startBus() throws JSONException {

        // Collection<JSONObject> jsonBus = new ArrayList<JSONObject>();

        // JSONArray jsonArray = new JSONArray(jsonBus);

        backend_.getDatabase().createBus();

        return new JsonRepresentation(new JSONObject());
    }

}
