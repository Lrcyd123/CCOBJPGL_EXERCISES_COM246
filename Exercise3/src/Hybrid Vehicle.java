class HybridVehicle extends Hybrid {
    String carName;
 
    public HybridVehicle(String carName) {
        this.carName = carName;
    }
 
    public void displayFeatures() {
        System.out.println("Car Name: " + carName);
        chargebattery();
        fillgas();
    }
}