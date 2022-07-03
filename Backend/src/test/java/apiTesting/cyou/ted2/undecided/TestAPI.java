package apiTesting.cyou.ted2.undecided;

import cyou.ted2.undecided.repository.UserRepository;
import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.path.json.JsonPath;
import io.swagger.v3.core.util.Json;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.restassured.filter.*;
import io.restassured.response.Response;
import io.restassured.specification.*;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;


import java.util.*;

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


    private Cookies cookies;


    @BeforeEach
    public void setUp() {
        Cookie jSessionID = given().relaxedHTTPSValidation().header("Authorization",
                "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes())).
                when().get("https://undecided.ted2.cyou/auth/authenticate").getDetailedCookie("JSESSIONID");
        List<Cookie> cookieList = new ArrayList<>();
        cookieList.add(jSessionID);
        Cookie xsrfToken = given().relaxedHTTPSValidation().cookie(jSessionID).when().get("https" +
                "://undecided.ted2" +
                ".cyou/api/user").getDetailedCookie("XSRF-TOKEN");
        cookieList.add(xsrfToken);

        this.cookies = new Cookies(cookieList);
    }
    @BeforeEach
    public void addCsrfCookieFilter() {
        RestAssured.filters(new Filter() {
            @Override
            public Response filter(FilterableRequestSpecification requestSpec,
                                   FilterableResponseSpecification responseSpec, FilterContext ctx) {
                String csrfToken = requestSpec.getCookies().getValue("XSRF-TOKEN");
                if (csrfToken == null) {
                    csrfToken =
                            RestAssured.given().noFilters().relaxedHTTPSValidation().header("Authorization",
                                    "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes())).
                                    get("https://undecided.ted2.cyou/auth/authenticate").cookie("XSRF-TOKEN");
                }
                requestSpec.replaceHeader("X-XSRF-TOKEN", csrfToken);
                return ctx.next(requestSpec, responseSpec);
            }
        });
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
        // System.out.println(jsonPath.prettyPrint());
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
        JsonPath jsonPath =
                RestAssured.given().body("{\"userid\":" + userId + ", " +
                        "\"timestamp\":" + "2022-07-02T23:13:23.907+00:00}").relaxedHTTPSValidation().contentType(
                                "application/json").
                        header("Referer", "https://undecided.ted2.cyou/profile?follow=0").
                        header("X-Requested-With","XMLHttpRequest").
                        when().post(
                        "https" +
                                "://undecided" +
                                ".ted2" +
                                ".cyou/api/user/myFollower").jsonPath();
        System.out.println(jsonPath.prettyPrint());
    }
}
