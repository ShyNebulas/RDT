import java.util.ArrayList;
public class Sender extends TransportLayer{

    ArrayList<byte[]> dataQueue;

    public Sender(String name, NetworkSimulator simulator){
        super(name, simulator);
    }

    @Override
    public void init() {
        dataQueue = new ArrayList<>();
    }

    @Override
    public void rdt_send(byte[] data) { //Add message to the queue and send it
        dataQueue.add(data);

            TransportLayerPacket pkt = makePkt(dataQueue.get(0));
        System.out.println("Sending to network layer\n");
            simulator.sendToNetworkLayer(this, pkt);

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
            }

        }


    @Override
    public void timerInterrupt() {
    }
}
