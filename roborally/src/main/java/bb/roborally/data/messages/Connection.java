package bb.roborally.data.messages;

public class Connection implements Message {

    private int clientID;
    private String group = "BlindeBonbons";
    private String protocol = "Version 0.1";
    private boolean isAI;
    private Envelope.MessageType messageType;

    public int getClientID() {
        return clientID;
    }

    public Connection(){
    }


    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group){
        this.group = group;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol){
        this.protocol = protocol;
    }

    public boolean isAI() {
        return isAI;
    }

    public void setAI(boolean AI) {
        isAI = AI;
    }

    public Envelope.MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType (Envelope.MessageType connectionType) {
        this.messageType = connectionType;
    }


    @Override
    public String toJson() {
        return this.toEnvelope().toJson();
    }

    @Override
    public Envelope toEnvelope() {
        return new Envelope(this.messageType, this);
    }
}

