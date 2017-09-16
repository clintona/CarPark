package au.com.tla.carpark;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class VehicleRecordTest {

    @Test(expected = NullPointerException.class)
    public void testNullType() {
        new VehicleRecord(null, "BLAH");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullId() {
        new VehicleRecord(VehicleType.CAR, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyId() {
        new VehicleRecord(VehicleType.CAR, "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWhitespaceId() {
        new VehicleRecord(VehicleType.CAR, "   ");
    }

    @Test
    public void testOK() throws Exception {
        VehicleRecord record = new VehicleRecord(VehicleType.CAR, "Bob");
        Thread.sleep(100); // sleep 100 ms for date comparison
        assertTrue(new Date().after(record.getEntryTime()));
        assertEquals(VehicleType.CAR, record.getVehicleType());
        assertEquals(0, record.getDuration());
    }

    @Test
    public void testIsParkedTrue() {
        VehicleRecord record = new VehicleRecord(VehicleType.CAR, "Bob");
        assertTrue(record.isParked());
    }

    @Test
    public void testIsParkedFalse() {
        VehicleRecord record = new VehicleRecord(VehicleType.CAR, "Bob");
        record.setDuration(1); // duration > 0 implies vehicle has exited car park
        assertFalse(record.isParked());
    }

}