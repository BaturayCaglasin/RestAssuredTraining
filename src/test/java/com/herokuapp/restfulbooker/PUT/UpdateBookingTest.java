package com.herokuapp.restfulbooker.PUT;


import com.herokuapp.restfulbooker.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;


public class UpdateBookingTest extends BaseTest {
    @Test
    public void updateBookingTest() {
        //step 1: create booking, get response from POST

        Response responseCreate = createBooking(); //From BaseTest -> Post
        responseCreate.print();

        //step 2: get bookingId of new booking
        int bookingid = responseCreate.jsonPath().getInt("bookingid");
        System.out.println("created booking id is:" + bookingid);

        //step 3: update booking

        JSONObject body = new JSONObject();
        body.put("firstname", "Doga");
        body.put("lastname", "Caglasin");
        body.put("totalprice", 222);
        body.put("depositpaid", true);

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", "2022-03-02");
        bookingdates.put("checkout", "2022-03-05");

        body.put("bookingdates", bookingdates);
        body.put("additionalneeds", "Shower Towel");

        //Get Response from PUT
        Response updatedResponse = RestAssured.given(spec).auth().preemptive().basic("admin", "password123").contentType(ContentType.JSON).body(body.toString()).put("/booking/" + bookingid);
        updatedResponse.print();
        Assert.assertEquals(updatedResponse.getStatusCode(), 200);
    }
}
