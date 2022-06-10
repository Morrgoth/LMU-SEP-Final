package bb.roborally.data.messages.type_adapters;

import bb.roborally.data.messages.Connection;
import bb.roborally.data.messages.Envelope;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class ConnectionTypeAdapter extends TypeAdapter<Connection> {

    @Override
    public void write(JsonWriter jsonWriter, Connection connection) throws IOException {
        jsonWriter.beginObject();
        if(connection.getMessageType() == Envelope.MessageType.HELLO_CLIENT){
            jsonWriter.name("protocol").value(connection.getProtocol());
        }else if(connection.getMessageType() == Envelope.MessageType.ALIVE){
            //ALIVE has empty Body
        }else if(connection.getMessageType() == Envelope.MessageType.HELLO_SERVER){
            jsonWriter.name("group").value(connection.getGroup());
            jsonWriter.name("isAI").value(connection.isAI());
            jsonWriter.name("protocol").value(connection.getProtocol());
        }else if(connection.getMessageType() == Envelope.MessageType.WELCOME){
            jsonWriter.name("clientID").value(connection.getClientID());
        }
        jsonWriter.endObject();
    }

    @Override
    public Connection read(JsonReader jsonReader) throws IOException {
        Connection connection = new Connection();
        jsonReader.beginObject();
        while (jsonReader.hasNext()){
            if(jsonReader.nextName().equals("clientID")){
                connection.setClientID(jsonReader.nextInt());
            }
            if(jsonReader.nextName().equals("protocol")){
                connection.setProtocol(jsonReader.nextString());
            }
            if(jsonReader.nextName().equals("group")){
                connection.setGroup(jsonReader.nextString());
            }
            if(jsonReader.nextName().equals("isAI")){
                connection.setAI(jsonReader.nextBoolean());
            }
            //ConnectionType - Enums müssen noch in die Envelope (Message-Type)
        }
        jsonReader.endObject();
        return null;
    }
}
