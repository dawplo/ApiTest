package pl.google;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RestGoogleTest {

    private String googleSite = "https://google.pl";

    @Test
    public void makeSureThatGoogleIsUp() {
//        given().when().post("http://www.google.com").then().log().all().statusCode(405);
//        given().when().put("http://www.google.com").then().log().all().statusCode(405);
        given().when().put("https://www.googleapis.com/geolocation/v1/geolocate?key=xxx-xx-xx-xx").then().log().all().statusCode(404);
        System.out.println();


    }

    @Test
    public void testCookies(){


        given().when().get(googleSite).then().log().all()
                .statusCode(200);
    }

}
