class monitor {
    public void connect(VGA adapter) {
    System.out.println("Monitor expecting VGA adapter..");
    adapter.connectWithVGA();
    System.out.println("Connected!");
    
    }
}