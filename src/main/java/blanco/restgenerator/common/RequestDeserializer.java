package blanco.restgenerator.common;

import blanco.restgenerator.valueobject.ApiRequestHeader;
import blanco.restgenerator.valueobject.ApiTelegram;
import blanco.restgenerator.valueobject.CommonRequest;
import blanco.restgenerator.valueobject.RequestHeader;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by haino on 2016/08/20.
 */
public class RequestDeserializer extends StdDeserializer<CommonRequest>{

    private ApiTelegram requestClass = null;

    public RequestDeserializer(){
        this(null);
    }

    public RequestDeserializer(Class<?> vc){
        super(vc);
    }

    @Override
    public CommonRequest deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);

        RequestHeader info = null;
        JsonNode request = null;

        Iterator<Map.Entry<String,JsonNode>> fieldIte = node.fields();
        while(fieldIte.hasNext()){
            Map.Entry<String, JsonNode>fieldEntry = fieldIte.next();
            Util.infoPrintln(LogLevel.LOG_DEBUG, "RequestDeserializer: key = " + fieldEntry.getKey());
            JsonNode value = fieldEntry.getValue();
            if(value != null){
                if("info".equalsIgnoreCase(fieldEntry.getKey())){
                    info = this.parseRequestHeaser(value);
                }else if ("telegram".equalsIgnoreCase(fieldEntry.getKey())){
                    request = value;
                }
            }
        }


        Util.infoPrintln(LogLevel.LOG_DEBUG,"deserialize");
        CommonRequest cr = new CommonRequest();
        cr.setInfo(info);
        ObjectMapper mapper = new ObjectMapper();

        ApiTelegram requestClassInstance = null;
        if (request != null){
            requestClassInstance = mapper.convertValue(request, this.requestClass.getClass());
        }

        cr.setTelegram(requestClassInstance);

      return cr;
    }

    private RequestHeader parseRequestHeaser(JsonNode node) {
        ApiRequestHeader header = new ApiRequestHeader();

        String token = null;
        String lang = null;


        Iterator<Map.Entry<String,JsonNode>> fieldIte = node.fields();
        while(fieldIte.hasNext()){
            Map.Entry<String, JsonNode>fieldEntry = fieldIte.next();
            JsonNode value = fieldEntry.getValue();
            if(value != null){
                if("token".equalsIgnoreCase(fieldEntry.getKey())){
                    token = value.asText();
                }else if ("lang".equalsIgnoreCase(fieldEntry.getKey())){
                    lang = value.asText();
                }
            }
        }

        header.setToken(token);
//        header.setLang(lang);

        return header;
    }

    public ApiTelegram getRequestClass() {
        return requestClass;
    }

    public void setRequestClass(ApiTelegram requestClass) {
        this.requestClass = requestClass;
    }
}
