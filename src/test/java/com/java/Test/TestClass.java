package com.java.Test;

import com.java.main.MainClass;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestClass {

   Response response;
    RequestSpecBuilder requestSpecBuilder;
    RequestSpecification requestSpec;
   @BeforeTest
   public void BuildRequestSpecificBuilder(){

       requestSpecBuilder = new RequestSpecBuilder();
       requestSpecBuilder.setBaseUri(StaticTestData.BaseURL);
       requestSpecBuilder.setContentType(ContentType.JSON);
       requestSpec = requestSpecBuilder.build();
   }


    @Test(priority = 1)
    public void CreatePost() throws Exception {

       response= RestAssured.given()
                  .spec(requestSpec)
                  .when()
                  .body(MainClass.getJsonPayload())
                  .post(StaticTestData.PostEndpoint)
                  .then()
                  .assertThat()
                  .statusCode(200)
                  .extract()
                  .response();

        Assert.assertEquals(response.getBody().asString(),StaticTestData.ResponseBody);
        System.out.println("Response of POST "+response.getBody().asString());


    }

    @Test(priority = 2)
    public void GetDetails(){

        response= RestAssured.given()
                .spec(requestSpec)
                .log().all()
                .when()
                .get("/"+StaticTestData.User)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        System.out.println("Response of GET "+response.getBody().asString());

    }

    @Test(priority = 3)
    public void ValidateGetwithPost() throws Exception {
        int index=StaticTestData.index;
        String ActualJson=response.getBody().asString();
        Assert.assertEquals(MainClass.loadJsontoJava()[index].getId(),MainClass.getValueOfJsonObject(ActualJson,"id"));
        Assert.assertEquals(MainClass.loadJsontoJava()[index].getUsername(),MainClass.getValueOfJsonObject(ActualJson,"username"));
        Assert.assertEquals(MainClass.loadJsontoJava()[index].getFirstName(),MainClass.getValueOfJsonObject(ActualJson,"firstName"));
        Assert.assertEquals(MainClass.loadJsontoJava()[index].getLastName(),MainClass.getValueOfJsonObject(ActualJson,"lastName"));
        Assert.assertEquals(MainClass.loadJsontoJava()[index].getEmail(),MainClass.getValueOfJsonObject(ActualJson,"email"));
        Assert.assertEquals(MainClass.loadJsontoJava()[index].getPassword(),MainClass.getValueOfJsonObject(ActualJson,"password"));
        Assert.assertEquals(MainClass.loadJsontoJava()[index].getPhone(),MainClass.getValueOfJsonObject(ActualJson,"phone"));
        Assert.assertEquals(MainClass.loadJsontoJava()[index].getUserStatus(),MainClass.getValueOfJsonObject(ActualJson,"userStatus"));


    }

}
