public class Main {

    public static void main(String[] args) {
        NetworkSimulator sim = new NetworkSimulator(10, 0.0, 0.0, 10.0, false, 1);
    Sender sender = new Sender("sender", sim);
        Receiver receiver = new Receiver("receiver", sim);
        // TODO: Set the sender   (sim.setSender)
        sim.setSender(sender);
        // TODO: Set the receiver (sim.setReceiver)
        sim.setReceiver(receiver);
        sim.runSimulation();
    }

}
