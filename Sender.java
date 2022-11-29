public class Sender extends TransportLayer{

    public Sender(String name, NetworkSimulator simulator){
        super(name, simulator);
    }

    @Override
    public void init() {

    }

    @Override
    public void rdt_send(byte[] data) {
        TransportLayerPacket pkt = makePkt(data);
        simulator.sendToNetworkLayer(this, pkt);
    }

    @Override
    public void rdt_receive(TransportLayerPacket pkt) {

    }

    @Override
    public void timerInterrupt() {

    }
}
