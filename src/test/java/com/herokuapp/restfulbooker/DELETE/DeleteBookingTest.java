package com.herokuapp.restfulbooker.DELETE;

import org.testng.annotations.Test;
import com.herokuapp.restfulbooker.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;


public class DeleteBookingTest extends BaseTest {

    @Test
    public void deleteBookingTest() {

        //1.) Create Booking from POST

        Response createBooking = createBooking();
        createBooking.print();

        //2.) Get booking id

        int bookingid = createBooking.jsonPath().getInt("bookingid");
        System.out.println("crated booking id is: " + bookingid);

        //3.) Delete this booking id

        Response deletedResponse = RestAssured.given(spec).auth().preemptive().basic("admin","password123").contentType(ContentType.JSON).delete("/booking/" + bookingid);
        deletedResponse.print();

        Assert.assertEquals(deletedResponse.getStatusCode(), 201);


    }

}
