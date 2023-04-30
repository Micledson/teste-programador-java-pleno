package org.acme.client;

import org.acme.client.dto.request.ClientCreateRequestDto;
import org.acme.client.dto.request.ClientUpdateRequestDto;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/client")
public class ClientResource {
    @Inject
    ClientService clientServer;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {

        return Response.status(Response.Status.OK).entity(clientServer.findAll()).build();
    }

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(ClientCreateRequestDto clientDto) {
        System.out.println(clientDto + "");
        try {
            return Response.status(Response.Status.CREATED).entity(clientServer.create(clientDto)).build();
        } catch (WebApplicationException err) {
            return Response.status(err.getResponse().getStatus()).entity(err.getMessage()).type(MediaType.TEXT_PLAIN_TYPE).build();
        }
    }

    @Path("/{id}")
    @PUT
    @Transactional
    public Response update(ClientUpdateRequestDto clientDto, @PathParam("id") Long id) {
        try {
            return Response.status(Response.Status.OK).entity(clientServer.update(clientDto, id)).build();
        } catch (WebApplicationException err) {
            return Response.status(err.getResponse().getStatus()).entity(err.getMessage()).type(MediaType.TEXT_PLAIN_TYPE).build();
        }
    }


}
