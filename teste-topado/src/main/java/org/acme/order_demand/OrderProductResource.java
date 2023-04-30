package org.acme.order_demand;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/order/{id}/product")
public class OrderProductResource {
    @Inject
    private OrderProductService orderProductService;


    @GET
    public Response find(@PathParam("id") Long id) {
        try {
            return Response.status(Response.Status.OK).entity(orderProductService.find(id)).build();
        } catch (WebApplicationException err) {
            return Response.status(err.getResponse().getStatus()).entity(err.getMessage()).type(MediaType.TEXT_PLAIN_TYPE).build();
        }
    }
}
