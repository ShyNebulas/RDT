public class Main {

    public static void main(String[] args) {
        NetworkSimulator sim = new NetworkSimulator(10, 0.0, 0.2, 10.0, false, 0);
        Sender sender = new Sender("sender", sim);
        Receiver receiver = new Receiver("receiver", sim);
        sim.setSender(sender);
        sim.setReceiver(receiver);
        sim.runSimulation();
    }

}
