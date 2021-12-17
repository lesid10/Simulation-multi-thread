package fr.istic.m1.csr.interfaces;

import org.json.JSONException;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;

public interface OurResource<T> {

    @Get
    public default Representation retrieve(JsonRepresentation representation) throws JSONException {
        return null;
    };

    @Post
    public default Representation store(JsonRepresentation representation) throws JSONException {
        return null;
    };

    @Put
    public default Representation replace(JsonRepresentation representation) throws JSONException {
        return null;
    };

    @Delete
    public default Representation remove(JsonRepresentation representation) throws JSONException {
        return null;
    };
}
