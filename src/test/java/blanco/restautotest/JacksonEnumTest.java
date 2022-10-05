package blanco.restautotest;

import blanco.sample.EnumStatus;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class JacksonEnumTest {

    @Test
    public void testEnumStatus() {
        System.out.println(EnumStatus.valueOf("State01"));
        System.out.println(EnumStatus.State01.getState());
        System.out.println(EnumStatus.class.isEnum());
        Class<?> klass = EnumStatus.class;
        System.out.println("klass = " + klass.getSimpleName());
        // Search Getters
        List<Method> myGetters = new ArrayList<>();
        for (Method method : klass.getDeclaredMethods()) {
            if (method.getName().startsWith("get")) {
                System.out.println("found:" + method.getName());
                myGetters.add(method);
            }
        }
    }
}
