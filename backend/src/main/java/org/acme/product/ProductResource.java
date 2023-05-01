package org.acme.product;

import org.acme.product.dto.request.ProductCreateDto;
import org.acme.product.dto.request.ProductUpdateDto;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/product")
public class ProductResource {
    @Inject
    ProductService productService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.status(Response.Status.OK).entity(productService.findAll()).build();
    }

    @POST
    @Transactional
    public Response create(ProductCreateDto productDto) {
        try {
            productService.create(productDto);
            return Response.status(Response.Status.CREATED).entity(productDto).build();
        } catch (WebApplicationException err) {
            return Response.status(err.getResponse().getStatus()).entity(err.getMessage()).type(MediaType.TEXT_PLAIN_TYPE).build();
        }
    }

    @Path("/{id}")
    @PUT
    @Transactional
    public Response update(ProductUpdateDto productDto, @PathParam("id") Long id) {
        try {
            return Response.status(Response.Status.NO_CONTENT).entity(productService.update(productDto, id)).build();
        } catch (WebApplicationException err) {
            return Response.status(err.getResponse().getStatus()).entity(err.getMessage()).type(MediaType.TEXT_PLAIN_TYPE).build();
        }
    }

    @Path("/{id}")
    @DELETE
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        if (productService.delete(id)) return Response.status(Response.Status.NO_CONTENT).build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
