package pl.allegrosandbox;

import io.restassured.response.Response;
import objects.ErrorMessageAllegro;
import org.fest.assertions.Assertions;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.containsString;

public class RestAssuredTest {

    private String allegroSandboxEndpoint = "https://api.allegro.pl.allegrosandbox.pl";
    private String jsonapitest = "http://ip.jsontest.com/";

    private String jsonText = "fbdtbdffdbfdfdbfdb";

    @Test
    public void shouldExecuteGetRequestAndGetResponse() {
        given().log().all() //wyswietla requesta
                .when().get(allegroSandboxEndpoint)
                .then().log().all()
                .statusCode(404)
                .body(containsString("Contact the author of the application."));
        //wystarczy tylko ten test nie trzeba  tworzyć obiektów.
    }

    @Test  //2
    public void shouldCheckErrorMessageByJsonPath() {
        // when
        Response response = given().log().all().get(allegroSandboxEndpoint);

        response.prettyPrint();

        // then
        Assertions.assertThat(response.statusCode()).isEqualTo(404);
        Assertions.assertThat(response.jsonPath().getList("errors.code"))
                .contains("NotFoundException");
        Assertions.assertThat(response.jsonPath().getList("errors.message")).contains("Not found");
        Assertions.assertThat(response.jsonPath().getList("errors.userMessage")).contains("Function is not available. Contact the author of the application.");
    }

    @Test
    public void shouldCheckErrorMessageByMappingResponse() {
        // when
        ErrorMessageAllegro errorMessage = given()
                .log().all()
                .get(allegroSandboxEndpoint)
                .then().log().all()
                .extract().as(ErrorMessageAllegro.class);

        // then
        Assertions.assertThat(errorMessage.getErrors().size()).isEqualTo(1);
        Assertions.assertThat(errorMessage.getErrors().get(0).getCode()).isEqualTo("NotFoundException");
        Assertions.assertThat(errorMessage.getErrors().get(0).getMessage()).isEqualTo("Not found");
        Assertions.assertThat(errorMessage.getErrors().get(0).getUserMessage()).isEqualTo("Function is not available. Contact the author of the application.");
    }

    @Test
    public void RestAssuredLotto(){

        when().get(jsonapitest) .then().log().all().statusCode(200);
        when().post(jsonapitest).then().log().all().statusCode(200);

          Response response = given().log().all().get(jsonapitest);

                response.prettyPrint();

                Assertions.assertThat(response.jsonPath().getString("ip").contains("89.70.180.111"));   //nierobi


    }}
