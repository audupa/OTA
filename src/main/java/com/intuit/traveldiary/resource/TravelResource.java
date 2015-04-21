package com.intuit.traveldiary.resource;


import com.intuit.traveldiary.model.Travel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * User: AUDUPA
 * Date: 2/10/15
 * Time: 10:44 PM
 */

@Path("/travel")

public interface TravelResource {

    @POST
    @Produces({ MediaType.APPLICATION_JSON + ";charset=utf-8"})
    public Travel postTravelDetails(Travel travel) throws Exception;


    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=utf-8"})
    public Travel getTravelDetails(@PathParam("id") Long id) throws Exception;
}
