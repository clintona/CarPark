package au.com.tla.carpark;

import au.com.tla.carpark.command.Command;
import au.com.tla.carpark.command.CommandParser;
import au.com.tla.carpark.command.QuitCommand;

import java.util.Scanner;

/**
 * A Car Park Simulator program, for the command line.
 */
public class CarParkMain {

    CarPark carPark;
    CommandParser parser;

    public static void main(String[] args) {
        CarParkMain app = new CarParkMain(args);
        app.run();
    }

    public CarParkMain(String args[]) {
        // any option settings are processed here
        // Eg: options to change the hourly rates
    }

    /**
     * Run the simulation program, reading user input from the command line.
     */
    public void run() {
        // Nb: java 7 try-with-resources block - auto closes "resource"
        try (Scanner input = new Scanner(System.in)) {
            initialise(input);
            mainLoop(input);
        }
    }

    void initialise(Scanner input) {
        System.out.println("How many spaces does the car park have? ");
        int spaces = input.nextInt();
        input.nextLine();
        this.carPark = new CarParkImpl(spaces);
        this.parser = new CommandParser(carPark);
    }

    void mainLoop(Scanner input) {
        while (input.hasNextLine()) {
            try {
                String s = input.nextLine();
                Command cmd = parser.parse(s);
                cmd.execute();
                if (cmd instanceof QuitCommand) {
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
