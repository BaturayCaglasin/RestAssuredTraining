package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;


public class BaseTest {
    protected RequestSpecification spec;

    @BeforeMethod
    public void setUp() {
        spec = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();
    }

    protected Response createBooking() {
        //Create JSON Body
        JSONObject body = new JSONObject();
        body.put("firstname", "Baturay");
        body.put("lastname", "Caglasin");
        body.put("totalprice", 150);
        body.put("depositpaid", false);

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", "2022-03-02");
        bookingdates.put("checkout", "2022-03-05");

        body.put("bookingdates", bookingdates);
        body.put("additionalneeds", "Baby crib");

        //Get Response
        Response response = RestAssured.given(spec).contentType(ContentType.JSON).body(body.toString()).post("/booking");
        return response;
    }
}
