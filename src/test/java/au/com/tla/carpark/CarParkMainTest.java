package au.com.tla.carpark;

import java.io.ByteArrayInputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class CarParkMainTest {
    private CarParkMain main;

    @Before
    public void setup() {
        String[] args = new String[] {};
        this.main = new CarParkMain(args);
    }

    @Test
    public void testSuccessMain() throws Exception {
        String crlf = System.lineSeparator();
        String carParkSizeInput = "3" + crlf + "EXIT" + crlf;
        System.setIn(new ByteArrayInputStream(carParkSizeInput.getBytes()));

        CarParkMain.main(new String[] {});
    }

    @Test
    public void testInitialiseSuccess() throws Exception {
        String carParkSizeInput = "3" + System.lineSeparator();
        System.setIn(new ByteArrayInputStream(carParkSizeInput.getBytes()));
        Scanner in = new Scanner(System.in);

        main.initialise(in);
    }

    @Test(expected = InputMismatchException.class)
    public void testInitialiseFailure() throws Exception {
        String carParkSizeInput = "BLAH" + System.lineSeparator();
        System.setIn(new ByteArrayInputStream(carParkSizeInput.getBytes()));
        Scanner in = new Scanner(System.in);

        main.initialise(in);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInitialiseInvalidSpaces() throws Exception {
        String carParkSizeInput = "0" + System.lineSeparator();
        System.setIn(new ByteArrayInputStream(carParkSizeInput.getBytes()));
        Scanner in = new Scanner(System.in);

        main.initialise(in);
    }

    @Test
    public void testMainLoopSuccess() throws Exception {
        testInitialiseSuccess();
        System.setIn(new ByteArrayInputStream("QUIT".getBytes()));
        Scanner in = new Scanner(System.in);

        main.mainLoop(in);
    }

    @Test
    public void testSuccessfulRun() {
        String crlf = System.lineSeparator();
        String carParkSizeInput = "3" + crlf + "EXIT" + crlf;
        System.setIn(new ByteArrayInputStream(carParkSizeInput.getBytes()));

        main.run();
    }

    @Test
    public void testMainLoopException() {
        String crlf = System.lineSeparator();
        String carParkSizeInput = "3" + crlf + "BLAH" + crlf + "QUIT" + crlf;
        System.setIn(new ByteArrayInputStream(carParkSizeInput.getBytes()));

        main.run();
    }

}