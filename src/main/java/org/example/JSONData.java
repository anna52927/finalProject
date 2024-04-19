//Chat GPT generated -- Modified by Felix
package org.example;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JSONData {

    public static Map<String,Object> JSONImport(String filename) {
        System.out.println(filename);
        File jsonFile = new File("yaleImportantMetrics.json"); // Replace with the path to your JSON file
        Map<String, Object> map = new HashMap<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonFile);
            map = convertJsonToMap(rootNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    private static Map<String, Object> convertJsonToMap(JsonNode node) {
        Map<String, Object> map = new HashMap<>();

        if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode) node;
            Iterator<Map.Entry<String, JsonNode>> fields = objectNode.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                map.put(entry.getKey(), convertJsonToMap(entry.getValue()));
            }
        } else if (node.isArray()) {
            ArrayNode arrayNode = (ArrayNode) node;
            for (int i = 0; i < arrayNode.size(); i++) {
                map.put(String.valueOf(i), convertJsonToMap(arrayNode.get(i)));
            }
        } else if (node.isValueNode()) {
            if (node.isTextual())
                map.put("value", node.textValue());
            else if (node.isBoolean())
                map.put("value", node.booleanValue());
            else if (node.isNumber()) {
                if (node.isInt())
                    map.put("value", node.intValue());
                else if (node.isDouble())
                    map.put("value", node.doubleValue());
                else if (node.isFloat())
                    map.put("value", node.floatValue());
                else if (node.isLong())
                    map.put("value", node.longValue());
            }
        }
        return map;
    }
}