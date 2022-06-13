package bb.roborally.data.messages;

public class Error implements Message {

    private String error;

    {
        error = "Whoops. That did not work. Try to adjust something.";
    }

    public Error() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toJson() {
        return toEnvelope().toJson();
    }

    @Override
    public Envelope toEnvelope() {
        return new Envelope(Envelope.MessageType.ERROR, this);    }
}
