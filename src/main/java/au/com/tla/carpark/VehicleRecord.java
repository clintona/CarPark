package au.com.tla.carpark;

import java.util.Date;

/**
 * Record the duration of a vehicle in a car park.
 * Duration is finalised on exit, so a duration == 0 implies the vehicle is parked.
 */
public class VehicleRecord {
    private VehicleType type;
    private String vehicleId;
    private Date entryTime;
    private int duration;

    /**
     * Create a new VehicleRecord representing a vehicle entering the CarPark.
     */
    public VehicleRecord(VehicleType type, String vehicleId) {
        if (type == null) {
            throw new NullPointerException("vehicle type cannot be null");
        }
        if (vehicleId == null || vehicleId.trim().isEmpty()) {
            throw new IllegalArgumentException("invalid vehicle id");
        }
        this.entryTime = new Date();
        this.vehicleId = vehicleId;
        this.type = type;
    }

    public VehicleType getVehicleType() {
        return type;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isParked() {
        return duration < 1;
    }

    public int getSize() {
    	return type.getSize();
    }
}
