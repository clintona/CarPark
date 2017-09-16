package au.com.tla.carpark.command;

import au.com.tla.carpark.BalanceReport;
import au.com.tla.carpark.CarPark;

public class ReportCommand implements Command {
    private CarPark carPark;

    public ReportCommand(CarPark carPark) {
        this.carPark = carPark;
    }

    public void execute() {
        BalanceReport report = carPark.getBalanceReport(carPark);
        System.out.println(report);
    }
}
