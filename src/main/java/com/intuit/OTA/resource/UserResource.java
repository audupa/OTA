package com.intuit.OTA.resource;


import com.intuit.OTA.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * User: AUDUPA
 * Date: 2/10/15
 * Time: 10:44 PM
 */

@Path("/user")

public interface UserResource {


    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=utf-8"})
    public User getUserDetails(@PathParam("id") Long id) throws Exception;
}
