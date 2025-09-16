package com.booking;

import com.booking.vendor.controller.BusController;
import com.booking.vendor.entity.Bus;
import com.booking.vendor.entity.Vendor;
import com.booking.vendor.repository.BusDAO;
import com.booking.vendor.repository.VendorDAO;
import com.booking.vendor.service.BusService;
import org.example.dto.BusRequestDTO;
import org.example.dto.BusResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@TestPropertySource(locations = "classpath:application-test.yml")
public class BusApplicationTests {

	@Autowired
	private BusDAO busDAO;

	@Autowired
	private BusService busService;

	@Autowired
	private VendorDAO vendorDAO;

	@Autowired
	private BusController busController;

	@Test
	public void testFindByBusidIn() {
		// Given
		Bus bus1 = new Bus(1, "KA01AB1234", 40, 101);
		Bus bus2 = new Bus(2, "KA01AB5678", 30, 102);
		Bus bus3 = new Bus(3, "KA01CD9876", 35, 103);

		busDAO.saveAll(List.of(bus1, bus2, bus3));

		// When
		List<Bus> result = busDAO.findByBusidIn(List.of(1, 3));

		// Then
		assertEquals(2, result.size());
		List<Integer> resultIds = result.stream().map(Bus::getBusid).toList();
		assertTrue(resultIds.containsAll(List.of(1, 3)));
	}


	@BeforeEach
	public void setup() {
		busDAO.deleteAll();
		vendorDAO.deleteAll();

		Vendor vendor1 = new Vendor(101, "Vendor A", "");
		Vendor vendor2 = new Vendor(102, "Vendor B", "");

		vendorDAO.saveAll(List.of(vendor1, vendor2));

		Bus bus1 = new Bus(1, "KA01AB1234", 40, 101);
		Bus bus2 = new Bus(2, "KA01AB5678", 50, 102);
		Bus bus3 = new Bus(3, "KA01AB9999", 60, 101);

		busDAO.saveAll(List.of(bus1, bus2, bus3));
	}

	@Test
	public void testGetBuses() {
		// Given
		BusRequestDTO requestDTO = new BusRequestDTO();
		requestDTO.busids = Arrays.asList(1, 3);

		// When
		List<BusResponseDTO> responseList = busService.getBuses(requestDTO);

		// Then
		assertEquals(2, responseList.size());

		BusResponseDTO firstBus = responseList.get(0);
		assertTrue(
				firstBus.getVendorname().equals("Vendor A") ||
						responseList.get(1).getVendorname().equals("Vendor A")
		);

		List<Integer> returnedIds = responseList.stream().map(BusResponseDTO::getBusid).toList();
		assertTrue(returnedIds.containsAll(List.of(1, 3)));
	}

	@Test
	public void testGetBusByIdExists() {
		assertTrue(busController.getBusById(1));
	}

	@Test
	public void testGetBusByIdNotExists() {
		assertFalse(busController.getBusById(999));
	}

	@Test
	public void testGetBusesByIds(){
		assertEquals(2, busController.getbuses(new BusRequestDTO(List.of(1,2))).getBody().size());
	}

	@Test
	public void testGetAllBuses(){
		assertEquals(3, busController.getBus().getBody().size());
	}


	}