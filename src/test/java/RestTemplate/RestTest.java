package RestTemplate;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RestTest {

//    RestTemplate restTemplate = new RestTemplate():

//github

    private String endpoint = "https://api.github.com/users/kbl";
    private String followers = "https://api.github.com/users/kbl/followers\"";

    @Test

            public void makeSureThatGithubWorks(){

        given().when().get(endpoint).then().log().all().statusCode(200);
        given().when().get(followers).then().log().all().statusCode(404);

        System.out.println();

    }


}
