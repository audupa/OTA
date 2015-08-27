package util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.springframework.stereotype.Component;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

/**
 * User: AUDUPA
 * Date: 2/11/15
 * Time: 11:52 AM
 */

@Provider
@Produces(MediaType.APPLICATION_JSON)
@Component

public class JacksonJsonProvider extends JacksonJaxbJsonProvider {

    private static ObjectMapper commonMapper = null;

    public JacksonJsonProvider() {

        if (commonMapper == null) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            mapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            commonMapper = mapper;
        }
        super.setMapper(commonMapper);
    }
}

