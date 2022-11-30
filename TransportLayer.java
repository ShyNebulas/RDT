import java.util.zip.CRC32;
import java.util.zip.Checksum;

public abstract class TransportLayer {
    String name;
    NetworkSimulator simulator;

    public TransportLayer(String name, NetworkSimulator simulator) {
        this.name = name;
        this.simulator = simulator;
    }

    public abstract void init();

    public abstract void rdt_send(byte[] data);

    public abstract void rdt_receive(TransportLayerPacket pkt);

    public abstract void timerInterrupt();

    public String getName() {
        return this.name;
    }

    /** Creates a new checksum and returns its value as a long
     *
     * @param data The data used in the checksums creation
     *
     */
    public long makeChecksum(byte[] data){
        Checksum checksum = new CRC32();
        checksum.update(data);
        return checksum.getValue();
    }

    public TransportLayerPacket makePkt(byte[] data){
        return new TransportLayerPacket(data, makeChecksum(data));
    }

    /**
     * Checks if the actual checksum matches the correct checksum,
     * if no then the packet was corrupted
     * */
    public boolean isCorrupt(TransportLayerPacket pkt){
        long checksum = pkt.getChecksum();
        long newChecksum = makeChecksum(pkt.getData());
        System.out.println("Corruption checker\nCorrect Checksum: " + newChecksum + "\nActual Checksum: " + pkt.getChecksum() + "\n");
        return (newChecksum != checksum);
    }

}
