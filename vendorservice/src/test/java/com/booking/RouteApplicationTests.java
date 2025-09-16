package com.booking;

import com.booking.vendor.controller.RouteController;
import com.booking.vendor.entity.Route;
import com.booking.vendor.service.RouteService;
import org.example.dto.RouteRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@TestPropertySource(locations = "classpath:application-test.yml")
public class RouteApplicationTests {

    @Autowired
    private RouteService routeService;

    @Autowired
    private RouteController routeController;

    private Route route1, route2;

    @BeforeEach
    public void setUp() {
        route1 = new Route(1, "CityA", "CityB", 100, 101);
        route2 = new Route(2, "CityC", "CityD", 200, 102);

        // Clean slate before each test is guaranteed by @Transactional rollback
    }

    @Test
    public void testAddAndGetRoute() {
        // Add routes
        String result1 = routeService.addRoute(route1);
        String result2 = routeService.addRoute(route2);

        assertEquals("done", result1);
        assertEquals("done", result2);

        // Retrieve all routes
        List<Route> routes = routeService.getRoute();
        assertNotNull(routes);
        assertEquals(2, routes.size());
        assertTrue(routes.stream().anyMatch(r -> r.getRouteid().equals(1)));
        assertTrue(routes.stream().anyMatch(r -> r.getRouteid().equals(2)));
    }

    @Test
    public void testGetRouteId() {
        // Add route first
        routeService.addRoute(route1);

        // Fetch route id by src and dest
        Integer routeId = routeService.getRouteId("CityA", "CityB");

        assertNotNull(routeId);
        assertEquals(1, routeId);
    }

    @Test
    public void testGetRouteIdController() {
        // Add route first
        routeController.addRoute(new Route(1, "CityA", "CityB", 100, 101));

        // Fetch route id by src and dest
        ResponseEntity<Integer> routeId = routeController.getRoute(new RouteRequestDTO("CityA", "CityB"));
        assertNotNull(routeId);
        assertEquals(1, routeId.getBody());
    }
}

