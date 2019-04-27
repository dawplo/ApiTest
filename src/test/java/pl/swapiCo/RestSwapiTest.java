package pl.swapiCo;

import io.restassured.response.Response;
import org.fest.assertions.Assertions;
import org.junit.Test;
import swapiCo.People;

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

    People people = given().log().all()
            .get(swapiURL).then().log().all()
            .extract().as(People.class);

    Assertions.assertThat(people.getStarships().size()).isEqualTo(2); //sprawdza czy ilość starships jest 2.

    Assertions.assertThat(people.getFilms().size()).isEqualTo(5);  //sprawdza czy ilość filmów jest 5
    Assertions.assertThat(people.getFilms().get(4)).isEqualTo("https://swapi.co/api/films/7/");
    Assertions.assertThat(people.getFilms().get(0))
            .isEqualTo("https://swapi.co/api/films/2/"); //dlatego ze liczymy od zera

    Assertions.assertThat(people.getName()).isEqualTo("Luke Skywalker");
    Assertions.assertThat(people.getEye_color()).isEqualTo("blue");
//    Assertions.assertThat(people.getFilms()).containsOnly("https://swapi.co/api/films/2/"); //wywali sie bo oczekuje ze jest 1 film
    Assertions.assertThat(people.getUrl()).contains("https://swapi.co/api/people/1/");
    Assertions.assertThat(people.getStarships().get(1)).endsWith("starships/22/"); //czy drugi statek ma koniec 22 - liczymy od zera.

    }

}
