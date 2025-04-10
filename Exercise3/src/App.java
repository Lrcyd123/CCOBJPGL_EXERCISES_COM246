public class App {
    public static void main(String[] args) {
        HybridSedan sedan = new HybridSedan("Toyota Prius");
        HybridPickup pickup = new HybridPickup("Ford Maverick");
 
        sedan.displayFeatures();
        System.out.println();
        pickup.displayFeatures();


        Carwash wash1 = new Carwash();
        Carwash wash2 = new Carwash();
 
        wash1.wash(sedan);
        wash2.wash(pickup);
    }


}