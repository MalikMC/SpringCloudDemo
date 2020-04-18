package com.machun.orderservice.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @Description:
 * @Author: machun
 * @CreateDate: 2020/4/13 13:40
 * @UpdateDate: 2020/4/13 13:40
 * @menu
 */
public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static JsonNode str2JsonNode(String str) {
        try {
            return objectMapper.readTree(str);
        } catch (IOException var2) {
            return null;
        }
    }
}
