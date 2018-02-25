package au.com.tla.carpark.command;

import au.com.tla.carpark.CarPark;
import au.com.tla.carpark.CarParkException;
import au.com.tla.carpark.CarParkImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EnterTruckCommandTest {
    private CarPark carPark;

    @Before
    public void setUp() throws Exception {
        this.carPark = new CarParkImpl(2); // car park fits only one truck
    }

    @Test(expected=CarParkException.class)
    public void testEnterCarparkFull() throws Exception {
    	carPark.enterTruck(); // fills car park
        EnterTruckCommand cmd = new EnterTruckCommand(carPark);
        
        cmd.execute(); // expect exception, park full
    }

    @Test
    public void testEnterCarparkSuccess() {
    	EnterTruckCommand cmd = new EnterTruckCommand(carPark);
        
    	cmd.execute();
        assertEquals(1, carPark.getParkedTrucks().size());
    }

}