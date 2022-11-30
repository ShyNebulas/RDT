import java.util.ArrayList;
public class Sender extends TransportLayer{

    ArrayList<byte[]> dataQueue;
    boolean isFirst;

    public Sender(String name, NetworkSimulator simulator){
        super(name, simulator);
    }

    @Override
    public void init() {
        dataQueue = new ArrayList<>();
        isFirst = true;
    }

    @Override
    public void rdt_send(byte[] data) { //Add message to the queue and send it if it is the first message
        dataQueue.add(data);

        if (isFirst){
            TransportLayerPacket pkt = makePkt(dataQueue.get(0));
            simulator.sendToNetworkLayer(this, pkt);
        }
    }

    @Override
    public void rdt_receive(TransportLayerPacket pkt) {
        if (pkt.getData()[0] == 0 || isCorrupt(pkt)) { //If the packet is a NAK or the packet is corrupt then send a duplicate packet
            System.out.println("NAK or corrupt ACK received by Sender\nCreating duplicate");
            TransportLayerPacket dupPkt = makePkt(dataQueue.get(0)); //Create a packet with the current data
            System.out.println("Resending Duplicate\n");
            simulator.sendToNetworkLayer(this, dupPkt);
        }
        else {
            dataQueue.remove(0);
            System.out.println("ACK Received by sender\n");
            if (!dataQueue.isEmpty()) {
                System.out.println("Making next packet");
                TransportLayerPacket nextPkt = makePkt(dataQueue.get(0));
                System.out.println("Sending to network layer\n");
                simulator.sendToNetworkLayer(this, nextPkt);
            }

        }
    }

    @Override
    public void timerInterrupt() {
    }
}
