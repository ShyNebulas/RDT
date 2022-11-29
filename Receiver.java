public class Receiver  extends TransportLayer{


    public Receiver(String name, NetworkSimulator simulator){
        super(name, simulator);
    }

    @Override
    public void init() {

    }

    @Override
    public void rdt_send(byte[] data) {
        TransportLayerPacket pkt =  makePkt(data, makeChecksum(data));
        simulator.sendToNetworkLayer(this, pkt);
    }

    @Override
    public void rdt_receive(TransportLayerPacket pkt) {
        if (corrupt(pkt)) {
            byte[] data = new byte[1];
rdt_send(data);
        } else {
            byte[] data = pkt.getData();
            simulator.sendToApplicationLayer(this, data);
        }
    }

    @Override
    public void timerInterrupt() {

    }
}
