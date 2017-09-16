package au.com.tla.carpark.command;

import au.com.tla.carpark.CarPark;
import au.com.tla.carpark.VehicleType;

/**
 * Translates user input from the command line into Command classes.
 */
public class CommandParser {
    private CarPark carPark;

    public CommandParser(CarPark carPark) {
        this.carPark = carPark;
    }

    public Command parse(String line) {
        String[] tokens = line.toUpperCase().split(" ");

        switch (tokens[0]) {
            case "ENTER":
                return getEnterCommand(tokens);
            case "EXIT":
                return getExitCommand(tokens);
            case "REPORT":
                return new ReportCommand(carPark);
            case "QUIT":
                return new QuitCommand();
            case "HELP":
                return new HelpCommand();
        }
        throw new IllegalArgumentException("Invalid command '" + line + "'");
    }

    private Command getEnterCommand(String[] tokens) {
        Command cmd = null;
        if (tokens.length < 2) {
            throw new IllegalArgumentException("ENTER command is missing vehicle type");
        }
        VehicleType type = VehicleType.valueOf(tokens[1].toUpperCase());
        if (type == VehicleType.CAR) {
            cmd = new EnterCarCommand(carPark);
        } else if (type == VehicleType.TRUCK) {
            cmd = new EnterTruckCommand(carPark);
        }
        return cmd;
    }

    private Command getExitCommand(String[] tokens) {
        Command cmd = null;
        if (tokens.length < 4) {
            throw new IllegalArgumentException("Invalid EXIT vehicle command.");
        }
        VehicleType type = VehicleType.valueOf(tokens[1].toUpperCase());
        if (type == VehicleType.CAR) {
            cmd = new ExitCarCommand(carPark, tokens[2], Integer.valueOf(tokens[3]));
        } else if (type == VehicleType.TRUCK) {
            cmd = new ExitTruckCommand(carPark, tokens[2], Integer.valueOf(tokens[3]));
        }
        return cmd;
    }

}
