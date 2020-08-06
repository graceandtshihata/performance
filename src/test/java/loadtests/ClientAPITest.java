package loadtests;

import com.fasterxml.jackson.databind.ObjectMapper;
import helpers.Config;
import helpers.TestHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.File;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

public class ClientAPITest {
    Config config = new Config();

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void pre1() throws Exception {
        given().
                when().
                get("http://ergast.com/api/f1/2017/circuits.json").
                then().
                assertThat().
                body("MRData.CircuitTable.Circuits.circuitId",hasSize(20));

       }
}
