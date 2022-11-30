public class Receiver  extends TransportLayer{


    public Receiver(String name, NetworkSimulator simulator){
        super(name, simulator);
    }

    @Override
    public void init(){}

    @Override
    public void rdt_send(byte[] data) {}

    @Override
    public void rdt_receive(TransportLayerPacket pkt) {
        if (isCorrupt(pkt)) {
            byte[] data = new byte[1];
            System.out.println("Data corrupted - making NAK");
            TransportLayerPacket nakPkt = makePkt(data);
            System.out.println("Sending NAK to network layer\n");
            simulator.sendToNetworkLayer(this, nakPkt);
        }
        else{
            simulator.sendToApplicationLayer(this, pkt.getData());
            System.out.println("Sent to App Layer\n");
        }
    }

    @Override
    public void timerInterrupt() {
    }
}
