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

public class DataSetup {
    private TestHelper helper = new TestHelper();
    private Config config = new Config();
    ObjectMapper mapper = new ObjectMapper();


    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }


    @Test
    public void getActivityDetails() throws Exception {
//
//        RestAssured.baseURI = config.getArray3ApiUri();
//        String path = "/user";
//
//
//        ActivityResponse activity = given().log().all().auth().preemptive().oauth2(TestHelper.getToken())
//                .contentType(ContentType.JSON)
//                .pathParam("activityId", config.getActivityId())
//                .when()
//                .get(path)
//                .then()
//                .assertThat()
//                .statusCode(HttpStatus.SC_OK)
//                .extract()
//                .as(ActivityResponse.class);
//
//        TestHelper.createTestDataFile((mapper.writeValueAsString(activity.data)), "activity");

    }
}
