package util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: AUDUPA
 * Date: 2/10/15
 * Time: 11:59 PM
 */
public class JSONSerializer {

    private static final Logger logger = LoggerFactory.getLogger(JSONSerializer.class);
    static ObjectMapper mapper = null;

    static {
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static String generateJSON( Object object) {
        String json = null;

        try {
            json = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error("JSONProcessing: ", e);
        }
        return json;
    }
}
