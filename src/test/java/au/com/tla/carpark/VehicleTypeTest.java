package au.com.tla.carpark;

import static org.junit.Assert.*;

import org.junit.Test;

public class VehicleTypeTest {


	@Test
	public void testGetSize() {
		assertEquals(1, VehicleType.CAR.getSize());
		assertEquals(2, VehicleType.TRUCK.getSize());
	}

}
