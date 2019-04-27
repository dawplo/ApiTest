package pl;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class nbp {

    @Test
    public void checkCoursNbp(){

        given().log().all().when().get("http://onet.pl").then().statusCode(200);


        //rest
        //json
//        miktoserwisy
        //react  co to jest
        //node.js co to jest
        // jak tetowac backend  restassured
//testy w nazwie   java 1.8 junit bdd  cos jak bdd
        //kkody odpowiedzi.
        //pageobjectpattern - jedna strona jesdna klasa w tescie.
        //protokol http
        // maven budowanie projektu itp.  do budowania projektu.
        //narzedzie do testowania wydajnosci frontendu lighthouse.  poczytac.!!!! testowanie frontendu.
        //jmeteh backend
        // docker ---- co to jest. poczytac.
        //


    }
}
