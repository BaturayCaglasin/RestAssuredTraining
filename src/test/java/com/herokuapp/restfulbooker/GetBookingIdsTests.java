package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
import static io.restassured.RestAssured.*;

//TRADITIONAL WAY
public class GetBookingIdsTests {
    @Test
    public void getBookingIdsWithoutFilterTest() {

        //Get Response with Booking ids
        Response myresponse = RestAssured.get("https://restful-booker.herokuapp.com/booking");
        myresponse.print(); //this will print the response body.

        //Verify response 200
        Assert.assertEquals(myresponse.getStatusCode(), 200, "Status Code should be 200, but it is not.");

        //Verify at least 1 booking id in response
        List<Integer> bookingIds = myresponse.jsonPath().getList("bookingid");
        Assert.assertFalse(bookingIds.isEmpty(), "List of bookingIds is empty, but it shouldn't be like that.");
    }


}
