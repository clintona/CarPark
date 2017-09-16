package au.com.tla.carpark.command;

import au.com.tla.carpark.CarPark;
import au.com.tla.carpark.CarParkImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandParserTest {
    private CommandParser parser;

    @Before
    public void setUp() {
        CarPark carPark = new CarParkImpl(3);
        this.parser = new CommandParser(carPark);
    }

    @Test
    public void parseEnterCarSuccess() throws Exception {
        Command cmd = parser.parse("ENTER CAR");
        assertTrue (cmd instanceof EnterCarCommand);
    }

    @Test
    public void parseEnterTruckSuccess() throws Exception {
        Command cmd = parser.parse("ENTER TRUCK");
        assertTrue (cmd instanceof EnterTruckCommand);
    }

    @Test(expected=IllegalArgumentException.class)
    public void parseEnterFailure() {
        parser.parse("ENTER ");
    }

    @Test(expected=IllegalArgumentException.class)
    public void parseEnterInvalidVehicle() throws Exception {
        parser.parse("ENTER THE DRAGON");
    }

    @Test
    public void parseExitTruckSuccess() throws Exception {
        Command cmd = parser.parse("EXIT TRUCK 1 2");
        assertTrue (cmd instanceof ExitTruckCommand);
    }

    @Test(expected=IllegalArgumentException.class)
    public void parseExitFailure() {
        parser.parse("EXIT TRUCK STOP");
    }

    @Test(expected=IllegalArgumentException.class)
    public void parseExitInvalidVehicle() throws Exception {
        parser.parse("EXIT STAGE LEFT");
    }

    @Test
    public void parseReportSuccess() throws Exception {
        Command cmd = parser.parse("REPORT");
        assertTrue (cmd instanceof ReportCommand);

        cmd = parser.parse("report");
        assertTrue (cmd instanceof ReportCommand);
    }

    @Test
    public void parseHelpCommand() throws Exception {
        Command cmd = parser.parse("HELP");
        assertTrue (cmd instanceof HelpCommand);
    }


    @Test
    public void parseQuitCommand() throws Exception {
        Command cmd = parser.parse("QUIT");
        assertTrue (cmd instanceof QuitCommand);
    }
}