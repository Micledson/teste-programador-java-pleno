package org.acme.order;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/order")
public class OrderResouce {
    @Inject
    private OrderService orderService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.status(Response.Status.OK).entity(orderService.findAll()).build();
    }

}
