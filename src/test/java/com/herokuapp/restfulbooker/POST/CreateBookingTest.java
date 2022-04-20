package com.herokuapp.restfulbooker.POST;

import com.herokuapp.restfulbooker.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateBookingTest extends BaseTest {

    @Test
    public void createBookingTest() {
        Response response = createBooking();
        response.print();
        //Verifications
        Assert.assertEquals(response.getStatusCode(), 200);
    }


}
