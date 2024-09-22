package com.example.microservices.inventory_service;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryServiceApplicationTests {

	@ServiceConnection
	static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:16.4");
	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		postgreSQLContainer.start();
	}

	@Test
	void shouldGetInventory() {
		boolean existingItem = RestAssured.given()
				.contentType("application/json")
				.when()
				.get("/api/inventory?skuCode=iphone_16&quantity=100")
				.then()
				.log().all()
				.statusCode(200)
				.extract()
				.response().as(Boolean.class);
		assertTrue(existingItem);

		boolean nonExistingItem = RestAssured.given()
				.contentType("application/json")
				.when()
				.get("/api/inventory?skuCode=iphone_1&quantity=1000")
				.then()
				.log().all()
				.statusCode(200)
				.extract()
				.response().as(Boolean.class);
		assertFalse(nonExistingItem);
	}
}
