package b22.spartan;

import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import utilities.ConfigReader;
import utilities.SpartanUtil;

import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
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
        //This part is ConfigDemoTest
        System.out.println(ConfigReader.getProperty("serenity.project.name"));
        System.out.println(ConfigReader.getProperty("spartan.editor.username"));

    }

}
