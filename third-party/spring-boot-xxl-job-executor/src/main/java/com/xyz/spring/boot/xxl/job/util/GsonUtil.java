package com.xyz.spring.boot.xxl.job.util;

import com.google.gson.*;

import java.util.*;

public class GsonUtil {

    private static final Gson GSON = new GsonBuilder().serializeNulls().create();
    /**
     * 获取JsonObject
     *
     * @param json
     * @return
     */
    private static JsonObject parseJson(String json) {
        JsonParser parser = new JsonParser();
        JsonObject jsonObj = parser.parse(json).getAsJsonObject();
        return jsonObj;
    }

    public static String toJson(Object obj){
        return GSON.toJson(obj);
    }

    public static Map<String,Object> toMap2(String json){
        return GSON.fromJson(json,Map.class);
    }

    /**
     * 根据json字符串返回Map对象
     *
     * @param json
     * @return
     */
    public static Map<String, Object> toMap(String json) {
        return toMap(parseJson(json));
    }

    /**
     * 将JSONObjec对象转换成Map-List集合
     *
     * @param json
     * @return
     */
    private static Map<String, Object> toMap(JsonObject json) {
        Map<String, Object> map = new HashMap<String, Object>();
        Set<Map.Entry<String, JsonElement>> entrySet = json.entrySet();
        for (Iterator<Map.Entry<String, JsonElement>> iter = entrySet.iterator(); iter.hasNext();) {
            Map.Entry<String, JsonElement> entry = iter.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof JsonArray) {
                map.put((String) key, toList((JsonArray) value));
            } else if (value instanceof JsonObject) {
                map.put((String) key, toMap((JsonObject) value));
            } else {
                map.put((String) key, value);
            }
        }
        return map;
    }

    /**
     * 将JSONArray对象转换成List集合
     *
     * @param json
     * @return
     */
    private static List<Object> toList(JsonArray json) {
        List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < json.size(); i++) {
            Object value = json.get(i);
            if (value instanceof JsonArray) {
                list.add(toList((JsonArray) value));
            } else if (value instanceof JsonObject) {
                list.add(toMap((JsonObject) value));
            } else {
                list.add(value);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        String json = "{\"polciy\":\"image/jpeg;image/png\",\"age\":\"55\",\"number\":77}";
        System.out.println(json);
        Map<String,Object> map = GsonUtil.toMap(json);
        System.out.println(map.size());
    }
}
