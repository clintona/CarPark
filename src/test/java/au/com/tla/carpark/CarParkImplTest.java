package au.com.tla.carpark;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CarParkImplTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructorBadSize() {
        new CarParkImpl(0);
    }

    @Test
    public void testConstructorSuccess() {
        CarPark carPark = new CarParkImpl(1);
        assertEquals(1, carPark.getSpacesAvailable());
    }

    @Test
    public void enterCarOK() throws Exception {
        CarPark carPark = new CarParkImpl(1);
        VehicleRecord record = carPark.enterCar();
        assertEquals(1, carPark.getParkedCars().size());
        assertEquals(0, carPark.getSpacesAvailable());
        assertEquals(VehicleType.CAR, record.getVehicleType());
        assertEquals(0, record.getDuration());
    }

    @Test(expected = CarParkException.class)
    public void enterCarFull() throws Exception {
        CarPark carPark = new CarParkImpl(1);
        carPark.enterCar();
        carPark.enterCar();
    }

    @Test(expected = CarParkException.class)
    public void enterTruckFull() throws Exception {
        CarPark carPark = new CarParkImpl(1);
        carPark.enterTruck();
    }

    @Test
    public void exitCar() throws Exception {
        CarPark carPark = new CarParkImpl(1);
        VehicleRecord car1 = carPark.enterCar();
        carPark.exitCar(car1.getVehicleId(), 1);

        List<VehicleRecord> exitedVehicles = carPark.getExitedCars();
        assertEquals(1, exitedVehicles.size());
        assertEquals(1, carPark.getSpacesAvailable());
        assertEquals(VehicleType.CAR, car1.getVehicleType());
        assertTrue(exitedVehicles.contains(car1));
    }

    @Test
    public void exitTruck() throws Exception {
        CarPark carPark = new CarParkImpl(2);
        VehicleRecord truck1 = carPark.enterTruck();
        carPark.exitTruck(truck1.getVehicleId(), 1);

        List<VehicleRecord> exitedVehicles = carPark.getExitedTrucks();
        assertEquals(1, exitedVehicles.size());
        assertEquals(2, carPark.getSpacesAvailable());
        assertEquals(VehicleType.TRUCK, truck1.getVehicleType());
        assertTrue(exitedVehicles.contains(truck1));
    }

    @Test(expected=IllegalArgumentException.class)
    public void exitCarBadDuration() throws Exception {
        CarPark carPark = new CarParkImpl(1);
        VehicleRecord car1 = carPark.enterCar();
        carPark.exitCar(car1.getVehicleId(), 0);
    }

    @Test(expected=CarParkException.class)
    public void exitCarBadId() throws Exception {
        CarPark carPark = new CarParkImpl(1);
        carPark.enterCar();
        carPark.exitCar("BLAH", 1);
    }

    @Test(expected=IllegalStateException.class)
    public void exitCarAlreadyGone() throws Exception {
        CarPark carPark = new CarParkImpl(1);
        VehicleRecord car1 = carPark.enterCar();
        carPark.exitCar(car1.getVehicleId(), 1);
        carPark.exitCar(car1.getVehicleId(), 1);
    }

    @Test
    public void getParkedCars() throws Exception {
        CarPark carPark = new CarParkImpl(5);
        carPark.enterCar();
        carPark.enterTruck();
        carPark.enterCar();
        assertEquals(2, carPark.getParkedCars().size());
    }

    @Test
    public void getParkedTrucks() throws Exception {
        CarPark carPark = new CarParkImpl(5);
        carPark.enterCar();
        carPark.enterTruck();
        carPark.enterCar();
        assertEquals(1, carPark.getParkedTrucks().size());
    }

    @Test
    public void getExitedCars() throws Exception {
        CarPark carPark = new CarParkImpl(5);
        VehicleRecord car1 = carPark.enterCar();
        carPark.enterTruck();
        carPark.enterCar();
        carPark.exitCar(car1.getVehicleId(), 1);

        assertEquals(1, carPark.getExitedCars().size());
    }

    @Test
    public void getExitedTrucks() throws Exception {
        CarPark carPark = new CarParkImpl(5);
        VehicleRecord t1 = carPark.enterTruck();
        carPark.enterTruck();
        carPark.enterCar();
        carPark.exitTruck(t1.getVehicleId(), 1);

        assertEquals(1, carPark.getExitedTrucks().size());
    }

    @Test(expected=CarParkException.class)
    public void testTruckLeavingAsCar() throws Exception {
        CarPark carPark = new CarParkImpl(5);
        carPark.enterTruck(); // id is 1
        carPark.enterCar(); // id is 2
        carPark.exitTruck("2", 1); // should fail
    }

    @Test
    public void getZeroSpacesAvailable() {
        CarPark carPark = new CarParkImpl(1);
        carPark.enterCar();

        assertEquals(0, carPark.getSpacesAvailable());
    }

    @Test
    public void getSurplusSpacesAvailable() {
        CarPark carPark = new CarParkImpl(5);
        carPark.enterCar();
        carPark.enterTruck();

        assertEquals(2, carPark.getSpacesAvailable());
    }
    
    @Test
    public void getExitedCarsDuration() {
        CarPark carPark = new CarParkImpl(5);
        VehicleRecord car1 = carPark.enterCar();
        VehicleRecord car2 = carPark.enterCar();
        VehicleRecord car3 = carPark.enterCar();
        carPark.exitCar(car1.getVehicleId(), 1);
        carPark.exitCar(car2.getVehicleId(), 2);
        carPark.exitCar(car3.getVehicleId(), 3);
        
        assertEquals(6, carPark.getExitedCarsDuration());
    }

    @Test
    public void getExitedTrucksDuration() {
        CarPark carPark = new CarParkImpl(6);
        VehicleRecord truck1 = carPark.enterTruck();
        VehicleRecord truck2 = carPark.enterTruck();
        VehicleRecord truck3 = carPark.enterTruck();
        carPark.exitTruck(truck1.getVehicleId(), 1);
        carPark.exitTruck(truck2.getVehicleId(), 2);
        carPark.exitTruck(truck3.getVehicleId(), 3);

        assertEquals(6, carPark.getExitedTrucksDuration());
    }

    @Test
    public void getBalanceReportExample1() throws Exception {
        CarPark carPark = new CarParkImpl(10);
        carPark.enterCar();
        carPark.enterTruck();

        BalanceReport report = carPark.getBalanceReport(carPark);

        assertEquals(1, report.getCarsEntered());
        assertEquals(1, report.getTrucksEntered());
        assertEquals(0, report.carsExited);
        assertEquals(0, report.trucksExited);
        assertEquals(1, report.parkingCars);
        assertEquals(1, report.parkingTrucks);
        assertEquals(7, report.spacesAvailable);
        assertEquals(0, report.feesPaid);
    }

    @Test
    public void getBalanceReportExample2() throws Exception {
        CarPark carPark = new CarParkImpl(15);
        VehicleRecord car1 = carPark.enterCar();
        carPark.enterTruck();
        carPark.exitCar(car1.getVehicleId(), 2);

        BalanceReport report = carPark.getBalanceReport(carPark);

        assertEquals(1, report.getCarsEntered());
        assertEquals(1, report.getTrucksEntered());
        assertEquals(1, report.carsExited);
        assertEquals(0, report.trucksExited);
        assertEquals(0, report.parkingCars);
        assertEquals(1, report.parkingTrucks);
        assertEquals(13, report.spacesAvailable);
        assertEquals(4, report.feesPaid);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testInvalidHourlyRate() {
        CarPark carPark = new CarParkImpl(1);
        carPark.setCarHourlyRate(-1);
    }

    @Test
    public void testValidHourlyRate() {
        CarPark carPark = new CarParkImpl(1);
        carPark.setTruckHourlyRate(0);
        // $0 == free parking; test passes if no exception
        carPark.setCarHourlyRate(1);
    }
}