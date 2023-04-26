package b22.spartan;

import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utilities.ConfigReader;
import utilities.SpartanUtil;

import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static org.hamcrest.Matchers.notNullValue;

@Disabled
@SerenityTest
public class ConfigDemoTest {

    @BeforeAll
    public static void setUpBase() {
        baseURI = "http://3.216.30.92:7000";
    }

    @Test
    public void test1() {
        System.out.println(ConfigReader.getProperty("serenity.project.name"));
        System.out.println(ConfigReader.getProperty("spartan.editor.username"));

        Map<String, Object> randomSpartanBodyMap = SpartanUtil.getRandomSpartanMap();
        System.out.println("Randomly created new Spartan" + randomSpartanBodyMap);

        //send a post request as editor
        given()
                .auth().basic("editor", "editor")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(randomSpartanBodyMap)
                .log().body()

                .when()
                .post("/api/spartans")
                .prettyPrint();

        Ensure.that("Status code is 201?", statusCodeVerification -> statusCodeVerification.statusCode(201));

        Ensure.that("Content type is JSON?", contentTypeVerification -> contentTypeVerification.contentType(ContentType.JSON));

        Ensure.that("Message is correct?",
                messageVerification -> messageVerification.body("success", notNullValue()));

        Ensure.that("Message is correct?",
                messageVerification -> messageVerification.body("success", Matchers.is("A Spartan is Born!")));

        Ensure.that("Body info is correct?",
                bodyVerification -> bodyVerification.body("data.id", notNullValue()));

        Ensure.that("Body info is correct?",
                bodyVerification -> bodyVerification.body("data.name", Matchers.is(randomSpartanBodyMap.get("name"))));

        Ensure.that("Body info is correct?",
                bodyVerification -> bodyVerification.body("data.gender", Matchers.is(randomSpartanBodyMap.get("gender"))));

        Ensure.that("Body info is correct?",
                bodyVerification -> bodyVerification.body("data.phone", Matchers.is(randomSpartanBodyMap.get("phone"))));

    }
}
