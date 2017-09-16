package au.com.tla.carpark;

import java.util.Date;

/**
 * A Transfer Object providing Balance Report data.
 * Most fields are directly accessible, getters are provided for calculated data.
 */
public class BalanceReport {

    int carsExited;
    int trucksExited;
    int parkingCars;
    int parkingTrucks;
    int spacesAvailable;
    int feesPaid;
    Date reportDate;

    public BalanceReport() {
        this.reportDate = new Date();
    }

    public int getCarsEntered() {
        return carsExited + parkingCars;
    }

    public int getTrucksEntered() {
        return trucksExited + parkingTrucks;
    }

    public String toString() {
        String crlf = System.lineSeparator();
        StringBuilder buf = new StringBuilder("Car Park report ").append(reportDate).append(crlf);
        buf.append("Cars Entered: ").append(getCarsEntered()).append(crlf);
        buf.append("Trucks Entered: ").append(getTrucksEntered()).append(crlf);
        buf.append("Cars Exited: ").append(carsExited).append(crlf);
        buf.append("Trucks Exited: ").append(trucksExited).append(crlf);
        buf.append("Parking Cars: ").append(parkingCars).append(crlf);
        buf.append("Parking Trucks: ").append(parkingTrucks).append(crlf);
        buf.append("Spaces available: ").append(spacesAvailable).append(crlf);
        buf.append("Fees paid: ").append(feesPaid).append(crlf);
        return buf.toString();
    }
}
