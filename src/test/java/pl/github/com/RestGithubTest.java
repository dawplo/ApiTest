package pl.github.com;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import objects.ErrorMessageGithub;
import org.fest.assertions.Assertions;
import org.junit.Ignore;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

public class RestGithubTest {

    private String githubProduction = "https://api.github.com";
    private String githubSearchRepositories = "https://api.github.com/repositories/3081286";
    private String searchGitExample = "https://api.github.com/search/repositories?q=tetris+language:assembly&sort=stars&order=desc";
    private String search = "https://api.github.com/search";


    @Test
    public void shouldExecuteGetRequestAndGetResponse() {


        given().log().all()
                .when().get(githubProduction)
                .then().log().all()
                .statusCode(200)
                .body(containsString("current_user_url"));

    }

    @Test
    public void checkErrorMessageGithub() {

        Response response = given().when().get(githubSearchRepositories);
        Response response1 = given().when().head(githubSearchRepositories);

        response.prettyPrint();

        Assertions.assertThat(response.statusCode()).isEqualTo(200);


        Assertions.assertThat(response.jsonPath().getString("node_id")
                .contains("MDEwOlJlcG9zaXRvcnkzMDgxMjg2")); //it work's
        Assertions.assertThat(response1.jsonPath().getString("network_count")
                .contains("2"));
    }

    @Test
    public void checkMapJson() {


        Response response = given().log().all().get(search);

        response.prettyPrint();

        Assertions.assertThat(response.statusCode()).isEqualTo(404);
        Assertions.assertThat(response.jsonPath().getString("message"))
                .contains("Not Found");

    }

    @Ignore
    @Test

    public void CheckProductionGitByMappingResponse() {

        ErrorMessageGithub errorMessageGithub = given().log().all().get(searchGitExample)
                .then().log().all()
                .extract().as(ErrorMessageGithub.class);


        // then
        Assertions.assertThat(errorMessageGithub.getItems().size()).isEqualTo(1);
        Assertions.assertThat(errorMessageGithub.getIncomplete_results().get(0).getAvatar_url()).isEqualTo("NotFoundException");
        Assertions.assertThat(errorMessageGithub.getSearchGitExample().get(0).getEvents_url()).isEqualTo("An error has occurred");
        Assertions.assertThat(errorMessageGithub.getIncomplete_results().get(0).getFull_name()).isEqualTo("Funkcja niedostępna. Skontaktuj się z autorem aplikacji.");

    }


    public static String createCustomer = "https://regres.in/api/users";
    public static String raceUrl = "http://ergast.com/api/f1/2017/circuits.json";

    @Test
    public void countryNamesTest() {
        given()
                .when()
                .get(raceUrl)
                .then()
                .assertThat()
                .statusCode(200);
        Response response = given().when().get(raceUrl);
        String responceValues = response.getBody().asString();
        System.out.println("Response");
        System.out.println(responceValues);

        JsonPath jsonPath = new JsonPath(responceValues);
        String countryName = jsonPath.getString("MRData.CircuitTable.Circuits.location.country[0]");
        System.out.println(countryName);

    }

}



