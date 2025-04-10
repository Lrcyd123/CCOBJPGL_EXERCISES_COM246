class HybridPickup extends HybridVehicle {
    public HybridPickup(String carName) {
        super(carName);
    }
 
    @Override
    public void displayFeatures() {
        System.out.println("Hybrid Pickup:");
        super.displayFeatures();
    }
}