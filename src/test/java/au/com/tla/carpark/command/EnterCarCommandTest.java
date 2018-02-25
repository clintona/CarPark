package au.com.tla.carpark.command;

import au.com.tla.carpark.CarPark;
import au.com.tla.carpark.CarParkException;
import au.com.tla.carpark.CarParkImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EnterCarCommandTest {
    private CarPark carPark;

    @Before
    public void setUp() throws Exception {
        this.carPark = new CarParkImpl(1); // car park fits only one car
    }

    @Test(expected=CarParkException.class)
    public void testEnterCarparkFull() throws Exception {
    	carPark.enterCar(); // fills car park
        EnterCarCommand cmd = new EnterCarCommand(carPark);
        
        cmd.execute(); // expect exception, park full
    }

    @Test
    public void testEnterCarparkSuccess() {
    	EnterCarCommand cmd = new EnterCarCommand(carPark);
        
    	cmd.execute();
        assertEquals(1, carPark.getParkedCars().size());
    }

}