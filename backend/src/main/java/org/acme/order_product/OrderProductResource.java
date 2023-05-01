package org.acme.order_product;

import org.acme.order_product.dto.request.OrderProductCreateDto;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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

    @POST
    @Transactional
    public Response create(@PathParam("id") Long id, List<OrderProductCreateDto> orderProductDto) {
        try {
            return Response.ok(orderProductService.create(orderProductDto, id)).build();
        } catch (WebApplicationException err) {
            return Response.status(err.getResponse().getStatus()).entity(err.getMessage()).type(MediaType.TEXT_PLAIN_TYPE).build();
        } catch (Exception error) {
            throw error;
        }
    }
}
