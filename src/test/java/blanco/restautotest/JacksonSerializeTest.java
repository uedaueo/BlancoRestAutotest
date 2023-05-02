package blanco.restautotest;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JacksonSerializeTest {

    class MyData {
        public List<Long> myArray = new ArrayList<>();
    }

    @Test
    @Disabled
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

    /*
    fieldStructure.setInput30(BlancoXmlBindingUtil.getTextContent(elementList, "Input30"));
    fieldStructure.setExpect30(BlancoXmlBindingUtil.getTextContent(elementList, "Expect30"));
     */
    @Test
    @Disabled
    public void myTest() {
        for (int i = 31; i < 201; i++) {
            System.out.println("fieldStructure.setExpect" + i + "(BlancoXmlBindingUtil.getTextContent(elementList, \"Expect" + i + "\"));");
        }
    }
}
