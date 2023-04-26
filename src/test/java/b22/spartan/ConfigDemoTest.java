package b22.spartan;

import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utilities.ConfigReader;
import utilities.SpartanUtil;

import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.baseURI;
import static net.serenitybdd.rest.RestRequests.given;

@Disabled
@SerenityTest
public class ConfigDemoTest {

    @BeforeAll
    public static void setUpBase() {
        baseURI = "http://3.216.30.92:7000";
    }
    @Test
    public void test1(){
        System.out.println(ConfigReader.getProperty("serenity.project.name"));
        System.out.println(ConfigReader.getProperty("spartan.editor.username"));

        Map<String, Object> randomSpartanBodyMap= SpartanUtil.getRandomSpartanMap();
        System.out.println("Randomly created new Spartan"+randomSpartanBodyMap);

        //send a post request as editor
        given()
                .auth().basic("editor","editor")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(randomSpartanBodyMap)
                .log().body()

                .when()
                .post("/api/spartans")
                .prettyPrint();
    }
}
