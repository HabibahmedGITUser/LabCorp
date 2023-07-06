package com.java.Test;

import com.java.main.MainClass;

public class StaticTestData {

    static int Jsonindex=2;  //This is the index to select the json Object from Json Array of TestData.json
    public static   String BaseURL="https://petstore.swagger.io/v2/user";
    public static String path=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData.json";

    public static String PostEndpoint="/createWithArray";

    public static String ResponseBody="{\"code\":200,\"type\":\"unknown\",\"message\":\"ok\"}";

    public static String User;

    static {
        try {
            User = MainClass.loadJsontoJava()[Jsonindex].getUsername();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static int index=Jsonindex;




}
