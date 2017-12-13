package iosr.facebookapp.stats.sqs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SqsMessage {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @JsonProperty("Message")
    private String message;

    public static SqsMessage fromJSON(String messageJSON) throws IOException {
        return OBJECT_MAPPER.readValue(messageJSON, SqsMessage.class);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
