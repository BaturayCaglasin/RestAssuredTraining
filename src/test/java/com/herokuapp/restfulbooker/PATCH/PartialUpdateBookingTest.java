package com.herokuapp.restfulbooker.PATCH;

import com.herokuapp.restfulbooker.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PartialUpdateBookingTest extends BaseTest {

    @Test
    public void partialUpdateBookingTest() {

        //1.) create response from POST method
        Response createBooking = createBooking();
        createBooking.print();

        //2.) get booking id
        int bookingid = createBooking.jsonPath().getInt("bookingid");
        System.out.println("created booking id is: " + bookingid);


        //3.) update the body for PATCH :

        JSONObject body = new JSONObject();
        body.put("firstname", "Ada");
        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", "2022-03-11");
        body.put("bookingdates", bookingdates);

        //4) Get a response for PATCH
        Response patchedResponse = RestAssured.given(spec).auth().preemptive().basic("admin", "password123").contentType(ContentType.JSON).body(body.toString()).patch("/booking/" + bookingid);
        patchedResponse.print();
        Assert.assertEquals(patchedResponse.getStatusCode(), 200);


    }

}
