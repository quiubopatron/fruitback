package es.sm2.rookieproyect.controller;

import es.sm2.rookieproyect.dao.FruitRepository;
import es.sm2.rookieproyect.model.Fruit;
import es.sm2.rookieproyect.model.FruitDto;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import sun.security.util.Cache;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles({ "TST"})
public class ControllerTestIT {

    @LocalServerPort
    private int port;

    private List<Fruit> fruitList;

    @Autowired
    private FruitRepository fruitRepository;


    @Before
    public void setUp() throws Exception {

        fruitList = new ArrayList<>();

        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.defaultParser = Parser.JSON;

        fruitRepository.deleteAll();

        Fruit fruit = new Fruit();
        fruit.setName("Pera");
        fruit.setPricePerKg(1);
        fruit.setDateCreated(LocalDateTime.now());
        fruit = fruitRepository.save(fruit);
        fruitList.add(fruit);

        Fruit fruit2 = new Fruit();
        fruit2.setName("Naranja");
        fruit2.setPricePerKg(2);
        fruit2.setDateCreated(LocalDateTime.now());
        fruit2 = fruitRepository.save(fruit2);
        fruitList.add(fruit2);

    }

    //test list
    @Test
    public void returnFruitIds() {
        when()
            .get("/rookie/fruits")

        .then()
            .statusCode(200)
            .body("name", hasItems("Pera", "Naranja"));
    }

    //test getone
    @Test
    public void returnOneFruit(){
     when()
        .get("/rookie/fruits/"+fruitList.get(0).getIdFruit())
     .then()
        .statusCode(200)
        .body("name", equalTo("Pera"));
    }


    //test delete
    @Test
    public void deleteOneFruit(){
        when()
                .delete("/rookie/fruits/"+fruitList.get(0).getIdFruit())
        .then()
            .statusCode(204);
    }


    //test create
    @Test
    public void createOneFruit(){

        FruitDto fruitDto = new FruitDto();
        fruitDto.setName("melon");
        fruitDto.setPricePerKg(1);

        given().
                contentType("application/json").
                body(fruitDto).
        when()
                .post("/rookie/fruits")
        .then()
            .statusCode(201)
            .body("idFruit", notNullValue())
            .body("name", equalTo("melon"));

    }

    //test update
    @Test
    public void updateFruit(){

        FruitDto fruitDto = new FruitDto();
        fruitDto.setName("Pera");
        fruitDto.setPricePerKg(10);
        fruitDto.setDateCreated(LocalDateTime.now());

        given().
                contentType("application/json").
                body(fruitDto).
        when()
                .put("/rookie/fruits/"+fruitList.get(0).getIdFruit())
        .then()
                .statusCode(202)
                .body("pricePerKg", equalTo(10));
    }

}
