public class App {
    public static void main(String[] args) throws Exception {
       
        HDMI hdmi = new HDMI();

        
        VGA connector = new VGAtoHDMI(hdmi);

       
        monitor monitor = new monitor();
        monitor.connect(connector);
    }
}