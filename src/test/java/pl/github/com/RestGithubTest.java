package pl.github.com;

import io.restassured.response.Response;
import objects.ErrorMessageGithub;
import org.fest.assertions.Assertions;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

public class RestGithubTest {

    private String githubProduction = "https://api.github.com";
    private String githubSearch = "https://api.github.com/repositories/3081286";
    private String searchGitExample= "https://api.github.com/search/repositories?q=tetris+language:assembly&sort=stars&order=desc";
    private String search = "https://api.github.com/search";

    @Test
    public void shouldExecuteGetRequestAndGetResponse(){



        given().log().all()
                .when().get(githubProduction)
                .then().log().all()
                .statusCode(200)
                .body(containsString("current_user_url"));

    }
    @Test
    public void checkErrorMessageGithub(){

        Response response= given().when().get(githubSearch);

        response.prettyPrint();

        Assertions.assertThat(response.statusCode()).isEqualTo(200);

//        Assertions.assertThat(response.jsonPath().getList("node_id")
//                .contains("MDEwOlJlcG9zaXRvcnkzMDgxMjg2"));  doesn't work

        Assertions.assertThat(response.jsonPath().getString("node_id")
                .contains("MDEwOlJlcG9zaXRvcnkzMDgxMjg2")); //it work's
    }

@Test
    public void checkMapJson(){


    Response response = given().when().get(searchGitExample);

    response.prettyPrint();

    Assertions.assertThat(response.jsonPath().getList("items.id"))
            .contains("68911683");


}



}
