package fr.istic.m1.csr;

import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Context;
import org.restlet.Request;
import org.restlet.data.Method;
import org.restlet.data.Protocol;
import org.restlet.data.Reference;

import fr.istic.m1.csr.application.GeneralApplication;
import fr.istic.m1.csr.backend.Backend;

/**
 * Main RESTlet minimal example
 *
 */

public final class Main {

    /** Hide constructor. */
    private Main() {
        throw new UnsupportedOperationException();
    }

    /**
     * Main method.
     *
     * @param args The arguments of the commande line
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // Create a component
        Component component = new Component();
        Context context = component.getContext().createChildContext();
        component.getServers().add(Protocol.HTTP, 8124);

        // Create an application
        Application application = new GeneralApplication(context);

        // // Add the backend into component's context
        Backend backend = new Backend();
        context.getAttributes().put("backend", backend);
        component.getDefaultHost().attach(application);
        component.handle(new Request(Method.POST, new Reference("http://localhost:8124/bus/start")));

        // Start the component
        component.start();
    }

}
