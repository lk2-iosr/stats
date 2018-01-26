package iosr.facebookapp.stats.logging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class Log {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String getLog(TimeLog timeLog) throws JsonProcessingException{
        Map<String, Object> map = new HashMap<>();
        map.put(timeLog.getEventType(), timeLog.getTime());

        return OBJECT_MAPPER.writeValueAsString(map);
    }
}
