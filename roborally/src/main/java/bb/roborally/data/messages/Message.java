package bb.roborally.data.messages;

public interface Message {
    public String toJson();
    public Envelope toEnvelope();
}
