package au.com.tla.carpark.command;

import au.com.tla.carpark.CarPark;
import au.com.tla.carpark.CarParkException;
import au.com.tla.carpark.CarParkImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExitCarCommandTest {
    private CarPark carPark;

    @Before
    public void setUp() throws Exception {
        this.carPark = new CarParkImpl(3);
    }

    @Test(expected=CarParkException.class)
    public void testExitWrongId() throws Exception {
        carPark.enterCar(); // id 1
        carPark.enterTruck(); // id 2

        ExitCarCommand cmd = new ExitCarCommand(carPark, "2", 1);
        cmd.execute(); // expect exception, wrong id (1) should be 2
    }

    @Test
    public void testExitSuccess() {
        carPark.enterCar(); // id 1

        ExitCarCommand cmd = new ExitCarCommand(carPark, "1", 1);
        cmd.execute();

        assertEquals(1, carPark.getExitedCars().size());
    }

}