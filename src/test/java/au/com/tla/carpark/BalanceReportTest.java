package au.com.tla.carpark;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class BalanceReportTest {
    private BalanceReport report;

    @Before
    public void setUp() {
        this.report = new BalanceReport();
    }

    @Test
    public void getCarsEntered() throws Exception {
        report.carsExited = 1;
        report.parkingCars = 1;

        assertEquals(2, report.getCarsEntered());
    }

    @Test
    public void getTrucksEntered() throws Exception {
        report.trucksExited = 1;
        report.parkingTrucks = 1;

        assertEquals(2, report.getTrucksEntered());
    }

    @Test
    public void testToString() {
        Date now = new Date();
        report.reportDate = now;
        report.parkingCars = 1;
        String crlf = System.lineSeparator();

        String expected = "Car Park report " + now + crlf +
            "Cars Entered: 1" + crlf +
            "Trucks Entered: 0" + crlf +
            "Cars Exited: 0" + crlf +
            "Trucks Exited: 0" + crlf +
            "Parking Cars: 1" + crlf +
            "Parking Trucks: 0" + crlf +
            "Spaces available: 0" + crlf +
            "Fees paid: 0" + crlf;

        assertEquals(expected, report.toString());
    }

}