package tests.api;

import org.apache.log4j.Logger;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import epam.api.dto.Pet;
import epam.api.utils.RestUtilities;
import static epam.data.StatusCode.NOT_FOUND_404;
import static epam.data.StatusCode.OK_200;
import static epam.data.StringConstants.CONSTANT_VALUE_INT;
import static epam.data.StringConstants.ID;
import static epam.data.StringConstants.MESSAGE;
import static epam.data.StringConstants.PET_ID;
import static epam.data.StringConstants.PET_NOT_FOUND;
import epam.data.TestData;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

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
                .delete(PET_ID, petId);

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

    @Test
public void getPetsByExistingId() {

    given().spec(requestSpec)
            .get(String.valueOf(petDog.getId()))
            .then()
            .statusCode(OK_200)
            .and()
            .body(ID, equalTo(petDog.getId()));
}

@Test
public void getPetsByNotExistingId() {

    given().spec(requestSpec)
            .get(String.valueOf(CONSTANT_VALUE_INT))
            .then()
            .statusCode(NOT_FOUND_404);
}

@Test
public void testFindPetById_NotFound() {

    given().spec(requestSpec)
            .get(PET_ID, CONSTANT_VALUE_INT)
            .then()
            .statusCode(NOT_FOUND_404)
            .body(MESSAGE, equalTo(PET_NOT_FOUND));
}

    @AfterAll
    public static void clean() {
        logger.info("The tests were completed");
    }
}
