package au.com.tla.carpark;


public enum VehicleType {
    CAR(1),
    TRUCK(2);

    private int size;

    VehicleType(int size) {
        this.size = size;
    }

    public int getSize() { return this.size; }
}
