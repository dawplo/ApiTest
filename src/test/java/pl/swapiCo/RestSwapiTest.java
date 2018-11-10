package pl.swapiCo;

import objects.ErrorsAllegro;
import io.restassured.response.Response;
import org.fest.assertions.Assertions;
import org.junit.Test;
import swapiCo.ObjecSwapi;
import swapiCo.Objects;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

public class RestSwapiTest {

    public String swapiURL = "https://swapi.co/api/people/1/";


    @Test
    public void shouldGetRequestAndGetResponse(){

        given().log().all().when().get(swapiURL).then().log().all().statusCode(200).body(containsString("Luke Skywalker"));
    }



    @Test
    public void shouldCheckErrorMessageByJsonPath() {
        // when
        Response response = given().log().all().get(swapiURL);

        response.prettyPrint();

        // then
        Assertions.assertThat(response.statusCode()).isEqualTo(200);
        Assertions.assertThat(response.jsonPath().getString("films"))
                .contains("https://swapi.co/api/films/2/");
        Assertions.assertThat(response.jsonPath().getString("height")).contains("172");
        Assertions.assertThat(response.jsonPath().getList("films")).contains("https://swapi.co/api/films/2/");
        Assertions.assertThat(response.jsonPath().getList("species")).contains("https://swapi.co/api/species/1/");
        Assertions.assertThat((response.jsonPath().getList("starships")).contains("https://swapi.co/api/starships/12/"));
        Assertions.assertThat(response.jsonPath().getString("created").contains("2014-12-09T13:50:51.644000Z"));
        Assertions.assertThat(response.jsonPath().getString("edited").contains("2014-12-20T21:17:56.891000Z"));
        Assertions.assertThat(response.jsonPath().getString("url").contains("https://swapi.co/api/people/1/"));

    }
@Test
    public void shouldCheckMessageByMappingResponse(){

    ObjecSwapi objecSwapi = given().log().all()
            .get(swapiURL).then().log().all()
            .extract().as(ObjecSwapi.class);

    Assertions.assertThat(objecSwapi.getObject().size()).isEqualTo(1);
    Assertions.assertThat(objecSwapi.getObject().get(0).getGender()).isEqualTo("male");





    }

}
