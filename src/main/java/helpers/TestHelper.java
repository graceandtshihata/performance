package helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.ArrayLoginRequest;
import models.ArrayLoginResponse;
import models.ClientResponseRequestBody;
import models.QuestionRequestBody;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

public class TestHelper {

    Config config = new Config();

    public WebDriver getDriver(String selGrid) {

        //Desired Caps
        DesiredCapabilities capabilities = null;
        ChromeOptions options = new ChromeOptions();

        if (config.getBrowser().toLowerCase().contains("firefox")) {
            capabilities = DesiredCapabilities.firefox();
        } else {
            options.addArguments("--start-maximized");
            if (config.useSelenium()) {
                options.setHeadless(true);
            }
        }

        WebDriver driver = null;
        if (!config.useSelenium() && config.getBrowser().toLowerCase().contains("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
        } else if (!config.useSelenium() && config.getBrowser().toLowerCase().contains("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            try {
                if (config.getBrowser().toLowerCase().contains("firefox")) {
                    driver = new RemoteWebDriver(new URL((selGrid + "/wd/hub")), capabilities);
                } else {
                    driver = new RemoteWebDriver(new URL((selGrid + "/wd/hub")), options);
                }
            } catch (Exception e) {
                Assert.fail("Couldn't connect to Selenium Grid: " + selGrid);
            }
        }
        // timeout if site page does not load in 10 seconds
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    /**
     * Sleep in ms
     *
     * @param duration
     * @throws Exception
     */
    public static void delay(long duration) throws Exception {
        Thread.sleep(duration);
    }

    /**
     * Capture screenshot
     *
     * @param webdriver
     * @param name
     */
    public void takeScreenShot(WebDriver webdriver, String name) {
        try {
            if (config.takeScreenshots()) {
                String fileWithPath = "./screenshots/" + name.replace(".", "").replace("@", "") + ".png";

                //Convert web driver object to TakeScreenshot

                TakesScreenshot scrShot = ((TakesScreenshot) webdriver);

                //Call getScreenshotAs method to create image file

                File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

                //Move image file to new destination

                File DestFile = new File(fileWithPath);

                //Copy file at destination

                FileUtils.copyFile(SrcFile, DestFile);

            }
        } catch (Exception e) {
            System.out.println("Screenshot failed");
        }
    }

    /**
     * Close the browser
     *
     * @param driver
     */
    public void closeBrowser(WebDriver driver) {
        try {
            driver.quit();
        } catch (Exception e) {
            System.out.println("Failed to close the browser");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Return a random number in range
     *
     * @param min
     * @param max
     * @return
     */
    public static int getRandomNumber(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static boolean getRandomBoolean() {
        Random rd = new Random();
        return rd.nextBoolean();
    }

    /**
     * print msg is console
     *
     * @param text
     */
    public void print(String text) {
        if (config.printConsole()) {
            System.out.println(text);
        }
    }

    public static String getToken() throws
            JSONException {

        Config config = new Config();
        Response response =
                given().auth().preemptive().basic(config.getClientId(), config.getClientSecret())
                        .formParam("grant_type", "password")
                        .formParam("username", config.getAdminUsername())
                        .formParam("password", config.getAdminPassword())
                        .formParam("scope", config.getScope())
                        .when()
                        .post(config.getAuthServer());
        JSONObject jsonObject = new JSONObject(response.getBody().asString());
        String accessToken = jsonObject.get("access_token").toString();
        return accessToken;
    }

    public static void createTestDataFile(String json, String fileName) {
        String fileLocation = "./src/main/resources/data/" + fileName + ".json";
        try {
            FileWriter file = new FileWriter(fileLocation);
            file.write(json);
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayLoginResponse arrayLogin(String activityUniqueId, String userEmail) {

        ArrayLoginRequest arrayLoginRequest = new ArrayLoginRequest(activityUniqueId, userEmail);

        RestAssured.baseURI = new Config().getClientUrl();

        return given()
                .contentType(ContentType.JSON)
                .body(arrayLoginRequest)
                .when()
                .post("/api/array-login")
                .then()
                .assertThat()
                .statusCode(201)
                .extract()
                .as(ArrayLoginResponse.class);
    }

}
