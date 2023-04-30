package org.acme.order;

import org.acme.order.dto.request.OrderCreateDto;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
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

    @POST
    @Transactional
    public Response create(OrderCreateDto orderDto) {
        try {
            return Response.status(Response.Status.CREATED).entity(orderService.create(orderDto)).build();
        } catch (WebApplicationException err) {
            return Response.status(err.getResponse().getStatus()).entity(err.getMessage()).type(MediaType.TEXT_PLAIN_TYPE).build();
        }
    }

}
