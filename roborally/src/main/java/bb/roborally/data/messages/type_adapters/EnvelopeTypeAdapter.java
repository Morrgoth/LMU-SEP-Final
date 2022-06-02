package bb.roborally.data.messages.type_adapters;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.LoginConfirmation;
import bb.roborally.data.messages.LoginRequest;
import bb.roborally.data.messages.Message;
import bb.roborally.data.util.User;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class EnvelopeTypeAdapter extends TypeAdapter<Envelope> {
    @Override
    public void write(JsonWriter jsonWriter, Envelope envelope) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("messageType");
        jsonWriter.value(envelope.getMessageType());
        jsonWriter.name("messageBody");
        if (envelope.getMessageType().equals("LoginRequest")) {
            new LoginRequestTypeAdapter().write(jsonWriter, (LoginRequest) envelope.getMessageBody());
        } else if (envelope.getMessageType().equals("LoginConfirmation")) {
            new LoginConfimationTypeAdapter().write(jsonWriter, (LoginConfirmation) envelope.getMessageBody());
        } else {
            // TODO: Error handling
        }
        jsonWriter.endObject();
    }

    @Override
    public Envelope read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        Envelope envelope = null;
        String fieldName = null;
        if ("messageType".equals(jsonReader.nextName())) {
            envelope = new Envelope();
            envelope.setMessageType(jsonReader.nextString());
            if ("messageBody".equals(jsonReader.nextName())) {
                if (envelope.getMessageType().equals("LoginRequest")) {
                    envelope.setMessageBody(new LoginRequestTypeAdapter().read(jsonReader));
                } else if (envelope.getMessageType().equals("LoginConfirmation")) {
                    envelope.setMessageBody(new LoginConfimationTypeAdapter().read(jsonReader));
                } else {
                    // TODO: Error handling
                    envelope.setMessageBody(null);
                }
            }
        }
        return envelope;
    }
}
