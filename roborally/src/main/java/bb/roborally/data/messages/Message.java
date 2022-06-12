package bb.roborally.data.messages;

public interface Message {
    public String toJson();
    public Envelope toEnvelope();

   // private Envelope.MessageType messageType;
   // private Message messageBody;
   // public PlayerValuesLobby (MessageType messageType,Message messageBody){
}
