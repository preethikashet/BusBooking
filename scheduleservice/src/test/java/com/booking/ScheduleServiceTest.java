package com.booking;
import org.example.dto.*;
import com.booking.schedule.client.*;
import com.booking.schedule.entity.Schedule;
import com.booking.schedule.repository.Schedulerepository;
import com.booking.schedule.service.ScheduleService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ScheduleServiceTest {

    @Mock
    private BusClient busClient;

    @Mock
    private DriverClient driverClient;

    @Mock
    private SeatClient seatClient;

    @Mock
    private RouteClient routeClient;

    @Mock
    private Schedulerepository schedulerepository;

    @InjectMocks
    private ScheduleService scheduleService;

    @BeforeEach
    void setup() {
        // Mock repository findBusIdsByRouteAndDate or findAll as needed
        Schedule schedule = new Schedule();
        schedule.setScheduleid(1);
        schedule.setBusid(101);
        schedule.setDriverid(201);
        schedule.setRouteid(301);
        schedule.setDeparturetime(LocalDateTime.of(2025, 9, 17, 10, 0));
        schedule.setArrivaltime(LocalDateTime.of(2025, 9, 17, 14, 0));
        schedule.setPrice(500);

        when(schedulerepository.findBusIdsByRouteAndDate(any(), any())).thenReturn(List.of(schedule));

        // Mock RouteClient response
        when(routeClient.getRoute(any())).thenReturn(org.springframework.http.ResponseEntity.ok(301));

        // Mock SeatClient response
        BookingResponseDTO booking = new BookingResponseDTO();
        booking.setBusid(101);
        booking.setRemainingseats(10);
        when(seatClient.getRemainingSeats(any())).thenReturn(List.of(booking));

        // Mock BusClient response
        BusResponseDTO bus = new BusResponseDTO();
        bus.setBusid(101);
        bus.setBusno("BUS-101");
        bus.setVendorname("VendorX");
        bus.setTotalseats(40);
        when(busClient.getbuses(any())).thenReturn(org.springframework.http.ResponseEntity.ok(List.of(bus)));

        // Mock DriverClient response
        DriverResponseDTO driver = new DriverResponseDTO();
        driver.setDriverid(201);
        driver.setDrivernumber("DR-201");

        List<DriverResponseDTO> driverList = List.of(driver);
        ResponseEntity<List<DriverResponseDTO>> responseEntity = ResponseEntity.ok(driverList);

        when(driverClient.getDriverDetails(any())).thenReturn(responseEntity);

    }

    @Test
    void testGetSchedules() {
        List<UserResponseDTO> results = scheduleService.getSchedules("CityA", "CityB", "17-09-2025");

        assertNotNull(results);
        assertEquals(1, results.size());

        UserResponseDTO dto = results.get(0);
        assertEquals("BUS-101", dto.busnumber);
        assertEquals("VendorX", dto.vendorname);
        assertEquals("DR-201", dto.drivernumber);
        assertEquals(10, dto.remainingseats);
        assertEquals(500, dto.price);
    }
//    @Test
//    void testGetSchedules_returnsAllSchedules() {
//        // Prepare sample schedules
//        Schedule schedule1 = new Schedule();
//        schedule1.setScheduleid(1);
//        schedule1.setBusid(101);
//
//        Schedule schedule2 = new Schedule();
//        schedule2.setScheduleid(2);
//        schedule2.setBusid(102);
//
//        List<Schedule> mockSchedules = List.of(schedule1, schedule2);
//
//        // Mock repository to return the sample list
//        when(schedulerepository.findAll()).thenReturn(mockSchedules);
//
//        // Call the service method
//        List<Schedule> result = scheduleService.getSchedules();
//
//        // Verify the result is exactly what repository returns
//        assertNotNull(result);
//        assertEquals(2, result.size());
////        assertEquals(schedule1.getScheduleid(), result.get(0).getScheduleid());
////        assertEquals(schedule2.getBusid(), result.get(1).getBusid());
//    }


}
