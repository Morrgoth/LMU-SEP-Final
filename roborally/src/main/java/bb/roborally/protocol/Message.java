package bb.roborally.protocol;

/**
 * @author Bence Ament
 */
public interface Message {
    public String toJson();
    public Envelope toEnvelope();

}
