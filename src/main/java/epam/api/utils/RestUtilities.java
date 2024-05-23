package epam.api.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static epam.api.utils.Path.BASE_PATH;
import static epam.core.configuration.PropertiesService.getProperty;
import static epam.data.TestProperties.ENDPOINT;

public class RestUtilities {

    public static RequestSpecification getRequestSpecification() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(getProperty(ENDPOINT))
                .setBasePath(BASE_PATH)
                .setContentType(ContentType.JSON)
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
        return requestSpecBuilder.build();
    }
}