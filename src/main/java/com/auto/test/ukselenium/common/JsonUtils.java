package com.auto.test.ukselenium.common;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Component
public class JsonUtils {

    public JSONObject readJsonAsObject(String fileName) throws IOException, ParseException {

        JSONParser parser = new JSONParser();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        Object obj = parser.parse(new FileReader(file));
        return (JSONObject) obj;
    }

    public File readJsonAsFile(String fileName) throws IOException, ParseException {

        JSONParser parser = new JSONParser();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        return file;
    }

    public JSONObject readJsonAsObjectByKey(String fileName, String key) throws IOException, ParseException {

        JSONParser parser = new JSONParser();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(file));

        return (JSONObject) jsonObject.get(key);

    }


}
