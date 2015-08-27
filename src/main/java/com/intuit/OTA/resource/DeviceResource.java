package com.intuit.OTA.resource;

import com.intuit.OTA.model.Device;
import com.intuit.OTA.model.User;
import org.elasticsearch.search.facet.FacetExecutor;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created with IntelliJ IDEA.
 * User: audupa
 * Date: 8/27/15
 * Time: 2:27 PM
 * To change this template use File | Settings | File Templates.
 */
@Path("/device")
public interface DeviceResource {

    @POST
    @Consumes({ APPLICATION_JSON })
    @Produces({ APPLICATION_JSON })
    public Device createDevice(Device device) throws Exception;

    @GET
    @Consumes({ APPLICATION_JSON })
    @Produces({ APPLICATION_JSON })
    public java.util.List<Device> getAllDevices() throws Exception;
}
