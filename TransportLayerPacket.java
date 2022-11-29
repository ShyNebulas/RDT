public class TransportLayerPacket {

    // Maybe remove these
    // You may need extra fields

    int seqnum;
    int acknum;
    byte[] data;

    // You may need extra methods

    public TransportLayerPacket(TransportLayerPacket pkt) {
      this.data = pkt.getData();
      this.seqnum = pkt.seqnum;
      this.acknum = pkt.acknum;
    }

    public TransportLayerPacket(byte[] data){
    this.data = data;
    }

    public void setSeqnum(int seqnum) {
        this.seqnum = seqnum;
    }

    public void setAcknum(int acknum) {
        this.acknum = acknum;
    }

    public byte[] getData() {
        return data;
    }

}
