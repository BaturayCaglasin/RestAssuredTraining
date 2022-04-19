package com.herokuapp.restfulbooker;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static io.restassured.RestAssured.*;

public class GetBookingTest {
    @Test
    public void getBookingTest() {
        Response myresponse = RestAssured.get("https://restful-booker.herokuapp.com/booking/5");
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
