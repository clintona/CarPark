package au.com.tla.carpark.command;

import au.com.tla.carpark.CarPark;
import au.com.tla.carpark.VehicleRecord;

public class ExitCarCommand implements Command {
    private CarPark carPark;
    private int duration;
    private String vehicleId;

    public ExitCarCommand(CarPark carPark, String id, int duration) {
        this.carPark = carPark;
        this.duration = duration;
        this.vehicleId = id;
    }

    public void execute() {
        VehicleRecord car = carPark.exitCar(vehicleId, duration);
        System.out.println("Car " + car.getVehicleId() + " exited after " + duration + " hours.");
    }
}
