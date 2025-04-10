class HybridSedan extends HybridVehicle {
    public HybridSedan(String carName) {
        super(carName);
    }
 
    @Override
    public void displayFeatures() {
        System.out.println("Hybrid Sedan:");
        super.displayFeatures();
    }
}