package tests.api;

import epam.api.dto.Pet;
import epam.api.utils.RestUtilities;
import epam.data.TestData;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;


import static epam.data.StatusCode.NOT_FOUND_404;
import static epam.data.StatusCode.OK_200;

import static epam.data.StringConstants.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Execution(ExecutionMode.CONCURRENT)
public class TestsPet {

    private static final Logger logger = Logger.getLogger(TestsPet.class);
    private static RequestSpecification requestSpec;

    private static Pet petDog;

    @BeforeAll
    public static void initSpec() {
        requestSpec = RestUtilities.getRequestSpecification();
        logger.info("Init was completely successfully");
        petDog = TestData.getTestPet();
    }

    @BeforeEach
    public void setUp() {
            given().spec(requestSpec)
                    .body(petDog)
                    .when().post()
                    .then()
                    .statusCode(OK_200);
        logger.info("Test data was created successfully");
    }

    // Test case for DELETE request
    @Test
    public void deletePetTest() {
        long petId = 1; // Define petId here

        Response response = given().spec(requestSpec)
                .when()
                .delete(PET_ID,petId);

        // Assert status code
        assertEquals(200, response.getStatusCode());
    }

    // Test case for GET request
    @Test
    public void findPetsByStatusTest() {
        String status = "available"; // Define status here

        Response response = given().spec(requestSpec)
                .header("Accept", "application/json")
                .when()
                .get("/findByStatus?status=" + status);

        // Assert status code
        assertEquals(200, response.getStatusCode());
    }

    @AfterAll
    public static void clean() {
        logger.info("The tests were completed");
    }
}