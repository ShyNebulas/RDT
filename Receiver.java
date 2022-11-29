public class Receiver  extends TransportLayer{


    public Receiver(String name, NetworkSimulator simulator){
        super(name, simulator);
    }

    @Override
    public void init() {

    }

    @Override
    public void rdt_send(byte[] data) {


    }

    @Override
    public void rdt_receive(TransportLayerPacket pkt) {
        byte[] data = pkt.getData();
        simulator.sendToApplicationLayer(this, data);
    }

    @Override
    public void timerInterrupt() {

    }
}
