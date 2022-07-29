package bb.roborally.protocol.type_adapters.connection;

import bb.roborally.protocol.connection.Welcome;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * @author Veronika Heckel
 */
public class WelcomeTypeAdapter extends TypeAdapter<Welcome> {
    @Override
    public void write(JsonWriter jsonWriter, Welcome welcome) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("clientID").value(welcome.getClientID());
        jsonWriter.endObject();
    }

    @Override
    public Welcome read(JsonReader jsonReader) throws IOException {
        Welcome welcome = new Welcome();
        jsonReader.beginObject();
        String name;
        while (jsonReader.hasNext()){
        name = jsonReader.nextName();
            if (name.equals("clientID")) {
                welcome.setClientID(jsonReader.nextInt());
            }
        }
        jsonReader.endObject();
        return welcome;
    }
}
