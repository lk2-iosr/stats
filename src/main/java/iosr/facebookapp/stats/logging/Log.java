package iosr.facebookapp.stats.logging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Log {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String getLog(Object logObject) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(logObject);
    }
}
