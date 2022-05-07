package com.tqs;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class RestAssuredTest {

    private final String api = "https://jsonplaceholder.typicode.com/todos";
    
    @Test
    public void whenAccessEndpoint_thenStatus200() {
        given().when().get(api).then().assertThat().statusCode(200);
    }

    @Test
    public void whenGetTodo4_returnsSpecificObject() {
        given().when().get(api+"/4").then().body("title", equalTo("et porro tempora"));
    }

    @Test
    public void whenGettingAllTodos_resultHas198and199() {
        given().when().get(api).then().body("id", hasItems(198,199));
    }
    
}
