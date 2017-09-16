package au.com.tla.carpark.command;

import au.com.tla.carpark.CarPark;
import au.com.tla.carpark.VehicleRecord;

public class EnterCarCommand implements Command {
    private CarPark carPark;

    public EnterCarCommand(CarPark carPark) {
        this.carPark = carPark;
    }

    public void execute() {
        VehicleRecord car = carPark.enterCar();
        System.out.println("Car " + car.getVehicleId() + " entered at " + car.getEntryTime());
    }
}
