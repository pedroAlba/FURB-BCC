package com.resources;

import com.service.CPFService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("cpf")
public class CPFResource {

    @Inject
    private CPFService cpfService;

    @GET
    @Path("{cpf}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response validate(@PathParam("cpf") String cpf){
        return Response
                    .status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity(cpfService.isValid(cpf) ? "Valido" : "Invalido")
                    .build();
    }
}
