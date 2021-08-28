package com.charlotte.cblog.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JackSonUtil {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();


    public static String objectToJson(Object data)  {
        try {
            return MAPPER.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) throws JsonProcessingException {
        return MAPPER.readValue(jsonData, beanType);
    }


    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) throws JsonProcessingException {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        return MAPPER.readValue(jsonData, javaType);
    }

}
