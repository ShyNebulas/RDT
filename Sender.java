public class Sender extends TransportLayer{
TransportLayerPacket pktCopy;

    public Sender(String name, NetworkSimulator simulator){
        super(name, simulator);
    }

    @Override
    public void init() {

    }

    @Override
    public void rdt_send(byte[] data) {
        TransportLayerPacket pkt = makePkt(data, makeChecksum(data));
        pktCopy = pkt;
        simulator.sendToNetworkLayer(this, pkt);
    }

    @Override
    public void rdt_receive(TransportLayerPacket pkt) {
        if (pkt.getData()[0] == 0){
        rdt_send(pktCopy.getData());
        }
        else{
            System.out.println("Sent successfully");
        }
    }

    @Override
    public void timerInterrupt() {

    }
}
