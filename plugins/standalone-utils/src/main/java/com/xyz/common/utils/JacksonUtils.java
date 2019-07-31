package com.xyz.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JacksonUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    public static <T> List<T> toList(String json, Class<T> clazz) {
        return (List) toCollection(json, List.class, clazz);
    }

    public static <T> LinkedList<T> toLinkedList(String json, Class<T> clazz) {
        return (LinkedList) toCollection(json, LinkedList.class, clazz);
    }

    public static <T> Set<T> toSet(String json, Class<T> clazz) {
        return (Set) toCollection(json, Set.class, clazz);
    }

    public static <K, V> Map<K, V> toMap(String json, Class<K> keyClazz, Class<V> valueClazz) {
        return (Map) toCollection(json, Map.class, keyClazz, valueClazz);
    }

    private static Object toCollection(String json, Class<?> parametrized, Class... parameterClasses) {
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructParametricType(parametrized, parameterClasses));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T toJavaBean(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JsonNode toJsonNode(String json) {
        try {
            return mapper.readTree(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapper.valueToTree(json);
    }
}
