package au.com.tla.carpark;

import java.util.ArrayList;
import java.util.List;

/**
 * Car Park implementation.
 * Manages a List of VehicleRecords
 */

public class CarParkImpl implements CarPark {
    private List<VehicleRecord> records;
    private int maxSize;
    private int nextId;
    private int carHourlyRate = 2;
    private int truckHourlyRate = 3;


    public CarParkImpl(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("size must be > 0");
        }
        this.maxSize = size;
        this.records = new ArrayList<>();
        this.nextId = 1;
    }


    public VehicleRecord enterCar() {
        return enterVehicle(VehicleType.CAR);
    }

    public VehicleRecord enterTruck() {
        return enterVehicle(VehicleType.TRUCK);
    }

    public VehicleRecord exitCar(String id, int duration) {
        return exitVehicle(id, VehicleType.CAR, duration);
    }

    public VehicleRecord exitTruck(String id, int duration) {
        return exitVehicle(id, VehicleType.TRUCK, duration);
    }

    public List<VehicleRecord> getParkedCars() {
        return getParkedVehicles(VehicleType.CAR);
    }

    public List<VehicleRecord> getParkedTrucks() {
        return getParkedVehicles(VehicleType.TRUCK);
    }

    public List<VehicleRecord> getExitedCars() {
        return getExitedVehicles(VehicleType.CAR);
    }

    public List<VehicleRecord> getExitedTrucks() {
        return getExitedVehicles(VehicleType.TRUCK);
    }

    int getNextId() {
        return nextId++;
    }

    void throwExceptionIfCannotAccept(VehicleType type) {
        if (getSpacesAvailable() < type.getSize()) {
            throw new CarParkException("The carpark cannot fit a " + type + " now");
        }
    }

    VehicleRecord enterVehicle(VehicleType vehicleType) {
        throwExceptionIfCannotAccept(vehicleType);
        VehicleRecord record = new VehicleRecord(vehicleType, Integer.toString(getNextId()));
        this.records.add(record);
        return record;
    }

    void throwExceptionIfExited(VehicleRecord vehicle) {
        if (!vehicle.isParked()) {
            throw new IllegalStateException(vehicle.getVehicleType() + " " + vehicle.getVehicleId() + " has already exited");
        }
    }

    VehicleRecord exitVehicle(String id, VehicleType type, int duration) {
        if (duration < 1) {
            throw new IllegalArgumentException("duration must be > 0");
        }
        VehicleRecord vehicle = getVehicle(id, type);
        throwExceptionIfExited(vehicle);
        vehicle.setDuration(duration);
        return vehicle;
    }

    VehicleRecord getVehicle(String id, VehicleType type) {
        for (VehicleRecord vehicle : records) {
            if (vehicle.getVehicleId().equals(id) && vehicle.getVehicleType() == type) {
                return vehicle;
            }
        }
        throw new CarParkException("Unknown " + type + " with id " + id);
    }

    int getSpacesUsed() {
        int usedSpaces = 0;
        for (VehicleRecord record : records) {
            if (record.getDuration() < 1) { // vehicle is parked if duration == 0
                usedSpaces += record.getVehicleType().getSize();
            }
        }
        return usedSpaces;
    }

    public int getSpacesAvailable() {
        return this.maxSize - getSpacesUsed();
    }


    public List<VehicleRecord> getParkedVehicles(VehicleType type) {
        List<VehicleRecord> parkedVehicles = new ArrayList<>();
        // This is Java 7 code, so no closures. Also no apache collections utils.
        for (VehicleRecord vehicle : records) {
            if (vehicle.isParked() && vehicle.getVehicleType() == type) {
                parkedVehicles.add(vehicle);
            }
        }
        return parkedVehicles;
    }

    public List<VehicleRecord> getExitedVehicles(VehicleType type) {
        List<VehicleRecord> exitedVehicles = new ArrayList<>();
        // This is Java 7 code, so no closures. Also no apache collections utils.
        for (VehicleRecord vehicle : records) {
            if (!vehicle.isParked() && vehicle.getVehicleType() == type) {
                exitedVehicles.add(vehicle);
            }
        }
        return exitedVehicles;
    }

    int sumDuration(List<VehicleRecord> vehicles) {
        int totalDuration = 0;
        for (VehicleRecord vehicle : vehicles) {
            totalDuration += vehicle.getDuration();
        }
        return totalDuration;
    }

    public int getExitedCarsDuration() {
        return sumDuration(getExitedCars());
    }

    public int getExitedTrucksDuration() {
        return sumDuration(getExitedTrucks());
    }

    public BalanceReport getBalanceReport(CarPark carPark) {
        BalanceReport report = new BalanceReport();
        report.carsExited = carPark.getExitedCars().size();
        report.trucksExited = carPark.getExitedTrucks().size();
        report.parkingCars = carPark.getParkedCars().size();
        report.parkingTrucks = carPark.getParkedTrucks().size();
        report.spacesAvailable = carPark.getSpacesAvailable();
        report.feesPaid = carPark.getExitedCarsDuration() * carHourlyRate + carPark.getExitedTrucksDuration() * truckHourlyRate;
        return report;
    }

    public void setCarHourlyRate(int hourlyRate) {
        carHourlyRate = validateHourlyRate(hourlyRate);
    }

    public void setTruckHourlyRate(int hourlyRate) {
        truckHourlyRate = validateHourlyRate(hourlyRate);
    }

    private int validateHourlyRate(int hourlyRate) {
        if (hourlyRate < 0) { // allow for free parking
            throw new IllegalArgumentException("Hourly rate must be >= 0");
        }
        return hourlyRate;
    }
}
