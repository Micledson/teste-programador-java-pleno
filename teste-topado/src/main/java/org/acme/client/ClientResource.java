package org.acme.client;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/client")
public class ClientResource {
    @Inject
    ClientService clientServer;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {

        System.out.println("eae");
        return Response.status(Response.Status.OK).entity(clientServer.findAll()).build();
    }


}
