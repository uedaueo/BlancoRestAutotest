package blanco.restautotest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class JacksonSerializeTest {

    class MyData {
        public List<Long> myArray = new ArrayList<>();
    }

    @Test
    @Ignore
    public void serial() {
        MyData myData = new MyData();
        myData.myArray.add(10L);
        myData.myArray.add(20L);
        myData.myArray.add(30L);

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
        printer.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
        String json = null;
        try {
            json = mapper.writer(printer).writeValueAsString(myData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(json);
    }
}
