package bb.roborally.data.messages.type_adapters.connection;

import bb.roborally.data.messages.connection.ConnectionUpdate;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class ConnectionUpdateTypeAdapter extends TypeAdapter<ConnectionUpdate> {

    @Override
    public void write(JsonWriter jsonWriter, ConnectionUpdate connectionUpdate) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("clientID").value(connectionUpdate.getClientID());
        jsonWriter.name("isConnected").value(connectionUpdate.isConnected());
        jsonWriter.name("action").value(connectionUpdate.getAction());
        jsonWriter.endObject();
    }

    @Override
    public ConnectionUpdate read(JsonReader jsonReader) throws IOException {
        ConnectionUpdate connectionUpdate = new ConnectionUpdate();
        jsonReader.beginObject();
        String name;
        while (jsonReader.hasNext()){
            name = jsonReader.nextName();
            if (name.equals("clientID")){
                connectionUpdate.setClientID(jsonReader.nextInt());
            }
            if (name.equals("isConnected")){
                connectionUpdate.setConnected(jsonReader.nextBoolean());
            }
            if (name.equals("action")){
                connectionUpdate.setAction(jsonReader.nextString());
            }
        }
        jsonReader.endObject();
        return connectionUpdate;
    }
}
