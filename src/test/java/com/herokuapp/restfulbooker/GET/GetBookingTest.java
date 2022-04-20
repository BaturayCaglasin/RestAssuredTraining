package com.herokuapp.restfulbooker.GET;

import com.herokuapp.restfulbooker.BaseTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.BitSet;
import java.util.List;

import static io.restassured.RestAssured.*;

public class GetBookingTest extends BaseTest {
    @Test
    public void getBookingTest() {

        //Create Booking
        Response responseCreate = createBooking();
        responseCreate.print();

        //Get bookingId of new booking
        int bookingid = responseCreate.jsonPath().getInt("bookingid");

        //PATH PARAMETER
        //Set path parameter
        spec.pathParam("bookingId", bookingid);

        Response myresponse = RestAssured.given(spec).get("/booking/{bookingId}}");
        myresponse.print();
        Assert.assertEquals(myresponse.getStatusCode(), 200, "Status Code should be 200, but it is not.");

        //Verify New Fields
        SoftAssert softAssert = new SoftAssert();

        String actualFirstName = myresponse.jsonPath().getString("firstname");
        int price = myresponse.jsonPath().getInt("totalprice");

        softAssert.assertEquals(actualFirstName, "Jim");
        softAssert.assertEquals(price, 972);

    }

}
