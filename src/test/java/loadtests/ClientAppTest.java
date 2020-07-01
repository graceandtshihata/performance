package loadtests;

import com.fasterxml.jackson.databind.ObjectMapper;
import helpers.Config;
import helpers.TestHelper;
import models.*;
import org.apache.commons.lang3.RandomStringUtils;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import views.*;

import java.io.File;
import java.io.IOException;

import static helpers.TestHelper.*;

public class ClientAppTest {
    private TestHelper helper = new TestHelper();
    Config config = new Config();



    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    @Test
    public void participants() throws IOException {

}
