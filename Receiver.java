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
            byte[] data = new byte[]{ (byte) 0};
rdt_send(data);
        } else {
            byte[] data = pkt.getData();
            simulator.sendToApplicationLayer(this, data);
        }
    }

    @Override
    public void timerInterrupt() {

    }

    public TransportLayerPacket makeAck(){

        byte[] data = new byte[]{ (byte) 0};
        return makePkt(data, makeChecksum(data));
    }
}
