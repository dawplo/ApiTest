package pl.allegrosandbox;

import io.restassured.response.Response;
import objects.ErrorMessageAllegro;
import org.fest.assertions.Assertions;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

public class RestAssuredTest {

    private String allegroSandboxEndpoint = "https://api.allegro.pl.allegrosandbox.pl";

    private String jsonText = "fbdtbdffdbfdfdbfdb";

    @Test
    public void shouldExecuteGetRequestAndGetResponse() {
        given().log().all() //wyswietla requesta
                .when().get(allegroSandboxEndpoint)
                .then().log().all()
                .statusCode(404)
                .body(containsString("Skontaktuj się z autorem aplikacji"));
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
        Assertions.assertThat(response.jsonPath().getList("errors.message")).contains("An error has occurred");
        Assertions.assertThat(response.jsonPath().getList("errors.userMessage")).contains("Funkcja niedostępna. Skontaktuj się z autorem aplikacji.");
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
        Assertions.assertThat(errorMessage.getErrors().get(0).getMessage()).isEqualTo("An error has occurred");
        Assertions.assertThat(errorMessage.getErrors().get(0).getUserMessage()).isEqualTo("Funkcja niedostępna. Skontaktuj się z autorem aplikacji.");
    }

}
