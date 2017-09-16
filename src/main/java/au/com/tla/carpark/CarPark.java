package au.com.tla.carpark;

import java.util.List;

/**
 * Defines simple services to simulate a Car Park, including capacity and financial reporting.
 */
public interface CarPark {

    VehicleRecord enterCar() throws CarParkException;
    VehicleRecord enterTruck() throws CarParkException;
    VehicleRecord exitCar(String id, int duration);
    VehicleRecord exitTruck(String id, int duration);

    int getSpacesAvailable();

    List<VehicleRecord> getParkedCars();
    List<VehicleRecord> getParkedTrucks();

    List<VehicleRecord> getExitedCars();
    List<VehicleRecord> getExitedTrucks();

    int getExitedCarsDuration();
    int getExitedTrucksDuration();

    BalanceReport getBalanceReport(CarPark carPark);

    void setCarHourlyRate(int hourlyRate);
    void setTruckHourlyRate(int hourlyRate);
}
