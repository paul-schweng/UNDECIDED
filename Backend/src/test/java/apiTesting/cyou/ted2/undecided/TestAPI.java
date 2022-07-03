package apiTesting.cyou.ted2.undecided;

import io.restassured.common.mapper.TypeRef;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestAPI {
    private String password = "test1234Test";
    private String hashPassword = "6901343eb1a4ad2af45e4d0a3390b82aecea231b71af160fa40f366f05836a27";
    private String username = "testuser";
    private String userId = "5c222ea3c45b443d";
    private String email = "test@test.de";
    private String modelType = "";
    private String name = "test";
    private String userType;
    private String description = "test";
    private String language;
    private String profileImage;
    private String birthdate = "0101-01-01T00:00:00+00:53:28";
    private String registerDate = "2022-07-02T22:25:52+02:00";
    private boolean verified = false;
    private int ratingsNum = 0;
    private int followerNum = 1;
    private int followingNum = 1;
    private int bannerImage = 0;
    private boolean darkTheme = false;


    private String timestamp;
    private Cookies cookies;


    @BeforeEach
    public void setUp() {
        timestamp = ZonedDateTime.now()
                .truncatedTo(ChronoUnit.MINUTES)
                .format(DateTimeFormatter.ISO_DATE_TIME);
        timestamp = timestamp.split("\\[")[0];

        Cookie jSessionID = given().relaxedHTTPSValidation().header("Authorization",
                "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes())).
                when().get("https://undecided.ted2.cyou/auth/authenticate").getDetailedCookie("JSESSIONID");
        List<Cookie> cookieList = new ArrayList<>();
        cookieList.add(jSessionID);

        this.cookies = new Cookies(cookieList);
    }

    @AfterEach
    public void cleanUp() {
        this.password = "test1234Test";
        this.hashPassword = "6901343eb1a4ad2af45e4d0a3390b82aecea231b71af160fa40f366f05836a27";
        this.username = "testuser";
        this.userId = "5c222ea3c45b443d";
        this.email = "test@test.de";
        this.modelType = "";
        this.name = "test";
        this.userType = null;
        this.description = "test";
        this.language = null;
        this.profileImage = null;
        this.birthdate = "0101-01-01T00:00:00+00:53:28";
        this.registerDate = "2022-07-02T22:25:52+02:00";
        this.verified = false;
        this.ratingsNum = 0;
        this.followerNum = 1;
        this.followingNum = 1;
        this.bannerImage = 0;
        this.darkTheme = false;
    }


    @Test
    public void testAuthenticate() {

        given().relaxedHTTPSValidation().header("Authorization",
                "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes())).
                when().get("https://undecided.ted2.cyou/auth/authenticate").then().assertThat().statusCode(200);
    }


    @Test
    public void testGetUser() {
        JsonPath jsonPath = given().relaxedHTTPSValidation().cookies(this.cookies).when().get("https" +
                "://undecided.ted2" +
                ".cyou/api/user").getBody().jsonPath();
        assertEquals(hashPassword, jsonPath.get("password"));
        assertEquals(username, jsonPath.get("username"));
        assertEquals(userId, jsonPath.get("id"));
        assertEquals(email, jsonPath.get("email"));
        assertEquals(modelType, jsonPath.get("modelType"));
        assertEquals(name, jsonPath.get("name"));
        assertNull(jsonPath.get("userType"));
        assertEquals(description, jsonPath.get("description"));
        assertNull(jsonPath.get("usertype"));
        assertNull(jsonPath.get("language"));
        assertEquals(birthdate, jsonPath.get("birthdate"));
        assertEquals(registerDate, jsonPath.get("registerDate"));
        assertEquals(verified, jsonPath.get("verified"));
        assertEquals(ratingsNum, (int) jsonPath.get("ratingsNum"));
        assertEquals(followerNum, (int) jsonPath.get("followerNum"));
        assertEquals(followingNum, (int) jsonPath.get("followingNum"));
        assertEquals(bannerImage, (int) jsonPath.get("bannerImage"));
        assertEquals(darkTheme, jsonPath.get("darkTheme"));
    }


    @Test
    public void testGetMyFollower() {
        List<Map<String, Object>> jsonPath =
                given().body("{\"userid\":\"" + userId + "\", " +
                        "\"timestamp\":\"" + timestamp + "\"}").relaxedHTTPSValidation().contentType(
                        "application/json")
                        .cookies(cookies).
                        when().post("https://undecided.ted2.cyou/api/user/myFollower").as(new TypeRef<List<Map<String, Object>>>() {
                });
        assertEquals("chrisg", jsonPath.get(0).get("username"));
    }

    @Test
    public void testGetMyFollowing() {
        List<Map<String, Object>> jsonPath =
                given().body("{\"userid\":\"" + userId + "\", " +
                        "\"timestamp\":\"" + timestamp + "\"}").relaxedHTTPSValidation().contentType(
                        "application/json")
                        .cookies(cookies).
                        when().post("https://undecided.ted2.cyou/api/user/myFollowing").as(new TypeRef<List<Map<String,
                        Object>>>() {
                });
        assertEquals("chrisg", jsonPath.get(0).get("username"));
    }

    @Test
    public void testGetMyFriends() {
        List<Map<String, Object>> jsonPath =
                given().body("{\"userid\":\"" + userId + "\", " +
                        "\"timestamp\":\"" + timestamp + "\"}").relaxedHTTPSValidation().contentType(
                        "application/json")
                        .cookies(cookies).
                        when().post("https://undecided.ted2.cyou/api/user/myFriends").as(new TypeRef<List<Map<String,
                        Object>>>() {
                });
        assertEquals("chrisg", jsonPath.get(0).get("username"));
    }
}
