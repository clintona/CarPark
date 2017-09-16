package au.com.tla.carpark;

public class CarParkException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CarParkException(String msg) {
        super(msg);
    }
}
