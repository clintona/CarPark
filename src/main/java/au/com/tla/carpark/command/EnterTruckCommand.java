package au.com.tla.carpark.command;

import au.com.tla.carpark.CarPark;
import au.com.tla.carpark.VehicleRecord;

public class EnterTruckCommand implements Command {
    private CarPark carPark;

    public EnterTruckCommand(CarPark carPark) {
        this.carPark = carPark;
    }

    public void execute() {
        VehicleRecord truck = carPark.enterTruck();
        System.out.println("Truck " + truck.getVehicleId() + " entered at " + truck.getEntryTime());
    }
}
