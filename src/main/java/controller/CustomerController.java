package controller;

import dto.CustomerDTO;
import service.CustomerService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerController {

    @Inject
    CustomerService customerService;

    @GET
    public List<CustomerDTO> findAll() {
        return this.customerService.findAllCustomers();
    }

    @GET
    @Path("/{id}")
    public CustomerDTO findByID(@PathParam(value = "id") Long id) {
        return this.customerService.findByID(id);
    }

    @POST
    @Transactional
    public Response create(CustomerDTO customerDTO) {

        try {
            this.customerService.createCustomer(customerDTO);
            return Response.ok().build();
        } catch (Exception exception) {
            exception.printStackTrace();
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam(value = "id") Long id) {
        try {
            this.customerService.updateCustomer(id);
            return Response.ok().build();
        } catch (RuntimeException exception) {
            exception.printStackTrace();
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam(value = "id") Long id) {
        try {
            this.customerService.delete(id);
            return Response.ok().build();
        } catch (Exception exception) {
            exception.printStackTrace();
            return Response.serverError().build();
        }
    }
}