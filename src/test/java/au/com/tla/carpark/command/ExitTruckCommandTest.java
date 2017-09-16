package au.com.tla.carpark.command;

import org.junit.Before;
import org.junit.Test;

import au.com.tla.carpark.CarPark;
import au.com.tla.carpark.CarParkException;
import au.com.tla.carpark.CarParkImpl;

public class ExitTruckCommandTest {
    private CarPark carPark;

    @Before
    public void setUp() throws Exception {
        this.carPark = new CarParkImpl(3);
    }

    @Test(expected = CarParkException.class)
    public void testExitWrongId() throws Exception {
        carPark.enterCar(); // id 1
        carPark.enterTruck(); // id 2

        ExitTruckCommand cmd = new ExitTruckCommand(carPark, "1", 1);
        cmd.execute(); // expect exception, wrong id (1) should be 2
    }

}