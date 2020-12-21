package blanco.restgenerator.common;

import blanco.restgenerator.valueobject.*;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by haino on 2016/09/03.
 */
public class ResponseDeserializer extends StdDeserializer<CommonResponse> {

    private ApiTelegram responseClass = null;

    public ResponseDeserializer(){
        this(null);
    }

    public ResponseDeserializer(Class<?> vc){
        super(vc);
    }

    @Override
    public CommonResponse deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);

        Long time = 0L;
        String result = null;
        ArrayList<ErrorItem> errors = null;
        JsonNode response = null;
        ArrayList<MessageItem> messages = null;

        Iterator<Map.Entry<String, JsonNode>> fieldIte = node.fields();
        while (fieldIte.hasNext()) {
            Map.Entry<String, JsonNode> fieldEntry = fieldIte.next();
            JsonNode value = fieldEntry.getValue();
            if (value != null) {
                if ("time".equalsIgnoreCase(fieldEntry.getKey())) {
                    time = value.asLong();
                } else if ("result".equalsIgnoreCase(fieldEntry.getKey())) {
                    result = value.asText();
                } else if ("errors".equalsIgnoreCase(fieldEntry.getKey())) {
                    errors = this.parseErrors(value);
                } else if ("telegram".equalsIgnoreCase(fieldEntry.getKey())) {
                    response = value;
                } else if ("messages".equalsIgnoreCase(fieldEntry.getKey())) {
                    messages = this.parseMessages(value);
                }
            }
        }

        Util.infoPrintln(LogLevel.LOG_DEBUG,"deserialize");
        CommonResponse cr = new CommonResponse();
        cr.setTime(time);
        cr.setResult(result);
        cr.setErrors(errors);
        cr.setMessages(messages);

        ObjectMapper mapper = new ObjectMapper();

        ApiTelegram responseClassInstance = null;
        if (response != null) {
            responseClassInstance = mapper.convertValue(response, this.responseClass.getClass());
        }

        cr.setTelegram(responseClassInstance);

        return cr;
    }

    private ArrayList<ErrorItem> parseErrors(JsonNode node) {
        ArrayList<ErrorItem> errors = null;

        if (node.isArray()) {
            errors = new ArrayList<>();
            for (JsonNode itemNode : node) {
                ErrorItem errorItem = parseErrorItem(itemNode);
                errors.add(errorItem);
            }
        }

        return errors;
    }

    private ErrorItem parseErrorItem(JsonNode node) {
        ErrorItem errorItem = new ErrorItem();

        String code = "";
        String message = "";
        String messageNumber = "";

        Iterator<Map.Entry<String, JsonNode>> fieldIte = node.fields();
        while (fieldIte.hasNext()) {
            Map.Entry<String, JsonNode> fieldEntry = fieldIte.next();
            JsonNode value = fieldEntry.getValue();
            if (value != null) {
                if ("code".equalsIgnoreCase(fieldEntry.getKey())) {
                    code = value.asText();
                } else if ("message".equalsIgnoreCase(fieldEntry.getKey())) {
                    message = value.asText();
                } else if ("messageNumber".equalsIgnoreCase(fieldEntry.getKey())) {
                    messageNumber = value.asText();
                }
            }
        }

        errorItem.setCode(code);
        errorItem.setMessage(message);
        errorItem.setMessageNumber(messageNumber);

        return errorItem;
    }


    private ArrayList<MessageItem> parseMessages(JsonNode node) {
        ArrayList<MessageItem> messages = null;

        if (node.isArray()) {
            messages = new ArrayList<>();
            for (JsonNode itemNode : node) {
                MessageItem messageItem = parseMessageItem(itemNode);
                messages.add(messageItem);
            }
        }

        return messages;
    }

    private MessageItem parseMessageItem(JsonNode node) {
        MessageItem messageItem = new MessageItem();

        String code = "";
        String message = "";
        String messageNumber = "";

        Iterator<Map.Entry<String, JsonNode>> fieldIte = node.fields();
        while (fieldIte.hasNext()) {
            Map.Entry<String, JsonNode> fieldEntry = fieldIte.next();
            JsonNode value = fieldEntry.getValue();
            if (value != null) {
                if ("code".equalsIgnoreCase(fieldEntry.getKey())) {
                    code = value.asText();
                } else if ("message".equalsIgnoreCase(fieldEntry.getKey())) {
                    message = value.asText();
                } else if ("messageNumber".equalsIgnoreCase(fieldEntry.getKey())) {
                    messageNumber = value.asText();
                }
            }
        }

        messageItem.setCode(code);
        messageItem.setMessage(message);
        messageItem.setMessageNumber(messageNumber);

        return messageItem;
    }

    public ApiTelegram getResponseClass() {
        return responseClass;
    }

    public void setResponseClass(ApiTelegram responseClass) {
        this.responseClass = responseClass;
    }
}

