package com.java.main;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.testng.Assert;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MainClass {

    public static String path;
   static {
       path=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData.json";

    }

    public static String  getJsonPayload() throws Exception {
      return new String(Files.readAllBytes(Paths.get(path)));
    }

    public static int getObjectCount() throws Exception {

        String JsonObject=new String(Files.readAllBytes(Paths.get(path)));
        String idPath="$..id";
        List<String> Ids=JsonPath.read(JsonObject,idPath);
        return Ids.size();
    }

    public boolean validateJsonData(String ExpectedJson,String ActualJson){
        io.restassured.path.json.JsonPath expectedJson=new io.restassured.path.json.JsonPath(ExpectedJson);
        io.restassured.path.json.JsonPath actualJson=new io.restassured.path.json.JsonPath(ActualJson);
        return ExpectedJson.equals(ActualJson);

    }

   public static UserPOJO[] loadJsontoJava() throws Exception {

       ObjectMapper mapper = new ObjectMapper();
       mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
       UserPOJO[] POJO = mapper.readValue(getJsonPayload(), UserPOJO[].class);
       return POJO;

   }

    public static Object getValueOfJsonObject(String Json,String attribute){
        io.restassured.path.json.JsonPath jspath=new io.restassured.path.json.JsonPath(Json);
        return jspath.get(attribute);





    }
}
