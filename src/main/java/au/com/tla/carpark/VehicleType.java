package au.com.tla.carpark;


public enum VehicleType {
    CAR(1),
    TRUCK(2);

    private int size;

    VehicleType(int size) {
        if (size < 1 || size > 2) {
            throw new IllegalArgumentException("Invalid vehicle size: " + size);
        }
        this.size = size;
    }

    public int getSize() { return this.size; }
}
