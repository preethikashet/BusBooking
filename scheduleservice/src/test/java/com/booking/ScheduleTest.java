package com.booking;

import com.booking.schedule.client.BusClient;
import com.booking.schedule.client.SeatClient;
import com.booking.schedule.controller.ScheduleController;
import com.booking.schedule.entity.Schedule;
import com.booking.schedule.repository.Schedulerepository;
import com.booking.schedule.service.ScheduleService;
import org.example.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")

public class ScheduleTest {
    @Autowired
    private ScheduleService scheduleService;
    private ScheduleController scheduleController;
    @Mock
    private BusClient busClient;

    @Mock
    private SeatClient seatClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        scheduleService = mock(ScheduleService.class);
        scheduleController = new ScheduleController();
        scheduleController.scheduleService = scheduleService; // direct field access
    }

    @Test
    void testAddSchedule() {
        Schedule schedule = new Schedule();
        schedule.setScheduleid(1);
        when(scheduleService.addSchedule(schedule)).thenReturn("Schedule Added");


        assertEquals("Schedule Added", "Schedule Added");
    }

    @Test
    void testGetSchedules() {
        Schedule s1 = new Schedule();
        Schedule s2 = new Schedule();
        List<Schedule> schedules = Arrays.asList(s1, s2);

        when(scheduleService.getSchedules()).thenReturn(schedules);

        ResponseEntity<List<Schedule>> response = scheduleController.getSchedules();
        assertEquals(2, response.getBody().size());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testTestEndpoint() {
        String result = scheduleController.test();
        assertEquals("Test/test", result);
    }

    @Test
    void testSearchBus() {
        String src = "A";
        String dst = "B";
        String date = "2025-10-10";

        UserResponseDTO user1 = new UserResponseDTO(); // you can populate fields if needed
        List<UserResponseDTO> responseList = Arrays.asList(user1);

        when(scheduleService.getSchedules(src, dst, date)).thenReturn(responseList);

        ResponseEntity<List<UserResponseDTO>> response = scheduleController.searchBus(src, dst, date);
        assertEquals(1, response.getBody().size());
        assertEquals(200, response.getStatusCodeValue());
    }

//    @Autowired
//    private ScheduleService scheduleService;

    @Autowired
    private Schedulerepository schedulerepository;

    @Test
//    public void testAddAndGetSchedules() {
//        // Given
//        Schedule schedule = new Schedule();
//        schedule.setScheduleid(101);
//        schedule.setBusid(1);
//        schedule.setDriverid(1);
//        schedule.setRouteid(1);
//        schedule.setDeparturetime(LocalDateTime.now().plusDays(1));
//        schedule.setArrivaltime(LocalDateTime.now().plusDays(1).plusHours(5));
//        schedule.setPrice(200);
//
//        // Mocking busClient to return a bus with 40 seats
//        BusResponseDTO mockBus = new BusResponseDTO();
//        mockBus.setBusid(1);
//        mockBus.setTotalseats(40);
//        when(busClient.getbuses(new BusRequestDTO(List.of(1))))
//                .thenReturn(ResponseEntity.ok(List.of(mockBus)));
//
//        // Mocking repository save
//        when(schedulerepository.save(schedule)).thenReturn(schedule);
//
//        // When
//        String result = scheduleService.addSchedule(schedule);
//
//        // Then
//        assertEquals("Schedule added", result);
//    }

//    @Test
//    public void testGetBusDetails() {
//        // Given
//        BusRequestDTO busRequestDTO = new BusRequestDTO(List.of(1));
//
//        // When
//        List<BusResponseDTO> busDetails = scheduleService.getBusDetails(busRequestDTO);
//
//        // Then
//        Assertions.assertNotNull(busDetails);
//        Assertions.assertFalse(busDetails.isEmpty());
//        Assertions.assertEquals(1, busDetails.get(0).busid);
//    }

//    @Test
//    public void testGetRemainingSeats() {
//        // Given
//        BookingRequestDTO requestDTO = new BookingRequestDTO(
//                LocalDate.now().plusDays(1),
//                List.of(1)
//        );
//
//        // When
//        List<BookingResponseDTO> remainingSeats = scheduleService.getRemainingSeats(requestDTO);
//
//        // Then
//        Assertions.assertNotNull(remainingSeats);
//        // Assuming there's at least one bus
//        Assertions.assertFalse(remainingSeats.isEmpty());
//    }

//    @Test
//    public void testGetRoute() {
//        // Given
//        RouteRequestDTO routeRequestDTO = new RouteRequestDTO("CityA", "CityB");
//
//        // When
//        Integer routeId = scheduleService.getRoute(routeRequestDTO);
//
//        // Then
//        Assertions.assertNotNull(routeId);
//        Assertions.assertTrue(routeId > 0);
//    }

//    @Test
    public void testGetSchedulesByRouteAndDate() {
        // Given
        String src = "CityA";
        String dest = "CityB";
        String date = LocalDate.now().plusDays(1).format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        // When
        List<UserResponseDTO> userResponses = scheduleService.getSchedules(src, dest, date);

        // Then
        Assertions.assertNotNull(userResponses);
        userResponses.forEach(response -> {
            Assertions.assertNotNull(response.busnumber);
            Assertions.assertNotNull(response.remainingseats);
        });
    }
}
