package fr.istic.m1.csr.utils;

import org.restlet.resource.Status;

@Status(value = 409, serialize = true)
public class VoyageurException extends RuntimeException {
    public VoyageurException(String message, Exception e) {
        super(message, e);
    }
}
