package au.com.tla.carpark.command;

import au.com.tla.carpark.CarPark;
import au.com.tla.carpark.VehicleRecord;

public class ExitTruckCommand implements Command {
    private CarPark carPark;
    private int duration;
    private String vehicleId;

    public ExitTruckCommand(CarPark carPark, String id, int duration) {
        this.carPark = carPark;
        this.duration = duration;
        this.vehicleId = id;
    }

    public void execute() {
        VehicleRecord truck = carPark.exitTruck(vehicleId, duration);
        System.out.println("Truck " + truck.getVehicleId() + " exited after " + duration + " hours.");
    }
}
